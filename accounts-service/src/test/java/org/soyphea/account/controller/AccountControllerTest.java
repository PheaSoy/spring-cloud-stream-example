package org.soyphea.account.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.soyphea.account.domain.Account;
import org.soyphea.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith({SpringExtension.class})
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;
  final ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  AccountService accountService;

  Account testAccount = new Account("John", "123-0999");


  @Test
  @DisplayName("Test Create account should be ok")
  void test() throws Exception {
    String payload = objectMapper.writeValueAsString(testAccount);
    when(accountService.add(any())).thenReturn(testAccount);
    mockMvc.perform(post("/accounts")
        .content(payload)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.name", is("John")))
        .andDo(print());
  }

}
