package nucleus.tutget.qna.controller;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nucleus.tutget.qna.domain.Answer;
import nucleus.tutget.qna.domain.Question;
import nucleus.tutget.qna.domain.Student;
import nucleus.tutget.qna.domain.Subject;
import nucleus.tutget.qna.domain.Tutor;
import nucleus.tutget.qna.domain.User;
import nucleus.tutget.qna.domain.UserType;
import nucleus.tutget.qna.repository.QnaRepository;
import nucleus.tutget.qna.repository.UserRepository;

// CrossOrigin to bypass CORS POLICY blocking
@CrossOrigin(origins = "http://localhost:8080/") 
@RestController
@Validated
@RequestMapping(value = "/qna")
public class QnaController {
    
    private static final QnaRepository qnaRepo = new QnaRepository();
    private static final UserRepository userRepo = new UserRepository();

    // test data. TODO remove and add methods for database.
    static {
        Student student1 = new Student("Anna");
        Student student2 = new Student("Bree");
        Tutor tutor1 = new Tutor("Clara");
        Tutor tutor2 = new Tutor("Darlene");
        userRepo.addUser(student1);
        userRepo.addUser(student2);
        userRepo.addUser(tutor1);
        userRepo.addUser(tutor2);
        Question q1 = new Question("What is the powerhouse of the cell?", student1, Subject.SCIENCE);
        Question q2 = new Question("Should I use \"comprising of\" or \"compromising of\"?", student1, Subject.ENGLISH);
        Question q3 = new Question("Need someone to explain what imaginary numbers mean :<", student2, Subject.MATHS);
        qnaRepo.addQuestion(q1);
        qnaRepo.addQuestion(q2);
        qnaRepo.addQuestion(q3);
        Answer a1 = new Answer("The mitochondria makes energy for the cell, which is also often described as the powerhouse of the cell.", tutor1, q1.getId());
        Answer a2 = new Answer("Mitochondria are known as the powerhouse of cells. It is because the mitochondrion is the site of cellular respiration where energy in the form of ATP (Adenosine triphosphate) is generated as a result of oxidation of food constituents.", tutor2, q1.getId());
        qnaRepo.addAnswerToQuestion(a1, q1.getId());
        qnaRepo.addAnswerToQuestion(a2, q1.getId());
    }

	@GetMapping("/")
	public String index() {
		return "Hewwo!! ^w^";
	}

	@GetMapping(value = "/getQuestions")
    public List<Question> getQuestionsList() {
        return qnaRepo.getQuestionsList();
    }

    
	// @RequestMapping(value = "/newQuestion", method = RequestMethod.POST)
	@GetMapping(value = "/newQuestions")
	@ResponseBody
    public Question createQuestion(@NonNull String questionString, String questionerUserId, Subject subject) {
        User questioner = userRepo.getUserById(questionerUserId);
        Question newQuestion = new Question(questionString, questioner, subject);

        qnaRepo.addQuestion(newQuestion);

        return newQuestion;
    }
}
