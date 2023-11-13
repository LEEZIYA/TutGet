import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
import { CreatePaymentForm } from 'src/app/DTO/CreatePaymentForm';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  BASE_URL: string = '/payment';

  constructor(private restclient: RestclientService) {}

  getListingDetails(listing_id: string){
    return this.restclient.getrawjson(this.BASE_URL + '/' + listing_id, false);
  }

  updateListing(form: CreateListingForm){
    this.restclient.putjson(this.BASE_URL, form);
  }

  createPayment(form: CreatePaymentForm){
    return this.restclient.postjsonReturnString(this.BASE_URL + '/addPayment', form);
  }

}
