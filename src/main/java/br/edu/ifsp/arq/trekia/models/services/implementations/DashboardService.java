package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.models.services.Result;
import br.edu.ifsp.arq.trekia.models.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService implements IDashboardService {

    private final IExternalApiService externalApiService;
    private final IUserService userService;
    private final ITripService tripService;

    @Autowired
    public DashboardService(IExternalApiService externalApiService, IUserService userService,
            ITripService tripService) {
        this.externalApiService = externalApiService;
        this.userService = userService;
        this.tripService = tripService;
    }

    @Override
    public ResponseEntity<?> getTripsByUserId(long userId) {

        // TODO: RETORNAR A FOTO PRINCIPAL (USAR ESSA SERVICE COMO RESUMO NA TELA DE DASHBOARD)
        return tripService.getTripsByUserId(userId);
    }

    @Override
    public ResponseEntity<?> getWeather(BigDecimal latitude, BigDecimal longitude) {

        var weatherResponse = externalApiService.getWeather(latitude, longitude);

        if (weatherResponse.isEmpty()) {
             return Result.toResponse("Erro ao obter dados climáticos", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Result.toResponse(weatherResponse.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCurrency(String fromCurrency, String toCurrency) {

        var currencyResponse = externalApiService.getCurrentLocalBalance(fromCurrency, List.of(toCurrency));

        if (currencyResponse.isEmpty()) {
            return Result.toResponse("Erro ao obter dados de câmbio", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(currencyResponse.get());
    }

    @Override
    public ResponseEntity<?> getCurrencyCodes() {
        var currencyCodesResponse = externalApiService.getCurrencyCodes();

        if (currencyCodesResponse.isEmpty()) {
            return Result.toResponse("Erro ao obter códigos de moedas", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Result.toResponse(currencyCodesResponse.get().currencies(), HttpStatus.OK);
    }
}
