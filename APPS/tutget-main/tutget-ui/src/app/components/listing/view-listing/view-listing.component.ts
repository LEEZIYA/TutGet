import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';
import { ListingService } from 'src/app/services/API/listing.service';
import { RestclientService } from 'src/app/services/restclient.service';
import { ACADEMICLEVELIDLIST, ACADEMICSUBJECTIDLIST } from 'src/app/utilities/code-table/AcademicLevelSubjectList';
import { Constants } from 'src/app/utilities/constants';
import { DialogComponent } from 'src/app/utilities/dialog/dialog.component';
import { STUDENT, TEACHER, TEACHER2 } from '../test-users';
import { LoginService } from 'src/app/services/API/login.service';

@Component({
  selector: 'app-view-listing',
  templateUrl: './view-listing.component.html',
  styleUrls: ['./view-listing.component.css']
})
export class ViewListingComponent implements OnInit {

  private sub: any;
  createListingForm: CreateListingForm = new CreateListingForm();
  // id: string = '';
  objProp: string[] = ['acadLvl', 'acadSubject', 'dayOfWeek'];
  objPropHeader: string[] = ['Academic Level', 'Academic Subject', 'Day of Week'];
  objPropLabel: string[];
  academicLvlList: Map<String, String> = ACADEMICLEVELIDLIST;
  academicSubjectList: Map<String, String> = ACADEMICSUBJECTIDLIST;
  academicLvlLabel: String | undefined;
  academicSubjectLabel: String | undefined;
  address: string;
  schedule: string = '';
  dayOfWeek: string[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  startDate: string | null;
  frequencyList = ['Weekly','Biweekly','Monthly'];
  deleted: boolean = false;
  enableAd: boolean = false;

  listingOwner: any;
  activeUser: any;
  listingRights: boolean = false;

  requested: boolean = false;
  requestedUsers: string[] = [];
  requestedUsersName: string[] = [];

  assignedATutor: boolean = false;


  constructor(private restClient: RestclientService, private router: Router, private activatedRoute: ActivatedRoute, private listingService: ListingService, private dialog: MatDialog,
    private loginService: LoginService) { }

  ngOnInit() {
    this.loginService.user.subscribe(user => this.activeUser = user);
    this.restClient.getrawjson('/ad', false)
      .then((res)=>{
        this.enableAd = res;
      });

    this.sub = this.activatedRoute.params.subscribe(params => {
      this.listingService.getListing(params['id'])
          .then((res) => {
            if(res){
              // this.id = params['id'];
              this.createListingForm = res;

               this.loginService.getUser(res.userId).then((res) => {
                if(res){
                  this.listingOwner = res;

                  this.requestedUsers = this.createListingForm.requests.split(",");
                  this.requestedUsers.shift();


                  if(this.activeUser.userID == this.listingOwner.userID ){
                    this.listingRights = true;
                  } else {
                    if(this.requestedUsers.includes(this.activeUser.userID)){
                      this.requested = true;
                    }
                  }

                  this.academicLvlLabel = this.academicLvlList.get(this.createListingForm.acadLvl);
                  this.academicSubjectLabel = this.academicSubjectList.get(this.createListingForm.acadSubject);

                  this.restClient.getrawjson(Constants.oneMapURLStart + this.createListingForm.postalCode + Constants.oneMapURLEnd, true)
                  .then((res) => {
                    this.address = res.results[0].ADDRESS;
                  })

                  for(let i = 0; i < 7; i++){
                    if(this.createListingForm.dayOfWeek[i] === "1"){
                      let day = this.dayOfWeek[i];
                      let hour = this.createListingForm.selectedHour[i];
                      let hourToDisplay = hour > 12 ? hour - 12 : hour;
                      let min = this.createListingForm.selectedMin[i];
                      let minToDisplay;
                      if(min == 0){
                        minToDisplay = '00'
                      } else if (min == 5){
                        minToDisplay = '05'
                      } else {
                        minToDisplay = this.createListingForm.selectedMin[i].toString();
                      }
                      let ampm = ((hour >= 12) && (hour != 24)) ? 'PM' : 'AM';
                      let startTime = hourToDisplay.toString() + ':' + minToDisplay + ' ' + ampm;
                      let startDayTime = day + ' ' + startTime;

                      let startDate = new Date(2023, 8, 11, hour, Number(minToDisplay));
                      startDate.setTime(startDate.getTime() + (Number(this.createListingForm.selectedHourNum[i]) * 60 * 60 * 1000));
                      let endTime = startDate.toLocaleTimeString([], {timeStyle: 'short'});

                      this.schedule += startDayTime + ' - ' + endTime + '\n';
                    }

                    const datepipe: DatePipe = new DatePipe('en-US')
                    this.startDate = datepipe.transform(this.createListingForm.startDate, 'd MMMM y (EEEE)');

                  }

                }
              })


            } else {
              this.router.navigate(['']);
            }
          });
    })

  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

  editListing(){
    this.router.navigateByUrl('listing', { state: this.createListingForm });
  }

  injectDialogConfig(dialogConfig: MatDialogConfig){
    dialogConfig.width = '400px';
    dialogConfig.height = '200px';
    dialogConfig.position = {
      'top': '20vh'
    };
    dialogConfig.autoFocus = false;

  }

  deleteListing(){
    const dialogConfig = new MatDialogConfig();
    this.injectDialogConfig(dialogConfig);
    console.log('This shows my createListingForm', this.createListingForm);
    dialogConfig.data = {para: 'Are you sure to delete this listing?'};
    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if(result === 'confirm'){
        this.listingService.deleteListing(this.createListingForm.id);
        this.deleted = true;
      }
    })
  }

