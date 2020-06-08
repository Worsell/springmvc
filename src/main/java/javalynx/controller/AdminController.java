package javalynx.controller;

import javalynx.service.UserService;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCarsTable(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String postUpdate(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String postDelete(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

}
