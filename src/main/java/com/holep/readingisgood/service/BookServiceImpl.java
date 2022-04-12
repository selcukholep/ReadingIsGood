package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.data.entity.Book;
import com.holep.readingisgood.data.mapper.BookEntityMapper;
import com.holep.readingisgood.data.repository.BookRepository;
import com.holep.readingisgood.exception.BookNotFoundException;
import com.holep.readingisgood.exception.StockModifiedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository repository;
    final BookEntityMapper mapper;

    @Override
    public BookDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public BookDTO create(BookDTO bookDTO) {

        Book book = mapper.toEntity(bookDTO);

        book.setId(UUID.randomUUID());

        return mapper.toDTO(repository.save(book));
    }

    @Override
    public BookDTO updateStock(BookDTO bookDTO, int newStock) throws StockModifiedException {

        Book book = mapper.toEntity(bookDTO);

        book.setStock(newStock);

        try {
            // Try to save
            return mapper.toDTO(repository.save(book));
        } catch (OptimisticLockingFailureException e) {
            throw new StockModifiedException();
        }
    }

    @Override
    public boolean isStockEnough(BookDTO bookDTO, int amount) {
        return bookDTO.getStock() >= amount;
    }
}
