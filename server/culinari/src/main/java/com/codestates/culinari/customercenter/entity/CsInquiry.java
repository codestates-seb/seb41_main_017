package com.codestates.culinari.customercenter.entity;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ProcessStatus processStatus;

    public static CsInquiry of(Long id, String title, String content, Profile profile, ProcessStatus processStatus) {
        return new CsInquiry(id, title, content, profile, processStatus);
    }
}