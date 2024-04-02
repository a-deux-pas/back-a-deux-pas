// package adeuxpas.back.service;

// import com.cloudinary.springboot.dto.ImageModel;
// import com.cloudinary.springboot.service.ImageService;

// import adeuxpas.back.entity.ArticlePicture;
// import adeuxpas.back.repository.ArticlePictureRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import java.util.Map;

// @Service
// public class ImageServiceImpl implements ImageService {
// @Autowired
// private CloudinaryService cloudinaryService;
// @Autowired
// private ArticlePictureRepository articlePictureRepository;

// @Override
// public ResponseEntity<Map> uploadImage(ArticlePicture imageModel) {
// try {
// if (imageModel.getUrl().isEmpty()) {
// return ResponseEntity.badRequest().build();
// }
// ArticlePicture image = new ArticlePicture();
// image.setUrl(imageModel.getUrl());
// if(image.getUrl() == null) {
// return ResponseEntity.badRequest().build();
// }
// articlePictureRepository.save(image);
// return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
// } catch (Exception e) {
// e.printStackTrace();
// return null;
// }

// }
