package demoapp.sdemo.controller;

import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Recipe;
import demoapp.sdemo.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    /**
     * Constructor to inject RecipeService
     * @param recipeService
     */
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Gets the Recipe based on id
     * @return Recipe
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id){
        return recipeService.getRecipe(id);
    }

    /**
     * Gets all the Recipe's
     * @return List<Recipe></Recipe>
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        return recipeService.getAll();
    }

    /**
     * API which create the new Recipe
     * @param recipe The Recipe payload
     * @return Recipe Response
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
        recipe.setRecipeId(null);
        return recipeService.addRecipe(recipe);
    }

    /**
     * API which updates a Recipe
     * @param  recipe The Recipe payload
     * @return Recipe Response
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> updateRecipe(@RequestBody Recipe recipe){
        return recipeService.updateRecipe(recipe);
    }

    /**
     * API which deletes a Recipe
     *
     * @param id The id
     * @return GenericResponse
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> deleteRecipeByRecipeId(@PathVariable Integer id)
    {
        return recipeService.deleteRecipe(id);
    }


}
