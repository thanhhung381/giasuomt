package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import giasuomt.demo.uploadfile.model.BillIamgeAws;
import giasuomt.demo.uploadfile.repository.IBillImageAwsRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
public class BillImageAwsService extends AwsClientS3 implements IBillImageAwsService {
	
	@Autowired
	private IBillImageAwsRepository iBillImageAwsRepository;
	
	@Value("${amazon.billImageURL}")
	private String urlBillImage;
	
	@Value("${amazon.bucketnamebillImage}")
	private String bucketNameBillImage;
	
	
	private void uploadPublicFile(String filename,File file)
	{
		amazonS3.putObject(new PutObjectRequest(bucketNameBillImage, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	private String uploadMultipartFile(MultipartFile multipartFile)
	{
		
		String billImageURL=null;
		try {
			
			File file=FileUltils.convertMultiPathToFile(multipartFile);
			
			String urlname=FileUltils.generateNameFile(multipartFile);
			
			uploadPublicFile(urlname, file);
			
			file.delete();
			
			billImageURL=urlBillImage.concat(urlname);
			
			return billImageURL;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	@Override
	public BillIamgeAws uploadImageToAmazon(MultipartFile multipartFile) {
		BillIamgeAws billIamgeAws=new BillIamgeAws();
		try {
			
			String url=uploadMultipartFile(multipartFile);
			
			billIamgeAws.setUrlBillImage(url);
			
			return iBillImageAwsRepository.save(billIamgeAws);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BillIamgeAws> findAll() {
		
		return iBillImageAwsRepository.findAll();
	}

	@Override
	public void deleteByFileNameAndID(String urlFile,Long id) {
		amazonS3.deleteObject(bucketNameBillImage,urlFile.substring(urlFile.lastIndexOf('/')+1));
		iBillImageAwsRepository.deleteById(id);
		
	}

	@Override
	public boolean checkExistIdOfT(Long id) {
		
		return iBillImageAwsRepository.countById(id)>=1;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		if(amazonS3.doesObjectExist(bucketNameBillImage, name))
			return true;
		return false;
	}

	
	

}
