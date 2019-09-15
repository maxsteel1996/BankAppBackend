package com.myapp.myapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.model.User;
import com.myapp.myapp.service.UserService;

@RestController
//@RequestMapping("/")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(path = "/users")
  public User saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @GetMapping(path = "/users")
  public List<User> findUsers() {
    return userService.findUsers();
  }
  
  @GetMapping(path = "/users/{username}")
  public ResponseEntity<User> findUser(@PathVariable(value="username") String username) {
    Optional<User> user = userService.findUser(username);
    if (user.isPresent())
      return ResponseEntity.status(HttpStatus.OK).body(user.get());
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  @DeleteMapping(path = "/users/{username}")
  public ResponseEntity<String> deleteUser(@PathVariable(value="username") String username) {
    Optional<User> user = userService.findUser(username);
    if (user.isPresent()) {
      userService.deleteUser(username);
      return ResponseEntity.ok().body("User deleted").status(HttpStatus.OK).build();
    }
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    
  }
  
  @PutMapping(path = "/users/{username}")
  public ResponseEntity<User> updateUser(@PathVariable(value="username") String username, @RequestBody User u){
    Optional<User> user = userService.findUser(username);
    if (user.isPresent()) {
      userService.saveUser(user.get());
      return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
