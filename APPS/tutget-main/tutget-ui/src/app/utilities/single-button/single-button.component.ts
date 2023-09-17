import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-single-button',
  templateUrl: './single-button.component.html',
  styleUrls: ['./single-button.component.css']
})
export class SingleButtonComponent {

  @Input()
  buttonTitle: string = "";

  @Input()
  rounderStyle: boolean = false;

  @Input()
  longerStyle: boolean = false;

  @Output()
  eventEmit: EventEmitter<any> = new EventEmitter();

  buttonHandler(event: any){
    this.eventEmit.emit(event);
  }

}
