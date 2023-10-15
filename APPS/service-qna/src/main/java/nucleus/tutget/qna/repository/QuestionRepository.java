package nucleus.tutget.qna.repository;

import nucleus.tutget.qna.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, String> {
    
}
