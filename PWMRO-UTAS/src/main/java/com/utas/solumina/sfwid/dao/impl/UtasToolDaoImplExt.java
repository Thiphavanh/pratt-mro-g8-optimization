package com.utas.solumina.sfwid.dao.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfwid.dao.IToolDao;

public abstract class UtasToolDaoImplExt extends ImplementationOf<IToolDao> implements IToolDao 
{
	 public void insertOperationTool(String orderId,
									 Number operationKey,
									 String operationNumber,
									 String stepNumber,
									 Number stepKey,
									 String toolNumber,
									 String toolChange,
									 String userName,
									 String serialFlag,
									 String expirationFlag,
									 Number quantity,
									 String toolNotes,
									 String alterationId,
									 String toolTitle,
									 String toolType,
									 String ucfPlanToolVch1,
									 String ucfPlanToolVch2,
									 String ucfPlanToolVch3,
									 String ucfPlanToolFlag1,
									 Number ucfPlanToolNumber1,
									 Date ucfPlanToolDate1,
									 String orientationFlag,
									 String crossOrderFlag,
									 String optionalFlag,
									 String suspectFlag,
									 String refernceId,
									 String blockId,
									 String ucfPlanToolVch4,
									 String ucfPlanToolVch5,
									 String ucfPlanToolVch6,
									 String ucfPlanToolVch7,
									 String ucfPlanToolVch8,
									 String ucfPlanToolVch9,
									 String ucfPlanToolVch10,
									 String ucfPlanToolVch11,
									 String ucfPlanToolVch12,
									 String ucfPlanToolVch13,
									 String ucfPlanToolVch14,
									 String ucfPlanToolVch15,
									 Number ucfPlanToolNumber2,
									 Number ucfPlanToolNumber3,
									 Number ucfPlanToolNumber4,
									 Number ucfPlanToolNumber5,
									 Date ucfPlanToolDate2,
									 Date ucfPlanToolDate3,
									 Date ucfPlanToolDate4,
									 Date ucfPlanToolDate5,
									 String ucfPlanToolFlag2,
									 String ucfPlanToolFlag3,
									 String ucfPlanToolFlag4,
									 String ucfPlanToolFlag5,
									 String ucfPlanToolVch2551,
									 String ucfPlanToolVch2552,
									 String ucfPlanToolVch2553,
									 String ucfPlanToolVch40001,
									 String ucfPlanToolVch40002,
									 Number displayLineNumber,
									 String overUsedFlag,
									 String securityGroup,
									 String toolId,
	            				     String bomCompToolId)
	 {
		securityGroup = inheritOrderSecurityGroup(securityGroup);
		 
		 Super.insertOperationTool(orderId,
                 				   operationKey,
                 				   operationNumber,
                 				   stepNumber,
                 				   stepKey,
                 				   toolNumber,
                 				   toolChange,
                 				   userName,
                 				   serialFlag,
                 				   expirationFlag,
                 				   quantity,
                 				   toolNotes,
                 				   alterationId,
                 				   toolTitle,
                 				   toolType,
                 				   ucfPlanToolVch1,
                 				   ucfPlanToolVch2,
                 				   ucfPlanToolVch3,
                 				   ucfPlanToolFlag1,
                 				   ucfPlanToolNumber1,
                 				   ucfPlanToolDate1,
                 				   orientationFlag,
                 				   crossOrderFlag,
                 				   optionalFlag,
                 				   suspectFlag,
                 				   refernceId,
                 				   blockId,
                 				   ucfPlanToolVch4,
                 				   ucfPlanToolVch5,
                 				   ucfPlanToolVch6,
                 				   ucfPlanToolVch7,
                 				   ucfPlanToolVch8,
                 				   ucfPlanToolVch9,
                 				   ucfPlanToolVch10,
                 				   ucfPlanToolVch11,
                 				   ucfPlanToolVch12,
                 				   ucfPlanToolVch13,
                 				   ucfPlanToolVch14,
                 				   ucfPlanToolVch15,
                 				   ucfPlanToolNumber2,
                 				   ucfPlanToolNumber3,
                 				   ucfPlanToolNumber4,
                 				   ucfPlanToolNumber5,
                 				   ucfPlanToolDate2,
                 				   ucfPlanToolDate3,
                 				   ucfPlanToolDate4,
                 				   ucfPlanToolDate5,
                 				   ucfPlanToolFlag2,
                 				   ucfPlanToolFlag3,
                 				   ucfPlanToolFlag4,
                 				   ucfPlanToolFlag5,
                 				   ucfPlanToolVch2551,
                 				   ucfPlanToolVch2552,
                 				   ucfPlanToolVch2553,
                 				   ucfPlanToolVch40001,
                 				   ucfPlanToolVch40002,
                 				   displayLineNumber,
                 				   overUsedFlag,
                 				   securityGroup,
                 				   toolId,
                 				   bomCompToolId);
	 }
	 
	 private String inheritOrderSecurityGroup(String securityGroup) 
	 {
		 String orderSecurityGroup = (String)ContextUtil.getUser().getContext().get("ORDER_SECURITY_GROUP");
		 if(StringUtils.isNotEmpty(orderSecurityGroup))
		 {
			 String planSecurityGroupArr[] = orderSecurityGroup.split(",");
			 String itemSecurityGroupArr[] = StringUtils.isEmpty(securityGroup) ? null : securityGroup.split(",");
			 if(itemSecurityGroupArr != null && itemSecurityGroupArr.length > 0)
			 {
				 boolean securityGroupContain = false;
				 for(int i = 0; i < itemSecurityGroupArr.length; i++)
				 {
					 for(int j = 0; j < planSecurityGroupArr.length; j++)
					 {
						 if(StringUtils.equals(planSecurityGroupArr[j], itemSecurityGroupArr[i]))
						 {
							 securityGroupContain = true;
							 break;
						 }
					 }
				 }
				 if(!securityGroupContain)
				 {
					 securityGroup = securityGroup + "," + orderSecurityGroup;
				 }
			 }
			 else
			 {
				 securityGroup = orderSecurityGroup;
			 }
		 }
		 return securityGroup;
	 }
}
