import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  formUsuario!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private usuarioService: UsuarioService
  ) {
    this.buildForm();
  }

  irRegistro() {
    this.router.navigate(['./registre']);
  }

  IniciarSession() {
    if (this.formUsuario.valid) {
      const usuario = this.formUsuario.value;
      console.log(usuario);
    }
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
