package com.robsonapsilva.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;

@ApplicationScoped
@RequiredArgsConstructor
public class S3ClientComponent {

    @Getter
   private final S3Client s3Client;

    public  PutObjectResponse upload(String bucketName, String filename, String mimeType, File file) throws S3Exception{
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .contentType(mimeType)
                .build();
         return getS3Client().putObject(putObjectRequest, RequestBody.fromFile(file));
    }

    public ResponseBytes<GetObjectResponse> download(String bucketName, String objectKey) throws S3Exception {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        return getS3Client().getObjectAsBytes(getObjectRequest);
    }
    

}
