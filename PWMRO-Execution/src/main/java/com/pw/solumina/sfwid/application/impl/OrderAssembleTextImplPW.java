package com.pw.solumina.sfwid.application.impl;

import java.util.HashMap;
import java.util.List;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.pw.solumina.sfcore.application.impl.AssembleTextIUtilImpl;
import com.pw.solumina.sfcore.dao.AssembleTextDaoPW;
import com.ibaset.solumina.sfwid.application.IOrderAssembleText;

public abstract class OrderAssembleTextImplPW extends ImplementationOf<IOrderAssembleText> implements IOrderAssembleText{

	@Reference private AssembleTextDaoPW assembleTextDaoPW;
	@Reference private AssembleTextIUtilImpl assembleTextIUtilImpl;
	 
	
	public List runAssembleStepText(String currentNavLevelFieldName,
									String orderId,
									String operationNumber,
									String stepNumber,
									String lowNavigationLevel,
									String editMode){


		List orderTextList = null;
		
		orderTextList = Super.runAssembleStepText(currentNavLevelFieldName, orderId, operationNumber, stepNumber, lowNavigationLevel, editMode);

		if (orderTextList!=null && !orderTextList.isEmpty()){

			HashMap orderTextHashMap = (HashMap) orderTextList.get(0);
			if (orderTextHashMap!=null && !orderTextHashMap.isEmpty()){
				orderTextList= assembleTextIUtilImpl.buildOrderTextHashMap(orderTextHashMap, orderId);
			}
		}
		return orderTextList;
	}
}
