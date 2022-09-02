package giasuomt.demo.job.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.job.dto.SaveJobProgressDto;
import giasuomt.demo.job.model.JobProgress;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/jobprogress")
public class JobProgressController extends GenericController<SaveJobProgressDto, JobProgress, Long> {

}
