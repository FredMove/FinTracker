package com.fintracker.fintacker.Controller;

import com.fintracker.fintacker.Service.TransactionService;
import com.fintracker.fintacker.dto.TransactionRequestDTO;
import com.fintracker.fintacker.dto.TransactionResponseDTO;
import com.fintracker.fintacker.entity.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@Valid @RequestBody TransactionRequestDTO transaction){
        return ResponseEntity.status(201).body(transactionService.create(transaction));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllByUser(@PathVariable long userId){
        return ResponseEntity.ok().body(transactionService.getAllByUser(userId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
