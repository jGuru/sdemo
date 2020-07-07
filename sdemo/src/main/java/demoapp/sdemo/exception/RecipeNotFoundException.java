package demoapp.sdemo.exception;

public class RecipeNotFoundException  extends RuntimeException{
    private String message;

    public RecipeNotFoundException(String message){
        super(message);
    }

    public RecipeNotFoundException(){
    }
}
