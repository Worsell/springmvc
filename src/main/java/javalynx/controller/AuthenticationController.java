package javalynx.controller;

import javalynx.service.RoleService;
import javalynx.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthenticationController {

    private UserService userService;

    private RoleService roleService;

    AuthenticationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String getLogin() {
        System.err.println("LOGIN");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin() {
        return "login";
    }

    @GetMapping("/error")
    public String getError() {
        return "";
    }
}
