import * as ngCore from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {Component, ElementRef, NgModule, NgZone} from '@angular/core';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {AppRoutingModule} from './/app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {FooterComponent} from './footer/footer.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./auth/token.interceptor";
import {ProfileService} from "./profile.service";
import { InvoiceComponent } from './invoice/invoice.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { CarFilterPipe } from './carFilter.pipe';
import { InvoiceFilterPipe } from './invoice-filter.pipe';
import {DatePipe} from "@angular/common";
import { AgmCoreModule } from '@agm/core';
import {InvoiceService} from "./invoice.service";
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    FooterComponent,
    InvoiceComponent,
    InvoicesComponent,
    CarFilterPipe,
    InvoiceFilterPipe,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDXdAc0zDyz_bjoXzZRLGv8IfzxphVlhfU'
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    ProfileService,
    InvoiceService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
