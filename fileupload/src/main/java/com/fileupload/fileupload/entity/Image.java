package com.fileupload.fileupload.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "imagedb")
@Data
public class Image {
    @Id
    private String id;
    private String name;
    private byte[] data;

    
}
