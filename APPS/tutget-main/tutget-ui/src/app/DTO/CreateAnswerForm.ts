export class CreateAnswerForm{

  public id: string;
  public acadLvl: string;
  public acadSubject: string = "";
  public qnaString: string;
  public postDate: Date;
  public posterId: string;
  public posterName: string;
  public upvotes: number;
  public downvotes: number;

  public questionId: string;
  
}