import { StorageService } from './storage.service';
import { Injectable } from '@angular/core';

const SHOW_MENU = 'SHOW_MENU';
const IS_STUD = 'IS_STUD';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  constructor(private storageService: StorageService) {}

  clearStorageToken() {
    return this.storageService.clearStorageToken();
  }

  setShowMenu(value: boolean) {
    this.storageService.setValue(SHOW_MENU, value);
  }

  getShowMenu() {
    return this.storageService.getValue(SHOW_MENU);
  }

  setIsStudent(value: boolean) {
    this.storageService.setValue(IS_STUD, value);
  }

  getIsStud() {
    return this.storageService.getValue(IS_STUD);
  }

}
