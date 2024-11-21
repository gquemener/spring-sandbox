package com.example.demo.ports.driven;

public interface ForPublishingDomainEvent {
  public void publish(Object event);
}
