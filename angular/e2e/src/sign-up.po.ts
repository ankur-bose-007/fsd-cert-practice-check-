import { browser, by, element } from "protractor";

export class SignUp{
    navigateTo(){
        element(by.id('SignUp')).click();
    }
    navigateToLogin(){
        element(by.id('login')).click();
    }
    setEmail(email:string){
        element(by.id('InputEmail')).clear();
        element(by.id('InputEmail')).sendKeys(email);
    }
    setPassword(password:string){
        element(by.id('InputPassword')).clear();
        element(by.id('InputPassword')).sendKeys(password);
    }
    setAge(age:number){
        element(by.id('InputAge')).clear();
        element(by.id('InputAge')).sendKeys(age);
    }
    setGender(gender:string){
        element(by.id('Gender'+gender)).click();
    }
    signUp(){
        element(by.name('submitButton')).click();
    }
}