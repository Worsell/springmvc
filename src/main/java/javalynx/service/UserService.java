package javalynx.service;

import javalynx.dao.UserDao;
import javalynx.model.Role;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private UserDao userDao;

    private RoleService roleService;

    public UserService(UserDao userDao, RoleService roleService) throws SQLException {
        User admin = new User();
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin");
        this.userDao = userDao;
        this.roleService = roleService;
        admin.setAuthorities(roleService.getAdminRole());
        userDao.addUser(admin);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUser();
    }

    public boolean updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public boolean removeUser(User user) throws SQLException {
        return userDao.removeUser(user);
    }

    public boolean removeUser(long id) throws SQLException {
        return removeUser((new User()).setId(id));
    }

    public boolean addUser(User user) throws SQLException {
        return userDao.addUser(user);
    }

    public User getUserByFLP(User user) throws SQLException {
        return userDao.validateUser(user) ? userDao.getUserByFLname(user.getFirstName(), user.getLastName()) : null;
    }
    public User getUserByID(Long id) throws SQLException {
        return userDao.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getUserByEmail(email);
    }

}
