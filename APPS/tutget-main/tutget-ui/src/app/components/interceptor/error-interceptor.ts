import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import {OAuthService} from "angular-oauth2-oidc";
import {environment} from "../../../environments/environment";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router, private oauthService: OAuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let url = request.url.toLowerCase();
    if (!url.startsWith(environment.apiBaseUrl)) {
      return next.handle(request);
    }

    let token = this.oauthService.getAccessToken();
    if (token != null) {
      request = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + token)
      });
    }

    return next.handle(request).pipe(
      catchError((error: any) => {
        // Check for specific error status or message
        console.log(JSON.stringify(error), this.router.url);

        const viewListingPattern = new RegExp('listing/.+$');
        const viewQnaPattern = new RegExp('qna');

        console.log('test view qna regexp: ' + viewQnaPattern.test(this.router.url));

        if (!viewQnaPattern.test(this.router.url) && !viewListingPattern.test(this.router.url) && error.status === 401 && this.router.url !== '/') {
          // Redirect to login page
          this.router.navigate(['/login']);
        }
        else if (error.status === 401 && request.url.includes('/realms')) {
          console.log(error);
          this.oauthService.loadDiscoveryDocumentAndLogin();
        }
        else if (this.router.url !== '/') {
          console.log(error);
          this.router.navigate(['/error'], error);
        }

        // Optionally handle other errors
        return throwError(error);
      })
    );
  }
}
