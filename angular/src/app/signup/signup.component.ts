import { Component, OnInit } from '@angular/core';
import { FormBuilder,Validators } from '@angular/forms';
import { User } from '../Model/User';
import { SignupService } from './signup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpFormGroup=this.fb.group({
    email:['',[Validators.required,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
    password:['',[Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&]).{8,}$')]],
    gender:[''],
    age:['',[Validators.required,Validators.min(10)]]
  });
  message:String;
  statusCode:Number;
  constructor(private fb:FormBuilder,private signUpService:SignupService,private router:Router) {
  }
  
  ngOnInit() {
  }
  
  get formValidations(){return this.signUpFormGroup.controls}

  onSubmit(user:User){
    this.signUpService.onSignup(user).subscribe(response=>{
      if(response.status==201){
        alert("Successfully Registered");
        this.router.navigate(['']);
      }
    },error=>{
      if(error.status==409)
        alert("Email Id Already Exists")
      else
        alert("Something went wrong");
    });
  }
}