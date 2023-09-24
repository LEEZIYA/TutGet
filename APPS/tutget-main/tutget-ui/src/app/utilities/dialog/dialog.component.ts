import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog'

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent {

  @Output()
  confirmButton: EventEmitter<any> = new EventEmitter();

  dialogTitle: string = 'For confirmation';

  para: string = '';

  constructor(private dialogRef: MatDialogRef<DialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any){

  }


  ngOnInit(){
    this.para = this.data.para;
  }

  confirm(){
    this.dialogRef.close('confirm');
  }

  stayOnPage(){
    this.dialogRef.close('no');
  }


}
