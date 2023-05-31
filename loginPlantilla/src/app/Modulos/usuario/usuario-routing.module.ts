import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TablaUsuarioComponent } from './componentes/tabla-usuario/tabla-usuario.component';
import { EditUsuarioComponent } from './componentes/edit-usuario/edit-usuario.component';

const routes: Routes = [
  {path:'', component:TablaUsuarioComponent },
  {path:'edit/:id', component:EditUsuarioComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
