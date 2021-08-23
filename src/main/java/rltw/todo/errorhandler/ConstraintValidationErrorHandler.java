package rltw.todo.errorhandler;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.validation.exceptions.ConstraintExceptionHandler;
import jakarta.inject.Singleton;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Replaces(ConstraintExceptionHandler.class)
public class ConstraintValidationErrorHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        if (!exception.getConstraintViolations().isEmpty()) {
            for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
                String fieldName = null;
                for (Node node : constraintViolation.getPropertyPath()) {
                    fieldName = node.getName();
                }
                errors.put(fieldName, constraintViolation.getMessage());
            }
        }

        Map<String,Object> response = new HashMap<>();
        response.put("fieldErrors",errors);
        response.put("message","Invalid payload");
        return HttpResponse.badRequest(response);
    }
}
