package com.telco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Represents a wallet that holds a balance for a person.
 */
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    private String id;

    @NotBlank
    private String name;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Wallet() {
        this.id = UUID.randomUUID().toString();
    }

    public Wallet(String name, double initialBalance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = initialBalance >= 0 ? initialBalance : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", walletType=" + walletType +
                ", account=" + account +
                '}';
    }
}
