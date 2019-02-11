import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalserviceService {
  jwtToken:String;
  constructor() { }
}
