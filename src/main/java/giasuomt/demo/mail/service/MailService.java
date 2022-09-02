package giasuomt.demo.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import giasuomt.demo.user.model.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService implements IMailService {

	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String email, User user,String urlUpdatePassword,String token) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("khangle1904041@gmail.com", "khang le supporter");
		helper.setTo(email);
		String subject = "Here's token to reset your password"; // title cu link
		String content = "<p>Dear," + user.getUsername() + "</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Here is token and  the link below to authenticate your token: </p>" + "<p> <b>"
				+ token + " </p>" + "<br>" + "<p><a href=\"" + urlUpdatePassword.concat("/swagger-ui.html".concat("#!/user45controller/updatePasswordUsingPUT"))
				+ "\">link to change password</a></p>" + "<br>"

				+ "<p>Ignore this email if you do remember your password, " + "or you have not made the request.</p>"
				+ "<br>" + "<p>Best Regards</p>";

		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);

	}

}
