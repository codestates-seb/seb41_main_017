package com.codestates.culinari.product.repository.querydsl;

import com.codestates.culinari.product.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepositoryCustom{

//    Page<Product> findAllWithSortAndFilter(String category, String brand, Pageable pageable) throws UnsupportedEncodingException;

    Page<Product> findAllWithSortAndFilter(List<String> category, List<String> brand, Pageable pageable) throws UnsupportedEncodingException;

    Page<Product> findAllFrequentOrderProduct(LocalDateTime createdAfterDateTime, Integer frequency, Long profileId, Pageable pageable);

    Page<Product> findBestProducts(List<String> category, List<String> brand, Integer frequency, Pageable pageable);

    Long countFrequentOrderProduct(LocalDateTime createdAfterDateTime, Integer frequency, Long profileId);
}
