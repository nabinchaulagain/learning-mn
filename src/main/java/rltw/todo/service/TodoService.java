package rltw.todo.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.todo.error.NotFoundException;
import rltw.todo.model.Todo;
import rltw.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;


@Singleton
public class TodoService {
    @Inject
    TodoRepository todoRepository;

    public Todo getTodo(long id) throws NotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if(todoOptional.isEmpty()){
            throw new NotFoundException("Todo");
        }

        return todoOptional.get();
    }

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo editTodo(long id, Todo todoPayload) throws NotFoundException {
        Todo todoOriginal = this.getTodo(id);

        todoOriginal.setText(todoPayload.getText());
        todoOriginal.setCompleted(todoPayload.getIsCompleted());

        return todoRepository.update(todoOriginal);
    }

    public void deleteTodo(long id) throws NotFoundException {
        Todo todo = this.getTodo(id);

        todoRepository.delete(todo);
    }

    public List<Todo> getTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

}
