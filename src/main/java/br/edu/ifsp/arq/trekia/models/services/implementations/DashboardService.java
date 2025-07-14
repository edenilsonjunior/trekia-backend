package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.models.services.contracts.IDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService implements IDashboardService {
    @Override
    public ResponseEntity<?> getTripsByUserEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity<?> getWeather(BigDecimal latitude, BigDecimal longitude, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCurrency(String fromCurrency, String toCurrency) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCurrencyCodes() {
        return null;
    }
}
