package com.example.novie.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class UserProfile {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;
    private String imageType;

    @Column(name = "image_data", columnDefinition = "BYTEA")
    private byte[] imageData;



    @JsonIgnore
    @OneToOne(mappedBy = "userProfile",fetch = FetchType.LAZY)
    private User user;


}
