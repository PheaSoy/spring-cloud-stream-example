package org.soyphea.account.output;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class AccountBinder<T> {

  Object target;
  Logger logger = LoggerFactory.getLogger(this.getClass());
  final private Processor processor;

  public AccountBinder(Processor processor) {
    this.processor = processor;
  }

  public void send(T data) {
    logger.info("Send message => {} to the channel",data);
    Message message = MessageBuilder
        .withPayload(data)
        .setHeaderIfAbsent("operation","created")
        .build();
    processor.output()
        .send(message);
  }
}
