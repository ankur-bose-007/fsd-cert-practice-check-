import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Model/User';
@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http:HttpClient) { }

  onSignup(user:User){
    return this.http.post<User>("user/signup",user,{observe:'response'});
  }
}
