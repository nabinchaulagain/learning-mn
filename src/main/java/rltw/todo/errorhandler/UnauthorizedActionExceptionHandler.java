package rltw.todo.errorhandler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import rltw.todo.error.UnauthorizedActionException;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class UnauthorizedActionExceptionHandler implements ExceptionHandler<UnauthorizedActionException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, UnauthorizedActionException exception) {
        Map<String,String> response = new HashMap<>();
        response.put("error","You are not permitted to do this.");

        return HttpResponse.badRequest(response);
    }
}
