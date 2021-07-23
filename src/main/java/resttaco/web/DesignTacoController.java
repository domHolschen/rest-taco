package resttaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
    private EntityModel<Taco> getTacoById(@PathVariable Long id) {
        Taco taco = tacoRepository.findById(id).orElse(null);
        // http://localhost:8080/tacos/1?order
        return EntityModel.of(
                taco,
                linkTo(methodOn(DesignTacoController.class).getTacoById(id)).withSelfRel(),
                linkTo(methodOn(DesignTacoController.class).allTacos()).withRel("design")
        );
    }

    @GetMapping
    @ResponseBody
    public Iterable<Taco> allTacos() {
        return tacoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private Taco postTaco(@RequestBody TacoDTO dto) {
        // declare list to hold ingredients
        List<Ingredient> tacoIngredients = new ArrayList<>();
        // take the ids from the ingredients and search the ingredients table by id, add the found ingredient objects to the list
        dto.getIngredientIds().forEach(x -> tacoIngredients.add(ingredientRepository.findById(x).orElse(null)));
        // save that new taco object in the SQL table
        Taco taco =  dto.convertToTaco(tacoIngredients);
        // return the taco to the front end
        tacoRepository.save(taco);
        return taco;
    }
}
