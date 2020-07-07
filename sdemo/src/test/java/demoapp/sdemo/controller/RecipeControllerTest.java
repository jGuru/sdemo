package demoapp.sdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import demoapp.sdemo.model.GenericResponse;
import demoapp.sdemo.model.Ingredients;
import demoapp.sdemo.model.Recipe;
import demoapp.sdemo.security.SpringSecurityWebAuxTestConfig;
import demoapp.sdemo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = SpringSecurityWebAuxTestConfig.class)
class RecipeControllerTest {

    @Autowired
    private RecipeController recipeController;

    @MockBean
    private RecipeService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test

    @WithUserDetails("foo")
    void contextLoads() {
        assertThat(recipeController).isNotNull();
    }

    @Test
    @WithUserDetails("foo")
    void getRecipeById() throws Exception {
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
        mockMvc.perform(get("/v1/recipe/1")).andExpect(status().isOk());
        when(service.getRecipe(any(Integer.class))).thenReturn(new ResponseEntity<>(recipe, HttpStatus.OK));
        verify(service).getRecipe(1);
    }

    @Test
    @WithMockUser("foo")
    void getAllRecipe() throws Exception {
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
        List<Recipe> recipeList=new ArrayList<>();
        recipeList.add(recipe);
        ResponseEntity<List<Recipe>> responseEntity=new ResponseEntity<>(recipeList,HttpStatus.OK);
        when(service.getAll()).thenReturn(responseEntity);
        mockMvc.perform(get("/v1/recipe/all")).andExpect(status().isOk());
        verify(service).getAll();

    }

    @Test
    @WithMockUser("foo")
    void addRecipe() throws Exception {
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
        when(service.addRecipe(recipe)).thenReturn(new ResponseEntity<>(recipe, HttpStatus.CREATED));
        mockMvc.perform(
                post("/v1/recipe/").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(recipe)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

    }

    @Test
    @WithMockUser("foo")
    void updateRecipe() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setCreationDate(new Date());
        recipe.setInstructions("New Test Instruction");
        recipe.setSuitableFor(5);
        recipe.setVeg(true);
        Ingredients i1=new Ingredients();
        i1.setItem("Onion");
        i1.setRecipeId(recipe);
        List<Ingredients> list=new ArrayList<>();
        list.add(i1);
        recipe.setIngredientsList(list);
        when(service.addRecipe(recipe)).thenReturn(new ResponseEntity<>(recipe, HttpStatus.OK));
        mockMvc.perform(
                put("/v1/recipe/").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(recipe)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @WithMockUser("foo")
    void deleteRecipeByRecipeId() throws Exception {
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
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        ResponseEntity<GenericResponse> responseEntity = new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
        when(service.deleteRecipe(any(Integer.class))).thenReturn(responseEntity);
        mockMvc.perform(
                delete("/v1/recipe/{id}",1)).andExpect(status().isAccepted());
    }
}