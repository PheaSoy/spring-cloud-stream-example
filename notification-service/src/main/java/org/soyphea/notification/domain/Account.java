package org.soyphea.notification.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDate;

public class Account implements BaseDomain{

  private String name;

  @JsonProperty("account_number")
  private String accountNumber;


  @JsonProperty("created_date")
  private LocalDate createDate;

  public Account() {
  }

  public Account(String name, String accountNumber, LocalDate createDate) {
    this(name,accountNumber);
    this.createDate = createDate;
  }

  public Account(String name, String accountNumber) {
    this.name = name;
    this.accountNumber = accountNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDate createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "Account{" +
        "name='" + name + '\'' +
        ", accountNumber='" + accountNumber + '\'' +
        ", createDate=" + createDate +
        '}';
  }
}
