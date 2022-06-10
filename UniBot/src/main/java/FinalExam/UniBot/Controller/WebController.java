package FinalExam.UniBot.Controller;

import FinalExam.UniBot.Entity.LoginResponse;
import FinalExam.UniBot.Entity.User;
import FinalExam.UniBot.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    CommonService commonService;


    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    Principal principal;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(HttpSession session){
        System.out.println(session.getAttribute("name"));
        return "index";
    }


    @RequestMapping(value = "/web/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @GetMapping("/web/login")
    public String login(Model model , @RequestParam("error") Optional<String> err ){

        if(err.isPresent()){
            model.addAttribute("error" , "Invalid Credentials!");
        }

        return "login";
    }

    @GetMapping("/web/logout")
    public String logout (Model model){
        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
        authetication.setAuthenticated(false);
        return "redirect:/";
    }



    @RequestMapping(value = "/web/home" , method = RequestMethod.GET)
    public String home(Principal principal, HttpSession httpSession){
        setupSession( httpSession, principal );
        System.out.println(principal.getName());
        httpSession.setAttribute("name" , "Dominic");
        return "home";
    }


    @RequestMapping(value = "/web/admin" , method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    private void setupSession(HttpSession httpSession, Principal principal) {
        if(httpSession.getAttribute("userLoggedIn") == null ){
            commonService.setSession(httpSession , principal);
        }
    }

    @RequestMapping(value = "/web/add" , method = RequestMethod.POST)
    public String registerSave(User user, HttpSession session , HttpServletRequest request , Principal principal) throws ServletException {

        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ));


        commonService.save(user);/////  ==== 5

        request.login(user.getEmail() , user.getPassword());

        return "redirect:/web/login";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String email = user.getEmail();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(authenticatedUser);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT" , securityContext);
    }



}
