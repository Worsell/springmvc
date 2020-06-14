package javalynx.controller;

import javalynx.service.RoleService;
import javalynx.service.UserService;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getCarsTable(ModelMap model) throws SQLException {
        System.out.println("TAG TAG");
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/create")
    public String postCreate(User user, @RequestParam(value = "admin", defaultValue = "false") boolean a,
                             @RequestParam(value = "user", defaultValue = "false") boolean u) throws SQLException {
        if (a) {
            user.setAuthorities(roleService.getAdminRole());
        }
        if (u) {
            user.setAuthorities(roleService.getUserRole());
        }
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String getUpdate() throws SQLException {
        return "update";
    }

    @PostMapping("/update")
    public String postUpdate(User user) throws SQLException {
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id) throws SQLException {
        userService.removeUser(id);
        return "redirect:/admin/";
    }

}
