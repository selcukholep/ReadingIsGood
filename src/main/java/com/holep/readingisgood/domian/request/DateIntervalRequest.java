package com.holep.readingisgood.domian.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class DateIntervalRequest {

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date startDate;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date endDate;
}
