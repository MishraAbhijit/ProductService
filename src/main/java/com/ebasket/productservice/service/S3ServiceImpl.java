package com.ebasket.productservice.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class S3ServiceImpl {

    @Autowired
    private AmazonS3 s3Client;

    public void uploadImage(MultipartFile multipartFile, String bucketName, String fileName) throws IOException {
        File file = File.createTempFile("temp", multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
        PutObjectResult putObjectResult = s3Client.putObject(putObjectRequest);
        log.info("S3 Result:: "+putObjectResult);
        file.delete();
    }

    public String getPresignedUrl(String bucketName,String key, int minutesToExpire) {
        Date expiration = new Date(System.currentTimeMillis() + minutesToExpire * 60 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        return s3Client.generatePresignedUrl(request).toString();
    }
}
