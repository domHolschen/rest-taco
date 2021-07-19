package resttaco.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import resttaco.domain.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
