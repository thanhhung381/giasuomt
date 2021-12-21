package giasuomt.demo.uploadfile.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.uploadfile.model.FeedBackImage;
import giasuomt.demo.uploadfile.model.ResponsiveFeedBackImage;
import giasuomt.demo.uploadfile.model.ResponsiveRetainedImgsIdentification;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentification;
import giasuomt.demo.uploadfile.service.IFeedBackImageService;
import giasuomt.demo.uploadfile.service.IRetainedImgsIdentificationService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/RetainedImgsIdentification")
@RestController
@AllArgsConstructor
public class RetainedImgsIdentificationController {
	private IRetainedImgsIdentificationService iRetainedImgsIdentificationService;

	@PostMapping("/create")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		if (filename.contains(".jpeg") || filename.contains(".jpg") || filename.contains(".png")) {

			ResponsiveRetainedImgsIdentification responsiveFile = new ResponsiveRetainedImgsIdentification();// tránh lộ
																												// mã
																												// data

			RetainedImgsIdentification entity = iRetainedImgsIdentificationService.save(file);

			iRetainedImgsIdentificationService.mapDto(responsiveFile, entity);

			return ResponseHandler.getResponse(responsiveFile, HttpStatus.CREATED);
		}

		else
			return ResponseHandler.getResponse("You have to upload files which have type of .jpg, .png, .jpeg ",
					HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		List<ResponsiveRetainedImgsIdentification> fileEntities = iRetainedImgsIdentificationService.findAll();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(fileEntities, HttpStatus.OK);

	}

	@GetMapping("/downloadFile/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename, HttpServletRequest request) {

		RetainedImgsIdentification doc = iRetainedImgsIdentificationService.getByNameFile(filename);

		String mimeType = request.getServletContext().getMimeType(doc.getNameFile());

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
						"inline;fileName=" + doc.getNameFile())
				.body(doc.getData());

	}
}
