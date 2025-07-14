package br.edu.ifsp.arq.trekia.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "check_item")
public class CheckItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(length = 120, nullable = false)
    private String description;

    @Column(name = "is_checked")
    private Boolean isChecked;
}
