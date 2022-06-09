package giasuomt.demo.staff.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.staff.dto.SaveStaffDto;
import giasuomt.demo.staff.dto.UpdateAvatarStaff;
import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.staff.service.IStaffService;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/staff")
@RestController
public class StaffController extends GenericController<SaveStaffDto, Staff, Long> {
	
	@Autowired
	private IStaffService iStaffService;
	
	
	@Autowired
	private IAvatarAwsService iAvatarAwsService;
	
	@Value("${amazon.avatarURL}")
	private String urlAvatar;
	
	

	
	
	
	
	
	@GetMapping("/findAllStaffAvatars")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iAvatarAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}
	
	
	@PostMapping("/createOrUpdateStaffAvatar/{id}")
	public ResponseEntity<Object> uploadOrUpdate(@RequestParam("file") MultipartFile file,@PathVariable("id") String id)  {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		
		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL=iAvatarAwsService.uploadImageToAmazon(file,id);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}
	
	@DeleteMapping("/deleteStaffImg/{nameFile}/")
	public ResponseEntity<Object> delete(@PathVariable("nameFile") String nameFile) {
		
		
		 
		
		if (!iAvatarAwsService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);
		
		iAvatarAwsService.deleteByFileNameAndID(urlAvatar+nameFile);
		
		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	@PostMapping("/createOrUpdateStaffImgs")
	public ResponseEntity<Object> uploadOrUpdate(@RequestParam("files") MultipartFile[] files) throws IOException {

		

		try {
			for (MultipartFile file : files) {
				
				String filename = StringUtils.cleanPath(file.getOriginalFilename());
				
				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL=iAvatarAwsService.uploadImageToAmazon(file,filename);

				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
			
			return ResponseHandler.getResponse("Upload files successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse("Cant not upload", HttpStatus.BAD_REQUEST);
		
		
		

	}
}
