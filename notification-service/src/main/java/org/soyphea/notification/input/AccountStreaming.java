package org.soyphea.notification.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soyphea.notification.domain.Account;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
public class AccountStreaming {

  static Class clazz = AccountStreaming.class;
  static Logger logger = LoggerFactory.getLogger(clazz.getName());

  @StreamListener(Sink.INPUT)
  public void receivedAccount(Account account) {
    logger.info("Received account :{} created.", account);
  }
}
