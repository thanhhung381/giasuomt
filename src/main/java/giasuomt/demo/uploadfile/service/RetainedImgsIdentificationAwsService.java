package giasuomt.demo.uploadfile.service;

import java.io.File;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.modelmapper.internal.asm.tree.MultiANewArrayInsnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.collect.Sets;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
public class RetainedImgsIdentificationAwsService extends AwsClientS3 implements IRetainedImgsIdentificationAwsService {

	@Autowired
	private IJobRepository iJobRepository;

	@Value("${amazon.retainedimgsidentificationURL}")
	private String urlRetainedImgsIdentification;

	@Value("${amazon.bucketnameretainedimgsidentification}")
	private String bucketNameRetainedImgsIdentification;

	private void uploadPulicFile(String filename, File file, String bucketName) {
		this.client.putObject(
				new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String uploadMutipartFile(MultipartFile multipartFile, String nameFile, String bucketName, String url) {
		String retainedImgsIdentificationUrl = null;
		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			uploadPulicFile(nameFile, file, bucketName);

			file.delete();

			retainedImgsIdentificationUrl = url.concat(nameFile);

			return retainedImgsIdentificationUrl;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, String fileName) {

		try {
			
			System.out.println("name: " +fileName+" bucket:"+ bucketNameRetainedImgsIdentification+"url:"+urlRetainedImgsIdentification);

			String url = uploadMutipartFile(multipartFile, fileName, bucketNameRetainedImgsIdentification,
					urlRetainedImgsIdentification);

			System.out.println(url);
			
			Job job = iJobRepository.getOne(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("RetainedImgsIdentification")));

			Set<String> urlRetainedImgsIdentifications = job.getRetainedImgsIdentification();

			urlRetainedImgsIdentifications.add(url);

			List<String> converter = new LinkedList<>(urlRetainedImgsIdentifications);

			converter = removeDuplicateElemet(converter);

			job.setRetainedImgsIdentification(Sets.newHashSet(converter));

			iJobRepository.save(job);

			return "Insert RetainedImgsIdentification img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<String> findAll() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNameRetainedImgsIdentification);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlRetainedImgsIdentification + os.getKey());
		}

		return listObject;
	}

	@Override
	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketNameRetainedImgsIdentification, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			Job job = iJobRepository.getOne(urlFile.substring(urlFile.lastIndexOf('/') + 1, urlFile.lastIndexOf("RetainedImgsIdentification")));

			Set<String> retainedImgsIdentificationImgs = job.getRetainedImgsIdentification();

			retainedImgsIdentificationImgs.remove(urlFile);

			job.setRetainedImgsIdentification(retainedImgsIdentificationImgs);

			iJobRepository.save(job);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean checkExistIdOfT(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNameRetainedImgsIdentification, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String findAllByNameContainer(String name, List<String> containURLs) {
		List<String> findAllListContain = new LinkedList<>();
		for (String object : containURLs) {
			if (object.contains(name))
				findAllListContain.add(object);
		}

		Collections.sort(findAllListContain);

		if (findAllListContain.isEmpty())
			return null;

		return findAllListContain.get(findAllListContain.size() - 1);
	}

	@Override
	public String updateRetainedImgsIdentificationToAmazon(MultipartFile file, String nameFile) {
		try {

			String url = uploadMutipartFile(file, nameFile, bucketNameRetainedImgsIdentification, urlRetainedImgsIdentification);

			return " Update RetainedImgsIdentification img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	private List<String> removeDuplicateElemet(List<String> string) {
		List<String> temp = new LinkedList<>();

		for (int i = 0; i < string.size(); i++) {
			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (string.get(i).contains(string.get(j))) {
					check = true;
					break;
				}
			}

			if (check == false) {

				for (int j = i + 1; j < string.size(); j++) {
					if (string.get(i).contains(string.get(j))) {

						string.remove(j);
						j--;
					}
				}
				temp.add(string.get(i));
			}

		}

		return temp;
	}

}
