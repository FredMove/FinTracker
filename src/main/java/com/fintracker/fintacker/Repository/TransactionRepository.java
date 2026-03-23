package com.fintracker.fintacker.Repository;

import com.fintracker.fintacker.dto.TransactionResponseDTO;
import com.fintracker.fintacker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{ //Мы просто делаем репо под наш сервис, в котором наследуются все нужные нам методы
    List<Transaction> findByUserId(Long userId);
}
