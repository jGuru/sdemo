package demoapp.sdemo.exception;

public class ApplicationException extends RuntimeException{
    private String message;

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(){
        
    }
}
