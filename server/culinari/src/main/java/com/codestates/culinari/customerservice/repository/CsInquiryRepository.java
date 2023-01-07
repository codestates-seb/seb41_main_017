package com.codestates.culinari.customerservice.repository;

import com.codestates.culinari.customerservice.entity.CsInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsInquiryRepository extends JpaRepository<CsInquiry, Long> {
}