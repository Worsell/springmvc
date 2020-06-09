package javalynx.controller;

import javalynx.service.UserService;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCarsTable(ModelMap model) throws SQLException {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@RequestParam Map<String, Object> params, ModelMap model) throws SQLException {
        String firstname = (String) params.get("firstname");
        String lastname = (String) params.get("lastname");
        String role = (String) params.get("role");
        String password = (String) params.get("password");
        User user = new User(firstname, lastname, password);
        user.setRole(role);
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdate(ModelMap model) throws SQLException {
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String postUpdate(@RequestParam Map<String, Object> params, ModelMap model) throws SQLException {
        long id = Long.parseLong((String) params.get("id"));
        String firstname = (String) params.get("firstname");
        String lastname = (String) params.get("lastname");
        String role = (String) params.get("role");
        String password = (String) params.get("password");
        User user = new User(firstname, lastname, password);
        user.setId(id);
        user.setRole(role);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String postDelete(@RequestParam Map<String, Object> params, ModelMap model) throws SQLException {
        long id = Long.parseLong((String) params.get("id"));
        User user = new User();
        user.setId(id);
        userService.removeUser(user);
        return "redirect:/admin/";
    }

}
