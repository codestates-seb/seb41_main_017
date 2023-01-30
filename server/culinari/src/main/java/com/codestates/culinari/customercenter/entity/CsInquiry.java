package com.codestates.culinari.customercenter.entity;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
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
public class CsInquiry extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 65554)
    private String content;

    @Column(nullable = false, length = 20)
    private String category;

    @ManyToOne(optional = false)
    private Profile profile;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ProcessStatus processStatus;

    public static CsInquiry of(Long id, String title, String content, String category, Profile profile, ProcessStatus processStatus) {
        return new CsInquiry(id, title, content,category ,profile, processStatus);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateCategory(String category) {
        this.category = category;
    }
}
