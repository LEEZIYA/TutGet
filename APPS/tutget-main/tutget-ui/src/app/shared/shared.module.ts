import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SingleButtonComponent } from '../utilities/single-button/single-button.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogComponent } from '../utilities/dialog/dialog.component';



@NgModule({
  declarations: [
    SingleButtonComponent,
    DialogComponent
    ],
  imports: [
    CommonModule,
    MatDialogModule
  ],
  exports: [
    SingleButtonComponent,
    DialogComponent
  ]
})
export class SharedModule { }
