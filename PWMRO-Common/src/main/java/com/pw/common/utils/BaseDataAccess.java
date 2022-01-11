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
*  File:    BaseDataAccess.java
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

//import java.sql.*;
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
public class BaseDataAccess 
{
//  public Connection connection;

  final SimpleDateFormat sqldateformatter = new SimpleDateFormat ("yyyy-MM-dd");

  final SimpleDateFormat sqldatetimeformatter
        = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");


  /**
   * getSqlDate()
   * @param date as string
   */
  public String getEscapedDate(java.util.Date date) {

    if (date==null) return "";

    return sqldatetimeformatter.format(date);

  }

  /**
   * getSqlDate()
   * @param java.util.Date
   * @return java.sql.Date
   */
  public java.sql.Date getSqlDate(java.util.Date utildate) {

    return new java.sql.Date(utildate.getTime());

  }

  public java.util.Date getUtilDate(String escdateString) throws ParseException {

    java.util.Date utilDate;

    if (escdateString.indexOf(":")!=-1) {
      utilDate=sqldatetimeformatter.parse(escdateString);
    } else {
      utilDate=sqldateformatter.parse(escdateString);
    }

    return utilDate;

  }

  /**
   * remove table data
   */
/*  public int deleteData(String table,String[] wheres)
      throws SQLException,ParseException {

    String sql="delete from "+table+" where "
                +Utils.join(wheres," and ");

    return deleteData(sql);
  } */


  public String buildSelectSql(String[] tables,
                               String[] columns,
                               String[] wheres,
                               String orderby) {

    String[] orderbys={ orderby };

    return buildSelectSql(tables, columns, wheres, orderbys);

  }

  public String buildSelectSql(String[] tables,
                               String[] columns,
                               String[] wheres) {

    String[] orderbys={};

    return buildSelectSql(tables, columns, wheres, orderbys);

  }

  public String buildSelectSql(String[] tables,
                               String[] columns,
                               String[] wheres,
                               String[] orderbys) {

    String sql = "select "+Utils.join(columns,", ")+
                 " from "+Utils.join(tables,", ");
                if (wheres.length>0) sql+=" where "+Utils.join(wheres," and ");

    if (orderbys.length>0) sql+=" order by "+Utils.join(orderbys,",");

    return sql;

  }

  public String getJoinedFields(String table1,
                                String table2,
                                String column) {

    return table1+"."+column+"="+table2+"."+column;

  }

}
