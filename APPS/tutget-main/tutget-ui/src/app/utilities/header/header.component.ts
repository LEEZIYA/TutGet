import { LoginService } from './../../services/API/login.service';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  @Input()
  showMenu: boolean = false;

  @Input()
  isStudent: boolean = false;

  constructor(private router: Router, private loginService: LoginService){

  }



  createListing(){
    this.router.navigate(['listing'])
  }

  createPayment(){
    this.router.navigate(['payment'])
  }

  createSearch(){
    this.router.navigate(['search'])
  }

  logOut(){
    this.loginService.logout();
  }
}
