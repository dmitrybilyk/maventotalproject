package com.learn.bases.datatypes;

import org.apache.tools.ant.util.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class datetest {
	//	public static final String DATE_REGEX_PATTERN = "dd.MM.yyyy";
	public static final String DATE_REGEX_PATTERN = "[\\d]{1,2}.[\\d]{1,2}.[\\d]{4}";

	public static void main(String[] args)
	{

//		String dateString = "17/07/2015 00:00:00";
//		String cutString = dateString.substring(0, dateString.length() - 10);
//		try
//		{
//			Date date1 = new SimpleDateFormat(DATE_TIME_SLASH_PATTERN).parse(dateString);
//            String s = DateUtils.format(date1, DATE_SLASH_PATTERN);
//            System.out.println(s);
//        } catch (ParseException e) {
//
//        }
//
//        try {
//
//            Date d =  new SimpleDateFormat(DATE_SLASH_PATTERN).parse(dateString);
//            String ss = new SimpleDateFormat(DATE_SLASH_PATTERN).format(d);
//
//            System.out.println("dfdsfsd" +new Date().getTime());
////            System.out.println(ss);
//        } catch (ParseException e) {
////            throw new RuntimeException(String.format("Date %s doesn't have valid date pattern (%s)", dateString, datePattern));
//        }


		String dateString = "30.05.1220";
//		String dateString = "hernya";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_REGEX_PATTERN);
		if ( ! dateString.matches("^[\\d]{1,2}.[\\d]{1,2}.[\\d]{4}$"))
//		if ( ! dateString.matches("^\\s*[\\d]{1,2}.[\\d]{1,2}.[\\d]{4}\\s*$"))
		{
			System.out.println("matches error");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		simpleDateFormat.setLenient(false);
		try
		{
			Date date = simpleDateFormat.parse(dateString);
			System.out.println(date);
//			if (!dateString.matches(DATE_REGEX_PATTERN))
//			{
//				throw new ParseException(String.format("the day value \"%s\" doesn't corresponds to yyyy format", dateString), 0);
//			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

    }


}
