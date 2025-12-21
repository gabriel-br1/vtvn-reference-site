package com.emerald.vitruvian.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    // uploads image Multipart file to a specified upload directory using the image's filename
    public void uploadFile(String uploadDir, String fileName, MultipartFile image  ) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try{
            InputStream inputStream = image.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public void deleteFile(String uploadDir, String fileName) throws IOException {
        Path filePath = Path.of(uploadDir, fileName);
        if(Files.exists(filePath)){
            Files.delete(filePath);
        }
    }

}
