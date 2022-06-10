package FinalExam.UnibotApi.repos;

import FinalExam.UnibotApi.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
}
