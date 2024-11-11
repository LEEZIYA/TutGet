import { LoginService } from './../../services/API/login.service';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { LocalStorageService } from './../../services/local-storage.service';
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

  firstName?: string;

  constructor(private router: Router, private loginService: LoginService, private localStorageService: LocalStorageService, private oauthService: OAuthService){
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.loadDiscoveryDocument();
  }

  ngOnInit(): void {
    this.loginService.user.subscribe(user => {
        this.showMenu = this.localStorageService.getShowMenu();

        this.loginService.getUser().then( res => {
              if (res) {
                this.firstName = res.firstName;
              } else {
                this.firstName = "";
              }
              console.log('header component: ' + this.showMenu)
            })
          .catch(() => this.firstName = "");
      });
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
