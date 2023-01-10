package com.codestates.culinari.customerservice.repository;

import com.codestates.culinari.customerservice.entity.CsNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsNoticeRepository extends JpaRepository<CsNotice, Long> {
}