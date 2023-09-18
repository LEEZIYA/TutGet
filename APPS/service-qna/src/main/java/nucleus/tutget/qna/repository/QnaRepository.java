package nucleus.tutget.qna.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nucleus.tutget.qna.domain.Answer;
import nucleus.tutget.qna.domain.Question;

public class QnaRepository {
    private Map<String, Question> idToQuestionMap;

    // stub with placeholder data. TODO change to database commands
    public QnaRepository() {
        idToQuestionMap = new HashMap<>();
    }

    public boolean addQuestion(Question newQuestion) {
        idToQuestionMap.put(newQuestion.getId(), newQuestion);

        return true;
    }

    public List<Question> getQuestionsList() {
        return new ArrayList<Question>(idToQuestionMap.values());
    }

    public void addAnswerToQuestion(Answer answer, String questionId) {
        Question q = idToQuestionMap.get(questionId);

        q.addAnswer(answer);
    }
}
