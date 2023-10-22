import { PaymentService } from 'src/app/services/API/payment.service';
import { Component, OnInit } from '@angular/core';
import { RestclientService } from 'src/app/services/restclient.service';
import { CreatePaymentForm } from 'src/app/DTO/CreatePaymentForm';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{

  private sub: any;
  createPaymentForm: CreatePaymentForm = new CreatePaymentForm();
  createListingFormResult: CreateListingForm = new CreateListingForm();
  checkoutForm: FormGroup;
  id: string | null=null;
  acadLvl: String | undefined;
  acadSubject: String | undefined;
  frequency: number;
  hourlyRate: number;
  userId: String | undefined;
  assignedTutorId: String | undefined;
  schedule: string | null=null;
  computedTotal: number;
  totalHours:number;

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
  listingId: string;

  transactionId: string = '';

  constructor(private router: Router, private restClient: RestclientService, private paymentService: PaymentService, private fb: FormBuilder, private route: ActivatedRoute) {}

  ngOnInit(){
    this.id = this.route.snapshot.paramMap.get('id');
    console.log('This is the id', this.id);
    if (this.id == null){
      alert('An error occured, this ID is null.');
    }

    this.schedule = this.route.snapshot.paramMap.get('schedule');
    console.log('This is the schedule', this.schedule);

    this.sub = this.route.params.subscribe(params => {
      this.paymentService.getListingDetails(params['id'])
          .then((res) => {
            if(res){
              this.createListingFormResult = res;
              console.log('LISTING FORM INFO', this.createListingFormResult);
              this.acadLvl = this.createListingFormResult.acadLvl;
              this.acadSubject = this.createListingFormResult.acadSubject;
              this.frequency = this.createListingFormResult.frequency;
              this.hourlyRate = this.createListingFormResult.hourlyRate;
              this.assignedTutorId = this.createListingFormResult.assignedTutorId;
              this.computedTotal = this.createListingFormResult.computedTotal;
              this.totalHours = (this.computedTotal/this.hourlyRate);
            }
          
      });
    })

    //Query using ID to get Name, subject, Frequency, Hourly Rate 

    this.checkoutForm = this.fb.group({
      firstName: ['', [Validators.required, this.onlyLettersValidator]],
      lastName: ['', [Validators.required, this.onlyLettersValidator]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required]],
      address2: [''], // Address 2 is now optional
      country: ['', [Validators.required]],
      state: ['', [Validators.required]],
      postal: ['', [Validators.required]],
      paymentMethod: ['creditCard', [Validators.required]],
      nameOnCard: ['', [Validators.required]],
      creditCardNum: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expiration: ['', [Validators.required]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3,4}$/)]],
      listingId: [this.id], // Address 2 is now optional
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
      console.log('Im running here too');
      // Handle form submission (e.g., send data to a server)
      const formData = this.checkoutForm.value;
      console.log('This is form data',formData);
      //Insert record into payment table
      this.paymentService.createPayment(formData)
        .then((res) => {
          this.transactionId = res;
          console.log('This is my transaction ID', this.transactionId);

          //Update Listing table to set status to Paid
          this.createListingFormResult.status = 'Paid';
          this.paymentService.updateListing(this.createListingFormResult);
    
          // Reset the form after successful submission
          this.checkoutForm.reset();
          this.router.navigate(['payment/release-contact', this.id, this.schedule, this.transactionId]);
        })
      
    } else {
      // Mark form controls as touched to display validation errors
      // Object.values(this.checkoutForm.controls).forEach(control => {
      //   control.markAsTouched();
      // });
    }
  }
}
