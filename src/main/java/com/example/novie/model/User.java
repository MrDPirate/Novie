package com.example.novie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"password","userProfile"})
@Entity
@Table(name="users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    @Column(unique = true)
    private String emailAddress;


    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private String password;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    private UserProfile userProfile;



    @JsonIgnore
    public String getPassword(){
        return password;
    }


}