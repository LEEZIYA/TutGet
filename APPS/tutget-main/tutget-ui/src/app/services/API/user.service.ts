import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  BASE_URL: string = '/users'

  constructor(private restclient: RestclientService) {}

  createUser(form: CreateUserForm){
    return this.restclient.postjsonReturnString(this.BASE_URL, form);
  }


}
