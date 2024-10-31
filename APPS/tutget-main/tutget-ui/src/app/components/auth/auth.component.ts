import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { authCodeFlowConfig } from '../../auth.config';
import { filter } from 'rxjs';
import { Router } from '@angular/router';
import { LoginService } from './../../services/API/login.service';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html'
})
export class AuthComponent {
  constructor(private oauthService: OAuthService, private router: Router, private loginService: LoginService, private localStorageService: LocalStorageService) {
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();

    // this.oauthService.setupAutomaticSilentRefresh();

    // Automatically load user profile
    this.oauthService.events
      .pipe(filter((e) => e.type === 'token_received'))
      .subscribe(() => this.handleOAuthTokenReceived());
  }

  private async handleOAuthTokenReceived() {
    try {
      const profile: any = await this.oauthService.loadUserProfile();
      console.log(profile);
      const email = profile.info.email;
      const accessToken = this.oauthService.getAccessToken();
      console.log(accessToken);

      const loginUser = new CreateUserForm();
      loginUser.emailAddress = email;

      const res: any = await this.loginService.loginWithOAuth(loginUser, accessToken).toPromise();
      console.log('User data sent to backend:', res);

      this.localStorageService.setIsStudent(res.userType === 'S');
      this.localStorageService.setShowMenu(true);

      // Delay before navigating to '/' route
      setTimeout(() => this.router.navigate(['/']), 2000); // Adjust delay as needed

    } catch (error) {
      console.error('Error during OAuth token handling:', error);
    }
  }

  get userName(): string {
    const claims = this.oauthService.getIdentityClaims();
    if (!claims) return "";
    return claims['given_name'];
  }

  get email(): string {
    const claims = this.oauthService.getIdentityClaims();
    if (!claims) return "";
    return claims['email'];
  }

  get idToken(): string {
    return this.oauthService.getIdToken();
  }

  get accessToken(): string {
    return this.oauthService.getAccessToken();
  }

}
