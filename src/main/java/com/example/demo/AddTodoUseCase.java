package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.demo.ports.driven.ForPersistingTodo;
import com.example.demo.ports.driven.ForPublishingDomainEvent;

import jakarta.transaction.Transactional;

@Service
public class AddTodoUseCase {
  private ForPersistingTodo forPersistingTodo;
  private ForPublishingDomainEvent forPublishingDomainEvent;

  public AddTodoUseCase(
      ForPersistingTodo forPersistingTodo,
      ForPublishingDomainEvent forPublishingDomainEvent) {
    this.forPersistingTodo = forPersistingTodo;
    this.forPublishingDomainEvent = forPublishingDomainEvent;
  }

  @Transactional
  public void process(Long id, String description) {
    Todo todo = new Todo(id, description);
    forPersistingTodo.save(todo);
    forPublishingDomainEvent.publish(new TodoCreated(id, description));
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
