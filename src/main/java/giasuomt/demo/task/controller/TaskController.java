package giasuomt.demo.task.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;
import lombok.AllArgsConstructor;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController extends GenericController<SaveTaskDto, Task, Long, BindingResult> {

}
