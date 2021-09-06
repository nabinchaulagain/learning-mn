package rltw.todo.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import rltw.todo.error.NotFoundException;
import rltw.todo.error.UnauthorizedActionException;
import rltw.todo.model.Todo;
import rltw.todo.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/todos")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TodoController {
    @Inject
    TodoService todoService;

    @Get("/")
    public List<Todo> getTodos(Authentication authentication){
        return todoService.getTodos((long) authentication.getAttributes().get("userId"));
    }

    @Post("/")
    public Todo addTodo(Authentication authentication,@Body @Valid Todo todoPayload){
        todoPayload.setUserId((long) authentication.getAttributes().get("userId"));

        return todoService.addTodo(todoPayload);
    }

    @Get("/{id}/")
    public Todo getTodo(Authentication authentication, @PathVariable long id) throws NotFoundException, UnauthorizedActionException {
        return todoService.getTodo((long) authentication.getAttributes().get("userId"),id);
    }

    @Delete("/{id}/")
    public String deleteTodo(Authentication authentication, @PathVariable long id) throws NotFoundException,UnauthorizedActionException {
        todoService.deleteTodo((long) authentication.getAttributes().get("userId"),id);

        return "Deleted the todo";
    }

    @Patch("/{id}/")
    public Todo editTodo(Authentication authentication,@PathVariable long id, @Body Todo todo) throws NotFoundException, UnauthorizedActionException {
        return todoService.editTodo((long) authentication.getAttributes().get("userId"),id,todo);
    }
}
