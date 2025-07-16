package br.edu.ifsp.arq.trekia.models.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "planned_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime plannedAt;

    @Column(length = 120, nullable = false)
    private String title;

    @Column(length = 120)
    private String description;

    @Column(precision = 8, scale = 6, nullable = false)
    private BigDecimal latitude = BigDecimal.ZERO;

    @Column(precision = 9, scale = 6, nullable = false)
    private BigDecimal longitude = BigDecimal.ZERO;

    @Column(name = "current_local_balance", nullable = false)
    private BigDecimal currentLocalBalance = BigDecimal.ONE;

    @Column(name = "currency_code", length = 3, nullable = false, columnDefinition = "CHAR(3)")
    private String currencyCode;

    @Column(name = "min_temperature", precision = 3, scale = 1, nullable = false)
    private BigDecimal minTemperature = BigDecimal.ZERO;

    @Column(name = "max_temperature", precision = 3, scale = 1, nullable = false)
    private BigDecimal maxTemperature = BigDecimal.ZERO;

    @Column(name = "precipitation_chance", nullable = false)
    private Integer precipitationChance = 0;

    @PrePersist
    public void prePersist() {
        this.plannedAt = LocalDateTime.now();
    }
}
