package rltw.todo.controller;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import rltw.todo.model.Todo;
import rltw.todo.service.TodoService;

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
    public Todo addTodo(@Body Todo todoPayload){
        return todoService.addTodo(todoPayload);
    }

    @Get("/{id}/")
    public Todo getTodo(@PathVariable long id){
        return todoService.getTodo(id);
    }

    @Delete("/{id}/")
    public String deleteTodo(@PathVariable long id){
        todoService.deleteTodo(id);

        return "Deleted the todo";
    }

    @Patch("/{id}/")
    public Todo editTodo(@PathVariable long id, @Body Todo todo){
        return todoService.editTodo(id,todo);
    }
}
