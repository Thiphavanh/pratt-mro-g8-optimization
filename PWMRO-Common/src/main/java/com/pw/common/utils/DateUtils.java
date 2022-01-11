/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    DateUtils.java
* 
*  Created: 2017-08-08
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-08 		c079222		    Initial Release XXXXXXX
* 2018-10-02	Bob Preston		SoluminaOOB, defect 970 -- Added getNowClientTimeStamp().
* 2018-10-11	Bob Preston		SoluminaOOB, defect 970 -- Added getNowClientTimeStamp(serverTZ, clientTZ).
*/
package com.pw.common.utils;

import java.util.*;

import com.ibaset.common.util.SoluminaUtils;

import java.text.*;

//import com.pwusa.solumina.oagis.pwOagisSegments.DATETIME;

public class DateUtils {
	
  public static final String COLON = ":";		///2012-12-19 SIQA_TR_T7
  public static final String PLUS = "+";		///2012-12-19 SIQA_TR_T7
  public static final String SLASH = "/";		///2012-12-19 SIQA_TR_T7
  public static final String SPACE = " ";		///2012-12-19 SIQA_TR_T7
  
  
  final static String[] MONTHSTRINGS = { "January", "February", "March",
                                         "April", "May", "June", "July",
                                         "August", "September", "October",
                                         "November", "December"};
   

  final static int[] daysinmonth = { 31,28,31,30,
                                     31,30,31,31,
                                     30,31,30,31 };

  final static String[] WEEKDAYSTRINGS = { "Sunday", "Monday", "Tuesday",
                                           "Wednesday", "Thursday",
                                           "Friday", "Saturday"};

//2013-02-12 SEPL_OP_13, Defect 797
  static final SimpleDateFormat timestampformat
  		   = new SimpleDateFormat ("yyyyMMddHHmmss");
//2013-02-12 SEPL_OP_13, Defect 797
  
  static final SimpleDateFormat datetimeformatter
           = new SimpleDateFormat ("MM/dd/yyyy HH:mm");

  static final SimpleDateFormat dateformatter
           = new SimpleDateFormat ("MM/dd/yy");
  
  static final SimpleDateFormat datefullyearformatter
			 = new SimpleDateFormat ("MM/dd/yyyy");
  
  static final SimpleDateFormat dateTimeFullYearFormatter
  			= new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");

  static final SimpleDateFormat nasaformatter
           = new SimpleDateFormat ("yyMMdd");

  static final SimpleDateFormat monthdayformatter
           = new SimpleDateFormat ("MM/dd");

  static final SimpleDateFormat dayformatter
           = new SimpleDateFormat ("dd");
 
  static final SimpleDateFormat longdatetimeformatter 
  		   = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 		   

	static final SimpleDateFormat mediumdatetimeformatter 
			 = new SimpleDateFormat("dd-MMM-yyyy HH:mm"); 		   

  static final SimpleDateFormat xmlDatetimeFormatter			///2012-12-19 SIQA_TR_T7
			= new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
  
  static final SimpleDateFormat amPMDatetimeFormatter			// 2013-02-06 SEQA_QN_08
	        = new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss a");
	  

  static final SimpleDateFormat t20DateTimeFullYearFormatter	///2013-06-05 SIOE_TR_T20 (Prolink)
			= new SimpleDateFormat ("MM/dd/yyyy, HH:mm:ss");
  
  public static java.util.Date getAdjustedDate(java.util.Date date,
                                               int adjust) {

    Calendar aCalendar = Calendar.getInstance();
    aCalendar.setTime(date);
    aCalendar.add(Calendar.DAY_OF_MONTH,adjust);

    return aCalendar.getTime();

  }


 public static String getTimeDifference(java.util.Date date1,
              java.util.Date date2) {

		long difference = 0;
		
		if (date1.getTime() > date2.getTime()) {
			difference = date1.getTime()-date2.getTime();
		} 
		else {
			difference = date2.getTime()-date1.getTime();
		}
		long minutes = difference/(1000*60);
		long hours = difference/(3600*1000);
		long hminutes = hours*60;
		long lminutes = minutes-hminutes;
		
		StringBuffer sb = new StringBuffer(1);
		sb.append( hours ).append( " Hrs " );
		
		if (lminutes>0) {
			sb.append( lminutes ).append( " Min" );
		}
		
		return sb.toString();

	  }

