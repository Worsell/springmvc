package javalynx.dao;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.SessionFactory;
import javalynx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> getAllUser() {
        TypedQuery<User> query= sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
