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
*  File:    Utils.java
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
*/
package com.pw.common.utils;

//import java.util.*;
//import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
* A Class class.
* <P>
* @author Catrina Nguyen         02/05/2005
*/
public class Utils {
    private static int retryCnt =0;
    private static int retryLimit = 10;


//----------------------------------------------------------
// html utils
//----------------------------------------------------------
public static String tagger(String text,String tag) {
    return "<"+tag+">"+text+"</"+tag+">\n";
}

//----------------------------------------------------------
// html utils
//----------------------------------------------------------
public static void tagger(PrintWriter out,String text,String tag) {
    out.println(tagger(text,tag));
}

public static String nbsp(int count) {
    String newstr="";
    for (int i=0;i<count;i++) newstr+="&nbsp;";
    return newstr;
}

public static String nbsp(String test) {
    if (test==null) return "&nbsp;";
    if (test.length()==0) return "&nbsp;";
    return test;
}

public static String replaceSpaces(String str) {
    String newstr="";
    for (int i=0;i<str.length();i++) {
      if (str.substring(i,i+1).equals(" "))
                    newstr+="&nbsp;";
      else
                    newstr+=str.charAt(i);
    }
    return newstr;
}

public static void printPreText(PrintWriter out,String text) {
    out.println("<pre>"+text+"</pre>\n");
}

public static void printExceptionHtml(Exception ex, PrintWriter out) {
    out.print("<pre>");
    ex.printStackTrace(out);
    out.println("</pre>");
}


//----------------------------------------------------------
// table utils
//----------------------------------------------------------
public static void printTableHtml(PrintWriter out,String[][] data) {
    printTableHtml(out,data,"");
}

public static void printTableHtml(PrintWriter out,String[][] data,String taginfo) {
    out.print("<table "+taginfo+">");
    for (int r=0;r<data.length;r++) {
      out.print("<tr>");
      for (int c=0;c<data[0].length;c++) {
                    if (r==0) out.print("<th>"); else out.print("<td>");
                    out.print(data[r][c]);
                    if (r==0) out.print("</th>"); else out.print("</td>");
      }
      out.println("</tr>");
    }
    out.println("</table>");
}

public static String getHeaderRow(String[] headers) {
    String html="";
    html+=("<tr>");
    for (int c=0;c<headers.length;c++) {
      html+="<th>";
      html+=headers[c];
      html+="</th>";
    }
    html+="</tr>\n";
    return html;
}

public static String getHeaderRow(String[] headers, String[] tdetc) {
    String html="";
    html+=("<tr>");
    for (int c=0;c<headers.length;c++) {
      if (tdetc[c].length()>0)
                    html+="<th "+tdetc[c]+">";
      else
                    html+="<th>";
      html+=headers[c];
      html+="</th>";
    }
    html+="</tr>\n";
    return html;
}

public static String getDetailRow(String[] details) {

    String html="";
    html+=("\n<tr>");
    for (int c=0;c<details.length;c++) {
      html+="\n  <td valign=top>";
      if (details[c].length()>0)
                    html+=details[c];
      else
                    html+="&nbsp;";

      html+="</td>";
    }
    html+="\n</tr>\n";
    return html;
}

public static String getDetailRow(String[] details, String[] tdetc) {
    for (int i=0;i<details.length;i++) System.out.println(i+":"+details[i]);

    String html="";
    html+=("<tr>");
    for (int c=0;c<details.length;c++) {
      if (tdetc[c].length()>0)
                    html+="\n  <td valign=top "+tdetc[c]+">";
      else
                    html+="<td>";

      if (details[c]!=null && details[c].length()>0)
                    html+=details[c];
      else
                    html+="&nbsp;";

      html+="</td>";
    }
    html+="\n</tr>\n";
    return html;
}

//----------------------------------------------------------
// perlish utils
//----------------------------------------------------------
public static String join(String[] s,String delim) {
    String newstring=s[0];
    for (int i=1;i<s.length;i++) {
      newstring+=delim+s[i];
    }
    return newstring;
}

public static String join(Vector v,String delim) {
    String newstring=(String)v.elementAt(0);
    for (int i=1;i<v.size();i++) {
      newstring+=delim+(String)v.elementAt(i);
    }
    return newstring;
}

public static String[] split(String s,String delim) {
    StringTokenizer st=new StringTokenizer(s,delim);
    int ntokens=st.countTokens();
    String[] parts=new String[ntokens];
    for (int i=0;i<ntokens;i++)
                    parts[i]=st.nextToken();
    return parts;
}

