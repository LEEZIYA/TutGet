import { Component, Input } from '@angular/core';
import { Question } from '../question';

@Component({
  selector: 'app-qna-view-question',
  templateUrl: './qna-view-question.component.html',
  styleUrls: ['./qna-view-question.component.css']
})
export class QnaViewQuestionComponent {
  @Input() question?: Question;
  answerInput?: string;
}
