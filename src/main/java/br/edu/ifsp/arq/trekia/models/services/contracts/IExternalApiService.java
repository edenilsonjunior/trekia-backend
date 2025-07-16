package br.edu.ifsp.arq.trekia.models.services.contracts;

import br.edu.ifsp.arq.trekia.dtos.dashboard.CurrencyCodesDataDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.CurrencyDataDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.GetWeatherResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IExternalApiService {

    Optional<CurrencyDataDto> getCurrentLocalBalance(String fromCurrencyCode, List<String> toCurrencyCodes);

    Optional<GetWeatherResponseDto> getWeather(BigDecimal lat, BigDecimal lon);

    Optional<CurrencyCodesDataDto> getCurrencyCodes();
}