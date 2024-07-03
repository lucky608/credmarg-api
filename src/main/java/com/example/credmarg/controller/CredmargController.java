package com.example.credmarg.controller;

import com.example.credmarg.models.Employee;
import com.example.credmarg.models.Vendor;
import com.example.credmarg.sevice.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("credmarg")
public class CredmargController {

    private static final Logger log = LoggerFactory.getLogger(CredmargController.class);
    private Map<Long,Employee> allEmployees= new HashMap<>();
    private Map<Long, Vendor> allVenders= new HashMap<>();
    private Map<Long, Vendor> allEmailedVenders= new HashMap<>();
    @Autowired
   private MailService mailService;
    @GetMapping("/all-employees")
    public List<Employee> GetAllEmployees(){
        return new ArrayList<>(allEmployees.values());
    }

    @PostMapping("/add-employee")
    public Employee CreateEmployee(@RequestBody Employee employee){

            employee.setId(allEmployees.isEmpty() ?1:allEmployees.size()+1);

        allEmployees.put(employee.getId(),employee);
        return employee;
    }

    @GetMapping("/all-vendors")
    public List<Vendor> GetAllVendors(){
        return new ArrayList<>(allVenders.values());
    }

    @GetMapping("/all-emailed-vendors")
    public List<Vendor> GetAllEmailedVendors(){
        return new ArrayList<>(allEmailedVenders.values());
    }

    @PostMapping("/add-vendor")
    public Vendor CreateVendor(@RequestBody Vendor vendor){
        vendor.setId(allVenders.isEmpty() ?1:allVenders.size()+1);
        allVenders.put(vendor.getId(),vendor);
        return vendor;
    }

    @PostMapping("/send-email")
    public boolean SendMailToVendor(@RequestBody List<Long> vendorIds){
        try {
            for (Long vendorId : vendorIds) {
                Vendor vendorData = allVenders.get(vendorId);
                allEmailedVenders.put(vendorData.getId(), vendorData);
                mailService.sendEmail(vendorData.getEmail(), "Payment sent", "Sending payment to " + vendorData.getName() + " at upi: " + vendorData.getUpi());
            }
        }catch (Exception e) {
            log.error("error in mail sending", e);
        }
        return true;
    }
}
