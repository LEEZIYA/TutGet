import { PaymentService } from 'src/app/services/API/payment.service';
import { Component} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContactDetails } from 'src/app/DTO/ContactDetails';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';

@Component({
  selector: 'app-release-contact',
  templateUrl: './release-contact.component.html',
  styleUrls: ['./release-contact.component.css']
})
export class ReleaseContactComponent{

  contactDetails: ContactDetails;
  contactPictureUrl: string = ''; // Initialize the picture URL as an empty string
  createListingFormResult: CreateListingForm = new CreateListingForm();
  schedule: string | null=null;
  id: string | null=null;
  transactionId: string | null=null;

  acadLvl: String | undefined;
  acadSubject: String | undefined;
  frequency: number;
  hourlyRate: number;
  userId: String | undefined;
  assignedTutorId: String | undefined;

  private sub: any;

  constructor(private paymentService: PaymentService, private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.id = this.route.snapshot.paramMap.get('id');
    console.log('This is the id', this.id);
    if (this.id == null){
      alert('An error occured, this ID is null.');
    }

    this.schedule = this.route.snapshot.paramMap.get('schedule');
    console.log('This is the schedule', this.schedule);

    this.transactionId = this.route.snapshot.paramMap.get('transactionId');
    console.log('This is the transactionId', this.transactionId);

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
            }
          
      });
    })
  }
}

