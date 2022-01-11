package com.utas.solumina.paar.application.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.utas.solumina.paar.application.AssembleText;
import com.utas.solumina.paar.application.dao.AssembleTextDaoImpl;

public class AssembleTextImpl implements AssembleText
{
    private AssembleTextDaoImpl  paarAsmblTxtDao;
    
    
    public String permSghtValidMode(String requestId,
                                    String validToolMode)
    {
        String result = "VIEW";
        // Get the status of the PAAR Request.
        Map paarHdrMap = paarAsmblTxtDao.getPaarReqHdr(requestId);
        
        if (! paarHdrMap.isEmpty())
        {
            String reqStatus = (String) paarHdrMap.get("REQUEST_STATUS");
            
            if (StringUtils.equalsIgnoreCase("OPEN",reqStatus))
            {
                result = "EDIT";
            }
        }
        
        return result;
    }
    
    
    public String widPermSghtValidMode(String requestId,
            						   String validToolMode)
    {
    	String result = "VIEW";
    	// Get the status of the PAAR Request.
    	Map paarHdrMap = paarAsmblTxtDao.getWIDPaarReqHdr(requestId);

    	if (! paarHdrMap.isEmpty())
    	{
    		String reqStatus = (String) paarHdrMap.get("REQUEST_STATUS");

    		if (StringUtils.equalsIgnoreCase("OPEN",reqStatus))
    		{
    			result = "EDIT";
    		}
    	}

    	return result;
    }


    public String permSghtAssembleText(String requestId,
                                       String validToolMode)
    {
        StringBuffer text = new StringBuffer();

        if (StringUtils.equalsIgnoreCase(SoluminaConstants.VIEWMODES_VIEW, validToolMode))
        {
            text = permSghtAssembleTextBuild(requestId, "VIEW");            
        }
        else if (StringUtils.equalsIgnoreCase(SoluminaConstants.EDITMODES_EDIT, validToolMode))
        {
            text = permSghtAssembleTextBuild(requestId, "EDIT");  
        }

        return text.toString();
    }
    
