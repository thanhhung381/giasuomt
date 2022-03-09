package giasuomt.demo.uploadfile.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.uploadfile.model.AvatarAws;
import giasuomt.demo.uploadfile.model.BillIamgeAws;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.uploadfile.service.IBillImageAwsService;
import lombok.AllArgsConstructor;
import lombok.experimental.PackagePrivate;

@RequestMapping("/api/billImage")
@RestController
@AllArgsConstructor
public class BillImageAwsController {
	
	
	private IBillImageAwsService iBillImageService;
	

	
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		List<BillIamgeAws> fileEntities = iBillImageService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			BillIamgeAws billIamgeAws=iBillImageService.uploadImageToAmazon(file);

			return ResponseHandler.getResponse(billIamgeAws, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}
	
	@DeleteMapping("/delete/{nameFile}/{id}")
	public ResponseEntity<Object> delete(@PathVariable("nameFile") String nameFile,@PathVariable("id") Long id) {
		
		String url="https://s3.ap-southeast-1.amazonaws.com/avatargsomt/";
		 
		if (!iBillImageService.checkExistIdOfT(id) || !iBillImageService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url", HttpStatus.BAD_REQUEST);
		
		iBillImageService.deleteByFileNameAndID(url+nameFile,id);
		
		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
		
	
		 
		if (!iBillImageService.checkExistIdOfT(id) )
			return ResponseHandler.getResponse("Don't have any id", HttpStatus.BAD_REQUEST);
		
		iBillImageService.deleteById(id);
		
		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	
	
}
