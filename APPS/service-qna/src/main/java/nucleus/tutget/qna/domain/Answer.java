package nucleus.tutget.qna.domain;

public class Answer extends QnaPost {
    private String questionId;

    public Answer(String answerString, User user, String questionId) {
        super(answerString, user);
        this.questionId = questionId;
    }
}
