import { UtilitiesService } from './../../../services/utilities.service';
import { ListingService } from './../../../services/API/listing.service';
import { Constants } from './../../../utilities/constants';
import { ACADEMICLEVELSUBJECTLIST, ACADEMICLEVELIDLIST, ACADEMICSUBJECTIDLIST,  } from './../../../utilities/code-table/AcademicLevelSubjectList';
import { Component } from '@angular/core';
import { CreateListingForm } from 'src/app/DTO/CreateListingForm';
import { RestclientService } from 'src/app/services/restclient.service';
import { CodeTable } from 'src/app/utilities/code-table/CodeTable';
import { ReloadService } from 'src/app/services/reload.service';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';
import { STUDENT } from '../test-users';


@Component({
  selector: 'app-create-listing',
  templateUrl: './create-listing.component.html',
  styleUrls: ['./create-listing.component.css']
})
export class CreateListingComponent {

  createListingForm: CreateListingForm = new CreateListingForm();
  academicLvlList: Map<String, String> = ACADEMICLEVELIDLIST;
  academicLvlSubjectList: string[] | undefined;
  academicSubjectList: Map<String, String> = ACADEMICSUBJECTIDLIST;
  academicLvlLabel: String | undefined;
  address: string;
  dayOfWeek: boolean[];
  selectedHour: number[];
  selectedMin: string[];
  selectedMinNum: number[];
  selectedAMPM: number[];
  selectedHourNum: number[];
  computedEndTimeArr: string[];
  frequency: number;
  checkAllSelect: boolean;
  frequencyList = ['Weekly','Biweekly','Monthly'];
  showTotal: boolean;
  computedTotal: number = 0;
  hourlyRate: any;
  subjErr: string = '';
  dateTimeErr: string = '';
  startDtErr: string = '';
  freqErr: string = '';
  hourlyRateErr: string = '';
  selected24Hour: number[];
  listingId: string = '';
  editMode: boolean = false;
  submitted: boolean = false;

  activeUser: any = STUDENT;

  constructor(private restClient: RestclientService, private listingService: ListingService, private utilitiesService: UtilitiesService, private reloadService: ReloadService, private router: Router, public activatedRoute: ActivatedRoute) {
    //console.log(this.router.getCurrentNavigation()?.extras.state);
  }

  ngOnInit(){

    this.loadComponent();

    this.reloadService.notifyObservable$.subscribe(res => {
        if(res.refresh){
          this.loadComponent();
        }
      }
    )

    this.activatedRoute.paramMap
    .pipe(map(() => window.history.state)).subscribe(res=>{
          if(res.id){
            this.createListingForm = res;
            this.listingId = res.id;
            this.editMode = true;
            this.hourlyRate = this.createListingForm.hourlyRate;
            this.checkAllSelect = true;
            for(let i = 0; i < 7; i++){
              if(this.createListingForm.dayOfWeek[i] == "1"){
                this.dayOfWeek[i] = true;
                let hour = this.createListingForm.selectedHour[i];
                let hourToDisplay = hour > 12 ? hour - 12 : hour;
                this.selectedHour[i] = hourToDisplay;
                this.selectedAMPM[i] = ((hour >= 12) && (hour != 24)) ? 2  : 1;

                let min = this.createListingForm.selectedMin[i];
                let minToDisplay;
                if(min == 0){
                  minToDisplay = '00'
                } else if (min == 5){
                  minToDisplay = '05'
                } else {
                  minToDisplay = this.createListingForm.selectedMin[i].toString();
                }
                this.selectedMin[i] = minToDisplay;
                this.selectedHourNum[i] = Number(this.createListingForm.selectedHourNum[i]);
                this.computeEndTime(i);

              }
            }
          }
     })

  }

  loadComponent(){
    this.submitted = false;
    this.editMode = false;
    this.checkAllSelect = false;
    this.listingId = '';
    this.clearError();
    this.initTableValue();
    this.createListingForm = new CreateListingForm();
    this.createListingForm.frequency = -1;
    this.showTotal = false;
    this.hourlyRate = undefined;

    this.createListingForm.acadLvl = this.activeUser.acadLvl;
    //call userservice or get from localstorage after login to get user academic level

    this.academicLvlLabel = this.academicLvlList.get(this.createListingForm.acadLvl);

    // ACADEMICLEVELSUBJECTLIST.forEach((lvlSubj) => {
    //   if(lvlSubj.acadLvlId === this.createListingForm.acadLvl){
    //     this.academicLvlSubjectList = lvlSubj.acadSubjectIdList;
    //   }
    // })

    this.academicLvlSubjectList = ACADEMICLEVELSUBJECTLIST.get(this.createListingForm.acadLvl);

    this.createListingForm.postalCode = this.activeUser.postalCode;
    //200640
    //call userservice or get from localstorage after login to get user postal code

    this.restClient.getrawjson(Constants.oneMapURLStart + this.createListingForm.postalCode + Constants.oneMapURLEnd, true)
          .then((res) => {
            this.address = res.results[0].ADDRESS;
          })

  }


  initTableValue(){
    this.dayOfWeek = Array(7).fill(false);
    this.selectedHour = Array(7).fill(0);
    this.selectedMin = Array(7).fill('');
    this.selectedAMPM = Array(7).fill(0);
    this.selectedHourNum = Array(7).fill(0);
    this.computedEndTimeArr = Array(7).fill('');
    this.selected24Hour = Array(7).fill(0);
    this.selectedMinNum = Array(7).fill(0);
  }



