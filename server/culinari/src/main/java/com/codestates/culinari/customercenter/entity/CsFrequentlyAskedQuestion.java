package com.codestates.culinari.customercenter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CsFrequentlyAskedQuestion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 65554)
    private String content;

    @Column(nullable = false, length = 20)
    private String category;

    public static CsFrequentlyAskedQuestion of(String title, String content, String category) {
        return new CsFrequentlyAskedQuestion(null, title, content, category);
    }
}
