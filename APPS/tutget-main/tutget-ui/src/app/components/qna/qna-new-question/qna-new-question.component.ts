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
  
  constructor(private restClient: RestclientService, private router: Router, private activatedRoute: ActivatedRoute, private qnaService: QnaService) { }

  loadComponent(){
  }

  backToQnaBoards() {
    this.router.navigate(['/qna']);
  }

  submit() {
    this.createQuestionForm.postDate = new Date();
    this.qnaService.createQuestion(this.createQuestionForm)
    .then((res) => {
      window.alert('Success! Navigating to main qna board.');
      this.router.navigate(['/qna']);
    })
  }
}
