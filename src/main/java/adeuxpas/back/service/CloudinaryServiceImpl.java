package adeuxpas.back.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import adeuxpas.back.config.SecureTempFileConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import java.nio.file.Path;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryServiceImpl.class);
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
            return cloudinary.uploader().upload(file,
                    ObjectUtils.asMap(
                            "transformation", transformation,
                            "public_id", publicId));
        } catch (IOException e) {
            logger.error("Error uploading file to Cloudinary", e);
            throw new IOException("Failed to upload file to Cloudinary: " + e.getMessage(), e);
        } finally {
            if (file != null && !file.delete()) {
                logger.warn("Failed to delete temporary file: " + file.getAbsolutePath());
            }
        }
    }

    // This method takes a MultipartFile (typically a file uploaded via an HTTP
    // request)
    // creates a temporary file with a unique name in a temporary directory
    // writes the content of the uploaded file to it, and returns a reference to
    // this temporary file.
    // This approach is useful for securely handling uploaded files
    // before further processing them (in this case, before uploading them to
    // Cloudinary)
    private File convert(MultipartFile multipartFile) throws IOException {
        Path tempFile = SecureTempFileConfig.createSecureTempFile("upload-", ".tmp");
        try {
            Files.write(tempFile, multipartFile.getBytes());
            return tempFile.toFile();
        } catch (IOException e) {
            SecureTempFileConfig.safelyDeleteFile(tempFile);
            throw e;
        }
    }

    // Defines what transformation to apply on the file depending whether
    // it is a new user's profile picture or a new article's pictures
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

    private String sanitizePublicId(String publicId) {
        return publicId.replaceAll("[^a-zA-Z0-9_-]", "");
    }

    // TO DO :: to see if I can implement it once I get the ad deleting process
    // public Map delete(String id) throws IOException {
    // return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    // }
}
