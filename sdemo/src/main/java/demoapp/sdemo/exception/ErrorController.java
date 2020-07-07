package demoapp.sdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController {

    private Log logger = LogFactory.getLog(ErrorController.class);
    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recipe Not found")
    public void handleRecipeNotFoundException(HttpServletRequest request, HttpServletResponse response, RecipeNotFoundException ex)
    {
        logger.error("Request "+request.getRequestURL()+" Raised "+ex.getMessage());
    }

    @ExceptionHandler(IngredientsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ingredients Not found")
    public void handleIngredientsNotFoundException(HttpServletRequest request, HttpServletResponse response, IngredientsNotFoundException ex)
    {
        logger.error("Request "+request.getRequestURL()+" Raised "+ex.getMessage());
    }
    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Application Error or Bad request")
    public void handleApplicationException(HttpServletRequest request, HttpServletResponse response, ApplicationException ex){
        logger.error("Request "+request.getRequestURL()+" Raised "+ex.getMessage());
    }

}
