package com.myapp.myapp;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.myapp.model.Admin;
import com.myapp.myapp.model.Appointment;
import com.myapp.myapp.model.AppointmentStatus;
import com.myapp.myapp.model.Branch;
import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Customer;
import com.myapp.myapp.model.HNICustomer;
import com.myapp.myapp.model.Role;
import com.myapp.myapp.model.Staff;
import com.myapp.myapp.repository.AppointmentRepository;
import com.myapp.myapp.repository.BranchHeadRepository;
import com.myapp.myapp.repository.BranchRepository;
import com.myapp.myapp.repository.HNICustomerRepository;
import com.myapp.myapp.repository.StaffRepository;
import com.myapp.myapp.repository.UserRepository;

@Component
public class SaveSampleDataOnStartup {

	@Autowired
	BranchRepository branchRepository;
	@Autowired
	BranchHeadRepository branchHeadRepository;
	@Autowired
	StaffRepository staffRepo;
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	HNICustomerRepository hniCustomerRepo;
	
	@Autowired
	UserRepository userRepo;

	@PostConstruct
	public void saveData() {
		branchRepository.deleteAll();
		Branch br1 = new Branch(null, "Chickpet", "BANGALORE", "560053", "KARNATAKA");
    Branch br2 = new Branch(null, "Wilson Garden", "BANGALORE", "560027", "KARNATAKA");
    Branch br3 = new Branch(null, "J P Nagar", "BANGALORE", "560076", "KARNATAKA");
    Branch br4 = new Branch(null, "Cantonment", "BANGALORE", "560042", "KARNATAKA");
    Branch br5 = new Branch(null, "Pitampura", "DELHI", "110088", "DELHI");
    
    branchRepository.save(br1);
    branchRepository.save(br2);
    branchRepository.save(br3);
    branchRepository.save(br4);
    branchRepository.save(br5);

		// ---------Adding Sample Data for Branch Head ----------------

		BranchHead branchHead1 = new BranchHead();
		branchHead1.setName("Branch Head 1");
		branchHead1.setUserName("sanchitgarg2012@gmail.com");
		branchHead1.setPassword("1234");
		branchHead1.setRole(Role.BRANCH_HEAD);
		branchHead1.setBranch(br1);
		branchHeadRepository.save(branchHead1);
		
		BranchHead branchHead2 = new BranchHead();
		branchHead2.setName("Branch Head 2");
		branchHead2.setUserName("branchhead2@gmail.com");
		branchHead2.setPassword("1234");
		branchHead2.setRole(Role.BRANCH_HEAD);
		branchHead2.setBranch(br2);
		branchHeadRepository.save(branchHead2);
		
		BranchHead branchHead3 = new BranchHead();
    branchHead3.setName("Branch Head 3");
    branchHead3.setUserName("branchhead3@gmail.com");
    branchHead3.setPassword("1234");
    branchHead3.setRole(Role.BRANCH_HEAD);
    branchHead3.setBranch(br3);
    branchHeadRepository.save(branchHead3);
    
    BranchHead branchHead4 = new BranchHead();
    branchHead4.setName("Branch Head 2");
    branchHead4.setUserName("branchhead4@gmail.com");
    branchHead4.setPassword("1234");
    branchHead4.setRole(Role.BRANCH_HEAD);
    branchHead4.setBranch(br4);
    branchHeadRepository.save(branchHead4);
    
    BranchHead branchHead5 = new BranchHead();
    branchHead5.setName("Branch Head 5");
    branchHead5.setUserName("branchhead5@gmail.com");
    branchHead5.setPassword("1234");
    branchHead5.setRole(Role.BRANCH_HEAD);
    branchHead5.setBranch(br5);
    branchHeadRepository.save(branchHead5);

		// -------------Adding Sample Data for Staff-----------------

		Staff staff1 = new Staff();
		staff1.setUserName("staff1@gmail.com");
		staff1.setName("Staff 1");
		staff1.setPassword("1234");
		staff1.setRole(Role.STAFF);
		staffRepo.save(staff1);
		
		Staff staff2 = new Staff();
    staff2.setUserName("staff2@gmail.com");
    staff2.setName("Staff 2");
    staff2.setPassword("1234");
    staff2.setRole(Role.STAFF);
    staffRepo.save(staff2);

		// -------------Adding Sample Data for Customer-------------------

		Customer customer = new Customer("9873797189", "Customer 1", Boolean.FALSE);
		
		Appointment appointment = new Appointment(null, "Account", "Account Creation", br1, new Date(), customer,
				staff1, AppointmentStatus.UNATTENDED);
		appointmentRepo.save(appointment);
		
		// -------------Adding Sample Data for HNICustomer-------------------
		HNICustomer hnicustomer = new HNICustomer(1L, "Ankit","9873797189","3000000");
		hniCustomerRepo.save(hnicustomer);	
		
		/***************Adding data for Admin *******************************/
		Admin admin = new Admin();
    admin.setUserName("admin");
    admin.setName("Administrator");
    admin.setPassword("1234");
    admin.setRole(Role.ADMIN);
    userRepo.save(admin);
	}

}
