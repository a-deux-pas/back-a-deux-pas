package adeuxpas.back.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "erikaadeuxpas");
        valuesMap.put("api_key", "862338892614253");
        valuesMap.put("api_secret", "-nwEGHyFrRGMH1izCfW57eAHC00");
        cloudinary = new Cloudinary(valuesMap);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<String, Object> result = cloudinary.uploader().upload(file,
                ObjectUtils.asMap(
                        "transformation", new Transformation()
                                .gravity("face")
                                .height(400).width(400).crop("thumb")
                                .fetchFormat("webp")));
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
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
