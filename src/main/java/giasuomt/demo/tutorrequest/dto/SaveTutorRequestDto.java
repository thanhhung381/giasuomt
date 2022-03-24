package giasuomt.demo.tutorrequest.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTutorRequestDto {

	private Long id;
	
	private LocalDateTime createdAt;

	private String fromUrl;

	private Long chosenTutorId;

	private String phones;

	private String username;

	private String whenToContact;

	private String requireContent;

	private String local;
}
