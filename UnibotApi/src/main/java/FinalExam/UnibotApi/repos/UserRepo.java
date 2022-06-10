package FinalExam.UnibotApi.repos;

import FinalExam.UnibotApi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findAllByEmail(String username);
}
