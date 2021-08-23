package rltw.todo.model;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Introspected
public class Todo {
    private long id;

    @NotEmpty(message = "Todo text is required")
    private String text;

    private boolean isCompleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
