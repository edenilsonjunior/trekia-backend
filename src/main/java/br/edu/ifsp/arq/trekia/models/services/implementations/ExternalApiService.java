package br.edu.ifsp.arq.trekia.models.services.implementations;

import br.edu.ifsp.arq.trekia.dtos.dashboard.CurrencyCodesDataDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.CurrencyDataDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.GetWeatherResponseDto;
import br.edu.ifsp.arq.trekia.dtos.dashboard.WeatherDataDto;
import br.edu.ifsp.arq.trekia.models.services.contracts.IExternalApiService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExternalApiService implements IExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<CurrencyDataDto> getCurrentLocalBalance(String fromCurrencyCode, List<String> toCurrencyCodes) {


        var codes = String.join(",", toCurrencyCodes);

        if(codes.equals(fromCurrencyCode))
            return Optional.empty();

        String url = String.format("https://api.frankfurter.app/latest?from=%s&to=%s", fromCurrencyCode, codes);

        try {
            var currencyData = restTemplate.getForObject(url, CurrencyDataDto.class);

            return Optional.ofNullable(currencyData);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetWeatherResponseDto> getWeather(BigDecimal lat, BigDecimal lon) {
        String url = String.format("https://wttr.in/%s,%s?format=j1", lat, lon);

        try {
            var weatherData = restTemplate.getForObject(url, WeatherDataDto.class);

            if (weatherData == null) {
                return Optional.empty();
            }

            var weatherResponse = new GetWeatherResponseDto(
                    weatherData.getMinTempC(),
                    weatherData.getMaxTempC(),
                    weatherData.getMaxChanceOfRain()

            );


            return Optional.ofNullable(weatherResponse);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CurrencyCodesDataDto> getCurrencyCodes() {

        try {
            var response = restTemplate.exchange(
                    "https://api.frankfurter.app/currencies",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, String>>() {
                    });

            var currencies = response.getBody();

            return Optional.of(new CurrencyCodesDataDto(currencies));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}