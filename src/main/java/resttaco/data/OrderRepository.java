package resttaco.data;

import org.springframework.data.repository.CrudRepository;
import resttaco.domain.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {
}
