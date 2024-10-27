import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService} from '../local-storage.service'
// import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
// import { CreateListingForm } from 'src/app/DTO/CreateListingForm';

// import { environment } from '@environments/environment';
// import { User } from '@app/_models';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { STUDENT, TEACHER, TEACHER2 } from 'src/app/components/listing/test-users';

@Injectable({ providedIn: 'root' })
export class LoginService {
    private userSubject: BehaviorSubject<unknown>;
    public user: Observable<unknown>;
    BASE_URL: string = 'http://localhost:9000/api/users';


    constructor(
        private router: Router,
        private http: HttpClient,
        private restclient: RestclientService,
        private localStorageService: LocalStorageService
    ) {
        this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('ActiveUser')!));
        this.user = this.userSubject.asObservable();
    }

    public get userValue() {
        return this.userSubject.value;
    }

    login(loginForm:CreateUserForm) {

//            return this.restclient.postjson(this.BASE_URL + '/users/login',loginForm)
        return this.http.post<unknown>(
           this.BASE_URL + '/login',
           loginForm,
           { observe: 'response' } // To read response status in header
        ).pipe(map(res => {
               // store user details and jwt token in local storage to keep user logged in between page refreshes
               localStorage.setItem('ActiveUser', JSON.stringify(res.body));
               this.userSubject.next(res.body); //
               return res;
           }));
//            let loginForm:CreateUserForm;
//            loginForm.username = username;
//            loginForm.password = password;
//            userForm = this.restclient.postjson(this.BASE_URL + '/users/login',loginForm);
//            localStorage.setItem('user', JSON.stringify(user));
//            this.userSubject.next(user);
    }

    async logout() {
        // remove user from local storage and set current user to null
        console.log('AAA')
        localStorage.removeItem('ActiveUser');
        this.localStorageService.setShowMenu(false);
        this.userSubject.next(null);
        await this.restclient.getrawjson('/users/logout', false);

        this.router.navigate(['/']);
    }


    createUser(){
//       this.restclient.postjson('/users', STUDENT);
//       this.restclient.postjson('/users', TEACHER);
//       this.restclient.postjson('/users', TEACHER2);
    }


    getUser(id: string){
      return this.restclient.getrawjson('/users/userId/' + id, false);
    }




}
