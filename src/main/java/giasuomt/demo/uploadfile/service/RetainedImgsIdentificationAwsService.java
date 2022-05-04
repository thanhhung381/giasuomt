package giasuomt.demo.uploadfile.service;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.asm.tree.MultiANewArrayInsnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import giasuomt.demo.uploadfile.model.AvatarAws;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentificationAws;
import giasuomt.demo.uploadfile.repository.IRetainedImgsIdentificationAwsRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
public class RetainedImgsIdentificationAwsService extends AwsClientS3 implements IRetainedImgsIdentificationAwsService {

	@Autowired
	private IRetainedImgsIdentificationAwsRepository iRetainedImgsIdentificationAwsRepository;

	@Value("${amazon.retainedimgsidentificationURL}")
	private String urlRetainedImgsIdentification;

	@Value("${amazon.bucketnameretainedimgsidentification}")
	private String bucketNameRetainedImgsIdentification;

	private void uploadPulicFile(String filename, File file) {
		this.client.putObject(new PutObjectRequest(bucketNameRetainedImgsIdentification, filename, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String uploadMutipartFile(MultipartFile multipartFile) {
		String retainedImgsIdentificationUrl = null;
		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			String nameFile = FileUltils.generateNameFile(multipartFile);

			uploadPulicFile(nameFile, file);

			file.delete();

			urlRetainedImgsIdentification = urlRetainedImgsIdentification.concat(nameFile);

			return retainedImgsIdentificationUrl;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public RetainedImgsIdentificationAws uploadImageToAmazon(MultipartFile multipartFile) {

		RetainedImgsIdentificationAws retainedImgsIdentificationAws = new RetainedImgsIdentificationAws();
		try {

			String url = uploadMutipartFile(multipartFile);
			System.out.println(url);

			retainedImgsIdentificationAws.setUrlRetainedImgsIdentification(url);

			return iRetainedImgsIdentificationAwsRepository.save(retainedImgsIdentificationAws);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<RetainedImgsIdentificationAws> findAll() {

		return iRetainedImgsIdentificationAwsRepository.findAll();
	}

	@Override
	public void deleteByFileNameAndID(String urlFile, Long Id) {
		this.client.deleteObject(bucketNameRetainedImgsIdentification,
				urlFile.substring(urlFile.lastIndexOf('/') + 1));
		iRetainedImgsIdentificationAwsRepository.deleteById(Id);

	}

	@Override
	public boolean checkExistIdOfT(Long id) {
		
		return iRetainedImgsIdentificationAwsRepository.countById(id)>=1;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		if(this.client.doesObjectExist(bucketNameRetainedImgsIdentification, name))
			return true;
		return false;
	}

	@Override
	public void deleteById(Long id) {
		iRetainedImgsIdentificationAwsRepository.deleteById(id);
		
	}

}
