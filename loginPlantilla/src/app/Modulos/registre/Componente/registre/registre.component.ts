import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-registre',
  templateUrl: './registre.component.html',
  styleUrls: ['./registre.component.scss'],
})
export class RegistreComponent {
  formUsuario!: FormGroup;

  constructor(private formBuilder: FormBuilder,
     private router: Router,
     private usuarioService: UsuarioService) {
    this.buildForm();
  }

  irLogin() {
    this.router.navigate(['./login']);
  }

  saveUsuario() {
    this.usuarioService.getAllProducts()
    if (this.formUsuario.valid) {
      const usuario = this.formUsuario.value;
      console.log(this.formUsuario.value)
      console.log(this.usuarioService.getAllProducts())
      this.usuarioService.createUsuario(usuario)
      .subscribe();
      
    }
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }
}
