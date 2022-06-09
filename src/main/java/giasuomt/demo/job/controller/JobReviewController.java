package giasuomt.demo.job.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.job.dto.SaveJobReviewDto;
import giasuomt.demo.job.model.JobReview;
import giasuomt.demo.uploadfile.service.IFeedBackImageAwsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/jobReview")
public class JobReviewController extends GenericController<SaveJobReviewDto, JobReview, Long> {
	@Autowired
	private IFeedBackImageAwsService iFeedBackImageService;
	
	@Value("${amazon.feedbackImageURL}")
	private String urlFeedBackImage;

	@GetMapping("/findAllFeedbackImgs")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iFeedBackImageService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createFeedBackImg/{id}")
	public ResponseEntity<Object> uploadPublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iFeedBackImageService.findAll();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iFeedBackImageService.uploadImageToAmazon(file,
						id + "Review" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iFeedBackImageService.findAllByNameContainer(id + "Review", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazon(file,
							id + "Review" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("w") + 1));

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazon(file,
							id + "Review" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}

		}

	}
	
	@PostMapping("/createFeedBackImgs")
	public ResponseEntity<Object> uploadPublicImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") String id) throws IOException {

		try {

			int count = 0;
			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazon(file,
							id + "Review" + String.valueOf(count));

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

	@DeleteMapping("/deleteFeedbackImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iFeedBackImageService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iFeedBackImageService.deleteByFileNameAndID(urlFeedBackImage + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/updateFeedbackImg/{id}")
	public ResponseEntity<Object> UpdateFeedbackImage(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iFeedBackImageService.updateFeedBackToAmazon(file, id);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}
}
