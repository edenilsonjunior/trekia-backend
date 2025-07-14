package br.edu.ifsp.arq.trekia.models.services.contracts;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IDashboardService {

    ResponseEntity<?> getTripsByUserEmail(String email);

    ResponseEntity<?> getWeather(BigDecimal latitude, BigDecimal longitude, LocalDate startDate, LocalDate endDate);

    ResponseEntity<?> getCurrency(String fromCurrency, String toCurrency);

    ResponseEntity<?> getCurrencyCodes();
}
