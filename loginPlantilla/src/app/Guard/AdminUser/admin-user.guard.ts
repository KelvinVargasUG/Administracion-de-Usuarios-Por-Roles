import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree , Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/Service/Login/login.service';

@Injectable({
  providedIn: 'root'
})
export class adminUserGuard implements CanActivate {

  constructor(private loginService:LoginService,private router:Router){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.loginService.isLoggedIn() && this.loginService.getUserRol() == 'Admin' || this.loginService.isLoggedIn() && this.loginService.getUserRol() == 'User'){
      return true;
    }
    alert("No tiene permiso para esta paguina")
    this.router.navigate(['login']);
    return false;
  }

}
