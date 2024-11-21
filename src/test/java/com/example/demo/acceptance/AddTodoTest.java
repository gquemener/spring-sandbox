package com.example.demo.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.AddTodoUseCase;
import com.example.demo.Todo;
import com.example.demo.TodoCreated;
import com.example.demo.ports.driven.ForPersistingTodo;
import com.example.demo.ports.driven.ForPublishingDomainEvent;

public class AddTodoTest implements ForPersistingTodo, ForPublishingDomainEvent {
  private ArrayList<Todo> savedTodos = new ArrayList<>();
  private ArrayList<Object> publishedEvents = new ArrayList<>();

  @Test
  void successfully_add_todo() {
    ForPersistingTodo forPersistingTodo = this;
    ForPublishingDomainEvent forPublishingDomainEvent = this;

    AddTodoUseCase subject = new AddTodoUseCase(forPersistingTodo, forPublishingDomainEvent);
    Long id = 1L;
    String description = "Refactor!";
    subject.process(id, description);

    assertThat(savedTodos).hasSize(1);
    assertThat(savedTodos.get(0).id()).isEqualTo(id);
    assertThat(savedTodos.get(0).description()).isEqualTo(description);

    assertThat(publishedEvents).hasSize(1);
    assertThat(publishedEvents.get(0)).isEqualTo(new TodoCreated(id, description));
  }

  @Override
  public void save(Todo todo) {
    this.savedTodos.add(todo);
  }

  @Override
  public void publish(Object event) {
    this.publishedEvents.add(event);
  }
}
