package nucleus.tutget.qna.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import nucleus.tutget.qna.domain.AllQuestion;
import nucleus.tutget.qna.domain.Answer;
import nucleus.tutget.qna.domain.ListAnswer;
import nucleus.tutget.qna.domain.Question;
import nucleus.tutget.qna.domain.QuestionWithAnswers;
import nucleus.tutget.qna.domain.Student;
import nucleus.tutget.qna.domain.Subject;
import nucleus.tutget.qna.domain.Tutor;
import nucleus.tutget.qna.repository.UserRepository;
import nucleus.tutget.qna.service.QnaService;

// CrossOrigin to bypass CORS POLICY blocking
@RestController
@Validated
@RequestMapping(value = "/qna")
public class QnaController {
    
    @Autowired
    private QnaService qnaService;

    private static final UserRepository userRepo = new UserRepository();

    // test data. TODO remove and add methods for database.
    @PostConstruct
    public void init() {
        Student student1 = new Student("Anna");
        Student student2 = new Student("Bree");
        Tutor tutor1 = new Tutor("Clara");
        Tutor tutor2 = new Tutor("Darlene");
        userRepo.addUser(student1);
        userRepo.addUser(student2);
        userRepo.addUser(tutor1);
        userRepo.addUser(tutor2);
        Question q1 = new Question("What is the powerhouse of the cell?", "Haven't gotten to this part of my science class yet but everyone is talking and making fun of this I dont understend pls help,,", student1.getId(), student1.getName(), Subject.SCIENCE);
        Question q2 = new Question("Should I use \"comprising of\" or \"compromising of\"?", "As title.", student1.getId(), student1.getName(), Subject.ENGLISH);
        Question q3 = new Question("Need someone to explain what imaginary numbers mean :<", "You telling me numbers aint real??? smh smh", student2.getId(), student2.getName(), Subject.MATHS);
        qnaService.addQuestion(q1);
        qnaService.addQuestion(q2);
        qnaService.addQuestion(q3);
        Answer a1 = new Answer("The mitochondria makes energy for the cell, which is also often described as the powerhouse of the cell.", tutor1.getId(), tutor1.getName(), q1.getId(), q1.getSubject());
        Answer a2 = new Answer("Mitochondria are known as the powerhouse of cells. It is because the mitochondrion is the site of cellular respiration where energy in the form of ATP (Adenosine triphosphate) is generated as a result of oxidation of food constituents.", tutor2.getId(), tutor2.getName(), q1.getId(), q1.getSubject());
        qnaService.addAnswer(a1);
        qnaService.addAnswer(a2);
    }

	@GetMapping("/")
	public String index() {
		return "Hewwo!! ^w^";
	}

	@GetMapping(value = "/getQuestions")
    public AllQuestion getQuestionsList() {
        return qnaService.getAllQuestions();
    }

    @GetMapping(value = "/getQuestion/{id}")
    public Optional<QuestionWithAnswers> getQuestionWithAnswers(@PathVariable String id) {
        return qnaService.getQuestionWithAnswersById(id);
    }

    @GetMapping(value = "/getAnswers/{questionId}")
    public ListAnswer getAnswersByQuestionId(@PathVariable String questionId) {
        return qnaService.getAnswersByQuestionId(questionId);
    }
    
	@PostMapping(value = "/addQuestion")
    public String addQuestion(@RequestBody Question question) {
        return qnaService.addQuestion(question);
    }

    @PostMapping(value = "/addAnswer")
    public String addAnswer(@RequestBody Answer answer) {
        return qnaService.addAnswer(answer);
    }
}
