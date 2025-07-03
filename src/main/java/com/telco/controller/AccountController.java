package com.telco.controller;

import com.telco.model.Account;
import com.telco.model.Wallet;
import com.telco.service.AccountService;
import com.telco.utils.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class AccountController {

    private final AccountService personService;

    @Autowired
    public AccountController(AccountService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Account>>> getAllPersons() {
        List<Account> accounts = personService.getAllPersons();
        return new ResponseEntity<>(
                ApiResponse.success("Accounts retrieved successfully", accounts),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> getPersonById(@PathVariable String id) {
        Account account = personService.getPersonById(id);
        return new ResponseEntity<>(
                ApiResponse.success("Account retrieved successfully", account),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> createPerson(@Valid @RequestBody Account account) {
        Account createdAccount = personService.createPerson(account);
        return new ResponseEntity<>(
                ApiResponse.success("Account created successfully", createdAccount),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> updatePerson(@PathVariable String id, @Valid @RequestBody Account accountDetails) {
        Account updatedAccount = personService.updatePerson(id, accountDetails);
        return new ResponseEntity<>(
                ApiResponse.success("Account updated successfully", updatedAccount),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(
                ApiResponse.success("Account deleted successfully", null),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/wallets")
    public ResponseEntity<ApiResponse<List<Wallet>>> getPersonWallets(@PathVariable String id) {
        List<Wallet> wallets = personService.getPersonWallets(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallets retrieved successfully", wallets),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/wallets")
    public ResponseEntity<ApiResponse<Wallet>> createWallet(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam(defaultValue = "0.0") double initialBalance) {
        Wallet wallet = personService.createWalletForPerson(id, name, initialBalance);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet created successfully", wallet),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/total-balance")
    public ResponseEntity<ApiResponse<Double>> getTotalBalance(@PathVariable String id) {
        Double totalBalance = personService.getPersonTotalBalance(id);
        return new ResponseEntity<>(
                ApiResponse.success("Total balance retrieved successfully", totalBalance),
                HttpStatus.OK);
    }
}