  /**
   * getDateString()
   * @param date as string
   */
  public static String getDateString(Date date) {

    if (date == null) return "";
    return dateformatter.format(date);

  }

  /**
   * getDateYearString()
   * @param date as date
   */
  public static String getDateYearString(Date date) {

	if (date == null) return "";
	return datefullyearformatter.format(date);

  }
  
  public static String getDateTimeYearString(Date date) {

	if (date == null) return "";
	return dateTimeFullYearFormatter.format(date);

  }  

  public static String getNasaString(Date date) {

    if (date == null) return "";
    return nasaformatter.format(date);

  }

  /**
   * getDateTimeString()
   * @param date as string
   */
  public static String getDateTimeString(Date date) {

    if (date==null) return "";
    return datetimeformatter.format(date);

  }

  public static String getLongDateTimeString(Date date) {

    if (date==null) return "";
    return longdatetimeformatter.format(date);

  }

  public static String getOracleDateTimeString(Date date) {
  	if (date==null) return "";
  	return mediumdatetimeformatter.format(date);
  	
  }

  public static String getMonthString(int month) {

    return MONTHSTRINGS[month];

  }

  public static String getDOWString(int dow) {

    return WEEKDAYSTRINGS[dow];

  }

  public static int getLastDay(int month, int year) {

    int last=daysinmonth[month];
    if (year % 4 == 0 && month==1) last++;

    return last;

  }

  //------------------------------------------------------------
  // date conversions
  //-------------------------------------------------------------
  /**
   * getDate()
   * @param date as string
   */
  public static java.util.Date getUtilDate(String dateString)
                throws ParseException {

    java.util.Date utilDate = null;
    dateString=dateString.trim();

    if (dateString.indexOf(":") != -1) {
      utilDate = datetimeformatter.parse(dateString);
    } else {
      utilDate = dateformatter.parse(dateString);
    }

    return utilDate;

  }

  public static java.util.Date getUtilLongDate(String dateString)
                throws ParseException {

    java.util.Date utilDate = null;
    dateString=dateString.trim()+":00";

    if (dateString.indexOf(":") != -1) {
      utilDate = longdatetimeformatter.parse(dateString);
    } else {
      utilDate = dateformatter.parse(dateString);
    }

    return utilDate;

  }

  public static String getMonthDayString(java.util.Date date) {

    if (date == null) return "";
    return monthdayformatter.format(date);

  }

  public static String getDayString(java.util.Date date) {

    if (date == null) return "";
    return dayformatter.format(date);

  }

//2013-02-12 SEPL_OP_13, Defect 797
  public static String getNowTimeStamp()
  {
	  Calendar cal = Calendar.getInstance();
	  String currentTimeStamp = timestampformat.format(cal.getTime());
	  return currentTimeStamp;
  }
//2013-02-12 SEPL_OP_13, Defect 797
  
  
  // Begin defect 970
  public static String getNowClientTimeStamp() {
	  Calendar cal = Calendar.getInstance();
	  Date date = SoluminaUtils.tzShiftToClient(cal.getTime());
	  String currentTimeStamp = timestampformat.format(date);
	  return currentTimeStamp;
  }
  // End defect 970
  
  
  // Begin defect 970
  private static long toMinutes(long offset) {
	  return offset / 60L / 1000L;
  }
  
