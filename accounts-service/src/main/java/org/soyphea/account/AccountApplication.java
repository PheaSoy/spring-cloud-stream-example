package org.soyphea.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class,args);
  }


}
