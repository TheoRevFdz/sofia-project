package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.services.IFakerDataService;
import com.educibertec.sofiaproject.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fakerData")
public class FakerDataController {
    @Autowired
    private IFakerDataService service;

    @PostMapping("/generate")
    public ResponseEntity<Object> generate() {
        try {
            MessageUtil resp = service.generateFakerData();
            if (resp.getStatus() == HttpStatus.OK.value()) {
                return ResponseEntity.ok(resp);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