    public static Connection getConnection(Connection conn, ResourceBundle mqProps, Logger log, String url, String username, String password) throws InterruptedException {
                    
                    //log.info("About to try the Connection to the database with url: "+url+" - username: "+username);

                    try{
                                    if (conn!=null){
                                                    conn.close();
                                    }
                                    //Class.forName(className); 
                                    conn = DriverManager.getConnection(url,username, password);
                                    log.info("Connection to the database was successful with url: "+url+" - username: "+username);
                    }catch ( Exception cnfe ) {
                                    //log.print();
                                    log.error("Error in T3.getConnection method: " + cnfe.toString());
                                    //System.out.println( "Error in getConnection method: " + cnfe.toString() );
                                    log.error("Try to connect again after 5 seconds....");
                                    //log.printError();
                                    retryCnt++;
                                    try {
                                                    Thread.sleep(10000);                                                     
                                    } catch ( InterruptedException e )
                                    {
                                                    System.out.println( "awakened prematurely" );
                                    }
                                    if(retryCnt==retryLimit) {
                                                    retryCnt=0;
                                                    //log.info("Sending Email to support");
                                                    //log.print();
                                                    //sendMail(mqProps, serverName, "Unable to connect to database: " + cnfe.getMessage(), log);
                                    }
                                    getConnection(conn, mqProps, log,  url, username, password);
                    }
                    return conn;
    }

    public static void returnConnection(Connection conn, ResourceBundle mqProps , Logger log ) 
                                    throws InterruptedException {
                    //System.out.println("in returnConnection");
                    try{
                                    if (conn!=null){
                                                    conn.close();
                                                    conn = null;
                                    }
                    }catch ( Exception cnfe ) {
                                    log.error("Error in returnConnection method: " + cnfe.toString());
                                    //System.out.println( "Error in returnConnection method: " + cnfe.toString() );
                    }
    }

    /*public static void sendMail(ResourceBundle mqProps, String serverName, String env, String error, Logger log){
                    try{
                                                    String smtpserver = mqProps.getString(serverName+ "_mailServer");
                                                    String AddressList = mqProps.getString(serverName+"_mailTo");
                                                    String mailFrom = mqProps.getString(serverName+"_mailFrom");
                                                    int fromIndex = 0;
                                                    int nextIndex = 0;
                                                    InternetAddress toAddress;
                                                    String nextAddress = "";
                                                    InternetAddress fromAddress = new InternetAddress(mailFrom);
    
    System.out.println("Setting up the properties.  smtpserver: "+smtpserver);
                                                    Properties props = new Properties();
                                    
                                                    System.out.println("setting up the stmpserver with mailhub.utc.com");
                                                    props.put("mail.smtp.host",smtpserver); //smtpserver  "mailhost"
//                                                props.put("mailhub.utc.com", smtpserver);
                                                    System.out.println("Setting mail session");
                                                    Session mailsession = Session.getDefaultInstance(props,null);
                                                    System.out.println("setting msg");
                                                    Message msg = new MimeMessage(mailsession);
                                                    System.out.println("adding recipient "+AddressList);
                                                    nextIndex =AddressList.indexOf(";",fromIndex); 
                                                    while ( nextIndex > 0) {
                                                                    nextAddress = AddressList.substring(fromIndex,nextIndex);
                                                                    toAddress = new InternetAddress(nextAddress);
                                                                    System.out.println("toAddress: "+nextAddress);
                                                                    fromIndex = nextIndex+1;
                                                                    nextIndex =AddressList.indexOf(";",fromIndex);                                                                             
                                                                    msg.addRecipient(Message.RecipientType.TO, toAddress);
                                                    }
                                                    if (fromIndex > 0) {
                                                                    nextAddress = AddressList.substring(fromIndex,AddressList.length());
                                                                    toAddress = new InternetAddress(nextAddress);
                                                                    System.out.println("toAddress: "+nextAddress);
//                                                                fromIndex = nextIndex+1;
//                                                                nextIndex =AddressList.indexOf(";",fromIndex);                                                                             
                                                                    msg.addRecipient(Message.RecipientType.TO, toAddress);                                                                        
                                                    }
                                                    msg.setSubject("rMRP MessageServer Error");
                                                    msg.setFrom(fromAddress);
                                                    String errormail =  "IMPORTANT " +  env.toUpperCase() + " rMRP Failure at " + new Date().toString() +" \n" +
                                                                                                                    "Please notify the SOLUMINA Support On-CALL Person of this error. \n" +
                                                                                                                    "Examine error condition and refer to the Solumina Decision tree for assistance. \n" +
                                                                                                                    "Reason of failure:  " + error;
                                                                                                                    
                                                    msg.setText(errormail);
                                                    log.info("Sent to support: \n");
                                                    log.info(errormail);
//                                                log.print();
                                                    System.out.println("About to send the message");
                                                    Transport.send(msg);  
                                                    System.out.println("After sending the message");

                                    
                    }catch(Exception e){
                                    log.info("Utils.SendMail - ERROR: " + e.getMessage() + " - "+e.toString());
                                    
                    }
                    
    }
*/


/**
* Constructor
*/
public Utils() { 
      //System.out.println("in Utils");
}


}
