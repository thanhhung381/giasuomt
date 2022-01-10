package giasuomt.demo.uploadfile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.simpleworkflow.model.RespondActivityTaskCanceledRequest;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.uploadfile.model.Avatar;
import giasuomt.demo.uploadfile.model.AwsAvatar;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;

import giasuomt.demo.uploadfile.service.IAwsAvartarService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/storage")
@AllArgsConstructor
public class AWSControllerAvatar {
	
	private IAwsAvartarService iAwsAvartarService;
	
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		List<AwsAvatar> fileEntities = iAwsAvartarService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			AwsAvatar awsAvatar=iAwsAvartarService.uploadImageToAmazon(file);

			return ResponseHandler.getResponse(awsAvatar, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

}