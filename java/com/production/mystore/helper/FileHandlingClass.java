package com.production.mystore.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileHandlingClass {
	
	private final static String uploadDir="C:\\Users\\digvi\\OneDrive\\Desktop\\eccomers website dj\\mystore\\mystore\\src\\main\\resources\\static\\images";
	
	public static boolean saveFileInFolder(MultipartFile file) throws IOException {
		boolean flag=false;
		try {
			InputStream is = file.getInputStream();
			byte [] data = new byte[is.available()];
			is.read(data);
			FileOutputStream fos = new FileOutputStream(uploadDir+File.separator+file.getOriginalFilename());
			fos.write(data);
			flag= true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;	
	}
}
