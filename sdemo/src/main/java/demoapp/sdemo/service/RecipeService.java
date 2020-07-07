package demoapp.sdemo.service;

import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Recipe;
import demoapp.sdemo.repository.IngredientsRepository;
import demoapp.sdemo.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class to manage Recipe
 *
 * @author Neeraj
 */
@Service
public class RecipeService {

    final RecipeRepository recipeRepository;
    final IngredientsRepository ingredientsRepository;

    /**
     * Constructor to inject RecipeRepository
     *
     * @param recipeRepository The recipe repository
     * @param ingredientsRepository
     */
    public RecipeService(RecipeRepository recipeRepository, IngredientsRepository ingredientsRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    /**
     * Adds a new Recipe
     *
     * @param recipe The Recipe
     */
    public ResponseEntity<Recipe> addRecipe(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    /**
     * Deletes the Recipe
     *
     * @param id The id
     */

    public ResponseEntity<GenericResponse> deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
    }

    /**
     * Updates the Recipe
     *
     * @param recipe The Recipe
     * @return GenericResponse
     */
    public ResponseEntity<GenericResponse> updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    /**
     * Gets the Recipe
     *
     * @param id The id
     * @return Recipe
     */

    public ResponseEntity<Recipe> getRecipe(Integer id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    }
    /**
     * Gets all the recipes
     * @return List<Recipe></Recipe>
     * */
    public ResponseEntity<List<Recipe>> getAll() {
        List<Recipe> all =  recipeRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

}
