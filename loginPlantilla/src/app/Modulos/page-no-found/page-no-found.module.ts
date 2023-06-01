import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PageNoFoundRoutingModule } from './page-no-found-routing.module';
import { PageNoFoundComponent } from './componentes/page-no-found/page-no-found.component';


@NgModule({
  declarations: [
    PageNoFoundComponent
  ],
  imports: [
    CommonModule,
    PageNoFoundRoutingModule
  ]
})
export class PageNoFoundModule { }
