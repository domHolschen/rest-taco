package resttaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import resttaco.data.IngredientRepository;
import resttaco.data.TacoRepository;
import resttaco.domain.Ingredient;
import resttaco.domain.Taco;
import resttaco.domain.TacoDTO;

import javax.validation.Valid;
import java.lang.reflect.Array;
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
    @ResponseBody
    private Taco getTaco(@PathVariable Long id) {
        return tacoRepository.findById(id).orElse(new Taco());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private void postTaco(@RequestBody TacoDTO dto) {
        // declare list to hold ingredients
        List<Ingredient> tacoIngredients = new ArrayList<>();
        // take the ids from the ingredients and search the ingredients table by id, add the found ingredient objects to the list
        dto.getIngredientIds().forEach(x -> tacoIngredients.add(ingredientRepository.findById(x).orElse(null)));
        // save that new taco object in the SQL table
        Taco taco =  dto.convertToTaco(tacoIngredients);
        // return the taco to the front end
        tacoRepository.save(taco);
    }
}
