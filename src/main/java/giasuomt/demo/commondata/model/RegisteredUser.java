package giasuomt.demo.commondata.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import giasuomt.demo.commondata.util.RegisteredUserStatus;
import giasuomt.demo.person.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="registered_user")
public class RegisteredUser extends AbstractEntity {
        @Size(min = 3, max = 50, message = "{user.username.size}")
        @Column(unique = true) //để các giá trị username ko được trùng nhau
        private String username;
        
        private String password;
        
//        @NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
        @Enumerated(EnumType.STRING) //phải dùng Enumerated để database biết là nó phải tạo cái cột kiểu là gì (vd nếu Enum mình để là số thì type là Interger, ở đây Enum mình là chữ nên Type là STRING)
        private RegisteredUserStatus status; //UserStatus là 1 enum (xem trong UserStatus.java) //enum giống như là 1 kiểu dữ liệu do mình tự định nghĩa nhưng nó gọn hơn Object


        @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
        @JoinColumn(name = "person_id", referencedColumnName = "id")
        private Person person;
}