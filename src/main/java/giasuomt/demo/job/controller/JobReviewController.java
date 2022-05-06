package giasuomt.demo.job.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.job.dto.SaveJobReviewDto;
import giasuomt.demo.job.model.JobReview;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/jobReview")
@AllArgsConstructor
public class JobReviewController extends GenericController<SaveJobReviewDto, JobReview, Long> {

}
