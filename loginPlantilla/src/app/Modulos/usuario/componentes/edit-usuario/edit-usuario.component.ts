import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Rol } from 'src/app/Entidades/Rol.entidad';
import { Usuario } from 'src/app/Entidades/Usuario.entidad';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-edit-usuario',
  templateUrl: './edit-usuario.component.html',
  styleUrls: ['./edit-usuario.component.scss'],
})
export class EditUsuarioComponent implements OnInit {
  usuario!: Usuario;
  formUsuario!: FormGroup;
  id!: number;

  constructor(
    private usuarioService: UsuarioService,
    private formBuilder: FormBuilder,
    private activeRouter: ActivatedRoute,
    private router: Router,
  ) {
    this.buildForm();
  }
  ngOnInit(): void {
    this.extraerID();
  }

  getUsuarioById() {
    this.usuarioService.getUsuarioById(this.id).subscribe(
      {next:(data:any)=>{
        this.usuario=data;
      },
    complete:()=>{ 
      this.formUsuario.patchValue({
        nombre:this.usuario.nombre,
        apellido:this.usuario.apellido,
        email:this.usuario.email,
        estado:this.usuario.estado,
        rol:this.usuario.roles[0].idRol
      })
    }}
      );
  }

  extraerID() {
    this.activeRouter.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.getUsuarioById();
    });
  }

  updateUsuario() {
    if (this.formUsuario.valid) {
      const rol: Rol = {
        nombre: '',
        idRol: this.formUsuario.get('rol')?.value,
      };

      const usuario: Usuario = {
        idUsuario:this.usuario.idUsuario,
        nombre: this.formUsuario.get('nombre')?.value,
        apellido: this.formUsuario.get('apellido')?.value,
        email: this.formUsuario.get('email')?.value,
        password: this.usuario.password,
        estado: this.formUsuario.get('estado')?.value,
        roles: [rol],
      };

      this.usuarioService.updateUsuario(usuario).subscribe(
        {complete:()=>{
          this.irUsuario()
        }}
      );
    }
    
    
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      estado: ['', [Validators.required]],
      rol: ['', [Validators.required]],
    });
  }

  irUsuario(){
    this.router.navigate(['./usuario'])
  }
}
