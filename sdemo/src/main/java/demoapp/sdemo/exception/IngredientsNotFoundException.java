package demoapp.sdemo.exception;

public class IngredientsNotFoundException extends RuntimeException {
    private String message;

    public IngredientsNotFoundException(String message){
        super(message);
    }

    public IngredientsNotFoundException(){
    }
}
