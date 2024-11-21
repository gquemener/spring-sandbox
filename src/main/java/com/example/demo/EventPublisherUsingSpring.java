package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.demo.ports.driven.ForPublishingDomainEvent;

@Service
public class EventPublisherUsingSpring implements ForPublishingDomainEvent {

  private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherUsingSpring(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
  public void publish(Object event) {
    applicationEventPublisher.publishEvent(event);
  }
}