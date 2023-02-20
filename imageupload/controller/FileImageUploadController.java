package com.example.imageupload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.imageupload.entity.ImageAttachment;
import com.example.imageupload.repository.ImageUploadRepo;

@RestController
public class FileImageUploadController {

	@Autowired
	private ImageUploadRepo imageUploadRepo;

	@PostMapping("/Upload-file")
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(file.getOriginalFilename());
		System.err.println(file.getContentType());
		System.out.println(file.getSize());
		String location = "D:\\JavaTrainning\\Imageupload\\imageupload\\imageupload\\src\\main\\resources\\static\\image";

		// CreatingThe entityobject,and setting the required value
		ImageAttachment imageAttachment = new ImageAttachment();
		imageAttachment.setImagename(file.getOriginalFilename());
		imageAttachment.setImagePath(location);
		imageUploadRepo.save(imageAttachment);

		// this is the localpath of where the image is stored
		String path = "D:\\JavaTrainning\\Imageupload\\imageupload\\imageupload\\src\\main\\resources\\static\\image";
		Files.copy(file.getInputStream(), Paths.get(path + File.separator + file.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);

		return "Successfully Image uploaded";
	}
     //this method is used converting the image to Base64 String format
	@PostMapping("/Convertbase64")
	public String convertBase64(@RequestParam("filename") String fileName) throws IOException {

		String expectedPath = "D:\\JavaTrainning\\Imageupload\\imageupload\\imageupload\\src\\main\\resources\\static\\image\\"
				+ fileName;

		File file = new File(expectedPath);

		if (!file.exists()) {
			throw new FileNotFoundException(fileName + " is not exist");
		}

		FileInputStream istream = new FileInputStream(file);
		byte[] arr = new byte[(int)file.length()];
		istream.read(arr);
		istream.close();

		// converting the ByteArray to Base64 String format
		String base64File = Base64.getEncoder().encodeToString(arr);

		return base64File;

	}
}
