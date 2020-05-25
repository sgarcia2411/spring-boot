package com.example.demo.db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User addNewUser(String name, String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return n;
  }

  public User get(Integer id) {
    Optional<User> user = this.userRepository.findById(id);
    return user.get();
  }

  public Iterable<User> findAll() {
    return this.userRepository.findAll();
  }
}
