import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CreateQuestionForm } from 'src/app/DTO/CreateQuestionForm';
import { QnaService } from 'src/app/services/API/qna.service';
import { RestclientService } from 'src/app/services/restclient.service';
import { ACADEMICLEVELIDLIST, ACADEMICLEVELSUBJECTLIST, ACADEMICSUBJECTIDLIST } from 'src/app/utilities/code-table/AcademicLevelSubjectList';

@Component({
  selector: 'app-qna-new-question',
  templateUrl: './qna-new-question.component.html',
  styleUrls: ['./qna-new-question.component.css']
})
export class QnaNewQuestionComponent {
  createQuestionForm: CreateQuestionForm = new CreateQuestionForm();
  academicLvlList: Map<String, String> = ACADEMICLEVELIDLIST;
  academicLvlSubjectList: Map<String, String[]> = ACADEMICLEVELSUBJECTLIST;
  academicSubjectList: Map<String, String> = ACADEMICSUBJECTIDLIST;

  titleErr: string = '';
  acadLvlErr: string = '';
  acadSubjErr: string = '';
  descErr: string = '';

  readonly titleEmptyErr: string = 'Please input the question title';
  readonly acadLvlEmptyErr: string = 'Please select an academic level';
  readonly acadSubjEmptyErr: string = 'Please select a subject';
  readonly descEmptyErr: string = 'Please input the question description';

  constructor(private restClient: RestclientService, private router: Router, private activatedRoute: ActivatedRoute, private qnaService: QnaService) { }

  loadComponent(){
  }

  backToQnaBoards() {
    this.router.navigate(['/qna']);
  }


  submit() {
    this.clearError();

    if(!this.validateFormHasError()){
      this.createQuestionForm.posterId = '123';
      this.createQuestionForm.posterName = 'LoggedInUserPlaceholder'; // TODO with profile
      this.createQuestionForm.postDate = new Date();
      this.qnaService.createQuestion(this.createQuestionForm)
      .then((res) => {
        window.alert('Success! Navigating to main qna board.');
        this.router.navigate(['/qna']);
      })
    }
  }

  clearError() {
    this.titleErr = '';
    this.acadLvlErr = '';
    this.acadSubjErr = '';
    this.descErr = '';
  }

  validateFormHasError() {
    let hasErr = false;

    if(!this.createQuestionForm.questionTitle){
      this.titleErr = this.titleEmptyErr;
      hasErr = true;
    }
    if(!this.createQuestionForm.acadLvl){
      this.acadLvlErr = this.acadLvlEmptyErr;
      hasErr = true;
    }
    if(!this.createQuestionForm.acadSubject){
      this.acadSubjErr = this.acadSubjEmptyErr;
      hasErr = true;
    }
    if(!this.createQuestionForm.qnaString){
      this.descErr = this.descEmptyErr;
      hasErr = true;
    }

    return hasErr;
  }
}
