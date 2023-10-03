import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReloadService } from 'src/app/services/reload.service';

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

  constructor(private reloadService: ReloadService){
  }

  buttonHandler(event: any){
    this.eventEmit.emit(event);
    if(this.buttonTitle == 'Create Listing'){
      this.reloadService.notifyOther({refresh: true});
    }
  }

}
