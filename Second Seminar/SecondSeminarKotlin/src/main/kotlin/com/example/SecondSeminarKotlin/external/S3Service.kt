package com.example.SecondSeminarKotlin.external

import com.example.SecondSeminarKotlin.common.config.external.AWSConfig
import com.example.SecondSeminarKotlin.exception.BaseCustomException
import com.example.SecondSeminarKotlin.external.exception.S3ExceptionType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*

private const val IMAGE_EXTENSIONS_JPEG = "image/jpeg"
private const val IMAGE_EXTENSIONS_PNG = "image/png"
private const val IMAGE_EXTENSIONS_JPG = "image/jpg"
private const val IMAGE_EXTENSIONS_WEBP = "image/webp"

private const val MAX_FILE_SIZE = 5 * 1024 * 1024L

@Component
class S3Service(
    @Value("\${aws-property.s3-bucket-name}")
    private val bucketName: String,
    private val awsConfig: AWSConfig
) {
    private val IMAGE_EXTENSIONS =
        arrayListOf(IMAGE_EXTENSIONS_JPEG, IMAGE_EXTENSIONS_PNG, IMAGE_EXTENSIONS_JPG, IMAGE_EXTENSIONS_WEBP)

    fun uploadImage(directoryPath: String, image: MultipartFile): String {
        validateExtension(image)
        validateFileSize(image)

        val key = directoryPath + generateImageFileName()
        val s3Client = awsConfig.getS3Client()

        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()

        val requestBody = RequestBody.fromBytes(image.bytes)
        s3Client.putObject(request, requestBody)

        return key
    }

    fun validateExtension(image: MultipartFile) {
        val contentType = image.contentType
        if (!IMAGE_EXTENSIONS.contains(contentType))
            throw BaseCustomException(S3ExceptionType.UNSUPPORTED_EXTENSION)
    }

    fun validateFileSize(image: MultipartFile) {
        if (image.size > MAX_FILE_SIZE)
            throw BaseCustomException(S3ExceptionType.TOO_BIG_FILE_SIZE)
    }

    fun deleteImage(key: String) {
        val s3Client: S3Client = awsConfig.getS3Client()

        s3Client.deleteObject { builder -> builder.bucket(bucketName).key(key).build() }
    }

    fun generateImageFileName(): String {
        return UUID.randomUUID().toString()
    }

}