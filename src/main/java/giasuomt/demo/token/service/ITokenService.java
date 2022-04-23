package giasuomt.demo.token.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface ITokenService {
	public String getOtp(String key);

	public String generateCode(String key);

	public void clearOTP(String key);
}
