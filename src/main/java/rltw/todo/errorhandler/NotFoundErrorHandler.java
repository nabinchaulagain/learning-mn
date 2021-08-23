package rltw.todo.errorhandler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import rltw.todo.error.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class NotFoundErrorHandler implements ExceptionHandler<NotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, NotFoundException exception) {
        Map<String,String> response = new HashMap<>();
        response.put("message",exception.getEntityName() + " not found.");

        return HttpResponse.badRequest(response);
    }
}
