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
@Table(name = "movies")
public class Movie {

    // movieId
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    // Name
    @Column
    @Getter @Setter private String name;

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

    // CategoryId
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    @Getter @Setter
    private Category category;

}
