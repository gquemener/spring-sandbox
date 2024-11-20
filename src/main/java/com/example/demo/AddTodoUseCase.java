package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AddTodoUseCase {
  private TodoRepository todoRepository;
  private ApplicationEventPublisher eventPublisher;

  public AddTodoUseCase(
      TodoRepository todoRepository,
      ApplicationEventPublisher eventPublisher) {
    this.todoRepository = todoRepository; // (Driven port) for persisting todo
    this.eventPublisher = eventPublisher; // (Driven port) for publishing event
  }

  @Transactional
  public void process(String description) {
    Todo todo = new Todo(description);
    todoRepository.save(todo);
    System.out.println("After todoRepository.save");
    eventPublisher.publishEvent(new TodoCreated(todo.id(), todo.description()));
    System.out.println("After eventPublisher.publishEvent");
  }

  /**
   * WITHOUT @Transactional (and with @EventListener)
   *
   * Hibernate: select next_val as id_val from todo_seq for update
   * Hibernate: update todo_seq set next_val= ? where next_val=?
   * Hibernate: insert into todo (description,id) values (?,?)
   * After todoRepository.save
   * Todo #502: foo
   * After eventPublisher.publishEvent
   */

  /**
   * WITH @Transactional (and @TransactionalEventListener)
   *
   * Hibernate: select next_val as id_val from todo_seq for update
   * Hibernate: update todo_seq set next_val= ? where next_val=?
   * After todoRepository.save
   * After eventPublisher.publishEvent
   * Hibernate: insert into todo (description,id) values (?,?)
   * Todo #552: foo
   */
}
