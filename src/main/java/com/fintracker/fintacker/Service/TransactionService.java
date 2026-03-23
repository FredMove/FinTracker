package com.fintracker.fintacker.Service;

import com.fintracker.fintacker.Repository.TransactionRepository;
import com.fintracker.fintacker.dto.TransactionRequestDTO;
import com.fintracker.fintacker.dto.TransactionResponseDTO;
import com.fintracker.fintacker.entity.Transaction;
import com.fintracker.fintacker.kafka.TransactionProducer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionProducer transactionProducer;

    public TransactionService(TransactionRepository transactionRepository, TransactionProducer transactionProducer) {
        this.transactionRepository = transactionRepository;
        this.transactionProducer = transactionProducer;
    }

    public TransactionResponseDTO create(TransactionRequestDTO dto){
        Transaction transaction = new Transaction(); //Конвертим
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setCreatedAt(dto.getCreatedAt());

        Transaction saved = transactionRepository.save(transaction); //Сохраняем конвертнутое

        transactionProducer.sendTransaction(
                saved.getUser() != null ? saved.getUser().getId() : null,
                saved.getCategory() != null ? saved.getCategory().getId() : null,
                saved.getAmount()
        );

        TransactionResponseDTO response = new TransactionResponseDTO(); //Составляем ответ в нужном формате
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setDescription(saved.getDescription());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    public List<TransactionResponseDTO> getAllByUser(Long userId){
        List<Transaction> find = new ArrayList<>();
        find = transactionRepository.findByUserId(userId);

        List<TransactionResponseDTO> response = find.stream()
                .map(tr -> new TransactionResponseDTO(tr.getId(), tr.getAmount(), tr.getDescription(), tr.getCreatedAt(), tr.getCategory()!=null? Long.parseLong(tr.getCategory().getName()) : null))
                .collect(Collectors.toList());
        return response;
    }

    public void delete(long id){
        transactionRepository.deleteById(id);
    }
}
