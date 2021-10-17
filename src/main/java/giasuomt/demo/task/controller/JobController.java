package giasuomt.demo.task.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.task.dto.SaveJobDto;
import lombok.AllArgsConstructor;


@RequestMapping("/api/job")
@RestController
@AllArgsConstructor
public class JobController extends GenericController<SaveJobDto, Job, Long, BindingResult> {

}
