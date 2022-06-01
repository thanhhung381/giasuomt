package giasuomt.demo.person.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNowLevelAndNowUpdateAtDto {

	private Long id;

	private int nowLevel;



}
 