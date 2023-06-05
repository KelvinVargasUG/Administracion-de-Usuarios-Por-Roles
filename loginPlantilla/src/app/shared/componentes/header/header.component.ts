import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/Service/Login/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private loginService:LoginService, private router:Router){}

  CerrarSession(){
    this.loginService.cerrarSession();
    this.router.navigate(['./login'])
  }
}
