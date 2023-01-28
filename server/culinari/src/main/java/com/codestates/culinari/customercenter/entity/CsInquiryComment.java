package com.codestates.culinari.customercenter.entity;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CsInquiryComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10000)
    private String content;

    @ManyToOne(optional = false)
    private CsInquiry csInquiry;

    @ManyToOne(optional = false)
    private Profile profile;

    public static CsInquiryComment of( String content, CsInquiry csInquiry, Profile profile) {
        return new CsInquiryComment(null, content, csInquiry, profile);
    }
    public void updateContent(String content) {
        this.content = content;
    }
}
