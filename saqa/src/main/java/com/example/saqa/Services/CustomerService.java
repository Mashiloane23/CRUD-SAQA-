package com.example.saqa.Services;

import com.example.saqa.Repositories.CustomerRepository;
import com.example.saqa.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomersFromFile(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Customer> customers = br.lines()
                    .map(line -> {
                        String[] data = line.split(",");
                        Customer customer = new Customer();
                        customer.setName(data[0]);
                        customer.setEmail(data[1]);
                        customer.setPhoneNumber(data[2]);
                        return customer;
                    })
                    .collect(Collectors.toList());

            customerRepository.saveAll(customers);
        }
    }

    public List<Customer> sortCustomersAlphabetically() {

        return customerRepository.findAllByOrderByNameAsc();
    }


    public List<Customer> getAllCustomersSortedByName() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }


    public Customer addCustomer(Customer cust){

        return customerRepository.save(cust);
    }

    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
   }
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }




    public Optional<Customer> searchCustomerByName(String name) {
        List<Customer> customers = getAllCustomers();
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .findFirst();
    }



}
