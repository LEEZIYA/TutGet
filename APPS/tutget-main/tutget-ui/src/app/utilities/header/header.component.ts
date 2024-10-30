import { LoginService } from './../../services/API/login.service';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { authCodeFlowConfig } from 'src/app/auth.config';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  @Input()
  showMenu: boolean = false;

  @Input()
  isStudent: boolean = false;

  constructor(private router: Router, private loginService: LoginService, private oauthService: OAuthService){
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.loadDiscoveryDocument();
  }



  createListing(){
    this.router.navigate(['listing'])
  }

  createPayment(){
    this.router.navigate(['payment'])
  }

  createSearch(){
    this.router.navigate(['search'])
  }

  logOut(){
    if (this.oauthService.hasValidAccessToken()) {
      this.oauthService.loadDiscoveryDocument();
      this.oauthService.logOut();  
    }
    this.loginService.logout();
  }
}
