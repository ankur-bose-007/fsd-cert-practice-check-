import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalserviceService } from 'src/globalservices/globalservice.service';
import { NewsService } from '../newsApi/news.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  mArticles:Array<any>;
  mSources:Array<any>;
  constructor(private router:Router,private globalService:GlobalserviceService,private newsapi:NewsService) { }

  ngOnInit() {
    this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);
    //load news sources
    this.newsapi.initSources().subscribe(data=> this.mSources = data['sources']);  
    }


  searchArticles(source:String){
    console.log("selected source is: "+source);
    this.newsapi.getArticlesByID(source).subscribe(data => this.mArticles = data['articles']);
  }

  LogOut(){
    sessionStorage.removeItem('token');
    this.globalService.jwtToken='';
    this.router.navigate(['/']);
  }
}
