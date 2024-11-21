package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class PrintNewTodoToTerminalPolicy {
  @TransactionalEventListener
  public void onTodoCreated(TodoCreated event) {
    System.out.println(event);
  }
}
