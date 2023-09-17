import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';

@Injectable({
  providedIn: 'root'
})
export class ListingService {

  BASE_URL: string = '/listings'

  constructor(private restclient: RestclientService) {}

  createListing(form: CreateListingForm){
    return this.restclient.postjsonReturnString(this.BASE_URL, form);
  }


}
