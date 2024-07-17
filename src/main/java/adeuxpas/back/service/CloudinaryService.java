package adeuxpas.back.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);
    Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", cloudName);
        valuesMap.put("api_key", apiKey);
        valuesMap.put("api_secret", apiSecret);

        cloudinary = new Cloudinary(valuesMap);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> upload(String type, MultipartFile multipartFile) throws IOException {
        logger.info("Received file with name: {}", multipartFile.getOriginalFilename());
        logger.info("File size: {} bytes", multipartFile.getSize());
        logger.info("File content type: {}", multipartFile.getContentType());
        File file = convert(multipartFile);

        try {
            Map<String, Object> result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap(
                            "transformation", new Transformation()
                                    .gravity("face")
                                    .height(400).width(600).crop("fill").quality("auto")
                                    .fetchFormat("webp"),
                            "public_id", type));
            if (!Files.deleteIfExists(file.toPath())) {
                throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
            }
            return result;
        } catch (Exception e) {
            logger.error("Error uploading file to Cloudinary", e);
            throw new IOException("Failed to upload file to Cloudinary: " + e.getMessage(), e);
        }
    }

    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
