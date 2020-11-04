package com.ess.filter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

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
}