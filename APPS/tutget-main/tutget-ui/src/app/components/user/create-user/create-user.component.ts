import { UtilitiesService } from './../../../services/utilities.service';
import { UserService } from './../../../services/API/user.service';
import { ACADEMICLEVELSUBJECTLIST, ACADEMICLEVELIDLIST, ACADEMICSUBJECTIDLIST,  } from './../../../utilities/code-table/AcademicLevelSubjectList';
import { USERTYPELIST } from './../../../utilities/code-table/USERTYPELIST'
import { Component } from '@angular/core';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { RestclientService } from 'src/app/services/restclient.service';
import { CodeTable } from 'src/app/utilities/code-table/CodeTable';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {

  createUserForm: CreateUserForm = new CreateUserForm();
  academicLvlList: Map<String, String> = ACADEMICLEVELIDLIST;
  userTypeList: Map<String, String> = USERTYPELIST;

  academicLvlLabel: String | undefined;
//
//   acadLvl: string;
//   userID: string;
//   password: string;
//
//   userType: string;
//   firstName: string;
//   lastName: string;
//   phoneNumber: string;
//   emailAddress: string;
//   billingAddress: string;
//   description: string;

  phoneErr: string = '';
  emailErr: string = '';
  passwordErr: string = '';
  userTypeErr: string = '';

  response

  constructor(private restClient: RestclientService, private userService: UserService, private utilitiesService: UtilitiesService) {
  }
  ngOnInit(){
    this.createUserForm.acadLvl = "J1";
    //call userservice or get from localstorage after login to get user academic level
    this.academicLvlLabel = this.academicLvlList.get(this.createUserForm.acadLvl);



  }
  submit(){

      this.clearError();

      if(!this.validateForm()){
        this.createUserForm.description = this.createUserForm.description.trim();



        this.userService.createUserForm(this.createUserForm)
          .then((res) => {
            this.response = res;
          })


      } else{
        this.utilitiesService.scrollToTop();
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
  validateUserID(id:string){

    this.userService.validateUserID(userID)
      .then((res) => {
        this.response = res;

      })
  }


}
