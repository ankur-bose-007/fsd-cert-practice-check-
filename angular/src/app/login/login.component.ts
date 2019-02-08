import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { User } from '../Model/User';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginFormGroup=this.fb.group({
    email:[''],
    password:['']
  });


  constructor(private fb:FormBuilder,private loginService:LoginService) { }

  ngOnInit() {
  }

  onLogin(user:User){
    console.log(user);
    
    this.loginService.login(user).subscribe(response=>{
      if(response.status==201)
        alert("Successfully Logged in");
    },error=>{
      alert('Wrong credentials');
    });
  }
}
