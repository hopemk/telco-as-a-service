package com.telco.repository;

import com.telco.model.EntityStatus;
import com.telco.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, String> {
    List<WalletType> findByEntityStatus(EntityStatus entityStatus);
    WalletType findByName(String name);
}