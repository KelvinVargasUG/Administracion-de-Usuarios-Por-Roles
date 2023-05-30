import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'login', loadChildren: ()=>import('./Modulos/login/login.module').then(m => m.LoginModule)},
  {path: 'registre', loadChildren: ()=>import('./Modulos/registre/registre.module').then(m => m.RegistreModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
