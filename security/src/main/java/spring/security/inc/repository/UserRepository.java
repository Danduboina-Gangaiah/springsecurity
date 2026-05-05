package spring.security.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.inc.entity.User;
import java.util.*;

public interface UserRepository extends JpaRepository<User,Long>
{
	Optional<User> findByUsername(String username);

}
