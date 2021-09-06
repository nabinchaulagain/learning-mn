package rltw.todo.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.todo.error.NotFoundException;
import rltw.todo.error.UnauthorizedActionException;
import rltw.todo.model.Todo;
import rltw.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;


@Singleton
public class TodoService {
    @Inject
    TodoRepository todoRepository;

    public Todo getTodo(long userId, long id) throws NotFoundException,UnauthorizedActionException {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if(todoOptional.isEmpty()){
            throw new NotFoundException("Todo");
        }

        if(todoOptional.get().getUserId() != userId){
            throw new UnauthorizedActionException();
        }

        return todoOptional.get();
    }

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo editTodo(long userId, long id, Todo todoPayload) throws NotFoundException,UnauthorizedActionException {
        Todo todoOriginal = this.getTodo(userId, id);


        todoOriginal.setText(todoPayload.getText());
        todoOriginal.setCompleted(todoPayload.getIsCompleted());

        return todoRepository.update(todoOriginal);
    }

    public void deleteTodo(long userId,long id) throws NotFoundException,UnauthorizedActionException {
        Todo todo = this.getTodo(userId, id);

        todoRepository.delete(todo);
    }

    public List<Todo> getTodos(long userId) {
        return todoRepository.findByUserIdOrderByIdDesc(userId);
    }

}
