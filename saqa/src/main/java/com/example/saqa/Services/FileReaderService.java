package com.example.saqa.Services;

import com.example.saqa.entities.Customer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileReaderService {
    public List<Customer> readCustomersFromTxtFile(String filePath) {
        List<Customer> customers = new ArrayList<>();
        try {
            Files.lines(Paths.get(filePath))
                    .forEach(line -> {
                        String[] parts = line.split(",");
                        Customer customer = new Customer();
                        customer.setName(parts[0]);
                        customer.setEmail(parts[1]);
                        customer.setPhoneNumber(parts[2]);
                        customers.add(customer);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
