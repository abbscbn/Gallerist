package com.abbascoban.gallerist.service.impl;

import com.abbascoban.gallerist.service.IFileStorageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService implements IFileStorageService {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File cannot be empty");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(UPLOAD_DIR + fileName);

        file.transferTo(dest);

        // DB'ye kaydedilecek path
        return "/uploads/" + fileName;
    }
}
