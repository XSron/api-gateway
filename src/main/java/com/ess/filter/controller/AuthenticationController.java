package com.ess.filter.controller;

import com.ess.filter.service.impl.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthenticationController {

    private PingService pingService;

    @Autowired
    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity<?> healthCheck() throws Exception {
        return ResponseEntity.ok("Authentication Service running");
    }

    @RequestMapping(value = "/api/check", method = RequestMethod.GET)
    public ResponseEntity<?> checkApi() throws Exception {
        return ResponseEntity.ok("Authentication Service running api");
    }

    @RequestMapping(value = "/api/admin", method = RequestMethod.GET)
    public ResponseEntity<?> checkAdmin() throws Exception {
        return ResponseEntity.ok("Authentication checkAdmin running api");
    }

    @GetMapping(value = "/ping")
    public ResponseEntity<HashMap<String,Object>> ping(){
        return ResponseEntity.ok(pingService.ping());
    }
}