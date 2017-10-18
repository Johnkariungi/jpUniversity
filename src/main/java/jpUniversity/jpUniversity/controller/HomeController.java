package jpUniversity.jpUniversity.controller;

import jpUniversity.jpUniversity.domain.User;
import jpUniversity.jpUniversity.domain.security.PasswordResetToken;
import jpUniversity.jpUniversity.service.UserService;
import jpUniversity.jpUniversity.service.impl.UserSecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired /*auto wire user service for token*/
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }

    @RequestMapping("/newUser")
    public String newUser(
            Locale locale,
            @RequestParam("token") String token,
            Model model) {

        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if(passToken == null)
            String message = "Invalid token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest"; /*error page for those trying to hijack the page*/

        /*code block below is to set the current session to that user*/

        /*otherwise get user from the token in the database*/
        User user = passToken.getUser();
        /*assign username*/
        String username = user.getUsername();
        /*get user details form user security services*/
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        /*define authentication environment*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
        /*retrieve current security context from the current and set authentication to the current user*/
        SecurityContextHolder.getContext().setAuthentication(authentication);



        model.addAttribute("classActiveEdit", true);
        return "myProfile";
    }
}
