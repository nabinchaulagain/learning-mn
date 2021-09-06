package rltw.todo.errorhandler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import rltw.todo.error.AuthException;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class AuthExceptionHandler implements ExceptionHandler<AuthException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, AuthException exception) {
        Map<String,String> response = new HashMap<>();
        response.put("error",exception.getMessage());

        return HttpResponse.badRequest(response);
    }
}
