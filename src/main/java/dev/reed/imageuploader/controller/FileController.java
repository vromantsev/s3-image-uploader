package dev.reed.imageuploader.controller;

import dev.reed.imageuploader.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(consumes = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("bucketName") final String bucketName,
                           @RequestParam("objectKey") final String objectKey,
                           @RequestParam("file") final MultipartFile file) {
        this.fileService.upload(bucketName, objectKey, file);
    }
}
