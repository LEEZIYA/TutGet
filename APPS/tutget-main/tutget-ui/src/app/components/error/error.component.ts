import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: 'error-component',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent {
  error: any

  constructor(public router: Router) {
    this.error = this.router.getCurrentNavigation()?.extras;
  }
}
