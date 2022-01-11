/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    CallOrderCreate.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.05.22
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-05-22       Fred Ettefagh  
*  2018-06-02       Fred Ettefagh  added localScheduledStartDate to trace code for debug 
*  2018-07-09       Fred Ettefagh  Changed code for defect 820, update tasks based on each IM data row
*  2019-04-19       Fred Ettefagh  added code for repair order create 
*  2019-04-19       Fred Ettefagh  defect 1104, start and complete WO ALT when updating tasks after WO create.
*  2019-07_18       Fred Ettefagh  Defect 1327, removed insert into pwust_sfwid_order_desc to class OrderImplSfplPW
*  2019-10-17       Fred Ettefagh  PW_SMRO_eWORK_202, defect 1389, due to R2SP4 upgrade, after overhaul order create need to update new orders with plans work loc 
*  2019-11-20       Fred Ettefagh  SMRO_TR_319, move method to update order's ASGND_LOCATION_ID to commonDaoPW.updateSfwidOrderDescWorkLoc 
*/
package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.ORDER;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.ibaset.solumina.sfpl.dao.IOrderDao;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfwid.application.IOrderAlteration;
import com.ibaset.solumina.sfwid.dao.IAlterationDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfpl.dao.impl.LogOrderCreateErrors;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;

public class CallOrderCreate {
	
