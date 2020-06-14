package javalynx.service;

import javalynx.dao.RoleDao;
import javalynx.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    private static String admin_string = "ADMIN";

    private static String user_string = "USER";


    private static Role ADMIN;

    private static Role USER;


    public Role getAdminRole() {
        if (ADMIN == null) {
            Role role = roleDao.getRoleByType(admin_string);
            if (role == null) {
                ADMIN = new Role(admin_string);
                roleDao.saveRole(ADMIN);
            } else {
                ADMIN = role;
            }
        }
        return ADMIN;
    }

    public Role getUserRole() {
        if (USER == null) {
            Role role = roleDao.getRoleByType(user_string);
            if (role == null) {
                USER = new Role(user_string);
                roleDao.saveRole(USER);
            } else {
                USER = role;
            }
        }
        return USER;
    }

}
