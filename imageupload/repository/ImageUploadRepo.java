package com.example.imageupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imageupload.entity.ImageAttachment;

@Repository
public interface ImageUploadRepo extends JpaRepository<ImageAttachment,Integer>
{

}
