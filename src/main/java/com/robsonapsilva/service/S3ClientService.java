package com.robsonapsilva.service;

import com.robsonapsilva.component.S3ClientComponent;
import com.robsonapsilva.properties.S3ClientProperties;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;

@ApplicationScoped
@RequiredArgsConstructor
public class S3ClientService {

    private final S3ClientProperties s3ClientProperties;
    private final S3ClientComponent s3ClientComponent;

    public PutObjectResponse upload(File file, String key, String mimeType) {
        return s3ClientComponent.upload(s3ClientProperties.bucketName(), key, mimeType, file);
    }

    public ResponseBytes download(String objectKey) {
        return s3ClientComponent.download(s3ClientProperties.bucketName(), objectKey);
    }

}