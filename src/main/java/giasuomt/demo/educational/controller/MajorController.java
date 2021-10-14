package giasuomt.demo.educational.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.educational.dto.SaveMajorDto;
import giasuomt.demo.educational.model.Major;
import lombok.AllArgsConstructor;

@RequestMapping("/api/major")
@RestController
@AllArgsConstructor
public class MajorController extends GenericController<SaveMajorDto, Major, Long, BindingResult> {

}