  public static String getNowClientTimeStamp(String serverTimeZone, String clientTimeZone)
  {
	  // Derive client time with only server time, server time zone and client time zone 
	  // (including daylight savings time adjustments):
	  
	  
	  // Compute offsets in minutes to correct a Java bug and for my sanity.
	  long toMillis = 60L * 1000L;
	  
	  
	  TimeZone serverTZ = null;
	  if ((serverTimeZone == null) || (serverTimeZone.equals(""))) {
		  serverTZ = TimeZone.getDefault();
	  } else {
		  serverTZ = TimeZone.getTimeZone(serverTimeZone);
	  }
	  if ((serverTZ == null) || serverTZ.getID().equals("GMT")) {
		  throw new RuntimeException("Invalid server time zone: " + serverTZ);
	  }
	    
	  TimeZone clientTZ = TimeZone.getTimeZone(clientTimeZone);
	  if ((clientTZ == null) || clientTZ.getID().equals("GMT")) {
		  throw new RuntimeException("Invalid client time zone: " + clientTZ);
	  }
	    
	  SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
	  format.setTimeZone(serverTZ);
	  
	  long serverTime = new Date().getTime();
	  
	  long serverOffset = serverTZ.getOffset(serverTime);
	  serverOffset = toMinutes(serverOffset);
	  
	  long clientOffset = clientTZ.getOffset(serverTime);
	  clientOffset = toMinutes(clientOffset);
	  
	  // Make an educated guess for client time.
	  long clientTime = serverTime + ((clientOffset - serverOffset) * toMillis);
	  
	  long serverRawOffset = serverTZ.getRawOffset() & 0xffffffff;
	  serverRawOffset = toMinutes(serverRawOffset);
	  
	  long clientRawOffset = clientTZ.getRawOffset() & 0xffffffff;
	  clientRawOffset = toMinutes(clientRawOffset);
	  
	  long serverDaylightDelta = serverTZ.getDSTSavings() & 0xffffffff;
	  serverDaylightDelta = toMinutes(serverDaylightDelta);
	  
	  long clientDaylightDelta = clientTZ.getDSTSavings() & 0xffffffff;
	  clientDaylightDelta = toMinutes(clientDaylightDelta);
	  
	  long clientOffset2 = clientTZ.getOffset(clientTime);
	  clientOffset2 = toMinutes(clientOffset2);
	  
	  // Compute possible client time.
	  long clientTime2 = serverTime + ((clientOffset2 - serverOffset) * toMillis);
	  
	  long standardstandardTime = (clientRawOffset - serverRawOffset);
      long daylightdaylightTime = (clientRawOffset + clientDaylightDelta) - (serverRawOffset + serverDaylightDelta);
      long standarddaylightTime = ((clientRawOffset + clientDaylightDelta) - serverRawOffset);
      long daylightstandardTime = (clientRawOffset - (serverRawOffset + serverDaylightDelta));
	  
	  if (serverTZ.inDaylightTime(new Date(serverTime))) {
		  if (clientTZ.inDaylightTime(new Date(clientTime2))) {
			  // System.out.println("daylight/daylight");
			  if (clientTime == clientTime2) {
				  clientTime = serverTime + (daylightdaylightTime * toMillis);
			  }
		  } else if (clientTime == clientTime2) {
			  // System.out.println("daylight/standard");
			  clientTime = serverTime + (daylightstandardTime * toMillis);
		  }
	  } else {
		  if (clientTZ.inDaylightTime(new Date(clientTime2))) {
			  // System.out.println("standard/daylight");
			  if (clientTime == clientTime2) {
				  clientTime = serverTime + (standarddaylightTime * toMillis);
			  }
		  } else if (clientTime == clientTime2) {
			  // System.out.println("standard/standard");
			  clientTime = serverTime + (standardstandardTime * toMillis);
		  }
	  }

	  if (!serverTZ.inDaylightTime(new Date(serverTime)) && serverTZ.inDaylightTime(new Date(clientTime))) {
		  // SimpleDateFormat factors this in.
		  clientTime -= serverDaylightDelta * toMillis;
	  } else if (serverTZ.inDaylightTime(new Date(serverTime)) && !serverTZ.inDaylightTime(new Date(clientTime))) {
		  // SimpleDateFormat factors this in.
		  clientTime += serverDaylightDelta * toMillis;
	  }
	  
	  String currentTimeStamp = format.format(new Date(clientTime));
	  return currentTimeStamp;
  }
  // End defect 970

  
  /**
   * @param date as string
   */
  public static Date getMonthStartDate()
                throws ParseException {

    Calendar cal;
    cal = Calendar.getInstance();
    int day=cal.get(Calendar.DAY_OF_MONTH);
    cal.add(Calendar.DAY_OF_MONTH, -day+1);

    return cal.getTime();

  }

  /**
   * get month length
   * @param date as string
   */
  public static int[] getMonthDetails(java.util.Date date)
                throws ParseException {

    int[] details = new int[4];

    Calendar calendar = Calendar.getInstance();
    calendar.setTime((java.util.Date) date);

    details[0] = calendar.get(Calendar.MONTH);
    details[1] = calendar.get(Calendar.YEAR);
    details[2] = calendar.get(Calendar.DAY_OF_MONTH);
    details[3] = DateUtils.getLastDay(details[0], details[1]);

    return details;

  }

