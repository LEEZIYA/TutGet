import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Check for specific error status or message
        console.log(JSON.stringify(error), this.router.url);

        const viewListingPattern = new RegExp('listing/.+$');
        const viewQnaPattern = new RegExp('qna');

        console.log('test regexp: ' + viewListingPattern.test(this.router.url));

        if (!viewQnaPattern.test(this.router.url) && !viewListingPattern.test(this.router.url) && error.status === 401 && this.router.url !== '/') {
          // Redirect to login page
          this.router.navigate(['/login']);
        }

        // Optionally handle other errors
        return throwError(error);
      })
    );
  }
}
