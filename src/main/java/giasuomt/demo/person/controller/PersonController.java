package giasuomt.demo.person.controller;

import java.util.List;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;

import giasuomt.demo.person.dto.SavePersonDto;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.person.service.IPersonService;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;
import giasuomt.demo.uploadfile.service.IAvatarService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/person")
@AllArgsConstructor
public class PersonController extends GenericController<SavePersonDto, Person, Long, BindingResult> {

}
