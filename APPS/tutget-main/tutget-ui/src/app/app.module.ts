import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './utilities/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './utilities/footer/footer/footer.component';
import { QnaViewQuestionComponent } from './components/qna/qna-view/qna-view-question/qna-view-question.component';
import { SharedModule } from './shared/shared.module';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ViewListingComponent } from './components/listing/view-listing/view-listing.component';
import { ReleaseContactComponent } from './components/payment/release-contact/release-contact.component';
import { QnaComponent } from './components/qna/qna-view/qna.component';
import { QnaNewQuestionComponent } from './components/qna/qna-new-question/qna-new-question.component';
import { SearchComponent } from './components/search/search.component';

import { environment } from '../environments/environment';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';
import { AuthComponent } from './components/auth/auth.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { LoginComponent } from './components/user/login/login.component';

import { HttpClientXsrfModule } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { ErrorComponent } from "./components/error/error.component";
//import {CreateUserComponent} from './components/user/create-user/create-user.component';
// import { ProfileComponent } from './components/user/profile/profile.component';
import { ErrorInterceptor } from './components/interceptor/error-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    FooterComponent,
    QnaComponent,
    QnaViewQuestionComponent,
    QnaNewQuestionComponent,
    CreateListingComponent,
    PaymentComponent,
    ViewListingComponent,
    ReleaseContactComponent,
    //CreateUserComponent,
    // ProfileComponent,
    LoginComponent,
    SearchComponent,
    AuthComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SharedModule,
    ReactiveFormsModule,
    CommonModule,
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    OAuthModule.forRoot(),
  ],
  providers: [
    // Csrf token
    importProvidersFrom(HttpClientModule),
    importProvidersFrom(
      HttpClientXsrfModule.withOptions()
    ),
    // Interceptor
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
