package javalynx.controller;

import com.sun.tracing.dtrace.ModuleAttributes;
import javalynx.model.Role;
import javalynx.service.RoleService;
import javalynx.service.UserService;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/create")
    public String postCreate(User user, @RequestParam(value = "roles") String[] roles) throws SQLException {
        if (Arrays.stream(roles).anyMatch((x) -> x.equals(roleService.getAdminRole().getAuthority()))) {
            user.setAuthorities(roleService.getAdminRole());
        }
        if (Arrays.stream(roles).anyMatch((x) -> x.equals(roleService.getUserRole().getAuthority()))) {
            user.setAuthorities(roleService.getUserRole());
        }
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam Long id, ModelMap modelMap) throws SQLException {
        User user = userService.getUserByID(id);
        modelMap.addAttribute("firstName", user.getFirstName());
        modelMap.addAttribute("lastName", user.getLastName());
        modelMap.addAttribute("email", user.getEmail());
        modelMap.addAttribute("password", user.getPassword());
        modelMap.addAttribute("urole", user.getAuthorities());
        List<Role> roles = roleService.getRoles();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("id", id);

        return "update";
    }

    @PostMapping("/update")
    public String postUpdate(User user, @RequestParam(value = "nroles") List<String> nroles) throws SQLException {
        if (nroles.stream().anyMatch((x) -> x.equals(roleService.getAdminRole().getAuthority()))) {
            user.setAuthorities(roleService.getAdminRole());
        }
        if (nroles.stream().anyMatch((x) -> x.equals(roleService.getUserRole().getAuthority()))) {
            user.setAuthorities(roleService.getUserRole());
        }
        System.out.println(nroles);
        System.out.println(user);
        System.err.println("ERIT");
        System.err.println(userService.updateUser(user));
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam Long id) throws SQLException {
        userService.removeUser(id);
        return "redirect:/admin/";
    }

}
