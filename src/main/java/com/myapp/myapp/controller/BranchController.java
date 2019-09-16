package com.myapp.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.model.Branch;
import com.myapp.myapp.service.BranchService;

@RestController
@RequestMapping("/branch_head")
public class BranchController {

  @Autowired
  BranchService branchService;

  @GetMapping(path = "/branches")
  public List<Branch> findBranches() {
    return branchService.getAllBranches();
  }
}
