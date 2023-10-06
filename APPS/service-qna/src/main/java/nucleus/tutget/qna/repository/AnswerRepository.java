package nucleus.tutget.qna.repository;

import nucleus.tutget.qna.domain.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, String> {
    
}
