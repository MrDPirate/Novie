package com.example.novie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"movieList", "subCategoryMovies"})
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "categories")
@Getter
@Setter
public class Category {

    // categoryId
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name
    @Column
    private String name;



    // MovieId
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Movie> movieList;

    @JsonIgnore
    @ManyToMany(mappedBy = "subCategories")
    private Set<Movie> subCategoryMovies = new HashSet<>();

}
