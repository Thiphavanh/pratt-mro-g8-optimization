/*
*  This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    Permission.java
* 
*  Created: 2018-11-15
* 
*  Author:  David Miron
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	-------------------------------------------------------------------
* 2018-08-24 	D.Miron		    Added PWU-124 Update from iBaset - Francis Graham
*/

package com.utas.solumina.paar.domain;

import java.util.Date;

import com.ibaset.solumina.sffnd.domain.SecurityGroupDef;
import com.ibaset.solumina.sffnd.domain.User;
import com.ibaset.solumina.sffnd.domain.WorkLocDef;

public class Permission 
{
	private String sghtId;
	private String authId;
	private String action;
	private String effect;
	private String type;
	private String typeEntity;
	private Date startDate;
	private Date endDate;
	private User user;
	private WorkLocDef workLocDef;
	private SecurityGroupDef securityGroupDef;
	private String permissionStatus;
	
	public Permission() 
	{
	}
	
	public Permission(String sghtId, 
					  String authId,
					  String action,
					  String effect,
					  String type,
					  String typeEntity,
					  Date startDate,
					  Date endDate)
	
	{
		this.sghtId = sghtId;
		this.authId = authId;
		this.action = action;
		this.effect = effect;
		this.type = type;
		this.typeEntity = typeEntity;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Permission(User user,
					  WorkLocDef workLocDef,
					  SecurityGroupDef securityGroupDef)

	{
		this.user = user;
		this.workLocDef = workLocDef;
		this.securityGroupDef = securityGroupDef;
	}
	
	public Permission(String sghtId, 
					  String authId,
					  String action,
					  String effect,
					  String type,
					  String typeEntity,
					  Date startDate,
					  Date endDate,
					  User user,
					  WorkLocDef workLocDef,
					  SecurityGroupDef securityGroupDef)
	{
		this.sghtId = sghtId;
		this.authId = authId;
		this.action = action;
		this.effect = effect;
		this.type = type;
		this.typeEntity = typeEntity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.workLocDef = workLocDef;
		this.securityGroupDef = securityGroupDef;
	}

	public String getSghtId() 
	{
		return sghtId;
	}
	
	public void setSghtId(String sghtId) 
	{
		this.sghtId = sghtId;
	}
	
	public String getAuthId() 
	{
		return authId;
	}
	
	public void setAuthId(String authId) 
	{
		this.authId = authId;
	}
	
	public String getAction() 
	{
		return action;
	}
	
	public void setAction(String action) 
	{
		this.action = action;
	}
	
	public String getEffect() 
	{
		return effect;
	}
	
	public void setEffect(String effect) 
	{
		this.effect = effect;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String getTypeEntity() 
	{
		return typeEntity;
	}
	
	public void setTypeEntity(String typeEntity) 
	{
		this.typeEntity = typeEntity;
	}
	
	public Date getStartDate() 
	{
		return startDate;
	}
	
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
	public SecurityGroupDef getSecurityGroup() 
	{
		return securityGroupDef;
	}
	
	public void setSecurityGroup(SecurityGroupDef securityGroup)
	{
		this.securityGroupDef = securityGroup;
	}
	
	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
	
	public WorkLocDef getWorkloc()
	{
		return workLocDef;
	}
	
	public void setWorkloc(WorkLocDef workLoc) 
	{
		this.workLocDef = workLoc;
	}

	public String getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(String permissionStatus) 
	{
		this.permissionStatus = permissionStatus;
	}
	
}
