package giasuomt.demo.tutorReview.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.task.service.IApplicationService;
import giasuomt.demo.tutorReview.dto.SaveTutorReviewDto;
import giasuomt.demo.tutorReview.model.TutorReview;
import lombok.AllArgsConstructor;

@RequestMapping("/api/tutorReview")
@RestController
@AllArgsConstructor
public class TutorReviewController extends GenericController<SaveTutorReviewDto, TutorReview, Long> {

}
