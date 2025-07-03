package com.telco.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class WalletType {

    @Id
    private String id;

    @NotBlank
    private String name;

    private EntityStatus entityStatus;

    public WalletType(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public WalletType() {

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

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
