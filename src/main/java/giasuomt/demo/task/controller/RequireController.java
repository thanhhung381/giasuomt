package giasuomt.demo.task.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.task.dto.SaveRequireDto;
import giasuomt.demo.task.model.Require;

@RequestMapping("/api/require")
@RestController
public class RequireController extends GenericController<SaveRequireDto, Require, Long, BindingResult> {

}
