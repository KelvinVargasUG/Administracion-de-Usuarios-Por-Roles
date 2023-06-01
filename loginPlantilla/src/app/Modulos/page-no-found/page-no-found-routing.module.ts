import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNoFoundComponent } from './componentes/page-no-found/page-no-found.component';

const routes: Routes = [
  {path:'', component:PageNoFoundComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PageNoFoundRoutingModule { }
