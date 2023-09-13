import { Component } from '@angular/core';
import { Question } from './question';
import { User } from './user';
import { QnaService } from '../services/qna.service';

@Component({
  selector: 'app-qna',
  templateUrl: './qna.component.html',
  styleUrls: ['./qna.component.css']
})
export class QnaComponent {
    questionsList: Question[] = [];
    selectedQuestion?: Question;

    answerInput?: string;

    testingString: String = "";

    loggedInUser?: User;

    constructor(private qnaService: QnaService) { }

    ngOnInit() {
        this.getQuestionsList();
        console.log("in qna component");
    }

    onViewQuestion(question: Question): void {
        this.selectedQuestion = question;
    }

    getQuestionsList(): void {
        this.qnaService.getQuestions()
            .subscribe(questions => this.questionsList = questions);
    }
}
