import { Component } from '@angular/core';
import { Question } from './question';
import { User } from './user';
import { QnaService } from 'src/app/services/API/qna.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/API/login.service';

@Component({
  selector: 'app-qna',
  templateUrl: './qna.component.html',
  styleUrls: ['./qna.component.css']
})
export class QnaComponent {
    questionsList: Question[] = [];

    tabSortingOptions: SortOption[] = [{displayName: "Newest", id: "new"},
                                    {displayName: "Weekly Popular", id:"popular"},
                                    {displayName: "Weekly Upvoted", id:"upvoted"}];
    selectedTabSort: SortOption = this.tabSortingOptions[0];

    answerInput?: string;

    testingString: String = "";

    loggedInUser?: User;
    activeUser?: any

    constructor(
      private qnaService: QnaService,
      private router: Router,
      private loginService: LoginService
    ) {
    }

    ngOnInit() {
        this.getQuestionsList();
        console.log("in qna component");
    }

    onNewQuestion() {
      this.loginService.getUser().then( res => {
        if (res) {
          this.router.navigate(['/qna/new-question']);
        } else {
          this.router.navigate(['/login']);
        }
      })
    }

    onViewQuestion(question: Question): void {
        this.router.navigate(['/qna/view' , question.id]);
    }

    onSelectTabSort(sortOption: SortOption) {
        this.selectedTabSort = sortOption;
    }

    getQuestionsList(): void {
        this.qnaService.getQuestions().then(questions => this.questionsList = questions);
    }
}

class SortOption {
    displayName: String;
    id: String;
}
