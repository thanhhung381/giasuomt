package giasuomt.demo.task.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.model.Application;
import lombok.AllArgsConstructor;

@RequestMapping("/api/application")
@RestController
@AllArgsConstructor
public class ApplicationController  extends GenericController<SaveApplicationDto, Application, Long, BindingResult>{

}
