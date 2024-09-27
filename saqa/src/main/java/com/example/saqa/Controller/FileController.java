package com.example.saqa.Controller;

import com.example.saqa.Services.FileReaderService;
import com.example.saqa.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileReaderService fileReaderService;

    @GetMapping("/read")
    public ResponseEntity<List<Customer>> readCustomersFromFile(@RequestParam String filePath) {
        List<Customer> customers = fileReaderService.readCustomersFromTxtFile(filePath);
        return ResponseEntity.ok(customers);
    }
}
