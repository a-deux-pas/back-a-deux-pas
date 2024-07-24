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
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import java.nio.file.Path;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {

        Map<String, String> cloudinaryCredentials = new HashMap<>();
        cloudinaryCredentials.put("cloud_name", cloudName);
        cloudinaryCredentials.put("api_key", apiKey);
        cloudinaryCredentials.put("api_secret", apiSecret);

        cloudinary = new Cloudinary(cloudinaryCredentials);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> upload(String publicId, MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        try {
            String safePublicId = sanitizePublicId(publicId);
            Transformation<?> transformation = getTheRightTransformation(safePublicId);
            Map<String, Object> result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap(
                            "transformation", transformation,
                            "public_id", publicId));
            return result;
        } catch (Exception e) {
            logger.error("Error uploading file to Cloudinary", e);
            throw new IOException("Failed to upload file to Cloudinary: " + e.getMessage(), e);
        } finally {
            if (file != null && !file.delete()) {
                logger.warn("Failed to delete temporary file: " + file.getAbsolutePath());
            }
        }
    }

    // TO DO :: to see if I can implement it once I get the ad deleting process
    // public Map delete(String id) throws IOException {
    // return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    // }

    private File convert(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString();
        Path tempDir = Files.createTempDirectory("upload-");
        Path filePath = tempDir.resolve(fileName);
        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(multipartFile.getBytes());
        }
        return filePath.toFile();
    }

    private String sanitizePublicId(String publicId) {
        return publicId.replaceAll("[^a-zA-Z0-9_-]", "");
    }

    private Transformation<?> getTheRightTransformation(String publicId) {
        if (publicId.startsWith("profilePicture")) {
            return new Transformation<>()
                    .gravity("face")
                    .height(208).width(208).crop("fill").quality("auto")
                    .radius("max").chain()
                    .fetchFormat("webp");
        } else {
            return new Transformation<>()
                    .height(400).width(600).crop("fill").quality("auto")
                    .fetchFormat("webp");
        }
    }
}
