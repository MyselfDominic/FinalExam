package FinalExam.UniBot.Repository;

import FinalExam.UniBot.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
}
