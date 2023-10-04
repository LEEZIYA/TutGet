import { PaymentService } from 'src/app/services/API/payment.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContactDetails } from 'src/app/DTO/ContactDetails';
import { TuitionDetails } from 'src/app/DTO/TuitionDetails';

@Component({
  selector: 'app-release-contact',
  templateUrl: './release-contact.component.html',
  styleUrls: ['./release-contact.component.css']
})
export class ReleaseContactComponent{

  contactDetails: ContactDetails;
  tuitionDetails: TuitionDetails;
  contactPictureUrl: string = ''; // Initialize the picture URL as an empty string


  private sub: any;

  constructor(private router: Router, private paymentService: PaymentService, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    // Fetch contact details data from the service
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.paymentService.getContactDetails(params['teacher_id'])
        .then((res) => {
          this.contactDetails = res;
          this.loadContactPicture(res);
        }
      )
    });

    this.sub = this.activatedRoute.params.subscribe(params => {
      this.paymentService.getTuitionDetails(params['listing_id'])
        .then((res) => {
          this.tuitionDetails = res;
        }
      )
    });
  }

  loadContactPicture(profilePic:Blob) {
    // Assuming you have a function to fetch the blob data from your API
    const reader = new FileReader();
    reader.onload = () => {
      // Set the image URL once the blob data is loaded
      this.contactPictureUrl = reader.result as string;
    };
    reader.readAsDataURL(profilePic);
  }
}

