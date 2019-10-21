package com.company.stocktransfer;

import lombok.Data;

@Data
public class MovedStock {

    private StockId id;
    private String batch;
    private String moveType;
    private String movTypeSumResult;
}
