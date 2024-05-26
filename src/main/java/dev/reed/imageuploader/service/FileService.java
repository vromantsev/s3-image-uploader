package dev.reed.imageuploader.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Client s3Client;

    public void upload(String bucketName, String objectKey, MultipartFile file) {
        try {
            this.s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(Objects.requireNonNull(bucketName, "Parameter [bucketName] must not be null!"))
                            .key(Objects.requireNonNull(objectKey, "Parameter [objectKey] must not be null!"))
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );
            log.info("Successfully uploaded object with key '{}' to bucket '{}'", file.getOriginalFilename(), bucketName);
        } catch (IOException e) {
            throw new RuntimeException("Cannot upload a file '%s' to bucket '%s'".formatted(file.getOriginalFilename(), bucketName), e);
        }
    }
}
