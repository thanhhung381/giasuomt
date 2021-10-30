package giasuomt.demo.commondata.generator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.task.repository.ITaskRepository;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TaskCodeGenerator {

	private static String localDateTime = DateTimeUtils.toString(LocalDateTime.now());





	public static String generatorCode() {

		String[] sep = localDateTime.split("-");//tách các chuỗi ra mảng nhỏ
		//vd:2014-12-12 10:54:32
		String year = sep[0]; //2014

		String month = sep[1];//12

		String dateAndTime = sep[2];//12 10:54:32

		String dateArrayString[] = dateAndTime.split(" ");//tách 12 10:54:32

		String date = dateArrayString[0];//12

		int yearReal = Integer.valueOf(year);
		

		int yearOrigin = 2021;// lấy ngày làm chuẩn

		int standardFirstLetter = 67;// mã ancii nếu muốn lấy in thường mình trừ cho 32 là

		String codeYear = "";

		int delta = yearReal - yearOrigin;// độ lệch ngày

		codeYear = String.valueOf((char) (standardFirstLetter + delta));

		String codeMonth = String.valueOf(generateFromMonth(month));

		

		codeYear = codeYear.concat(codeMonth.concat(date));

		return codeYear;
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

	public static char generateFromMonth(String month) {
		char ma;
		switch (month) {
		case "01": {
			ma = 65;
			break;
		}
		case "02": {
			ma = 66;
			break;
		}
		case "03": {
			ma = 67;
			break;
		}
		case "04": {
			ma = 68;
			break;
		}
		case "05": {
			ma = 69;
			break;
		}
		case "06": {
			ma = 70;
			break;
		}
		case "07": {
			ma = 71;
			break;
		}
		case "08": {
			ma = 72;
			break;
		}
		case "09": {
			ma = 73;
			break;
		}
		case "10": {
			ma = 74;
			break;
		}
		case "11": {
			ma = 75;
			break;
		}
		case "12": {
			ma = 76;
			break;
		}
		default:
			ma = ' ';
			break;
		}
		return ma;
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
	
	public static int generateResponsiveReserve(String no) {
		int ma;
		switch (no) {
		case "01": {
			ma = 1;
			break;
		}
		case"02" : {
			ma = 2;
			break;
		}
		case "03": {
			ma = 3;
			break;
		}
		case "04": {
			ma = 4;
			break;
		}
		case "05" : {
			ma = 5;
			break;
		}
		case "06": {
			ma = 6;
			break;
		}
		case "07": {
			ma = 7;
			break;
		}
		case "08": {
			ma = 8;
			break;
		}
		case "09": {
			ma = 9;
			break;
		}
		default:
			ma = Integer.parseInt(no);
			break;
		}
		return ma;
	}

}
