import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QnaViewQuestionComponent } from './qna-view-question.component';

describe('QnaViewQuestionComponent', () => {
  let component: QnaViewQuestionComponent;
  let fixture: ComponentFixture<QnaViewQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QnaViewQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QnaViewQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
