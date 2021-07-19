package resttaco.data;

import org.springframework.data.repository.CrudRepository;
import resttaco.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
