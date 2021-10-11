package giasuomt.demo.person.model;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schooler")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" }) // chống báo lỗi
public class Schooler extends AbstractEntity {
	private String confirmImgs;

	private String nowLevel;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) // Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) // Quy định date format để lưu xuống database
	private LocalDateTime nowLevelUpdatedAt;

	private String institutionName;

	private String institutionAbbrName;

	private String institutionCode;

	private String institutionType;


	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnore
	private Person person;
}
