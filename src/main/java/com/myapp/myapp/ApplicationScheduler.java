package com.myapp.myapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myapp.myapp.model.Appointment;
import com.myapp.myapp.model.Branch;
import com.myapp.myapp.model.BranchHead;
import com.myapp.myapp.model.Customer;
import com.myapp.myapp.service.BranchHeadService;
import com.myapp.myapp.service.BranchService;

@Component
public class ApplicationScheduler {

  @Autowired
  BranchService branchService;

  @Autowired
  BranchHeadService branchHeadService;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
      .ofPattern("HH:mm:ss");

  @Scheduled(cron = "0 9 * * 1-6 ?")  //Send daily mails at 9:00 AM (Mon-Sat)
  public void scheduleTaskWithCronExpression() {
    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

    List<Branch> branchList = branchService.getAllBranches();
    for (Branch branch : branchList) {
      BranchHead branchHead = branchHeadService.getBranchHead(branch);
      List<Appointment> appointments = branchHeadService
          .getAllBranchAppointmentsForToday(branchHead.getBranch());
      for (Appointment appointment : appointments) {
        sendEmail(branchHead, appointment.getCustomer(), appointment);
      }
    }
  }

  public void sendEmail(BranchHead branchHead, Customer customer, Appointment appointment) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(branchHead.getUserName());
    msg.setSubject("" + "Gentle Reminder! High Net Worth Customer Visiting Your Branch");
    msg.setText("Hi " + branchHead.getName() + ", \n\n" + customer.getName()
        + " is going to visit your branch today for "
        + appointment.getPurpose() + ".\n\n" + "Sincerely,\n" + "Syndicate Bank");
    javaMailSender.send(msg);
  }
}
