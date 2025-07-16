package br.edu.ifsp.arq.trekia.dtos.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDataDto {

    private double amount;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public BigDecimal getBrazilianReal() {
        var brlRate = rates.getOrDefault("BRL", 0.0);

        return BigDecimal.valueOf(brlRate);
    }
}