package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.applications.requests.v1.FileUploadDto;
import com.nugroho.spring.api.global.Config;
import com.nugroho.spring.api.global.UploadDataType;
import com.nugroho.spring.api.utility.Global;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

@Service
public class UploadUseCase {

    @Value("${image.folder}")
    private String imageFolder;

    public Path save(FileUploadDto uploadedData) throws IOException, InterruptedException {
        var file = uploadedData.getFile();
        var uploadFolder = Global.projectDir() + "/" + imageFolder + "/original";
        var path = Paths.get(uploadFolder, file.getOriginalFilename());

        Files.write(path, file.getBytes());

        return path;
    }

    @Async(Config.BEAN_THREAD_EXECUTOR)
    public void compressImage(Path path, String extension, int percentage) throws IOException, InterruptedException {

        var compressedFolder = Global.projectDir() + "/" + imageFolder + "/compressed";
        var uuid = UUID.randomUUID().toString() + "-" + Global.dateFormat("yyyyMMddHHmmss");
        var nameCompressedImage = path.getFileName() + "-" + uuid + "." + extension;
        var bufferedImage = ImageIO.read(path.toFile());
        var resizePercentage = 100 / percentage;
        var outputImage = Scalr.resize(bufferedImage, (bufferedImage.getHeight() / resizePercentage));
        var pathCompressedImage = Paths.get(compressedFolder, nameCompressedImage);
        var newImageFile = pathCompressedImage.toFile();

        ImageIO.write(outputImage, extension, newImageFile);
        outputImage.flush();
    }

}
