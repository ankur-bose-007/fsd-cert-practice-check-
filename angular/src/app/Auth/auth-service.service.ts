import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { GlobalserviceService } from 'src/globalservices/globalservice.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private jwtHelper:JwtHelperService,private globalService:GlobalserviceService) { }

  public isAuthenticated():boolean{
      const token = this.globalService.jwtToken;
      return !this.jwtHelper.isTokenExpired(token);
  }
}
