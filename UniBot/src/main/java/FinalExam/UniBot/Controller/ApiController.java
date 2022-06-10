package FinalExam.UniBot.Controller;

import FinalExam.UniBot.Entity.Conversation;
import FinalExam.UniBot.Entity.Message;
import FinalExam.UniBot.Entity.User;
import FinalExam.UniBot.Service.ChatInput;
import FinalExam.UniBot.Service.MessageBody;
import FinalExam.UniBot.Service.Response;
import FinalExam.UniBot.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/web/api/hello" , method = RequestMethod.POST)
    public ResponseEntity<Response> hello(@RequestBody MessageBody messageBody){
        String student =  "anonymous";

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();

        if( authetication != null && authetication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authetication.getPrincipal() ;
            student = userDetails.getUsername();
        }

        if( messageBody.getIndex() == 0){
            Conversation con = commonService.createConversation( student );
            messageBody.setIndex( con.getId() );
        }

        commonService.saveChat( messageBody.getIndex(), student , messageBody.getMessage() , "user" );


        String botResponse = unibot(messageBody.getMessage() );

        Response response = new Response();
        response.setStatus("success");
        response.setData(botResponse);
        response.setConvId(messageBody.getIndex());

        commonService.saveChat( messageBody.getIndex(), student , botResponse , "bot" );

        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    public String unibot(String message ){

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();

        if( authetication != null && authetication.getPrincipal() instanceof UserDetails ){
            UserDetails userDetails = (UserDetails) authetication.getPrincipal() ;
            String[] userpropeties = { "name" , "email" , "department" , "faculty" , "mobile"};
            if(Arrays.stream(userpropeties).toList().contains( message.toLowerCase())){
                User user = commonService.getUser(userDetails.getUsername());
                switch ( message.toLowerCase() ){
                    case "name" : return user.getName();
                    case "email" : return user.getEmail();
                    case "department" : return user.getDepartment();
                    case "faculty" : return user.getFaculty();
                    case "mobile" : return user.getMobile();
                }
            }
        }

        String qna = commonService.isInQnA( message );
        return qna;
    }

    @ResponseBody
    @RequestMapping(value = "/web/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/web/admin/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messages(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }
}
