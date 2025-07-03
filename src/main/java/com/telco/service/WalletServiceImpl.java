package com.telco.service;

import com.telco.exception.ResourceNotFoundException;
import com.telco.model.Wallet;
import com.telco.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(String id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + id));
    }

    @Override
    public Wallet updateWallet(String id, Wallet walletDetails) {
        Wallet wallet = getWalletById(id);
        wallet.setName(walletDetails.getName());
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(String id) {
        Wallet wallet = getWalletById(id);
        walletRepository.delete(wallet);
    }

    @Override
    @Transactional
    public Wallet deposit(String walletId, double amount) {
        Wallet wallet = getWalletById(walletId);
        if (wallet.getBalance() < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be negative");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet withdraw(String walletId, double amount) {
        Wallet wallet = getWalletById(walletId);

        if (wallet.getBalance() < amount) {
            return null;
        }
        wallet.setBalance(wallet.getBalance() - amount);
        wallet = walletRepository.save(wallet);
        return wallet;
    }

    @Override
    @Transactional
    public Wallet transfer(String sourceWalletId, String destinationWalletId, double amount) {
        Wallet sourceWallet = getWalletById(sourceWalletId);

        if (sourceWallet.getBalance() < amount) {
            return null;
        }
        //Wallet destinationWallet = getWalletById(destinationWalletId);

        sourceWallet.setBalance(sourceWallet.getBalance()- amount);

        walletRepository.save(sourceWallet);

        return sourceWallet;
    }
}
