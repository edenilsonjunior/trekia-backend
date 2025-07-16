package br.edu.ifsp.arq.trekia.models.services.contracts;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IDashboardService {

    ResponseEntity<?> getTripsByUserId(long userId);

    ResponseEntity<?> getWeather(BigDecimal latitude, BigDecimal longitude);

    ResponseEntity<?> getCurrency(String fromCurrency, String toCurrency);

    ResponseEntity<?> getCurrencyCodes();
}
