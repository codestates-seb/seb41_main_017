package com.codestates.culinari.customercenter.repository;

import com.codestates.culinari.customercenter.entity.CsInquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsInquiryRepository extends JpaRepository<CsInquiry, Long> {
    Page<CsInquiry> findAllByProfileId(Long profileId, Pageable pageable);
}
