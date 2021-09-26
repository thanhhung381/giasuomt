package giasuomt.demo.commondata.responseHandler;

import java.util.LinkedList;
import java.util.List;

public class ListUtils {
	//ListUtils.emptyStringList để dùng cho những thằng nào trong code của dự án của mình mà muốn trả về 1 mảng/list rỗng (để mình ko bị cứ new LinkedList<>() hoài gây tốn bộ nhớ)
	public static List<String> emptyStringList = new LinkedList<>();
}
