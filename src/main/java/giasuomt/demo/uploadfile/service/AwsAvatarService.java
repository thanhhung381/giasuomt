package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import giasuomt.demo.uploadfile.model.AwsAvatar;
import giasuomt.demo.uploadfile.repository.IAWSAvatarRepository;
import lombok.AllArgsConstructor;

@Service
public class AwsAvatarService implements IAwsAvartarService {

	@Autowired
	private IAWSAvatarRepository iawsAvatarRepository;
	
	private AmazonS3 amazonS3;

   
    // Contains URl of IMAGE
    @Value("${amazon.endPointURl}")
    private String url;

    // Your bucket name.
    @Value("${amazon.bucketname}")
    private String bucketName;

    // The IAM access key.
    @Value("${amazon.accesskey}")
    private String accessKey;

    // The IAM secret key.
    @Value("${amazon.secretkey}")
    private String secretKey;
	
    @PostConstruct
    private void inhit()
    {
    	//khởi tạo xác thực AmazonS3 bằng cách sử dụng BasiCAWSCredentials. 
    	BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(accessKey, secretKey);
    	
    	// bắt đầu sử dụng AWS bằng cách sử dụng lớp AWSS#CLinetBuilder 
    	amazonS3=AmazonS3ClientBuilder.standard()
    			.withRegion(Regions.AP_SOUTHEAST_1)
    			.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
    			.build();
    }
    
    
    // phương thức Convert Multipart sang File 
    // Mulitipath là một  lớp cho các file upload lên từ request 
    
    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException 
    {
    	
    	//lớp Objects 
    	
    	File convertFile=new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    	
    	// FileOutputStream là một output stream được sử dụng để ghi dữ liệu vào một file theo định dạng byte (byte stream)
    	
    	//khởi tạo truyền tham số file (đường dẫn nào đó) vào
    	FileOutputStream fileOutputStream=new FileOutputStream(convertFile);
    	// Nó được sử dụng để ghi ary.length bytes từ mảng byte đến file output stream.
    	fileOutputStream.write(multipartFile.getBytes());
    	//xài xong đóng file lại
    	fileOutputStream.close();
    	
		return convertFile;
    	
    }
    //phương thức lấy tên File
    private String generateFileName(MultipartFile multipartFile)
    {
    	 return  Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ", "_");   
    }
    
    
    // upload hình lên aws s3 nếu nó xảy ra lỗi thì tự động sẽ xóa 
    private void uploadPublicFile(String filename,File file)
    {
    	// phương thức putObject để xác thực khi lưu file  trên  cái bucket thông qua tên file 
    	// withCannedAclt là cấu hình chính sách truy cập
    	amazonS3.putObject(new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
  
    }
	
    // Tạo đường dẫn
    private String uploadMultipartFile(MultipartFile multipartFile)
    {
    	 String imageURL=null;
    	
    	try {
    		// lấy file
    		
			File file=convertMultipartToFile(multipartFile);
			
			// lấy tên file
			String nameFile=generateFileName(multipartFile);
			
			//uploadFile
			uploadPublicFile(nameFile, file);
			
			// lấy xong xóa file 
			file.delete();
			
			imageURL=url.concat(nameFile);
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	return imageURL;
    }
    
    public AwsAvatar uploadImageToAmazon(MultipartFile multipartFile)
    {
    	AwsAvatar awsAvatar=new AwsAvatar();
    	try {
			String url =uploadMultipartFile(multipartFile);
			
			
			awsAvatar.setUrlImage(url);
			return iawsAvatarRepository.save(awsAvatar);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	
    	return null;
    }
    
	public List<AwsAvatar> findAll()
	{
		return iawsAvatarRepository.findAll();
	}
	
	public void deleteById(String URL)
	{
		
		amazonS3.deleteBucket(URL.substring(URL.lastIndexOf('/')+1));
		iawsAvatarRepository.deleteByUrlImage(URL);
	}


	@Override
	public List<AwsAvatar> uploadFiles(List<MultipartFile> multipartFile) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
