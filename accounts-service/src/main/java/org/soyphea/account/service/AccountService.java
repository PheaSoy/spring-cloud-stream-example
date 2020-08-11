package org.soyphea.account.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soyphea.account.domain.Account;
import org.soyphea.account.exception.AccountException;
import org.soyphea.account.output.AccountBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final Logger logger = LoggerFactory.getLogger(AccountService.class);

  private List<Account> accounts = new ArrayList<>();

  @Autowired
  AccountBinder accountBinder;


  public Account add(Account account) {
    long count = accounts.stream()
        .filter(acc -> acc.getAccountNumber().equals(account.getAccountNumber()))
        .count();
    logger.info("Found [{}] with account number:{}", count, account.getAccountNumber());
    if (count > 0) {
      throw new AccountException("Account :" + account.getAccountNumber() + " already existed");
    }
    Account registerAccount = new Account(account.getName(), account.getAccountNumber());
    registerAccount.setCreateDate(LocalDate.now());
    accounts.add(registerAccount);
    accountBinder.send(registerAccount);
    return registerAccount;
  }
}
