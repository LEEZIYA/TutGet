import { Answer } from "./answer";
import { qnaPost } from "./qnaPost";

export interface Question extends qnaPost {
    answers: Answer[];
}