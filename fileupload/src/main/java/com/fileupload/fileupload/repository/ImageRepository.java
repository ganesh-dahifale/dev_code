package com.fileupload.fileupload.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fileupload.fileupload.entity.Image;

public interface ImageRepository extends MongoRepository<Image,Integer>{

    Optional<Image> findByName(String name);
    
}
