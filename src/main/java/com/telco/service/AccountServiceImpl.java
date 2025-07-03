package com.telco.service;

import com.telco.exception.ResourceNotFoundException;
import com.telco.model.Account;
import com.telco.model.Wallet;
import com.telco.repository.PersonRepository;
import com.telco.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    
    private final PersonRepository personRepository;
    private final WalletRepository walletRepository;
    
    @Autowired
    public AccountServiceImpl(PersonRepository personRepository, WalletRepository walletRepository) {
        this.personRepository = personRepository;
        this.walletRepository = walletRepository;
    }
    
    @Override
    public List<Account> getAllPersons() {
        return personRepository.findAll();
    }
    
    @Override
    public Account getPersonById(String id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }
    
    @Override
    public Account createPerson(Account account) {
        return personRepository.save(account);
    }
    
    @Override
    public Account updatePerson(String id, Account accountDetails) {
        Account account = getPersonById(id);
        account.setName(accountDetails.getName());
        return personRepository.save(account);
    }
    
    @Override
    public void deletePerson(String id) {
        Account account = getPersonById(id);
        personRepository.delete(account);
    }
    
    @Override
    @Transactional
    public Wallet createWalletForPerson(String personId, String walletName, double initialBalance) {
        Account account = getPersonById(personId);
        Wallet wallet = account.createWallet(walletName, initialBalance);
        personRepository.save(account);
        return wallet;
    }
    
    @Override
    public List<Wallet> getPersonWallets(String personId) {
        Account account = getPersonById(personId);
        return account.getWallets();
    }
    
    @Override
    public double getPersonTotalBalance(String personId) {
        Account account = getPersonById(personId);
        return account.getTotalBalance();
    }
}