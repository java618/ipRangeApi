package com.example.demo.controllers;


import com.example.demo.models.IpRangeModel;
import com.example.demo.services.IpRangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("ipRange")
public class IpRangeController {

    public IpRangeController(IpRangeServiceImpl ipRangeService) {
        this.ipRangeService = ipRangeService;
    }

    @Autowired
    IpRangeServiceImpl ipRangeService;



    @GetMapping("/{ipAddress}")
    public Object findRange(@PathVariable String ipAddress){
        return ipRangeService.findRange(ipAddress);
    }

    @PostMapping("/addRange")
    public ResponseEntity<String> addRange(@RequestBody IpRangeModel ipRangeModel){
        return ipRangeService.addIpRange(ipRangeModel);
    }
}
