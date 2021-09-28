package com.currency.converter.Controller;

import com.currency.converter.Exception.CustomException;
import com.currency.converter.Exception.ErrorCode;
import com.currency.converter.Model.CurrencyConverterRequest;
import com.currency.converter.Model.CurrencyConverterResponse;
import com.currency.converter.Service.CurrencyConverterService;
import com.currency.converter.ServiceImpl.CurrencyConverterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CurrencyController {

    public static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyConverterService service;

    @PostMapping("/currency-converter")
    public ResponseEntity<CurrencyConverterResponse> getDataConverter(@Valid @RequestBody CurrencyConverterRequest converter, BindingResult bindingResult) {
        try{
            //call api from exchange-rates-api
            CurrencyConverterResponse response = service.callCurrencyConverterApi(converter.getBase(), converter.getSymbol());
            //calculate amount
            service.calculateAmount(converter.getAmount(), response);
            logger.info("Exchange rates from {} to {} is: {}",converter.getBase(), converter.getSymbol(), response);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            if(bindingResult.hasErrors()){
                throw new CustomException(ErrorCode.INCORRECT_REQUEST_BODY);
            }else{
                throw new CustomException(ErrorCode.NO_SUCH_CURRENCY);
            }
        }
    }
}
