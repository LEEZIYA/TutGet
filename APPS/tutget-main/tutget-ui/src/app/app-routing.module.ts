import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { QnaComponent } from './qna/components/qna.component';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';
// import { CreateUserComponent } from './components/user/create-user/create-user.component';
import { LoginComponent } from './components/user/login/login.component';
// import { ProfileComponent } from './components/user/profile/profile.component';


const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'create-listing', component: CreateListingComponent},
  {path: 'qna', component: QnaComponent},
//   {path: 'create-user', component: CreateUserComponent},
  {path: 'login', component: LoginComponent}
//   {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
