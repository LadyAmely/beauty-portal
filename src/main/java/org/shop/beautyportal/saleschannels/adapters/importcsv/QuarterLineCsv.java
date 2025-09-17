package org.shop.beautyportal.saleschannels.adapters.importcsv;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class QuarterLineCsv {
    @CsvBindByName(column = "month")
    private Integer month;

    @CsvBindByName(column = "channel")
    private String channel;

    @CsvBindByName(column = "amountInputCcy")
    private BigDecimal amountInputCcy;
}

