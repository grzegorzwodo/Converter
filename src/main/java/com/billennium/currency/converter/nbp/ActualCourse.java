package com.billennium.currency.converter.nbp;

import lombok.Data;

import java.util.List;

@Data
public class ActualCourse {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
