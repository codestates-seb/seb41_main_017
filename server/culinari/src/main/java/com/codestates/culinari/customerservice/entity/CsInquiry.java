package com.codestates.culinari.customerservice.entity;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CsInquiry extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 65554)
    private String content;

    @ManyToOne(optional = false)
    private Profile profile;

}
