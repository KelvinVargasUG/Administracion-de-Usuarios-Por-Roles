import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms'
import {HttpClientModule} from "@angular/common/http";
import { FooterComponent } from './shared/componentes/footer/footer.component';
import { HeaderComponent } from './shared/componentes/header/header.component';
import { LayoutComponent } from './layout/layout.component';
import { authInterceptorProviders } from './Service/auth.interceptor';
import { HomeComponent } from './Modulos/Users/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LayoutComponent,
    HomeComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,

  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