    public String widPermSghtAssembleText(String requestId,
            							  String validToolMode)
    {
    	StringBuffer text = new StringBuffer();

    	if (StringUtils.equalsIgnoreCase(SoluminaConstants.VIEWMODES_VIEW, validToolMode))
    	{
    		text = widPermSghtAssembleTextBuild(requestId, "VIEW");            
    	}
    	else if (StringUtils.equalsIgnoreCase(SoluminaConstants.EDITMODES_EDIT, validToolMode))
    	{
    		text = widPermSghtAssembleTextBuild(requestId, "EDIT");  
    	}

    	return text.toString();
    }
    
    
    private StringBuffer widPermSghtAssembleTextBuild(String requestId, String toolMode)
    {
        // First set UDVs
        String udvSecGrpRequestPnl = "HAMSI_19B962E50FB3222DE053D20F0A0AC8AE";
        String udvWorkLocRequestPnl = "HAMSI_19B93B8E88791F92E053D20F0A0A4196";
        
        StringBuffer text = new StringBuffer();
        
        text.append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"LabelPaarRecord,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);

        text.append("<M=ScopeQueryValues(")
            .append("REQUEST_ID=" + requestId + ")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"HAMSI_19B64C91261A3D50E053D20F0A0A768F(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);
                    
        text.append("<IMG \"LabelPaarSecGrpPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)                                   
            .append("<IMG \"" + udvSecGrpRequestPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)        
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);        
                        
        text.append("<IMG \"LabelPaarWorkLocPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)                       
            .append("<IMG \"" + udvWorkLocRequestPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);
                   
        text.append("<S=@RegularText>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<B=PlanTextRO(REQUEST_ID=" + requestId +")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</B>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</S>");
      
        return text;
    }
    
    private StringBuffer permSghtAssembleTextBuild(String requestId, String toolMode)
    {
        // First set UDVs
        String udvSecGrpRequestPnl = "HAMSI_16A77B6EA1117C6EE053D20F0A0A4E94";
        String udvWorkLocRequestPnl = "HAMSI_16A77B6EA10D7C6EE053D20F0A0A4E94";
        String udvUserRequestPnl = "HAMSI_16A77B6EA1037C6EE053D20F0A0A4E94";
                
        StringBuffer text = new StringBuffer();
        
        text.append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"LabelPaarRecord,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);

        text.append("<M=ScopeQueryValues(")
            .append("REQUEST_ID=" + requestId + ")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"HAMSI_16A77B6E8E977C6EE053D20F0A0A4E94(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);
            
        text.append("<IMG \"LabelPaarSecGrpPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)                         
            .append("<IMG \"" + udvSecGrpRequestPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);        
        
        text.append("<IMG \"LabelPaarWorkLocPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"" + udvWorkLocRequestPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);
            
        text.append("<IMG \"LabelPaarUserPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1100)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)           
            .append("<IMG \"" + udvUserRequestPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")        
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);            
            
        text.append("<S=@RegularText>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<B=PlanTextRO(REQUEST_ID=" + requestId +")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</B>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</S>");
       
        return text;
    }
    
    //UTAS-478
    public String permApprovalValidMode(String requestId,
    									String validToolMode)
    {
        String result = "VIEW";
        // Get the status of the PAAR Request.
        Map paarHdrMap = paarAsmblTxtDao.getPaarReqHdrForApproval(requestId);
        
        if (! paarHdrMap.isEmpty())
        {
            String reqStatus = (String) paarHdrMap.get("REQUEST_STATUS");
            
            if (StringUtils.equalsIgnoreCase("OPEN",reqStatus))
            {
                result = "EDIT";
            }
        }
        return result;
    }

    //UTAS-478
    public String permApprovalAssembleText(String requestId,
			   							   String validToolMode)
    {
        StringBuffer text = new StringBuffer();

        if (StringUtils.equalsIgnoreCase(SoluminaConstants.VIEWMODES_VIEW, validToolMode))
        {
            text = permApprovalAssembleTextBuild(requestId, "VIEW");            
        }
        else if (StringUtils.equalsIgnoreCase(SoluminaConstants.EDITMODES_EDIT, validToolMode))
        {
            text = permApprovalAssembleTextBuild(requestId, "EDIT");  
        }

        return text.toString();
    }
    
    //UTAS-478
    private StringBuffer permApprovalAssembleTextBuild(String requestId, String toolMode)
    {       
        // First set UDVs to read-only version (view mode)
        String udvUserRetainPnl = "HAMSI_1EA09A7D0DD72706E053D20F0A0A2912";
        String udvUserRemovePnl = "HAMSI_1EA09A7D0D772706E053D20F0A0A2912";
        String udvFacilityRetainPnl = "HAMSI_1EA09A7D0DE92706E053D20F0A0A2912";
        String udvFacilityRemovePnl = "HAMSI_1EA09A7D0D692706E053D20F0A0A2912";
        String udvSecGrpRetainPnl = "HAMSI_1EA09A7D0DF42706E053D20F0A0A2912";
        String udvSecGrpRemovePnl = "HAMSI_1EA09A7D0D5A2706E053D20F0A0A2912";

        if (StringUtils.equalsIgnoreCase("EDIT", toolMode))
        {
            // Determine if user has the required privs - if not show the Read-Only UDV.
            // Now check for Retain / Add Priv
            if (ContextUtil.getUser().hasPrivilege("PAAR_APPROVAL-CAN_AUTHORIZE_ADDS"))
            {
                udvUserRetainPnl = "HAMSI_18C5659E5917342FE053D20F0A0AB439";
                udvFacilityRetainPnl = "HAMSI_18C7FB347A7D0BCDE053D20F0A0A27BE";
                udvSecGrpRetainPnl = "HAMSI_18C846489BE71846E053D20F0A0A2DCD";
            }
            
            // Now check for Remove Priv
            if (ContextUtil.getUser().hasPrivilege("PAAR_APPROVAL-CAN_AUTHORIZE_DELS"))
            {
                udvUserRemovePnl = "HAMSI_1E15B555C0436B84E053D20F0A0A0B60";
                udvFacilityRemovePnl = "HAMSI_1E15B555C0656B84E053D20F0A0A0B60";
                udvSecGrpRemovePnl = "HAMSI_1E1838322A794DF7E053D20F0A0AA314";
            }
        }

        StringBuffer text = new StringBuffer();
        
        text.append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"LabelPaarRecord,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);

        text.append("<M=ScopeQueryValues(")
            .append("REQUEST_ID=" + requestId + ")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"HAMSI_18D8ED1781254D54E053D20F0A0A658C(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);
            
        text.append("<IMG \"LabelPaarSecGrpPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)      
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>RETAIN PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)              
            .append("<IMG \"" + udvSecGrpRetainPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>REMOVE PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)       
            .append("<IMG \"" + udvSecGrpRemovePnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);        
        
        text.append("<IMG \"LabelPaarWorkLocPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)                   
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>RETAIN PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)  
            .append("<IMG \"" + udvFacilityRetainPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>REMOVE PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)       
            .append("<IMG \"" + udvFacilityRemovePnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)            
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);                    
        
        text.append("<IMG \"LabelPaarUserPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>RETAIN PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)       
            .append("<IMG \"" + udvUserRetainPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>REMOVE PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)       
            .append("<IMG \"" + udvUserRemovePnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);        
               
        text.append("<S=@RegularText>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<B=PlanTextRO(REQUEST_ID=" + requestId +")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</B>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</S>");
        
        return text;
    }
    
    //UTAS-496
    public String permOrderSecGrpApprovalValidMode(String requestId,
    											   String validToolMode)
    {
        String result = "VIEW";
        // Get the status of the PAAR Request.
        Map paarHdrMap = paarAsmblTxtDao.getPaarOrderSecGrpReqHdrForApproval(requestId);
        
        if (! paarHdrMap.isEmpty())
        {
            String reqStatus = (String) paarHdrMap.get("REQUEST_STATUS");
            
            if (StringUtils.equalsIgnoreCase("OPEN",reqStatus))
            {
                result = "EDIT";
            }
        }
        return result;
    }
    
    //UTAS-496
    public String permOrderSecGrpApprovalAssembleText(String requestId,
			   							    		  String validToolMode)
    {
        StringBuffer text = new StringBuffer();

        if (StringUtils.equalsIgnoreCase(SoluminaConstants.VIEWMODES_VIEW, validToolMode))
        {
            text = permOrderSecGrpApprovalAssembleTextBuild(requestId, "VIEW");            
        }
        else if (StringUtils.equalsIgnoreCase(SoluminaConstants.EDITMODES_EDIT, validToolMode))
        {
            text = permOrderSecGrpApprovalAssembleTextBuild(requestId, "EDIT");  
        }

        return text.toString();
    }
    
    //UTAS-496
    private StringBuffer permOrderSecGrpApprovalAssembleTextBuild(String requestId, String toolMode)
    {        
        // First set UDVs to read-only version
        String udvFacilityRetainPnl = "HAMSI_1EA09A7D0DC02706E053D20F0A0A2912";
        String udvFacilityRemovePnl = "HAMSI_1EA09A7D0D542706E053D20F0A0A2912";
        String udvSecGrpRetainPnl = "HAMSI_1EA09A7D0DCB2706E053D20F0A0A2912";
        String udvSecGrpRemovePnl = "HAMSI_1EA09A7D0D872706E053D20F0A0A2912";
        
       
       if (StringUtils.equalsIgnoreCase("EDIT", toolMode))
       {
           // Determine if user has the required privs - if not show the Read-Only UDV.
           // Now check for Retain / Add Priv
           if (ContextUtil.getUser().hasPrivilege("PAAR_APPROVAL-CAN_AUTHORIZE_ADDS"))
           {
               udvFacilityRetainPnl = "HAMSI_19A5FB98D9C51880E053D20F0A0A7EDC";
               udvSecGrpRetainPnl = "HAMSI_19A6837D683429FAE053D20F0A0A3A96";
           }
           
           // Now check for Remove Priv
           if (ContextUtil.getUser().hasPrivilege("PAAR_APPROVAL-CAN_AUTHORIZE_DELS"))
           {
               udvFacilityRemovePnl = "HAMSI_1E8DFB09F1C078E0E053D20F0A0AD0B3";
               udvSecGrpRemovePnl = "HAMSI_1E8DFB09F1CE78E0E053D20F0A0AD0B3";
           }
       }
        
        StringBuffer text = new StringBuffer();
        
        text.append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"LabelPaarRecord,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);

        text.append("<M=ScopeQueryValues(")
            .append("REQUEST_ID=" + requestId + ")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"HAMSI_19A4F5A330507670E053D20F0A0A8CBD(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);    
            
        text.append("<IMG \"LabelPaarSecGrpPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)  
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>RETAIN PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)                        
            .append("<IMG \"" + udvSecGrpRetainPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>REMOVE PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)   
            .append("<IMG \"" + udvSecGrpRemovePnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);              
        
        text.append("<IMG \"LabelPaarWorkLocPermissions,@Transparent=False\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<SHAPE=divider(color=$997050,height=2,width=1450)>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)      
            .append(SoluminaConstants.FRAMEWORK_LINEFEED) 
            .append("<S=@PAARText1>RETAIN PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<IMG \"" + udvFacilityRetainPnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<S=@PAARText1>REMOVE PERMISSIONS</S>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)   
            .append("<IMG \"" + udvFacilityRemovePnl + "(REQUEST_ID=" + requestId + ").@UDV\">")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED);        
                    
        text.append("<S=@RegularText>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("<B=PlanTextRO(REQUEST_ID=" + requestId +")>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</B>")
            .append(SoluminaConstants.FRAMEWORK_LINEFEED)
            .append("</S>");

        return text;
    }
    
    // Setters and Getters
    public void setPaarAsmblTxtDao(AssembleTextDaoImpl paarAsmblTxtDao)
    {
        this.paarAsmblTxtDao = paarAsmblTxtDao;
    }

}
