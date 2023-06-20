import {Component, OnChanges, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from 'src/app/Service/Login/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) {
  }

  CerrarSession() {
    this.loginService.cerrarSession();
    this.router.navigate(['./login'])
  }

  isAdmin(): boolean {
    if (this.loginService.getUserRol() == 'Admin') {
      return true

    } else {
      return false;
    }
  }

  ngOnInit(): void {
  }
}
