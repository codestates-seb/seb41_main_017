package com.codestates.culinari.global.file;

import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Component
public class FileStore {
    @Value("C:/Users/J/Pictures/Screenshots")
    private String fileDirPath;


    public ProductReviewImage storeReviewImage(MultipartFile multipartFile) throws IOException{
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalImageName = multipartFile.getOriginalFilename();
        String storeImageName = createStoreImageName(originalImageName);
        multipartFile.transferTo(new File(createReviewPath(storeImageName)));

        return ProductReviewImage.builder()
                .originImageName(originalImageName)
                .storePath(storeImageName)
                .build();

    }
    //이미지 여러 장 저장
    public List<ProductReviewImage> storeReviewImages(List<MultipartFile> multipartFiles) throws  IOException{
        List<ProductReviewImage> productReviewImages = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                productReviewImages.add(storeReviewImage(multipartFile));
            }
        }
        return productReviewImages;
    }

    //리뷰 이미지 저장 경로
    public String createReviewPath(String storeImageName){
        String viaPath = "/images/review/";
        return fileDirPath + viaPath + storeImageName;
    }

    //이미지 네임 중복 방지
    private String createStoreImageName(String originalImageName){
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalImageName);
        return uuid + ext;
    }

    //관리의 용이성을 위한 확장자 추출
    private String extractExt(String originalImageName){
        int idx = originalImageName.lastIndexOf(".");
        return originalImageName.substring(idx);
    }
}
