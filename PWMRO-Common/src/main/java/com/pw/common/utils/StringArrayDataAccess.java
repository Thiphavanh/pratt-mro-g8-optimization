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
*  File:    StringArrayDataAccess.java
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
import java.sql.*;

//import java.util.*;
//import java.sql.Date;
import java.text.*;
//import java.io.*;

//import javax.servlet.http.HttpSession;

import com.pw.common.utils.*;

/**
* A Class class.
* <P>
* @author Catrina Nguyen
*/
public class StringArrayDataAccess extends BaseDataAccess{
//  private ConnectionPool pool;
    
/**
* Constructor
*/
public StringArrayDataAccess() {
    super();
}

/**
* retrieves array of data
* @param report date
* @param dsrgroup
*/
public String[][] getDataArray(String[] tables,
                                                    String[] columns,
                                                    String[] wheres)
                               throws SQLException, ParseException {

    return getDataArray(tables,columns,wheres,new String[] {}, null);
}

/**
* retrieves array of data
* @param report date
* @param dsrgroup
*/
public String[][] getDataArray(String[] tables,
                                                    String[] columns,
                                                    String[] wheres,
                                                    String[] orderbys,
                                                    Connection connection)
                               throws SQLException, ParseException {

    ResultSet resultSet = null;
    Statement statement = null;
    String[][] array = {}; 
    
    String sql="";

    try {
    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                     ResultSet.CONCUR_READ_ONLY);

    //----------------------------------------------------
    // get data by report date and group
    //----------------------------------------------------

    sql = buildSelectSql(tables, columns, wheres, orderbys);
//  System.out.println("sql statement = "+sql);
    resultSet = statement.executeQuery(sql);
    ResultSetMetaData rsmd = resultSet.getMetaData();

    //----------------------------------------------
    // Set array size
    //----------------------------------------------
    int count;
    if (resultSet.next()) {
      resultSet.last();
      count = resultSet.getRow();
    } else
      count = 0;    

    array = new String[count][columns.length];

    resultSet.beforeFirst();
    int row=0;
    int pos=-1;
    while (resultSet.next()) {
      for (int c=0;c<columns.length;c++) {
           if (rsmd.getColumnType(c+1)==Types.CHAR)
                  array[row][c]=resultSet.getString(c+1);
           else if (rsmd.getColumnType(c+1)==Types.VARCHAR)
                  array[row][c]=resultSet.getString(c+1);
           else if (rsmd.getColumnTypeName(c+1).equals("DATETIME")) {
                  String str=resultSet.getString(c+1);
                  if (str == null)
                    array[row][c] = null;
                  else {
                    java.util.Date date = getUtilDate(resultSet.getString(c+1));
                    array[row][c] = DateUtils.getDateTimeString(date);
                  }
           }
           else if (rsmd.getColumnTypeName(c+1).equals("DATETIME")) {
                  java.util.Date date = getUtilDate(resultSet.getString(c+1));
                  array[row][c] = DateUtils.getDateTimeString(date);
           }
           else if (rsmd.getColumnType(c+1)==Types.INTEGER)
                  array[row][c] = resultSet.getString(c+1);
           else {
                  System.out.println("Unassigned type:"+c+":"+rsmd.getColumnName(c+1)+"="+rsmd.getColumnTypeName(c+1));
                  array[row][c]=resultSet.getString(c+1);
           }
      }
      row++;
    }
    
    } catch(Exception e) {
           System.out.println("Error building array from resultset: "+e);
    } 
    finally {
           // Clean up
           resultSet.close();
           statement.close();
    }
    return array;

}

/**
* retrieves array of data
* @param report date
* @param dsrgroup
*/
public String[][] getDataArray(String sql,
                                                    String[] columns,
                                                    Connection connection)
                               throws SQLException, ParseException {

    ResultSet resultSet = null;
    Statement statement = null;
    String[][] array = {}; 
    

    try {
    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                     ResultSet.CONCUR_READ_ONLY);

    //----------------------------------------------------
    // get data by report date and group
    //----------------------------------------------------

    resultSet = statement.executeQuery(sql);
    ResultSetMetaData rsmd = resultSet.getMetaData();

    //----------------------------------------------
    // Set array size
    //----------------------------------------------
    int count;
    if (resultSet.next()) {
      resultSet.last();
      count = resultSet.getRow();
    } else
      count = 0;    

    array = new String[count][columns.length];

    resultSet.beforeFirst();
    int row=0;
    int pos=-1;
    while (resultSet.next()) {
      for (int c=0;c<columns.length;c++) {
           if (rsmd.getColumnType(c+1)==Types.CHAR)
                  array[row][c]=resultSet.getString(c+1);
           else if (rsmd.getColumnType(c+1)==Types.VARCHAR)
                  array[row][c]=resultSet.getString(c+1);
           else if (rsmd.getColumnTypeName(c+1).equals("DATETIME")) {
                  String str=resultSet.getString(c+1);
                  if (str == null)
                    array[row][c] = null;
                  else {
                    java.util.Date date = getUtilDate(resultSet.getString(c+1));
                    array[row][c] = DateUtils.getDateTimeString(date);
                  }
           }
           else if (rsmd.getColumnTypeName(c+1).equals("DATETIME")) {
                  java.util.Date date = getUtilDate(resultSet.getString(c+1));
                  array[row][c] = DateUtils.getDateTimeString(date);
           }
           else if (rsmd.getColumnType(c+1)==Types.INTEGER)
                  array[row][c] = resultSet.getString(c+1);
           else {
                  System.out.println("Unassigned type:"+c+":"+rsmd.getColumnName(c+1)+"="+rsmd.getColumnTypeName(c+1));
                  array[row][c]=resultSet.getString(c+1);
           }
      }
      row++;
    }
    
    } catch(Exception e) {
           System.out.println("Error building array from resultset: "+e);
    } 
    finally {
           // Clean up
           resultSet.close();
           statement.close();
          
    }
    return array;

}


}
