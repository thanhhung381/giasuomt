package giasuomt.demo.uploadfile.service;

import java.io.File;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.finance.repository.IJobFinanceRepository;


import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import giasuomt.demo.user.model.User;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class BillImageAwsService extends AwsClientS3 implements IBillImageAwsService {
	

	
	@Value("${amazon.billImageURL}")
	private String urlBillImage;
	
	@Value("${amazon.bucketnamebillImage}")
	private String bucketNameBillImage;
	
	@Autowired
	private IJobFinanceRepository iJobFinanceRepository;
	

	private void upploadPublicFile(String filename, File file) {
		client.putObject(
				new PutObjectRequest(bucketNameBillImage, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String uploadMultipartFile(MultipartFile multipartFile, String nameFile) {
		String imageURL = null;

		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			upploadPublicFile(nameFile, file);

			file.delete();

			//urlAvatar "http://meomeo/"
			imageURL = urlBillImage.concat(nameFile);

			return imageURL;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, Long id) {

		try {

			String url = uploadMultipartFile(multipartFile, String.valueOf(id));

			System.out.println(url);
		
			JobFinance jobFinance=iJobFinanceRepository.getOne(id);
			
			jobFinance.setBillImg(url);
			
			iJobFinanceRepository.save(jobFinance);
			

			return "Insert Or Update Avatar successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketNameBillImage, urlFile.substring(urlFile.lastIndexOf('/') + 1));
			

			JobFinance jobFinance=iJobFinanceRepository.getOne(Long.parseLong(urlFile.substring(urlFile.lastIndexOf('/') + 1)));
			
			jobFinance.setBillImg(null);
			
			iJobFinanceRepository.save(jobFinance);
			

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}
	}

//	
	@Override
	public List<String> findAll() {

		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNameBillImage);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlBillImage+os.getKey());
		}

		return listObject;
	}



	@Override
	public boolean checkExistObjectinS3(String name) {

		try {
			boolean flag = this.client.doesObjectExist(bucketNameBillImage, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}



	

	
	

}
