import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './layout/layout.component';
import {AdminGuard} from './Guard/admin/admin.guard';
import {adminUserGuard} from "./Guard/AdminUser/admin-user.guard";

const routes: Routes = [
  {path: 'login', loadChildren: () => import('./Modulos/login/login.module').then(m => m.LoginModule)},
  {path: 'registre', loadChildren: () => import('./Modulos/registre/registre.module').then(m => m.RegistreModule)},
  {
    path: '', component: LayoutComponent, children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {
        path: 'usuarios',
        loadChildren: () => import('./Modulos/usuario/usuario.module').then(m => m.UsuarioModule),
        canActivate: [AdminGuard]
      },
      {
        path: 'home',
        loadChildren: () => import('./Modulos/Users/users.module').then(m => m.UsersModule),
        canActivate: [adminUserGuard]
      },
    ]
  },
  {
    path: '**',
    loadChildren: () => import('./Modulos/page-no-found/page-no-found.module').then(m => m.PageNoFoundModule)
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
