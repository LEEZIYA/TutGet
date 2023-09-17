export class CreateListingForm{

  public acadLvl: string;
  public acadSubject: string = "";
  public postalCode: string;
  public postDate: Date;
  public dayOfWeek: string;
  public frequency: number = -1;
  public startDate: Date;
  public hourlyRate: number;
  public description: string = "";
  public selectedHour: number[];
  public selectedMin: number[];
  public selectedHourNum: string;

}