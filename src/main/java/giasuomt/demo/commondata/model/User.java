package giasuomt.demo.commondata.model;

import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User extends AbstractEntity {        
//        @Email
//        @ElementCollection(targetClass=String.class)
        private String emails;
        
//        @NotNull
//        @ElementCollection(targetClass=String.class)
        private String phones;
        
        private String zaloes;
        
        private String fbs;
        
        private String avatars;
        
//        @NotBlank
        private String fullName;
        
//        @NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
        private String gender;        
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
        @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
        private LocalDate birthDate;
        
        private String birthYear;
        

        //getter,setter kiểu fluentAPI
        public User emails(String emails) {
                this.emails = emails;
                return this;
        }
        public User phones(String phones) {
                this.phones = phones;
                return this;
        }
        public User zaloes(String zaloes) {
                this.zaloes = zaloes;
                return this;
        }
        public User fbs(String fbs) {
                this.fbs = fbs;
                return this;
        }
        public User avatars(String avatars) {
                this.avatars = avatars;
                return this;
        }
        public User fullName(String fullName) {
                this.fullName = fullName;
                return this;
        }
        public User birthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
                return this;
        }
        public User birthYear(String birthYear) {
                this.birthYear = birthYear;
                return this;
        }
}
