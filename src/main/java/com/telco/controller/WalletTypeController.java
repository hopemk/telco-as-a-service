package com.telco.controller;

import com.telco.model.EntityStatus;
import com.telco.model.WalletType;
import com.telco.service.WalletTypeService;
import com.telco.utils.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wallet-types")
public class WalletTypeController {

    private final WalletTypeService walletTypeService;

    @Autowired
    public WalletTypeController(WalletTypeService walletTypeService) {
        this.walletTypeService = walletTypeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WalletType>>> getAllWalletTypes() {
        List<WalletType> walletTypes = walletTypeService.getAllWalletTypes();
        return new ResponseEntity<>(
                ApiResponse.success("Wallet types retrieved successfully", walletTypes),
                HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<WalletType>>> getWalletTypesByStatus(@PathVariable EntityStatus status) {
        List<WalletType> walletTypes = walletTypeService.getWalletTypesByStatus(status);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet types with status " + status + " retrieved successfully", walletTypes),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WalletType>> getWalletTypeById(@PathVariable String id) {
        WalletType walletType = walletTypeService.getWalletTypeById(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type retrieved successfully", walletType),
                HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<WalletType>> getWalletTypeByName(@PathVariable String name) {
        WalletType walletType = walletTypeService.getWalletTypeByName(name);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type retrieved successfully", walletType),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WalletType>> createWalletType(@Valid @RequestBody WalletType walletType) {
        WalletType createdWalletType = walletTypeService.createWalletType(walletType);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type created successfully", createdWalletType),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WalletType>> updateWalletType(@PathVariable String id, @Valid @RequestBody WalletType walletTypeDetails) {
        WalletType updatedWalletType = walletTypeService.updateWalletType(id, walletTypeDetails);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type updated successfully", updatedWalletType),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWalletType(@PathVariable String id) {
        walletTypeService.deleteWalletType(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type deleted successfully", null),
                HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<WalletType>> activateWalletType(@PathVariable String id) {
        WalletType activatedWalletType = walletTypeService.activateWalletType(id);
        return new ResponseEntity<>(
                ApiResponse.success("Wallet type activated successfully", activatedWalletType),
                HttpStatus.OK);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<WalletType> deactivateWalletType(@PathVariable String id) {
        return new ResponseEntity<>(walletTypeService.deactivateWalletType(id), HttpStatus.OK);
    }
}
