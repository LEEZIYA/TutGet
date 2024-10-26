import { LocalStorageService } from './../../../services/local-storage.service';
import { Component, Input, OnInit } from '@angular/core';
// import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule,FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

// import { UtilitiesService } from './../../../services/utilities.service';
import { LoginService } from './../../../services/API/login.service';
import { Constants } from './../../../utilities/constants';
// import { ACADEMICLEVELSUBJECTLIST, ACADEMICLEVELIDLIST, ACADEMICSUBJECTIDLIST,  } from './../../../utilities/code-table/AcademicLevelSubjectList';
// import { Component } from '@angular/core';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { RestclientService } from 'src/app/services/restclient.service';
import { Router } from '@angular/router';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    invalid = '';

    userForm: CreateUserForm | null;// = new CreateUserForm();
//     userForm2: CreateUserForm | null;
//     loginName : string | null = null;
    loginSuccess : string = '';
    loginErr: string = '';


    constructor(
        private formBuilder: FormBuilder,
//         private route: ActivatedRoute,
//         private router: Router,
        private loginService: LoginService,
        private localStorageService: LocalStorageService,
        private router: Router
    ) {
      this.userForm = null;
//       this.loginService.user.subscribe(user => this.userForm = user);

    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
          userName: ['', Validators.required],
          password: ['', Validators.required],
        });

//         this.loginService.createUser();
        this.clearStorage();
        this.localStorageService.setShowMenu(false);
        this.localStorageService.setIsStudent(false);


    }

//     // convenience getter for easy access to form fields
//     get f() { return this.form.controls; }

    onSubmit(form:FormGroup) {
//         this.submitted = true;

        // reset alerts on submit
//         this.alertService.clear();

//         stop here if form is invalid
        if (this.loginForm.invalid) {
            this.invalid = 'Please fill in user name and password';
            return;
        }

//         this.loading = true;
//         this.listingService.createListing(this.createListingForm)
//           .then((res) => {
//             this.listingId = res;
//           })
//         this.loginName = form.value.userName;
        let loginUserForm:CreateUserForm = new CreateUserForm();
        loginUserForm.userID = form.value.userName;
        loginUserForm.password = form.value.password;
        this.loginService.login(loginUserForm)
            .pipe(first())
            .subscribe({
                next: (res: any) => {
                    if(res && res.status === 200){
//                         console.log('login verified');
//                         console.log(res.id);
                        this.loginSuccess = 'You have logged in as user';
                        this.loginErr = '';



                        if(res.body.userType === 'S'){
                          this.localStorageService.setIsStudent(true);
                        }


                        this.localStorageService.setShowMenu(true);
                        this.router.navigate(['']);
// //                         this.userForm = res;
// //                         this.userForm2 = this.loginService.userValue;
                    }
                    else {
                      this.loginErr = 'Login failed!';

                      this.loginService.logout();
                    }

                    // get return url from query parameters or default to home page
//                     const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
//                     this.router.navigateByUrl(returnUrl);
                },
                error: error => {
//                     this.alertService.error(error);
//                     this.loading = false;
                     this.loginErr = 'login failed'+error;
                }
            });
    }
    logOut(){
      this.loginService.logout();
      this.loginSuccess = '';
    }

    clearStorage(){
      this.localStorageService.clearStorageToken();
      localStorage.clear();
      window.sessionStorage.clear();
    }
}
