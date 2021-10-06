package giasuomt.demo.tutor.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentDto {

	private String confirmImgs;

	private String nowLevel;

	private Long idTutor;

	private LocalDateTime nowLevelUpdatedAt;

}
