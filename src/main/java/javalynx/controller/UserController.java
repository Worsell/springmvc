package javalynx.controller;


import javalynx.model.User;
import javalynx.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public String getUser(Authentication authentication, ModelMap model){
        // User
        User user = (User) userService.loadUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "u";
    }
}
