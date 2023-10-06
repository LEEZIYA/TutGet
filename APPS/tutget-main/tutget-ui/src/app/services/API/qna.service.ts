import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';
import { CreateQuestionForm } from 'src/app/DTO/CreateQuestionForm';
import { CreateAnswerForm } from 'src/app/DTO/CreateAnswerForm';

@Injectable({
  providedIn: 'root'
})
export class QnaService {
  BASE_URL: string = '/qna';

  constructor(private restclient: RestclientService) {}

  getQuestions() {
    return this.restclient.getrawjson(this.BASE_URL + '/' + 'getQuestions', false);
  }

  getQuestionWithAnswers(id: String) {
    return this.restclient.getrawjson(this.BASE_URL + '/getQuestion/'+ id, false);
  }

  getAnswersByQuestionId(questionId: String) {
    return this.restclient.getrawjson(this.BASE_URL + '/getAnswers/' + questionId, false);
  }

  createQuestion(form: CreateQuestionForm){
    return this.restclient.postjsonReturnString(this.BASE_URL + '/addQuestion', form);
  }

  createAnswer(form: CreateAnswerForm){
    return this.restclient.postjsonReturnString(this.BASE_URL + '/addAnswer', form);
  }
}
