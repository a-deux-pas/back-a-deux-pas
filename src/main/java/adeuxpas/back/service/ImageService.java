package adeuxpas.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import adeuxpas.back.entity.Image;
import adeuxpas.back.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<Image> list() {
        return imageRepository.findByOrderById();
    }

    public Optional<Image> getOne(Long id) {
        return imageRepository.findById(id);
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }
}
