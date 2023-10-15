import { Injectable } from '@angular/core';
// import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

// import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
// import { CreateListingForm } from 'src/app/DTO/CreateListingForm';

// import { environment } from '@environments/environment';
// import { User } from '@app/_models';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';

@Injectable({ providedIn: 'root' })
export class LoginService {
    private userSubject: BehaviorSubject<CreateUserForm | null>;
    public user: Observable<CreateUserForm | null>;
    BASE_URL: string = '/';


    constructor(
//         private router: Router,
        private http: HttpClient,
        private restclient: RestclientService
    ) {
        this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('ActiveUser')!));
        this.user = this.userSubject.asObservable();
    }

    public get userValue() {
        return this.userSubject.value;
    }

    login(loginForm:CreateUserForm) {

//            return this.restclient.postjson(this.BASE_URL + '/users/login',loginForm)
        return this.http.post<CreateUserForm>(this.BASE_URL + '/users/login',loginForm)
           .pipe(map(res => {
               // store user details and jwt token in local storage to keep user logged in between page refreshes
               localStorage.setItem('ActiveUser', JSON.stringify(res));
               this.userSubject.next(JSON.parse(res));
               return res;
           }));
//            let loginForm:CreateUserForm;
//            loginForm.username = username;
//            loginForm.password = password;
//            userForm = this.restclient.postjson(this.BASE_URL + '/users/login',loginForm);
//            localStorage.setItem('user', JSON.stringify(user));
//            this.userSubject.next(user);
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('user');
        this.userSubject.next(null);
//         this.router.navigate(['/account/login']);
    }
}
