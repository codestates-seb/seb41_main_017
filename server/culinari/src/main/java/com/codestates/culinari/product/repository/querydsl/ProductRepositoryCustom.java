package com.codestates.culinari.product.repository.querydsl;

import com.codestates.culinari.product.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ProductRepositoryCustom{

//    Page<Product> findAllWithSortAndFilter(String category, String brand, Pageable pageable) throws UnsupportedEncodingException;

    Page<Product> findAllWithSortAndFilter(List<String> category, List<String> brand, Pageable pageable) throws UnsupportedEncodingException;
}
