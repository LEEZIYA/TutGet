package nucleus.tutget.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Question extends QnaPost {
    private Subject subject;
    private List<Answer> answers;


    public Question(String questionString, User user, Subject subject) {
        super(questionString, user);
        this.subject = subject;
        this.answers = new ArrayList<>();
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
