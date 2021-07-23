package resttaco.data;

import org.springframework.data.repository.CrudRepository;
import resttaco.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
