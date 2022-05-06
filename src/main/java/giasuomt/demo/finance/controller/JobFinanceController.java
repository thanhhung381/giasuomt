package giasuomt.demo.finance.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.finance.dto.SaveJobFinanceDto;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/jobFinance")
@RestController
@AllArgsConstructor
public class JobFinanceController extends GenericController<SaveJobFinanceDto, JobFinance, Long> {

}
