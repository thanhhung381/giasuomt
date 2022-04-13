package giasuomt.demo.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import giasuomt.demo.user.model.User;

public interface IMailService {
	public void sendEmail(String email,User user,String urlUpdatePassword,String token) throws UnsupportedEncodingException, MessagingException;
}
