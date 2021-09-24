package com.currency.converter.Model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
public class CurrencyConverterRequest {
    @NotNull
    @Length(min=3,max=3)
    private String base;
    @NotNull
    @Length(min=3,max=3)
    private String symbol;
    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Double amount;

}
