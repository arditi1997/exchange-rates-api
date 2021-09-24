package com.currency.converter.ServiceImpl;

import com.currency.converter.Model.CurrencyConverterResponse;
import com.currency.converter.Service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Configuration
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    @Value("${api-url}")
    private String apiUrl;

    @Value("${api-key}")
    private String apiKey;

    @Autowired
    CacheManager cacheManager;

    //call api
    @Override
    @Cacheable(value = "currencyApi")
    public CurrencyConverterResponse callCurrencyConverterApi(String base, String symbol) {
        String url = apiUrl + "?access_key=" + apiKey + "&base=" + base + "&symbols=" + symbol;
        return new RestTemplate().getForObject(url, CurrencyConverterResponse.class);
    }

    //calculate amount
    @Override
    public void calculateAmount(Double amount, CurrencyConverterResponse currencyConverterResponse) {
        for (Map.Entry<String, Double> pair : currencyConverterResponse.getRates().entrySet()) {
            Double value = currencyConverterResponse.getRates().get(pair.getKey());
            currencyConverterResponse.setTotalAmount(value * amount);
        }
    }
    //clear chache every day on 08:00
    @Scheduled(cron = "0 0 8 * * ?")
    public void clearCache() {
        cacheManager.getCache("currencyApi").clear();
    }
}
