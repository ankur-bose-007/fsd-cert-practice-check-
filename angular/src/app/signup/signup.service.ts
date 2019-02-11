import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Model/User';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http:HttpClient) { }

  onSignup(user:User){
    return this.http.post("user/signup",user,{responseType:'text',observe:'response'});
  }
}
