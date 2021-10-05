package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.applications.requests.v1.FileUploadDto;
import com.nugroho.spring.api.global.UploadDataType;
import com.nugroho.spring.api.utility.Global;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

@Service
public class UploadUseCase {

    @Value("${image.folder}")
    private String imageFolder;

    public void save(FileUploadDto uploadedData) throws IOException {
        var uploadType = UploadDataType.valueOf(uploadedData.getType().toUpperCase());

        switch (uploadType) {
            case IMAGE:
                var path = compressImage(uploadedData.getFile(), 30);
            case CSV:
            case DOC:
            case DOCX:
            case PDF:
        }
    }

    public String compressImage(MultipartFile image, int percentage) throws IOException {
        var uploadFolder = Global.projectDir() + "/" + imageFolder;

        var path = Paths.get(uploadFolder, image.getOriginalFilename());
        Files.write(path, image.getBytes());

        var extension = FilenameUtils.getExtension(image.getOriginalFilename());
        var uuid = UUID.randomUUID().toString() + "-" + Global.dateFormat("yyyyMMddHHmmss");
        var nameCompressedImage = FilenameUtils.getBaseName(image.getName()) + "-" + uuid + "." + extension;
        var bufferedImage = ImageIO.read(path.toFile());
        var resizePercentage = 100 / percentage;
        var outputImage = Scalr.resize(bufferedImage, (bufferedImage.getHeight() / resizePercentage));
        var pathCompressedImage = Paths.get(imageFolder, nameCompressedImage);
        var newImageFile = pathCompressedImage.toFile();

        ImageIO.write(outputImage, extension, newImageFile);
        outputImage.flush();

        return pathCompressedImage.toString();
    }

}
