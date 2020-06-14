package javalynx.dao;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javalynx.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        TypedQuery<User> query = manager
                .createQuery("from User", User.class);
        List<User> answer = query.getResultList();
        System.out.println(answer);
        return answer;
    }

    @Transactional()
    public boolean addUser(User user) throws SQLException {
        manager.persist(user);
        manager.flush();
        return true;
    }

    @Transactional
    public boolean removeUser(long id) throws SQLException {
        User user = manager.find(User.class, id);
        manager.remove(user);
        return true;
    }

    @Transactional
    public boolean removeUser(User user) throws SQLException {
        return removeUser(user.getId());
    }

    @Transactional
    public boolean validateUser(User user) throws SQLException {
        String hql = "select count(id) FROM User where firstname = :firstname and lastname = :lastname and password = :password";
        TypedQuery<User> query = manager
                .createQuery(hql, User.class);
        query.setParameter("firstname", user.getFirstName());
        query.setParameter("lastname", user.getLastName());
        query.setParameter("password", user.getPassword());
        return query.getSingleResult() != null;
    }

    @Transactional
    public boolean updateUser(User user) throws SQLException {
        if (user.getId() == 0) return false;
        manager.merge(user);
        manager.flush();
        return true;
    }

    @Nullable
    @Transactional
    public User getUserById(long user) throws SQLException {
        if (user == 0) return null;
        User answer;
        answer = manager.find(User.class, user);
        return answer;
    }

    @Nullable
    @Transactional
    public User getUserByEmail(String email) {
        String hql = "FROM User where email = :email";
        TypedQuery<User> query = manager.createQuery(hql, User.class);
        query.setParameter("email", email);
        User user;
        user = query.getSingleResult();
        System.out.println(user);
        return user;
    }

    @Nullable
    @Transactional
    public User getUserByFLname(String firstname, String lastname) throws SQLException {
        String hql = "FROM User where firstname = :firstname and lastname = :lastname";
        User user;
        TypedQuery<User> query = manager.createQuery(hql, User.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        user =  query.getSingleResult();
        return user;
    }

    @Nullable
    @Transactional
    public Long getIdByFLname(String firstname, String lastname) throws SQLException {
        return Objects.requireNonNull(getUserByFLname(firstname, lastname)).getId();
    }

    @Nullable
    @Transactional
    public Long getIdByUser(User user) throws SQLException {
        return getIdByFLname(user.getFirstName(), user.getLastName());
    }

    @Nullable
    @Transactional
    public Collection<? extends GrantedAuthority> getRolesByEmail(String email) {
        return Objects.requireNonNull(getUserByEmail(email)).getAuthorities();
    }

}
