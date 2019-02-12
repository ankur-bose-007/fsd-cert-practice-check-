import { SignUp } from "./sign-up.po";
import { browser, protractor, ExpectedConditions } from "protractor";

describe('sign-up tests',()=>{
    let page:SignUp;

    beforeEach(()=>{
        page=new SignUp();
        browser.get('/');
        page.navigateTo();
    });

    it('redirected to signup',()=>{
        // browser.wait(protractor.ExpectedConditions.urlContains('/signup'));
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
    });

    it('Email already exists',()=>{
        page.setAge(12);
        page.setEmail('bose.ankur007@gmail.com');
        page.setPassword('Password@46');
        page.setGender('Male');
        page.signUp();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('Email Id Already Exists');
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
    });
    it('User Registered',()=>{
        page.setAge(62);
        page.setEmail('jeet.ankur007@gmail.com');
        page.setPassword('Password@46');
        page.setGender('Male');
        page.signUp();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('Successfully Registered');
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
    it('Navigate to login',()=>{
        page.navigateToLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    })
});