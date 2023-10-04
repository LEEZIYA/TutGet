import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
import { CreatePaymentForm } from 'src/app/DTO/CreatePaymentForm';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  BASE_URL: string = '/payment';
  http: any;

  constructor(private restclient: RestclientService) {}

  createPayment(form: CreatePaymentForm){
    return this.restclient.postjsonReturnString(this.BASE_URL, form);
  }

  getContactDetails(teacher_id: string){
    return this.restclient.postjsonReturnString(this.BASE_URL, teacher_id);
  }

  getTuitionDetails(listing_id: string){
    return this.restclient.postjsonReturnString(this.BASE_URL, listing_id);
  }
}
