package zen8.hieule.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle request data
     *
     * @param e Exception
     * @param request WebRequest
     * @return error
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "500 Response",
                                    summary = "Handle request data",
                                    value = """
                                            {
                                              "timestamp": "2023-10-19T06:26:34.388+00:00",
                                              "status": 500,
                                              "path": "/*",
                                              "error": "Internal Server Error",
                                              "messages": "Request failed"
                                            }"""
                            ))})
    })
    public Error handleValidationException(Exception e, WebRequest request) {
//        Error error = new Error();
//        error.setTimestamp(new Date());
//        error.setStatus(BAD_REQUEST.value());
//        error.setPath(request.getDescription(false).replace("uri=", ""));
//
//        String message = e.getMessage();
//        if (e instanceof MethodArgumentNotValidException) {
//            int start = message.lastIndexOf("[");
//            int end = message.lastIndexOf("]");
//            message = message.substring(start + 1, end - 1);
//            error.setError("Body is invalid");
//            error.setMessages(message);
//        } else if (e instanceof MissingServletRequestParameterException) {
//            error.setError("Parameter is invalid");
//            error.setMessages(message);
//        } else if (e instanceof ConstraintViolationException) {
//            error.setError("Parameter is invalid");
//            error.setMessages(message.substring(message.indexOf(" ") + 1));
//        } else {
//            error.setError("Data is invalid");
//            error.setMessages(message);
//        }

        return null;
    }

}

