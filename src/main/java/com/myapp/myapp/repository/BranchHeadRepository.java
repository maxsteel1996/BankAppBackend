package com.myapp.myapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.myapp.model.Branch;
import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Role;

@Repository
public interface BranchHeadRepository extends CrudRepository<BranchHead, String> {

	public BranchHead findByBranch(Branch branch);
	
	public Branch findByUserNameAndRole(String branchHeadUserName, Role role);

}
