package nucleus.tutget.qna.domain;

import jakarta.persistence.Entity;

@Entity
public class Answer extends QnaPost {
    private String questionId;

    public Answer() {
        
    }

    public Answer(String answerString, String userId, String userName, String questionId, Subject subject) {
        super(answerString, userId, userName, subject);
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }
}
