import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LandingComponent } from './landing/landing.component';
import { JwtModule, JwtHelperService } from '@auth0/angular-jwt';
import { AuthGuardService } from './Auth/auth-guard.service';
export function tokenGetter() {
  return sessionStorage.getItem('token');
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    LandingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,JwtModule.forRoot({config:{
      tokenGetter: tokenGetter
    }})
  ],
  providers: [AuthGuardService,JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule { 
}
