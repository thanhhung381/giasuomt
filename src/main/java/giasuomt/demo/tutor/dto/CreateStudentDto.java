package giasuomt.demo.tutor.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import giasuomt.demo.commondata.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentDto {
    private String confirmImgs;
    
    private String nowLevel;
    
    @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
    private LocalDateTime nowLevelUpdatedAt;
    
    private Long institutionId;
    
    private Long majorId;
    
    private String anotherMajor;
    
    private Long tutorId;
}
