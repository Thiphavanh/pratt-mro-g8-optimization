package com.utas.solumina.sfwid.application.impl;

import java.util.Date;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfwid.application.ITool;
import com.ibaset.solumina.sfwid.dao.IOrderDao;

public abstract class UtasToolImplExt extends ImplementationOf<ITool> implements ITool 
{
	@Reference
	IOrderDao orderDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void insertAlteration(String orderId,
			 					 Number operationKey,
			 					 Number stepKey,
			 					 String operationNumber,
			 					 String toolNumber,
			 					 String toolChange,
			 					 String serialFlag,
			 					 String expirationFlag,
			 					 Number quantity,
			 					 String toolNotes,
			 					 String ucfPlanToolVch1,
			 					 String ucfPlanToolVch2,
			 					 String ucfPlanToolVch3,
			 					 String ucfPlanToolFlag1,
			 					 Number ucfPlanToolNumber1,
			 					 Date ucfPlanToolDate1,
			 					 String orientationFlag,
			 					 String crossOrderFlag,
			 					 String optionalFlag,
			 					 String referenceId,
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
			 					 String overUsedFlag)
	{
		Map orderInfo = orderDao.selectOrderDetails(orderId);
		String orderSecurityGroup = (String) orderInfo.get("SECURITY_GROUP");
		ContextUtil.getUser().getContext().set("ORDER_SECURITY_GROUP", orderSecurityGroup);
		
		try
		{
			Super.insertAlteration( orderId,
	                				operationKey,
	                				stepKey,
	                				operationNumber,
	                				toolNumber,
	                				toolChange,
	                				serialFlag,
	                				expirationFlag,
	                				quantity,
	                				toolNotes,
	                				ucfPlanToolVch1,
	                				ucfPlanToolVch2,
	                				ucfPlanToolVch3,
	                				ucfPlanToolFlag1,
	                				ucfPlanToolNumber1,
	                				ucfPlanToolDate1,
	                				orientationFlag,
	                				crossOrderFlag,
	                				optionalFlag,
	                				referenceId,
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
	                				overUsedFlag);
		}
		finally
		{
			ContextUtil.getUser().getContext().set("ORDER_SECURITY_GROUP", null);
		}
	}
}
