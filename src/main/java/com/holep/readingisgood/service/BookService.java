package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.BookDTO;

import java.util.UUID;

public interface BookService {

    BookDTO getById(UUID id);
    BookDTO create(BookDTO bookDTO);
    BookDTO updateStock(BookDTO bookDTO, int newStock);

    void checkStockEnough(BookDTO bookDTO, int amount);
}
