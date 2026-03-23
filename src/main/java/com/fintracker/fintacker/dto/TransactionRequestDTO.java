package com.fintracker.fintacker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionRequestDTO {
    @NotNull(message = "Необходимо ввести amount")
    @Positive(message = "Не может amount быть отрицательным :(")
    private BigDecimal amount;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String description;

    @NotNull(message = "Вне времени и пространства, да? (createdAt должен присутствовать)")
    private LocalDateTime createdAt;
    private String categoryName;
}
