package com.holep.readingisgood.service.impl;

import com.holep.readingisgood.data.constant.OrderDetail;
import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.exception.NotEnoughStockException;
import com.holep.readingisgood.exception.StockModifiedException;
import com.holep.readingisgood.service.BookService;
import com.holep.readingisgood.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    final BookService bookService;

    @Retryable(value = StockModifiedException.class, maxAttemptsExpression = "${app.order.retry.max-attempts:3}")
    @Override
    public OrderDetail create(OrderDetail orderDetail) {

        BookDTO bookDTO = bookService.getById(orderDetail.getBookId());

        checkStock(bookDTO, orderDetail);
        decrementAndUpdateStock(bookDTO, orderDetail);

        orderDetail.setPrice(bookDTO.getPrice() * orderDetail.getAmount());

        return orderDetail;
    }

    // Private Methods

    private void checkStock(BookDTO bookDTO, OrderDetail orderDetail) {
        if (!bookService.isStockEnough(bookDTO, orderDetail.getAmount())) {
            throw new NotEnoughStockException();
        }
    }

    private void decrementAndUpdateStock(BookDTO bookDTO, OrderDetail orderDetail) {
        bookService.updateStock(bookDTO, bookDTO.getStock() - orderDetail.getAmount());
    }
}
