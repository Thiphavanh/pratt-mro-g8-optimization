package com.utas.solumina.paar.domain;

import java.util.Date;

import com.ibaset.solumina.sfpl.domain.PlanDesc;
import com.ibaset.solumina.sfpl.domain.PlanRev;
import com.ibaset.solumina.sfpl.domain.PlanRevId;

public class PermissionDesc 
{
	private String requestId;
	private String requestUserId;
	private String requestReason;
	private Date creationDate ;
	private Date replyRequiredDate;
	private String priorRequestId;
	private String requestStatus;
	private PlanRevId planRevId;
	private PlanDesc planDesc;
// 	private PlanRev planRev; Removed due to bug in G8 R1 - GE-15929
	
	public PermissionDesc() 
	{	
	}
	
	public PermissionDesc(String requestId,
						  String requestUserId,
						  String requestReason,
						  Date creationDate,
						  Date replyRequiredDate,
						  String priorRequestId,
						  PlanRevId planRevId,
						  PlanDesc planDesc,
						  String requestStatus)
	{
		this.requestId = requestId;
		this.requestUserId = requestUserId;
		this.requestReason = requestReason;
		this.creationDate = creationDate;
		this.replyRequiredDate = replyRequiredDate;
		this.priorRequestId = priorRequestId;
		this.planRevId = planRevId;
		this.planDesc = planDesc;
		this.requestStatus = requestStatus;
	}
	
	public String getRequestId() 
	{
		return requestId;
	}
	
	public void setRequestId(String requestId) 
	{
		this.requestId = requestId;
	}
	
	public String getRequestUserId() 
	{
		return requestUserId;
	}
	
	public void setRequestUserId(String requestUserId) 
	{
		this.requestUserId = requestUserId;
	}
	
	public String getRequestReason() 
	{
		return requestReason;
	}
	
	public void setRequestReason(String requestReason) 
	{
		this.requestReason = requestReason;
	}
	public Date getCreationDate() 
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) 
	{
		this.creationDate = creationDate;
	}
	
	public Date getReplyRequiredDate() 
	{
		return replyRequiredDate;
	}
	
	public void setReplyRequiredDate(Date replyRequiredDate) 
	{
		this.replyRequiredDate = replyRequiredDate;
	}
	
	public String getPriorRequestId() 
	{
		return priorRequestId;
	}
	
	public void setPriorRequestId(String priorRequestId) 
	{
		this.priorRequestId = priorRequestId;
	}
		
	public String getRequestStatus() 
	{
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) 
	{
		this.requestStatus = requestStatus;
	}

    public PlanRevId getPlanRevId()
    {
        return planRevId;
    }

    public void setPlanRevId(PlanRevId planRevId)
    {
        this.planRevId = planRevId;
    }

    public PlanDesc getPlanDesc()
    {
        return planDesc;
    }

    public void setPlanDesc(PlanDesc planDesc)
    {
        this.planDesc = planDesc;
    }	
}
