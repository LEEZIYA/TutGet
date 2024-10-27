import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpStatusCode } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { OAuthService, OAuthStorage } from "angular-oauth2-oidc";
import { catchError, Observable, throwError } from "rxjs";
import { environment } from "../../environments/environment";
import { authCodeFlowConfig } from "../auth.config";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private oauthService: OAuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let url = req.url.toLowerCase();
        if (!url.startsWith(environment.apiBaseUrl)) {
            return next.handle(req);
        }

        let token = this.oauthService.getAccessToken();
        if (token != null) {
            req = req.clone({
                headers: req.headers.set('Authorization', 'Bearer ' + token)
            });
        }

        return next.handle(req).pipe(
            catchError((e: HttpErrorResponse) => {
                if (e.status == HttpStatusCode.Unauthorized) {
                    this.oauthService.loadDiscoveryDocumentAndLogin();
                }
                return throwError(() => e)
            })
        );
    }
}