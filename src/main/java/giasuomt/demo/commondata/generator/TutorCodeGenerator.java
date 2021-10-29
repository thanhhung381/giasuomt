package giasuomt.demo.commondata.generator;

import java.time.LocalDateTime;

import giasuomt.demo.commondata.util.DateTimeUtils;

public class TutorCodeGenerator {
	
	private static String localDateTime = DateTimeUtils.toString(LocalDateTime.now());

	public static String generatorCode() {

		String[] sep = localDateTime.split("-");//tách các chuỗi ra mảng nhỏ
		//vd:2014-12-12 10:54:32
		String year = sep[0]; //2014

		String month = sep[1];//12

		String dateAndTime = sep[2];//12 10:54:32

		String dateArrayString[] = dateAndTime.split(" ");//tách 12 10:54:32

		String date = dateArrayString[0];//12

		String codeTutor = "";

		String twoEndNumOfYear = year.substring(2, 4);

		codeTutor=codeTutor.concat(twoEndNumOfYear.concat(date.concat(month)));

		return codeTutor;

	}

	
	public static int AutoGennerate(String day) {

		if(day==null)
		{
			return -1;
		}
		else  
		{
			
				String[] sep = localDateTime.split("-");

				String year = sep[0];

				String month = sep[1];

				String dateAndTime = sep[2];

				String dateArrayString[] = dateAndTime.split(" ");

				String date = dateArrayString[0];
				
				int dayNow = Integer.parseInt(date);//thoi gian hien tai
				
				
				
				String[] sepEnd = day.split("-");

				String yearEnd = sep[0];

				String monthEnd = sep[1];

				String dateAndTimeEnd = sep[2];

				String dateArrayStringEnd[] = dateAndTime.split(" ");

				String dateEnd = dateArrayString[0];

				int dayEnd = Integer.parseInt(dateEnd);//thời gian lấy từ ngày cuối cùng trong DB
				
				if(dayNow>dayEnd)
					return 2;
				else if(dayNow==dayEnd)
					return 3;
				
				return 4;
				
			
		}
		
		
		
	}
	
	public static String generateResponsive(int no) {
		String ma;
		switch (no) {
		case 1: {
			ma = "01";
			break;
		}
		case 2: {
			ma = "02";
			break;
		}
		case 3: {
			ma = "03";
			break;
		}
		case 4: {
			ma = "04";
			break;
		}
		case 5: {
			ma = "05";
			break;
		}
		case 6: {
			ma = "06";
			break;
		}
		case 7: {
			ma = "07";
			break;
		}
		case 8: {
			ma = "08";
			break;
		}
		case 9: {
			ma = "09";
			break;
		}
		default:
			ma = String.valueOf(no);
			break;
		}
		return ma;
	}
}
