package com.myapp.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.dto.ChangeAppointmentStaffDto;
import com.myapp.myapp.model.Appointment;
import com.myapp.myapp.model.Branch;
import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Staff;
import com.myapp.myapp.service.BranchHeadService;
import com.myapp.myapp.service.BranchService;

@RestController
@RequestMapping("/branch_head")
public class BranchHeadController {

  @Autowired
  BranchHeadService branchHeadService;

  @Autowired
  BranchService branchService;

  @GetMapping(path = "/find_branch/{branchHeadUserName")
  public Branch findBranch(@PathVariable("branchHeadUserName") String branchHeadUserName) {
    return branchHeadService.findBranchbyBranchHeadUserName(branchHeadUserName);
  }

  @GetMapping(path = "/find_branch_staff/{branchName}")
  public List<Staff> findAllBranchStaff(@PathVariable("branchName") String branchName) {
    return branchHeadService.findStaffByBranch(branchService.getBranch(branchName));
  }

  @GetMapping(path = "/find_branch_appointments")
  public List<Appointment> findAllBranchAppointmentForToday(HttpServletRequest request) {
    Object obj = request.getAttribute("user");
    if (!(obj instanceof BranchHead))
      return null;
    BranchHead branchHead = (BranchHead) obj;
    try {
      return branchHeadService.getAllBranchAppointmentsForToday(branchHead.getBranch());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @PutMapping(path = "/reassign_staff")
  public ResponseEntity<String> reassignStaff(HttpServletRequest request,
      @RequestBody ChangeAppointmentStaffDto changeAppointmentStaffDto) {
    Object obj = request.getAttribute("user");
    if (!(obj instanceof BranchHead))
      return null;
    BranchHead branchHead = (BranchHead) obj;
    try {
      branchHeadService.changeAppointmentStaff(changeAppointmentStaffDto.getId(),
          changeAppointmentStaffDto.getUsername());
      return ResponseEntity.ok()
          .body("Appoinment assigned to " + changeAppointmentStaffDto.getUsername());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
