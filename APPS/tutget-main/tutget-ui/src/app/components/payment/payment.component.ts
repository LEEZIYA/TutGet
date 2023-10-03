import { PaymentService } from 'src/app/services/API/payment.service';
import { Component, OnInit } from '@angular/core';
import { RestclientService } from 'src/app/services/restclient.service';
import { CreatePaymentForm } from 'src/app/DTO/CreatePaymentForm';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{

  createPaymentForm: CreatePaymentForm = new CreatePaymentForm();
  checkoutForm: FormGroup;


  firstName: string;
  lastName: string;
  email: string;
  address: string;
  address2: string;
  country: string;
  state: string;
  postal: number;
  paymentMethod: number;
  nameOnCard: string;
  creditCardNum: number;
  expiration: string;
  cvv: number;
  transactionalId: number;
  
  constructor(private router: Router, private restClient: RestclientService, private paymentService: PaymentService, private fb: FormBuilder) {}

  ngOnInit(){
    this.checkoutForm = this.fb.group({
      firstName: ['', [Validators.required, this.onlyLettersValidator]],
      lastName: ['', [Validators.required, this.onlyLettersValidator]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required]],
      address2: [''], // Address 2 is now optional
      country: ['', [Validators.required]],
      state: ['', [Validators.required]],
      zip: ['', [Validators.required]],
      paymentMethod: ['creditCard', [Validators.required]],
      nameOnCard: ['', [Validators.required]],
      creditCardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expiration: ['', [Validators.required]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3,4}$/)]]
    });
  }

  onlyLettersValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const value = control.value;
    if (value && !/^[a-zA-Z]+$/.test(value)) {
      return { 'onlyLetters': true };
    }
    return null;
  }

  onSubmit() {

    console.log("Im running!@");
    if (this.checkoutForm.valid) {
      // Handle form submission (e.g., send data to a server)
      const formData = this.checkoutForm.value;
      console.log(formData);

      // Reset the form after successful submission
      //this.checkoutForm.reset();
      this.router.navigate(['create-listing'])
    } else {
      // Mark form controls as touched to display validation errors
      Object.values(this.checkoutForm.controls).forEach(control => {
        control.markAsTouched();
      });
    }
  }
}
