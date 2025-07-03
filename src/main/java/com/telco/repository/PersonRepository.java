package com.telco.repository;

import com.telco.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Account, String> {
    // Spring Data JPA will automatically implement basic CRUD operations
}