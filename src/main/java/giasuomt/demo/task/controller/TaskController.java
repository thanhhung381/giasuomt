package giasuomt.demo.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.dto.ResponseTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.ITaskService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController extends GenericController<SaveTaskDto, Task, Long, BindingResult> {
	
}
