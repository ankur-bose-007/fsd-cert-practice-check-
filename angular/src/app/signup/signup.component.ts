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
    email:['',[Validators.required,Validators.minLength(6),Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+).([a-zA-Z]{2,5})$')]],
    password:['',Validators.required],
    gender:['',Validators.required],
    age:['',Validators.required]
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
    },error=>{alert("Email Id Already Exists")});
  }
}
