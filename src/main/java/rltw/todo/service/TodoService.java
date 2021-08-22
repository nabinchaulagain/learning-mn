package rltw.todo.service;

import com.google.gson.Gson;
import jakarta.inject.Singleton;
import rltw.todo.error.NotFoundException;
import rltw.todo.model.Todo;
import rltw.todo.util.FileUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class TodoService {
    public static final String DATA_FILE = "./src/main/resources/todos.json";

    public Todo getTodo(long id) throws NotFoundException {
        List<Todo> todos = this.getTodos();

        Optional<Todo> filteredTodoOptional = todos.stream().filter((Todo todo) -> todo.getId() == id).findFirst();
        if(filteredTodoOptional.isEmpty()){
            throw new NotFoundException("Todo");
        }

        return filteredTodoOptional.get();
    }

    public Todo addTodo(Todo todo){
        List<Todo> todos = this.getTodos();
        long newTodoId = 1;

        if(todos.size() > 0){
            newTodoId = todos.get(todos.size()-1).getId() + 1;
        }

        todo.setId(newTodoId);
        todos.add(todo);
        FileUtility.writeToFile(DATA_FILE,new Gson().toJson(todos));

        return todo;
    }

    public Todo editTodo(long id, Todo todo) throws NotFoundException {
        List<Todo> todos = this.getTodos();

        List<Todo> newTodos = todos.stream().map((Todo todoItem)->{
            if(todoItem.getId() == id){
                todoItem.setCompleted(todo.getIsCompleted());
                todoItem.setText(todo.getText());
            }

            return todoItem;
        }).collect(Collectors.toList());

        FileUtility.writeToFile(DATA_FILE,new Gson().toJson(newTodos));

        return this.getTodo(id);
    }

    public void deleteTodo(long id) throws NotFoundException {
        Todo todo = this.getTodo(id);
        List<Todo> todos = this.getTodos();
        List<Todo> newTodos = todos.stream().filter((Todo todoItem)-> todoItem.getId() != id).collect(Collectors.toList());

        FileUtility.writeToFile(DATA_FILE,new Gson().toJson(newTodos));
    }

    public List<Todo> getTodos() {
        String rawContents = FileUtility.readFile(DATA_FILE);

        return new ArrayList<>(Arrays.asList(new Gson().fromJson(rawContents, Todo[].class)));
    }

}
