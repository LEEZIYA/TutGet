import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { QnaComponent } from './qna/components/qna.component';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';
import { ViewListingComponent } from './components/listing/view-listing/view-listing.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'listing', component: CreateListingComponent},
  {path: 'listing/:id', component: ViewListingComponent},
  {path: 'qna', component: QnaComponent},
  {path: '**', pathMatch: 'full', component: MainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
