package javalynx.service;

import javalynx.dao.UserDao;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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

    public String getStringAU(User user) throws SQLException {
        String answer = userDao.getUserById(userDao.getIdByUser(user)).getRole();
        if(Objects.equals(answer, "admin") || Objects.equals(answer, "user"))
            return answer;
        else
            return null;
    }
    public String getStringAU(Long id) throws SQLException {
        String answer = userDao.getUserById(id).getRole();
        if(Objects.equals(answer, "admin") || Objects.equals(answer, "user"))
            return answer;
        else
            return null;
    }

    public User getUserByFLP(User user) throws SQLException {
        return userDao.validateUser(user) ? userDao.getUserByFLname(user.getFirstName(), user.getLastName()) : null;
    }
    public User getUserByID(Long id) throws SQLException {
        return userDao.getUserById(id);
    }

}
