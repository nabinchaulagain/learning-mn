package rltw.todo.controller;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import rltw.todo.error.NotFoundException;
import rltw.todo.model.Todo;
import rltw.todo.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/todos")
public class TodoController {
    @Inject
    TodoService todoService;

    @Get("/")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @Post("/")
    public Todo addTodo(@Body @Valid Todo todoPayload){
        return todoService.addTodo(todoPayload);
    }

    @Get("/{id}/")
    public Todo getTodo(@PathVariable long id) throws NotFoundException {
        return todoService.getTodo(id);
    }

    @Delete("/{id}/")
    public String deleteTodo(@PathVariable long id) throws NotFoundException {
        todoService.deleteTodo(id);

        return "Deleted the todo";
    }

    @Patch("/{id}/")
    public Todo editTodo(@PathVariable long id, @Body Todo todo) throws NotFoundException {
        return todoService.editTodo(id,todo);
    }
}
