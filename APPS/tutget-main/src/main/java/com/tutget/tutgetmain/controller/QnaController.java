package com.tutget.tutgetmain.controller;

import com.tutget.tutgetmain.model.qna.Answer;
import com.tutget.tutgetmain.model.qna.Question;
import com.tutget.tutgetmain.model.qna.QuestionWithAnswers;
import com.tutget.tutgetmain.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/qna")
public class QnaController {
    
    @Autowired
    private QnaService qnaService;

    @RequestMapping("/getQuestions")
    public List<Question> getQuestions() {
        System.out.println("inQnaController main");
        return qnaService.getQuestions();
    }

    @RequestMapping("/getQuestion/{id}")
    public QuestionWithAnswers getQuestionWithAnswers(@PathVariable String id) {
        System.out.println("inQnaController main");
        return qnaService.getQuestionWithAnswers(id);
    }

    @RequestMapping("/getAnswers/{id}")
    public List<Answer> getAnswersByQuestionId(@PathVariable String id) {
        return qnaService.getAnswersByQuestionId(id);
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question){
        return qnaService.addQuestion(question);
    }

    @PostMapping("/addAnswer")
    public String addQuestion(@RequestBody Answer answer){
        return qnaService.addAnswer(answer);
    }
}
