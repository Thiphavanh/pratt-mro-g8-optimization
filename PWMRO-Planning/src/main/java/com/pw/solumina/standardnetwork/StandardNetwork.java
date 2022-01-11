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
*  File:    StandardNetwork.java
* 
*  Created: 2017-09-06
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-06 	xcs4331		    Initial Release XXXXXXX
* 2019-02-20    xcs4331         defect 1130, process new xml format 
*/

package com.pw.solumina.standardnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.pw.solumina.sfpl.dao.StandardNetworkDao;

/**
 * @author xcs4331
 *
 */
public class StandardNetwork {

	@Reference public IEvent fndEvent;
	
	@Reference public StandardNetworkDao standardNetworkDao;
	
	@Reference public IMessage message;
	
	@Reference private IValidator validator;
	
	// this class is used from standard network dispatch 
	

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public Map getSTDNetworkValueForEngineType(String userid){
		
		Map returnMap = new HashMap();
		String engineType = (String)ContextUtil.getUser().getContext().get("SD_ENGINE_TYPE");
		returnMap.put("ENGINE_TYPE", engineType);
		return returnMap;
	}
	
	@SuppressWarnings("deprecation")
	public void launchStandardNetworkDispatch(String engineType){
		
		ContextUtil.getUser().getContext().set("SD_ENGINE_TYPE", engineType);
		//StringBuilder sb = new StringBuilder("\"ENGINE_TYPE = '").append(engineType).append("'\""); 
		//fndEvent.launchDispatch("Process Planning", "Standard Network", null, sb.toString()); 
		fndEvent.launchDispatch("Process Planning", "Standard Network", null, null);
		
		
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public List selectStandardNetworkParentList(String whereClause){
		
		List networkStandardParentList = new ArrayList<Map>(0);
		
		String engineType = (String)ContextUtil.getUser().getContext().get("SD_ENGINE_TYPE");
		
		networkStandardParentList = standardNetworkDao.selectStandardNetworkParentList(engineType, whereClause);
		
		return networkStandardParentList;
		
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public List selectStandardNetworkChildList(String networkActNo, String whereClause){
		
		List networkStandardChildList = new ArrayList<Map>(0);
		
		String engineType = (String)ContextUtil.getUser().getContext().get("SD_ENGINE_TYPE");

		networkStandardChildList = standardNetworkDao.selectStandardNetworkRecord(engineType,networkActNo,whereClause);
		
		return networkStandardChildList;
		
	}

	@SuppressWarnings("deprecation")
	public void insertNetworkDef(String networkNo, 
								String networkNodeNo, 
								String networkDesc, 
								String subNetworkNo,
								String subNetworkNodeNo,
								String subNetworkDesc, 
								String engineType,
								String engineTypeDesc, 
								String networkActNo,
								String networkActDesc, 
								String subNetworkActNo,
								String subNetworkActDesc,
								String moduleCatCode, 
								String moduleCatDesc,
								String workCenter,
								String controlKey,
								String gate, 
								String duration,
								String durationUnit,
								String work, 
								String workUnit, 
								String eWorkScopingFlag,
								String lidNo, 
								String obsoleteFlag){

		validator.checkUserPrivilege("STANDARD_NETWORK_UPDATE");
		
		
		if (StringUtils.isNotBlank(engineType) &&
			StringUtils.isNotBlank(networkNo) &&
			StringUtils.isNotBlank(networkNodeNo) &&
			StringUtils.isNotBlank(subNetworkNo) &&
			StringUtils.isNotBlank(subNetworkNodeNo)){

				checkForDuplicateKey(networkNo, networkNodeNo, subNetworkNo,subNetworkNodeNo);

				standardNetworkDao.insertNetworkDef(networkNo, 
							                        networkNodeNo, 
							                        networkDesc, 
							                        subNetworkNo,
							                        subNetworkNodeNo,
							                        subNetworkDesc, 
							                        engineType,
							                        engineTypeDesc, 
							                        networkActNo,
							                        networkActDesc, 
							                        subNetworkActNo,
							                        subNetworkActDesc,
							                        moduleCatCode, 
							                        moduleCatDesc,
							                        workCenter,
							                        controlKey,
							                        gate, 
							                        duration,
							                        durationUnit,
							                        work, 
							                        workUnit, 
							                        eWorkScopingFlag,
							                        lidNo, 
							                        "N");	
			
				ContextUtil.getUser().getContext().set("SD_ENGINE_TYPE", engineType);
				fndEvent.refreshTool();

		}else{
			message.raiseError("MFI_20"," network No = " + networkNo  + " network Node No = " + networkNodeNo + "  sub Network No = " + subNetworkNo + " subNetwork Node No = "+ subNetworkNodeNo + " Cannot be null");
		}
		
		

	}

	
	@SuppressWarnings("rawtypes")
	public void checkForDuplicateKey(String networkNo, String networkNodeNo, String subNetworkNo,String subNetworkNodeNo) {

		List list = standardNetworkDao.selectStandardNetworkRecord(networkNo, networkNodeNo, subNetworkNo, subNetworkNodeNo);
		if (list !=null && !list.isEmpty()){
			message.raiseError("MFI_20"," Duplicate data network No = " + networkNo  + " network Node No = " + networkNodeNo + "  sub Network No = " + subNetworkNo + " subNetwork Node No = "+ subNetworkNodeNo );
		}
	}

	public void updateNetworkDef(String networkNo, 
			                     String networkNodeNo, 
			                     String networkDesc, 
			                     String subNetworkNo,
			                     String subNetworkNodeNo,
			                     String subNetworkDesc, 
			                     String engineType,
			                     String engineTypeDesc, 
			                     String networkActNo,      //  SUPERIOR_NETWORK_ACT
			                     String networkActDesc, 
			                     String subNetworkActNo,   
			                     String subNetworkActDesc,
			                     String moduleCatCode, 
			                     String moduleCatDesc,
			                     String workCenter,
			                     String controlKey,
			                     String gate, 
			                     String duration,
			                     String durationUnit,
			                     String work, 
			                     String workUnit, 
			                     String eWorkScopingFlag,
			                     String lidNo, 
			                     String obsoleteFlag){
			
		validator.checkUserPrivilege("STANDARD_NETWORK_UPDATE");
		
		if (StringUtils.isNotBlank(engineType) &&
			StringUtils.isNotBlank(networkNo) &&
			StringUtils.isNotBlank(networkNodeNo) &&
			StringUtils.isNotBlank(subNetworkNo) &&
			StringUtils.isNotBlank(subNetworkNodeNo)){
			
			        standardNetworkDao.updateNetworkDef(networkNo, 
                                                        networkNodeNo, 
                                                        networkDesc, 
                                                        subNetworkNo,
                                                        subNetworkNodeNo,
                                                        subNetworkDesc, 
                                                        engineType,
                                                        engineTypeDesc, 
                                                        networkActNo,
                                                        networkActDesc, 
                                                        subNetworkActNo,
                                                        subNetworkActDesc,
                                                        moduleCatCode, 
                                                        moduleCatDesc,
                                                        workCenter,
                                                        controlKey,
                                                        gate, 
                                                        duration,
                                                        durationUnit,
                                                        work, 
                                                        workUnit, 
                                                        eWorkScopingFlag,
                                                        lidNo, 
                                                        obsoleteFlag);	
			
			
				standardNetworkDao.updateNetworkDef(engineTypeDesc, 
						                            networkNo, 
													networkActDesc, 
													subNetworkNo, 
													subNetworkActDesc,
													moduleCatCode, 
													moduleCatDesc, 
													workCenter, 
													controlKey, 
													gate, 
													duration, 
													durationUnit, 
													work, 
													workUnit, 
													eWorkScopingFlag, 
													lidNo,
													obsoleteFlag,
													networkDesc,
													subNetworkDesc,
													engineType,
													networkActNo,
													subNetworkActNo,
													subNetworkNodeNo,
													networkNodeNo);
			
				fndEvent.refreshTool();
			}else{
				message.raiseError("MFI_20"," engine Type, network No, network node no, sub network no, sub network node no, can not be blank");
			}
		
	}	

	
	public void deleteNetworkDef(String networkNo, 
							     String networkNodeNo, 
							     String subNetworkNo,
							     String subNetworkNodeNo){
		
		
		validator.checkUserPrivilege("STANDARD_NETWORK_UPDATE");
		
		if (StringUtils.isNotBlank(networkNo) &&
			StringUtils.isNotBlank(networkNodeNo) &&
			StringUtils.isNotBlank(subNetworkNo) &&
			StringUtils.isNotBlank(subNetworkNodeNo)){
		
				standardNetworkDao.deletetSandardNetworkRecord(networkNo,networkNodeNo, subNetworkNo,subNetworkNodeNo);
				
				fndEvent.refreshTool();
		}
		
	}
	
}
