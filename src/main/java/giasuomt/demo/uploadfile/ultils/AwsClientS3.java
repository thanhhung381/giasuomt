package giasuomt.demo.uploadfile.ultils;

import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.services.s3.S3ClientOptions;
import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;

@Getter
@Setter
@Service
public class AwsClientS3 {
	
	@Value("${amazon.accessKeyKhangAdmin}")
	protected String accessKey;
	
	@Value("${amazon.secretkeyKhangAdmin}")
	protected String secretKey;
	

	protected AmazonS3 client;
	
	@PostConstruct 
	public void init()
	{
		BasicAWSCredentials awsCredentials=new BasicAWSCredentials(accessKey,secretKey);
		 this.client = new AmazonS3Client(awsCredentials);
		 this.client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		 this.client.setEndpoint("hn.ss.bfcplatform.vn");
		
				
	}
	
	
}