	@Reference 	IOrder orderImpl =null;
	@Reference	private OrderCreateDaoPW orderCreateDaoPW = null;
	@Reference	private IPlanDao planDao = null;
	@Reference	private IOrderDao orderDao = null;
	@Reference	private LogOrderCreateErrors logOrderCreateErrors;
    @Reference  private UpdateOverHaulTaskStatus updateOverHaulTaskStatus=null;
    @Reference  private IAlterationDao alterationDao = null;
	@Reference	private IOrderAlteration orderAlteration;
	@Reference CommonDaoPW commonDaoPW = null;
    
	    
	@SuppressWarnings("rawtypes")
	public String startWorkOrderAlt(String orderId){
		
			String newAlterationId = null;
			Map sfwidOrderDescMap = orderCreateDaoPW.selectPwustSfwidOrderDesc(orderId);
			String displaySequence = (String) sfwidOrderDescMap.get("DISPLAY_SEQUENCE");
			
			newAlterationId = orderAlteration.alterateStart(orderId, 
					                                        displaySequence, // alt_reason
					                                        ORDER, // alter_type
					                                        null, // alteration id
															null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
															null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
															null, null, null, null, 
															YES, // FND-18129 "Hide this alteration as it is created from background"
															null, null, null, null, null, null);
		    return newAlterationId;
	}
	
	
	@SuppressWarnings("rawtypes")
	public void endWorkOrderAlt(String alterationId,String orderId){
		
        Map taskMap = alterationDao.selectTaskId(orderId,alterationId);
        if (!taskMap.isEmpty()){
        	String tempTaskId = (String) taskMap.get("TASK_ID");
        	orderAlteration.completeAlteration(tempTaskId, null, null);
        }
	}
	
	
	@SuppressWarnings({ "rawtypes", "unused", "deprecation" })
	public void callOOBCreateOrder(String  salesOrderNumber,
			                       String  engineType,
			                       String  engineModel,
			                       String  workLocation,
			                       String  workScope,
			                       String  superiorNet,
			                       String  subNet,
			                       String  customer,
			                       String  planId,
			                       Number  planVer,
			                       Number  planRev,
			                       Number  planAlt,
			                       Number  planUpdtNo,
			                       String  partNo,
			                       String  partChg,
								   String todaysDate, 
								   String userName, 
								   Number orderQuantity, 
								   String effectivityType, 
								   String orderType,
								   String scheduledStartDate, 
								   String orderNoAutoGenerated,
								   String pwOrderNumber,
								   String calledFrom,
								   List imPlanList) {

		    try{

					orderImpl.createWorkOrder(partNo, //partNo, 
						                      partChg, 
						                      planId, 
						                      orderNoAutoGenerated, //pwOrderNumber, 
						                      orderQuantity, 
								              todaysDate,//scheduledStartDate, //planDataSelected.getSalesOrderSubActNoStartDate(), //scheduledStartDate, 
								              null,// scheduledEndDate, 
								              effectivityType, 
								              null,//effectivityUnit, 
								              null,//notes, 
								              null,//model, 
								              orderType, 
								              null,//customer, 
								              null,//customerDescription, 
								              null,//customerOrderNumber, 
								              null,//contract, 
								              workLocation, 
								              null,//orderReview, 
								              null,//owpId, 
								              userName,//ucfOrderVch1, 
								              null,//userEnteredData.getSalesOrderNumber(),//ucfOrderVch2, 
								              null,//userEnteredData.getSuperiorNet(),//ucfOrderVch3, 
								              null,//userEnteredData.getSubNet(),//ucfOrderVch4, 
								              null,//userEnteredData.getWorkScope(),//ucfOrderVch5, 
								              null,//ucfOrderVch6, 
								              todaysDate,//ucfOrderVch7, 
								              null,//userEnteredData.getEngineModel(),//ucfOrderVch8, 
								              null,//ucfOrderVch9, 
								              null,//userEnteredData.getCustomer(),//ucfOrderVch10, 
								              salesOrderNumber,//ucfOrderVch11, 
								              salesOrderNumber,//ucfOrderVch12,
								              pwOrderNumber,//ucfOrderVch13, 
								              engineModel,//ucfOrderVch14, 
								              null,//ucfOrderVch15, 
								              null,//ucfOrderNum1, 
								              null,//ucfOrderNum2, 
								              null,//ucfOrderNumber3,
								              null,//ucfOrderNumber4, 
								              null,//ucfOrderNumber5, 
								              null,//ucfOrderDate1, 
								              null,//ucfOrderDate2, 
								              null,//ucfOrderDate3, 
								              null,//ucfOrderDate4, 
								              null,//ucfOrderDate5, 
								              null,//ucfOrderFlag1, 
								              null,//ucfOrderFlag2, 
								              null,//ucfOrderFlag3, 
								              null,//ucfOrderFlag4, 
								              null,//ucfOrderFlag5, 
								              null,//ucfOrderVch2551, 
								              null,//ucfOrderVch2552, 
								              null,//ucfOrderVch2553, 
								              null,//ucfOrderVch40001, 
								              null,//ucfOrderVch40002, 
								              null,//replacementAction, 
								              null,//asWorkedBomId, 
								              null,//relatedOrderId, 
								              null,//selectedField, 
								              null,//groupOfSerialList,
								              null,//orderNumberForGroupOfSerial, 
								              null,//calledFrom, 
								              null,//serialNumbersPassed, 
								              null,//lotNumberPassed, 
								              null,//unitNumberPassed,
								              null,//planVersionPassed, 
								              null,//planRevisionPassed, 
								              null,//planAlterationsPassed, 
								              null,//hiddenValues,
								              null,//parentOrderId, 
								              null,//startingItemNo,
								              null,//startingItemRev,
								              null,//configNmae, 
								              null,//changeRequestId,
								              null,//plannedActionId, 
								              null,//releasePackageId,
								              null,//discrepancyId, 
								              null,//discrepancyLineNumber,
								              planVer, // Number planVersionSelected,
								              planRev, // Number planRevisionSelected,  // GE-4863
								              planAlt);
					


					Map sfwidOrderDescMap = orderCreateDaoPW.selectOrderId(orderNoAutoGenerated);
					String newOrderId  = (String)sfwidOrderDescMap.get("ORDER_ID");
					
					updateNewOrderWorkLoc(newOrderId,planId, planVer, planRev, planAlt);

					// defect 820 Start
					String alterationId= null;
					if (imPlanList!=null && !imPlanList.isEmpty()){
						Iterator iterator = imPlanList.iterator();
						
						
						String superNetPWString = null;
						String subNetPWString = null;
						String workScopePWString = null;
						String customerPWString = null;
						
						alterationId = startWorkOrderAlt(newOrderId);
						
						while (iterator.hasNext()){
							Map map = (Map) iterator.next();
							
							String workScopePW  = (String)  map.get("WORK_SCOPE");
							String superNetPW = (String) map.get("SUPERIORNET");
							String subNetPW = (String) map.get("SUBNET");
							String customerPW = (String) map.get("CUSTOMER");
							
							
							if (StringUtils.isNotBlank(superNetPW) || 
								StringUtils.isNotBlank(subNetPW) ||	
							    StringUtils.isNotBlank(workScopePW) ||
								StringUtils.isNotBlank(customerPW)){

									    updateOverHaulTaskStatus.updateTaskStatus(pwOrderNumber, 
									    		                                  orderNoAutoGenerated, 
									    		                                  partNo, 
									    		                                  planId, 
									    		                                  planVer, 
									    		                                  planRev, 
									    		                                  planAlt, 
									    		                                  null,//notes, 
									    		                                  calledFrom, 
									    		                                  null,//discrepancyId, 
									    		                                  null,//orderId, 
									    		                                  superNetPW, 
									    		                                  subNetPW, 
									    		                                  workScopePW, 
									    		                                  customerPW);
									    if (StringUtils.isNotBlank(superNetPW)){
										    if (StringUtils.isBlank(superNetPWString)){
										    	superNetPWString = superNetPW;
										    }else{
										    	superNetPWString = superNetPWString + "," + superNetPW;
										    }
									    }
									    
									    if (StringUtils.isNotBlank(subNetPW)){
										    if (StringUtils.isBlank(subNetPWString)){
										    	subNetPWString = subNetPW;
										    }else{
										    	subNetPWString = subNetPWString + "," + subNetPW;
										    }
									    }

									    if (StringUtils.isNotBlank(workScopePW)){
										    if (StringUtils.isBlank(workScopePWString)){
										    	workScopePWString = workScopePW;
										    }else{
										    	workScopePWString = workScopePWString + "," + workScopePW;
										    }
									    }
									    
									    if (StringUtils.isNotBlank(customerPW)){
										    if (StringUtils.isBlank(customerPWString)){
										    	customerPWString = customerPW;
										    }else{
										    	customerPWString = customerPWString + "," + customerPW;
										    }
									    }
									    
									   
						}
					}
						
					if (StringUtils.isNotBlank(superNetPWString) || 
						StringUtils.isNotBlank(subNetPWString) ||	
						StringUtils.isNotBlank(workScopePWString) ||
						StringUtils.isNotBlank(customerPWString)){
							
								orderCreateDaoPW.updatePwustSfwidOrderDesc(pwOrderNumber, workScopePWString, superNetPWString, subNetPWString, customerPWString);
					}
					endWorkOrderAlt(alterationId, newOrderId);
				}
				// defect 820 Start End

					
		    }catch (Exception e) {


					String errMsg = e.getMessage();
					
					if (StringUtils.isNotBlank(errMsg) && errMsg.length() >2000){
						errMsg = errMsg.substring(1, 1999);
					}
					
					logOrderCreateErrors.insertIntoErrorTable(errMsg,
							                              pwOrderNumber,
							                              partNo,
							                              planId,
							                              planRev,
							                              planRev,
							                              planAlt,
							                              planUpdtNo,
							                              salesOrderNumber,
							                              engineType,
							                              engineModel,
							                              workLocation,
							                              workScope,
							                              superiorNet,
							                              subNet,
							                              customer,
							                              calledFrom);
					pwOrderNumber =  "Error " + pwOrderNumber + " "  + errMsg;
					throw e ;
					
				}finally{
					ContextUtil.getUser().getContext().set("SUPER_NET", null);
					ContextUtil.getUser().getContext().set("SUB_NET",  null);
					ContextUtil.getUser().getContext().set("WORK_SCOPE", null);
					ContextUtil.getUser().getContext().set("CUSTOMER", null);
					ContextUtil.getUser().getContext().set("CUSTOMER", null);
					ContextUtil.getUser().getContext().set("SALES_ORDER_NO", null);
					ContextUtil.getUser().getContext().set("PW_ORDER_NO", null);
					ContextUtil.getUser().getContext().set("ENGINE_MODEL", null);
					
					ContextUtil.getUser().getContext().set("LAUNCH_ORDER", null);
					ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", null);
										
	            }				    	
			}


	public void updateNewOrderWorkLoc(String newOrderId, String planId, Number planVer, Number planRev,Number planAlt) {
		
		String workLocationId = null;
		Map planVmap =orderCreateDaoPW.selectSfplPlanV(planId, planVer, planRev, planAlt);
		workLocationId = (String) planVmap.get("PLND_LOCATION_ID");
		int i = commonDaoPW.updateSfwidOrderDescWorkLoc(newOrderId,workLocationId);
	}
}
	
