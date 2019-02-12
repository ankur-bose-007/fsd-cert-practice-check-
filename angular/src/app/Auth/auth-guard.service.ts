import { Injectable } from '@angular/core';
import { AuthServiceService } from './auth-service.service';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private auth:AuthServiceService,private router:Router) { }

  canActivate(): boolean {
    if (!this.auth.isAuthenticated()) {
      alert("acc");
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
