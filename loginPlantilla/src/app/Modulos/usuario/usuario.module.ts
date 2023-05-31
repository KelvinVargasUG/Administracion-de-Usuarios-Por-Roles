import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { TablaUsuarioComponent } from './componentes/tabla-usuario/tabla-usuario.component';
import { EditUsuarioComponent } from './componentes/edit-usuario/edit-usuario.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'


@NgModule({
  declarations: [
    TablaUsuarioComponent,
    EditUsuarioComponent
  ],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    FormsModule, 
    ReactiveFormsModule
  ]
})
export class UsuarioModule { }
