import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './Componente/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SelectRolComponent } from './Componente/select-rol/select-rol.component';


@NgModule({
  declarations: [
    LoginComponent,
    SelectRolComponent,
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule,
    ReactiveFormsModule

  ]
})
export class LoginModule { }
