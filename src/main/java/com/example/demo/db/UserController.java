package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(path="/add")
  public @ResponseBody User addNewUser (@RequestParam String name, @RequestParam String email) {
    User user = this.userService.addNewUser(name, email);
    return user;
  }

  @GetMapping(path="/{id}")
  public @ResponseBody User get(@PathVariable Integer id) {
    return this.userService.get(id);
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return this.userService.findAll();
  }
}
