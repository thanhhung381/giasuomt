package giasuomt.demo.token.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class TokenService implements ITokenService {
	private static final Integer EXPIRE_MINS = 5;

	private LoadingCache<String, String> otpCache;

	public TokenService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)//set thời gian
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return null;
					}
				});
	}

	public String getOtp(String key) {
		try {
			return otpCache.get(key); // lây giá trị thông qua tên username = key
		} catch (Exception e) {
			return null;
		}
	}

	public String generateCode(String key) {
		Random rd = new Random();

		String token = "";

		List<String> randomNumber = new ArrayList<>();

		for (int i = 0; i < 7; i++) {

			randomNumber.add(String.valueOf(0 + rd.nextInt(9)));

		}

		for (int i = 0; i < 7; i++) {
			token += randomNumber.get(i);
		}

		otpCache.put(key, token); // lưu vào cache tương ứng tên
		return token;
	}

	// This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key); // xoa các phương thưc cache
	}
}
