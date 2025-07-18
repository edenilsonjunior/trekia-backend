package br.edu.ifsp.arq.trekia.models.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 120)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CheckItem> checkItems;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TripMedia> medias;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
