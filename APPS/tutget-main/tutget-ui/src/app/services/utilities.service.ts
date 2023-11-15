import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {

  constructor() { }

  scrollToTop(){
    window.scrollTo({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
