import { LoginService } from 'src/app/services/API/login.service';
import { LocalStorageService } from './../../services/local-storage.service';
import { Component, Input } from '@angular/core';
import { CreateUserForm } from 'src/app/DTO/CreateUserForm';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

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

  }


  ngOnInit() {
    this.loginService.user.subscribe(user => {
      this.showMenu = this.localStorageService.getShowMenu();

      this.loginService.getUser().then( res => {
            if (res) {
              this.firstName = res.firstName;
            } else {
              this.firstName = "";
            }
            console.log('main component: ' + this.showMenu)
          })
    });



//     this.router.events
//       .pipe(filter((event: any) => event instanceof NavigationEnd))
//       .subscribe(() => {
//         this.loginService.getUser().then( res => {
//           if (res) {
//             console.log('main component: res');
//           } else {
//             console.log('main component: no res');
//           }
//         })
//       });
  }


}
