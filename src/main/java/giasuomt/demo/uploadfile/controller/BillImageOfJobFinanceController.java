package giasuomt.demo.uploadfile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import giasuomt.demo.uploadfile.service.IBillImageAwsService;

@RequestMapping("/api/BillImage")
@RestController
public class BillImageOfJobFinanceController {

	@Autowired
	private IBillImageAwsService iBillImageAwsService;
	
	

	@Value("${amazon.billImageURL}")
	private String BillImageURL;

	@GetMapping("/findallBillImage")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iBillImageAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createOrUpdateBillImage/{id}")
	public ResponseEntity<Object> uploadOrUpdate(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id)
			throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String billImagerURL = iBillImageAwsService.uploadImageToAmazon(file, id);

			return ResponseHandler.getResponse(billImagerURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/deleteBillImage/{nameFile}/")
	public ResponseEntity<Object> delete(@PathVariable("nameFile") String nameFile) {

		if (!iBillImageAwsService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iBillImageAwsService.deleteByFileNameAndID(BillImageURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
}