  computeEndTime(day: number){

    let selectedDay = this.dayOfWeek[day];
    let hour = this.selectedHour[day];
    let min = this.selectedMin[day];
    let ampm = this.selectedAMPM[day];
    let hourNum = this.selectedHourNum[day];


    if(selectedDay && hour && min && ampm && hourNum){

      let min2 = Number(min);
      let hour2 = Number(hour);
      if(ampm == 2 && hour2 != 12){
        hour2 += 12;
      } else if (ampm == 1 && hour2 == 12){
        hour2 = 24;
      }
      this.selected24Hour[day] = hour2;
      this.selectedMinNum[day] = min2;
      let startDate = new Date(2023, 8, 11, hour2, min2);
      startDate.setTime(startDate.getTime() + (Number(hourNum) * 60 * 60 * 1000));

      this.computedEndTimeArr[day] = startDate.toLocaleTimeString([], {timeStyle: 'short'});
      this.computeTotal();

    } else {
      if(!selectedDay){
        this.computedEndTimeArr[day] = '';
        this.selectedHour[day] = 0;
        this.selectedMin[day] = '';
        this.selectedAMPM[day] = 0;
        this.selectedHourNum[day] = 0;
        this.selected24Hour[day] = 0;
        this.selectedMinNum[day] = 0;
      }
    }
  }

  checkAll(type: number){
    if(type < 7){
      if(this.dayOfWeek[type]){
        this.checkAllSelect = true;
      } else if (new Set(this.dayOfWeek).size == 1 && !this.dayOfWeek[type]){
        this.checkAllSelect = false;
      }
    } else {
      if(this.checkAllSelect){
        this.dayOfWeek = Array(7).fill(true);
      } else{
        this.initTableValue();
      }
    }
    this.computeTotal();
  }

  submit(){

    this.clearError();

    if(!this.validateForm()){
      this.createListingForm.postDate = new Date();
      let dayOfWeekStr = '';
      this.dayOfWeek.forEach((e) => {
        if(e){
          dayOfWeekStr += '1';
        } else {
          dayOfWeekStr += '0';
        }
      });
      this.createListingForm.dayOfWeek = dayOfWeekStr;
      this.createListingForm.hourlyRate = Number(this.hourlyRate);
      this.createListingForm.description = this.createListingForm.description.trim();
      this.createListingForm.selectedHour = this.selected24Hour;
      this.createListingForm.selectedHourNum = this.selectedHourNum.join("");
      this.createListingForm.selectedMin = this.selectedMinNum;
      this.createListingForm.userId = this.activeUser.userID;
      this.createListingForm.status = 'N';


      if(!this.editMode){
        this.listingService.createListing(this.createListingForm)
        .then((res) => {
          this.listingId = res;
          this.submitted = true;
        })
      } else {
        this.listingService.updateListing(this.createListingForm);
        this.submitted = true;
      }

    } else{
      this.utilitiesService.scrollToTop();
    }

  }

  computeTotal(){
    if((new Set(this.computedEndTimeArr).size > 1) && (this.createListingForm.frequency != -1) && !isNaN(this.hourlyRate) ){
        this.showTotal = true;
        let total = 0;
        this.computedEndTimeArr.forEach((e, i) => {if(e){total +=  this.selectedHourNum[i] * Number(this.hourlyRate)}})
        this.computedTotal = total;
    } else {
      this.showTotal = false;
    }
  }

  onKeyup(e: any){
    if((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105) && !isNaN(this.hourlyRate)){
      this.computeTotal();
    } else {
      this.showTotal = false;
    }
  }

  validateForm(){
    let hasErr = false;
    if(!this.createListingForm.acadSubject){
      this.subjErr = 'Please select a subject';
      hasErr = true;
    }
    if(new Set(this.computedEndTimeArr).size == 1){
      this.dateTimeErr = 'Please input all the required information on the above table';
      hasErr = true;
    }

    this.dayOfWeek.forEach((e, i) => {
      if(e){
        if(!this.computedEndTimeArr[i]){
          this.dateTimeErr = 'Please input all the required information on the above table';
          hasErr = true;
        }
      }
    });

    if(this.createListingForm.startDate){
      let dateIndex = new Date(this.createListingForm.startDate).getDay();
      if(dateIndex == 0){
        dateIndex = 6;
      } else {
        dateIndex -= 1;
      }
      if(!this.dayOfWeek[dateIndex]){
        this.startDtErr = 'Day of week do not match';
        hasErr = true;
      }
      if(new Date(this.createListingForm.startDate) <= new Date()){
        this.startDtErr = 'Start date must be after today';
        hasErr = true;
      }

    } else {
      this.startDtErr = 'Please select the start date';
      hasErr = true;
    }

    if(this.createListingForm.frequency == -1){
      this.freqErr = 'Please select a frequency';
      hasErr = true;
    }

    if(!this.hourlyRate){
      this.hourlyRateErr = "Please input an amount";
      hasErr = true;
    } else {
      if(isNaN(this.hourlyRate)){
        this.hourlyRateErr = "Please input a valid number";
        hasErr = true;
      }
    }

     return hasErr;

  }

  clearError(){
    this.subjErr = '';
    this.dateTimeErr = '';
    this.startDtErr = '';
    this.freqErr = '';
    this.hourlyRateErr = '';
  }

  viewListing(){
    this.router.navigate(['/listing', this.listingId]);
  }

}
