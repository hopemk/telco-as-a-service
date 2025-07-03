package com.telco.service;

import com.telco.model.Wallet;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {
    List<Wallet> getAllWallets();
    Wallet getWalletById(String id);
    Wallet updateWallet(String id, Wallet walletDetails);
    void deleteWallet(String id);
    Wallet deposit(String walletId, double amount);
    Wallet withdraw(String walletId, double amount);
    Wallet transfer(String sourceWalletId, String destinationWalletId, double amount);
}
