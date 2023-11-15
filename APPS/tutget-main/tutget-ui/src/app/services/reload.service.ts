import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';


@Injectable({
  providedIn: 'root'
})
export class ReloadService {

  constructor() { }

  public notify = new BehaviorSubject<any>('');

  notifyObservable$ = this.notify.asObservable();

    public notifyOther(data: any) {
    if (data) {
        this.notify.next(data);
    }
}


}
