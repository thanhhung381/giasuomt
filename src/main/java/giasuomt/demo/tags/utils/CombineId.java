package giasuomt.demo.tags.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

public class CombineId {
	
	
	
	private static String removeAccent(String s)
	{
		String temp=Normalizer.normalize(s, Form.NFD);
		
		Pattern pattern=Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		
		return pattern.matcher(temp).replaceAll("");
	}
	
	
	private static String  nomolizeForm(String s)
	{
		return removeAccent(s).toLowerCase().replace(" ","");
	}
	
	
	public  static String combineTagGroupAndTagName(String tagGroup , String tagName)
	{
		
		
		
		return nomolizeForm(tagGroup)+"-"+nomolizeForm(tagName);
	}
}
