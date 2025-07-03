package com.telco.service;

import com.telco.exception.ResourceNotFoundException;
import com.telco.model.EntityStatus;
import com.telco.model.WalletType;
import com.telco.repository.WalletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WalletTypeServiceImpl implements WalletTypeService {
    
    private final WalletTypeRepository walletTypeRepository;
    
    @Autowired
    public WalletTypeServiceImpl(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }
    
    @Override
    public List<WalletType> getAllWalletTypes() {
        return walletTypeRepository.findAll();
    }
    
    @Override
    public List<WalletType> getWalletTypesByStatus(EntityStatus status) {
        return walletTypeRepository.findByEntityStatus(status);
    }
    
    @Override
    public WalletType getWalletTypeById(String id) {
        return walletTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet type not found with id: " + id));
    }
    
    @Override
    public WalletType getWalletTypeByName(String name) {
        WalletType walletType = walletTypeRepository.findByName(name);
        if (walletType == null) {
            throw new ResourceNotFoundException("Wallet type not found with name: " + name);
        }
        return walletType;
    }
    
    @Override
    @Transactional
    public WalletType createWalletType(WalletType walletType) {
        // Set default status if not provided
        if (walletType.getEntityStatus() == null) {
            walletType.setEntityStatus(EntityStatus.ACTIVE);
        }
        return walletTypeRepository.save(walletType);
    }
    
    @Override
    @Transactional
    public WalletType updateWalletType(String id, WalletType walletTypeDetails) {
        WalletType walletType = getWalletTypeById(id);
        walletType.setName(walletTypeDetails.getName());
        
        // Only update status if provided
        if (walletTypeDetails.getEntityStatus() != null) {
            walletType.setEntityStatus(walletTypeDetails.getEntityStatus());
        }
        
        return walletTypeRepository.save(walletType);
    }
    
    @Override
    @Transactional
    public void deleteWalletType(String id) {
        WalletType walletType = getWalletTypeById(id);
        walletType.setEntityStatus(EntityStatus.DELETED);
        walletTypeRepository.save(walletType);
    }
    
    @Override
    @Transactional
    public WalletType activateWalletType(String id) {
        WalletType walletType = getWalletTypeById(id);
        walletType.setEntityStatus(EntityStatus.ACTIVE);
        return walletTypeRepository.save(walletType);
    }
    
    @Override
    @Transactional
    public WalletType deactivateWalletType(String id) {
        WalletType walletType = getWalletTypeById(id);
        walletType.setEntityStatus(EntityStatus.INACTIVE);
        return walletTypeRepository.save(walletType);
    }
}