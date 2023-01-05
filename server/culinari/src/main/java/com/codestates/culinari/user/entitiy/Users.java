package com.codestates.culinari.user.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

    // 논의 @ElementCollection
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();//

    @ManyToOne
    private Profile profile;

}

//
// @ElementCollection