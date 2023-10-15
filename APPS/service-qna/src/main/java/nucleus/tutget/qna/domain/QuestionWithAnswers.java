package nucleus.tutget.qna.domain;

import java.util.List;

public class QuestionWithAnswers {
    private Question question;
    private List<Answer> answers;

    public QuestionWithAnswers() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getanswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
