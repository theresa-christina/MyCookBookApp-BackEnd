package com.passionproject.cookbook.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.passionproject.cookbook.models.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class PhotoStorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    private AmazonS3 s3Client;
    private PhotoDataService svc;

    @Autowired
    public PhotoStorageService(AmazonS3 s3Client, PhotoDataService svc) {
        this.s3Client = s3Client;
        this.svc = svc;
    }

    // Add Photo in RDS Table THEN add Photo to S3
    public Long uploadPhoto(MultipartFile file) {
        Long photoId = svc.addPhoto(new Photo()).getId();

        File fileObj = convertMultiPartFileToFile(file);
        String filename = photoId.toString();
        s3Client.putObject(
                new PutObjectRequest(bucketName, filename, fileObj)
        );
        fileObj.delete(); // delete so it doesn't keep adding it
        return photoId;
    }

    // Use %20 for spaces in filenames when testing via Insomnia, Postman, etc.
    // Delete from AWS S3 and from VideoDataRepository
    public Long deleteFile(String filename) {
        s3Client.deleteObject(bucketName, filename);
        Long photoId = Long.parseLong(filename);
        svc.deletePhoto(photoId);
        return photoId;
    }

    public byte[] downloadPhotoByName(String filename) {
        S3Object s3Object = s3Client.getObject(bucketName, filename);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] downloadPhotoByKey(String keyName) {
        GetObjectRequest req = new GetObjectRequest(this.bucketName, keyName);
        S3Object s3Object = s3Client.getObject(req);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }

        return convertedFile;
    }
}
