package com.example.saqa.Controller;

import com.example.saqa.Repositories.CustomerRepository;
import com.example.saqa.Services.CustomerService;
import com.example.saqa.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerControl {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    // Endpoint to upload customer data from a file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCustomerData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file.");
        }

        try {
            customerService.saveCustomersFromFile(file);
            return ResponseEntity.ok("Customer data uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload customer data.");
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    //@GetMapping("/sorted")
    //public ResponseEntity<List<Customer>> getAllCustomersSortedByName() {
        //List<Customer> sortedCustomers = customerRepository.findAllSortedByName();
        //return new ResponseEntity<>(sortedCustomers, HttpStatus.OK);
   //}
    @GetMapping("/sorted")
    public ResponseEntity<List<Customer>> getAllCustomersSortedByName() {
        List<Customer> sortedCustomers = customerRepository.findAllSortedByName();
        return ResponseEntity.ok(sortedCustomers);
    }

    // Endpoint to get sorted customers
    @GetMapping("/sort")
    public ResponseEntity<List<Customer>> getSortedCustomers() {
        List<Customer> sortedCustomers = customerService.sortCustomersAlphabetically();
        return ResponseEntity.ok(sortedCustomers);
    }
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String searchTerm) {
        return customerRepository.findByNameContainingIgnoreCase(searchTerm);
    }
}