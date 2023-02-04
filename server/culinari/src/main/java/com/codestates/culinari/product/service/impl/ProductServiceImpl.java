package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.global.search.SearchFilter;
import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductLikeDto;
import com.codestates.culinari.product.dto.response.ProductResponseToPage;
import com.codestates.culinari.product.dto.response.ProductResponse;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductLike;
import com.codestates.culinari.product.repository.ProductLikeRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProfileRepository profileRepository;
    private final ProductLikeRepository productLikeRepository;
    private final SearchFilter searchFilter;

    //찜 조회
    @Transactional(readOnly = true)
    @Override
    public Page<ProductLikeDto> readProductLike(CustomPrincipal principal, Pageable pageable){
        Page<ProductLikeDto> productLikePage = productLikeRepository.findAllByProfileId(principal.profileId(), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending())).map(ProductLikeDto::from);
        return productLikePage;
    }

    //찜 등록
    @Override
    public void createProductLike(Long productId, CustomPrincipal principal){
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다."));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        ProductLike productLike = productLikeRepository.findByProductIdAndProfileId(productId, principal.profileId());
        if(productLike != null){
            throw new BusinessLogicException(ExceptionCode.PRODUCT_LIKE_IS_EXIST);
        }
        else
        productLikeRepository.save(ProductLike.of(profile, product));
    }

    //찜 삭제
    @Override
    public void deleteProductLike(Long productId, CustomPrincipal principal){
        productLikeRepository.deleteByProductId(productId);
    }

    //TODO get/read 는 서비스 내부에 entity 호출하여 dto를 리턴해주는 메소드를 만들어서 사용
    //ID 상품 조회
    @Transactional(readOnly = true)  //상품 후기 문의
    public Product findProduct(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse readProductWithCS(Long productId){
        return ProductResponse.from(findProduct(productId));
    }

    //통합 검색 (Name, Seller, Brand)
    @Transactional(readOnly = true)
    @Override
    public Page<ProductDto> readProductWithKeyWord(String keyWord, Pageable pageable) {
        return productRepository.findAllByNameContainingOrBrandContainingOrSellerContaining(keyWord,keyWord,keyWord, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending())).map(ProductDto::from);
//        return null;
    }
    //신상품 조회
    @Override
    public Page<ProductDto> readProductWithSortedType(String sortedType, String filter, Pageable pageable) throws UnsupportedEncodingException {
        if(filter != null){
            HashMap<String, String> filterMap = searchFilter.hashFilterMap(filter);
            String category = filterMap.get("category");
            String brand = filterMap.get("brand");

            List<String> categoryList = searchFilter.listFilter(category);
            List<String> brandList = searchFilter.listFilter(brand);

            if(sortedType.equals("lower"))
                return productRepository.findAllWithSortAndFilter(categoryList,brandList,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price")))
                        .map(ProductDto::from);
            else if(sortedType.equals("higher"))
                return productRepository.findAllWithSortAndFilter(categoryList,brandList,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending()))
                        .map(ProductDto::from);
            return productRepository.findAllWithSortAndFilter(categoryList,brandList,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()))
                    .map(ProductDto::from);
        } else {
            if(sortedType.equals("lower"))
                return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price")))
                        .map(ProductDto::from);
            else if(sortedType.equals("higher"))
                return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending()))
                        .map(ProductDto::from);
        }
        return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()))
                .map(ProductDto::from);

    }
    //베스트 조회
    @Override
    public Page<ProductDto> readBestProductWithSortedType(String sortedType, String filter, Integer frequency, Pageable pageable) throws UnsupportedEncodingException {
        if(filter != null){
            HashMap<String, String> filterMap = searchFilter.hashFilterMap(filter);
            String category = filterMap.get("category");
            String brand = filterMap.get("brand");

            List<String> categoryList = searchFilter.listFilter(category);
            List<String> brandList = searchFilter.listFilter(brand);

            if(sortedType.equals("lower"))
                return productRepository.findBestProducts(categoryList,brandList,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price")))
                        .map(ProductDto::from);
            else if(sortedType.equals("higher"))
                return productRepository.findBestProducts(categoryList,brandList,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending()))
                        .map(ProductDto::from);
            return productRepository.findBestProducts(categoryList,brandList,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()))
                    .map(ProductDto::from);
        } else {
            if(sortedType.equals("lower"))
                return productRepository.findBestProducts(null,null,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price")))
                        .map(ProductDto::from);
            else if(sortedType.equals("higher"))
                return productRepository.findBestProducts(null,null,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending()))
                        .map(ProductDto::from);
        }
        return productRepository.findBestProducts(null,null,frequency,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()))
                .map(ProductDto::from);
    }

    //카테고리 조회
    @Transactional(readOnly = true)
    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, Pageable pageable){
        if(categoryCode.length() <=3 && sortedType.equals(("lower"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price"))).map(ProductDto::from);
        if(categoryCode.length() <=3 && sortedType.equals(("higher"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending())).map(ProductDto::from);
        if(categoryCode.length() <=3 && sortedType.equals(("newest"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending())).map(ProductDto::from);
        if(categoryCode.length() >3 && sortedType.equals(("lower"))) return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price"))).map(ProductDto::from);
        if(categoryCode.length() >3 && sortedType.equals(("higher"))) return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending())).map(ProductDto::from);
        else return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending())).map(ProductDto::from);
    }

    @Override
    public Page<ProductResponseToPage> readFrequentOrderProduct(Integer searchMonths, Integer frequency, Pageable pageable, CustomPrincipal principal) {
        return productRepository.findAllFrequentOrderProduct(LocalDateTime.now().minusMonths(searchMonths), frequency, principal.profileId(), pageable).map(ProductResponseToPage::from);
    }
}
