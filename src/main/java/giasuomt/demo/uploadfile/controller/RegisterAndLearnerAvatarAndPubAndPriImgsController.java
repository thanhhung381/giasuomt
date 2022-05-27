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
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;

@RequestMapping("/api/registerAndLearnerAvatarAndPubAndPriImgs")
@RestController
public class RegisterAndLearnerAvatarAndPubAndPriImgsController {

	@Autowired
	private IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;
	
	
	@Value("${amazon.registerandlearnerURL}")
	private String registerAndLearnerAvatarURL;
	
	@Value("${amazon.registerandlearnerPublicimgsURL}")
	private String registerAndLearnerAvatarPublicImgsURL;
	
	@Value("${amazon.registerandlearnerPrivateimgsURL}")
	private String registerAndLearnerAvatarPrivateImgsURL;
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createOrUpdateAvatar/{id}")
	public ResponseEntity<Object> uploadOrUpdateAvatar(@RequestParam("file") MultipartFile file,
			@PathVariable("id") Long id) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazon(file, id);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}
	
	@PutMapping("/UpdatePrivateImg/{nameFile}")
	public ResponseEntity<Object> UpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("nameFile") String nameFile) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.updatePrivateImageToAmazon(file, nameFile);

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

			String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.updatePublicImageToAmazon(file, nameFile);

			return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/createPublicImg/{id}")
	public ResponseEntity<Object> uploadPublicImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllPublicImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

				String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPubclicImgs(file,
						id + "Public" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {
			
			String lastURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllByNameContainer(id + "Public", listOject);
			if(lastURL==null)
			{
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPubclicImgs(file,
							id + "Public" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
			else
			{
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					

					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("c") + 1));

					String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPubclicImgs(file,
							id + "Public" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
			
		}

	}

	@PostMapping("/createPrvateImg/{id}")
	public ResponseEntity<Object> uploadOrUpdatePrivateImg(@RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) throws IOException {

		List<String> listOject = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllPrivateImgs();
		if (listOject.isEmpty()) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {
				String awsAvatarURL=	iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPrivateImgs(file,
						id + "Private" + String.valueOf(1));

				return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
			}

			else
				return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
						HttpStatus.BAD_REQUEST);
		} else {
			
			String lastURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllByNameContainer(id + "Private", listOject);
			if(lastURL==null)
			{
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {
					String awsAvatarURL=	iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPrivateImgs(file,
							id + "Private" + String.valueOf(1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
			else
			{
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					
					int i = Integer.parseInt(lastURL.substring(lastURL.lastIndexOf("e") + 1));

					String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPrivateImgs(file,
							id + "Private" + String.valueOf(i + 1));

					return ResponseHandler.getResponse(awsAvatarURL, HttpStatus.CREATED);
				}

				else
					return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
							HttpStatus.BAD_REQUEST);
			}
			
			
		}

	}

	@PostMapping("/createPrivateImgsForTutor")
	public ResponseEntity<Object> uploadPrivateImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") String id) throws IOException {

		try {

			int count = 0;

			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPrivateImgs(file,
							id + "Private" + String.valueOf(count));

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

	@PostMapping("/createPublicImgsForTutor")
	public ResponseEntity<Object> uploadPublicImgs(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") String id) throws IOException {

		try {

			int count = 0;
			for (MultipartFile file : files) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());

				if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

					count += 1;

					String awsAvatarURL = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.uploadImageToAmazonPubclicImgs(file,
							id + "Public" + String.valueOf(count));

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

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndID(registerAndLearnerAvatarURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePrivateImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPrivateImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectPrivateInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndIDPrivateImgs(registerAndLearnerAvatarPrivateImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePublicImg/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectPublicInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndIDPublicImgs(registerAndLearnerAvatarPublicImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
}
