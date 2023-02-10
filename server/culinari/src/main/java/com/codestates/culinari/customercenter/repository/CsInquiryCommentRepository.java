package com.codestates.culinari.customercenter.repository;

import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.customercenter.repository.querydsl.CsInquiryCommentCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CsInquiryCommentRepository extends JpaRepository<CsInquiryComment, Long>, CsInquiryCommentCustom {

//    @Query(value = "select c from CsInquiryComment c where c.id = :commentId and c.profile.id = :profileId")
    Optional<CsInquiryComment> findByIdAndProfileId(Long commentId, Long profileId);
}
