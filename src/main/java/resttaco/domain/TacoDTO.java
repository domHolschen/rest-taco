package resttaco.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoDTO {
    String name;
    ArrayList<String> ingredientIds;

    public Taco convertToTaco(List<Ingredient> ingredientList) {
        Taco taco = new Taco();
        taco.setName(name);
        taco.setIngredients(ingredientList);
        return taco;
    }
}
