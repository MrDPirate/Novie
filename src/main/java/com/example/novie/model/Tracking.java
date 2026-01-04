package com.example.novie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tracking")
public class Tracking {

    // trackingId
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    // Status
    @Column
    @Getter @Setter private String status;

    // CreatedAt
    @Column
    @CreationTimestamp
    @Setter @Getter
    private LocalDateTime createdAt;

    // UpdatedAt
    @Column
    @UpdateTimestamp
    @Setter @Getter
    private LocalDateTime updatedAt;

    // MovieId
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    @Getter @Setter
    private Movie movie;

}
