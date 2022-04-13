package com.holep.readingisgood.data.mapper;

import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.data.entity.Book;
import org.springframework.stereotype.Component;

@Component("mapper-book")
public class BookEntityMapper implements EntityMapper<Book, BookDTO> {

    @Override
    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .authorName(book.getAuthorName())
                .stock(book.getStock())
                .version(book.getVersion())
                .price(book.getPrice())
                .build();
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .authorName(bookDTO.getAuthorName())
                .stock(bookDTO.getStock())
                .version(bookDTO.getVersion())
                .price(bookDTO.getPrice())
                .build();
    }
}
