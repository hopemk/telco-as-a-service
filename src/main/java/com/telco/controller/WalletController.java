package com.telco.controller;

import com.telco.model.Wallet;
import com.telco.service.WalletService;
import com.telco.utils.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Wallet>>> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return new ResponseEntity<>(
                ApiResponse.success("Wallets retrieved successfully", wallets),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Wallet>> getWalletById(@PathVariable String id) {
        Wallet wallet = walletService.getWalletById(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet retrieved successfully", wallet),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Wallet>> updateWallet(@PathVariable String id, @Valid @RequestBody Wallet walletDetails) {
        Wallet updatedWallet = walletService.updateWallet(id, walletDetails);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet updated successfully", updatedWallet),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWallet(@PathVariable String id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet deleted successfully", null),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<ApiResponse<Wallet>> deposit(@PathVariable String id, @RequestParam double amount) {
        try {
            Wallet wallet = walletService.deposit(id, amount);
            return new ResponseEntity<>(
                    ApiResponse.success("Funds deposited successfully", wallet),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiResponse.error("Failed to deposit funds: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<ApiResponse<Boolean>> withdraw(@PathVariable String id, @RequestParam double amount) {
        try {
            boolean success = walletService.withdraw(id, amount);
            if (success) {
                return new ResponseEntity<>(
                        ApiResponse.success("Funds withdrawn successfully", true),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        ApiResponse.error("Failed to withdraw funds: Insufficient balance"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiResponse.error("Failed to withdraw funds: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{sourceId}/transfer/{destinationId}")
    public ResponseEntity<ApiResponse<Boolean>> transfer(
            @PathVariable String sourceId,
            @PathVariable String destinationId,
            @RequestParam double amount) {
        try {
            boolean success = walletService.transfer(sourceId, destinationId, amount);
            if (success) {
                return new ResponseEntity<>(
                        ApiResponse.success("Funds transferred successfully", true),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        ApiResponse.error("Failed to transfer funds: Insufficient balance"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ApiResponse.error("Failed to transfer funds: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
