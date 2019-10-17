package jdbc.tomcat.pool;

import io.micronaut.spring.tx.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = builder.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        Query<User> query = session.createQuery(cr);
        return query.getResultList();
    }
}
