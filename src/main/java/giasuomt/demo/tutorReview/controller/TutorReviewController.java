package giasuomt.demo.tutorReview.controller;

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
import giasuomt.demo.task.service.IApplicationService;
import giasuomt.demo.tutorReview.dto.SaveTutorReviewDto;
import giasuomt.demo.tutorReview.model.TutorReview;
import giasuomt.demo.uploadfile.service.IFeedBackImageAwsService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/tutorReview")
@RestController
public class TutorReviewController extends GenericController<SaveTutorReviewDto, TutorReview, Long> {

	@Autowired
	private IFeedBackImageAwsService iFeedBackImageService;

	@Value("${amazon.privateFeedBackTutorReviewURL}")
	private String urlPrivate;

	@Value("${amazon.publicFeedBackTutorReviewURL}")
	private String urlPublic;

	@PostMapping("/createOrUpdateTutorReviewPublicImg/{id}")
	public ResponseEntity<Object> uploadOrUpdatePublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iFeedBackImageService.findAllPublicImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPubclicImgs(file,
						id + "Public" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iFeedBackImageService.findAllByNameContainer(id + "Public", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPubclicImgs(file,
							id + "Public" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("c") + 1));

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPubclicImgs(file,
							id + "Public" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}

		}

	}

	@PostMapping("/createOrUpdateTutorReviewPrivateImg/{id}")
	public ResponseEntity<Object> uploadOrUpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iFeedBackImageService.findAllPrivateImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPrivateImgs(file,
						id + "Private" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iFeedBackImageService.findAllByNameContainer(id + "Private", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPrivateImgs(file,
							id + "Private" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {

				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("e") + 1));

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPrivateImgs(file,
							id + "Private" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
		}

	}

	@PostMapping("/createOrUpdateForTutorReviewPrivateImgs")
	public ResponseEntity<Object> uploadPrivateImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("tutorCode") String tutorCode) throws IOException {

		try {

			int count = 0;

			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPrivateImgs(file,
							tutorCode + "Private" + String.valueOf(count));

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

	@PostMapping("/createOrUpdateForTutorPublicImgs")
	public ResponseEntity<Object> uploadPublicImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("tutorCode") String tutorCode) throws IOException {

		try {

			int count = 0;
			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iFeedBackImageService.uploadImageToAmazonPubclicImgs(file,
							tutorCode + "Public" + String.valueOf(count));

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

	@DeleteMapping("/deleteTutorReviewPrivateImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPrivateImg(@PathVariable("nameFile") String nameFile) {

		if (!iFeedBackImageService.checkExistObjectPrivateInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iFeedBackImageService.deleteByFileNameAndIDPrivateImgs(urlPrivate + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deleteTutorReviewPublicImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iFeedBackImageService.checkExistObjectPublicInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iFeedBackImageService.deleteByFileNameAndIDPublicImgs(urlPublic + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@PutMapping("/updateTutorReviewPrivateImg/{nameFile}")
	public ResponseEntity<Object> UpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iFeedBackImageService.updatePrivateImageToAmazon(file, nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/updateTutorReviewPublicImg/{nameFile}")
	public ResponseEntity<Object> UpdatePublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iFeedBackImageService.updatePublicImageToAmazon(file, nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

}
