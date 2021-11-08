package giasuomt.demo.thymeleafcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class StaffpageController {

	
	@RequestMapping(value = {"/index"} ,method = RequestMethod.GET )
	public String index()
	{	
		return "index";
	}
	
	@RequestMapping(value = {"/main-staff"} ,method = RequestMethod.GET )
	public String mainStaff()
	{	
		return "main-staff";
	}
	
}
