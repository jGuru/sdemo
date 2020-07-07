package demoapp.sdemo.controller;

import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Ingredients;
import demoapp.sdemo.model.Recipe;
import demoapp.sdemo.service.IngredientsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    /**
     * Constructor to inject IngredientsService
     * @param ingredientsService
     */
    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    /**
     * Gets the Ingredient based on id
     * @return Ingredient
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredients> getIngredientsById(@PathVariable Integer id){
        return ingredientsService.getIngredients(id);
    }

    /**
     * Gets all the Ingredients
     * @return List<Ingredients></Ingredients>
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Ingredients>> getAll(){
        return ingredientsService.getAll();
    }

    /**
     * API which create the new Ingredients
     * @param ingredients The Ingredients payload
     * @return Ingredients Response
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients ingredients ){
        ingredients.setIngId(null);
       return ingredientsService.addIngredients(ingredients);
    }

    /**
     * API which updates a Ingredients
     * @param  ingredients The Ingredients payload
     * @return Ingredients Response
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> updateIngredients(@RequestBody Ingredients ingredients){
        return ingredientsService.updateIngredient(ingredients);
    }

    /**
     * API which deletes a Ingredients
     *
     * @param id The id
     * @return GenericResponse
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> deleteIngredientsById(@PathVariable  Integer id){
        return ingredientsService.deleteIngredients(id);
    }
}
