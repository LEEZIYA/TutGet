import { Injectable } from '@angular/core';
import { EncDecService } from './encrypt-decrypt.service';


@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor(private encDecService: EncDecService) {}

  setValue(key: string, value: any) {
    this.encDecService.secureStorage.setItem(key, value);
  }

  getValue(key: string) {
    return this.encDecService.secureStorage.getItem(key);
  }

  clearStorageToken() {
    return this.encDecService.secureStorage.clear();
  }
}
