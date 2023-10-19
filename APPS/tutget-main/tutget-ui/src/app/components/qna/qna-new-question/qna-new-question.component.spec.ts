import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QnaNewQuestionComponent } from './qna-new-question.component';

describe('QnaNewQuestionComponent', () => {
  let component: QnaNewQuestionComponent;
  let fixture: ComponentFixture<QnaNewQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QnaNewQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QnaNewQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
