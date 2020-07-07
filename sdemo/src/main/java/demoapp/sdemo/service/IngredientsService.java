package demoapp.sdemo.service;

import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Ingredients;
import demoapp.sdemo.repository.IngredientsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    /**
     * Constructor to inject IngredientsRepository
     *
     * @param ingredientsRepository The ingredients repository
     */
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    /**
     * Adds a new Ingredient
     *
     * @param ingredients The Ingredient
     */

    public ResponseEntity<Ingredients> addIngredients(Ingredients ingredients) {
        Ingredients savedIngredients = ingredientsRepository.save(ingredients);
        return new ResponseEntity<>(savedIngredients, HttpStatus.OK);
    }

    /**
     * Deletes a Ingredient
     * @param id The id
     */

    public ResponseEntity<GenericResponse>deleteIngredients(Integer id)
    {
        ingredientsRepository.deleteById(id);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    /**
     * Updates the Ingredients
     *
     * @param ingredient The Ingredient
     * @return GenericResponse
     */

    public ResponseEntity<GenericResponse> updateIngredient(Ingredients ingredient) {
        ingredientsRepository.save(ingredient);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    /**
     * Gets the Ingredient
     *
     * @param id The id
     * @return Ingredients
     */
    public ResponseEntity<Ingredients> getIngredients(Integer id) {
        Optional<Ingredients> ingredient = ingredientsRepository.findById(id);
        return new ResponseEntity<>(ingredient.get(), HttpStatus.OK);
    }

    /**
     * Gets all the Ingredients
     * @return List<Ingredients></Ingredients>
     */
    public ResponseEntity<List<Ingredients>>getAll(){
        List<Ingredients> all= ingredientsRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
