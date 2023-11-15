package nucleus.tutget.qna.domain;

import java.util.List;

public class AllQuestion {

    private List<Question> allQuestion;

    public AllQuestion() {
    }

    public List<Question> getAllQuestion() {
        return allQuestion;
    }

    public void setAllQuestion(List<Question> allQuestion) {
        this.allQuestion = allQuestion;
    }
}

