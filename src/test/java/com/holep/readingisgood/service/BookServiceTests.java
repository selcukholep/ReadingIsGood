package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.data.entity.Book;
import com.holep.readingisgood.data.mapper.BookEntityMapper;
import com.holep.readingisgood.data.repository.BookRepository;
import com.holep.readingisgood.exception.BookNotFoundException;
import com.holep.readingisgood.exception.NotEnoughStockException;
import com.holep.readingisgood.exception.StockModifiedException;
import com.holep.readingisgood.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Spy
    BookEntityMapper bookEntityMapper;

    private static BookDTO bookDTO;
    private static Book book;

    @BeforeEach
    public void init() {

        bookDTO = BookDTO.builder()
                .price(15.9)
                .stock(15)
                .name("Book A")
                .authorName("Author A")
                .build();

        book = Book.builder()
                .name("Book A")
                .authorName("Author A")
                .stock(15)
                .price(15.9)
                .id(UUID.fromString("8f4a0e56-012b-46c6-a29c-052425468f7c"))
                .version(0L)
                .build();
    }

    @Test
    public void getBook_whenBookNotFound_thenThrowBookNotFoundException() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getById(UUID.randomUUID()));
    }

    @Test
    public void isStockEnough_whenStockNotEnough_thenThrowStockNotEnoughException() {

        assertThrows(NotEnoughStockException.class, () -> bookService.checkStockEnough(bookDTO, bookDTO.getStock() + 1));
    }

    @Test
    public void updateStock_whenOptimisticLockingFailureExceptionOccurred_thenThrowStockModifiedException() {
        when(bookRepository.save(any()))
                .thenThrow(OptimisticLockingFailureException.class);

        assertThrows(StockModifiedException.class, () -> bookService.updateStock(bookDTO, 5));
    }

    @Test
    public void updateStock_whenNoLockingFailuresOccurred_thenUpdateStockAndReturnNewResponse() {

        when(bookRepository.save(any()))
                .then(invocation -> {
                    book.setStock(5);
                    return book;
                });

        bookDTO.setId(book.getId());

        assertEquals(5, bookService.updateStock(bookDTO, 5).getStock());
    }
}
