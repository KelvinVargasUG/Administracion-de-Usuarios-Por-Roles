import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'usuario', pathMatch: 'full'},
  {path: 'usuario', loadChildren: ()=>import('./Modulos/usuario/usuario.module').then(m => m.UsuarioModule)},
  {path: 'login', loadChildren: ()=>import('./Modulos/login/login.module').then(m => m.LoginModule)},
  {path: 'registre', loadChildren: ()=>import('./Modulos/registre/registre.module').then(m => m.RegistreModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
