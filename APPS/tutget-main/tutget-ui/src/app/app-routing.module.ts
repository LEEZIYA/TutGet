import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'create-listing', component: CreateListingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
