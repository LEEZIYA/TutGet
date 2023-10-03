import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { QnaComponent } from './qna/components/qna.component';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';
import { PaymentComponent } from './components/payment/payment.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'create-listing', component: CreateListingComponent},
  {path: 'qna', component: QnaComponent},
  {path: 'payment', component: PaymentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
