package giasuomt.demo.person.controller;

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
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.dto.UpdateAvatarRegisterAndLearner;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.service.IRegisterAndLearnerService;
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/registerAndLearner")
public class RegisterAndLearnerController
		extends GenericController<SaveRegisterAndLearnerDto, RegisterAndLearner, Long> {

	@Autowired
	private IRegisterAndLearnerService iRegisterAndLearnerService;
	
	
	@Autowired
	private IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;
	
	
	@Value("${amazon.registerandlearnerURL}")
	private String registerAndLearnerAvatarURL;
	
	@Value("${amazon.registerandlearnerPublicimgsURL}")
	private String registerAndLearnerAvatarPublicImgsURL;
	
	@Value("${amazon.registerandlearnerPrivateimgsURL}")
	private String registerAndLearnerAvatarPrivateImgsURL;

	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByPhoneNumber(phoneNumber);

		if (registerAndLearners.isEmpty())
			return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByEndPhoneNumber(endPhoneNumber);

		if (registerAndLearners.isEmpty())
			return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}
 
	@GetMapping("/findByFullNameAndReturnObject/{fullname}")
	public ResponseEntity<Object> findByFullnameAndReturnObject(@RequestParam("fullname") String fullname) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService
				.findByFullNameContaining(fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {
			List<RegisterAndLearner> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByEnglishFullNameContaining(StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndReturnFullName/{fullname}")
	public ResponseEntity<Object> findByFullnameAndReturnStringFullname(@RequestParam("fullname") String fullname) {

		List<String> registerAndLearners = iRegisterAndLearnerService
				.findByFullNameAndShowFullName(fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {
			List<String> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByEnglishNameAndShowEngLishFullName(StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndVocativeAndReturnObject/{fullname}/{vocative}")
	public ResponseEntity<Object> findByFullnameAndVocativeAndReturnObject(@RequestParam("fullname") String fullname,
			@RequestParam("vocative") String vocative) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByVocativeAndFullName(vocative,
				fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {

			List<RegisterAndLearner> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByVocativeAndEnglishFullNameContaining(vocative, StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);
	}

	@GetMapping("/findByFullNameAndVocativeAndReturnFullName/{fullname}/{vocative}")
	public ResponseEntity<Object> findByFullnameAndVocativeAndReturnFullName(@RequestParam("fullname") String fullname,
			@RequestParam("vocative") String vocative) {

		List<String> registerAndLearners = iRegisterAndLearnerService.findByVocativeAndFullNameAndShowFullName(vocative,
				fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {

			List<String> RegisterAndLearnerServiceWithEnglishFullName = iRegisterAndLearnerService
					.findByVocativeAndEnglishFullNameAndShowFullName(vocative, StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (RegisterAndLearnerServiceWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(RegisterAndLearnerServiceWithEnglishFullName, HttpStatus.OK);

		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);
	}
	
	@PutMapping("/updateAvatarRegisterAndLearner")
	public ResponseEntity<Object> updateAvatarRegisterAndLearner(@RequestBody UpdateAvatarRegisterAndLearner dto,
			BindingResult errors) {

		RegisterAndLearner registerAndLearner = iRegisterAndLearnerService.updateAvatarRegisterAndLearner(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse( registerAndLearner, HttpStatus.OK);

	}
	
	
	

	
	@GetMapping("/findallRegisterAndLearner")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createOrUpdateAvatarRegisterAndLearner/{id}")
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
	
	@PutMapping("/UpdatePrivateImgRegisterAndLearner/{nameFile}")
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
	
	@PutMapping("/UpdatePublicImgRegisterAndLearner/{nameFile}")
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

	@PostMapping("/createPublicImgRegisterAndLearner/{id}")
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

	@PostMapping("/createPrvateImgRegisterAndLearner/{id}")
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

	@PostMapping("/createPrivateImgsForRegisterAndLearner")
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

	@PostMapping("/createPublicImgsForRegisterAndLearner")
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

	@DeleteMapping("/deleteRegisterAndLearnerAvatar/{nameFile}")
	public ResponseEntity<Object> deleteTutorAvatar(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectinS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndID(registerAndLearnerAvatarURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePrivateImgRegisterAndLearner/{nameFile}")
	public ResponseEntity<Object> deleteTutorPrivateImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectPrivateInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndIDPrivateImgs(registerAndLearnerAvatarPrivateImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deletePublicImgRegisterAndLearner/{nameFile}")
	public ResponseEntity<Object> deleteTutorPublicImg(@PathVariable("nameFile") String nameFile) {

		if (!iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.checkExistObjectPublicInS3(nameFile))
			return ResponseHandler.getResponse("Don't have any url and id", HttpStatus.BAD_REQUEST);

		iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.deleteByFileNameAndIDPublicImgs(registerAndLearnerAvatarPublicImgsURL + nameFile);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	

}
