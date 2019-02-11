import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Model/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  login(user:User){
    return this.http.post("user/login",user,{responseType:'text',observe:'response'});
  }
  
}
