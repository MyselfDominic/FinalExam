package FinalExam.UnibotApi.controllers;

import FinalExam.UnibotApi.entities.Conversation;
import FinalExam.UnibotApi.entities.Message;
import FinalExam.UnibotApi.utilities.ChatInput;
import FinalExam.UnibotApi.utilities.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController{

    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/api/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messages(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversationsuser(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messagesuser(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }


}
