package giasuomt.demo.person.dto;

import java.util.HashSet;
import java.util.Set;

import giasuomt.demo.commondata.util.Calendar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCalendarDto {
	
	private Long id;
	
	private Set<Calendar> calendars=new HashSet<>(); 
}
