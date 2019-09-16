package com.myapp.myapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.myapp.model.Appointment;
import com.myapp.myapp.model.Branch;
import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Role;
import com.myapp.myapp.model.Staff;
import com.myapp.myapp.repository.AppointmentRepository;
import com.myapp.myapp.repository.BranchHeadRepository;
import com.myapp.myapp.repository.StaffRepository;

@Service
public class BranchHeadService {

	@Autowired
	AppointmentRepository appointmentRepo;

	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
  BranchHeadRepository branchHeadRepository;
	
	@Autowired
	StaffService staffService;
  
  public BranchHead getBranchHead(Branch branch) {
    return branchHeadRepository.findByBranch(branch);
  }
  
  public Branch findBranchbyBranchHeadUserName(String branchHeadUserName) {
    return branchHeadRepository.findByUserNameAndRole(branchHeadUserName, Role.BRANCH_HEAD);
  }

	public List<Appointment> getAllBranchAppointmentsForToday(Branch branch) {
		return appointmentRepo.findByBranchAndDate(branch, new Date());
	}
	
	public List<Staff> findStaffByBranch(Branch branch){
	  return staffService.findStaffByBranch(branch);
	}

	public void changeAppointmentStaff(Long appointmentId, String username) throws Exception {

		Optional<Appointment> appointmentOpt = appointmentRepo.findById(appointmentId);
		if (!appointmentOpt.isPresent())
			throw new Exception("invalid appointment id");

		Optional<Staff> staffOptional = staffRepository.findById(username);
		if (!staffOptional.isPresent())
			throw new Exception("invalid staff username");

		Appointment appointment = appointmentOpt.get();
		Staff staff = staffOptional.get();

		appointment.setStaff(staff);
		appointmentRepo.save(appointment);

	}

}
