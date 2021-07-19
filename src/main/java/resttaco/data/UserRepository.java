package resttaco.data;

import org.springframework.data.repository.CrudRepository;
import resttaco.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
