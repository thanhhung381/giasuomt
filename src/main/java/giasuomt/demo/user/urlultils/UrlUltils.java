package giasuomt.demo.user.urlultils;

import javax.servlet.http.HttpServletRequest;

public class UrlUltils {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString(); // lay thong tin này từ tên giao thức cho đến chuỗi truy (1)
																// vấn trong dòng đầu tiên của request HTTP
		return siteURL.replace(request.getServletPath(), "");// getServletPath() tra ve mot phan cua url
	}

}

// vi du nhu sau:
	// ban dau url http://localhost:8080/api/user/resetPassword/forgotPassword

//(1): sử dụng getRequestURL-> trở thành như sau: http://localhost:8080/api/user/resetPassword/forgotPassword
//(2): Sử dụng getServletPath->/api/user/resetPassword/forgotPassword -> sẽ ra http://localhost:8080