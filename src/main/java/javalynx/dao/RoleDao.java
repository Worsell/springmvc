package javalynx.dao;

import javalynx.model.Role;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class RoleDao {

    @PersistenceContext
    EntityManager manager;

    @Transactional
    @Nullable
    public Role getRoleByType(String type) {
        String hql = "from Role where type = :t";
        List<Role> roles = manager.createQuery(hql, Role.class).setParameter("t", type).getResultList();
        return roles.size() == 1 ? roles.get(0) : null;
    }

    @Transactional
    public boolean saveRole(Role role) {
        manager.persist(role);
        manager.flush();
        return true;
    }

}
