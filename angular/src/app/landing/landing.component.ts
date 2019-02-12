import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalserviceService } from 'src/globalservices/globalservice.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  constructor(private router:Router,private globalService:GlobalserviceService) { }

  ngOnInit() {
  }
  LogOut(){
    sessionStorage.removeItem('token');
    this.globalService.jwtToken='';
    this.router.navigate(['/']);
  }
}
