import { User } from "./user";

export interface qnaPost {
    id: string;
    qnaString: string;
    upvotes: number;
    downvotes: number;
    postDate: string;
    posterId: string;
    posterName: string;
}