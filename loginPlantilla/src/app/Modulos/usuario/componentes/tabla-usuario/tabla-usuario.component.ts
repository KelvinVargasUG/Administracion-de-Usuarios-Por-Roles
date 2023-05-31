import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';
import { Usuario } from 'src/app/Entidades/Usuario.entidad';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Rol } from 'src/app/Entidades/Rol.entidad';

@Component({
  selector: 'app-tabla-usuario',
  templateUrl: './tabla-usuario.component.html',
  styleUrls: ['./tabla-usuario.component.scss'],
})
export class TablaUsuarioComponent implements OnInit {
  usuarios!: Usuario[];
  formUsuario!: FormGroup;

  constructor(
    private usuarioService: UsuarioService,
    private formBuilder: FormBuilder
  ) {
    this.buildForm();
  }

  ngOnInit(): void {
    this.getAllUsuarios();
  }

  getAllUsuarios() {
    this.usuarioService.getAllUsuario().subscribe({
      next: (data: Usuario[]) => {
        this.usuarios = data;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  deleteUsuario(id: number) {
    this.usuarioService.deleteUsuario(id).subscribe({
      next: (data: any) => {
        if (data.status == 200) {
          const usuariosActualizados = this.usuarios.filter(
            (usuario) => usuario.idUsuario !== id
          );
          this.usuarios = [...usuariosActualizados];
          alert('Eliminado Correctamente');
        }
      },
      error: (error) => {
        if (error.status == 404) {
          {
            alert('El usuario no existe');
          }
        }
      },
    });
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      estado: ['', [Validators.required]],
      rol: ['', [Validators.required]],
    });
  }

  saveUsuario() {
    if (this.formUsuario.valid) {
      const rol: Rol = {
        nombre: '',
        idRol: this.formUsuario.get('rol')?.value,
      };

      const usuario: Usuario = {
        nombre: this.formUsuario.get('nombre')?.value,
        apellido: this.formUsuario.get('apellido')?.value,
        email: this.formUsuario.get('email')?.value,
        password: this.formUsuario.get('password')?.value,
        estado: this.formUsuario.get('estado')?.value,
        roles: [rol],
      };

      this.usuarioService.createUsuario(usuario).subscribe({
        next: (data: any) => {
          if (data.nombre != null) {
            this.usuarios.push(usuario);
            alert(
              data.nombre + ' ' + data.apellido + ' Guardado Correctamente'
            );
          }
        },
      });
    }
  }
}
