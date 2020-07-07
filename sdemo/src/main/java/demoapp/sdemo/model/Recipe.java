package demoapp.sdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neeraj
 * 
 * This class represent the domain object Recipe
 * This is technically an Entity class and used serialize and de-serialize the Recipe object
 */
@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RECIPE_ID")
    private Integer recipeId;
    @Column(name = "CREATION_DATE")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date creationDate;
    @Column(name = "SUITABLE_FOR")
    private Integer suitableFor;
    @Column(name = "VEG")
    private boolean veg;
    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @JsonManagedReference
    @OneToMany(mappedBy = "recipeId", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Ingredients> ingredientsList;


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }



    public Recipe() {
    }

    public Recipe(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(Integer suitableFor) {
        this.suitableFor = suitableFor;
    }

    public boolean getVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }


    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeId != null ? recipeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.recipeId == null && other.recipeId != null) || (this.recipeId != null && !this.recipeId.equals(other.recipeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ recipeId=" + recipeId + " ]";
    }
    
}
