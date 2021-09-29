package giasuomt.demo.tutor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "certificate")
public class Certificate extends AbstractEntity {
<<<<<<< Updated upstream
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	private String certificateName;
	
	private String certificateGroup;
	
	private String description;
	
	private String certificateImgs;
	
	@ManyToMany(mappedBy = "certificates", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Tutor> tutor = new HashSet<>();
}
=======
        @Id //Quy định khoá chính
        @GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
        @Column(updatable = false) //Column này ko update được
        private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
        
        private String certificateName;
        
        private String certificateGroup;
        
        private String description;
        
        private String certificateImgs;
        
        @ManyToMany(mappedBy = "certificates", fetch = FetchType.LAZY)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        private Set<Tutor> tutor = new HashSet<>();
}
>>>>>>> Stashed changes
