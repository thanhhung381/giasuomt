package giasuomt.demo.job.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.dto.UpdateJobResultDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.service.IJobService;
import giasuomt.demo.task.dto.UpdateLessonDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.uploadfile.service.IRetainedImgsIdentificationAwsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/job")
public class JobController extends GenericController<SaveJobDto,Job, Long> {
	
	@Autowired
	private IJobService iJobService;
	
	
	@Autowired
	private IRetainedImgsIdentificationAwsService iRetainedImgsIdentificationService;

	@Value("${amazon.retainedimgsidentificationURL}")
	private String retainedImgsIdentificationURL;
	
	@PutMapping("/updateJobResult")
	public ResponseEntity<Object> updateJobResult(@RequestBody UpdateJobResultDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Job updatedJob = iJobService.updateJobResult(dto);
		
		return ResponseHandler.getResponse(updatedJob, HttpStatus.OK);
	}


	@GetMapping("/findallRetainedImgsIdentificationimg")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iRetainedImgsIdentificationService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createRetainedImgsIdentificationimg{id}")
	public ResponseEntity<Object> uploadPublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iRetainedImgsIdentificationService.findAll();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iRetainedImgsIdentificationService.uploadImageToAmazon(file,
						id + "RetainedImgsIdentification" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iRetainedImgsIdentificationService
					.findAllByNameContainer(id + "RetainedImgsIdentification", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iRetainedImgsIdentificationService.uploadImageToAmazon(file,
							id + "RetainedImgsIdentification" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("n") + 1));

					String awsAvatarURL = iRetainedImgsIdentificationService.uploadImageToAmazon(file,
							id + "RetainedImgsIdentification" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}

		}

	}

	@PostMapping("/createRetainedImgsIdentificationimgs")
	public ResponseEntity<Object> uploadPublicImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") String id) throws IOException {

		try {

			int count = 0;
			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iRetainedImgsIdentificationService.uploadImageToAmazon(file,
							id + "RetainedImgsIdentification" + String.valueOf(count));

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

	@DeleteMapping("/deleteRetainedImgsIdentificationimg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iRetainedImgsIdentificationService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iRetainedImgsIdentificationService.deleteByFileNameAndID(retainedImgsIdentificationURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@PutMapping("/UpdateRetainedImgsIdentificationimg/{nameFile}")
	public ResponseEntity<Object> UpdatePrivateRetainImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iRetainedImgsIdentificationService.updateRetainedImgsIdentificationToAmazon(file,
					nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}
	
}
