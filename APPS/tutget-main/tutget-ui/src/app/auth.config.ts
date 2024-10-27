import { AuthConfig } from 'angular-oauth2-oidc';
import { environment } from '../environments/environment';

export const authCodeFlowConfig: AuthConfig = {
  issuer: environment.authIssuer,
  redirectUri: window.location.origin + '/auth',
  postLogoutRedirectUri: window.location.origin + '/',
  clientId: 'tutget-webapp',
  responseType: 'code',
  scope: 'openid profile email offline_access',
  showDebugInformation: true,
  timeoutFactor: 0.01,
  checkOrigin: false,
};
