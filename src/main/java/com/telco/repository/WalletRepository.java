package com.telco.repository;

import com.telco.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    List<Wallet> findByPersonId(String personId);
    Wallet findByPersonIdAndName(String personId, String name);
}