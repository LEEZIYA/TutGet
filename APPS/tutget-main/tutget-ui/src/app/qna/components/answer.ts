import { qnaPost } from "./qnaPost";

export interface Answer extends qnaPost {
    questionId: string;
}