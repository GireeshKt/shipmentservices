package com.blumeglobal.shipmentmanagement.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.service.helper.DispatchConstants;




/**
 * The DateUtil component class is used to manage date conversions.
 *
 */
@Component
public class DateUtils
{
	private  static Log logger = LogFactory.getLog(DateUtils.class);

	private DateUtils() {
		
	}
	/**
	 * This method is used to convert the date to String
	 * 
	 * @param date - This is used to pass input date to convert
	 * @return String - return converted string value
	 *
	 */
	public static String convertDateToString(Date date)
	{
		if (date != null ) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYY_HHMM);
			return dateFormat.format(date);
		}
		return null;
	}

	/**
	 * This method is used to convert the date to String with date pattern
	 * 
	 * @param date - This is used to pass input date to convert
	 * @param date - This is used to pass user defined to date pattern
	 * @return String - return converted string value
	 *
	 */
	public static String convertDateToString(Date date, String datePattern)
	{
		if (date != null ) {
			SimpleDateFormat dateFormat = null;
			if (datePattern != null && datePattern.length() > 0) {
				dateFormat = new SimpleDateFormat(datePattern);
			} else {
				dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYY) ;
			}
			return dateFormat.format(date);
		}
		return null;
	}
	/**
	 * This method is used to convert the String to Date
	 * 
	 * @param String - This is used to pass input string to convert
	 * @return Date - return converted date value
	 *
	 */

	public static Date convertStringToDate(String dateStr)
	{

		SimpleDateFormat dateFormat = null;	
		try
		{
			if (dateStr != null && (dateStr.length() > 0) ){
				String[] colonArr = dateStr.split(":");
				if (dateStr.contains("-")) {
					if(colonArr.length == 3){ //separated by hyphens
						dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS);
					}else{
						dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
					}
				} else  { //separated by slashes
					if(colonArr.length == 3){
						dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYYYY_HHMMSS);
					}else{
						dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYYYY_HHMM);
					}
					
				}
				return dateFormat.parse(dateStr);
			}
		}
		catch (ParseException pe) {
			logger.debug("Exception in convertStringToDate:" + pe.getMessage(), pe);
		}
		return null;
	}
	/**
	 * This method is used to convert the String to Date
	 * 
	 * @param String - This is used to pass input string to convert
	 * @return Date - return converted date value
	 *
	 */
	public static Date convertStringToDate(String dateStr, String datePattern)
	{
		try {
			if (dateStr != null && (dateStr.length() > 0) ) {
				SimpleDateFormat dateFormat = null;
				if (datePattern != null && datePattern.length() > 0) {
					dateFormat = new SimpleDateFormat(datePattern) ;
				} else {
					dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYYYY_HHMM);
				}
				return dateFormat.parse(dateStr);
			}else {
				return null;
			}
		}
		catch (ParseException pe) {
			logger.debug("\nException in convertStringToDate  :" + pe.getMessage(), pe);
		}
		return null;
	}

	public static Timestamp convertStringToTimestamp(String dateStr, String datePattern)
	{
		try {
			if (dateStr != null && (dateStr.length() > 0) ) {
				SimpleDateFormat dateFormat = null;
				if (datePattern != null && datePattern.length() > 0) {
					dateFormat = new SimpleDateFormat(datePattern) ;
				} else {
					dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MMDDYYYY_HHMM);
				}
				 Date date = (Date) dateFormat.parse(dateStr);
			   java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				return timeStampDate;
			}else {
				return null;
			}
		}
		catch (ParseException pe) {
			logger.debug("\nException in convertStringToDate  :" + pe.getMessage(), pe);
		}
		return null;
	}
	
	public static String convertToString(String dateStr) throws ParseException {
		
			if (dateStr != null) {
				Date dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_YYYY_MM_DD_HH_SS_SSS).parse(dateStr);
				String date = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM_SS).format(dateFormat);
				return date;
			}else {
				return null;
			}
	}
	/*
	 * Returns today as a java.util.Date
	 * @return a java.util.date  object
	 */
	public static java.util.Date getNow()
	{
		logger.debug("DateUtil: getNow entered...");
		Calendar calendar = new GregorianCalendar();
		return calendar.getTime();
	}

	public static String getNowAsString()
	{
		return convertDateToString(getNow());
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * @param string - This is used to pass input string to convert
	 * @return the current date
	 */
	public static Calendar getToday(String datePattern)
	{
		Date today = new Date();
		if (datePattern == null ) {
			datePattern = DispatchConstants.DATE_FORMAT_MMDDYYYY;
		}
		SimpleDateFormat df = new SimpleDateFormat(datePattern);
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	// Move the calendar to the beginning of the Week
	
	/**
	 * This method is used to know given input is business day or not 
	 * 
	 * @param Date - This is used to pass input date
	 * @return Boolean - return true or false
	 *
	 */
	public static boolean isBusinessDay(Date date)
	{
		Calendar cal = new GregorianCalendar();
		if (date != null ) {
			cal.setTime(date);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if ( (dayOfWeek > 1) && (dayOfWeek < 7) ) {
				return true;
			}
		}
		return false;
	}

	public static String getDateByDaysOffset(String datePattern, int offset, Date date)
	{
		if (datePattern == null ) {
			datePattern = DispatchConstants.DATE_FORMAT_MMDDYYYY;
		}
		if ( date == null ) {
			date = getNow();
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern) ;
		cal.add(Calendar.DATE, offset);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * This method is used to convert Util date to  SQL Date
	 * 
	 * @param java.Util.Date - This is used to pass input util date to sql date
	 * @return java.sql.Date - return converted sql date value
	 *
	 */	
	public static java.sql.Date convertutilDatetoSqlDate(Date dateutil)
	{
		return new java.sql.Date(dateutil.getTime());
	}

	public static java.util.Date convertWithTries(String dateStr) {

		String[] datePattern = new String[5];
		datePattern[0] = "dd-MMM-yy";
		datePattern[1] = "MM/dd/yy HH:mm";
		datePattern[2] = "MM/dd/yy";
		datePattern[3] = "dd-MMM-yy HH:mm";
		datePattern[4] = "MM-dd-yyyy";

		for(int i = 0; i< datePattern.length; i++) {

			try {
				if (dateStr != null && (dateStr.length() > 0) ) {
					SimpleDateFormat dateFormat = null;
					if (datePattern[i] != null && datePattern[i].length() > 0) {
						dateFormat = new SimpleDateFormat(datePattern[i]) ;
					} else {
						dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
					}
					return dateFormat.parse(dateStr);
				}
			}
			catch (ParseException pe) {
				logger.debug("\nException in convertStringToDate  :" + pe.getMessage(), pe);
			}
		}

		return null;
	}
	/**
	 * This method is used to convert the String to parsed Date
	 * 
	 * @param String - This is used to pass input string to convert
	 * @return Date - return converted  parsed date value
	 *
	 */
	//MEthod Aded for Reports
	public static Date getParsedDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		if(dateStr != null && !"".equals(dateStr)){
			return sdf.parse(dateStr);
		}
		return null;
	}
	public  Date getConvertDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		if(dateStr != null && !"".equals(dateStr)){
			try{
				return sdf.parse(dateStr);
				
			}catch(Exception ex) {
				return null;
			}
			
		}
		return null;
		
	}
	

	//	FIX BUF 335
	/**
	 * This method is used to convert the String to formated Date
	 * 
	 * @param String - This is used to pass input string to convert
	 * @return Date - return converted formated date value
	 *
	 */
	public static String getFormatedDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		if(date != null ){
			return sdf.format(date);
		}
		return null;
	}

	public static String getFormatedDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if(date != null){
			return sdf.format(date);
		}
		return null;
	}
	/**
	 * This method is used to Adjust the Time Zone
	 * 
	 * @param Date - This is used to pass input date with time zone to convert
	 * @return Date - return converted  time zone date value
	 *
	 */
	public static String adjustTimezone(Date inputDate, String targetTimezone){
		String convertedDate = null;
	   try
	   {
	   DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	   df2.setTimeZone(TimeZone.getTimeZone(targetTimezone));
	   convertedDate = df2.format(inputDate);
	   }
	   catch(Exception e){
		   if(logger.isErrorEnabled()){
			   logger.error("Exception occurred in adjustTimezone");
		   }
	   }

	return convertedDate;
	}

	/**
	 * Gets the date from specific time zone to local timezone.
	 *
	 * @param date the date
	 * @param timeZone the time zone
	 * @return the date in local time zone
	 */
	public static Date convertSpecificTimezoneDateIntoLocal(Date date, String timeZone){
		if(null != timeZone){
			TimeZone tz = TimeZone.getTimeZone(timeZone);

			// Getting time in facility timezone
			Calendar localWorkOrderDateCal = Calendar.getInstance(tz);
			localWorkOrderDateCal.setTime(date);

			// Converting facility timezone into local timezone
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTimeInMillis(localWorkOrderDateCal.getTimeInMillis());
			localWorkOrderDateCal.set(Calendar.DATE, localCalendar.get(Calendar.DATE));
			localWorkOrderDateCal.set(Calendar.MONTH, localCalendar.get(Calendar.MONTH));
			localWorkOrderDateCal.set(Calendar.YEAR, localCalendar.get(Calendar.YEAR));
			localWorkOrderDateCal.set(Calendar.HOUR_OF_DAY, localCalendar.get(Calendar.HOUR_OF_DAY));
			localWorkOrderDateCal.set(Calendar.MINUTE, localCalendar.get(Calendar.MINUTE));

			return localWorkOrderDateCal.getTime();

		}
		else {
			return null;
		}
	}
	/**
	 * This method is used to convert the Specific Time Zone date to Facility Date
	 * 
	 * @param String - This is used to pass input specific time zone to convert
	 * @return Date - return converted facility date value
	 *
	 */
	public static Date convertSpecificTimezoneDateIntoFacility(Date date, String timeZone){
		if(null != timeZone){
			TimeZone tz = TimeZone.getTimeZone(timeZone);

			// Getting time in facility timezone
			Calendar localWorkOrderDateCal = Calendar.getInstance();
			localWorkOrderDateCal.setTime(date);

			// Converting facility timezone into local timezone
			Calendar localCalendar = Calendar.getInstance(tz);
			localCalendar.setTimeInMillis(localWorkOrderDateCal.getTimeInMillis());
			localWorkOrderDateCal.set(Calendar.DATE, localCalendar.get(Calendar.DATE));
			localWorkOrderDateCal.set(Calendar.MONTH, localCalendar.get(Calendar.MONTH));
			localWorkOrderDateCal.set(Calendar.YEAR, localCalendar.get(Calendar.YEAR));
			localWorkOrderDateCal.set(Calendar.HOUR_OF_DAY, localCalendar.get(Calendar.HOUR_OF_DAY));
			localWorkOrderDateCal.set(Calendar.MINUTE, localCalendar.get(Calendar.MINUTE));

			return localWorkOrderDateCal.getTime();

		}
		else {
			return null;
		}
	}


	/**
	 * Gets the date before minute.
	 *
	 * @param timeInMillis the time in millis
	 * @param mins the mins
	 * @return the date before minute
	 */
	public static Date getDateBeforeMinute(Long timeInMillis, int mins ){
		try {
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(timeInMillis);
			calender.add(Calendar.MINUTE, (-1) * mins);
			return calender.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Get the date after one hour
	 */

	public static Date getDateAfterHours(int hours ){
		try {
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.HOUR_OF_DAY, hours);
			return calender.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Convert valid string to date.
	 *
	 * @param dateStr the date str
	 * @param datePattern the date pattern
	 * @return the date
	 */
	public static Date convertValidStringToDate(String dateStr, String datePattern) {
		Date returnDate = null;
		try {
			if (dateStr != null && (dateStr.length() > 0) ) {
				SimpleDateFormat dateFormat = null;
				if (datePattern != null && datePattern.length() > 0) {
					dateFormat = new SimpleDateFormat(datePattern) ;
				} else {
					dateFormat = new SimpleDateFormat(DispatchConstants.DATE_FORMAT_MM_DD_YYYY_HH_MM);
				}
				dateFormat.setLenient(false);
				returnDate =  dateFormat.parse(dateStr);
			}
		}
		catch (ParseException pe) {
			logger.debug("ParseException in convertValidStringToDate.");
		}
		return returnDate;
	}
	
	
	/**
	 * Return the number of days the second date is ahead of first one. Date d2 must be greater than d1
	 *
	 * @param d1 the d1
	 * @param d2 the d2
	 * @return the diff in days
	 */
	public static int getDiffInDays(Date d1, Date d2){
		Date minDate = getStartTimeOfDay(d1);
		Integer millisecsInDay = 86400000;
		return  (int)((d2.getTime() - minDate.getTime())/millisecsInDay);
	}
	
	
	/**
	 * Takes a date as input parameter and returns the start time of the day, i.e. date having time as 00:00:00
	 *
	 * @param date the date
	 * @return Date
	 */
	public static Date getStartTimeOfDay(Date date){
	   	Calendar cal = Calendar.getInstance();
	   	cal.setTime(date);
	   	cal.set(Calendar.HOUR_OF_DAY, 0);
	   	cal.set(Calendar.MINUTE, 0);
	   	cal.set(Calendar.SECOND, 0);
	   	cal.set(Calendar.MILLISECOND, 0);
	   	return cal.getTime();
	}
	
	public static Date getBeforeDates(Date date, int noofDaysBefore) {
		Calendar cal = Calendar.getInstance();
	   	cal.setTime(date);
	   	cal.add(Calendar.DATE, noofDaysBefore);
	   	return cal.getTime();
	}
	public static Date getAfterDates(Date date, int noofDaysAfter) {
		Calendar cal = Calendar.getInstance();
	   	cal.setTime(date);
	   	cal.add(Calendar.DATE, noofDaysAfter);
	   	return cal.getTime();
	}
	 public static boolean validateDateFormat(String dateToValdate) {

		    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		    //To make strict date format validation
		    formatter.setLenient(false);
		    Date parsedDate = null;
		    try {
		        parsedDate = formatter.parse(dateToValdate);
		       // System.out.println("++validated DATE TIME ++"+formatter.format(parsedDate));

		    } catch (ParseException e) {
		    	return false;
		    }
		    return true;
		}
	 public static Date validDate(Date date) {
		 try {
				
		     SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
		     format.setLenient(false);
		     Date date1=new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS").parse(date.toString());  
		     return date1;
	}catch(Exception ex) {
		return null;
	}
	 }
}
