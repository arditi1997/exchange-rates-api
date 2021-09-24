package com.currency.converter.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class CurrencyConverterResponse {
    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, Double> rates;
    @JsonProperty("totalAmount")
    private Double totalAmount;


}
