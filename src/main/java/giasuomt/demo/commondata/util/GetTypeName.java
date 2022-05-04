package giasuomt.demo.commondata.util;

import java.lang.reflect.ParameterizedType;

public class GetTypeName<T> {
	
	private String name;
	
	public  String getGenericName()
	{
		return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
	}
	
	private String getName(String name)
	{
		name =getGenericName();
		return name;
	}

}
