package com.telco.service;

import com.telco.model.Account;
import com.telco.model.Wallet;
import java.util.List;

public interface AccountService {
    List<Account> getAllPersons();
    Account getPersonById(String id);
    Account createPerson(Account account);
    Account updatePerson(String id, Account accountDetails);
    void deletePerson(String id);
    Wallet createWalletForPerson(String personId, String walletName, double initialBalance);
    List<Wallet> getPersonWallets(String personId);
    double getPersonTotalBalance(String personId);
}