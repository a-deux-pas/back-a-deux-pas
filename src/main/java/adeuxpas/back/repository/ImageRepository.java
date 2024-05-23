package adeuxpas.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adeuxpas.back.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByOrderById();
}
