package giasuomt.demo.educational.controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.SubjectGroup;
import lombok.AllArgsConstructor;

@RestController // Tức là server sẽ trả JSON về client
@RequestMapping("/api/subjectGroup") // Ghi nhận yêu cầu gọi api của Client
@AllArgsConstructor
public class SubjectGroupController extends GenericController<SaveSubjectGroupDto, SubjectGroup, Long, BindingResult>{
	
}
