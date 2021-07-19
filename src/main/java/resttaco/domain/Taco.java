package resttaco.domain;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
    @NotBlank
    @Size(min=1, message = "Please enter a valid taco name (at least 1 character).")
    private String name;
    @Size(min=2, message = "You must have at least 2 ingredients to make a taco.")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {this.ingredients.add(ingredient);}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