  /**
   * get month length
   * @param date as string
   */
  public static Calendar[] getCalendars(java.util.Date date)
                throws ParseException {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime((java.util.Date) date);

    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    Calendar[] cals=new Calendar[32];
    int last = DateUtils.getLastDay(month, year);

    for (int c=1;c<=last;c++) {
      cals[c] = Calendar.getInstance();
      cals[c].set(year,month,c);
    }

    return cals;

  }

  /**
   * get month length
   * @param date as string
   */
  public static Calendar[] getLastCalendars(java.util.Date date, int count)
                throws ParseException {

    Calendar fixedcalendar = Calendar.getInstance();
    fixedcalendar.setTime((java.util.Date) date);

    int month = fixedcalendar.get(Calendar.MONTH);
    int year = fixedcalendar.get(Calendar.YEAR);

    Calendar[] cals = new Calendar[count];
    int last=DateUtils.getLastDay(month, year);

    for (int c=0; c<count; c++) {
      cals[c] = Calendar.getInstance();
      cals[c].add(Calendar.DAY_OF_MONTH, -c);
    }
    return cals;

  }

  public static String getMonth(java.util.Date date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime((java.util.Date)date);

    return getMonthString(calendar.get(Calendar.MONTH));

  }

  public static int get2digitsMonth(java.util.Date date) {

	Calendar calendar = Calendar.getInstance();
	calendar.setTime((java.util.Date)date);

	return calendar.get(Calendar.MONTH);

  }

  public static String getYear(java.util.Date date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime((java.util.Date)date);

    return Integer.toString(calendar.get(Calendar.YEAR));

  }

  public static java.util.Date getCalculatedDate(java.util.Date date,
                                               int adjust) {

    Calendar aCalendar = Calendar.getInstance();
    aCalendar.setTime(date);
    aCalendar.add(Calendar.DATE,adjust);

    return aCalendar.getTime();

  }
  

  public static String getPrevYear(java.util.Date date) {

	Calendar calendar = Calendar.getInstance();
	calendar.setTime((java.util.Date)date);
	calendar.add(Calendar.YEAR, -1);

	return Integer.toString(calendar.get(Calendar.YEAR));

  }

  
  public static java.util.Date getXmlUtilLongDate(String dateString)
		  		throws ParseException 
{																///2012-12-19 SIQA_TR_T7

	java.util.Date utilDate = null;
	dateString=dateString.trim();  /////+":00";
	
	if (dateString.indexOf(":") != -1) 
	{
		utilDate = xmlDatetimeFormatter.parse(dateString);
	} 
	else 
	{
		utilDate = dateformatter.parse(dateString);
	}
	
	return utilDate;

}
  
  public static java.util.Date getamPMDate(String dateString)
	  		throws ParseException 
{															// 2013-02-06 SEQA_QN_08	

	java.util.Date utilDate = null;
	dateString=dateString.trim();  /////+":00";
	
	if (dateString.indexOf(":") != -1) 
	{
		utilDate = amPMDatetimeFormatter.parse(dateString);
	} 
	else 
	{
		utilDate = dateformatter.parse(dateString);
	}
	
	return utilDate;

}
  

/*  public java.util.Date pwueXmlGetDate(DATETIME xmlDateTime)
  {																		 ///2012-12-19 SIQA_TR_T7	
	java.util.Date outDate = null;
	
	String dateString = null;
	
	dateString = 	xmlDateTime.getMONTH() + DateUtils.SLASH + xmlDateTime.getDAY()  + DateUtils.SLASH + 
					xmlDateTime.getYEAR() + DateUtils.SPACE + xmlDateTime.getHOUR() + DateUtils.COLON + 
			  		xmlDateTime.getMINUTE() + DateUtils.COLON + xmlDateTime.getSECOND();
	
	try {
		outDate = getXmlUtilLongDate(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		outDate = null;
	}
	
	return outDate;
	
  }*/
  

  public static String getNowT20DateTimeStamp() 	///2013-06-05 SIOE_TR_T20 (Prolink)
  {
	  Calendar cal = Calendar.getInstance();
	  String currentTimeStamp = t20DateTimeFullYearFormatter.format(cal.getTime());
	  return currentTimeStamp;
  }
  
}
