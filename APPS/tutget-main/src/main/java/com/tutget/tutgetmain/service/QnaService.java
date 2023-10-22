package com.tutget.tutgetmain.service;

import com.tutget.tutgetmain.model.qna.Answer;
import com.tutget.tutgetmain.model.qna.ListAnswer;
import com.tutget.tutgetmain.model.qna.QuestionWithAnswers;
import com.tutget.tutgetmain.model.qna.Question;
import com.tutget.tutgetmain.model.qna.AllQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class QnaService {

    @Autowired
    private RestTemplate restTemplate;

    private String microserviceURL = "http://qna-service/qna";

    public List<Question> getQuestions() {
        AllQuestion allQuestions = restTemplate.getForObject(microserviceURL + "/getQuestions", AllQuestion.class);
        return allQuestions.getAllQuestion();
    }
    public QuestionWithAnswers getQuestionWithAnswers(String id) {
        return restTemplate.getForObject(microserviceURL + "/getQuestion/" + id, QuestionWithAnswers.class);
    }

    public List<Answer> getAnswersByQuestionId(String questionId) {
        ListAnswer listAnswers = restTemplate.getForObject(microserviceURL + "/getAnswers/" + questionId, ListAnswer.class);
        return listAnswers.getListAnswers();
    }

    public String addQuestion(Question question) {
        return restTemplate.postForObject(microserviceURL + "/addQuestion", question, String.class);
    }
}
