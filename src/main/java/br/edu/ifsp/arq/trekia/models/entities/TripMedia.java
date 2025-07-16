package br.edu.ifsp.arq.trekia.models.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "trip_media")
public class TripMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Lob
    private byte[] media;

    @Column(length = 100)
    private String description;

    @Column(name = "uploaded_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    public String getMediaBase64() {
        return media != null ? java.util.Base64.getEncoder().encodeToString(media) : null;
    }
}
