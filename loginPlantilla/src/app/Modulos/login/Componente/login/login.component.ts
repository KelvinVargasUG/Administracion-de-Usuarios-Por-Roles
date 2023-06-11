import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginService} from 'src/app/Service/Login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  formUsuario!: FormGroup;
  user!:any;
  ocultarBtnLongIn:boolean=true;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService
  ) {
  }

  ngOnInit(): void {
   this.checkLoggedInStatus();
   this.buildForm();
  }

  checkLoggedInStatus() {
    if (this.loginService.isLoggedIn()) {
      this.router.navigate(["./home"]);
    } else {
      this.loginService.cerrarSession();
    }
  }

  private buildForm() {
    this.formUsuario = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
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
            next: (user: any) => {
              this.comprobarRoles(user);
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

  comprobarRoles(user: any) {
    if (user.authorities.length === 1) {
      this.guardarUsuario(user);
    }else {
      this.user=user;
      this.ocultarBtnLongIn=false;
    }
  }

  guardarUsuario(user: any) {
    this.loginService.setUser(user);
    const userRol = this.loginService.getUserRol();
    if (userRol === 'Admin' || userRol === 'User') {
      this.router.navigate(['./home']);
      this.loginService.loginStatusSubjec.next(true);
    } else {
      this.loginService.cerrarSession();
    }
  }

  extraerSeleccion(user: any) {
      this.guardarUsuario(user);
  }

  cancelarSeleccion(cancelar: boolean) {
    if(cancelar===true){
      this.loginService.cerrarSession();
      this.buildForm();
      this.ocultarBtnLongIn=true;

    }
  }
}
