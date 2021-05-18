import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './service/authentication.service';
import { google } from "google-maps";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'IT Shop';
  google : google;
  today: number = Date.now();
  abc = 'test';

  constructor(public loginService:AuthenticationService) { }

  ngOnInit() {
    
  }
}
