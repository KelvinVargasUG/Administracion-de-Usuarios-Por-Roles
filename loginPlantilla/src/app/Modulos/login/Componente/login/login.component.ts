import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Service/Login/login.service';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  formUsuario!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService
  ) {
    this.buildForm();
  }
  
  ngOnInit(): void {
    if(this.loginService.isLoggedIn()){
      this.router.navigate(["./home"])
    }else{
    this.loginService.cerrarSession();
  }
}

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  irRegistro() {
    this.router.navigate(['./registre']);
  }

  IniciarSession() {
    if (this.formUsuario.valid) {
      const usuario = this.formUsuario.value;
      this.loginService.generateToken(usuario).subscribe({
        next: (data: any) => {
          this.loginService.loginUser(data.token);
          this.loginService.getCurrentUser().subscribe({
            next: (user) => {
              this.loginService.setUser(user);
              
              if (this.loginService.getUserRol() == 'Admin') {
                this.router.navigate(['./home']);
                this.loginService.loginStatusSubjec.next(true);
              } else if (this.loginService.getUserRol() == 'User') {
                this.router.navigate(['./home']);
                this.loginService.loginStatusSubjec.next(true);
              } else {
                this.loginService.cerrarSession();
              }
            },
            error: (error) => {
            },
          });
        },
        error: (error) => {
          alert('Datos invalidos, intente de nuevo');

        },
      });
    }
  }
}
