import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';
import {LoginService} from "../../../../Service/Login/login.service";

@Component({
  selector: 'app-registre',
  templateUrl: './registre.component.html',
  styleUrls: ['./registre.component.scss'],
})
export class RegistreComponent {
  formUsuario!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService:LoginService
  ) {
    this.loginService.cerrarSession();
    this.buildForm();
  }

  irLogin() {
    this.router.navigate(['./login']);
  }

  saveUsuario() {
    if (this.formUsuario.valid) {
      const usuario = this.formUsuario.value;
      this.loginService.registreUsuario(usuario).subscribe({
        next: (data) => {
          if (data == null) {
            alert('El email ya existe');
          } else {
            alert('Usuario Registrado');
          }
        },
        error: (error) => {
          alert('El email proporsionado ya existe');
        },
      });
      this.irLogin();
    }
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
