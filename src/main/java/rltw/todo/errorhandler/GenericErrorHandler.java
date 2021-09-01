package rltw.todo.errorhandler;

import io.micronaut.http.HttpRequest;
import  io.micronaut.http.server.exceptions.ExceptionHandler;
import  io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class GenericErrorHandler implements ExceptionHandler<Exception,HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, Exception exception)  {
        Map<String,Object> response = new HashMap<>();
        response.put("error",exception.getMessage());
        response.put("stackTrace",this.getExceptionStackTrace(exception));

        exception.printStackTrace();

        return HttpResponse.serverError(response);
    }

    public String getExceptionStackTrace(Exception ex){
        return Arrays.stream(ex.getStackTrace()).map((StackTraceElement el)-> el.toString()).collect(Collectors.joining("\n"));
    }
}
