package javalynx.service;

import javalynx.dao.UserDao;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return userDao.getAllUser();
    }

    public boolean addUser(User user) {
        return true;
    }

    public boolean removeUser(User user) {
        return true;
    }

}
