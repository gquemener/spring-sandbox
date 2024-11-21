package com.example.demo;

import org.springframework.stereotype.Service;

import com.example.demo.ports.driven.ForPersistingTodo;

@Service
public class TodoRepositoryUsingSpringData implements ForPersistingTodo {

  private TodoRepository jpaRepository;

  public TodoRepositoryUsingSpringData(TodoRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public void save(Todo todo) {
    jpaRepository.save(todo);
  }

}
