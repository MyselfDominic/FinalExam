package FinalExam.UniBot.Repository;


import FinalExam.UniBot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {



    List<User> findAllByEmail (String username);

}
