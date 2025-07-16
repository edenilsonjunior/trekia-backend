package br.edu.ifsp.arq.trekia.dtos.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDto {

    @JsonProperty("weather")
    private List<WeatherDayDto> weather;

    public BigDecimal getMinTempC() {
        return new BigDecimal(weather.getFirst().getMintempC());
    }

    public BigDecimal getMaxTempC() {
        return new BigDecimal(weather.getFirst().getMaxtempC());
    }

    public int getMaxChanceOfRain() {
        if (weather == null || weather.isEmpty()) {
            return 0;
        }

        var hourly = weather.getFirst().getHourly();

        return hourly
                .stream()
                .mapToInt(h -> Integer.parseInt(h.getChanceOfRain()))
                .max()
                .orElse(0);
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherDayDto {
        @JsonProperty("mintempC")
        private String mintempC;

        @JsonProperty("maxtempC")
        private String maxtempC;

        @JsonProperty("hourly")
        private List<WeatherHourlyDto> hourly;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherHourlyDto {
        @JsonProperty("chanceofrain")
        private String chanceOfRain;
    }
}
