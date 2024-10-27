import { LoginService } from 'src/app/services/API/login.service';
import { LocalStorageService } from './../../services/local-storage.service';
import { Component, Input } from '@angular/core';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  activeUser: CreateUserForm | null;
  firstName?: string

  @Input()
  showMenu: boolean = false;

  constructor(private loginService: LoginService, private router: Router, private localStorageService: LocalStorageService) {
    this.showMenu = localStorageService.getShowMenu();
  }


  ngOnInit() {
//     this.loginService.user.subscribe(user => this.activeUser = user);
    this.loginService.getUser('unusedIdGetFromContext').then( res => {
      this.firstName = res.firstName
    })
  }


}
