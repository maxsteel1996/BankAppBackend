package com.myapp.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.myapp.model.User;
import com.myapp.myapp.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
  
  public User saveUser(User user) {
    return userRepository.save(user);
  }
    
  public List<User> findUsers() {
    return (List<User>) userRepository.findAll();
  }
  
  public Optional<User> findUser(String username) {
    return userRepository.findById(username);
  }
  
  public void deleteUser(String username) {
    userRepository.deleteById(username);
  }
}
