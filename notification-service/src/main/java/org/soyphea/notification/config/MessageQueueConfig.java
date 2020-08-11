package org.soyphea.notification.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

  @Bean
  public MessageConverter messageConverter() {
    ObjectMapper mapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
        DateTimeFormatter.ISO_DATE_TIME));
    mapper.registerModule(javaTimeModule);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(
        mapper);
    ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter();
    messageConverter.addDelegate("application/json", jackson2JsonMessageConverter);
    return messageConverter;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setMessageConverter(messageConverter());
    return factory;
  }

}
