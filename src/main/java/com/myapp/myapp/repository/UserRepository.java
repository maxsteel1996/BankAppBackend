package com.myapp.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.myapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  
}
