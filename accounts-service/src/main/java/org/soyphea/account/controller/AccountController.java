package org.soyphea.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soyphea.account.domain.Account;
import org.soyphea.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/accounts")
  public ResponseEntity<Account> registerAccount( @RequestBody Account account) {
    logger.info("Received the request with account name:{}",account.getName());
      return  ResponseEntity.ok(accountService.add(account));
  }

}
