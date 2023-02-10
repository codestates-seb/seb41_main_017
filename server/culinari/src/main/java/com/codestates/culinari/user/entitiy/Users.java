package com.codestates.culinari.user.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles = new ArrayList<>();

    @ManyToOne
    private Profile profile;

    public static Users of(Long id, String username, String password, List<UserRole> userRoles, Profile profile) {
        return new Users(id, username, password, userRoles, profile);
    }

    public void addUserRole(UserRole userRole){
        this.userRoles.add(userRole);
    }

    public void updatePassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
