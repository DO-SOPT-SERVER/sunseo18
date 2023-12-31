package com.example.SecondSeminar.external;

import com.example.SecondSeminar.common.config.AWSConfig;
import com.example.SecondSeminar.external.exception.S3Exception;
import com.example.SecondSeminar.external.exception.S3ExceptionType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Service {
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg",
            "image/webp");
    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;


    private final String bucketName;
    private final AWSConfig awsConfig;


    public S3Service(@Value("${aws-property.s3-bucket-name}") final String bucketName, AWSConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    public String uploadImage(String directoryPath, MultipartFile image) throws IOException {

        validateExtension(image);
        validateFileSize(image);

        final String key = directoryPath + generateImageFileName();
        final S3Client s3Client = awsConfig.getS3Client();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
        s3Client.putObject(request, requestBody);
        return key;
    }

    public void validateExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new S3Exception(S3ExceptionType.UNSUPPORTED_EXTENSION);
        }
    }

    private void validateFileSize(MultipartFile image) {
        if (image.getSize() > MAX_FILE_SIZE) {
            throw new S3Exception(S3ExceptionType.TOO_BIG_FILE_SIZE);
        }
    }

    public void deleteImage(String key) throws IOException {
        final S3Client s3Client = awsConfig.getS3Client();

        s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                builder.bucket(bucketName)
                        .key(key)
                        .build()
        );
    }

    public String generateImageFileName() {
        return UUID.randomUUID().toString();
    }
}
