import { Component, Input } from '@angular/core';
import { Question } from '../question';
import { RestclientService } from 'src/app/services/restclient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { QnaService } from 'src/app/services/API/qna.service';
import { Answer } from '../answer';

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
}

class SortOption {
  displayName: String;
  id: String;
}