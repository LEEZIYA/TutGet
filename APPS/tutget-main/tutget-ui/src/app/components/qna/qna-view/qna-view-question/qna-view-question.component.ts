import { Component, Input } from '@angular/core';
import { Question } from '../question';
import { RestclientService } from 'src/app/services/restclient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { QnaService } from 'src/app/services/API/qna.service';
import { Answer } from '../answer';
import { CreateAnswerForm } from 'src/app/DTO/CreateAnswerForm';

@Component({
  selector: 'app-qna-view-question',
  templateUrl: './qna-view-question.component.html',
  styleUrls: ['./qna-view-question.component.css']
})
export class QnaViewQuestionComponent {
  private sub: any;

  constructor(private restClient: RestclientService, private router: Router, private activatedRoute: ActivatedRoute, private qnaService: QnaService) { }
  question?: Question;
  answers?: Answer[];

  answerInput?: string;

  tabSortingOptions: SortOption[] = [{displayName: "Newest", id: "new"},
  {displayName: "Weekly Popular", id:"popular"},
  {displayName: "Weekly Upvoted", id:"upvoted"}];
  selectedTabSort: SortOption = this.tabSortingOptions[0];


  createAnswerForm: CreateAnswerForm = new CreateAnswerForm();
  answerErr: string = '';

  readonly answerEmptyErr: string = 'Please input an answer';

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.qnaService.getQuestionWithAnswers(params['id']).then((res) => {
        if (res) {
          this.question = res.question;
          this.answers = res.answers;
        } else {
        }

      })
    })
  }

  backToQnaBoards() {
    this.router.navigate(['/qna']);
  }

  onSelectTabSort(sortOption: SortOption) {
    this.selectedTabSort = sortOption;
  }

  submit() {
    this.clearError();

    if(!this.validateFormHasError()){
      this.createAnswerForm.questionId = this.question!.id;
      this.createAnswerForm.posterId = "123";
      this.createAnswerForm.posterName = "LoggedInUserPlaceholder";
      this.createAnswerForm.acadLvl = this.question!.acadLvl;
      this.createAnswerForm.acadSubject = this.question!.acadSubj;
      this.createAnswerForm.postDate = new Date();
      this.qnaService.createAnswer(this.createAnswerForm)
      .then((res) => {
        if (res) {
          window.alert('Success! Reloading page now.');
          location.reload();
        } else {
          this.router.navigate(['/login']);
        }
      }).catch(() => {
        this.router.navigate(['/login']);
      });
    }
  }

  clearError() {
    this.answerErr = '';
  }

  validateFormHasError() {
    let hasErr = false;

    if (!this.createAnswerForm.qnaString) {
      this.answerErr = this.answerEmptyErr;
      hasErr = true;
    }

    return hasErr;
  }
}

class SortOption {
  displayName: String;
  id: String;
}
