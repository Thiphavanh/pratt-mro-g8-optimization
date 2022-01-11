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
*  File:    LdapBean.java
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

public class LdapBean {
	
	
	   private String user_id = "unknown";   
	   private String user_pw = "unknown";
	   private String user_name = "unknown";
	   private String authorized = "N";
	   private String group_member = "unknown"; 
	   private String first_name = "unknown";
	   private String last_name = "unknown";
	   private String email = "unknown";
	   private String cn = "unknown";
	   private String citznShip = "unknown";
	   private String userDN = "unknown";
			   
	   //private Logger log;
	 /**
	    * Returns user_id
	    * @return String
	    */ 
	   public String getUserID() { return user_id; }
	   
	   
	   /**
	    * Sets user_id
	    * @param val user_id
	    */ 
	   public void setUserID (String val ) { user_id = val; }
	   

	   /**
	    * Returns user_pw
	    * @return String
	    */ 
	   public String getUserPW() { return user_pw; }
	   
	   
	   /**
	    * Sets user_pw
	    * @param val user_pw
	    */ 
	   public void setUserPW (String val ) { user_pw = val; }

	      
	   /**
	    * Returns user_name
	    * @return String
	    */ 
	   public String getUserFullName() { return user_name; }
	   
	   
	   /**
	    * Sets user_name
	    * @param val user_name
	    */ 
	   public void setUserFullName (String val ) { user_name = val; }


	   /**
	    * Returns authorized
	    * @return String
	    */ 
	   public String getAuthorized() { return authorized; }
	   
	   
	   /**
	    * Sets authorized
	    * @param val authorized
	    */ 
	   public void setAuthorized (String val ) { authorized = val; }


	   /**
	    * Returns group_member
	    * @return String
	    */ 
	   public String getGroupMember() { return group_member; }
	   
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setGroupMember (String val ) { group_member = val; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setFirstName (String val ) { first_name = val; }
	   
	   /**
	    * Returns group_member
	    * @return String
	    */ 
	   public String getFirstName() { return first_name; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setLastName (String val ) { last_name = val; }
	   
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public String getLastName () { return last_name; }
	   
	   	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setEmail (String val ) { email = val; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public String getEmail () { return email; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setCn (String val ) { cn = val; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public String getCn () { return cn; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public void setCitzn (String val ) { citznShip = val; }
	   
	   /**
	    * Sets group_member
	    * @param val group_member
	    */ 
	   public String getCitzn () { return citznShip; }
	   
	   
	   /**
	    * Sets userDN
	    * @param val userDN
	    */ 
	   public String getUserDN () { return userDN; }
	   	   
	   /**
	    * Sets userDN
	    * @param val userDN
	    */ 
	   public void setUserDN (String val ) { userDN = val; }
}
