package giasuomt.demo.uploadfile.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.uploadfile.model.ResponsiveFile;
import giasuomt.demo.uploadfile.service.FileEntityService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RequestMapping("/api/file")
@RestController
@AllArgsConstructor
public class FileController {
	
		private FileEntityService entityService;
		
		@PostMapping("/create")
		public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file)
		{
			try {
				
				entityService.save(file);
				
				return new ResponseEntity<>("ok",HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>("bad",HttpStatus.BAD_REQUEST);
			}
		}
	
		
		@GetMapping()
		public ResponseEntity<Object> findAll()
		{
			
			
			
			return new ResponseEntity<>(entityService.findAll(),HttpStatus.OK);
		}
		
		
		
	
}
