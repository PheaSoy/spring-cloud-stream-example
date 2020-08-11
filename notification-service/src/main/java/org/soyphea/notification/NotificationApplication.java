package org.soyphea.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class NotificationApplication {

  @Autowired
  private ObjectMapper objectMapper;

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }
}