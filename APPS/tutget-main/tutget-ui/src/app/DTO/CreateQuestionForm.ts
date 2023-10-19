export class CreateQuestionForm{

  public id: string;
  public acadLvl: string;
  public acadSubject: string = "";
  public questionTitle: string;
  public qnaString: string;
  public postDate: Date;
  public posterId: string;
  public upvotes: number;
  public downvotes: number;

  public answerIds: string[];
  
}