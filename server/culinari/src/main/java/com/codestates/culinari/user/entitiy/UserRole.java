package com.codestates.culinari.user.entitiy;

import com.codestates.culinari.user.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @JsonBackReference
    @ManyToOne
    private Users user;

    public static UserRole of(RoleType roleType, Users user) {
        return new UserRole(null, roleType, user);
    }
}
