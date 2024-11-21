package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Todo {
  @Id
  private Long id;

  private String description;

  public Todo(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  public Todo() {}

  public Long id() {
    return id;
  }

  public String description() {
    return description;
  }
}
