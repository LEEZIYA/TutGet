import { Component } from '@angular/core';
import { Question } from './question';
import { User } from './user';
import { QnaService } from 'src/app/services/API/qna.service';
import { Router } from '@angular/router';

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

    constructor(private qnaService: QnaService, private router: Router) { }

    ngOnInit() {
        this.getQuestionsList();
        console.log("in qna component");
    }

    onNewQuestion() {
      this.router.navigate(['/qna/new-question']);
    }

    onViewQuestion(question: Question): void {
        this.router.navigate(['/qna/view' , question.id]);
    }

    onSelectTabSort(sortOption: SortOption) {
        this.selectedTabSort = sortOption;
    }

    getQuestionsList(): void {
        this.qnaService.getQuestions().then(questions => this.questionsList = questions['allQuestion']);
    }
}

class SortOption {
    displayName: String;
    id: String;
}
