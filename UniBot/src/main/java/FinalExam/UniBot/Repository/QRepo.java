package FinalExam.UniBot.Repository;

import FinalExam.UniBot.Entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRepo extends JpaRepository<QnA , Long> {
    List<QnA> findAllByQuestion(String message);
}
