package FinalExam.UniBot.Service;

import FinalExam.UniBot.Entity.*;
import FinalExam.UniBot.Repository.ChatRepo;
import FinalExam.UniBot.Repository.ConvRepo;
import FinalExam.UniBot.Repository.QRepo;
import FinalExam.UniBot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ChatRepo chatRepo;

    @Autowired
    ConvRepo convRepo;

    @Autowired
    QRepo qRepo;

    public void save(User user) {
        userRepo.save(user);
    }


    public void setSession(HttpSession httpSession, Principal principal) {

        List<User> ops = userRepo.findAllByEmail(principal.getName());

        if (!ops.isEmpty()) {
            httpSession.setAttribute("email", ops.get(0).getEmail());
        } else {
            System.out.println("System Error");
        }
    }

    public Conversation createConversation(String anonymous) {
        Conversation conversation = new Conversation();
        conversation.setStudent("Anonymous");
        conversation.setStarttime(new Date());
        convRepo.save(conversation);

        return conversation;
    }

    public void saveChat(Long index, String user, String msg, String owner) {
        Message message = new Message();
        message.setMessage(msg);
        message.setUser(user);
        message.setOwner(owner);
        message.setConversation(index);
        message.setTime(new Date());
        chatRepo.save(message);
    }

    public String isInQnA(String message) {

        List<QnA> qnAList = qRepo.findAllByQuestion(message);

        if (qnAList.isEmpty()) {
            return "Sorry! I didn't understand";
        } else {
            return qnAList.get(0).getAnswer();
        }

    }

    public User getUser(String username) {

        return userRepo.findAllByEmail(username).get(0);

    }

    public List<Conversation> getConversations() {
        return convRepo.findAll();
    }

    public List<Message> getMessages(Long id) {
        return chatRepo.findAllByConversation(id);


    }
}