  goHome(){
    this.router.navigateByUrl('');
  }

  requestListing(){
    const dialogConfig = new MatDialogConfig();
    this.injectDialogConfig(dialogConfig);
    dialogConfig.data = {para: 'Are you sure to request this assignment?'};

    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if(result === 'confirm'){
        this.createListingForm.requests += ',' + this.activeUser.userID;
        this.listingService.updateListing(this.createListingForm);
        this.requested = true;
        alert("You have successfully requested for this assignment!");
      }
    })

  }


  cancelRequest(){
    const dialogConfig = new MatDialogConfig();
    this.injectDialogConfig(dialogConfig);
    dialogConfig.data = {para: 'Are you sure to cancel your request for this assignment?'};
    dialogConfig.height = '230px';

    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if(result === 'confirm'){
        this.createListingForm.requests = this.createListingForm.requests.replace("," + this.activeUser.userID, "");
        this.listingService.updateListing(this.createListingForm);
        this.requested = false;
        alert("You have successfully cancelled your request for this assignment!");
      }
    })
  }

  assignListing(tutorId: string){
    const dialogConfig = new MatDialogConfig();
    this.injectDialogConfig(dialogConfig);
    dialogConfig.data = {para: 'Are you sure to assign to this tutor?'};

    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if(result === 'confirm'){
        this.createListingForm.assignedTutorId = tutorId;
        this.createListingForm.status = 'A';
        this.listingService.updateListing(this.createListingForm);
        this.assignedATutor = true;
        alert("You have successfully assigned a tutor!");
        console.log(this.createListingForm.assignedTutorId);
      }
    })
  }

  cancelAssign(){
    const dialogConfig = new MatDialogConfig();
    this.injectDialogConfig(dialogConfig);
    dialogConfig.height = '230px';
    dialogConfig.data = {para: 'Are you sure to cancel your assignment to this tutor?'};

    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if(result === 'confirm'){
        this.createListingForm.assignedTutorId = '';
        this.createListingForm.status = 'N';
        this.listingService.updateListing(this.createListingForm);
        this.assignedATutor = false;
        alert("You have successfully cancelled your assignment!");
        console.log(this.createListingForm.assignedTutorId);
      }
    })
  }

  createPayment(){
    this.router.navigate(['payment', this.createListingForm.id, this.schedule])
  }


}
