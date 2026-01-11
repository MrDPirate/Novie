package com.example.novie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"category", "subCategories", "trackings", "reviews", "wishlists"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    // movieId
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Year year;

    // CategoryId (main category)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    // Subcategories
    @ManyToMany
    @JoinTable(name = "movie_subcategories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> subCategories = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private Set<Completed> completedSet = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private Set<Wishlist> wishlists = new HashSet<>();

}
