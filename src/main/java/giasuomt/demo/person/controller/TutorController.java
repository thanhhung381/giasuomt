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
import giasuomt.demo.person.dto.TutorForWebDto;
import giasuomt.demo.person.dto.UpdateCalendarDto;
import giasuomt.demo.person.dto.UpdateNowLevelAndNowUpdateAtDto;
import giasuomt.demo.person.dto.SaveTutorDto;

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.service.ITutorService;
import giasuomt.demo.task.dto.UpdateSubjectGroupForSureDto;
import giasuomt.demo.task.dto.UpdateSubjectGroupMaybeDto;
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateImgsTutorAwsService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@RequestMapping(value = "/api/tutor")
public class TutorController extends GenericController<SaveTutorDto, Tutor, Long> {

	@Autowired
	private ITutorService iTutorService;
	
	@Autowired
	private IAvatarAndPublicAndPrivateImgsTutorAwsService iAvatarTutorAwsService;
	

	@Value("${amazon.tutorAvatarURL}")
	private String tutorAvatarURL;

	@Value("${amazon.tutorPublicimgsURL}")
	private String tutorPublicImgsURL;

	@Value("${amazon.tutorPrivateimgsURL}")
	private String tutorPrivateImgsURL;;

	@GetMapping("/findByTutorCode/{tutorCode}")
	public ResponseEntity<Object> findByTutorCode(@RequestParam("tutorCode") Long tutorCode) {

		Tutor tutor = iTutorService.findByTutorCode(tutorCode);

		if (tutor == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);
	}

	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<Tutor> tutors = iTutorService.findByPhoneNumber(phoneNumber);

		if (tutors.isEmpty())
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<Tutor> tutors = iTutorService.findByEndPhoneNumber(endPhoneNumber);

		if (tutors.isEmpty())
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByFullName/{fullName}")
	public ResponseEntity<Object> findByFullnameAndReturnObject(@RequestParam("fullName") String fullName) {

		List<Tutor> tutors = iTutorService.findByFullNameContain(fullName.toUpperCase());

		if (tutors.isEmpty()) {
			List<Tutor> tutorsByEngName = iTutorService
					.findByEnglishFullName(StringUltilsForAreaID.removeAccent(fullName.toUpperCase()));
			if (tutorsByEngName.isEmpty())
				return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(tutorsByEngName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndReturnName/{fullNameShowName}")
	public ResponseEntity<Object> findByFullnameAndReturnFullName(
			@RequestParam("fullNameShowName") String fullNameShowName) {

		List<String> tutorNames = iTutorService.findByfullnameAndShowFullName(fullNameShowName.toUpperCase());

		if (tutorNames.isEmpty()) {
			List<String> tutorEngNames = iTutorService.findByEngfullnameAndShowFullName(
					StringUltilsForAreaID.removeAccent(fullNameShowName.toUpperCase()));
			if (tutorEngNames.isEmpty())
				return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(tutorEngNames, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(tutorNames, HttpStatus.OK);

	}


	@GetMapping("/findAllTutorForWeb")
	public ResponseEntity<Object> findAllTutorForWeb() {
		List<TutorForWebDto> list = iTutorService.findAllTutorForWeb();
		if (list.isEmpty())
			return ResponseHandler.getResponse("No content", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(list, HttpStatus.OK);
	}

	@PutMapping("/updateSubjectGroupForSure")
	public ResponseEntity<Object> updateSubjectGroupForSure(@RequestBody UpdateSubjectGroupForSureDto dto,
			BindingResult errors) {

		Tutor tutor = iTutorService.updateSubjectGroupForSure(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);

	}

	@PutMapping("/updateSubjectGroupMaybe")
	public ResponseEntity<Object> updateSubjectGroupMaybe(@RequestBody UpdateSubjectGroupMaybeDto dto,
			BindingResult errors) {

		Tutor tutor = iTutorService.updateSubjetGroupMaybe(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);

	}

	@PutMapping("/updateNowLevelAndNowUpdateAt")
	public ResponseEntity<Object> updateNowLevelAndNowUpdateAt(@RequestBody UpdateNowLevelAndNowUpdateAtDto dto,
			BindingResult errors) {

		Tutor tutor = iTutorService.updateNowLevelAndNowUpdateAt(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);

	}

	@PutMapping("/updateCalendar")
	public ResponseEntity<Object> updateCalendar(@RequestBody UpdateCalendarDto dto, BindingResult errors) {

		Tutor tutor = iTutorService.updateCalendar(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);
	}
	

	


	@GetMapping("/findallTutorImg")
	public ResponseEntity<Object> findAll() {

		List<String> fileEntities = iAvatarTutorAwsService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

	@PostMapping("/createOrUpdateTutorImg/{tutorCode}")
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

	@PostMapping("/createOrUpdatePublicImgTutorImg/{tutorCode}")
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

	@PostMapping("/createOrUpdatePrvateImgTutorImg/{tutorCode}")
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

	@PostMapping("s/createOrUpdatePublicImgsForTutor")
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

	@PutMapping("/updatePrivateImg/{nameFile}")
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

	@PutMapping("/updatePublicImg/{nameFile}")
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
