package nucleus.tutget.qna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nucleus.tutget.qna.domain.AllQuestion;
import nucleus.tutget.qna.domain.Answer;
import nucleus.tutget.qna.domain.ListAnswer;
import nucleus.tutget.qna.domain.Question;
import nucleus.tutget.qna.domain.QuestionWithAnswers;
import nucleus.tutget.qna.repository.AnswerRepository;
import nucleus.tutget.qna.repository.QuestionRepository;

@Service
public class QnaService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public AllQuestion getAllQuestions() {
        AllQuestion allQuestion = new AllQuestion();
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);

        allQuestion.setAllQuestion(questions);
        return allQuestion;
    }

    public ListAnswer getAnswersByQuestionId(String id) {
        ListAnswer listAnswer = new ListAnswer();
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            return new ListAnswer();
        }
        List<Answer> answers = new ArrayList<>();
        List<String> answersId = optionalQuestion.get().getAnswers();
        answerRepository.findAllById(answersId).forEach(answers::add);
        
        listAnswer.setAnswers(answers);
        return listAnswer;
    }

    public Optional<Question> getQuestionById(String id) {
        return questionRepository.findById(id);
    }

    public Optional<QuestionWithAnswers> getQuestionWithAnswersById(String id) {
        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers();
        Optional<Question> question = getQuestionById(id);
        if (!question.isPresent()) {
            return Optional.empty();
        }
        questionWithAnswers.setQuestion(questionRepository.findById(id).get());
        questionWithAnswers.setAnswers(getAnswersByQuestionId(id).getListAnswers());
        return Optional.of(questionWithAnswers);
    }

    public String addQuestion(Question question) {
        questionRepository.save(question);
        return question.getId();
    }

    public String addAnswer(Answer answer) {
        Question question = questionRepository.findById(answer.getQuestionId()).get();
        question.addAnswer(answer.getId());
        questionRepository.save(question);
        answerRepository.save(answer);

        return answer.getId();
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public void updateAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }

    public void deleteAnswer(String id) {
        Answer answer = answerRepository.findById(id).get();
        Question question = questionRepository.findById(answer.getQuestionId()).get();
        question.removeAnswerById(answer.getId());
        questionRepository.save(question);
        answerRepository.deleteById(id);
    }
}
