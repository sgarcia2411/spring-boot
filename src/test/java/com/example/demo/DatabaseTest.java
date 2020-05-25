package com.example.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.db.User;
import com.example.demo.db.UserController;
import com.example.demo.db.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
class DatabaseTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService service;

  @Test
  public void getUserShouldReturnMessageFromService() throws Exception {
    User mockedUser= new User(1, "test", "test@test.com");

    Mockito.when(service.get(1))
      .thenReturn(mockedUser);

    this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().string(containsString("{\"id\":1,\"name\":\"test\",\"email\":\"test@test.com\"}")));
  }

  @Test
  public void getAllUserShouldReturnMessageFromService() throws Exception {
    User mockedUser1 = new User(1, "test1", "test1@test.com");
    User mockedUser2 = new User(2, "test2", "test2@test.com");
    User mockedUser3 = new User(3, "test3", "test3@test.com");

    List<User> list = new ArrayList<User>();
    list.add(mockedUser1);
    list.add(mockedUser2);
    list.add(mockedUser3);


    Mockito.when(service.findAll())
      .thenReturn(list);

    this.mockMvc.perform(get("/user/all")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"test1\",\"email\":\"test1@test.com\"},{\"id\":2,\"name\":\"test2\",\"email\":\"test2@test.com\"},{\"id\":3,\"name\":\"test3\",\"email\":\"test3@test.com\"}]")));
  }

}
