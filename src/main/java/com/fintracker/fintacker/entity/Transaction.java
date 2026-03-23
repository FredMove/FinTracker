package com.fintracker.fintacker.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data // с помощью lombok прописываем все гет-сет-оверрайды
@Entity
@Table(name = "transactions")
public class Transaction {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Id //Говорим, что поле - первичный ключ ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // и отдаем генерацию уникальных idшников самой БД
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

}
