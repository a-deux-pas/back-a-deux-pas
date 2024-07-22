package adeuxpas.back.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
// TO DO : :checker si ce controller sert encore à qqchose ?

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import adeuxpas.back.entity.Image;
import adeuxpas.back.service.AdService;
import adeuxpas.back.service.CloudinaryService;
import adeuxpas.back.service.ImageService;

@RestController
@RequestMapping("api/cloudinary")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    public CloudinaryController(
            @Autowired CloudinaryService cloudinaryService,
            @Autowired ImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    // TO DO :: check si j'ai besoin de l'objecr IMAGE
    @PostMapping("/upload/{type}")
    @ResponseBody
    public ResponseEntity<Object> upload(@PathVariable String type,
            @RequestParam("multipartFile") MultipartFile multipartFile) {
        try {
            // Logging information about the received file
            System.out.println("Received file with name: " + multipartFile.getOriginalFilename());
            System.out.println("File size: " + multipartFile.getSize() + " bytes");
            System.out.println("File content type: " + multipartFile.getContentType());

            // Check if the uploaded file is a valid image
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return ResponseEntity.badRequest().body("The uploaded file is not a valid image.");
            }

            // Upload the file to Cloudinary
            Map<String, Object> result = cloudinaryService.upload(type, multipartFile);

            // Save information about the uploaded image
            Image image = new Image((String) result.get("original_filename"),
                    (String) result.get("url"),
                    (String) result.get("public_id"));
            imageService.save(image);

            System.out.println("url: " + result.get("url"));

            // Prepare response object with URL
            UploadResponse response = new UploadResponse("Image successfully uploaded.", (String) result.get("url"));
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the uploaded file.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Inner class for response object containing message and image URL
    static class UploadResponse {
        private final String message;
        private final String imageUrl;

        public UploadResponse(String message, String imageUrl) {
            this.message = message;
            this.imageUrl = imageUrl;
        }

        public String getMessage() {
            return message;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    // Optional<Image> imageOptional = imageService.getOne(id);
    // if (imageOptional.isEmpty()) {
    // return new ResponseEntity<>("doesn't exist", HttpStatus.NOT_FOUND);
    // }
    // Image image = imageOptional.get();
    // String cloudinaryImageId = image.getImageId();
    // try {
    // cloudinaryService.delete(cloudinaryImageId);
    // } catch (IOException e) {
    // return new ResponseEntity<>("Failed to delete image from Cloudinary",
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // imageService.delete(id);
    // return new ResponseEntity<>("image has been deleted !", HttpStatus.OK);
    // }

}
