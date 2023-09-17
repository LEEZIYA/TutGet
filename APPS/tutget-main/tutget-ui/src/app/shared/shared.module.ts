import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SingleButtonComponent } from '../utilities/single-button/single-button.component';



@NgModule({
  declarations: [
    SingleButtonComponent,
    ],
  imports: [
    CommonModule,
  ],
  exports: [
    SingleButtonComponent,
  ]
})
export class SharedModule { }
