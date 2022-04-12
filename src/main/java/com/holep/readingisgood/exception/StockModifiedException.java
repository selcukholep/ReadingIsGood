package com.holep.readingisgood.exception;

import java.io.Serial;

public class StockModifiedException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -9176734782799769306L;

    private static final String CODE = "1007";
    private static final String DESCRIPTION = "Stock modified before.";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
