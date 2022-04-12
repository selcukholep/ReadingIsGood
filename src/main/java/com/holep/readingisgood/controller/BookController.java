package com.holep.readingisgood.controller;

import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class BookController {

    final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> create(
            @Valid BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.create(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateStock(
            @PathVariable String id,
            @RequestParam @Valid @Min(0) int stock) {

        BookDTO bookDTO = bookService.getById(UUID.fromString(id));

        return ResponseEntity.ok(bookService.updateStock(bookDTO, stock));
    }
}
