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

import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Role;
import com.myapp.myapp.model.Staff;
import com.myapp.myapp.model.User;
import com.myapp.myapp.service.UserService;

@RestController
//@RequestMapping("/")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(path = "/users")
  public User saveUser(@RequestBody User user) {
    if (user.getRole().equals(Role.STAFF)) {
      Staff staff = new Staff();
      staff.setName(user.getName());
      staff.setPassword(user.getPassword());
      staff.setPhone(user.getPhone());
      staff.setRole(Role.STAFF);
      staff.setUserName(user.getUserName());
      staff.setBranch(user.getBranch());
      return userService.saveUser(staff);
    }
    else if (user.getRole().equals(Role.BRANCH_HEAD)) {
      BranchHead branchHead = new BranchHead();
      branchHead.setName(user.getName());
      branchHead.setPassword(user.getPassword());
      branchHead.setPhone(user.getPhone());
      branchHead.setRole(Role.BRANCH_HEAD);
      branchHead.setUserName(user.getUserName());
      branchHead.setBranch(user.getBranch());
      return userService.saveUser(branchHead);
    }
    return null;
    
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
    Optional<User> userOptional = userService.findUser(username);
    
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (user.getRole().equals(Role.STAFF)) {
        Staff staff = new Staff();
        staff.setName(u.getName());
        staff.setPassword(u.getPassword());
        staff.setPhone(u.getPhone());
        staff.setRole(Role.STAFF);
        staff.setUserName(u.getUserName());
        
        userService.saveUser(staff);
      }
      else if (user.getRole().equals(Role.BRANCH_HEAD)) {
        BranchHead branchHead = new BranchHead();
        branchHead.setName(u.getName());
        branchHead.setPassword(u.getPassword());
        branchHead.setPhone(u.getPhone());
        branchHead.setRole(Role.BRANCH_HEAD);
        branchHead.setUserName(u.getUserName());
        userService.saveUser(branchHead);
      }
      return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
