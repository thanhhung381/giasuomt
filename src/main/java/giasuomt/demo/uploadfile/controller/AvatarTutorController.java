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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateImgsTutorAwsService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/AvatarTutor")
@RestController
public class AvatarTutorController {
	@Autowired
	private IAvatarAndPublicAndPrivateImgsTutorAwsService iAvatarTutorAwsService;

	@Value("${amazon.tutorAvatarURL}")
	private String tutorAvatarURL;

	@Value("${amazon.tutorPublicimgsURL}")
	private String tutorPublicImgsURL;

	@Value("${amazon.tutorPrivateimgsURL}")
	private String tutorPrivateImgsURL;;

	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iAvatarTutorAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createOrUpdate/{tutorCode}")
	public ResponseEntity<Object> uploadOrUpdate(@RequestParam("file") MultipartFile file,
			@PathVariable("tutorCode") String tutorCode) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazon(file, tutorCode);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/createOrUpdatePublicImg/{tutorCode}")
	public ResponseEntity<Object> uploadOrUpdatePublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("tutorCode") String tutorCode) throws IOException {

		List<String> listOject = iAvatarTutorAwsService.findAllPublicImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPubclicImgs(file,
						tutorCode + "Public" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iAvatarTutorAwsService.findAllByNameContainer(tutorCode + "Public", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPubclicImgs(file,
							tutorCode + "Public" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("c") + 1));

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPubclicImgs(file,
							tutorCode + "Public" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}

		}

	}

	@PostMapping("/createOrUpdatePrvateImg/{tutorCode}")
	public ResponseEntity<Object> uploadOrUpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("tutorCode") String tutorCode) throws IOException {

		List<String> listOject = iAvatarTutorAwsService.findAllPrivateImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPrivateImgs(file,
						tutorCode + "Private" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {

			String lastURL = iAvatarTutorAwsService.findAllByNameContainer(tutorCode + "Private", listOject);
			if (lastURL == null) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPrivateImgs(file,
							tutorCode + "Private" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			} else {

				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("e") + 1));

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPrivateImgs(file,
							tutorCode + "Private" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
		}

	}

	@PostMapping("/createOrUpdatePrivateImgsForTutor")
	public ResponseEntity<Object> uploadPrivateImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("tutorCode") String tutorCode) throws IOException {

		try {

			int count = 0;

			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPrivateImgs(file,
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

	@PostMapping("/createOrUpdatePublicImgsForTutor")
	public ResponseEntity<Object> uploadPublicImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("tutorCode") String tutorCode) throws IOException {

		try {

			int count = 0;
			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iAvatarTutorAwsService.uploadImageToAmazonPubclicImgs(file,
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

	@DeleteMapping("/deleteTutorAvatar/{nameFile}")
	public ResponseEntity<Object> deleteTutorAvatar(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarTutorAwsService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarTutorAwsService.deleteByFileNameAndID(tutorAvatarURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePrivateImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPrivateImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarTutorAwsService.checkExistObjectPrivateInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarTutorAwsService.deleteByFileNameAndIDPrivateImgs(tutorPrivateImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePublicImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarTutorAwsService.checkExistObjectPublicInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarTutorAwsService.deleteByFileNameAndIDPublicImgs(tutorPublicImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@PutMapping("/UpdatePrivateImg/{nameFile}")
	public ResponseEntity<Object> UpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iAvatarTutorAwsService.updatePrivateImageToAmazon(file, nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/UpdatePublicImg/{nameFile}")
	public ResponseEntity<Object> UpdatePublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iAvatarTutorAwsService.updatePublicImageToAmazon(file, nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

}
