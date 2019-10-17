package jdbc.tomcat.pool;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
}
