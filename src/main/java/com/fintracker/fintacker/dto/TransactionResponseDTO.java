package com.fintracker.fintacker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(Long id, BigDecimal amount, String description, LocalDateTime createdAt, Long categoryId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private Long categoryId;
}
