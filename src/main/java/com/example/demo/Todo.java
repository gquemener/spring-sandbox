package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String description;

  public Todo(String description) {
    this.description = description;
  }

  public Long id() {
    return id;
  }

  public String description() {
    return description;
  }
}
