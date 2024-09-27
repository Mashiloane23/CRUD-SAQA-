package com.example.saqa.Repositories;

import com.example.saqa.entities.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);

        List<Customer> findAll(Sort sort);
    List<Customer> findAllByOrderByNameAsc();
    @Query("SELECT c FROM Customer c ORDER BY c.name")
    List<Customer> findAllSortedByName();
    }

