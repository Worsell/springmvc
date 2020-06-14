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
        System.out.println("ADDDD");

        System.out.println(authentication.getName());
        System.out.println("DDDDA");

        User user = (User) userService.loadUserByUsername(authentication.getName());
        model.addAttribute("id", user.getId());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("firstname", user.getFirstName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("authorities", user.getAuthorities());
        return "user";
    }
}
