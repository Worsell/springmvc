package javalynx.service;

import javalynx.dao.RoleDao;
import javalynx.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleDao roleDao;

    private static String admin_string = "ADMIN";

    private static String user_string = "USER";

    private static Role ADMIN;

    private static Role USER;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
        if (ADMIN == null) {
            Role role = roleDao.getRoleByType(admin_string);
            if (role == null) {
                ADMIN = new Role(admin_string);
                roleDao.saveRole(ADMIN);
            } else {
                ADMIN = role;
            }
        }
        if (USER == null) {
            Role role = roleDao.getRoleByType(user_string);
            if (role == null) {
                USER = new Role(user_string);
                roleDao.saveRole(USER);
            } else {
                USER = role;
            }
        }
    }


    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    public Role getAdminRole() {
        return ADMIN;
    }

    public Role getUserRole() {
        return USER;
    }

}
