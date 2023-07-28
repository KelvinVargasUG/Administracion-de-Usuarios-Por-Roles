import {Component, DoCheck, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {UsuarioService} from 'src/app/Service/Usuario/usuario.service';
import {Usuario} from 'src/app/Entidades/Usuario.entidad';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Rol} from 'src/app/Entidades/Rol.entidad';
import {RolService} from "../../../../Service/Rol/rol.service";

@Component({
  selector: 'app-tabla-usuario',
  templateUrl: './tabla-usuario.component.html',
  styleUrls: ['./tabla-usuario.component.scss'],
})
export class TablaUsuarioComponent implements OnInit {
  usuarios!: Usuario[];
  roles!: Rol[];
  rolesUsuario!: Rol[];
  formUsuario!: FormGroup;

  idUsuario!: number;

  totalPagesArray: number[] = [];
  totalPages: number=2;
  pageActual: number = 1;


  constructor(
    private usuarioService: UsuarioService,
    private rolService: RolService,
    private formBuilder: FormBuilder
  ) {
    this.buildForm();
  }

  ngOnInit(): void {
    this.cargarData(this.pageActual);
    this.getAllRol();
    this.extraerNumeroPages();
  }

  extraerNumeroPages() {
    for (let i = 1; i <= this.totalPages; i++) {
      this.totalPagesArray.push(i);
    }
  }

  cargarData(pageActual: number) {
    this.getAllUsuarios(pageActual);
  }

  getRolesUser(roles: any[], idUsuario: any) {
    this.rolesUsuario = roles;
    this.idUsuario = idUsuario;
  }

  deleteRolUser(idRol: number) {
    this.rolService.deletelRol(this.idUsuario, idRol).subscribe(
      {
        next: (data: any) => {
          let usuario = this.usuarios.find((user: any) =>
            user.idUsuario === this.idUsuario
          )
          if (usuario) {
            usuario.roles = usuario.roles.filter((rol: any) => rol.idRol !== idRol)

            const indexRol = this.rolesUsuario.findIndex(
              (rol) => rol.idRol !== idRol
            );
            if (indexRol !== -1) {
              this.rolesUsuario.splice(indexRol, 1);
              alert('Rol Eliminado Correctamente');
            }
          }
        }
      }
    )
  }

  mostrarRolesEnTabla(roles: any[]): string {
    return roles.map(rol => rol.nombre).join(' - ');
  }

  getAllRol() {
    this.rolService.getAllRol().subscribe(
      {
        next: (data: any) => {
          this.roles = data;
        },
        error: (error) => {
          console.log("Extraer todos los roles " + error)
        }
      }
    )
  }

  getAllUsuarios(pageActual: number) {
    const params = {
      page: pageActual - 1,
      size: 1
    }
    this.usuarioService.getAllUsuario(params).subscribe({
      next: (data: any) => {
        this.usuarios = [];
        this.totalPages = data.body.totalPages;

        data.body.content.forEach((el: any) => {
          this.usuarios.push(el)
        })
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
            this.usuarios.unshift(data);
            this.buildForm();
            alert(
              data.nombre + ' ' + data.apellido + ' Guardado Correctamente'
            );
          }
        },
      });
    }
  }

}
