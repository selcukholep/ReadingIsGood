package com.holep.readingisgood.domian.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PaginationRequest {

    @Min(0)
    private int page = 0;

    @Min(1) @Max(20)
    private int size = 10;
}
