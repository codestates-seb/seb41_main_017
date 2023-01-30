package com.codestates.culinari.global.file;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Uploader {
    private final AmazonS3 amazonS3;

    private static final int CAPACITY_LIMIT_BYTE = 1024 * 1024 * 10;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public List<String> uploads(List<MultipartFile> multipartFiles) throws IOException {
        List<String> imgUrlList = new ArrayList<>();
        if (multipartFiles.isEmpty()) {
            return imgUrlList;
        }
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                imgUrlList.add(upload(multipartFile));
            }
        }
        return imgUrlList;
    }


    public String upload(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename(); // s3에 저장되는 파일이름 중복안되게 하기
        String ext = extractExt(s3FileName);
        String contentType = "";

        //파일크기 용량제한 넘으면 예외 던지기
        if (multipartFile.getSize() > CAPACITY_LIMIT_BYTE) {
            throw new RuntimeException("이미지가 10M 제한을 넘어갑니다.");
        }

        switch (ext) {
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "txt":
                contentType = "text/plain";
                break;
            case "csv":
                contentType = "text/csv";
                break;
        }

        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentType(contentType);
            objMeta.setContentLength(multipartFile.getInputStream().available()); //파일의 사이즈 S3에 알려주기
            amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta); //S3 API 메소드 이용해서 S3에 파일 업로드
//            return amazonS3.getUrl(bucket, s3FileName).toString(); //getUrl로 S3에 업로드된 사진 URL 가져오기
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    //로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    /*    */
    /*   로컬에 파일 저장하기   *//*
    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return Optional.empty();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFileName(originalFilename);
        File file = new File(fileDir + storeFilename);
        multipartFile.transferTo(file);
        return Optional.of(file);
    }*/

    /*  파일 이름이 이미 업로드된 파일들과 겹치지 않게 UUID를 사용한다.   */
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    /*  사용자가 업로드한 파일에서 확장자를 추출한다.   */
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}