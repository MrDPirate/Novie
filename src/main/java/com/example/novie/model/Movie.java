//package com.example.novie.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Year;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//@Table(name = "movies")
//@Getter
//@Setter
//public class Movie {
//
//    // movieId
//    @Id
//    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Name
//    @Column
//    private String name;
//
//    @Column
//    private String description;
//
//    @Column
//    private Year year;
//
//    // CategoryId
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    @JsonIgnore
//    private Category category;
//
//}
