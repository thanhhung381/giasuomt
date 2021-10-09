package giasuomt.demo.commondata.dto;

import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SaveUserDto {
//  @Email
  private String emails;
  
//  @NotNull
  private String phones;
  
  private String zaloes;
  
  private String fbs;
  
  private String avatars;
  
//  @NotBlank
  private String fullName;
  
//  @NotBlank
  private String gender;        
  
  @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
  private LocalDate birthDate;
  
  private String birthYear;
}
