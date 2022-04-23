package giasuomt.demo.token.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.mail.service.IMailService;
import giasuomt.demo.security.jwt.JwtUltils;
import giasuomt.demo.token.service.ITokenService;
import giasuomt.demo.token.service.TokenService;
import giasuomt.demo.user.dto.EnterEmailDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import giasuomt.demo.user.service.IUserService;
import giasuomt.demo.user.urlultils.UrlUltils;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@RequestMapping("/api/user/resetPassword")
@RestController
@AllArgsConstructor
public class UserForgotPasswordController {

	private IUserService iUserService;

	private IMailService iMailService;

	private ITokenService iTokenService;


	@PostMapping("/forgotPassword")
	public ResponseEntity<Object> forgotPassword(@RequestBody EnterEmailDto dto, HttpServletRequest request) {

		User user = iUserService.findByEmail(dto.getEmail());

		

		if (user==null) 
			return ResponseHandler.getResponse("Your email is invalid", HttpStatus.BAD_REQUEST);
			
			
				
		try {
				String token = iTokenService.generateCode(user.getUsername());
			

				String link = UrlUltils.getSiteURL(request);

				iMailService.sendEmail(dto.getEmail(), user, link, token);

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (MessagingException e) {

				e.printStackTrace();
			}

			return ResponseHandler.getResponse("Your Token is sending your email now", HttpStatus.OK);

	}

}
