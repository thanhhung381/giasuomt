package giasuomt.demo.location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskPlaceAddressDto {

	private Long id;

	private String exactAddNumber;

	private String exactXCoo;

	private String exactYCoo;

	private String relAddNumber;

	private String relXCoo;

	private String relYCoo;

	private String addStreet;

	private String addStreetNote;

	private String idArea;
}
