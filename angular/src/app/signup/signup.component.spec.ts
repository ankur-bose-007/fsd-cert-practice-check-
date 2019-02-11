import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { SignupService } from './signup.service';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';

fdescribe('SignupComponent', () => {
  let component: SignupComponent;
  let debugElement:DebugElement;
  let fixture: ComponentFixture<SignupComponent>;
  let signUpService:SignupService;
  let serviceSpy;
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports: [ReactiveFormsModule,HttpClientModule,RouterTestingModule],
      providers:[SignupService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SignupComponent);
    debugElement=fixture.debugElement;
    component = fixture.componentInstance;
    signUpService=debugElement.injector.get(SignupService);
    serviceSpy=spyOn(signUpService,'onSignup').and.callThrough();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('invalid email',()=>{
    let email=component.signUpFormGroup.controls['email'];
    email.setValue('dsadsa');
    expect(component.signUpFormGroup.controls.email.invalid).toBeTruthy();
  });
  it('invalid password',()=>{
    let password=component.signUpFormGroup.controls['password'];
    password.setValue('Password@');
    expect(component.signUpFormGroup.controls.password.invalid).toBeTruthy();
  });
  it('null email',()=>{
    let email=component.signUpFormGroup.controls['email'];
    email.setValue('');
    expect(component.signUpFormGroup.controls.email.errors.required).toBeTruthy();
  });
  it('null password',()=>{
    let password=component.signUpFormGroup.controls['email'];
    password.setValue('');
    expect(component.signUpFormGroup.controls.password.errors.required).toBeTruthy();
  });
  it('wrong email pattern',()=>{
    let email=component.signUpFormGroup.controls['email'];
    email.setValue('dsadasd');
    expect(component.signUpFormGroup.controls.email.errors.pattern).toBeTruthy();
  });
  it('wrong password pattern',()=>{
    let password=component.signUpFormGroup.controls['password'];
    password.setValue('vjvdsajvads');
    expect(component.signUpFormGroup.controls.password.errors.pattern).toBeTruthy();
  });
  it('null age',()=>{
    let age=component.signUpFormGroup.controls['age'];
    age.setValue('');
    expect(component.signUpFormGroup.controls.age.errors.required).toBeTruthy();
  });
  it('wrong age',()=>{
    let age=component.signUpFormGroup.controls['age'];
    age.setValue(Number(-9));
    expect(component.signUpFormGroup.controls.age.errors.min).toBeTruthy();
  });
  it('should call onSignup',async(()=>{
    let email=component.signUpFormGroup.controls['email'];
    email.setValue("jeet.ankur007@gmail.com");
    let password=component.signUpFormGroup.controls['password'];
    password.setValue("Password@10");
    let gender=component.signUpFormGroup.controls['gender'];
    gender.setValue("male");
    let age=component.signUpFormGroup.controls['age'];
    age.setValue(11);

    debugElement.query(By.css('button.btn')).triggerEventHandler('click',null);
    fixture.whenStable().then(()=>{
      return expect(component.onSubmit).toHaveBeenCalled();
    });
  }));
});
