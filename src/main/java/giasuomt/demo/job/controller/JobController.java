package giasuomt.demo.job.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.model.Job;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/job")
public class JobController extends GenericController<SaveJobDto,Job, Long, BindingResult> {

}
