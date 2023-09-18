import { Injectable } from '@angular/core';
import { Question } from '../components/question';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QnaService {
  private qnaUrl:string = 'http://localhost:8094/qna/';
  private qnaGetQuestionsListUrl:string = this.qnaUrl + 'getQuestions';

  constructor(private http: HttpClient) {  }

  getQuestions(): Observable<Question[]> {
    console.log("in qna service");
    this.http.get<Question[]>(this.qnaGetQuestionsListUrl);
    console.log("in qna service 2");
    return this.http.get<Question[]>(this.qnaGetQuestionsListUrl);
  }
}
