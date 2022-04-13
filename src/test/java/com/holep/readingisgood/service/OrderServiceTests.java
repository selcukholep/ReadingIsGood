package com.holep.readingisgood.service;

import com.holep.readingisgood.data.constant.OrderDetail;
import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.service.impl.OrderDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceTests {

    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @MockBean
    BookService bookService;

    private static OrderDetail orderDetail;

    @BeforeEach
    public void init() {
        orderDetail = new OrderDetail();

        orderDetail.setAmount(5);
        orderDetail.setBookId(UUID.fromString("8f4a0e56-012b-46c6-a29c-052425468f7c"));
    }

    @Test
    public void createOrderDetail_whenStockEnoughAndStockNotModified_thenReturnNewResponse() {

        BookDTO bookDTO = BookDTO.builder()
                .id(orderDetail.getBookId())
                .name("Book A")
                .stock(10)
                .price(10).build();

        when(bookService.getById(orderDetail.getBookId()))
                .thenReturn(bookDTO);

        assertEquals(5 * 10L, orderDetailService.create(orderDetail).getPrice());
    }
}
