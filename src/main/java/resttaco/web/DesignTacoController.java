package resttaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import resttaco.data.IngredientRepository;
import resttaco.data.TacoRepository;
import resttaco.domain.Ingredient;
import resttaco.domain.Taco;
import resttaco.domain.TacoDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    private Taco getTaco(@PathVariable Long id) {
        return tacoRepository.findById(id).orElse(new Taco());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void postTaco(@RequestBody TacoDTO dto) {
        List<Ingredient> tacoIngredients = new ArrayList<>();
        dto.getIngredientIds().forEach(x -> tacoIngredients.add(ingredientRepository.findById(x).orElse(null)));
        Taco taco =  dto.convertToTaco(tacoIngredients);
        tacoRepository.save(taco);
    }
}
