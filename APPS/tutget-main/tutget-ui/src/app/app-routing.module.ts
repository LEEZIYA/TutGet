import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ViewListingComponent } from './components/listing/view-listing/view-listing.component';
import { ReleaseContactComponent } from './components/payment/release-contact/release-contact.component';
import { QnaComponent } from './components/qna/qna-view/qna.component';
import { QnaViewQuestionComponent } from './components/qna/qna-view/qna-view-question/qna-view-question.component';
import { QnaNewQuestionComponent } from './components/qna/qna-new-question/qna-new-question.component';
import { SearchComponent } from './components/search/search.component';
// import { CreateUserComponent } from './components/user/create-user/create-user.component';
import { AuthComponent } from "./components/auth/auth.component";
import { LoginComponent } from './components/user/login/login.component';
import { ErrorComponent } from "./components/error/error.component";
// import { ProfileComponent } from './components/user/profile/profile.component';


const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'listing', component: CreateListingComponent},
  {path: 'listing/:id', component: ViewListingComponent},
  {path: 'qna', component: QnaComponent},
  {path: 'payment/:id/:schedule', component: PaymentComponent},
  {path: 'payment/release-contact/:id/:schedule/:transactionId', component: ReleaseContactComponent},
  {path: 'search', component: SearchComponent},
  {path: 'qna/new-question', component: QnaNewQuestionComponent},
  {path: 'qna/view/:id', component: QnaViewQuestionComponent},
  //   {path: 'create-user', component: CreateUserComponent},
  {path: 'auth', component: AuthComponent},
  {path: 'login', component: LoginComponent},
  //   {path: 'profile', component: ProfileComponent}
  {path: 'error', component: ErrorComponent},
  {path: '**', pathMatch: 'full', component: MainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
