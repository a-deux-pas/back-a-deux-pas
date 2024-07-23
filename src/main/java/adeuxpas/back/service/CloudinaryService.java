package adeuxpas.back.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    Map<String, Object> upload(String publicId, MultipartFile multipartFile) throws IOException;
}
