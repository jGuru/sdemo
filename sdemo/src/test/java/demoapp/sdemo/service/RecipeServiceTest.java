package demoapp.sdemo.service;

import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Ingredients;
import demoapp.sdemo.model.Recipe;
import demoapp.sdemo.security.JwtUtil;
import demoapp.sdemo.security.MyUserDetailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RecipeServiceTest {
    @MockBean
    private RecipeService service;
    private static String token;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private  JwtUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        final UserDetails userDetails=userDetailsService.loadUserByUsername("foo");
        token=jwtTokenUtil.generateToken(userDetails);
        assertThat(token).isNotNull();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addRecipe() {
        Recipe recipe=new Recipe();
        recipe.setCreationDate(new Date());
        recipe.setInstructions("Test Instruction");
        recipe.setSuitableFor(5);
        recipe.setVeg(true);
        Ingredients i1=new Ingredients();
        i1.setItem("Onion");
        i1.setRecipeId(recipe);
        List<Ingredients> list=new ArrayList<>();
        list.add(i1);
        recipe.setIngredientsList(list);
        when(service.addRecipe(any(Recipe.class))).thenReturn(new ResponseEntity<>(recipe, HttpStatus.CREATED));
        ResponseEntity<Recipe> responseEntity=service.addRecipe(recipe);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void deleteRecipe() {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        when(service.deleteRecipe(any(Integer.class))).thenReturn(new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED));
        ResponseEntity<GenericResponse> responseEntity = service.deleteRecipe(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();
    }

    @Test
    void updateRecipe() {
        Recipe recipe=new Recipe();
        recipe.setCreationDate(new Date());
        recipe.setInstructions("Test Instruction");
        recipe.setSuitableFor(5);
        recipe.setVeg(true);
        Ingredients i1=new Ingredients();
        i1.setItem("Spring Onion");
        i1.setRecipeId(recipe);
        List<Ingredients> list=new ArrayList<>();
        list.add(i1);
        recipe.setIngredientsList(list);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        when(service.updateRecipe(any(Recipe.class))).thenReturn(new ResponseEntity<>(genericResponse, HttpStatus.OK));
        ResponseEntity<GenericResponse> responseEntity =service.updateRecipe(recipe);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getRecipe() {
        Recipe recipe=new Recipe();
        recipe.setCreationDate(new Date());
        recipe.setInstructions("Test Instruction");
        recipe.setSuitableFor(5);
        recipe.setVeg(true);
        Ingredients i1=new Ingredients();
        i1.setItem("Spring Onion");
        i1.setRecipeId(recipe);
        List<Ingredients> list=new ArrayList<>();
        list.add(i1);
        when(service.getRecipe(any(Integer.class))).thenReturn(new ResponseEntity<Recipe>(recipe, HttpStatus.OK));
        ResponseEntity<Recipe> responseEntity=service.getRecipe(1);
        assertThat(responseEntity.getBody().getInstructions()).isEqualTo("Test Instruction");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAll() {
        Recipe recipe=new Recipe();
        recipe.setCreationDate(new Date());
        recipe.setInstructions("Test Instruction");
        recipe.setSuitableFor(5);
        recipe.setVeg(true);
        Ingredients i1=new Ingredients();
        i1.setItem("Spring Onion");
        i1.setRecipeId(recipe);
        List<Ingredients> list=new ArrayList<>();
        list.add(i1);
        List<Recipe> recipeList=new ArrayList<>();
        recipeList.add(recipe);
        ResponseEntity<List<Recipe>> responseEntity=new ResponseEntity<>(recipeList,HttpStatus.OK);
        when(service.getAll()).thenReturn(responseEntity);
        ResponseEntity<List<Recipe>> result=service.getAll();
        assertThat(responseEntity.getBody().size()>0);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}