package br.edu.ifsp.arq.trekia.controllers;

import br.edu.ifsp.arq.trekia.models.services.contracts.IDashboardService;
import br.edu.ifsp.arq.trekia.models.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final IDashboardService dashboardService;
    private final IUserService userService;

    @Autowired
    public DashboardController(IDashboardService dashboardService, IUserService userService) {
        this.dashboardService = dashboardService;
        this.userService = userService;
    }

    @GetMapping("/trips")
    public ResponseEntity<?> getTripsByUserEmail() {

        var user = userService.getAuthenticatedUser();

        return dashboardService.getTripsByUserId(user.get().getId());
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude) {

        return dashboardService.getWeather(latitude, longitude);
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> getCurrency(
            @RequestParam("from") String fromCurrency,
            @RequestParam("to") String toCurrency) {

        return dashboardService.getCurrency(fromCurrency, toCurrency);
    }

    @GetMapping("/currencies/codes")
    public ResponseEntity<?> getCurrencyCodes() {
        return dashboardService.getCurrencyCodes();
    }
}
