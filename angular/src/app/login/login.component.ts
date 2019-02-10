import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from '../Model/User';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginFormGroup=this.fb.group({
    email:['',[Validators.required,Validators.minLength(7),Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+).([a-zA-Z]{2,5})$')]],
    password:['',Validators.required]
  });


  constructor(private fb:FormBuilder,private loginService:LoginService) { }

  ngOnInit() {
  }

  get formValidations(){return this.loginFormGroup.controls}

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
