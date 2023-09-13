import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { QnaComponent } from './qna/components/qna.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'qna', component: QnaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
