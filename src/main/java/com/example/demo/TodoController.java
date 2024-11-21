package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class TodoController {
  private AddTodoUseCase addTodoUseCase;

  public TodoController(AddTodoUseCase addTodoUseCase) {
    this.addTodoUseCase = addTodoUseCase;
  }

  @PostMapping("/todos")
  public @ResponseBody String createTodo(@RequestParam String id, @RequestParam String description) {
    addTodoUseCase.process(Long.valueOf(id), description);
    return "OK";
  }
}
