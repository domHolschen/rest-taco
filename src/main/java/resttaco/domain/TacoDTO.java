package resttaco.domain;

import lombok.Data;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoDTO {
    @NotBlank
    @Size(min=1, message = "Please enter a valid taco name (at least 1 character).")
    String name;
    @Size(min=2, message = "You must have at least 2 ingredients to make a taco.")
    @NotNull
    ArrayList<String> ingredientIds;

    public Taco convertToTaco(List<Ingredient> ingredientList) {
        Taco taco = new Taco();
        taco.setName(name);
        taco.setIngredients(ingredientList);
        return taco;
    }
}
