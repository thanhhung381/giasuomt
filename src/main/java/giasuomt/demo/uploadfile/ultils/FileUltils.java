package giasuomt.demo.uploadfile.ultils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class FileUltils {

	
	public static File convertMultiPathToFile(MultipartFile multipartFile)
	{
		try {
			File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			
			FileOutputStream fileOutputStream=new FileOutputStream(file);
			
			fileOutputStream.write(multipartFile.getBytes());
			
			fileOutputStream.close();
			
			return file;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateNameFile(MultipartFile multipartFile) {
		return  Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ", "_");
	}
	
	
}
