package com.currency.converter.Service;

import com.currency.converter.Model.CurrencyConverterResponse;

public interface CurrencyConverterService {

     CurrencyConverterResponse callCurrencyConverterApi(String base, String symbol);

     void calculateAmount(Double amount, CurrencyConverterResponse currencyConverterResponse);
}
