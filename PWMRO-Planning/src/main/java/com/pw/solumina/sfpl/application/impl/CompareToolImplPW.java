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
*  File:    CompareToolImplPW.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.08.07
* 
*  Author:  Thien-Long Phan
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-07-08     Thien-Long Phan  Defect 880. Order Compare displays PW_ORDER_NO
*  2018-16-08     Thien-Long Phan  Defect 880. Display PW_ORDER_NO for From Order No column
*/
package com.pw.solumina.sfpl.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static com.ibaset.common.util.SoluminaUtils.*;
import static org.apache.commons.lang.StringUtils.*;
import static com.ibaset.common.SoluminaConstants.*;
import static com.ibaset.common.DbColumnNameConstants.*;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.sql.security.SQLSecurityManager;
import com.ibaset.common.sql.security.UnauthorizedAccessException;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IUidEntry;
import com.ibaset.solumina.sffnd.dao.IStandardOperationDao;
import com.ibaset.solumina.sfpl.application.ICompareTool;
import com.ibaset.solumina.sfpl.application.ICompareToolHelper;
import com.ibaset.solumina.sfpl.dao.ICompareToolDao;
import com.ibaset.solumina.sfpl.dao.IOperationDao;
import com.ibaset.solumina.sfwid.application.IOrderAssembleText;
import com.pw.solumina.sfpl.dao.CompareToolDaoPW;

public abstract class CompareToolImplPW extends ImplementationOf<ICompareTool> implements ICompareTool
{
    private ICompareToolDao compareToolDao = null;

    private IUidEntry uidEntry = null;

    protected IParse parse = null;
    
    private IMessage message = null;
    
    private IOperationDao operationDao = null;
    
    private IStandardOperationDao standardOperationDao = null;
    
    @Reference
    private IOrderAssembleText orderAssembleText = null;
    
    @Reference
    private ICompareToolHelper compareToolHelper = null;
    
    @Reference
    private CompareToolDaoPW compareToolDaoPW;

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#generatePlanInstructions(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.Number,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.String)
     */
    public List generatePlanInstructions(String fromPlanId,
                                         Number fromPlanVersion,
                                         Number fromPlanRevision,
                                         Number fromPlanAlterations,
                                         Number fromPlanUpdateNumber,
                                         String toPlanId,
                                         Number toPlanVersion,
                                         Number toPlanRevision,
                                         Number toPlanAlterations,
                                         Number toPlanUpdateNumber,
                                         String fromOrderId,
                                         String toOrderId)
    {
        return this.generatePlanInstructions( fromPlanId, 
                                              fromPlanVersion, 
                                              fromPlanRevision, 
                                              fromPlanAlterations, 
                                              fromPlanUpdateNumber, 
                                              toPlanId, 
                                              toPlanVersion, 
                                              toPlanRevision, 
                                              toPlanAlterations, 
                                              toPlanUpdateNumber, 
                                              fromOrderId, 
                                              toOrderId, 
                                              null, 
                                              null,
                                              null);
    }
    
	public List generatePlanInstructions(String fromPlanId,
										 Number fromPlanVersion,
										 Number fromPlanRevision,
										 Number fromPlanAlterations,
										 Number fromPlanUpdateNumber,
										 String toPlanId,
										 Number toPlanVersion,
										 Number toPlanRevision,
										 Number toPlanAlterations,
										 Number toPlanUpdateNumber,
										 String fromOrderId,
										 String toOrderId,
										 String fromAltId, // FND-25023
										 String toAltId, // FND-25023
										 Number compareOperKey) // FND-25023
	{
		return this.generatePlanInstructions(fromPlanId,
										     fromPlanVersion,
										     fromPlanRevision, 
										     fromPlanAlterations, 
										     fromPlanUpdateNumber, 
										     toPlanId,
										     toPlanVersion, 
										     toPlanRevision,
										     toPlanAlterations,
										     toPlanUpdateNumber,
										     fromOrderId, 
										     toOrderId, 
										     fromAltId, 
										     toAltId, 
										     compareOperKey,
										     null);
	}
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#generatePlanInstructions(java.lang.String, 
     *      java.lang.Number, java.lang.Number, java.lang.Number, 
     *      java.lang.Number, java.lang.String, java.lang.Number, 
     *      java.lang.Number, java.lang.Number, java.lang.Number, 
     *      java.lang.String, java.lang.String, java.lang.String, 
     *      java.lang.String, java.lang.Number)
     */
	public List generatePlanInstructions(String fromPlanId,
                                         Number fromPlanVersion,
                                         Number fromPlanRevision,
                                         Number fromPlanAlterations,
                                         Number fromPlanUpdateNumber,
                                         String toPlanId,
                                         Number toPlanVersion,
                                         Number toPlanRevision,
                                         Number toPlanAlterations,
                                         Number toPlanUpdateNumber,
                                         String fromOrderId,
                                         String toOrderId,
                                         String fromAltId, // FND-25023
                                         String toAltId, // FND-25023
                                         Number compareOperKey, // FND-25023
                                         String calledFromChgAck) //GE-1213
    {
        // GE-1668 Stored main Plan Variable to another variables for further use
        String origFromPlanId = fromPlanId ;
        Number origFromPlanVersion = fromPlanVersion ;
        Number origFromPlanRevision = fromPlanRevision ;
        Number origFromPlanAlterations = fromPlanAlterations ;
        String origToPlanId = toPlanId ;
        Number origToPlanVersion = toPlanVersion ;
        Number origToPlanRevision = toPlanRevision ;
        Number origToPlanAlterations = toPlanAlterations ;
        
        String fromType = EMPTY;
        String toType = EMPTY;
        //FND-26246
        if(isNotEmpty(fromPlanId))
        {
        	fromType = "PLAN" ;
        }
        if(isNotEmpty(toPlanId))
        {
        	toType = "PLAN" ;
        }
        
        //if (isNotEmpty(fromOrderId))
        if (StringUtils.isNotEmpty(fromOrderId))
        {
            fromType = "ORDER";
            
            // FND-18325
            if(isEmpty(toOrderId) && isEmpty(toPlanId))
            {
            	// Source and destination Plan or Order must be selected for comparison
            	message.raiseError("MFI_B27F935DCD274B64A019AAE775EFC5EF");
            }
        }

        if (isNotEmpty(toOrderId))
        {
            toType = "ORDER";
            
            // FND-18325
            if(isEmpty(fromOrderId) && isEmpty(fromPlanId))
            {
            	// Source and destination Plan or Order must be selected for comparison
            	message.raiseError("MFI_B27F935DCD274B64A019AAE775EFC5EF");
            }
        }
        
        
        // FND-18325
        if(equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(toType, "PLAN"))
        {
        	if(isEmpty(fromPlanId) && isEmpty(toPlanId))
            {
        		// Source and destination Plan or Order must be selected for comparison
            	message.raiseError("MFI_B27F935DCD274B64A019AAE775EFC5EF");
            }
        }

        String fromTo = EMPTY;

        if (equalsIgnoreCase(fromType, toType))
        {
            fromTo = fromType;
        }
        else
        {
            fromTo = fromType + "/" + toType;
        }

        String alterationNumber = EMPTY;
        String changeAuthObjectId = EMPTY;
        String securityGroup = null;
        String fromDocumentType = null;

        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            Map planInfo = compareToolDao.selectPlanHeaderData(fromPlanId,
                                                               fromPlanVersion,
                                                               fromPlanRevision,
                                                               fromPlanAlterations);

            changeAuthObjectId = (String) planInfo.get("OBJECT_ID");
            securityGroup = (String) planInfo.get("SECURITY_GROUP");
            fromDocumentType = (String) planInfo.get("DOC_TYPE"); // FND-28715
        }
        //else if (equalsIgnoreCase(fromType, "ORDER"))
        else if (StringUtils.equals(fromType, "ORDER"))
        {
            if(isEmpty(toAltId))
            {
                try
                {
                    Map orderInfo = compareToolDao.selectOrderChangeInfo(fromOrderId);
    
                      alterationNumber = (String) orderInfo.get("ALT_NO");
                    changeAuthObjectId = (String) orderInfo.get("OBJECT_ID");
                }
                catch (EmptyResultDataAccessException e)
                {
    
                }
            }
            else
            {
                // FND-25023
                try
                {
                    Map orderInfo = compareToolDao.selectOrderChangeInfoAltLog(toAltId);
    
                      alterationNumber = (String) orderInfo.get("ALT_NO");
                    changeAuthObjectId = (String) orderInfo.get("OBJECT_ID");
                }
                catch (EmptyResultDataAccessException e)
                {
    
                }
            }
        }

        List returnList = new ArrayList();

        Map returnMap = new ListOrderedMap();

        String changeIndicator = EMPTY;
        
        // FND-28715
        if(equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(fromDocumentType,
                STANDARD_OPERATION_TEXT))
        {
        	fromTo = "STDOPER";
        }

        // Start Plan Header Data
        // GE-1904
        String image;
		comparePlanHeaderData(fromPlanId, 
							  fromPlanVersion, 
							  fromPlanRevision,
							  fromPlanAlterations, 
							  fromPlanUpdateNumber, 
							  toPlanId,
							  toPlanVersion, 
							  toPlanRevision, 
							  toPlanAlterations,
							  toPlanUpdateNumber, 
							  fromOrderId, 
							  toOrderId, 
							  fromAltId, 
							  toAltId,
							  fromType, 
							  toType, 
							  fromTo, 
							  alterationNumber, 
							  changeAuthObjectId,
							  securityGroup, 
							  returnList, 
							  returnMap);
        // End Plan Header Data

        // Start Plan Header Text
		// GE-1904
        String params;
		comparePlanHeaderText(fromPlanId, 
							  fromPlanUpdateNumber, 
							  toPlanId,
							  toPlanUpdateNumber, 
							  fromOrderId, 
							  toOrderId, 
							  fromAltId, 
							  toAltId,
							  fromType, 
							  toType, 
							  fromTo, 
							  returnList);
        // End Plan Header Standard Text

        
        // Plan Text Image List
        // FND-28458 - Added comparison for normal text also
		// GE-1904
        comparePlanTextIllustration(fromPlanId, 
        							fromPlanUpdateNumber, 
        							toPlanId,
        							toPlanUpdateNumber, 
        							fromOrderId, 
        							toOrderId, 
        							fromAltId, 
        							toAltId,
        							fromType, 
        							toType, 
        							fromTo, 
        							returnList);

        // End Plan Header Illustrations

        // End Plan Header Text
        
        // Start Operation        

        //FND-25704
        List operationNumberList = new ArrayList();
        // GE-1904
        operationNumberList = getOperationNumberList(fromPlanId,
													 fromPlanVersion, 
													 fromPlanRevision, 
													 fromPlanAlterations,
													 toPlanId, 
													 toPlanVersion, 
													 toPlanRevision, 
													 toPlanAlterations,
													 fromOrderId, 
													 toOrderId, 
													 fromAltId, 
													 toAltId, 
													 compareOperKey,
													 fromType, 
													 toType, 
													 operationNumberList);        

        // 05/07/2011 Suresh FND-15735
        // Performance: Compare Plan/Order 
        Map booleanCompareStateMap = null;
        
        ///////////////
        
        Iterator operationNumberIterator = operationNumberList.listIterator();

        while (operationNumberIterator.hasNext())
        {
            // GE-1668 Reset main Plan Variables to Origin main Plan values each time operation loop begin to execute.
            fromPlanId  = origFromPlanId ;
            fromPlanVersion = origFromPlanVersion ;
            fromPlanRevision = origFromPlanRevision ;
            fromPlanAlterations = origFromPlanAlterations ;
            toPlanId = origToPlanId ;
            toPlanVersion = origToPlanVersion ;
            toPlanRevision = origToPlanRevision ;
            toPlanAlterations = origToPlanAlterations ;
            
            // 05/07/2011 Suresh FND-15735
            // Performance: Compare Plan/Order
        	booleanCompareStateMap = new ListOrderedMap();
        	
            Map operationNumberMap = (Map) operationNumberIterator.next();

            String operationNumber = (String) operationNumberMap.get("OPER_NO");

            boolean fromOperationNumberExists = false;
            boolean toOperationNumberExists = false;
            
            Number fromOperationKey = null;
            Number fromOperationUpdateNumber = null;
            
            Number toOperationKey = null;
            Number toOperationUpdateNumber = null;
            
            //GE-2317
            Number fromScopeOperationKey = null;
            Number toScopeOperationKey = null;
            Map fromInfoMap = new HashMap();
            Map toInfoMap = new HashMap();
            
            Map fromStdOperMap = new HashMap();
            String fromStdOperPlanId = fromPlanId;
            Number fromStdOperPlanVersion = fromPlanVersion;
            Number fromStdOperPlanRevision = fromPlanRevision;
            Number fromStdOperPlanAlterations = fromPlanAlterations;
            Number fromStdOperOperationKey = null;
            Number fromStdOperOperUpdateNo = null;
            String fromMainOrStdOperOperNo = null;
            
            Map toStdOperMap = new HashMap();
            String toStdOperPlanId = toPlanId;
            Number toStdOperPlanVersion = toPlanVersion;
            Number toStdOperPlanRevision = toPlanRevision;
            Number toStdOperPlanAlterations = toPlanAlterations;
            Number toStdOperOperationKey = null;
            Number toStdOperOperUpdateNo = null;
            String toMainOrStdOperOperNo = null;
            
            //FND-25704
            Map fromOperationNumberMap = new ListOrderedMap();
            Map toOperationNumberMap = new ListOrderedMap();

            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {
                // Checks whether From Operation Number exists or not
            	try
                {
                	fromOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                        fromPlanId,
                                                                                        fromPlanVersion,
                                                                                        fromPlanRevision,
                                                                                        fromPlanAlterations);
                	//GE-1668 main from plan operation key and update number set
                	fromOperationKey = (Number) fromOperationNumberMap.get( "OPER_KEY" );
                	fromOperationUpdateNumber = (Number) fromOperationNumberMap.get( "OPER_UPDT_NO" );
                	fromStdOperOperationKey = fromOperationKey;
                	fromStdOperOperUpdateNo = fromOperationUpdateNumber;
                	// GE-2317
                	fromScopeOperationKey = fromOperationKey;
                	fromMainOrStdOperOperNo = operationNumber; // GE-2317
                }
                catch(NullPointerException e)
                {
                	
                }
            	// Checks whether To Operation Number exists or not
                try
                {
                	toOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                        														      toPlanId,
                        														      toPlanVersion,
                        														      toPlanRevision,
                        														      toPlanAlterations);
                	//GE-1668 main to plan operation key and update number set
                	toOperationKey = (Number) toOperationNumberMap.get( "OPER_KEY" );
                    toOperationUpdateNumber = (Number) toOperationNumberMap.get( "OPER_UPDT_NO" );
                	toStdOperOperationKey = toOperationKey;
                	toStdOperOperUpdateNo = toOperationUpdateNumber;
                	// GE-2317
                	toScopeOperationKey = toOperationKey;
                	toMainOrStdOperOperNo = operationNumber; // GE-2317
                }
                catch(NullPointerException e)
                {
                	
                }
                
                //GE-1668 check for operation load from standard Operation if it is Authoring mode standard operation otherwise not.
                if(fromOperationNumberMap != null && !fromOperationNumberMap.isEmpty())
                {
                    fromStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(fromPlanId, 
                                                                                       fromPlanVersion, 
                                                                                       fromPlanRevision, 
                                                                                       fromPlanAlterations, 
                                                                                       fromOperationKey,
                                                                                       fromOperationUpdateNumber);
                            
                    if(fromStdOperMap != null && !fromStdOperMap.isEmpty())
                    {
                        fromStdOperPlanId = (String) fromStdOperMap.get( "STDOPER_PLAN_ID" );
                        fromStdOperPlanVersion = (Number) fromStdOperMap.get( "STDOPER_PLAN_VER" );
                        fromStdOperPlanRevision = (Number) fromStdOperMap.get( "STDOPER_PLAN_REV" );
                        fromStdOperPlanAlterations = (Number) fromStdOperMap.get( "STDOPER_PLAN_ALT" );
                        fromStdOperOperationKey =  (Number) fromStdOperMap.get( "STDOPER_OPER_KEY" );
                        fromStdOperOperUpdateNo = (Number) fromStdOperMap.get("STDOPER_OPER_UPDT_NO");
                        fromMainOrStdOperOperNo = (String) fromStdOperMap.get("STDOPER_OPER_NO");
                        
                        fromPlanId = fromStdOperPlanId;
                        fromPlanVersion = fromStdOperPlanVersion;
                        fromPlanRevision = fromStdOperPlanRevision;
                        fromPlanAlterations = fromStdOperPlanAlterations;
                        fromOperationKey = fromStdOperOperationKey;
                        fromOperationUpdateNumber = fromStdOperOperUpdateNo;
                        
                    }
                }
                
                if(toOperationNumberMap != null && !toOperationNumberMap.isEmpty())
                {
                    toStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(toPlanId, 
                                                                                     toPlanVersion, 
                                                                                     toPlanRevision, 
                                                                                     toPlanAlterations,
                                                                                     toOperationKey,
                                                                                     toOperationUpdateNumber); 
                            
                    if(toStdOperMap != null && !toStdOperMap.isEmpty())
                    {
                        toStdOperPlanId = (String) toStdOperMap.get( "STDOPER_PLAN_ID" );
                        toStdOperPlanVersion = (Number) toStdOperMap.get( "STDOPER_PLAN_VER" );
                        toStdOperPlanRevision = (Number) toStdOperMap.get( "STDOPER_PLAN_REV" );
                        toStdOperPlanAlterations = (Number) toStdOperMap.get( "STDOPER_PLAN_ALT" );
                        toStdOperOperationKey =  (Number) toStdOperMap.get( "STDOPER_OPER_KEY" );
                        toStdOperOperUpdateNo = (Number) toStdOperMap.get("STDOPER_OPER_UPDT_NO");
                        toMainOrStdOperOperNo = (String) toStdOperMap.get("STDOPER_OPER_NO");
                        
                        toPlanId = toStdOperPlanId;
                        toPlanVersion = toStdOperPlanVersion;
                        toPlanRevision = toStdOperPlanRevision;
                        toPlanAlterations = toStdOperPlanAlterations;
                        toOperationKey = toStdOperOperationKey;
                        toOperationUpdateNumber = toStdOperOperUpdateNo;
                    }
                }
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(fromType, toType))
            {
                if(isEmpty( fromAltId ))
                {
                    // Checks whether From Operation Number exists or not
                	try
                	{
                		fromOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                           fromOrderId,
                                                                                           NumberUtils.INTEGER_MINUS_ONE);
                		fromMainOrStdOperOperNo = operationNumber;
                	}
                    catch(NullPointerException e)
                    {
                    	
                    }
                }
                else
                {	
                	// FND-25023
                    // Checks whether From Operation Number exists in alteration or not
                	try
                	{
                		fromOperationNumberMap = compareToolDao.selectOperationNumberExistsAltLog2(operationNumber,
                                                                                                 fromOrderId,
                                                                                                 NumberUtils.INTEGER_MINUS_ONE,
                                                                                                 fromAltId);
                	}
                    catch(NullPointerException e)
                    {
                    	
                    }
                }

                if(isEmpty( toAltId ))
                {
                	// Checks whether To Operation Number exists or not
                	try
                	{                        
                		toOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                         toOrderId,
                                                                                         NumberUtils.INTEGER_MINUS_ONE);
                		toMainOrStdOperOperNo = operationNumber;
                    }
                    catch(NullPointerException e)
                    {
                    	
                    }
                }
                else
                {
                	//FND-25023 Checks whether To Operation Number exists in alteration or not
                	try
                	{                        
                		toOperationNumberMap = compareToolDao.selectOperationNumberExistsAltLog2(operationNumber,
                                                                                                 toOrderId,
                                                                                                 NumberUtils.INTEGER_MINUS_ONE,
                                                                                                 toAltId);
                    }
                    catch(NullPointerException e)
                    {
                    	
                    }
                }
                
                if(fromOperationNumberMap != null && !fromOperationNumberMap.isEmpty())
                {
                    fromOperationKey = (Number) fromOperationNumberMap.get( "OPER_KEY" );
                    fromOperationUpdateNumber = (Number) fromOperationNumberMap.get( "OPER_UPDT_NO" );
                }
                if(toOperationNumberMap != null && !toOperationNumberMap.isEmpty())
                {
                    toOperationKey = (Number) toOperationNumberMap.get( "OPER_KEY" );
                    toOperationUpdateNumber = (Number) toOperationNumberMap.get( "OPER_UPDT_NO" );
                }
                
                
            }
            else
            {
                if (equalsIgnoreCase(fromType, "PLAN")
                        && equalsIgnoreCase(toType, "ORDER"))
                {
                    // Checks whether From Operation Number exists or not
                	try
                	{
                		fromOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                            fromPlanId,
                                                                                            fromPlanVersion,
                                                                                            fromPlanRevision,
                                                                                            fromPlanAlterations);
                		//GE-1668 main from plan operation key and update number set
                        fromOperationKey = (Number) fromOperationNumberMap.get( "OPER_KEY" );
                        fromOperationUpdateNumber = (Number) fromOperationNumberMap.get( "OPER_UPDT_NO" );
                        fromStdOperOperationKey = fromOperationKey;
                        fromStdOperOperUpdateNo = fromOperationUpdateNumber;
                        // GE-2317
                        fromScopeOperationKey = fromOperationKey;
                        fromMainOrStdOperOperNo = operationNumber; // GE-2317
                    }
                    catch(NullPointerException e)
                    {
                    	
                    }

                    // Checks whether To Operation Number exists or not
                    try
                    {
                    	toOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                          toOrderId,
                                                                                          NumberUtils.INTEGER_MINUS_ONE);
                    	toOperationKey = (Number) toOperationNumberMap.get( "OPER_KEY" );
                    	toOperationUpdateNumber = (Number) toOperationNumberMap.get( "OPER_UPDT_NO" );
                    	// GE-2317
                    	toScopeOperationKey = toOperationKey;
                    	toMainOrStdOperOperNo = operationNumber;
                    }
                    catch(NullPointerException e)
                    {
                    	
                    }
                    
                    //GE-1668 check for operation load from standard Operation if it is Authoring mode standard operation otherwise not.
                    if(fromOperationNumberMap != null && !fromOperationNumberMap.isEmpty())
                    {
                        fromStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(fromPlanId, 
                                                                                           fromPlanVersion, 
                                                                                           fromPlanRevision, 
                                                                                           fromPlanAlterations, 
                                                                                           fromOperationKey,
                                                                                           fromOperationUpdateNumber);
                                
                        if(fromStdOperMap != null && !fromStdOperMap.isEmpty())
                        {
                            fromStdOperPlanId = (String) fromStdOperMap.get( "STDOPER_PLAN_ID" );
                            fromStdOperPlanVersion = (Number) fromStdOperMap.get( "STDOPER_PLAN_VER" );
                            fromStdOperPlanRevision = (Number) fromStdOperMap.get( "STDOPER_PLAN_REV" );
                            fromStdOperPlanAlterations = (Number) fromStdOperMap.get( "STDOPER_PLAN_ALT" );
                            fromStdOperOperationKey =  (Number) fromStdOperMap.get( "STDOPER_OPER_KEY" );
                            fromStdOperOperUpdateNo = (Number) fromStdOperMap.get("STDOPER_OPER_UPDT_NO");
                            fromMainOrStdOperOperNo = (String) fromStdOperMap.get("STDOPER_OPER_NO");
                            
                            fromPlanId = fromStdOperPlanId;
                            fromPlanVersion = fromStdOperPlanVersion;
                            fromPlanRevision = fromStdOperPlanRevision;
                            fromPlanAlterations = fromStdOperPlanAlterations;
                            fromOperationKey = fromStdOperOperationKey;
                            fromOperationUpdateNumber = fromStdOperOperUpdateNo;
                            
                        }
                    }
                }
                else if (equalsIgnoreCase(fromType, "ORDER")
                        && equalsIgnoreCase(toType, "PLAN"))
                {
                    // Checks whether From Operation Number exists or not
                	try
                	{
                		fromOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                           fromOrderId,
                                                                                           NumberUtils.INTEGER_MINUS_ONE);
                		fromOperationKey = (Number) fromOperationNumberMap.get( "OPER_KEY" );
                		fromOperationUpdateNumber = (Number) fromOperationNumberMap.get( "OPER_UPDT_NO" );
                		// GE-2317
                		fromScopeOperationKey = fromOperationKey;
                		fromMainOrStdOperOperNo = operationNumber;
                	}
                    catch(NullPointerException e)
                    {
                    	
                    }

                    // Checks whether To Operation Number exists or not
                	try
                	{
                		toOperationNumberMap = compareToolDao.selectOperationNumberExists(operationNumber,
                                                                                         toPlanId,
                                                                                         toPlanVersion,
                                                                                         toPlanRevision,
                                                                                         toPlanAlterations);
                		//GE-1668 main to plan operation key and update number set
                        toOperationKey = (Number) toOperationNumberMap.get( "OPER_KEY" );
                        toOperationUpdateNumber = (Number) toOperationNumberMap.get( "OPER_UPDT_NO" );
                        toStdOperOperationKey = toOperationKey;
                        toStdOperOperUpdateNo = toOperationUpdateNumber;
                        // GE-2317
                		toScopeOperationKey = toOperationKey;
                		toMainOrStdOperOperNo = operationNumber; // GE-2317
                    }
                    catch(NullPointerException e)
                    {
                    	
                    }
                	
                	//GE-1668 check for operation load from standard Operation if it is Authoring mode standard operation otherwise not.
                	if(toOperationNumberMap != null && !toOperationNumberMap.isEmpty())
                    {
                        toStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(toPlanId, 
                                                                                         toPlanVersion, 
                                                                                         toPlanRevision, 
                                                                                         toPlanAlterations,
                                                                                         toOperationKey,
                                                                                         toOperationUpdateNumber); 
                                
                        if(toStdOperMap != null && !toStdOperMap.isEmpty())
                        {
                            toStdOperPlanId = (String) toStdOperMap.get( "STDOPER_PLAN_ID" );
                            toStdOperPlanVersion = (Number) toStdOperMap.get( "STDOPER_PLAN_VER" );
                            toStdOperPlanRevision = (Number) toStdOperMap.get( "STDOPER_PLAN_REV" );
                            toStdOperPlanAlterations = (Number) toStdOperMap.get( "STDOPER_PLAN_ALT" );
                            toStdOperOperationKey =  (Number) toStdOperMap.get( "STDOPER_OPER_KEY" );
                            toStdOperOperUpdateNo = (Number) toStdOperMap.get("STDOPER_OPER_UPDT_NO");
                            toMainOrStdOperOperNo = (String) toStdOperMap.get("STDOPER_OPER_NO");
                            
                            toPlanId = toStdOperPlanId;
                            toPlanVersion = toStdOperPlanVersion;
                            toPlanRevision = toStdOperPlanRevision;
                            toPlanAlterations = toStdOperPlanAlterations;
                            toOperationKey = toStdOperOperationKey;
                            toOperationUpdateNumber = toStdOperOperUpdateNo;
                        }
                    }
                }
            }
            
            // GE-2317
            fromInfoMap.put( "FROM_SCOPE_OPER_KEY", fromScopeOperationKey );
            fromInfoMap.put( "FROM_OPER_NO", fromMainOrStdOperOperNo );
            toInfoMap.put( "TO_SCOPE_OPER_KEY", toScopeOperationKey );
            toInfoMap.put( "TO_OPER_NO", toMainOrStdOperOperNo );
            
            if(fromOperationNumberMap !=null)
            {
	        	String frmOper = (String) fromOperationNumberMap.get("DUMMY_COL");
	        	
	        	if(isNotEmpty(frmOper))
	        	{
	        		fromOperationNumberExists = true;
	            }
	        }
        	
        	if(toOperationNumberMap != null)
        	{
	            String toOper = (String) toOperationNumberMap.get("DUMMY_COL");
	        	
	        	if(isNotEmpty(toOper))
	        	{
	        		toOperationNumberExists = true;
	           	}
	        }

            returnMap = new ListOrderedMap();
            changeIndicator = EMPTY;
            
            if (fromOperationNumberExists && toOperationNumberExists)
            {
                Map fromOperationStepInformation = null;
                Map toOperationStepInformation = null;

                if (equalsIgnoreCase(fromType, "PLAN")
                        && equalsIgnoreCase(fromType, toType))
                {
                    // Selects the Operation Step Information
                    fromOperationStepInformation = compareToolDao.selectOperationStepInformation(fromMainOrStdOperOperNo, // GE-1668
                                                                                                 fromPlanId,
                                                                                                 fromPlanVersion,
                                                                                                 fromPlanRevision,
                                                                                                 fromPlanAlterations,
                                                                                                 ELLIPSIS);

                    // Selects the Operation Step Information
                    toOperationStepInformation = compareToolDao.selectOperationStepInformation(toMainOrStdOperOperNo, //GE-1668
                                                                                               toPlanId,
                                                                                               toPlanVersion,
                                                                                               toPlanRevision,
                                                                                               toPlanAlterations,
                                                                                               ELLIPSIS);
                }
                else if (equalsIgnoreCase(fromType, "ORDER")
                        && equalsIgnoreCase(fromType, toType))
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Step Information
                        fromOperationStepInformation = compareToolDao.selectOperationStepInformation(operationNumber,
                                                                                                     fromOrderId,
                                                                                                     NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        // FND-25023
                        // Selects the Operation Step Information In Alteration
                        fromOperationStepInformation = compareToolDao.selectOperationStepInformationAltLog(operationNumber,
                                                                                                           fromOrderId,
                                                                                                           NumberUtils.INTEGER_MINUS_ONE,
                                                                                                           fromAltId);
                    }

                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Step Information
                        toOperationStepInformation = compareToolDao.selectOperationStepInformation(operationNumber,
                                                                                                   toOrderId,
                                                                                                   NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Step Information In Alteration
                        toOperationStepInformation = compareToolDao.selectOperationStepInformationAltLog(operationNumber,
                                                                                                         toOrderId,
                                                                                                         NumberUtils.INTEGER_MINUS_ONE,
                                                                                                         toAltId);
                    }
                    
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN")
                            && equalsIgnoreCase(toType, "ORDER"))
                    {
                        // Selects the Operation Step Information
                        fromOperationStepInformation = compareToolDao.selectOperationStepInformation(fromMainOrStdOperOperNo, // GE-1668
                                                                                                     fromPlanId,
                                                                                                     fromPlanVersion,
                                                                                                     fromPlanRevision,
                                                                                                     fromPlanAlterations,
                                                                                                     ELLIPSIS);

                        // Selects the Operation Step Information
                        toOperationStepInformation = compareToolDao.selectOperationStepInformation(operationNumber,
                                                                                                   toOrderId,
                                                                                                   NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else if (equalsIgnoreCase(fromType, "ORDER")
                            && equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Operation Step Information
                        fromOperationStepInformation = compareToolDao.selectOperationStepInformation(operationNumber,
                                                                                                     fromOrderId,
                                                                                                     NumberUtils.INTEGER_MINUS_ONE);

                        // Selects the Operation Step Information
                        toOperationStepInformation = compareToolDao.selectOperationStepInformation(toMainOrStdOperOperNo, //GE-1668
                                                                                                   toPlanId,
                                                                                                   toPlanVersion,
                                                                                                   toPlanRevision,
                                                                                                   toPlanAlterations,
                                                                                                   ELLIPSIS);
                    }
                }

                Number fromStepKey = (Number) fromOperationStepInformation.get("STEP_KEY");
                Number fromStepUpdateNumber = (Number) fromOperationStepInformation.get("STEP_UPDT_NO");

                Number toStepKey = (Number) toOperationStepInformation.get("STEP_KEY");
                Number toStepUpdateNumber = (Number) toOperationStepInformation.get("STEP_UPDT_NO");
                
                // Compares the Operation in both the Plans
                boolean compareOperation = compareOperation(operationNumber,
                                                            fromPlanId,
                                                            fromPlanVersion,
                                                            fromPlanRevision,
                                                            fromPlanAlterations,
                                                            fromOperationKey,
                                                            fromOperationUpdateNumber,
                                                            fromStepKey,
                                                            fromStepUpdateNumber,
                                                            toPlanId,
                                                            toPlanVersion,
                                                            toPlanRevision,
                                                            toPlanAlterations,
                                                            toOperationKey,
                                                            toOperationUpdateNumber,
                                                            toStepKey,
                                                            toStepUpdateNumber,
                                                            fromOrderId,
                                                            toOrderId,
                                                            fromType,
                                                            toType, 
                                                            booleanCompareStateMap,
                                                            fromAltId, // FND-25023
                                                            toAltId, 
                                                            fromStdOperMap, //GE-1668 
                                                            toStdOperMap, 
                                                            calledFromChgAck);

                if (compareOperation)
                {
                    changeIndicator = "CHECK_MARK";
                }
                else
                {
                    changeIndicator = "PENCIL_MARK";
                }

                alterationNumber = EMPTY;
                changeAuthObjectId = EMPTY;
                image = EMPTY;

                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    Map operInfo = compareToolDao.selectOperationHeaderData(fromMainOrStdOperOperNo, // GE-1668
                                                                            fromPlanId,
                                                                            fromPlanVersion,
                                                                            fromPlanRevision,
                                                                            fromPlanAlterations);
                    
                    operInfo.put("OPER_NO", operationNumber); // GE-2317 Put back the operation no of original plan. 
                    
                    changeAuthObjectId = (String) operInfo.get("OBJECT_ID");
                    
                    // FND-14904 Meena
                    if(isNotEmpty(changeAuthObjectId))
                    {
                    	image = "Image";
                    }
                }

                params = "FROM_OPER_NO=" + operationNumber
                        + ";FROM_OPER_KEY=" + fromOperationKey
                        + ";FROM_OPER_UPDT_NO=" + fromOperationUpdateNumber
                        + ";TO_OPER_NO=" + operationNumber + ";TO_OPER_KEY="
                        + toOperationKey + ";TO_OPER_UPDT_NO="
                        + toOperationUpdateNumber + ";FROM_STEP_NO="
                        + ELLIPSIS + ";FROM_STEP_KEY="
                        + fromStepKey + ";FROM_STEP_UPDT_NO="
                        + fromStepUpdateNumber + ";TO_STEP_NO="
                        + ELLIPSIS + ";TO_STEP_KEY="
                        + toStepKey + ";TO_STEP_UPDT_NO=" + toStepUpdateNumber
                        + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                        + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                        + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperationKey    // GE-2317
                        + ";TO_SCOPE_OPER_KEY=" + toScopeOperationKey;        // GE-2317

                returnMap.put("LEVEL", 1);
                returnMap.put("INSTRUCTION_NAME", "OPERATION "
                        + operationNumber);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", alterationNumber);
                returnMap.put("OBJECT_ID", changeAuthObjectId);
                returnMap.put("IMAGE", image);
                returnMap.put("PARENT_KEY", null);
                returnMap.put("CHILD_KEY", "OPERATION " + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                // Start Operation Header
                boolean compareOperationHeader = (booleanCompareStateMap.get("compareOperationHeader") == null) ? 
                									false : ((Boolean)booleanCompareStateMap.get("compareOperationHeader")).booleanValue();

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 2);
                returnMap.put("INSTRUCTION_NAME", "HEADER DATA");
                changeIndicator = EMPTY;

                if (compareOperationHeader)
                {
                    changeIndicator = "CHECK_MARK";
                }
                else
                {
                    changeIndicator = "PENCIL_MARK";
                }
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
                returnMap.put("CHILD_KEY", "HEADER DATA " + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                // End Operation Header

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                // Start Operation Header Meta Data
                boolean compareOperationHeaderData = (booleanCompareStateMap.get("compareOperationHeaderData") == null) ? 
                										false : ((Boolean)booleanCompareStateMap.get("compareOperationHeaderData")).booleanValue();

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 3);
                returnMap.put("INSTRUCTION_NAME", "HEADER META DATA");
                changeIndicator = EMPTY;

                if (compareOperationHeaderData)
                {
                    changeIndicator = "CHECK_MARK";
                }
                else
                {
                    changeIndicator = "PENCIL_MARK";
                }
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "HEADER DATA " + operationNumber);
                returnMap.put("CHILD_KEY", "HEADER META DATA "
                        + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                // End Operation Header Meta Data

                // Start Operation Process

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                
                // GE-1904
                //Compare Process type
                compareProcessType(returnList, 
                				   params, 
                				   booleanCompareStateMap,
                				   operationNumber);

                // End Operation Process

                // Start Operation Skills

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                
                // GE-1815. Compare Standards and Skills fields of operation
				// GE-1904
                // Compare Operation Standards and Skills
//                boolean compareOperationStandards;
//				boolean compareOperationSkill;
				compareOperationStandardsAndSkills(returnList, 
												   params,
												   booleanCompareStateMap, 
												   operationNumber);
				
				// GE-1904
				// Compare Operation Standards
                compareOperationStandards(returnList, 
                						  params,
                						  booleanCompareStateMap, 
                						  operationNumber);				 
                
                // GE-1904
                // Compare Operation Skills
                compareOperationSkills(returnList, 
                					   params,
                					   booleanCompareStateMap, 
                					   operationNumber);

                // End Operation Skill

                // Start Operation Precedence

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                // GE-1904
                // Compare Operation Precedence
                compareOperationPrecedence(returnList, 
                						   params,
                						   booleanCompareStateMap, 
                						   operationNumber);

                // End Operation Precedence

                // Start Operation Header Blocks

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                // Compares the Operation Header Blocks
                boolean compareOperationHeaderBlocks = (booleanCompareStateMap.get("compareOperationHeaderBlock") == null) ? 
                										false : ((Boolean)booleanCompareStateMap.get("compareOperationHeaderBlock")).booleanValue();

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 2);
                returnMap.put("INSTRUCTION_NAME", "OPERATION HEADER BLOCKS");
                changeIndicator = EMPTY;

                if (compareOperationHeaderBlocks)
                {
                    changeIndicator = "CHECK_MARK";
                }
                else
                {
                    changeIndicator = "PENCIL_MARK";
                }
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
                returnMap.put("CHILD_KEY", "OPERATION HEADER BLOCKS "
                        + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                String textType = EMPTY;
                String blockType = EMPTY;

                if (equalsIgnoreCase(fromType, "ORDER")
                        || equalsIgnoreCase(toType, "ORDER"))
                {
                    textType = "HEADER_DISP";
                    blockType = "DISPOSITION";

                    // Operation Header Disposition Block
                    List dispositionBlock = generateDispositionBlock(fromOrderId,
                                                                     fromOperationKey,
                                                                     fromStepKey,
                                                                     toOrderId,
                                                                     toOperationKey,
                                                                     toStepKey,
                                                                     textType,
                                                                     operationNumber,
                                                                     ELLIPSIS,
                                                                     HEADER,
                                                                     blockType,
                                                                     fromAltId,
                                                                     toAltId);

                    if (dispositionBlock.size() > 0)
                    {
                        returnList.addAll(dispositionBlock);
                    }
                }

                textType = "HEADER_PLANNING";
                blockType = "PLANNING";

                // Operation Header Planning Block
                List planningBlock = generateBlockHierarchy(operationNumber,
                                                            ELLIPSIS,
                                                            fromPlanId,
                                                            fromOperationKey,
                                                            fromOperationUpdateNumber,
                                                            fromStepKey,
                                                            fromStepUpdateNumber,
                                                            toPlanId,
                                                            toOperationKey,
                                                            toOperationUpdateNumber,
                                                            toStepKey,
                                                            toStepUpdateNumber,
                                                            textType,
                                                            blockType,
                                                            HEADER,
                                                            fromOrderId,
                                                            toOrderId,
                                                            fromType,
                                                            toType,
                                                            fromAltId,
                                                            toAltId,
                                                            fromInfoMap,  // GE-2317
                                                            toInfoMap);  // GE-2317
                if (planningBlock.size() > 0)
                {
                    returnList.addAll(planningBlock);
                }

                textType = "HEADER_QA";
                blockType = "QA";

                // Operation Header QA Block
                List QABlock = generateBlockHierarchy(operationNumber,
                                                      ELLIPSIS,
                                                      fromPlanId,
                                                      fromOperationKey,
                                                      fromOperationUpdateNumber,
                                                      fromStepKey,
                                                      fromStepUpdateNumber,
                                                      toPlanId,
                                                      toOperationKey,
                                                      toOperationUpdateNumber,
                                                      toStepKey,
                                                      toStepUpdateNumber,
                                                      textType,
                                                      blockType,
                                                      HEADER,
                                                      fromOrderId,
                                                      toOrderId,
                                                      fromType,
                                                      toType,
                                                      fromAltId,
                                                      toAltId,
                                                      fromInfoMap,  // GE-2317
                                                      toInfoMap);  // GE-2317
                if (QABlock.size() > 0)
                {
                    returnList.addAll(QABlock);
                }

                // Start Steps

                List distinctStepsList = new ArrayList();

                if (equalsIgnoreCase(fromType, "PLAN")
                        && equalsIgnoreCase(fromType, toType))
                {
                    // Selects the Steps for both From and To Plans
                    distinctStepsList = compareToolDao.selectDistinctStepNumber(fromPlanId,
                                                                                fromPlanVersion,
                                                                                fromPlanRevision,
                                                                                fromPlanAlterations,
                                                                                fromOperationKey,
                                                                                fromOperationUpdateNumber,
                                                                                toPlanId,
                                                                                toPlanVersion,
                                                                                toPlanRevision,
                                                                                toPlanAlterations,
                                                                                toOperationKey,
                                                                                toOperationUpdateNumber,
                                                                                ELLIPSIS);
                }
                else if (equalsIgnoreCase(fromType, "ORDER")
                        && equalsIgnoreCase(fromType, toType))
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Steps for both From and To Order
                        distinctStepsList = compareToolDao.selectDistinctStepNumber(fromOrderId,
                                                                                    fromOperationKey,
                                                                                    toOrderId,
                                                                                    toOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        //FND-25023 Selects the Steps in Alteration for both From and To Order
                        distinctStepsList = compareToolDao.selectDistinctStepNumberAltLog(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          toOrderId,
                                                                                          toOperationKey,
                                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                                          fromAltId,
                                                                                          toAltId);
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN")
                            && equalsIgnoreCase(toType, "ORDER"))
                    {
                        // Selects the Steps for both From and To Plan and Order
                        distinctStepsList = compareToolDao.selectDistinctStepNumber(fromPlanId,
                                                                                    fromPlanVersion,
                                                                                    fromPlanRevision,
                                                                                    fromPlanAlterations,
                                                                                    fromOperationKey,
                                                                                    fromOperationUpdateNumber,
                                                                                    ELLIPSIS,
                                                                                    toOrderId,
                                                                                    toOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else if (equalsIgnoreCase(fromType, "ORDER")
                            && equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Steps for both From and To Plan and Order
                        distinctStepsList = compareToolDao.selectDistinctStepNumber(toPlanId,
                                                                                    toPlanVersion,
                                                                                    toPlanRevision,
                                                                                    toPlanAlterations,
                                                                                    toOperationKey,
                                                                                    toOperationUpdateNumber,
                                                                                    ELLIPSIS,
                                                                                    fromOrderId,
                                                                                    fromOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE);
                    }
                }

                Iterator stepIterator = distinctStepsList.listIterator();

                while (stepIterator.hasNext())
                {
                    Map stepMap = (Map) stepIterator.next();

                    String stepNumber = (String) stepMap.get("STEP_NO");

                    boolean fromStepExists = false;
                    boolean toStepExists = false;

                    if (equalsIgnoreCase(fromType, "PLAN")
                            && equalsIgnoreCase(fromType, toType))
                    {
                        // Checks whether Step exists in From Plan
                        fromStepExists = compareToolDao.selectStepNumberExists(fromPlanId,
                                                                               fromPlanVersion,
                                                                               fromPlanRevision,
                                                                               fromPlanAlterations,
                                                                               fromOperationKey,
                                                                               fromOperationUpdateNumber,
                                                                               stepNumber);

                        // Checks whether Step exists in To Plan
                        toStepExists = compareToolDao.selectStepNumberExists(toPlanId,
                                                                             toPlanVersion,
                                                                             toPlanRevision,
                                                                             toPlanAlterations,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             stepNumber);
                    }
                    else if (equalsIgnoreCase(fromType, "ORDER")
                            && equalsIgnoreCase(fromType, toType))
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Checks whether Step exists in From Order
                            fromStepExists = compareToolDao.selectStepNumberExists(fromOrderId,
                                                                                   fromOperationKey,
                                                                                   stepNumber);
                        }
                        else
                        {
                            //FND-25023 Checks whether Step exists in Alteration in From Order
                            fromStepExists = compareToolDao.selectStepNumberExistsAltLog(fromOrderId,
                                                                                         fromOperationKey,
                                                                                         stepNumber,
                                                                                         fromAltId);
                        }

                        if(isEmpty( toAltId ))
                        {
                            // Checks whether Step exists in TO Order
                            toStepExists = compareToolDao.selectStepNumberExists(toOrderId,
                                                                                 toOperationKey,
                                                                                 stepNumber);
                        }
                        else
                        {
                            //FND-25023 Checks whether Step exists in Alteration in TO Order
                            toStepExists = compareToolDao.selectStepNumberExistsAltLog(toOrderId,
                                                                                       toOperationKey,
                                                                                       stepNumber,
                                                                                       toAltId);
                        }
                    }
                    else
                    {
                        if (equalsIgnoreCase(fromType, "PLAN")
                                && equalsIgnoreCase(toType, "ORDER"))
                        {
                            // Checks whether Step exists in From Plan
                            fromStepExists = compareToolDao.selectStepNumberExists(fromPlanId,
                                                                                   fromPlanVersion,
                                                                                   fromPlanRevision,
                                                                                   fromPlanAlterations,
                                                                                   fromOperationKey,
                                                                                   fromOperationUpdateNumber,
                                                                                   stepNumber);

                            // Checks whether Step exists in TO Order
                            toStepExists = compareToolDao.selectStepNumberExists(toOrderId,
                                                                                 toOperationKey,
                                                                                 stepNumber);
                        }
                        else if (equalsIgnoreCase(fromType, "ORDER")
                                && equalsIgnoreCase(toType, "PLAN"))
                        {
                            // Checks whether Step exists in From Order
                            fromStepExists = compareToolDao.selectStepNumberExists(fromOrderId,
                                                                                   fromOperationKey,
                                                                                   stepNumber);

                            // Checks whether Step exists in To Plan
                            toStepExists = compareToolDao.selectStepNumberExists(toPlanId,
                                                                                 toPlanVersion,
                                                                                 toPlanRevision,
                                                                                 toPlanAlterations,
                                                                                 toOperationKey,
                                                                                 toOperationUpdateNumber,
                                                                                 stepNumber);
                        }
                    }

                    // If Step exists in both the plans
                    // GE-1904
                    // Compare Steps Info, QA Block, Display Block
                    compareStep(fromPlanId,
                                fromPlanVersion,
                                fromPlanRevision,
                                fromPlanAlterations,
                                toPlanId,
                                toPlanVersion,
                                toPlanRevision,
                                toPlanAlterations,
                    			fromOrderId,
                    			toOrderId, 
                    			fromAltId, 
                    			toAltId, 
                    			fromType, 
                    			toType,
                    			returnList, 
                    			changeIndicator,
                    			booleanCompareStateMap, 
                    			operationNumber,
                    			fromOperationKey, 
                    			fromStepKey,
                    			fromOperationUpdateNumber, 
                    			toOperationKey,
                    			toStepKey, 
                    			toOperationUpdateNumber, 
                    			stepNumber,
                    			fromStepExists, 
                    			toStepExists,
                    			fromInfoMap,  // GE-2317
                    			toInfoMap);   // GE-2317
                }

                // End Steps

                // End Operation Header Blocks

                // Start Operation Footer Block

                fromOperationKey = (Number) fromOperationStepInformation.get("OPER_KEY");
                fromStepKey = (Number) fromOperationStepInformation.get("STEP_KEY");
                fromOperationUpdateNumber = (Number) fromOperationStepInformation.get("OPER_UPDT_NO");
                fromStepUpdateNumber = (Number) fromOperationStepInformation.get("STEP_UPDT_NO");
                toOperationKey = (Number) toOperationStepInformation.get("OPER_KEY");
                toStepKey = (Number) toOperationStepInformation.get("STEP_KEY");
                toOperationUpdateNumber = (Number) toOperationStepInformation.get("OPER_UPDT_NO");
                toStepUpdateNumber = (Number) toOperationStepInformation.get("STEP_UPDT_NO");

                // 05/07/2011 Suresh FND-15735
                // Performance: Compare Plan/Order
                // Compares the Operation Footer Blocks
                // GE-1904
                compareOperationFooterBlocks(fromPlanId,
                							 toPlanId, 
                							 fromOrderId,
                							 toOrderId, 
                							 fromAltId, 
                							 toAltId, 
                							 fromType, 
                							 toType,
                							 returnList, 
                							 booleanCompareStateMap, 
                							 operationNumber,
                							 fromOperationKey, 
                							 fromStepKey,
                							 fromOperationUpdateNumber, 
                							 fromStepUpdateNumber,
                							 toOperationKey, 
                							 toStepKey, 
                							 toOperationUpdateNumber,
                							 toStepUpdateNumber,
                							 fromInfoMap,
                							 toInfoMap);

                // End Operation Footer Block
            }
            else
            {
                params = EMPTY;
                String planId = EMPTY;
                Number planVersion = null;
                Number planRevision = null;
                Number planAlterations = null;
                String operNoToPass = null;
                image = EMPTY;
                String orderId = EMPTY;
                String altId = EMPTY; 

                returnMap = new ListOrderedMap();
                if (fromOperationNumberExists && !toOperationNumberExists)
                {
                    alterationNumber = EMPTY;
                    changeAuthObjectId = EMPTY;

                    changeIndicator = "MINUS_MARK";
                    planId = fromPlanId;
                    planVersion = fromPlanVersion;
                    planRevision = fromPlanRevision;
                    planAlterations = fromPlanAlterations;
                    orderId = fromOrderId;
                    altId = fromAltId;

                    if (isEmpty(orderId))
                    {
                        operNoToPass = (!fromStdOperMap.isEmpty()) ? fromMainOrStdOperOperNo : operationNumber; // GE-1668
                        Map operInfo = compareToolDao.selectOperationHeaderData(operNoToPass,
                                                                                fromPlanId,
                                                                                fromPlanVersion,
                                                                                fromPlanRevision,
                                                                                fromPlanAlterations);
                        if(operInfo != null)
                        {
                            operInfo.put( "OPER_NO", operationNumber );
                        }

                        changeAuthObjectId = (String) operInfo.get("OBJECT_ID");
                        
                        // FND-14904 Meena
                        if(isNotEmpty(changeAuthObjectId))
                        {
                        	image = "Image";
                        }
                    }
                }
                else if (!fromOperationNumberExists && toOperationNumberExists)
                {
                    changeIndicator = "PLUS_MARK";
                    planId = toPlanId;
                    planVersion = toPlanVersion;
                    planRevision = toPlanRevision;
                    planAlterations = toPlanAlterations;
                    operNoToPass = (!toStdOperMap.isEmpty()) ? toMainOrStdOperOperNo : operationNumber; // GE-1668
                    orderId = toOrderId;
                    altId = toAltId;
                }

                Map operationStepInformation = null;

                if (isEmpty(orderId))
                {
                    // Selects the Operation Step Information
                    operationStepInformation = compareToolDao.selectOperationStepInformation(operNoToPass, //GE-1668
                                                                                             planId,
                                                                                             planVersion,
                                                                                             planRevision,
                                                                                             planAlterations,
                                                                                             ELLIPSIS);
                }
                else
                {
                    if(isEmpty( altId ))
                    {
                        // Selects the Operation Step Information
                        operationStepInformation = compareToolDao.selectOperationStepInformation(operationNumber,
                                                                                                 orderId,
                                                                                                 NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Step Information in Alteration
                        operationStepInformation = compareToolDao.selectOperationStepInformationAltLog(operationNumber,
                                                                                                       orderId,
                                                                                                       NumberUtils.INTEGER_MINUS_ONE,
                                                                                                       altId);
                    }
                }

                Number operationKey = (Number) operationStepInformation.get("OPER_KEY");
                Number stepKey = (Number) operationStepInformation.get("STEP_KEY");
                Number operationUpdateNumber = (Number) operationStepInformation.get("OPER_UPDT_NO");
                Number stepUpdateNumber = (Number) operationStepInformation.get("STEP_UPDT_NO");

                if (equalsIgnoreCase(changeIndicator, "MINUS_MARK"))
                {
                    params = "FROM_OPER_NO=" + operationNumber
                            + ";FROM_OPER_KEY=" + operationKey
                            + ";FROM_OPER_UPDT_NO=" + operationUpdateNumber
                            + ";TO_OPER_NO=" + ";TO_OPER_KEY="
                            + ";TO_OPER_UPDT_NO=" + ";FROM_STEP_NO="
                            + ELLIPSIS + ";FROM_STEP_KEY="
                            + stepKey + ";FROM_STEP_UPDT_NO="
                            + stepUpdateNumber + ";TO_STEP_NO="
                            + ";TO_STEP_KEY=" + ";TO_STEP_UPDT_NO="
                            + ";TEXT_TYPE="
                            + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                            + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                            + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperationKey    // GE-2317
                            + ";TO_SCOPE_OPER_KEY=";       // GE-2317
                }
                else
                {
                    params = "FROM_OPER_NO=" + ";FROM_OPER_KEY="
                            + ";FROM_OPER_UPDT_NO=" + ";TO_OPER_NO="
                            + operationNumber + ";TO_OPER_KEY=" + operationKey
                            + ";TO_OPER_UPDT_NO=" + operationUpdateNumber
                            + ";FROM_STEP_NO=" + ";FROM_STEP_KEY="
                            + ";FROM_STEP_UPDT_NO=" + ";TO_STEP_NO="
                            + ELLIPSIS + ";TO_STEP_KEY="
                            + stepKey + ";TO_STEP_UPDT_NO=" + stepUpdateNumber
                            + ";TEXT_TYPE="
                            + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                            + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                            + ";FROM_SCOPE_OPER_KEY="    // GE-2317
                            + ";TO_SCOPE_OPER_KEY=" + toScopeOperationKey;       // GE-2317
                }

                returnMap.put("LEVEL", 1);
                returnMap.put("INSTRUCTION_NAME", "OPERATION "
                        + operationNumber);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", alterationNumber);
                returnMap.put("OBJECT_ID", changeAuthObjectId);
                returnMap.put("IMAGE", image);
                returnMap.put("PARENT_KEY", null);
                returnMap.put("CHILD_KEY", "OPERATION " + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 2);
                returnMap.put("INSTRUCTION_NAME", "HEADER DATA");
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
                returnMap.put("CHILD_KEY", "HEADER DATA " + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 3);
                returnMap.put("INSTRUCTION_NAME", "HEADER META DATA");
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "HEADER DATA " + operationNumber);
                returnMap.put("CHILD_KEY", "HEADER META DATA "
                        + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                List operationProcess = new ArrayList();

                if (isEmpty(orderId))
                {
                    operationProcess = compareToolDao.selectOperationProcess(operNoToPass, //GE-1668
                                                                             planId,
                                                                             planVersion,
                                                                             planRevision,
                                                                             planAlterations,
                                                                             null,
                                                                             null);
                }
                else
                {
                    if(isEmpty( altId ))
                    {
                        operationProcess = compareToolDao.selectOperationProcess(operationNumber,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 orderId,
                                                                                 NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        // FND-25023
                        operationProcess = compareToolDao.selectOperationProcessAltLog(operationNumber,
                                                                                       orderId,
                                                                                       NumberUtils.INTEGER_MINUS_ONE,
                                                                                       altId);
                    }
                }

                if (operationProcess.size() > 0)
                {
                    returnMap = new ListOrderedMap();
                    returnMap.put("LEVEL", 3);
                    returnMap.put("INSTRUCTION_NAME", "PROCESS TYPE");
                    returnMap.put("CHANGE_INDICATOR", changeIndicator);
                    returnMap.put("ALT_NO", null);
                    returnMap.put("OBJECT_ID", null);
                    returnMap.put("IMAGE", null);
                    returnMap.put("PARENT_KEY", "HEADER DATA "
                            + operationNumber);
                    returnMap.put("CHILD_KEY", "PROCESS TYPE "
                            + operationNumber);
                    returnMap.put("PARAMS", params);

                    returnList.add(returnMap);
                }
                
                // GE-1904
                changeIndicator = compareOperationStandardsAndSkillsForSingleOpeartion(returnList, 
                																	   changeIndicator, 
                																	   params, 
                																	   operationNumber,
                																	   planId, 
                																	   planVersion, 
                																	   planRevision, 
                																	   planAlterations,
                																	   orderId, 
                																	   altId, 
                																	   stepKey, 
                																	   operNoToPass); //GE-1668

                List operationPrecedence = new ArrayList();

                if (isEmpty(orderId))
                {
                    operationPrecedence = compareToolDao.selectOperationPrecedence(operNoToPass, //GE-1668
                                                                                   planId,
                                                                                   planVersion,
                                                                                   planRevision,
                                                                                   planAlterations,
                                                                                   null,
                                                                                   null);
                }
                else
                {
                    if(isEmpty( altId ))
                    {
                        operationPrecedence = compareToolDao.selectOperationPrecedence(operationNumber,
                                                                                       null,
                                                                                       null,
                                                                                       null,
                                                                                       null,
                                                                                       orderId,
                                                                                       NumberUtils.INTEGER_MINUS_ONE);
                    }
                    else
                    {
                        // FND-25023
                        operationPrecedence = compareToolDao.selectOperationPrecedenceAltLog(operationNumber,
                                                                                             orderId,
                                                                                             NumberUtils.INTEGER_MINUS_ONE,
                                                                                             altId);
                    }
                }

                if (operationPrecedence.size() > 0)
                {
                    returnMap = new ListOrderedMap();
                    returnMap.put("LEVEL", 3);
                    returnMap.put("INSTRUCTION_NAME", "OPERATION PRECEDENCE");
                    returnMap.put("CHANGE_INDICATOR", changeIndicator);
                    returnMap.put("ALT_NO", null);
                    returnMap.put("OBJECT_ID", null);
                    returnMap.put("IMAGE", null);
                    returnMap.put("PARENT_KEY", "HEADER DATA "
                            + operationNumber);
                    returnMap.put("CHILD_KEY", "OPERATION PRECEDENCE "
                            + operationNumber);
                    returnMap.put("PARAMS", params);

                    returnList.add(returnMap);
                }

                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", 2);
                returnMap.put("INSTRUCTION_NAME", "OPERATION HEADER BLOCKS");
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
                returnMap.put("CHILD_KEY", "OPERATION HEADER BLOCKS "
                        + operationNumber);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);

                String textType = EMPTY;
                String blockType = EMPTY;

                if (equalsIgnoreCase(fromType, "ORDER")
                        || equalsIgnoreCase(toType, "ORDER"))
                {
                    textType = "HEADER_DISP";
                    blockType = "DISPOSITION";

                    // Operation Header Disposition Block
                    List dispositionBlock = generateDispositionBlock(orderId,
                                                                     operationKey,
                                                                     stepKey,
                                                                     null,
                                                                     null,
                                                                     null,
                                                                     textType,
                                                                     operationNumber,
                                                                     ELLIPSIS,
                                                                     HEADER,
                                                                     blockType,
                                                                     fromAltId,
                                                                     toAltId);

                    if (dispositionBlock.size() > 0)
                    {
                        returnList.addAll(dispositionBlock);
                    }
                }

                textType = "HEADER_PLANNING";
                blockType = "PLANNING";

                // GE-1668 NO change as Operation No not passed to dao for fetching
                List planningBlock = generateSingleBlock(operationNumber,
                                                         ELLIPSIS,
                                                         planId,
                                                         operationKey,
                                                         operationUpdateNumber,
                                                         stepKey,
                                                         stepUpdateNumber,
                                                         textType,
                                                         blockType,
                                                         changeIndicator,
                                                         HEADER,
                                                         orderId,
                                                         altId, // FND-25023
                                                         fromInfoMap,
                                                         toInfoMap); 

                if (planningBlock.size() > 0)
                {
                    returnList.addAll(planningBlock);
                }

                textType = "HEADER_QA";
                blockType = "QA";

                // GE-1668 NO change as Operation No not passed to dao for fetching
                List QABlock = generateSingleBlock(operationNumber,
                                                   ELLIPSIS,
                                                   planId,
                                                   operationKey,
                                                   operationUpdateNumber,
                                                   stepKey,
                                                   stepUpdateNumber,
                                                   textType,
                                                   blockType,
                                                   changeIndicator,
                                                   HEADER,
                                                   orderId,
                                                   altId,  // FND-25023
                                                   fromInfoMap,  // GE-2317
                                                   toInfoMap);   // GE-2317

                if (QABlock.size() > 0)
                {
                    returnList.addAll(QABlock);
                }

                // Start Steps
                // GE-1904
                compareOperationStepForSingleOpeartion(fromAltId, 
                									   toAltId,
                									   fromType, 
                									   toType, 
                									   returnList, 
                									   changeIndicator,
                									   operationNumber,
                									   planId, planVersion, 
                									   planRevision,
                									   planAlterations,
                									   orderId,
                									   altId,
                									   operationKey,
                									   operationUpdateNumber,
                									   fromInfoMap,  // GE-2317
                									   toInfoMap);  // GE-2317

                // End Steps

                // Start Operation Footer Block
                // GE-1904
                // GE-1668 NO change as Operation No not passed to dao for fetching 
                compareOperationFooterBlockForSingleOpeartion(fromAltId,
                											  toAltId, 
                											  fromType, 
                											  toType, 
                											  returnList, 
                											  changeIndicator,
                											  operationNumber, 
                											  planId, orderId, 
                											  altId,
                											  operationStepInformation, 
                											  operationKey,
                											  operationUpdateNumber,
                											  fromInfoMap,  // GE-2317
                											  toInfoMap);  // GE-2317

                // End Operation Footer Block
            }
            
            // 05/07/2011 Suresh FND-15735
            // Clear booleanCompareStateMap object when operation loop completed.
            booleanCompareStateMap.clear();
        }
        // End Operation

        // GE-1668 Reset main Plan Variables to Origin main Plan values.
        fromPlanId  = origFromPlanId ;
        fromPlanVersion = origFromPlanVersion ;
        fromPlanRevision = origFromPlanRevision ;
        fromPlanAlterations = origFromPlanAlterations ;
        toPlanId = origToPlanId ;
        toPlanVersion = origToPlanVersion ;
        toPlanRevision = origToPlanRevision ;
        toPlanAlterations = origToPlanAlterations ;
        
        try
        {
            // GE-1668 From and To Variables should be of main Plan in place of Standard Operation.
            List OFDHierarchy = generateOFDHierarchy(fromPlanId,
                                                     fromPlanVersion,
                                                     fromPlanRevision,
                                                     fromPlanAlterations,
                                                     toPlanId,
                                                     toPlanVersion,
                                                     toPlanRevision,
                                                     toPlanAlterations,
                                                     fromOrderId,
                                                     toOrderId,
                                                     fromType,
                                                     toType,
                                                     fromAltId, // FND-25023
                                                     toAltId);

            if (OFDHierarchy.size() > 0)
            {
                returnList.addAll(OFDHierarchy);
            }
        }
        catch (IncorrectResultSizeDataAccessException e)
        {

        }
        

        return returnList;
    }

    // GE-1904
	protected void compareOperationFooterBlockForSingleOpeartion(String fromAltId, 
															   String toAltId, 
															   String fromType, 
															   String toType,
															   List returnList, 
															   String changeIndicator, 
															   String operationNumber,
															   String planId, 
															   String orderId, 
															   String altId,
															   Map operationStepInformation, 
															   Number operationKey,
															   Number operationUpdateNumber,
															   Map fromInfoMap,
															   Map toInfoMap) 
	{
		Map returnMap;
		String params;
		Number stepKey;
		Number stepUpdateNumber;
		String textType;
		String blockType;
		stepKey = (Number) operationStepInformation.get("STEP_KEY");
		stepUpdateNumber = (Number) operationStepInformation.get("STEP_UPDT_NO");
		
		// GE-2317
        Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
        Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
        String fromMainOrStdOperNo = operationNumber;
        String toMainOrStdOperNo = operationNumber;
        if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
            fromMainOrStdOperNo = (String)fromInfoMap.get( "FROM_OPER_NO" );
        }
        if(toInfoMap != null && !toInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
            toMainOrStdOperNo = (String)toInfoMap.get( "TO_OPER_NO" );
        }

		if (equalsIgnoreCase(changeIndicator, "MINUS_MARK"))
		{
		    params = "FROM_OPER_NO=" + operationNumber
		            + ";FROM_OPER_KEY=" + operationKey
		            + ";FROM_OPER_UPDT_NO=" + operationUpdateNumber
		            + ";TO_OPER_NO=" + ";TO_OPER_KEY="
		            + ";TO_OPER_UPDT_NO=" + ";FROM_STEP_NO="
		            + ELLIPSIS + ";FROM_STEP_KEY="
		            + stepKey + ";FROM_STEP_UPDT_NO="
		            + stepUpdateNumber + ";TO_STEP_NO="
		            + ";TO_STEP_KEY=" + ";TO_STEP_UPDT_NO="
		            + ";TEXT_TYPE="
		            + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		            + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
		            + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey  // GE-2317
		            + ";TO_SCOPE_OPER_KEY=";     // GE-2317
		}
		else
		{
		    params = "FROM_OPER_NO=" + ";FROM_OPER_KEY="
		            + ";FROM_OPER_UPDT_NO=" + ";TO_OPER_NO="
		            + operationNumber + ";TO_OPER_KEY=" + operationKey
		            + ";TO_OPER_UPDT_NO=" + operationUpdateNumber
		            + ";FROM_STEP_NO=" + ";FROM_STEP_KEY="
		            + ";FROM_STEP_UPDT_NO=" + ";TO_STEP_NO="
		            + ELLIPSIS + ";TO_STEP_KEY="
		            + stepKey + ";TO_STEP_UPDT_NO=" + stepUpdateNumber
		            + ";TEXT_TYPE="
		            + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		            + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                    + ";FROM_SCOPE_OPER_KEY="  // GE-2317
                    + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;     // GE-2317
		}

		returnMap = new ListOrderedMap();
		returnMap.put("LEVEL", 2);
		returnMap.put("INSTRUCTION_NAME", "OPERATION FOOTER BLOCKS");
		returnMap.put("CHANGE_INDICATOR", changeIndicator);
		returnMap.put("ALT_NO", null);
		returnMap.put("OBJECT_ID", null);
		returnMap.put("IMAGE", null);
		returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
		returnMap.put("CHILD_KEY", "OPERATION FOOTER BLOCKS "
		        + operationNumber);
		returnMap.put("PARAMS", params);

		returnList.add(returnMap);

		if (equalsIgnoreCase(fromType, "ORDER")
		        || equalsIgnoreCase(toType, "ORDER"))
		{
		    textType = "DISP";
		    blockType = "DISPOSITION";

		    // Operation Footer Disposition Block
		    List dispositionBlock = generateDispositionBlock(orderId,
		                                                     operationKey,
		                                                     stepKey,
		                                                     null,
		                                                     null,
		                                                     null,
		                                                     textType,
		                                                     operationNumber,
		                                                     ELLIPSIS,
		                                                     FOOTER,
		                                                     blockType,
		                                                     fromAltId,
		                                                     toAltId);

		    if (dispositionBlock.size() > 0)
		    {
		        returnList.addAll(dispositionBlock);
		    }
		}

		textType = "PLANNING";
		blockType = "PLANNING";

		List planningFooterBlock = generateSingleBlock(operationNumber,
		                                               ELLIPSIS,
		                                               planId,
		                                               operationKey,
		                                               operationUpdateNumber,
		                                               stepKey,
		                                               stepUpdateNumber,
		                                               textType,
		                                               blockType,
		                                               changeIndicator,
		                                               FOOTER,
		                                               orderId,
		                                               altId,  // FND-25023
		                                               fromInfoMap,  // GE-2317
		                                               toInfoMap);   // GE-2317

		if (planningFooterBlock.size() > 0)
		{
		    returnList.addAll(planningFooterBlock);
		}

		textType = "QA";
		blockType = "QA";

		List footerQABlock = generateSingleBlock(operationNumber,
		                                         ELLIPSIS,
		                                         planId,
		                                         operationKey,
		                                         operationUpdateNumber,
		                                         stepKey,
		                                         stepUpdateNumber,
		                                         textType,
		                                         blockType,
		                                         changeIndicator,
		                                         FOOTER,
		                                         orderId,
                                                 altId,  // FND-25023
                                                 fromInfoMap,  // GE-2317
                                                 toInfoMap);   // GE-2317

		if (footerQABlock.size() > 0)
		{
		    returnList.addAll(footerQABlock);
		}
	}

	// GE-1904
	protected void compareOperationStepForSingleOpeartion(String fromAltId,
														String toAltId, 
														String fromType, 
														String toType, 
														List returnList,
														String changeIndicator, 
														String operationNumber, 
														String planId,
														Number planVersion, 
														Number planRevision,
														Number planAlterations,
														String orderId, 
														String altId,
														Number operationKey,
														Number operationUpdateNumber,
														Map fromInfoMap, //  GE-2317
														Map toInfoMap)   //  GE-2317
	{
		Map returnMap;
		String params;
		Number stepKey;
		Number stepUpdateNumber;
		String textType;
		String blockType;
		List stepsList = new ArrayList();
		
		// GE-2317
        Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
        Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
        String fromMainOrStdOperNo = operationNumber;
        String toMainOrStdOperNo = operationNumber;
        if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
            fromMainOrStdOperNo = (String)fromInfoMap.get( "FROM_OPER_NO" );
        }
        if(toInfoMap != null && !toInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
            toMainOrStdOperNo = (String)toInfoMap.get( "TO_OPER_NO" );
        }

		if (isEmpty(orderId))
		{
		    // Selects the Steps for Plan
		    stepsList = compareToolDao.selectDistinctStepNumber(planId,
		                                                        planVersion,
		                                                        planRevision,
		                                                        planAlterations,
		                                                        operationKey,
		                                                        operationUpdateNumber,
		                                                        null,
		                                                        null,
		                                                        null,
		                                                        null,
		                                                        null,
		                                                        null,
		                                                        ELLIPSIS);
		}
		else
		{
		    if(isEmpty( altId ))
		    {
		        // Selects the Steps for Order
		        stepsList = compareToolDao.selectDistinctStepNumber(orderId,
		                                                            operationKey,
		                                                            null,
		                                                            null,
		                                                            NumberUtils.INTEGER_MINUS_ONE);
		    }
		    else
		    {
		        //FND-25023 Selects the Steps in Alteration for Order
		        stepsList = compareToolDao.selectDistinctStepNumberAltLog(orderId,
		                                                                  operationKey,
		                                                                  null,
		                                                                  null,
		                                                                  NumberUtils.INTEGER_MINUS_ONE,
		                                                                  fromAltId,
		                                                                  toAltId);
		    }
		}

		Iterator stepIterator = stepsList.listIterator();

		while (stepIterator.hasNext())
		{
		    Map stepMap = (Map) stepIterator.next();

		    String stepNumber = (String) stepMap.get("STEP_NO");

		    Map stepInfo = null;

		    if (isEmpty(orderId))
		    {
		        stepInfo = compareToolDao.selectStepInformation(planId,
		                                                        planVersion,
		                                                        planRevision,
		                                                        planAlterations,
		                                                        operationKey,
		                                                        operationUpdateNumber,
		                                                        stepNumber);
		    }
		    else
		    {
		        if(isEmpty( altId ))
		        {
		            stepInfo = compareToolDao.selectStepInformation(orderId,
		                                                            operationKey,
		                                                            stepNumber);
		        }
		        else
		        {
		            // FND-25023
		            stepInfo = compareToolDao.selectStepInformationAltLog(orderId,
		                                                                  operationKey,
		                                                                  stepNumber,
		                                                                  altId);
		        }
		    }

		    stepKey = (Number) stepInfo.get("STEP_KEY");
		    stepUpdateNumber = (Number) stepInfo.get("STEP_UPDT_NO");

		    if (equalsIgnoreCase(changeIndicator, "MINUS_MARK"))
		    {
		        params = "FROM_OPER_NO=" + fromMainOrStdOperNo  // GE-2317
		                + ";FROM_OPER_KEY=" + operationKey
		                + ";FROM_OPER_UPDT_NO=" + operationUpdateNumber
		                + ";TO_OPER_NO=" + ";TO_OPER_KEY="
		                + ";TO_OPER_UPDT_NO=" + ";FROM_STEP_NO="
		                + stepNumber + ";FROM_STEP_KEY=" + stepKey
		                + ";FROM_STEP_UPDT_NO=" + stepUpdateNumber
		                + ";TO_STEP_NO=" + ";TO_STEP_KEY="
		                + ";TO_STEP_UPDT_NO=" + ";TEXT_TYPE="
		                + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		                + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
		                + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey  // GE-2317
		                + ";TO_SCOPE_OPER_KEY=";     // GE-2317
		    }
		    else
		    {
		        params = "FROM_OPER_NO=" + ";FROM_OPER_KEY="
		                + ";FROM_OPER_UPDT_NO=" + ";TO_OPER_NO="
		                + toMainOrStdOperNo + ";TO_OPER_KEY="  // GE-2317
		                + operationKey + ";TO_OPER_UPDT_NO="
		                + operationUpdateNumber + ";FROM_STEP_NO="
		                + ";FROM_STEP_KEY=" + ";FROM_STEP_UPDT_NO="
		                + ";TO_STEP_NO=" + stepNumber + ";TO_STEP_KEY="
		                + stepKey + ";TO_STEP_UPDT_NO="
		                + stepUpdateNumber + ";TEXT_TYPE="
		                + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		                + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
		                + ";FROM_SCOPE_OPER_KEY="  // GE-2317
                        + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;     // GE-2317
		    }

		    returnMap = new ListOrderedMap();

		    returnMap.put("LEVEL",2);
		    returnMap.put("INSTRUCTION_NAME", "STEP " + stepNumber);
		    returnMap.put("CHANGE_INDICATOR", changeIndicator);
		    returnMap.put("ALT_NO", null);
		    returnMap.put("OBJECT_ID", null);
		    returnMap.put("IMAGE", null);
		    returnMap.put("PARENT_KEY", "OPERATION " + operationNumber); // FND-26100
		    returnMap.put("CHILD_KEY", "STEP " + operationNumber  + stepNumber);
		    returnMap.put("PARAMS", params);

		    returnList.add(returnMap);

		    if (equalsIgnoreCase(fromType, "ORDER")
		            || equalsIgnoreCase(toType, "ORDER"))
		    {
		        textType = "DISP";
		        blockType = "DISPOSITION";

		        // Operation Header Disposition Block
		        List dispositionBlock = generateDispositionBlock(orderId,
		                                                         operationKey,
		                                                         stepKey,
		                                                         null,
		                                                         null,
		                                                         null,
		                                                         textType,
		                                                         operationNumber,
		                                                         stepNumber,
		                                                         HEADER,
		                                                         blockType,
		                                                         fromAltId,
		                                                         toAltId);

		        if (dispositionBlock.size() > 0)
		        {
		            returnList.addAll(dispositionBlock);
		        }
		    }

		    textType = "PLANNING";
		    blockType = "PLANNING";

		    List stepPlanningBlock = generateSingleBlock(operationNumber,
		                                                 stepNumber,
		                                                 planId,
		                                                 operationKey,
		                                                 operationUpdateNumber,
		                                                 stepKey,
		                                                 stepUpdateNumber,
		                                                 textType,
		                                                 blockType,
		                                                 changeIndicator,
		                                                 HEADER,
		                                                 orderId,
		                                                 altId, // FND-25023
		                                                 fromInfoMap,  // GE-2317
		                                                 toInfoMap);   // GE-2317

		    if (stepPlanningBlock.size() > 0)
		    {
		        returnList.addAll(stepPlanningBlock);
		    }

		    textType = "QA";
		    blockType = "QA";

		    List stepQABlock = generateSingleBlock(operationNumber,
		                                           stepNumber,
		                                           planId,
		                                           operationKey,
		                                           operationUpdateNumber,
		                                           stepKey,
		                                           stepUpdateNumber,
		                                           textType,
		                                           blockType,
		                                           changeIndicator,
		                                           HEADER,
		                                           orderId,
                                                   altId, // FND-25023
                                                   fromInfoMap,  // GE-2317
                                                   toInfoMap);   // GE-2317

		    if (stepQABlock.size() > 0)
		    {
		        returnList.addAll(stepQABlock);
		    }
		}
	}

	// GE-1904
	protected String compareOperationStandardsAndSkillsForSingleOpeartion(List returnList , 
																		String changeIndicator , 
																		String params ,
																		String operationNumber , 
																		String planId , 
																		Number planVersion ,
																		Number planRevision , 
																		Number planAlterations , 
																		String orderId ,
																		String altId , 
																		Number stepKey , 
																		String operNoToPass ) //GE-1668
	{
		Map returnMap;
		List operationSkills = new ArrayList();
		List operationStandards =new ArrayList();			//GE-1815

		if (isEmpty(orderId))
		{
		    operationSkills = compareToolDao.selectOperationSkill(operNoToPass, //GE-1668
		                                                          planId,
		                                                          planVersion,
		                                                          planRevision,
		                                                          planAlterations,
		                                                          null,
		                                                          null);
		    
		    //GE-1815
		    operationStandards =compareToolDao.selectOperationStandards(operNoToPass, //GE-1668 
		    															planId, 
		    															planVersion, 
		    															planRevision, 
		    															planAlterations);
		}
		else
		{
		    if(isEmpty( altId ))
		    {
		        operationSkills = compareToolDao.selectOperationSkill(operationNumber,
		                                                              null,
		                                                              null,
		                                                              null,
		                                                              null,
		                                                              orderId,
		                                                              NumberUtils.INTEGER_MINUS_ONE);
		        
		        //GE-1815
		        operationStandards =compareToolDao.selectOperationStandards(orderId, 
		        															operationNumber, 
		        															stepKey);
		        
		    }
		    else
		    {
		        // FND-25023
		        operationSkills = compareToolDao.selectOperationSkillAltLog(operationNumber,
		                                                                    orderId,
		                                                                    NumberUtils.INTEGER_MINUS_ONE,
		                                                                    altId);
		        
		        //GE-1815
		        operationStandards =compareToolDao.selectOperationStandardsAltLog(orderId, 
						  														  operationNumber,  
						  														  NumberUtils.INTEGER_MINUS_ONE,
						  														  altId);
		    }
		}

		if (operationSkills.size() > 0 || operationStandards.size() > 0)
		{
			returnMap = new ListOrderedMap();
			returnMap.put("LEVEL", 3);
			returnMap.put("INSTRUCTION_NAME", "STANDARDS AND SKILLS");
			returnMap.put("CHANGE_INDICATOR", changeIndicator);
			returnMap.put("ALT_NO", null);
			returnMap.put("OBJECT_ID", null);
			returnMap.put("IMAGE", null);
			returnMap.put("PARENT_KEY", "HEADER DATA "
					+ operationNumber);
			returnMap.put("CHILD_KEY", "SKILLS " + operationNumber);
			returnMap.put("PARAMS", params);
			
			returnList.add(returnMap);

			//GE-1815 Standards
			returnMap = new ListOrderedMap();
			returnMap.put("LEVEL", 4);
			returnMap.put("INSTRUCTION_NAME", "STANDARDS");
			//changeIndicator = EMPTY;
			returnMap.put("CHANGE_INDICATOR", EMPTY);
			returnMap.put("ALT_NO", null);
			returnMap.put("OBJECT_ID", null);
			returnMap.put("IMAGE", null);
			returnMap.put("PARENT_KEY", "STANDARDS AND SKILLS "
					+ operationNumber);
			returnMap.put("CHILD_KEY", "STANDARDS" + operationNumber);
			returnMap.put("PARAMS", params);
			
			returnList.add(returnMap);

			//GE-1815 Skills
			if (operationSkills.size() > 0 )
			{
				returnMap = new ListOrderedMap();
				returnMap.put("LEVEL", 4);
				returnMap.put("INSTRUCTION_NAME", "SKILLS");
				//changeIndicator = EMPTY;
				returnMap.put("CHANGE_INDICATOR", EMPTY);
				returnMap.put("ALT_NO", null);
				returnMap.put("OBJECT_ID", null);
				returnMap.put("IMAGE", null);
				returnMap.put("PARENT_KEY", "STANDARDS AND SKILLS "
						+ operationNumber);
				returnMap.put("CHILD_KEY", "SKILLS " + operationNumber);
				returnMap.put("PARAMS", params);
				
				returnList.add(returnMap);
			}
		}
		return changeIndicator;
	}

	// GE-1904
	protected void compareOperationFooterBlocks(String fromPlanId,
											  String toPlanId, 
											  String fromOrderId, 
											  String toOrderId,
											  String fromAltId, 
											  String toAltId, 
											  String fromType, 
											  String toType,
											  List returnList, 
											  Map booleanCompareStateMap,
											  String operationNumber,
											  Number fromOperationKey,
											  Number fromStepKey,
											  Number fromOperationUpdateNumber,
											  Number fromStepUpdateNumber, 
											  Number toOperationKey,
											  Number toStepKey, 
											  Number toOperationUpdateNumber,
											  Number toStepUpdateNumber,
											  Map fromInfoMap,  // GE-2317
											  Map toInfoMap)    // GE-2317
	{
		Map returnMap;
		String changeIndicator;
		String params;
		String textType;
		String blockType;
		Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
		Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
		if(fromInfoMap != null && !fromInfoMap.isEmpty())
		{
		    fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
		}
		if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
        }
		boolean compareOperationFooterBlocks = (booleanCompareStateMap.get("compareMultipleBlocks_footer") == null) ? 
												false: ((Boolean)booleanCompareStateMap.get("compareMultipleBlocks_footer")).booleanValue();

		params = "FROM_OPER_NO=" + operationNumber + ";FROM_OPER_KEY="
		        + fromOperationKey + ";FROM_OPER_UPDT_NO="
		        + fromOperationUpdateNumber + ";TO_OPER_NO="
		        + operationNumber + ";TO_OPER_KEY=" + toOperationKey
		        + ";TO_OPER_UPDT_NO=" + toOperationUpdateNumber
		        + ";FROM_STEP_NO=" + ELLIPSIS
		        + ";FROM_STEP_KEY=" + fromStepKey
		        + ";FROM_STEP_UPDT_NO=" + fromStepUpdateNumber
		        + ";TO_STEP_NO=" + ELLIPSIS
		        + ";TO_STEP_KEY=" + toStepKey + ";TO_STEP_UPDT_NO="
		        + toStepUpdateNumber
		        + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		        + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
		        + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey  // GE-2317
		        + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;     // GE-2317

		returnMap = new ListOrderedMap();
		returnMap.put("LEVEL", 2);
		returnMap.put("INSTRUCTION_NAME", "OPERATION FOOTER BLOCKS");
		changeIndicator = EMPTY;

		if (compareOperationFooterBlocks)
		{
		    changeIndicator = "CHECK_MARK";
		}
		else
		{
		    changeIndicator = "PENCIL_MARK";
		}
		returnMap.put("CHANGE_INDICATOR", changeIndicator);
		returnMap.put("ALT_NO", null);
		returnMap.put("OBJECT_ID", null);
		returnMap.put("IMAGE", null);
		returnMap.put("PARENT_KEY", "OPERATION " + operationNumber);
		returnMap.put("CHILD_KEY", "OPERATION FOOTER BLOCKS "
		        + operationNumber);
		returnMap.put("PARAMS", params);

		returnList.add(returnMap);

		if (equalsIgnoreCase(fromType, "ORDER")
		        || equalsIgnoreCase(toType, "ORDER"))
		{
		    textType = "FOOTER_DISP";
		    blockType = "DISPOSITION";

		    // Operation Footer Disposition Block
		    List footerDispBlock = generateDispositionBlock(fromOrderId,
		                                                    fromOperationKey,
		                                                    fromStepKey,
		                                                    toOrderId,
		                                                    toOperationKey,
		                                                    toStepKey,
		                                                    textType,
		                                                    operationNumber,
		                                                    ELLIPSIS,
		                                                    FOOTER,
		                                                    blockType,
		                                                    fromAltId,
		                                                    toAltId);

		    if (footerDispBlock.size() > 0)
		    {
		        returnList.addAll(footerDispBlock);
		    }
		}

		textType = "PLANNING";
		blockType = "PLANNING";

		// Operation Footer Planning Block
		List planningFooterBlock = generateBlockHierarchy(operationNumber,
		                                                  ELLIPSIS,
		                                                  fromPlanId,
		                                                  fromOperationKey,
		                                                  fromOperationUpdateNumber,
		                                                  fromStepKey,
		                                                  fromStepUpdateNumber,
		                                                  toPlanId,
		                                                  toOperationKey,
		                                                  toOperationUpdateNumber,
		                                                  toStepKey,
		                                                  toStepUpdateNumber,
		                                                  textType,
		                                                  blockType,
		                                                  FOOTER,
		                                                  fromOrderId,
		                                                  toOrderId,
		                                                  fromType,
		                                                  toType,
		                                                  fromAltId,
		                                                  toAltId,
                                                          fromInfoMap,  // GE-2317
                                                          toInfoMap); // GE-2317
		if (planningFooterBlock.size() > 0)
		{
		    returnList.addAll(planningFooterBlock);
		}

		textType = "QA";
		blockType = "QA";

		// Operation Footer QA Block
		List footerQABlock = generateBlockHierarchy(operationNumber,
		                                            ELLIPSIS,
		                                            fromPlanId,
		                                            fromOperationKey,
		                                            fromOperationUpdateNumber,
		                                            fromStepKey,
		                                            fromStepUpdateNumber,
		                                            toPlanId,
		                                            toOperationKey,
		                                            toOperationUpdateNumber,
		                                            toStepKey,
		                                            toStepUpdateNumber,
		                                            textType,
		                                            blockType,
		                                            FOOTER,
		                                            fromOrderId,
		                                            toOrderId,
		                                            fromType,
		                                            toType,
		                                            fromAltId,
		                                            toAltId,
                                                    fromInfoMap,  // GE-2317
                                                    toInfoMap); // GE-2317
		if (footerQABlock.size() > 0)
		{
		    returnList.addAll(footerQABlock);
		}
	}

	// GE-1904
	protected void compareStep(String fromPlanId, 
    						 Number fromPlanVersion,
    						 Number fromPlanRevision, 
    						 Number fromPlanAlterations,
    						 String toPlanId, 
    						 Number toPlanVersion,
    						 Number toPlanRevision,
    						 Number toPlanAlterations, 
    						 String fromOrderId, 
    						 String toOrderId,
    						 String fromAltId, 
    						 String toAltId, 
    						 String fromType, 
    						 String toType,
    						 List returnList, 
    						 String changeIndicator,
    						 Map booleanCompareStateMap, 
    						 String operationNumber,
    						 Number fromOperationKey, 
    						 Number fromStepKey,
    						 Number fromOperationUpdateNumber, 
    						 Number toOperationKey,
    						 Number toStepKey, 
    						 Number toOperationUpdateNumber,
    						 String stepNumber, 
    						 boolean fromStepExists, 
    						 boolean toStepExists,
    						 Map fromInfoMap,
    						 Map toInfoMap) 
    {
    	Map returnMap;
    	String params;
    	Number fromStepUpdateNumber;
    	Number toStepUpdateNumber;
    	String textType;
    	String blockType;
        
        // GE-2317
        Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
        Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
        String fromMainOrStdOperNo = operationNumber;
        String toMainOrStdOperNo = operationNumber;
        if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
            fromMainOrStdOperNo = (String)fromInfoMap.get( "FROM_OPER_NO" );
        }
        if(toInfoMap != null && !toInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
            toMainOrStdOperNo = (String)toInfoMap.get( "TO_OPER_NO" );
        }
    	
    	if (fromStepExists && toStepExists)
    	{
    		Map fromStepInfo = null;
    		Map toStepInfo = null;

    		if (equalsIgnoreCase(fromType, "PLAN")
    				&& equalsIgnoreCase(fromType, toType))
    		{
    			// Selects Step information for From Plan
    			fromStepInfo = compareToolDao.selectStepInformation(fromPlanId,
    																fromPlanVersion,
    																fromPlanRevision,
    																fromPlanAlterations,
    																fromOperationKey,
    																fromOperationUpdateNumber,
    																stepNumber);

    			// Selects Step information for To Plan
    			toStepInfo = compareToolDao.selectStepInformation(toPlanId,
    															  toPlanVersion,
    															  toPlanRevision,
    															  toPlanAlterations,
    															  toOperationKey,
    															  toOperationUpdateNumber,
    															  stepNumber);
    		}
    		else if (equalsIgnoreCase(fromType, "ORDER")
    				&& equalsIgnoreCase(fromType, toType))
    		{
    			if(isEmpty( fromAltId ))
    			{
    				// Selects Step information for From Order
    				fromStepInfo = compareToolDao.selectStepInformation(fromOrderId,
    																	fromOperationKey,
    																	stepNumber);
    			}
    			else
    			{
    				//FND-25023 Selects Step information in Alteration for From Order
    				fromStepInfo = compareToolDao.selectStepInformationAltLog(fromOrderId,
    																		  fromOperationKey,
    																		  stepNumber,
    																		  fromAltId);
    			}

    			if(isEmpty( toAltId ))
    			{
    				// Selects Step information for To Order
    				toStepInfo = compareToolDao.selectStepInformation(toOrderId,
    																  toOperationKey,
    																  stepNumber);
    			}
    			else
    			{ 
    				//FND-25023 Selects Step information in Alteration for To Order
    				toStepInfo = compareToolDao.selectStepInformationAltLog(toOrderId,
    																		toOperationKey,
    																		stepNumber,
    																		toAltId);
    			}
    		}
    		else
    		{
    			if (equalsIgnoreCase(fromType, "PLAN")
    					&& equalsIgnoreCase(toType, "ORDER"))
    			{
    				// Selects Step information for From Plan
    				fromStepInfo = compareToolDao.selectStepInformation(fromPlanId,
    																	fromPlanVersion,
    																	fromPlanRevision,
    																	fromPlanAlterations,
    																	fromOperationKey,
    																	fromOperationUpdateNumber,
    																	stepNumber);

    				// Selects Step information for To Order
    				toStepInfo = compareToolDao.selectStepInformation(toOrderId,
    																  toOperationKey,
    																  stepNumber);
    			}
    			else if (equalsIgnoreCase(fromType, "ORDER")
    					&& equalsIgnoreCase(toType, "PLAN"))
    			{
    				// Selects Step information for From Order
    				fromStepInfo = compareToolDao.selectStepInformation(fromOrderId,
    																	fromOperationKey,
    																	stepNumber);

    				// Selects Step information for To Plan
    				toStepInfo = compareToolDao.selectStepInformation(toPlanId,
    																  toPlanVersion,
    																  toPlanRevision,
    																  toPlanAlterations,
    																  toOperationKey,
    																  toOperationUpdateNumber,
    																  stepNumber);
    			}
    		}

    		fromStepKey = (Number) fromStepInfo.get("STEP_KEY");
    		fromStepUpdateNumber = (Number) fromStepInfo.get("STEP_UPDT_NO");

    		toStepKey = (Number) toStepInfo.get("STEP_KEY");
    		toStepUpdateNumber = (Number) toStepInfo.get("STEP_UPDT_NO");

    		// 05/07/2011 Suresh FND-15735
    		// Performance: Compare Plan/Order
    		// Compares the Step
    		boolean compareStep = (booleanCompareStateMap.get("compareMultipleBlocks_"+stepNumber) == null) ? 
    				false : ((Boolean)booleanCompareStateMap.get("compareMultipleBlocks_"+stepNumber)).booleanValue();

    		if (compareStep)
    		{
    			changeIndicator = "CHECK_MARK";
    		}
    		else
    		{
    			changeIndicator = "PENCIL_MARK";
    		}

    		params = "FROM_OPER_NO=" + fromMainOrStdOperNo // GE-2317
    				+ ";FROM_OPER_KEY=" + fromOperationKey
    				+ ";FROM_OPER_UPDT_NO="
    				+ fromOperationUpdateNumber + ";TO_OPER_NO="
    				+ toMainOrStdOperNo + ";TO_OPER_KEY="      //  GE-2317
    				+ toOperationKey + ";TO_OPER_UPDT_NO="     //  GE-2317
    				+ toOperationUpdateNumber + ";FROM_STEP_NO="
    				+ stepNumber + ";FROM_STEP_KEY=" + fromStepKey
    				+ ";FROM_STEP_UPDT_NO=" + fromStepUpdateNumber
    				+ ";TO_STEP_NO=" + stepNumber + ";TO_STEP_KEY="
    				+ toStepKey + ";TO_STEP_UPDT_NO="
    				+ toStepUpdateNumber
    				+ ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
    				+ ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
    				+ ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey   // GE-2317
    				+ ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;      // GE-2317

    		returnMap = new ListOrderedMap();

    		returnMap.put("LEVEL", 2);
    		returnMap.put("INSTRUCTION_NAME", "STEP " + stepNumber);
    		returnMap.put("CHANGE_INDICATOR", changeIndicator);
    		returnMap.put("ALT_NO", null);
    		returnMap.put("OBJECT_ID", null);
    		returnMap.put("IMAGE", null);
    		returnMap.put("PARENT_KEY", "OPERATION " + operationNumber); // FND-26100
    		returnMap.put("CHILD_KEY", "STEP " + operationNumber  + stepNumber);
    		returnMap.put("PARAMS", params);

    		returnList.add(returnMap);

    		if (equalsIgnoreCase(fromType, "ORDER")
    				|| equalsIgnoreCase(toType, "ORDER"))
    		{
    			textType = "DISP";
    			blockType = "DISPOSITION";

    			// Operation Step Disposition Block
    			List stepDispBlock = generateDispositionBlock(fromOrderId,
    														  fromOperationKey,
    														  fromStepKey,
    														  toOrderId,
    														  toOperationKey,
    														  toStepKey,
    														  textType,
    														  operationNumber,
    														  stepNumber,
    														  HEADER,
    														  blockType,
    														  fromAltId,
    														  toAltId);

    			if (stepDispBlock.size() > 0)
    			{
    				returnList.addAll(stepDispBlock);
    			}
    		}

    		textType = "PLANNING";
    		blockType = "PLANNING";

    		// Step Planning Block
    		List stepPlanningBlock = generateBlockHierarchy(operationNumber,
    														stepNumber,
    														fromPlanId,
    														fromOperationKey,
    														fromOperationUpdateNumber,
    														fromStepKey,
    														fromStepUpdateNumber,
    														toPlanId,
    														toOperationKey,
    														toOperationUpdateNumber,
    														toStepKey,
    														toStepUpdateNumber,
    														textType,
    														blockType,
    														HEADER,
    														fromOrderId,
    														toOrderId,
    														fromType,
    														toType,
    														fromAltId,
    														toAltId,
    														fromInfoMap,  // GE-2317
    														toInfoMap);   // GE-2317
    		if (stepPlanningBlock.size() > 0)
    		{
    			returnList.addAll(stepPlanningBlock);
    		}

    		textType = "QA";
    		blockType = "QA";

    		// Step QA Block
    		List stepQABlock = generateBlockHierarchy(operationNumber,
    												  stepNumber,
    												  fromPlanId,
    												  fromOperationKey,
    												  fromOperationUpdateNumber,
    												  fromStepKey,
    												  fromStepUpdateNumber,
    												  toPlanId,
    												  toOperationKey,
    												  toOperationUpdateNumber,
    												  toStepKey,
    												  toStepUpdateNumber,
    												  textType,
    												  blockType,
    												  HEADER,
    												  fromOrderId,
    												  toOrderId,
    												  fromType,
    												  toType,
    												  fromAltId,
    												  toAltId,
                                                      fromInfoMap,  // GE-2317
                                                      toInfoMap);   // GE-2317
    		if (stepQABlock.size() > 0)
    		{
    			returnList.addAll(stepQABlock);
    		}

    	}
    	else
    	{
    		params = EMPTY;
    		String planId = EMPTY;
    		Number planVersion = null;
    		Number planRevision = null;
    		Number planAlterations = null;
    		Number operationKey = null;
    		Number operationUpdateNumber = null;
    		Number stepKey = null;
    		Number stepUpdateNumber = null;
    		String orderId = null;
    		String altId = null;

    		if (fromStepExists && !toStepExists)
    		{
    			changeIndicator = "MINUS_MARK";
    			planId = fromPlanId;
    			planVersion = fromPlanVersion;
    			planRevision = fromPlanRevision;
    			planAlterations = fromPlanAlterations;
    			operationKey = fromOperationKey;
    			operationUpdateNumber = fromOperationUpdateNumber;
    			orderId = fromOrderId;
    			altId = fromAltId;
    		}
    		else if (!fromStepExists && toStepExists)
    		{
    			changeIndicator = "PLUS_MARK";
    			planId = toPlanId;
    			planVersion = toPlanVersion;
    			planRevision = toPlanRevision;
    			planAlterations = toPlanAlterations;
    			operationKey = toOperationKey;
    			operationUpdateNumber = toOperationUpdateNumber;
    			orderId = toOrderId;
    			altId = toAltId;
    		}

    		Map stepInfo = null;

    		if (isEmpty(orderId))
    		{
    			stepInfo = compareToolDao.selectStepInformation(planId,
    															planVersion,
    															planRevision,
    															planAlterations,
    															operationKey,
    															operationUpdateNumber,
    															stepNumber);
    		}
    		else
    		{
    			if(isEmpty( altId ))
    			{
    				stepInfo = compareToolDao.selectStepInformation(orderId,
    																operationKey,
    																stepNumber);
    			}
    			else
    			{
    				//FND-25023 select step info in alteration of Operation  
    				stepInfo = compareToolDao.selectStepInformationAltLog(orderId,
    																	  operationKey,
    																	  stepNumber,
    																	  altId);
    			}
    		}

    		stepKey = (Number) stepInfo.get("STEP_KEY");
    		stepUpdateNumber = (Number) stepInfo.get("STEP_UPDT_NO");

    		if (equalsIgnoreCase(changeIndicator, "MINUS_MARK"))
    		{
    			params = "FROM_OPER_NO=" + fromMainOrStdOperNo
    					+ ";FROM_OPER_KEY=" + operationKey
    					+ ";FROM_OPER_UPDT_NO="
    					+ operationUpdateNumber + ";TO_OPER_NO="
    					+ ";TO_OPER_KEY=" + ";TO_OPER_UPDT_NO="
    					+ ";FROM_STEP_NO=" + stepNumber
    					+ ";FROM_STEP_KEY=" + stepKey
    					+ ";FROM_STEP_UPDT_NO=" + stepUpdateNumber
    					+ ";TO_STEP_NO=" + ";TO_STEP_KEY="
    					+ ";TO_STEP_UPDT_NO=" + ";TEXT_TYPE="
    					+ ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
    					+ ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                        + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey   // GE-2317
                        + ";TO_SCOPE_OPER_KEY=";      // GE-2317
    		}
    		else
    		{
    			params = "FROM_OPER_NO=" + ";FROM_OPER_KEY="
    					+ ";FROM_OPER_UPDT_NO=" + ";TO_OPER_NO="
    					+ toMainOrStdOperNo + ";TO_OPER_KEY="
    					+ operationKey + ";TO_OPER_UPDT_NO="
    					+ operationUpdateNumber + ";FROM_STEP_NO="
    					+ ";FROM_STEP_KEY=" + ";FROM_STEP_UPDT_NO="
    					+ ";TO_STEP_NO=" + stepNumber
    					+ ";TO_STEP_KEY=" + stepKey
    					+ ";TO_STEP_UPDT_NO=" + stepUpdateNumber
    					+ ";TEXT_TYPE="
    					+ ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
    					+ ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                        + ";FROM_SCOPE_OPER_KEY="   // GE-2317
                        + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;      // GE-2317
    		}

    		returnMap = new ListOrderedMap();

    		returnMap.put("LEVEL", 2);
    		returnMap.put("INSTRUCTION_NAME", "STEP " + stepNumber);
    		returnMap.put("CHANGE_INDICATOR", changeIndicator);
    		returnMap.put("ALT_NO", null);
    		returnMap.put("OBJECT_ID", null);
    		returnMap.put("IMAGE", null);
    		returnMap.put("PARENT_KEY", "OPERATION " + operationNumber); // FND-26100
    		returnMap.put("CHILD_KEY", "STEP " + operationNumber  + stepNumber);
    		returnMap.put("PARAMS", params);

    		returnList.add(returnMap);

    		if (equalsIgnoreCase(fromType, "ORDER")
    				|| equalsIgnoreCase(toType, "ORDER"))
    		{
    			textType = "DISP";
    			blockType = "DISPOSITION";

    			// Operation Step Disposition Block
    			List stepDispBlock = generateDispositionBlock(fromOrderId,
    														  fromOperationKey,
    														  fromStepKey,
    														  toOrderId,
    														  toOperationKey,
    														  toStepKey,
    														  textType,
    														  operationNumber,
    														  stepNumber,
    														  HEADER,
    														  blockType,
    														  fromAltId,
    														  toAltId);

    			if (stepDispBlock.size() > 0)
    			{
    				returnList.addAll(stepDispBlock);
    			}
    		}

    		textType = "PLANNING";
    		blockType = "PLANNING";

    		List stepPlanningBlock = generateSingleBlock(operationNumber,
    													 stepNumber,
    													 planId,
    													 operationKey,
    													 operationUpdateNumber,
    													 stepKey,
    													 stepUpdateNumber,
    													 textType,
    													 blockType,
    													 changeIndicator,
    													 HEADER,
    													 orderId,
    													 altId,  //FND-25023
    													 fromInfoMap,  // GE-2317
    													 toInfoMap);  // GE-2317

    		if (stepPlanningBlock.size() > 0)
    		{
    			returnList.addAll(stepPlanningBlock);
    		}

    		textType = "QA";
    		blockType = "QA";

    		List stepQABlock = generateSingleBlock(operationNumber,
    											   stepNumber,
    											   planId,
    											   operationKey,
    											   operationUpdateNumber,
    											   stepKey,
    											   stepUpdateNumber,
    											   textType,
    											   blockType,
    											   changeIndicator,
    											   HEADER,
    											   orderId,
                                                   altId,  //FND-25023
                                                   fromInfoMap,  // GE-2317
                                                   toInfoMap);  // GE-2317

    		if (stepQABlock.size() > 0)
    		{
    			returnList.addAll(stepQABlock);
    		}
    	}
    }

    // GE-1904
	protected void compareOperationPrecedence(List returnList, 
											String params,
											Map booleanCompareStateMap, 
											String operationNumber) 
	{
		Map returnMap;
		String changeIndicator;
		boolean fromOperationPrecedenceExists = (booleanCompareStateMap.get("fromOperationPrecedenceExists") == null) ? 
													false : ((Boolean)booleanCompareStateMap.get("fromOperationPrecedenceExists")).booleanValue();
		
		boolean toOperationPrecedenceExists = (booleanCompareStateMap.get("toOperationPrecedenceExists") == null) ? 
													false : ((Boolean)booleanCompareStateMap.get("toOperationPrecedenceExists")).booleanValue();

		if (fromOperationPrecedenceExists && toOperationPrecedenceExists)
		{
		    // 05/07/2011 Suresh FND-15735
		    // Performance: Compare Plan/Order
		    boolean compareOperationPrecedence = (booleanCompareStateMap.get("compareOperationPrecedence") == null) ? 
		    										false : ((Boolean)booleanCompareStateMap.get("compareOperationPrecedence")).booleanValue();

		    returnMap = new ListOrderedMap();
		    returnMap.put("LEVEL", 3);
		    returnMap.put("INSTRUCTION_NAME", "OPERATION PRECEDENCE");
		    changeIndicator = EMPTY;

		    if (compareOperationPrecedence)
		    {
		        changeIndicator = "CHECK_MARK";
		    }
		    else
		    {
		        changeIndicator = "PENCIL_MARK";
		    }
		    returnMap.put("CHANGE_INDICATOR", changeIndicator);
		    returnMap.put("ALT_NO", null);
		    returnMap.put("OBJECT_ID", null);
		    returnMap.put("IMAGE", null);
		    returnMap.put("PARENT_KEY", "HEADER DATA "
		            + operationNumber);
		    returnMap.put("CHILD_KEY", "OPERATION PRECEDENCE "
		            + operationNumber);
		    returnMap.put("PARAMS", params);

		    returnList.add(returnMap);
		}
		else
		{
		    changeIndicator = EMPTY;
		    boolean display = true;

		    if (fromOperationPrecedenceExists
		            && !toOperationPrecedenceExists)
		    {
		        changeIndicator = "MINUS_MARK";
		    }
		    else if (!fromOperationPrecedenceExists
		            && toOperationPrecedenceExists)
		    {
		        changeIndicator = "PLUS_MARK";
		    }
		    else if (!fromOperationPrecedenceExists
		            && !toOperationPrecedenceExists)
		    {
		        display = false;
		    }

		    if (display)
		    {
		        returnMap = new ListOrderedMap();
		        returnMap.put("LEVEL", 3);
		        returnMap.put("INSTRUCTION_NAME",
		                      "OPERATION PRECEDENCE");
		        returnMap.put("CHANGE_INDICATOR", changeIndicator);
		        returnMap.put("ALT_NO", null);
		        returnMap.put("OBJECT_ID", null);
		        returnMap.put("IMAGE", null);
		        returnMap.put("PARENT_KEY", "HEADER DATA "
		                + operationNumber);
		        returnMap.put("CHILD_KEY", "OPERATION PRECEDENCE "
		                + operationNumber);
		        returnMap.put("PARAMS", params);

		        returnList.add(returnMap);
		    }
		}
	}

	// GE-1904
	protected void compareOperationSkills(List returnList, 
										String params,
										Map booleanCompareStateMap, 
										String operationNumber) 
	{
		Map returnMap;
		String changeIndicator;
		boolean compareOperationSkill;
		boolean fromSkillExists = (booleanCompareStateMap.get("fromOperationSkillExists") == null) ? 
									false : ((Boolean)booleanCompareStateMap.get("fromOperationSkillExists")).booleanValue();
		
		boolean toSkillExists = (booleanCompareStateMap.get("toOperationSkillExists") == null) ? 
									false : ((Boolean)booleanCompareStateMap.get("toOperationSkillExists")).booleanValue();

		if (fromSkillExists && toSkillExists)
		{
		    // 05/07/2011 Suresh FND-15735
		    // Performance: Compare Plan/Order
		     compareOperationSkill = (booleanCompareStateMap.get("compareOperationSkill") == null) ? 
		    						   false : ((Boolean)booleanCompareStateMap.get("compareOperationSkill")).booleanValue();
		
		    returnMap = new ListOrderedMap();
		    returnMap.put("LEVEL", 4);
		    returnMap.put("INSTRUCTION_NAME", "SKILLS");
		    changeIndicator = EMPTY;

		    if (compareOperationSkill)
		    {
		        changeIndicator = "CHECK_MARK";
		    }
		    else
		    {
		        changeIndicator = "PENCIL_MARK";
		    }
		    returnMap.put("CHANGE_INDICATOR", changeIndicator);
		    returnMap.put("ALT_NO", null);
		    returnMap.put("OBJECT_ID", null);
		    returnMap.put("IMAGE", null);
		    returnMap.put("PARENT_KEY", "STANDARDS AND SKILLS "
		            + operationNumber);
		    returnMap.put("CHILD_KEY", "SKILLS " + operationNumber);
		    returnMap.put("PARAMS", params);

		    returnList.add(returnMap);
		}
		else
		{
		    changeIndicator = EMPTY;
		    boolean display = true;

		    if (fromSkillExists && !toSkillExists)
		    {
		        changeIndicator = "MINUS_MARK";
		    }
		    else if (!fromSkillExists && toSkillExists)
		    {
		        changeIndicator = "PLUS_MARK";
		    }
		    else if (!fromSkillExists && !toSkillExists)
		    {
		        display = false;
		    }

		    if (display)
		    {
		        returnMap = new ListOrderedMap();
		        returnMap.put("LEVEL", 4);
		        returnMap.put("INSTRUCTION_NAME", "SKILLS"); 
		        returnMap.put("CHANGE_INDICATOR", changeIndicator);
		        returnMap.put("ALT_NO", null);
		        returnMap.put("OBJECT_ID", null);
		        returnMap.put("IMAGE", null);
		        returnMap.put("PARENT_KEY", "STANDARDS AND SKILLS "
		                + operationNumber);
		        returnMap.put("CHILD_KEY", "SKILLS " + operationNumber);
		        returnMap.put("PARAMS", params);

		        returnList.add(returnMap);
		    }
		}
	}

	// GE-1904
	protected void compareOperationStandards(List returnList, 
	                                         String params,
	                                         Map booleanCompareStateMap, 
	                                         String operationNumber) 
	{
		Map returnMap;
		String changeIndicator;
		boolean compareOperationStandards;
		compareOperationStandards = (booleanCompareStateMap.get("compareOperationStandards") == null) ? false
				: ((Boolean) booleanCompareStateMap.get("compareOperationStandards")).booleanValue();

		returnMap = new ListOrderedMap();
		returnMap.put("LEVEL", 4);
		returnMap.put("INSTRUCTION_NAME", "STANDARDS");
		changeIndicator = EMPTY;

		if (compareOperationStandards) {
			changeIndicator = "CHECK_MARK";
		} else {
			changeIndicator = "PENCIL_MARK";
		}
		returnMap.put("CHANGE_INDICATOR", changeIndicator);
		returnMap.put("ALT_NO", null);
		returnMap.put("OBJECT_ID", null);
		returnMap.put("IMAGE", null);
		returnMap.put("PARENT_KEY", "STANDARDS AND SKILLS "
				+ operationNumber);
		returnMap.put("CHILD_KEY", "STANDARDS" + operationNumber);
		returnMap.put("PARAMS", params);

		returnList.add(returnMap);
	}

	// GE-1904
	protected void compareOperationStandardsAndSkills(List returnList,
													String params, 
													Map booleanCompareStateMap, 
													String operationNumber) 
	{
		Map returnMap;
		String changeIndicator;
		boolean compareOperationStandards = (booleanCompareStateMap.get("compareOperationStandards") == null) ? false
				: ((Boolean) booleanCompareStateMap.get("compareOperationStandards")).booleanValue();
		boolean compareOperationSkill = (booleanCompareStateMap.get("compareOperationSkill") == null) ? 
				false : ((Boolean)booleanCompareStateMap.get("compareOperationSkill")).booleanValue();
            
		returnMap = new ListOrderedMap();
		returnMap.put("LEVEL", 3);
		returnMap.put("INSTRUCTION_NAME", "STANDARDS AND SKILLS");
		changeIndicator = EMPTY;

		if (compareOperationSkill && compareOperationStandards)
		{
		    changeIndicator = "CHECK_MARK";
		}
		else
		{
		    changeIndicator = "PENCIL_MARK";
		}
		returnMap.put("CHANGE_INDICATOR", changeIndicator);
		returnMap.put("ALT_NO", null);
		returnMap.put("OBJECT_ID", null);
		returnMap.put("IMAGE", null);
		returnMap.put("PARENT_KEY", "HEADER DATA "
		        + operationNumber);
		returnMap.put("CHILD_KEY", "STANDARDS AND SKILLS " + operationNumber);
		returnMap.put("PARAMS", params);

		returnList.add(returnMap);
	}

    // GE-1904
	protected void compareProcessType(List returnList, 
    							 	String params,
    							 	Map booleanCompareStateMap, 
    							 	String operationNumber) 
    {
    	Map returnMap;
    	String changeIndicator;
    	boolean fromProcessExists = (booleanCompareStateMap.get("fromOperationProcessExists") == null) ? 
    			false : ((Boolean)booleanCompareStateMap.get("fromOperationProcessExists")).booleanValue();

    	boolean toProcessExists = (booleanCompareStateMap.get("toOperationProcessExists") == null) ? 
    			false : ((Boolean)booleanCompareStateMap.get("toOperationProcessExists")).booleanValue();

    	if (fromProcessExists && toProcessExists)
    	{
    		// 05/07/2011 Suresh FND-15735
    		// Performance: Compare Plan/Order
    		boolean compareOperationProcess = (booleanCompareStateMap.get("compareOperationProcess") == null) ? 
    				false : ((Boolean)booleanCompareStateMap.get("compareOperationProcess")).booleanValue();

    		returnMap = new ListOrderedMap();
    		returnMap.put("LEVEL", 3);
    		returnMap.put("INSTRUCTION_NAME", "PROCESS TYPE");
    		changeIndicator = EMPTY;

    		if (compareOperationProcess)
    		{
    			changeIndicator = "CHECK_MARK";
    		}
    		else
    		{
    			changeIndicator = "PENCIL_MARK";
    		}
    		returnMap.put("CHANGE_INDICATOR", changeIndicator);
    		returnMap.put("ALT_NO", null);
    		returnMap.put("OBJECT_ID", null);
    		returnMap.put("IMAGE", null);
    		returnMap.put("PARENT_KEY", "HEADER DATA "
    				+ operationNumber);
    		returnMap.put("CHILD_KEY", "PROCESS TYPE "
    				+ operationNumber);
    		returnMap.put("PARAMS", params);

    		returnList.add(returnMap);
    	}
    	else
    	{
    		changeIndicator = EMPTY;
    		boolean display = true;

    		if (fromProcessExists && !toProcessExists)
    		{
    			changeIndicator = "MINUS_MARK";
    		}
    		else if (!fromProcessExists && toProcessExists)
    		{
    			changeIndicator = "PLUS_MARK";
    		}
    		else if (!fromProcessExists && !toProcessExists)
    		{
    			display = false;
    		}

    		if (display)
    		{
    			returnMap = new ListOrderedMap();
    			returnMap.put("LEVEL", 3);
    			returnMap.put("INSTRUCTION_NAME", "PROCESS TYPE");
    			returnMap.put("CHANGE_INDICATOR", changeIndicator);
    			returnMap.put("ALT_NO", null);
    			returnMap.put("OBJECT_ID", null);
    			returnMap.put("IMAGE", null);
    			returnMap.put("PARENT_KEY", "HEADER DATA "
    					+ operationNumber);
    			returnMap.put("CHILD_KEY", "PROCESS TYPE "
    					+ operationNumber);
    			returnMap.put("PARAMS", params);

    			returnList.add(returnMap);
    		}
    	}
    }

    // GE-1904
	protected List getOperationNumberList(String fromPlanId,
										Number fromPlanVersion, 
										Number fromPlanRevision,
										Number fromPlanAlterations, 
										String toPlanId, 
										Number toPlanVersion,
										Number toPlanRevision, 
										Number toPlanAlterations,
										String fromOrderId, 
										String toOrderId, 
										String fromAltId,
										String toAltId, 
										Number compareOperKey, 
										String fromType,
										String toType, 
										List operationNumberList) 
	{
		if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Distinct Operations
        	operationNumberList = compareToolDao.selectDistinctOperationNumbers(fromPlanId,
                                                                                fromPlanVersion,
                                                                                fromPlanRevision,
                                                                                fromPlanAlterations,
                                                                                toPlanId,
                                                                                toPlanVersion,
                                                                                toPlanRevision,
                                                                                toPlanAlterations,
                                                                                compareOperKey); // GE-20
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Distinct Operations
            	operationNumberList = compareToolDao.selectDistinctOperationNumbers(fromOrderId,
                                                                                    toOrderId,
                                                                                    NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                // FND-25023 Selects the Distinct Operations for alterations
            	operationNumberList = compareToolDao.selectDistinctOperationNumbersAltLog(fromOrderId,
                                                                                          toOrderId,
                                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                                          fromAltId,
                                                                                          toAltId,
                                                                                          compareOperKey);
            }
            
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Distinct Operations
            	operationNumberList = compareToolDao.selectDistinctOperationNumbers(toOrderId,
                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                    fromPlanId,
                                                                                    fromPlanVersion,
                                                                                    fromPlanRevision,
                                                                                    fromPlanAlterations);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Distinct Operations
            	operationNumberList = compareToolDao.selectDistinctOperationNumbers(fromOrderId,
                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                    toPlanId,
                                                                                    toPlanVersion,
                                                                                    toPlanRevision,
                                                                                    toPlanAlterations);
            }
        }
		return operationNumberList;
	}

	// GE-1904
	protected void comparePlanTextIllustration(String fromPlanId,
											 Number fromPlanUpdateNumber, 
											 String toPlanId,
											 Number toPlanUpdateNumber, 
											 String fromOrderId, 
											 String toOrderId,
											 String fromAltId,
											 String toAltId, 
											 String fromType, 
											 String toType,
											 String fromTo, 
											 List returnList) 
	{
		Map returnMap;
		String changeIndicator;
		String params;
		boolean comparePlanTextIllustration = comparePlanTextImageList( fromPlanId, 
														        		fromPlanUpdateNumber, 
														        		toPlanId, 
														        		toPlanUpdateNumber,
														        		PLANNING, 
														        		fromOrderId, 
														        		toOrderId, 
														        		fromType, 
														        		toType,
														        		fromAltId,
														        		toAltId);

        params = "FROM_OPER_NO=" + ";FROM_OPER_KEY=" + ";FROM_OPER_UPDT_NO="
		          + ";TO_OPER_NO="  + ";TO_OPER_KEY=" + ";TO_OPER_UPDT_NO="
		          + ";FROM_STEP_NO=" + ";FROM_STEP_KEY=" + ";FROM_STEP_UPDT_NO="
		          + ";TO_STEP_NO=" + ";TO_STEP_KEY=" + ";TO_STEP_UPDT_NO="
		          + ";FROM_PLAN_UPDT_NO=" + fromPlanUpdateNumber
		          + ";TO_PLAN_UPDT_NO=" + toPlanUpdateNumber
		          + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
		          + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "");
        
        returnMap = new ListOrderedMap();
        returnMap.put("LEVEL", 2);
        returnMap.put("INSTRUCTION_NAME", "IMAGE LIST");
        changeIndicator = EMPTY;

        if (comparePlanTextIllustration)
        {
            changeIndicator = "CHECK_MARK";
        }
        else
        {
            changeIndicator = "PENCIL_MARK";
        }
        returnMap.put("CHANGE_INDICATOR", changeIndicator);
        returnMap.put("ALT_NO", null);
        returnMap.put("OBJECT_ID", null);
        returnMap.put("IMAGE", null);
        returnMap.put("PARENT_KEY", fromTo + " HEADER TEXT");
        //returnMap.put("CHILD_KEY", "ILLUSTRATION");
        returnMap.put("CHILD_KEY", "IMAGE LIST");
        returnMap.put("PARAMS", params);

        returnList.add(returnMap);
	}

	// GE-1904
	protected void comparePlanHeaderText(String fromPlanId,
									   Number fromPlanUpdateNumber, 
									   String toPlanId,
									   Number toPlanUpdateNumber, 
									   String fromOrderId, 
									   String toOrderId,
									   String fromAltId, 
									   String toAltId, 
									   String fromType, 
									   String toType,
									   String fromTo, 
									   List returnList) 
	{
		Map returnMap;
		String changeIndicator;
		returnMap = new ListOrderedMap();
        boolean comparePlanHeaderText = comparePlanHeaderText(fromPlanId,
                                                              fromPlanUpdateNumber,
                                                              toPlanId,
                                                              toPlanUpdateNumber,
                                                              fromOrderId,
                                                              toOrderId,
                                                              fromType,
                                                              toType,
                                                              fromAltId,
                                                              toAltId);
        
        String params = null;
        
        // FND-25023
        if(isNotEmpty( fromAltId ))
        {
            params = "FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                     +";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                     /*+";COMPARE_OPER_KEY=" + ((compareOperKey != null) ? compareOperKey.intValue() : 0)*/;
        }

        returnMap.put("LEVEL", NumberUtils.INTEGER_ONE);
        returnMap.put("INSTRUCTION_NAME", fromTo + " HEADER TEXT");
        changeIndicator = EMPTY;

        if (comparePlanHeaderText)
        {
            changeIndicator = "CHECK_MARK";
        }
        else
        {
            changeIndicator = "PENCIL_MARK";
        }
        returnMap.put("CHANGE_INDICATOR", changeIndicator);
        returnMap.put("ALT_NO", null);
        returnMap.put("OBJECT_ID", null);
        returnMap.put("IMAGE", null);
        returnMap.put("PARENT_KEY", null);
        returnMap.put("CHILD_KEY", fromTo + " HEADER TEXT");
        returnMap.put("PARAMS", params); // FND-25023
        
        if(isNotEmpty(fromType))
        {
        	returnList.add(returnMap);
        }
        // Start Plan Header Standard Text

        boolean comparePlanStandardText = comparePlanHeaderStandardText(fromPlanId,
                                                                        fromPlanUpdateNumber,
                                                                        toPlanId,
                                                                        toPlanUpdateNumber,
                                                                        fromOrderId,
                                                                        toOrderId,
                                                                        fromType,
                                                                        toType,
                                                                        fromAltId,
                                                                        toAltId);
        returnMap = new ListOrderedMap();
        returnMap.put("LEVEL", 2);
        returnMap.put("INSTRUCTION_NAME", "TEXT");
        changeIndicator = EMPTY;

        if (comparePlanStandardText)
        {
            changeIndicator = "CHECK_MARK";
        }
        else
        {
            changeIndicator = "PENCIL_MARK";
        }
        returnMap.put("CHANGE_INDICATOR", changeIndicator);
        returnMap.put("ALT_NO", null);
        returnMap.put("OBJECT_ID", null);
        returnMap.put("IMAGE", null);
        returnMap.put("PARENT_KEY", fromTo + " HEADER TEXT");
        returnMap.put("CHILD_KEY", "TEXT");
        returnMap.put("PARAMS", params); // FND-25023
        if(isNotEmpty(fromType))
        {
        	returnList.add(returnMap);
        }
	}

	// GE-1904
	protected void comparePlanHeaderData(String fromPlanId,
									   Number fromPlanVersion, 
									   Number fromPlanRevision,
									   Number fromPlanAlterations, 
									   Number fromPlanUpdateNumber,
									   String toPlanId, 
									   Number toPlanVersion, 
									   Number toPlanRevision,
									   Number toPlanAlterations, 
									   Number toPlanUpdateNumber,
									   String fromOrderId, 
									   String toOrderId, 
									   String fromAltId,
									   String toAltId, 
									   String fromType, 
									   String toType, 
									   String fromTo,
									   String alterationNumber, 
									   String changeAuthObjectId,
									   String securityGroup, 
									   List returnList,
									   Map returnMap) 
	{
		String changeIndicator;
		returnMap.put("LEVEL", NumberUtils.INTEGER_ONE);
        returnMap.put("INSTRUCTION_NAME", fromTo + " HEADER DATA");

        boolean comparePlanHeaderData = comparePlanHeaderData(fromPlanId,
                                                              fromPlanVersion,
                                                              fromPlanRevision,
                                                              fromPlanAlterations,
                                                              fromPlanUpdateNumber,
                                                              toPlanId,
                                                              toPlanVersion,
                                                              toPlanRevision,
                                                              toPlanAlterations,
                                                              toPlanUpdateNumber,
                                                              fromOrderId,
                                                              toOrderId,
                                                              fromType,
                                                              toType,
                                                              fromAltId,
                                                              toAltId);
        if (comparePlanHeaderData)
        {
            changeIndicator = "CHECK_MARK";
        }
        else
        {
            changeIndicator = "PENCIL_MARK";
        }

        returnMap.put("CHANGE_INDICATOR", changeIndicator);
        returnMap.put("ALT_NO", alterationNumber);
        returnMap.put("OBJECT_ID", changeAuthObjectId);

        // FND-14904 Meena
        String image = EMPTY;
        if(isNotEmpty(changeAuthObjectId))
        {
        	image = "Image";
        }
        returnMap.put("IMAGE", image);
        returnMap.put("PARENT_KEY", null);
        returnMap.put("CHILD_KEY", fromTo + " HEADER DATA");
        returnMap.put("PARAMS", null);
        returnMap.put("SECURITY_GROUP", securityGroup);

        if(isNotEmpty(fromType))
        {
        	returnList.add(returnMap);
        }
	}

    /**
     * Generates the Disposition Block Instructions
     * 
     * @param fromOrderId
     *            the From Order Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromStepKey
     *            the From Step Key
     * @param toOrderId
     *            the To Order Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toStepKey
     *            the To Step Key
     * @param textType
     *            the Text Type
     * @param operationNumber
     *            the Operation Number
     * @param stepNumber
     *            the Step Number
     * @param operationBlock
     *            the Operation Block
     * @param blockType
     *            the Block Type
     * @param fromAltId from Alteration ID
     * @param toAltId to Alteration ID
     * @return returns the Disposition Block Instructions
     */
    private List generateDispositionBlock(String fromOrderId,
                                          Number fromOperationKey,
                                          Number fromStepKey,
                                          String toOrderId,
                                          Number toOperationKey,
                                          Number toStepKey,
                                          String textType,
                                          String operationNumber,
                                          String stepNumber,
                                          String operationBlock,
                                          String blockType,
                                          String fromAltId,
                                          String toAltId)
    {
        List returnList = new ArrayList();

        Map returnMap = new ListOrderedMap();
        String changeIndicator = EMPTY;

        String params = "FROM_OPER_NO=" + operationNumber + ";FROM_OPER_KEY="
                + fromOperationKey + ";FROM_OPER_UPDT_NO=" + EMPTY
                + ";TO_OPER_NO=" + operationNumber + ";TO_OPER_KEY="
                + toOperationKey + ";TO_OPER_UPDT_NO=" + EMPTY
                + ";FROM_STEP_NO=" + stepNumber + ";FROM_STEP_KEY="
                + fromStepKey + ";FROM_STEP_UPDT_NO=" + EMPTY
                + ";TO_STEP_NO=" + stepNumber + ";TO_STEP_KEY=" + toStepKey
                + ";TO_STEP_UPDT_NO=" + EMPTY + ";TEXT_TYPE="
                + textType
                + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "");

        String parentKey = EMPTY;
        String childKey = EMPTY;
        int level;
        String tempTextType = DISP;

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            parentKey = "OPERATION " + operationBlock + " BLOCKS "
                    + operationNumber;
            childKey = "OPERATION " + operationBlock + " BLOCKS " + blockType
                    + " " + operationNumber;
            level = 3;
            if (equalsIgnoreCase(operationBlock, HEADER))
            {
                tempTextType = HEADER + "_"
                        + DISP;
            }
        }
        else
        {
            parentKey ="STEP " + operationNumber + stepNumber;
            childKey = "STEP " + blockType  + " "+  operationNumber  + stepNumber;
            level = 3; // FND-26100
        }

        // Selects distinct dispositions
        List dispositionList = null;
        
        if(isEmpty(fromAltId))
        {
            dispositionList = compareToolDao.selectDistinctDispositions(fromOrderId,
                                                                        fromOperationKey,
                                                                        fromStepKey,
                                                                        toOrderId,
                                                                        toOperationKey,
                                                                        toStepKey,
                                                                        tempTextType);
        }
        else
        {
            // FND-25023
            dispositionList = compareToolDao.selectDistinctDispositionsAltLog( fromOrderId, 
                                                                               fromOperationKey, 
                                                                               fromStepKey, 
                                                                               fromAltId, 
                                                                               toOrderId, 
                                                                               toOperationKey, 
                                                                               toStepKey, 
                                                                               toAltId, 
                                                                               tempTextType );
        }

        Iterator dispositionIterator = dispositionList.listIterator();

        while (dispositionIterator.hasNext())
        {
            Map dispositionMap = (Map) dispositionIterator.next();

            String dispositionTextType = (String) dispositionMap.get("TEXT_TYPE");

            boolean fromDispositionExists = false;
            if(isEmpty(fromAltId))
            {
                fromDispositionExists = compareToolDao.selectBlockExists(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         dispositionTextType);
            }
            else
            {
                // FND-25023
                fromDispositionExists = compareToolDao.selectBlockExistsAltLog(fromOrderId,
                                                                               fromOperationKey,
                                                                               fromStepKey,
                                                                               dispositionTextType,
                                                                               fromAltId);
            }

            boolean toDispositionExists = false;
            if(isEmpty(toAltId))
            {
                toDispositionExists = compareToolDao.selectBlockExists(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       dispositionTextType);
            }
            else
            {
                // FND-25023
                toDispositionExists = compareToolDao.selectBlockExistsAltLog(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             dispositionTextType,
                                                                             toAltId);
            }

            if (fromDispositionExists && toDispositionExists)
            {
                List dispositionInstructions = generateBlockHierarchy(operationNumber,
                                                                      stepNumber,
                                                                      null,
                                                                      fromOperationKey,
                                                                      null,
                                                                      fromStepKey,
                                                                      null,
                                                                      null,
                                                                      toOperationKey,
                                                                      null,
                                                                      toStepKey,
                                                                      null,
                                                                      dispositionTextType,
                                                                      dispositionTextType,
                                                                      operationBlock,
                                                                      fromOrderId,
                                                                      toOrderId,
                                                                      "ORDER",
                                                                      "ORDER",
                                                                      fromAltId,
                                                                      toAltId,
                                                                      null,  // GE-2317
                                                                      null); // GE-2317

                if (dispositionInstructions.size() > 0)
                {
                    returnList.addAll(dispositionInstructions);
                }
            }
            else
            {
                changeIndicator = EMPTY;
                boolean display = true;
                String orderId = EMPTY;
                Number operationKey = null;
                Number stepKey = null;
                String altId = EMPTY;

                if (fromDispositionExists && !toDispositionExists)
                {
                    changeIndicator = "MINUS_MARK";
                    orderId = fromOrderId;
                    operationKey = fromOperationKey;
                    stepKey = fromStepKey;
                    altId = fromAltId;
                }
                else if (!fromDispositionExists && toDispositionExists)
                {
                    changeIndicator = "PLUS_MARK";
                    orderId = toOrderId;
                    operationKey = toOperationKey;
                    stepKey = toStepKey;
                    altId = toAltId;
                }
                else if (!fromDispositionExists && !toDispositionExists)
                {
                    display = false;
                }

                if (display)
                {
                    /*
                     * returnMap = new ListOrderedMap(); returnMap.put("LEVEL",
                     * level); returnMap.put("INSTRUCTION_NAME",
                     * dispositionTextType); returnMap.put("CHANGE_INDICATOR",
                     * changeIndicator); returnMap.put("CHG_AUTH_TYPE", null);
                     * returnMap.put("ALT_NO", null);
                     * returnMap.put("CHG_AUTH_NOTES", null);
                     * returnMap.put("OBJECT_ID", null); returnMap.put("IMAGE",
                     * null); returnMap.put("PARENT_KEY", parentKey);
                     * returnMap.put("CHILD_KEY", childKey );
                     * returnMap.put("PARAMS", params );
                     * 
                     * returnList.add(returnMap);
                     */

                    List dispositionInstructions = generateSingleBlock(operationNumber,
                                                                       stepNumber,
                                                                       null,
                                                                       operationKey,
                                                                       null,
                                                                       stepKey,
                                                                       null,
                                                                       dispositionTextType,
                                                                       dispositionTextType,
                                                                       changeIndicator,
                                                                       operationBlock,
                                                                       orderId,
                                                                       altId, // FND-25023
                                                                       null,  // GE-2317
                                                                       null);   // GE-2317

                    if (dispositionInstructions.size() > 0)
                    {
                        returnList.addAll(dispositionInstructions);
                    }
                }
            }
        }// End while

        return returnList;
    }

    /**
     * Compares the Operation
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param booleanMap the Boolean Map object consists Operation state 
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap the From Standard Operation Map
     * @param toStdOperMap the To Standard Operation Map
     * @param calledFromChgAck
     * @return returns true if Operations are same returns false if Operation
     *         are not same
     */
    protected boolean compareOperation(String operationNumber ,
                                     String fromPlanId ,
                                     Number fromPlanVersion ,
                                     Number fromPlanRevision ,
                                     Number fromPlanAlterations ,
                                     Number fromOperationKey ,
                                     Number fromOperationUpdateNumber ,
                                     Number fromStepKey ,
                                     Number fromStepUpdateNumber ,
                                     String toPlanId ,
                                     Number toPlanVersion ,
                                     Number toPlanRevision ,
                                     Number toPlanAlterations ,
                                     Number toOperationKey ,
                                     Number toOperationUpdateNumber ,
                                     Number toStepKey ,
                                     Number toStepUpdateNumber ,
                                     String fromOrderId ,
                                     String toOrderId ,
                                     String fromType ,
                                     String toType , 
                                     Map booleanMap ,
                                     String fromAltId , // FND-25023
                                     String toAltId , 
                                     Map fromStdOperMap , //GE-1668
                                     Map toStdOperMap,
                                     String calledFromChgAck ) //GE-1213
    {
        boolean compare = false;

        // Compares the Operation Header Data
        boolean compareOperationHeader = compareOperationHeader(operationNumber,
                                                                fromPlanId,
                                                                fromPlanVersion,
                                                                fromPlanRevision,
                                                                fromPlanAlterations,
                                                                toPlanId,
                                                                toPlanVersion,
                                                                toPlanRevision,
                                                                toPlanAlterations,
                                                                fromOrderId,
                                                                toOrderId,
                                                                fromType,
                                                                toType, 
                                                                booleanMap,
                                                                fromAltId, // FND-25023
                                                                toAltId, 
                                                                fromStdOperMap, 
                                                                toStdOperMap,
                                                                calledFromChgAck);
        
        booleanMap.put("compareOperationHeader", compareOperationHeader);
        
        //FND-15735
        boolean compareHeaderBlocks = false;
        
//        if(compareOperationHeader)
//        {
	        // Compares the Operation Header Blocks
            // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
	        compareHeaderBlocks = compareOperationHeaderBlock(fromPlanId,
	                                                          fromPlanVersion,
	                                                          fromPlanRevision,
	                                                          fromPlanAlterations,
	                                                          fromOperationKey,
	                                                          fromOperationUpdateNumber,
	                                                          fromStepKey,
	                                                          fromStepUpdateNumber,
	                                                          toPlanId,
	                                                          toPlanVersion,
	                                                          toPlanRevision,
	                                                          toPlanAlterations,
	                                                          toOperationKey,
	                                                          toOperationUpdateNumber,
	                                                          toStepKey,
	                                                          toStepUpdateNumber,
	                                                          fromOrderId,
	                                                          toOrderId,
	                                                          fromType,
	                                                          toType, 
	                                                          booleanMap,
	                                                          fromAltId, // FND-25023
	                                                          toAltId);
	        
	        booleanMap.put("compareOperationHeaderBlock", compareHeaderBlocks);
//        }
	    //GE-516    
	    boolean compareStepBlocks = false;
	    // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
	    compareStepBlocks = compareStepBlock(fromPlanId,
                						     fromPlanVersion,
                						     fromPlanRevision,
                						     fromPlanAlterations,
                						     fromOperationKey,
                						     fromOperationUpdateNumber,
                						     fromStepKey,
                						     fromStepUpdateNumber,
                						     toPlanId,
                						     toPlanVersion,
                						     toPlanRevision,
                						     toPlanAlterations,
                						     toOperationKey,
                						     toOperationUpdateNumber,
                						     toStepKey,
                						     toStepUpdateNumber,
                						     fromOrderId,
                						     toOrderId,
                						     fromType,
                						     toType, 
                						     booleanMap,
                						     fromAltId, 
                						     toAltId);
	    
	    booleanMap.put("compareStepBlock", compareStepBlocks);
	        
        //FND-15735
        boolean compareFooterBlocks = false;
        
//        if(compareOperationHeader && compareHeaderBlocks)
//        {
	        // Compares the Operation Footer Blocks
            // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
	        compareFooterBlocks = compareMultipleBlocks(fromPlanId,
	                                                    fromOperationKey,
	                                                    fromOperationUpdateNumber,
	                                                    fromStepKey,
	                                                    fromStepUpdateNumber,
	                                                    toPlanId,
	                                                    toOperationKey,
	                                                    toOperationUpdateNumber,
	                                                    toStepKey,
	                                                    toStepUpdateNumber,
	                                                    ELLIPSIS,
	                                                    FOOTER,
	                                                    fromOrderId,
	                                                    toOrderId,
	                                                    fromType,
	                                                    toType,
	                                                    fromAltId, //FND-25023
	                                                    toAltId);
	        
	        booleanMap.put("compareMultipleBlocks_footer", compareFooterBlocks);
//        }

        if (compareOperationHeader && compareHeaderBlocks
                && compareFooterBlocks && compareStepBlocks)
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Operation Header Block
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param booleanMap the Boolean Map object consists Operation Header Block state
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true if Operations are same returns false if Operation
     *         are not same
     */
    private boolean compareOperationHeaderBlock(String fromPlanId,
                                                Number fromPlanVersion,
                                                Number fromPlanRevision,
                                                Number fromPlanAlterations,
                                                Number fromOperationKey,
                                                Number fromOperationUpdateNumber,
                                                Number fromStepKey,
                                                Number fromStepUpdateNumber,
                                                String toPlanId,
                                                Number toPlanVersion,
                                                Number toPlanRevision,
                                                Number toPlanAlterations,
                                                Number toOperationKey,
                                                Number toOperationUpdateNumber,
                                                Number toStepKey,
                                                Number toStepUpdateNumber,
                                                String fromOrderId,
                                                String toOrderId,
                                                String fromType,
                                                String toType, 
                                                Map booleanMap,
                                                String fromAltId, // FND-25023
                                                String toAltId)
    {
        boolean compare = false;
        boolean stepCompare = true;
        boolean totalStepCompare = true;

        // Compares the Operation Header Planning and QA Blocks
        // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
        boolean compareHeaderBlocks = compareMultipleBlocks(fromPlanId,
                                                            fromOperationKey,
                                                            fromOperationUpdateNumber,
                                                            fromStepKey,
                                                            fromStepUpdateNumber,
                                                            toPlanId,
                                                            toOperationKey,
                                                            toOperationUpdateNumber,
                                                            toStepKey,
                                                            toStepUpdateNumber,
                                                            ELLIPSIS,
                                                            HEADER,
                                                            fromOrderId,
                                                            toOrderId,
                                                            fromType,
                                                            toType,
                                                            fromAltId, //FND-25023
                                                            toAltId);
        
        booleanMap.put("compareMultipleBlocks_header", compareHeaderBlocks);

        // Returns true if all the blocks are same
        if (compareHeaderBlocks )
        {
            compare = true;
        }

        return compare;
    }
    
    //GE-516
    private boolean compareStepBlock(String fromPlanId,
            						 Number fromPlanVersion,
            						 Number fromPlanRevision,
            						 Number fromPlanAlterations,
            						 Number fromOperationKey,
            						 Number fromOperationUpdateNumber,
            						 Number fromStepKey,
            						 Number fromStepUpdateNumber,
            						 String toPlanId,
            						 Number toPlanVersion,
            						 Number toPlanRevision,
            						 Number toPlanAlterations,
            						 Number toOperationKey,
            						 Number toOperationUpdateNumber,
            						 Number toStepKey,
            						 Number toStepUpdateNumber,
            						 String fromOrderId,
            						 String toOrderId,
            						 String fromType,
            						 String toType, 
            						 Map booleanMap,
            						 String fromAltId, 
            						 String toAltId)
    {
    	 boolean compare = false;
      	 boolean stepCompare = true;
    	 boolean totalStepCompare= true;
         
    	 List stepNumberList = new ArrayList();
	        
         if (equalsIgnoreCase(fromType, "PLAN")
                 && equalsIgnoreCase(fromType, toType))
         {
             // Selects the Steps for both From and To Plans
             // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
         	stepNumberList = compareToolDao.selectDistinctStepNumber(fromPlanId,
                                                                     fromPlanVersion,
                                                                     fromPlanRevision,
                                                                     fromPlanAlterations,
                                                                     fromOperationKey,
                                                                     fromOperationUpdateNumber,
                                                                     toPlanId,
                                                                     toPlanVersion,
                                                                     toPlanRevision,
                                                                     toPlanAlterations,
                                                                     toOperationKey,
                                                                     toOperationUpdateNumber,
                                                                     ELLIPSIS);
         }
         else if (equalsIgnoreCase(fromType, "ORDER")
                 && equalsIgnoreCase(fromType, toType))
         {
             if(isEmpty( fromAltId ))
             {
                // Selects the Steps for both From and To Order
             	stepNumberList = compareToolDao.selectDistinctStepNumber(fromOrderId,
                                                                         fromOperationKey,
                                                                         toOrderId,
                                                                         toOperationKey,
                                                                         NumberUtils.INTEGER_MINUS_ONE);
             }
             else
             {
                 //FND-25023 Selects the Steps in Alteration for both From and To Order
                 stepNumberList = compareToolDao.selectDistinctStepNumberAltLog(fromOrderId,
                                                                                fromOperationKey,
                                                                                toOrderId,
                                                                                toOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                fromAltId,
                                                                                toAltId);
             }
         }
         else
         {
             if (equalsIgnoreCase(fromType, "PLAN")
                     && equalsIgnoreCase(toType, "ORDER"))
             {
                 // Selects the Steps for both From and To Plan and Order
             	stepNumberList = compareToolDao.selectDistinctStepNumber(fromPlanId,
                                                                             fromPlanVersion,
                                                                             fromPlanRevision,
                                                                             fromPlanAlterations,
                                                                             fromOperationKey,
                                                                             fromOperationUpdateNumber,
                                                                             ELLIPSIS,
                                                                             toOrderId,
                                                                             toOperationKey,
                                                                             NumberUtils.INTEGER_MINUS_ONE);
             }
             else if (equalsIgnoreCase(fromType, "ORDER")
                     && equalsIgnoreCase(toType, "PLAN"))
             {
                 // Selects the Steps for both From and To Plan and Order
             	stepNumberList = compareToolDao.selectDistinctStepNumber(toPlanId,
                                                                             toPlanVersion,
                                                                             toPlanRevision,
                                                                             toPlanAlterations,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             ELLIPSIS,
                                                                             fromOrderId,
                                                                             fromOperationKey,
                                                                             NumberUtils.INTEGER_MINUS_ONE);
             }
         }
	
	        Iterator stepIterator = stepNumberList.listIterator();
	
	        while (stepIterator.hasNext() /*&& stepCompare == true*/ )
	        {
	            Map stepMap = (Map) stepIterator.next();
	
	            String stepNumber = (String) stepMap.get("STEP_NO");
	            
	            boolean fromStepExists = false;
             boolean toStepExists = false;

             if (equalsIgnoreCase(fromType, "PLAN")
                     && equalsIgnoreCase(fromType, toType))
             {
                 // Checks whether Step exists in From Plan
                 fromStepExists = compareToolDao.selectStepNumberExists(fromPlanId,
                                                                        fromPlanVersion,
                                                                        fromPlanRevision,
                                                                        fromPlanAlterations,
                                                                        fromOperationKey,
                                                                        fromOperationUpdateNumber,
                                                                        stepNumber);

                 // Checks whether Step exists in To Plan
                 toStepExists = compareToolDao.selectStepNumberExists(toPlanId,
                                                                      toPlanVersion,
                                                                      toPlanRevision,
                                                                      toPlanAlterations,
                                                                      toOperationKey,
                                                                      toOperationUpdateNumber,
                                                                      stepNumber);
             }
             else if (equalsIgnoreCase(fromType, "ORDER")
                     && equalsIgnoreCase(fromType, toType))
             {
                 if(isEmpty( fromAltId ))
                 {
                     // Checks whether Step exists in From Order
                     fromStepExists = compareToolDao.selectStepNumberExists(fromOrderId,
                                                                            fromOperationKey,
                                                                            stepNumber);
                 }
                 else
                 {
                     //FND-25023 Checks whether Step exists in Alteration in From Order
                     fromStepExists = compareToolDao.selectStepNumberExistsAltLog(fromOrderId,
                                                                                  fromOperationKey,
                                                                                  stepNumber,
                                                                                  fromAltId);
                 }

                 if(isEmpty( toAltId ))
                 {
                     // Checks whether Step exists in TO Order
                     toStepExists = compareToolDao.selectStepNumberExists(toOrderId,
                                                                          toOperationKey,
                                                                          stepNumber);
                 }
                 else
                 {
                     //FND-25023 Checks whether Step exists in ALteration in TO Order
                     toStepExists = compareToolDao.selectStepNumberExistsAltLog(toOrderId,
                                                                                toOperationKey,
                                                                                stepNumber,
                                                                                toAltId);
                 }
             }
             else
             {
                 if (equalsIgnoreCase(fromType, "PLAN")
                         && equalsIgnoreCase(toType, "ORDER"))
                 {
                     // Checks whether Step exists in From Plan
                     fromStepExists = compareToolDao.selectStepNumberExists(fromPlanId,
                                                                            fromPlanVersion,
                                                                            fromPlanRevision,
                                                                            fromPlanAlterations,
                                                                            fromOperationKey,
                                                                            fromOperationUpdateNumber,
                                                                            stepNumber);

                     // Checks whether Step exists in TO Order
                     toStepExists = compareToolDao.selectStepNumberExists(toOrderId,
                                                                          toOperationKey,
                                                                          stepNumber);
                 }
                 else if (equalsIgnoreCase(fromType, "ORDER")
                         && equalsIgnoreCase(toType, "PLAN"))
                 {
                     // Checks whether Step exists in From Order
                     fromStepExists = compareToolDao.selectStepNumberExists(fromOrderId,
                                                                            fromOperationKey,
                                                                            stepNumber);

                     // Checks whether Step exists in To Plan
                     toStepExists = compareToolDao.selectStepNumberExists(toPlanId,
                                                                          toPlanVersion,
                                                                          toPlanRevision,
                                                                          toPlanAlterations,
                                                                          toOperationKey,
                                                                          toOperationUpdateNumber,
                                                                          stepNumber);
                 }
             }
	
	            // If Step exists in both the Plans
	            if (fromStepExists && toStepExists)
	            {
                 Map fromStepInfo = null;
                 Map toStepInfo = null;

                 if (equalsIgnoreCase(fromType, "PLAN")
                         && equalsIgnoreCase(fromType, toType))
                 {
                     // Selects Step information for From Plan
                     fromStepInfo = compareToolDao.selectStepInformation(fromPlanId,
                                                                         fromPlanVersion,
                                                                         fromPlanRevision,
                                                                         fromPlanAlterations,
                                                                         fromOperationKey,
                                                                         fromOperationUpdateNumber,
                                                                         stepNumber);

                     // Selects Step information for To Plan
                     toStepInfo = compareToolDao.selectStepInformation(toPlanId,
                                                                       toPlanVersion,
                                                                       toPlanRevision,
                                                                       toPlanAlterations,
                                                                       toOperationKey,
                                                                       toOperationUpdateNumber,
                                                                       stepNumber);
                 }
                 else if (equalsIgnoreCase(fromType, "ORDER")
                         && equalsIgnoreCase(fromType, toType))
                 {
                     if(isEmpty( fromAltId ))
                     {
                         // Selects Step information for From Order
                         fromStepInfo = compareToolDao.selectStepInformation(fromOrderId,
                                                                             fromOperationKey,
                                                                             stepNumber);
                     }
                     else
                     {
                         //FND-25023 Selects Step information for From Order
                         fromStepInfo = compareToolDao.selectStepInformationAltLog(fromOrderId,
                                                                                   fromOperationKey,
                                                                                   stepNumber,
                                                                                   fromAltId);
                     }

                     if(isEmpty( toAltId ))
                     {
                         // Selects Step information for To Order
                         toStepInfo = compareToolDao.selectStepInformation(toOrderId,
                                                                           toOperationKey,
                                                                           stepNumber);
                     }
                     else
                     {
                         //FND-25023 Selects Step information in Alteration for To Order
                         toStepInfo = compareToolDao.selectStepInformationAltLog(toOrderId,
                                                                                 toOperationKey,
                                                                                 stepNumber,
                                                                                 toAltId);
                     }
                 }
                 else
                 {
                     if (equalsIgnoreCase(fromType, "PLAN")
                             && equalsIgnoreCase(toType, "ORDER"))
                     {
                         // Selects Step information for From Plan
                         fromStepInfo = compareToolDao.selectStepInformation(fromPlanId,
                                                                             fromPlanVersion,
                                                                             fromPlanRevision,
                                                                             fromPlanAlterations,
                                                                             fromOperationKey,
                                                                             fromOperationUpdateNumber,
                                                                             stepNumber);

                         // Selects Step information for To Order
                         toStepInfo = compareToolDao.selectStepInformation(toOrderId,
                                                                           toOperationKey,
                                                                           stepNumber);
                     }
                     else if (equalsIgnoreCase(fromType, "ORDER")
                             && equalsIgnoreCase(toType, "PLAN"))
                     {
                         // Selects Step information for From Order
                         fromStepInfo = compareToolDao.selectStepInformation(fromOrderId,
                                                                             fromOperationKey,
                                                                             stepNumber);

                         // Selects Step information for To Plan
                         toStepInfo = compareToolDao.selectStepInformation(toPlanId,
                                                                           toPlanVersion,
                                                                           toPlanRevision,
                                                                           toPlanAlterations,
                                                                           toOperationKey,
                                                                           toOperationUpdateNumber,
                                                                           stepNumber);
                     }
                 }
	
	                Number fromStepKeyNew = (Number) fromStepInfo.get("STEP_KEY");
	                Number fromStepUpdateNumberNew = (Number) fromStepInfo.get("STEP_UPDT_NO");
	
	                Number toStepKeyNew = (Number) toStepInfo.get("STEP_KEY");
	                Number toStepUpdateNumberNew = (Number) toStepInfo.get("STEP_UPDT_NO");
	
	                // Compare Step Planning and QA blocks
	                stepCompare = compareMultipleBlocks(fromPlanId,
	                                                    fromOperationKey,
	                                                    fromOperationUpdateNumber,
	                                                    fromStepKeyNew,
	                                                    fromStepUpdateNumberNew,
	                                                    toPlanId,
	                                                    toOperationKey,
	                                                    toOperationUpdateNumber,
	                                                    toStepKeyNew,
	                                                    toStepUpdateNumberNew,
	                                                    stepNumber,
	                                                    null,
	                                                    fromOrderId,
	                                                    toOrderId,
	                                                    fromType,
	                                                    toType,
	                                                    fromAltId, //FND-25023
	                                                    toAltId);
	            }
	            else
	            {
	                stepCompare = false;
	            }
	            
             // Step Planning, QA, Disposition checking
             booleanMap.put("compareMultipleBlocks_"+stepNumber, stepCompare);
             
             if(!stepCompare)
             {
             	totalStepCompare = false;
             }
	        }
    	 
         if(totalStepCompare)
         {
        	 compare = true;
         }

    	return compare;
    }

    /**
     * Compares the Multiple Blocks
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param stepNumber
     *            the Step Number
     * @param operationHeader
     *            the Operation Header
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true if blocks are same returns false if blocks are not
     *         same
     */
    private boolean compareMultipleBlocks(String fromPlanId,
                                          Number fromOperationKey,
                                          Number fromOperationUpdateNumber,
                                          Number fromStepKey,
                                          Number fromStepUpdateNumber,
                                          String toPlanId,
                                          Number toOperationKey,
                                          Number toOperationUpdateNumber,
                                          Number toStepKey,
                                          Number toStepUpdateNumber,
                                          String stepNumber,
                                          String operationHeader,
                                          String fromOrderId,
                                          String toOrderId,
                                          String fromType,
                                          String toType,
                                          String fromAltId, // FND-25023
                                          String toAltId)
    {
        boolean compare = false;
        boolean isOperation = false;
        boolean compareDispositionBlock = true;

        if (equalsIgnoreCase(fromType, "ORDER")
                || equalsIgnoreCase(toType, "ORDER"))
        {
            String dispositionTextType = "DISP";

            if (equalsIgnoreCase(stepNumber, ELLIPSIS))
            {
                if (equalsIgnoreCase(operationHeader,
                                       HEADER))
                {
                    dispositionTextType = "HEADER_DISP";
                }
            }

            if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                compareDispositionBlock = compareBlock(null,
                                                       fromOperationKey,
                                                       null,
                                                       fromStepKey,
                                                       null,
                                                       null,
                                                       toOperationKey,
                                                       null,
                                                       toStepKey,
                                                       null,
                                                       dispositionTextType,
                                                       null,
                                                       false,
                                                       fromOrderId,
                                                       toOrderId,
                                                       fromType,
                                                       toType, 
                                                       stepNumber,
                                                       fromAltId, //FND-25023
                                                       toAltId);
            }
        }
        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
           isOperation = true;
        }
        
      //FND-27796
        boolean compareOperationDesc = false;
        
        if(compareDispositionBlock )
        {
        	 
        	  if (!(equalsIgnoreCase(operationHeader, FOOTER)))
              {
	        	compareOperationDesc = compareOperationDesc(fromPlanId,
	        												fromOperationKey,
	        												fromOperationUpdateNumber,
	        												fromStepKey,
	        												fromStepUpdateNumber,
	        												toPlanId,
	        												toOperationKey,
	        												toOperationUpdateNumber,
	        												toStepKey,
	        												toStepUpdateNumber,
	        												isOperation,
	        												fromOrderId,
	        												toOrderId,
	        												fromType,
	        												toType);
              }
        	  else
        	  {
        		  compareOperationDesc = true;
        	  } 
        }

        String textType = "PLANNING";
        String tableName = "SFFND_HTREF_STEP_TEXT";

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";
            isOperation = true;

            if (equalsIgnoreCase(operationHeader, HEADER))
            {
                textType = "HEADER_PLANNING";
            }
        }

        //FND-15735
        boolean comparePlanningBlock = false;
        
        if(compareDispositionBlock && compareOperationDesc)
        {
            // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
	        comparePlanningBlock = compareBlock(fromPlanId,
	                                            fromOperationKey,
	                                            fromOperationUpdateNumber,
	                                            fromStepKey,
	                                            fromStepUpdateNumber,
	                                            toPlanId,
	                                            toOperationKey,
	                                            toOperationUpdateNumber,
	                                            toStepKey,
	                                            toStepUpdateNumber,
	                                            textType,
	                                            tableName,
	                                            isOperation,
	                                            fromOrderId,
	                                            toOrderId,
	                                            fromType,
	                                            toType, 
	                                            stepNumber,
	                                            fromAltId, //FND-25023
                                                toAltId);
        }

        isOperation = false;
        textType = "QA";
        tableName = "SFFND_HTREF_STEP_TEXT";

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";
            isOperation = true;
            if (equalsIgnoreCase(operationHeader, HEADER))
            {
                textType = "HEADER_QA";
            }
        }

        // FND-15735
        boolean compareQABlock = false;
        
        if(comparePlanningBlock && compareDispositionBlock && compareOperationDesc )
        {
            // GE-1668 No change to Pass std Oper Map as queries are based on OPER_KEY and return Map does not contain OPER_NO
	        compareQABlock = compareBlock(fromPlanId,
	                                      fromOperationKey,
	                                      fromOperationUpdateNumber,
	                                      fromStepKey,
	                                      fromStepUpdateNumber,
	                                      toPlanId,
	                                      toOperationKey,
	                                      toOperationUpdateNumber,
	                                      toStepKey,
	                                      toStepUpdateNumber,
	                                      textType,
	                                      tableName,
	                                      isOperation,
	                                      fromOrderId,
	                                      toOrderId,
	                                      fromType,
	                                      toType, 
	                                      stepNumber,
	                                      fromAltId, //FND-25023
                                          toAltId);
        }

        if (comparePlanningBlock && compareQABlock && compareDispositionBlock && compareOperationDesc)
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Generate Block Hierarchy
     * 
     * @param operationNumber
     *            the Operation Number
     * @param stepNumber
     *            the Step Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the to Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param blockType
     *            the Block Type
     * @param operationBlock
     *            the Operation Block
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId from Alteration ID
     * @param toAltId to Alteration ID
     * @return returns the List for Block Instructions
     */
    protected List generateBlockHierarchy(String operationNumber,
                                        String stepNumber,
                                        String fromPlanId,
                                        Number fromOperationKey,
                                        Number fromOperationUpdateNumber,
                                        Number fromStepKey,
                                        Number fromStepUpdateNumber,
                                        String toPlanId,
                                        Number toOperationKey,
                                        Number toOperationUpdateNumber,
                                        Number toStepKey,
                                        Number toStepUpdateNumber,
                                        String textType,
                                        String blockType,
                                        String operationBlock,
                                        String fromOrderId,
                                        String toOrderId,
                                        String fromType,
                                        String toType,
                                        String fromAltId,
                                        String toAltId,
                                        Map fromInfoMap,  // GE-2317
                                        Map toInfoMap)    // GE-2317
    {
        Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
        Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
        if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
        }
        if(toInfoMap != null && !toInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
        }
        List returnList = new ArrayList();
        Map returnMap = new ListOrderedMap();
        String changeIndicator = EMPTY;
        String params = "FROM_OPER_NO=" + operationNumber + ";FROM_OPER_KEY="
                + fromOperationKey + ";FROM_OPER_UPDT_NO="
                + fromOperationUpdateNumber + ";TO_OPER_NO=" + operationNumber
                + ";TO_OPER_KEY=" + toOperationKey + ";TO_OPER_UPDT_NO="
                + toOperationUpdateNumber + ";FROM_STEP_NO=" + stepNumber
                + ";FROM_STEP_KEY=" + fromStepKey + ";FROM_STEP_UPDT_NO="
                + fromStepUpdateNumber + ";TO_STEP_NO=" + stepNumber
                + ";TO_STEP_KEY=" + toStepKey + ";TO_STEP_UPDT_NO="
                + toStepUpdateNumber + ";TEXT_TYPE=" + textType
                + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "")
                + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey
                + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;

        String parentKey = EMPTY;
        String childKey = EMPTY;
        boolean isOperation = false;
        String tableName = EMPTY;
        String blockTableName = EMPTY;
        int level;

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            parentKey = "OPERATION " + operationBlock + " BLOCKS "
                    + operationNumber;
            childKey = "OPERATION " + operationBlock + " BLOCKS " + blockType
                    + " " + operationNumber;
            isOperation = true;
            tableName = "SFFND_HTREF_OPER_TEXT";
            blockTableName = "SFPL_OPERATION_TEXT";
            level = 3;
        }
        else
        {
            parentKey ="STEP " + operationNumber + stepNumber;
            childKey = "STEP " + blockType  + " "+  operationNumber  + stepNumber;
            tableName = "SFFND_HTREF_STEP_TEXT";
            blockTableName = "SFPL_STEP_TEXT";
            level = 3;  // FND-26100
        }

        // Start Operation Block

        boolean fromBlockExists = false;
        boolean toBlockExists = false;

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Checks whether Block exists for From Plan Operation or not
            fromBlockExists = compareToolDao.selectBlockExists(fromPlanId,
                                                               fromOperationKey,
                                                               fromOperationUpdateNumber,
                                                               textType,
                                                               fromStepKey,
                                                               fromStepUpdateNumber,
                                                               blockTableName,
                                                               isOperation);

            // Checks whether Block exists for To Plan Operation or not
            toBlockExists = compareToolDao.selectBlockExists(toPlanId,
                                                             toOperationKey,
                                                             toOperationUpdateNumber,
                                                             textType,
                                                             toStepKey,
                                                             toStepUpdateNumber,
                                                             blockTableName,
                                                             isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Checks whether Block exists for From Order Operation or not
                fromBlockExists = compareToolDao.selectBlockExists(fromOrderId,
                                                                   fromOperationKey,
                                                                   fromStepKey,
                                                                   textType);
            }
            else
            {
                // FND-25023
                fromBlockExists = compareToolDao.selectBlockExistsAltLog(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType,
                                                                         fromAltId);
            }

            if(isEmpty(toAltId))
            {
                // Checks whether Block exists for To Order Operation or not
                toBlockExists = compareToolDao.selectBlockExists(toOrderId,
                                                                 toOperationKey,
                                                                 toStepKey,
                                                                 textType);
            }
            else
            {
                // FND-25023
                toBlockExists = compareToolDao.selectBlockExistsAltLog(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType,
                                                                       toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Checks whether Block exists for From Plan Operation or not
                fromBlockExists = compareToolDao.selectBlockExists(fromPlanId,
                                                                   fromOperationKey,
                                                                   fromOperationUpdateNumber,
                                                                   textType,
                                                                   fromStepKey,
                                                                   fromStepUpdateNumber,
                                                                   blockTableName,
                                                                   isOperation);

                // Checks whether Block exists for To Order Operation or not
                toBlockExists = compareToolDao.selectBlockExists(toOrderId,
                                                                 toOperationKey,
                                                                 toStepKey,
                                                                 textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Checks whether Block exists for From Order Operation or not
                fromBlockExists = compareToolDao.selectBlockExists(fromOrderId,
                                                                   fromOperationKey,
                                                                   fromStepKey,
                                                                   textType);

                // Checks whether Block exists for To Plan Operation or not
                toBlockExists = compareToolDao.selectBlockExists(toPlanId,
                                                                 toOperationKey,
                                                                 toOperationUpdateNumber,
                                                                 textType,
                                                                 toStepKey,
                                                                 toStepUpdateNumber,
                                                                 blockTableName,
                                                                 isOperation);
            }
        }

        if (fromBlockExists && toBlockExists)
        {
            boolean compareOperationBlock = compareBlock(fromPlanId,
                                                         fromOperationKey,
                                                         fromOperationUpdateNumber,
                                                         fromStepKey,
                                                         fromStepUpdateNumber,
                                                         toPlanId,
                                                         toOperationKey,
                                                         toOperationUpdateNumber,
                                                         toStepKey,
                                                         toStepUpdateNumber,
                                                         textType,
                                                         tableName,
                                                         isOperation,
                                                         fromOrderId,
                                                         toOrderId,
                                                         fromType,
                                                         toType, 
                                                         stepNumber,
                                                         fromAltId, //FND-25023
                                                         toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", blockType);
            changeIndicator = EMPTY;

            if (compareOperationBlock)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", childKey);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromBlockExists && !toBlockExists)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromBlockExists && toBlockExists)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromBlockExists && !toBlockExists)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", blockType);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", childKey);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        level = level + 1;
        String instructionName = EMPTY;

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            parentKey = "OPERATION " + operationBlock + " BLOCKS " + blockType
                    + " " + operationNumber;
        }
        else
        {
            parentKey = "STEP " + blockType + " " + operationNumber + stepNumber;
        }

        // Start Operation Block - Text
        instructionName = "TEXT";

        List fromOperationStandardText = new ArrayList();
        List toOperationStandardText = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
        	// FND-28458
            // Selects the Operation Standard Text for From Plan Operation
            fromOperationStandardText = compareToolDao.selectOperationTextObjects(fromPlanId,
                                                                                  fromOperationKey,
                                                                                  fromOperationUpdateNumber,
                                                                                  textType,
                                                                                  fromStepKey,
                                                                                  fromStepUpdateNumber,
                                                                                  tableName,
                                                                                  isOperation);

            // FND-28458
            // Selects the Operation Standard Text for To Plan Operation
            toOperationStandardText = compareToolDao.selectOperationTextObjects(toPlanId,
                                                                                toOperationKey,
                                                                                toOperationUpdateNumber,
                                                                                textType,
                                                                                toStepKey,
                                                                                toStepUpdateNumber,
                                                                                tableName,
                                                                                isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
            	// FND-28458
                // Selects the Operation Standard Text for From Order Operation
                fromOperationStandardText = compareToolDao.selectOperationTextObjects(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      textType);
            }
            else
            {
            	// FND-28458
                // FND-25023
                // Selects the Operation Standard Text for From Order Operation Alteration Log
                fromOperationStandardText = compareToolDao.selectOperationTextObjectsAltLog(fromOrderId,
                                                                                            fromOperationKey,
                                                                                            fromStepKey,
                                                                                            textType,
                                                                                            fromAltId);
            }

            if(isEmpty(toAltId))
            {
            	// FND-28458
                // Selects the Operation Standard Text for To Order Operation
                toOperationStandardText = compareToolDao.selectOperationTextObjects(toOrderId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    textType);
            }
            else
            {
            	// FND-28458
                // FND-25023
                // Selects the Operation Standard Text for To Order Operation Alteration Log
                toOperationStandardText = compareToolDao.selectOperationTextObjectsAltLog(toOrderId,
                                                                                          toOperationKey,
                                                                                          toStepKey,
                                                                                          textType,
                                                                                          toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
            	// FND-28458
                // Selects the Operation Standard Text for From Plan Operation
                fromOperationStandardText = compareToolDao.selectOperationTextObjects(fromPlanId,
                                                                                      fromOperationKey,
                                                                                      fromOperationUpdateNumber,
                                                                                      textType,
                                                                                      fromStepKey,
                                                                                      fromStepUpdateNumber,
                                                                                      tableName,
                                                                                      isOperation);
                // FND-28458
                // Selects the Operation Standard Text for To Order Operation
                toOperationStandardText = compareToolDao.selectOperationTextObjects(toOrderId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
            	// FND-28458
                // Selects the Operation Standard Text for From Order Operation
                fromOperationStandardText = compareToolDao.selectOperationTextObjects(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      textType);
                
                // FND-28458
                // Selects the Operation Standard Text for To Plan Operation
                toOperationStandardText = compareToolDao.selectOperationTextObjects(toPlanId,
                                                                                    toOperationKey,
                                                                                    toOperationUpdateNumber,
                                                                                    textType,
                                                                                    toStepKey,
                                                                                    toStepUpdateNumber,
                                                                                    tableName,
                                                                                    isOperation);
            }
        }

        boolean fromStandardText = fromOperationStandardText.size() > 0;
        boolean toStandardText = toOperationStandardText.size() > 0;

        if (fromStandardText && toStandardText)
        {
            // Compare Operation Standard Text
            /*boolean compareOperationBlockText = compareOperationStandardText(fromPlanId,
                                                                             fromOperationKey,
                                                                             fromOperationUpdateNumber,
                                                                             fromStepKey,
                                                                             fromStepUpdateNumber,
                                                                             toPlanId,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             toStepKey,
                                                                             toStepUpdateNumber,
                                                                             textType,
                                                                             "STDTEXT",
                                                                             tableName,
                                                                             isOperation,
                                                                             fromOrderId,
                                                                             toOrderId,
                                                                             fromType,
                                                                             toType,
                                                                             fromAltId, //FND-25023
                                                                             toAltId);*/
        	
        	boolean compareOperationBlockText = compareURLParams(fromOperationStandardText, toOperationStandardText);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockText)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromStandardText && !toStandardText)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromStandardText && toStandardText)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromStandardText && !toStandardText)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        // End Operation Block - Text


        // FND-22719 Image List Control
        instructionName = "IMAGE LIST";
        
        List fromOperationImageList = new ArrayList();
        List toOperationImageList = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Images for From Plan
            fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(fromPlanId,
						                                                                      fromOperationKey,
						                                                                      fromOperationUpdateNumber,
						                                                                      fromStepKey,
						                                                                      fromStepUpdateNumber,
						                                                                      textType,
						                                                                      tableName,
						                                                                      isOperation );

            // Selects the Operation Images for To Plan
            toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(toPlanId,
						                                                                    toOperationKey,
						                                                                    toOperationUpdateNumber,
						                                                                    toStepKey,
						                                                                    toStepUpdateNumber,
						                                                                    textType,
						                                                                    tableName,
						                                                                    isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Images for From Order
                fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
    						                                                                      fromOperationKey,
    						                                                                      fromStepKey,
    						                                                                      textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Images for From Order Alteration Log
                fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControlAltLog(fromOrderId,
                                                                                                        fromOperationKey,
                                                                                                        fromStepKey,
                                                                                                        textType,
                                                                                                        fromAltId);
            }
            
            
            if(isEmpty(toAltId))
            {
                // Selects the Operation Images for TO Order
                toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
    						                                                                    toOperationKey,
    						                                                                    toStepKey,
    						                                                                    textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Images for To Order Alteration Log
                toOperationImageList = compareToolDao.selectOperationImagesfromImageListControlAltLog(toOrderId,
                                                                                                      toOperationKey,
                                                                                                      toStepKey,
                                                                                                      textType,
                                                                                                      toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Images for From Plan
                fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(fromPlanId,
						                                                                          fromOperationKey,
						                                                                          fromOperationUpdateNumber,
						                                                                          fromStepKey,
						                                                                          fromStepUpdateNumber,
						                                                                          textType,
						                                                                          tableName,
						                                                                          isOperation);

                // Selects the Operation Images for TO Order
                toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
						                                                                        toOperationKey,
						                                                                        toStepKey,
						                                                                        textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Images for From Order
                fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
						                                                                          fromOperationKey,
						                                                                          fromStepKey,
						                                                                          textType);

                // Selects the Operation Images for To Plan
                toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl(toPlanId,
						                                                                        toOperationKey,
						                                                                        toOperationUpdateNumber,
						                                                                        toStepKey,
						                                                                        toStepUpdateNumber,
						                                                                        textType,
						                                                                        tableName,
						                                                                        isOperation);
            }
        }

        boolean fromImageList = fromOperationImageList.size() > 0;
        boolean toImageList = toOperationImageList.size() > 0;

        if (fromImageList && toImageList)
        {
            boolean compareOperationBlockImageList = compareOperationImageList(fromPlanId,
		                                                                       fromOperationKey,
		                                                                       fromOperationUpdateNumber,
		                                                                       fromStepKey,
		                                                                       fromStepUpdateNumber,
		                                                                       toPlanId,
		                                                                       toOperationKey,
		                                                                       toOperationUpdateNumber,
		                                                                       toStepKey,
		                                                                       toStepUpdateNumber,
		                                                                       textType,
		                                                                       tableName,
		                                                                       isOperation,
		                                                                       fromOrderId,
		                                                                       toOrderId,
		                                                                       fromType,
		                                                                       toType,
		                                                                       fromAltId, //FND-25023
		                                                                       toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockImageList)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromImageList && !toImageList)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromImageList && toImageList)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromImageList && !toImageList)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }
        // End Operation Block - Illustration

        // Start Operation Block - Parametric Data Collection

        instructionName = "PARAMETER DC";
        List fromOperationDataCollection = new ArrayList();
        List toOperationDataCollection = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Data Collection for From Plan
            fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                       fromOperationKey,
                                                                                       fromStepKey,
                                                                                       fromStepUpdateNumber,
                                                                                       fromOperationUpdateNumber,
                                                                                       textType,
                                                                                       tableName,
                                                                                       isOperation,
                                                                                       null); // GE-1353

            // Selects the Operation Data Collection for To Plan
            toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                     toOperationKey,
                                                                                     toStepKey,
                                                                                     toStepUpdateNumber,
                                                                                     toOperationUpdateNumber,
                                                                                     textType,
                                                                                     tableName,
                                                                                     isOperation,
                                                                                     null); // GE-1353
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           null); // GE-1353
            }
            else
            {
                // FND-25023
                // Selects the Operation Data Collection for From Order Alteration Log
                fromOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                 fromOperationKey,
                                                                                                 fromStepKey,
                                                                                                 textType,
                                                                                                 fromAltId,
                                                                                                 null); // GE-1353
            }

            if(isEmpty(toAltId))
            {
                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         null); // GE-1353
            }
            else
            {
                // FND-25023
                // Selects the Operation Data Collection for To Order Alteration Log
                toOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                               toOperationKey,
                                                                                               toStepKey,
                                                                                               textType,
                                                                                               toAltId,
                                                                                               null); // GE-1353
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Data Collection for From Plan
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           fromStepUpdateNumber,
                                                                                           fromOperationUpdateNumber,
                                                                                           textType,
                                                                                           tableName,
                                                                                           isOperation,
                                                                                           null); // GE-1353

                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         null); // GE-1353
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           null); // GE-1353

                // Selects the Operation Data Collection for To Plan
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         toStepUpdateNumber,
                                                                                         toOperationUpdateNumber,
                                                                                         textType,
                                                                                         tableName,
                                                                                         isOperation,
                                                                                         null); // GE-1353
            }
        }

        boolean fromDataCollection = fromOperationDataCollection.size() > 0;
        boolean toDataCollection = toOperationDataCollection.size() > 0;

        if (fromDataCollection && toDataCollection)
        {
            // Compare Operation Data Collection
            boolean compareOperationBlockDC = compareOperationDataCollection(fromPlanId,
                                                                             fromOperationKey,
                                                                             fromOperationUpdateNumber,
                                                                             fromStepKey,
                                                                             fromStepUpdateNumber,
                                                                             toPlanId,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             toStepKey,
                                                                             toStepUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             isOperation,
                                                                             fromOrderId,
                                                                             toOrderId,
                                                                             fromType,
                                                                             toType,
                                                                             fromAltId, //FND-25023
                                                                             toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockDC)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromDataCollection && !toDataCollection)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromDataCollection && toDataCollection)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromDataCollection && !toDataCollection)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        // End Operation Block - Parametric Data Collection
        
        // GE-1353 Start Operation Block - Calculated Data Collection
        
        generateBlockHierarchyForCDC( fromPlanId, 
                                       fromOperationKey,
                                       fromOperationUpdateNumber, 
                                       fromStepKey, 
                                       fromStepUpdateNumber,
                                       toPlanId, 
                                       toOperationKey, 
                                       toOperationUpdateNumber, 
                                       toStepKey,
                                       toStepUpdateNumber, 
                                       textType, 
                                       fromOrderId, 
                                       toOrderId, 
                                       fromType,
                                       toType, 
                                       fromAltId, 
                                       toAltId, 
                                       returnList, 
                                       params, 
                                       parentKey,
                                       isOperation, 
                                       tableName, 
                                       level );

        // GE-1353 End Operation Block - Calculated Data Collection

        // Start Operation Block - Tool Data Collection

        instructionName = "TOOL DC";

        List fromOperationTools = new ArrayList();
        List toOperationTools = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Tools for From Plan
            fromOperationTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                     fromOperationKey,
                                                                     fromStepKey,
                                                                     fromStepUpdateNumber,
                                                                     fromOperationUpdateNumber,
                                                                     textType,
                                                                     tableName,
                                                                     isOperation);

            // Selects the Operation Tools for To Plan
            toOperationTools = compareToolDao.selectOperationTools(toPlanId,
                                                                   toOperationKey,
                                                                   toStepKey,
                                                                   toStepUpdateNumber,
                                                                   toOperationUpdateNumber,
                                                                   textType,
                                                                   tableName,
                                                                   isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Tools for From Order
                fromOperationTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Tools for From Order Alteration Log
                fromOperationTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                               fromOperationKey,
                                                                               fromStepKey,
                                                                               textType,
                                                                               fromAltId);
            }

            if(isEmpty(toAltId))
            {
                // Selects the Operation Tools for TO Order
                toOperationTools = compareToolDao.selectOperationTools(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Tools for To Order Alteration Log
                toOperationTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             textType,
                                                                             toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Tools for From Plan
                fromOperationTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

                // Selects the Operation Tools for TO Order
                toOperationTools = compareToolDao.selectOperationTools(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Tools for From Order
                fromOperationTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);

                // Selects the Operation Tools for To Plan
                toOperationTools = compareToolDao.selectOperationTools(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
            }
        }

        boolean fromTools = fromOperationTools.size() > 0;
        boolean toTools = toOperationTools.size() > 0;

        if (fromTools && toTools)
        {
            boolean compareOperationBlockTool = compareOperationTools(fromPlanId,
                                                                      fromOperationKey,
                                                                      fromStepKey,
                                                                      fromStepUpdateNumber,
                                                                      fromOperationUpdateNumber,
                                                                      toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      isOperation,
                                                                      fromOrderId,
                                                                      toOrderId,
                                                                      fromType,
                                                                      toType,
                                                                      fromAltId, //FND-25023
                                                                      toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockTool)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromTools && !toTools)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromTools && toTools)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromTools && !toTools)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        // End Operation Block - Tool Data Collection

        // Start Operation Block - Part Data Collection

        instructionName = "PART DC";

        List fromOperationParts = new ArrayList();
        List toOperationParts = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Parts for From Plan
            fromOperationParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                     fromOperationKey,
                                                                     fromStepKey,
                                                                     fromStepUpdateNumber,
                                                                     fromOperationUpdateNumber,
                                                                     textType,
                                                                     tableName,
                                                                     isOperation);

            // Selects the Operation Parts for To Plan
            toOperationParts = compareToolDao.selectOperationParts(toPlanId,
                                                                   toOperationKey,
                                                                   toStepKey,
                                                                   toStepUpdateNumber,
                                                                   toOperationUpdateNumber,
                                                                   textType,
                                                                   tableName,
                                                                   isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Parts for From Order
                fromOperationParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Parts for From Order Alteration Log
                fromOperationParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                               fromOperationKey,
                                                                               fromStepKey,
                                                                               textType,
                                                                               fromAltId);
            }

            if(isEmpty(toAltId))
            {
                // Selects the Operation Parts for TO Order
                toOperationParts = compareToolDao.selectOperationParts(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Parts for To Order Alteration Log
                toOperationParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             textType,
                                                                             toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Parts for From Plan
                fromOperationParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

                // Selects the Operation Parts for TO Order
                toOperationParts = compareToolDao.selectOperationParts(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Parts for From Order
                fromOperationParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);

                // Selects the Operation Parts for To Plan
                toOperationParts = compareToolDao.selectOperationParts(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
            }
        }

        boolean fromParts = fromOperationParts.size() > 0;
        boolean toParts = toOperationParts.size() > 0;

        if (fromParts && toParts)
        {
            boolean compareOperationBlockPart = compareOperationParts(fromPlanId,
                                                                      fromOperationKey,
                                                                      fromStepKey,
                                                                      fromStepUpdateNumber,
                                                                      fromOperationUpdateNumber,
                                                                      toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      isOperation,
                                                                      fromOrderId,
                                                                      toOrderId,
                                                                      fromType,
                                                                      toType,
                                                                      fromAltId, //FND-25023
                                                                      toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockPart)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromParts && !toParts)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromParts && toParts)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromParts && !toParts)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        // End Operation Block - Part Data Collection

        // Start Operation Block - Buyoff

        instructionName = "BUYOFF";

        List fromOperationBuyoffs = new ArrayList();
        List toOperationBuyoffs = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Buyoffs for From Plan
            fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

            // Selects the Operation Buyoffs for To Plan
            toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Buyoffs for From Order
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Buyoffs for From Order Alteration Log
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                   fromOperationKey,
                                                                                   fromStepKey,
                                                                                   textType,
                                                                                   fromAltId);
            }

            if(isEmpty(toAltId))
            {
                // Selects the Operation Buyoffs for TO Order
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           textType);
            }
            else
            {
                // FND-25023
                // Selects the Operation Buyoffs for To Order Alteration Log
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                 toOperationKey,
                                                                                 toStepKey,
                                                                                 textType,
                                                                                 toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Buyoffs for From Plan
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             fromStepUpdateNumber,
                                                                             fromOperationUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             isOperation);

                // Selects the Operation Buyoffs for TO Order
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Buyoffs for From Order
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             textType);

                // Selects the Operation Buyoffs for To Plan
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           toStepUpdateNumber,
                                                                           toOperationUpdateNumber,
                                                                           textType,
                                                                           tableName,
                                                                           isOperation);
            }
        }

        boolean fromBuyoffs = fromOperationBuyoffs.size() > 0;
        boolean toBuyoffs = toOperationBuyoffs.size() > 0;

        if (fromBuyoffs && toBuyoffs)
        {
            boolean compareOperationBlockBuyoff = compareOperationBuyoffs(fromPlanId,
                                                                          fromOperationKey,
                                                                          fromStepKey,
                                                                          fromStepUpdateNumber,
                                                                          fromOperationUpdateNumber,
                                                                          toPlanId,
                                                                          toOperationKey,
                                                                          toStepKey,
                                                                          toStepUpdateNumber,
                                                                          toOperationUpdateNumber,
                                                                          textType,
                                                                          tableName,
                                                                          isOperation,
                                                                          fromOrderId,
                                                                          toOrderId,
                                                                          fromType,
                                                                          toType,
                                                                          fromAltId, //FND-25023
                                                                          toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockBuyoff)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromBuyoffs && !toBuyoffs)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromBuyoffs && toBuyoffs)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromBuyoffs && !toBuyoffs)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }

        // End Operation Block - Buyoff

        // End Operation Block

        return returnList;
    }

    /**
     * 
     * @param fromPlanId
     * @param fromOperationKey
     * @param fromOperationUpdateNumber
     * @param fromStepKey
     * @param fromStepUpdateNumber
     * @param toPlanId
     * @param toOperationKey
     * @param toOperationUpdateNumber
     * @param toStepKey
     * @param toStepUpdateNumber
     * @param textType
     * @param fromOrderId
     * @param toOrderId
     * @param fromType
     * @param toType
     * @param fromAltId
     * @param toAltId
     * @param returnList
     * @param params
     * @param parentKey
     * @param isOperation
     * @param tableName
     * @param level
     */
    protected void generateBlockHierarchyForCDC ( String fromPlanId,
                                                  Number fromOperationKey,
                                                  Number fromOperationUpdateNumber,
                                                  Number fromStepKey,
                                                  Number fromStepUpdateNumber,
                                                  String toPlanId,
                                                  Number toOperationKey,
                                                  Number toOperationUpdateNumber,
                                                  Number toStepKey,
                                                  Number toStepUpdateNumber,
                                                  String textType,
                                                  String fromOrderId,
                                                  String toOrderId,
                                                  String fromType,
                                                  String toType,
                                                  String fromAltId,
                                                  String toAltId,
                                                  List returnList,
                                                  String params,
                                                  String parentKey,
                                                  boolean isOperation,
                                                  String tableName,
                                                  int level )
    {
        Map returnMap;
        String changeIndicator;
        String instructionName;
        // GE-1353 Start Operation Block - Calculated Data Collection

        instructionName = "CALCULATED DC INFO";
        List fromOperationCalcDataCollection = new ArrayList();
        List toOperationCalcDataCollection = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Data Collection for From Plan
            fromOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           fromStepUpdateNumber,
                                                                                           fromOperationUpdateNumber,
                                                                                           textType,
                                                                                           tableName,
                                                                                           isOperation,
                                                                                           CALCULATED_DC); // GE-1353

            // Selects the Operation Data Collection for To Plan
            toOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         toStepUpdateNumber,
                                                                                         toOperationUpdateNumber,
                                                                                         textType,
                                                                                         tableName,
                                                                                         isOperation,
                                                                                         CALCULATED_DC); // GE-1353
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                               fromOperationKey,
                                                                                               fromStepKey,
                                                                                               textType,
                                                                                               CALCULATED_DC); // GE-1353
            }
            else
            {
                // FND-25023
                // Selects the Operation Data Collection for From Order Alteration Log
                fromOperationCalcDataCollection = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                     fromOperationKey,
                                                                                                     fromStepKey,
                                                                                                     textType,
                                                                                                     fromAltId,
                                                                                                     CALCULATED_DC); // GE-1353
            }

            if(isEmpty(toAltId))
            {
                // Selects the Operation Data Collection for To Order
                toOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                             toOperationKey,
                                                                                             toStepKey,
                                                                                             textType,
                                                                                             CALCULATED_DC); // GE-1353
            }
            else
            {
                // FND-25023
                // Selects the Operation Data Collection for To Order Alteration Log
                toOperationCalcDataCollection = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                                   toOperationKey,
                                                                                                   toStepKey,
                                                                                                   textType,
                                                                                                   toAltId,
                                                                                                   CALCULATED_DC); // GE-1353
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Data Collection for From Plan
                fromOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                               fromOperationKey,
                                                                                               fromStepKey,
                                                                                               fromStepUpdateNumber,
                                                                                               fromOperationUpdateNumber,
                                                                                               textType,
                                                                                               tableName,
                                                                                               isOperation,
                                                                                               CALCULATED_DC); // GE-1353

                // Selects the Operation Data Collection for To Order
                toOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                             toOperationKey,
                                                                                             toStepKey,
                                                                                             textType,
                                                                                             CALCULATED_DC); // GE-1353
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                               fromOperationKey,
                                                                                               fromStepKey,
                                                                                               textType,
                                                                                               CALCULATED_DC); // GE-1353

                // Selects the Operation Data Collection for To Plan
                toOperationCalcDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                             toOperationKey,
                                                                                             toStepKey,
                                                                                             toStepUpdateNumber,
                                                                                             toOperationUpdateNumber,
                                                                                             textType,
                                                                                             tableName,
                                                                                             isOperation,
                                                                                             CALCULATED_DC); // GE-1353
            }
        }

        boolean fromCalcDataCollection = fromOperationCalcDataCollection.size() > 0;
        boolean toCalcDataCollection = toOperationCalcDataCollection.size() > 0;

        if (fromCalcDataCollection && toCalcDataCollection)
        {
            // Compare Operation Data Collection
            boolean compareOperationBlockDC = compareOperationDataCollection(fromPlanId,
                                                                             fromOperationKey,
                                                                             fromOperationUpdateNumber,
                                                                             fromStepKey,
                                                                             fromStepUpdateNumber,
                                                                             toPlanId,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             toStepKey,
                                                                             toStepUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             isOperation,
                                                                             fromOrderId,
                                                                             toOrderId,
                                                                             fromType,
                                                                             toType,
                                                                             fromAltId, //FND-25023
                                                                             toAltId);

            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            changeIndicator = EMPTY;

            if (compareOperationBlockDC)
            {
                changeIndicator = "CHECK_MARK";
            }
            else
            {
                changeIndicator = "PENCIL_MARK";
            }
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        else
        {
            changeIndicator = EMPTY;
            boolean display = true;

            if (fromCalcDataCollection && !toCalcDataCollection)
            {
                changeIndicator = "MINUS_MARK";
            }
            else if (!fromCalcDataCollection && toCalcDataCollection)
            {
                changeIndicator = "PLUS_MARK";
            }
            else if (!fromCalcDataCollection && !toCalcDataCollection)
            {
                display = false;
            }

            if (display)
            {
                returnMap = new ListOrderedMap();
                returnMap.put("LEVEL", level);
                returnMap.put("INSTRUCTION_NAME", instructionName);
                returnMap.put("CHANGE_INDICATOR", changeIndicator);
                returnMap.put("ALT_NO", null);
                returnMap.put("OBJECT_ID", null);
                returnMap.put("IMAGE", null);
                returnMap.put("PARENT_KEY", parentKey);
                returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
                returnMap.put("PARAMS", params);

                returnList.add(returnMap);
            }
        }
    }

    /**
     * Generates the Single Operation or Step block
     * 
     * @param operationNumber
     *            the Operation Number
     * @param stepNumber
     *            the Step Number
     * @param planId
     *            the Plan Id
     * @param operationKey
     *            the Operation Key
     * @param operationUpdateNumber
     *            the Operation Update Number
     * @param stepKey
     *            the Step Key
     * @param stepUpdateNumber
     *            the Step Update Number
     * @param textType
     *            the Text Type
     * @param blockType
     *            the Block Type
     * @param changeIndicator
     *            the Change Indicator
     * @param operationBlock
     *            the Operation Block
     * @param orderId
     *            the Order Id
     * @param altId 
     *            the Alteration Id
     * @return returns the List of elements to be displayed in the block
     */
    protected List generateSingleBlock(String operationNumber,
                                     String stepNumber,
                                     String planId,
                                     Number operationKey,
                                     Number operationUpdateNumber,
                                     Number stepKey,
                                     Number stepUpdateNumber,
                                     String textType,
                                     String blockType,
                                     String changeIndicator,
                                     String operationBlock,
                                     String orderId,
                                     String altId, // FND-25023
                                     Map fromInfoMap,
                                     Map toInfoMap) 
    {
        List returnList = new ArrayList();
        Map returnMap = null;

        String parentKey = EMPTY;
        String childKey = EMPTY;
        boolean isOperation = false;
        String tableName = EMPTY;
        String blockTableName = EMPTY;
        int level;

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            parentKey = "OPERATION " + operationBlock + " BLOCKS "
                    + operationNumber;
            childKey = "OPERATION " + operationBlock + " BLOCKS " + blockType
                    + " " + operationNumber;
        	
        	isOperation = true;
            tableName = "SFFND_HTREF_OPER_TEXT";
            blockTableName = "SFPL_OPERATION_TEXT";
            level = 3;
        }
        else
        {
        	parentKey ="STEP " + operationNumber + stepNumber;
        	childKey = "STEP " + blockType + " " + operationNumber  + stepNumber;
        	
            tableName = "SFFND_HTREF_STEP_TEXT";
            blockTableName = "SFPL_STEP_TEXT";
            level = 3; // FND-26100
        }

        String params = EMPTY;
        
        // GE-2317
        Number fromScopeOperKey = NumberUtils.INTEGER_ZERO;
        Number toScopeOperKey = NumberUtils.INTEGER_ZERO;
        String fromMainOrStdOperNo = operationNumber;
        String toMainOrStdOperNo = operationNumber;
        if(fromInfoMap != null && !fromInfoMap.isEmpty())
        {
            fromScopeOperKey = (Number)fromInfoMap.get( "FROM_SCOPE_OPER_KEY" );
            fromMainOrStdOperNo = (String)fromInfoMap.get( "FROM_OPER_NO" );
        }
        if(toInfoMap != null && !toInfoMap.isEmpty())
        {
            toScopeOperKey = (Number)toInfoMap.get( "TO_SCOPE_OPER_KEY" );
            toMainOrStdOperNo = (String)toInfoMap.get( "TO_OPER_NO" );
        }

        if (equalsIgnoreCase(changeIndicator, "MINUS_MARK"))
        {
            params = "FROM_OPER_NO=" + fromMainOrStdOperNo + ";FROM_OPER_KEY="  // GE-2317
                    + operationKey + ";FROM_OPER_UPDT_NO="
                    + operationUpdateNumber + ";TO_OPER_NO=" + ";TO_OPER_KEY="
                    + ";TO_OPER_UPDT_NO=" + ";FROM_STEP_NO=" + stepNumber
                    + ";FROM_STEP_KEY=" + stepKey + ";FROM_STEP_UPDT_NO="
                    + stepUpdateNumber + ";TO_STEP_NO=" + ";TO_STEP_KEY="
                    + ";TO_STEP_UPDT_NO=" + ";TEXT_TYPE=" + textType
                    + ";COMPLETE_ALT_ID=" + defaultIfEmpty(altId, "")
                    + ";FROM_SCOPE_OPER_KEY=" + fromScopeOperKey  // GE-2317
                    + ";TO_SCOPE_OPER_KEY=";  // GE-2317
        }
        else
        {
            params = "FROM_OPER_NO=" + ";FROM_OPER_KEY="
                    + ";FROM_OPER_UPDT_NO=" + ";TO_OPER_NO=" + toMainOrStdOperNo  // GE-2317
                    + ";TO_OPER_KEY=" + operationKey + ";TO_OPER_UPDT_NO="
                    + operationUpdateNumber + ";FROM_STEP_NO="
                    + ";FROM_STEP_KEY=" + ";FROM_STEP_UPDT_NO="
                    + ";TO_STEP_NO=" + stepNumber + ";TO_STEP_KEY=" + stepKey
                    + ";TO_STEP_UPDT_NO=" + stepUpdateNumber + ";TEXT_TYPE="
                    + textType
                    + ";COMPLETE_ALT_ID=" + defaultIfEmpty(altId, "")
                    + ";FROM_SCOPE_OPER_KEY="  // GE-2317
                    + ";TO_SCOPE_OPER_KEY=" + toScopeOperKey;  // GE-2317
        }

        boolean blockExists = false;

        if (isEmpty(orderId))
        {
            // Checks whether Block exists for Plan Operation or not
            blockExists = compareToolDao.selectBlockExists(planId,
                                                           operationKey,
                                                           operationUpdateNumber,
                                                           textType,
                                                           stepKey,
                                                           stepUpdateNumber,
                                                           blockTableName,
                                                           isOperation);
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Checks whether Block exists for Order Operation or not
                blockExists = compareToolDao.selectBlockExists(orderId,
                                                               operationKey,
                                                               stepKey,
                                                               textType);
            }
            else
            {
                //FND-25023 Checks whether Block exists for Order Operation in Alteration or not
                blockExists = compareToolDao.selectBlockExistsAltLog(orderId,
                                                                     operationKey,
                                                                     stepKey,
                                                                     textType,
                                                                     altId);
            }
        }

        if (blockExists)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", blockType);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", childKey);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        level = level + 1;

        if (equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            parentKey = "OPERATION " + operationBlock + " BLOCKS " + blockType
                    + " " + operationNumber;
        }
        else
        {
            parentKey = "STEP " + blockType + " "  + operationNumber + stepNumber;  //FND-26100
        }

        String instructionName = "TEXT";

        List operationStandardText = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Standard Text for From Plan Operation
            operationStandardText = compareToolDao.selectOperationStandardText(planId,
                                                                               operationKey,
                                                                               operationUpdateNumber,
                                                                               textType,
                                                                               "STDTEXT",
                                                                               stepKey,
                                                                               stepUpdateNumber,
                                                                               tableName,
                                                                               isOperation);
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Selects the Operation Standard Text for Order Operation
                operationStandardText = compareToolDao.selectOperationStandardText(orderId,
                                                                                   operationKey,
                                                                                   stepKey,
                                                                                   textType,
                                                                                   "STDTEXT");
            }
            else
            {
                //FND-25023 Selects the Operation Standard Text in Alteration for Order Operation
                operationStandardText = compareToolDao.selectOperationStandardTextAltLog(orderId,
                                                                                         operationKey,
                                                                                         stepKey,
                                                                                         textType,
                                                                                         "STDTEXT",
                                                                                         altId);
            }
        }

        if (operationStandardText.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "ILLUSTRATION";

        List operationIllustration = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Illustrations for From Plan
            operationIllustration = compareToolDao.selectOperationIllustrations(planId,
                                                                                operationKey,
                                                                                operationUpdateNumber,
                                                                                textType,
                                                                                "SLIDE",
                                                                                stepKey,
                                                                                stepUpdateNumber,
                                                                                tableName,
                                                                                isOperation);
        }
        else
        {
            // Selects the Operation Illustrations for Order
            operationIllustration = compareToolDao.selectOperationIllustrations(orderId,
                                                                                operationKey,
                                                                                stepKey,
                                                                                textType,
                                                                                "SLIDE");
        }

        if (operationIllustration.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }
        
        // Image - List
        // FND-25023
        instructionName = "IMAGE LIST"; 

        List operationImageList = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Image List for From Plan
            operationImageList = compareToolDao.selectOperationImagesfromImageListControl( planId, 
                                                                                           operationKey, 
                                                                                           operationUpdateNumber, 
                                                                                           stepKey, 
                                                                                           stepUpdateNumber, 
                                                                                           textType, 
                                                                                           tableName, 
                                                                                           isOperation );
        }
        else
        {            
            if(isEmpty( altId ))
            {
                // Selects the Operation Images for From Order
                operationImageList = compareToolDao.selectOperationImagesfromImageListControl(orderId,
                                                                                              operationKey,
                                                                                              stepKey,
                                                                                              textType);
            }
            else
            {
                //FND-25023 Selects the Operation Images for From Order in Alteration
                operationImageList = compareToolDao.selectOperationImagesfromImageListControlAltLog(orderId,
                                                                                                    operationKey,
                                                                                                    stepKey,
                                                                                                    textType,
                                                                                                    altId);
            }
        }

        if (operationImageList.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "PARAMETER DC";

        List operationDataCollection = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Data Collection for From Plan
            operationDataCollection = compareToolDao.selectOperationDataCollection(planId,
                                                                                   operationKey,
                                                                                   stepKey,
                                                                                   stepUpdateNumber,
                                                                                   operationUpdateNumber,
                                                                                   textType,
                                                                                   tableName,
                                                                                   isOperation,
                                                                                   null); // GE-1353
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Selects the Operation Data Collection for Order
                operationDataCollection = compareToolDao.selectOperationDataCollection(orderId,
                                                                                       operationKey,
                                                                                       stepKey,
                                                                                       textType,
                                                                                       null); // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for Order
                operationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(orderId,
                                                                                             operationKey,
                                                                                             stepKey,
                                                                                             textType,
                                                                                             altId,
                                                                                             null); // GE-1353
            }
        }

        if (operationDataCollection.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "CALCULATED DC INFO";

        List operationCalcDataCollection = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Data Collection for From Plan
            operationCalcDataCollection = compareToolDao.selectOperationDataCollection(planId,
                                                                                   operationKey,
                                                                                   stepKey,
                                                                                   stepUpdateNumber,
                                                                                   operationUpdateNumber,
                                                                                   textType,
                                                                                   tableName,
                                                                                   isOperation,
                                                                                   CALCULATED_DC); // GE-1353
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Selects the Operation Data Collection for Order
                operationCalcDataCollection = compareToolDao.selectOperationDataCollection(orderId,
                                                                                       operationKey,
                                                                                       stepKey,
                                                                                       textType,
                                                                                       CALCULATED_DC); // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for Order
                operationCalcDataCollection = compareToolDao.selectOperationDataCollectionAltLog(orderId,
                                                                                             operationKey,
                                                                                             stepKey,
                                                                                             textType,
                                                                                             altId,
                                                                                             CALCULATED_DC); // GE-1353
            }
        }

        if (operationCalcDataCollection.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "TOOL DC";
        List operationTools = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Tools for From Plan
            operationTools = compareToolDao.selectOperationTools(planId,
                                                                 operationKey,
                                                                 stepKey,
                                                                 stepUpdateNumber,
                                                                 operationUpdateNumber,
                                                                 textType,
                                                                 tableName,
                                                                 isOperation);
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Selects the Operation Tools for Order
                operationTools = compareToolDao.selectOperationTools(orderId,
                                                                     operationKey,
                                                                     stepKey,
                                                                     textType);
            }
            else
            {
                //FND-25023 Selects the Operation Tools in Alteration for Order
                operationTools = compareToolDao.selectOperationToolsAltLog(orderId,
                                                                           operationKey,
                                                                           stepKey,
                                                                           textType,
                                                                           altId);
            }
        }

        if (operationTools.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "PART DC";
        List operationParts = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Parts for From Plan
            operationParts = compareToolDao.selectOperationParts(planId,
                                                                 operationKey,
                                                                 stepKey,
                                                                 stepUpdateNumber,
                                                                 operationUpdateNumber,
                                                                 textType,
                                                                 tableName,
                                                                 isOperation);
        }
        else
        {
            if(isEmpty( altId ))
            {
                // Selects the Operation Parts for Order
                operationParts = compareToolDao.selectOperationParts(orderId,
                                                                     operationKey,
                                                                     stepKey,
                                                                     textType);
            }
            else
            {
                //FND-25023 Selects the Operation Parts in Alteration for Order
                operationParts = compareToolDao.selectOperationPartsAltLog(orderId,
                                                                           operationKey,
                                                                           stepKey,
                                                                           textType,
                                                                           altId);
            }
        }

        if (operationParts.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        instructionName = "BUYOFF";
        List operationBuyoffs = new ArrayList();

        if (isEmpty(orderId))
        {
            // Selects the Operation Buyoffs for From Plan
            operationBuyoffs = compareToolDao.selectOperationBuyoffs(planId,
                                                                     operationKey,
                                                                     stepKey,
                                                                     stepUpdateNumber,
                                                                     operationUpdateNumber,
                                                                     textType,
                                                                     tableName,
                                                                     isOperation);
        }
        else
        {
            if(isEmpty( altId ))
            {
                 // Selects the Operation Buyoffs for Order
                 operationBuyoffs = compareToolDao.selectOperationBuyoffs(orderId,
                                                                          operationKey,
                                                                          stepKey,
                                                                          textType);
            }
            else
            {
                //FND-25023 Selects the Operation Buyoffs in Alteration for Order
                operationBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(orderId,
                                                                               operationKey,
                                                                               stepKey,
                                                                               textType,
                                                                               altId);
            }
        }

        if (operationBuyoffs.size() > 0)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", level);
            returnMap.put("INSTRUCTION_NAME", instructionName);
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", parentKey);
            returnMap.put("CHILD_KEY", parentKey + " " + instructionName);
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        return returnList;
    }

    /**
     * Generates the OFD Hierarchy
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns the List of OFD Hierarchy
     */
    protected List generateOFDHierarchy(String fromPlanId,
                                      Number fromPlanVersion,
                                      Number fromPlanRevision,
                                      Number fromPlanAlterations,
                                      String toPlanId,
                                      Number toPlanVersion,
                                      Number toPlanRevision,
                                      Number toPlanAlterations,
                                      String fromOrderId,
                                      String toOrderId,
                                      String fromType,
                                      String toType,
                                      String fromAltId, // FND-25023
                                      String toAltId)
    {
        List returnList = new ArrayList();
        Map returnMap = null;

        String params = "FROM_PLAN_ID=" + fromPlanId + ";FROM_PLAN_VER="
                + fromPlanVersion + ";FROM_PLAN_REV=" + fromPlanRevision
                + ";FROM_PLAN_ALT=" + fromPlanAlterations + ";TO_PLAN_ID="
                + toPlanId + ";TO_PLAN_VER=" + toPlanVersion + ";TO_PLAN_REV="
                + toPlanRevision + ";TO_PLAN_ALT=" + toPlanAlterations
                + ";FROM_ORDER_ID=" + fromOrderId + ";TO_ORDER_ID=" + toOrderId
                + ";FROM_ALT_ID=" + defaultIfEmpty(fromAltId, "")
                + ";TO_ALT_ID=" + defaultIfEmpty(toAltId, "");

        List fromLinks = new ArrayList();
        List toLinks = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Links for From Plan
            fromLinks = compareToolDao.selectPlanLinks(fromPlanId,
                                                       fromPlanVersion,
                                                       fromPlanRevision,
                                                       fromPlanAlterations);

            // Selects the Plan Links for To Plan
            toLinks = compareToolDao.selectPlanLinks(toPlanId,
                                                     toPlanVersion,
                                                     toPlanRevision,
                                                     toPlanAlterations);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Order Links for From Order
                fromLinks = compareToolDao.selectOrderLinks(fromOrderId);
            }
            else
            {
                //FND-25023 Selects the Order Links in Alteration for From Order
                fromLinks = compareToolDao.selectOrderLinksAltLog(fromOrderId,
                                                                  fromAltId);
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Order Links for To Order
                toLinks = compareToolDao.selectOrderLinks(toOrderId);
            }
            else
            {
                //FND-25023 Selects the Order Links in Alteration for To Order
                toLinks = compareToolDao.selectOrderLinksAltLog(toOrderId,
                                                                toAltId);

            }
            
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Links for From Plan
                fromLinks = compareToolDao.selectPlanLinks(fromPlanId,
                                                           fromPlanVersion,
                                                           fromPlanRevision,
                                                           fromPlanAlterations);

                // Selects the Order Links for To Order
                toLinks = compareToolDao.selectOrderLinks(toOrderId);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Links for From Order
                fromLinks = compareToolDao.selectOrderLinks(fromOrderId);

                // Selects the Plan Links for To Plan
                toLinks = compareToolDao.selectPlanLinks(toPlanId,
                                                         toPlanVersion,
                                                         toPlanRevision,
                                                         toPlanAlterations);
            }
        }

        boolean fromLinkExists = fromLinks.size() > 0;
        boolean toLinkExists = toLinks.size() > 0;
        boolean displayLink = true;
        String changeIndicatorLink = EMPTY;

        if (fromLinkExists && toLinkExists)
        {
            if (fromLinks.equals(toLinks))
            {
                changeIndicatorLink = "CHECK_MARK";
            }
            else
            {
                changeIndicatorLink = "PENCIL_MARK";
            }
        }
        else
        {
            if (fromLinkExists && !toLinkExists)
            {
                changeIndicatorLink = "MINUS_MARK";
            }
            else if (!fromLinkExists && toLinkExists)
            {
                changeIndicatorLink = "PLUS_MARK";
            }
            else if (!fromLinkExists && !toLinkExists)
            {
                displayLink = false;
            }
        }

        List fromDecisions = new ArrayList();
        List toDecisions = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Decision for From Plan
            fromDecisions = compareToolDao.selectPlanDecisions(fromPlanId,
                                                               fromPlanVersion,
                                                               fromPlanRevision,
                                                               fromPlanAlterations,
                                                               DECISION);

            // Selects the Plan Decision for From Plan
            toDecisions = compareToolDao.selectPlanDecisions(toPlanId,
                                                             toPlanVersion,
                                                             toPlanRevision,
                                                             toPlanAlterations,
                                                             DECISION);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Order Decision for From Order
                fromDecisions = compareToolDao.selectOrderDecisions(fromOrderId,
                                                                    DECISION);
            }
            else
            {
                //FND-25023 Selects the Order Decision in Alteration for From Order
                fromDecisions = compareToolDao.selectOrderDecisionsAltLog(fromOrderId,
                                                                          DECISION,
                                                                          fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                // Selects the Order Decision for To Order
                toDecisions = compareToolDao.selectOrderDecisions(toOrderId,
                                                                  DECISION);
            }
            else
            {
                //FND-25023 Selects the Order Decision in Alteration for To Order
                toDecisions = compareToolDao.selectOrderDecisionsAltLog(toOrderId,
                                                                        DECISION,
                                                                        toAltId);

            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Decision for From Plan
                fromDecisions = compareToolDao.selectPlanDecisions(fromPlanId,
                                                                   fromPlanVersion,
                                                                   fromPlanRevision,
                                                                   fromPlanAlterations,
                                                                   DECISION);

                // Selects the Order Decision for To Order
                toDecisions = compareToolDao.selectOrderDecisions(toOrderId,
                                                                  DECISION);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Decision for From Order
                fromDecisions = compareToolDao.selectOrderDecisions(fromOrderId,
                                                                    DECISION);

                // Selects the Plan Decision for From Plan
                toDecisions = compareToolDao.selectPlanDecisions(toPlanId,
                                                                 toPlanVersion,
                                                                 toPlanRevision,
                                                                 toPlanAlterations,
                                                                 DECISION);
            }
        }

        boolean fromLinkDecisions = fromDecisions.size() > 0;
        boolean toLinkDecisions = toDecisions.size() > 0;
        boolean displayDecision = true;
        String changeIndicatorDecision = EMPTY;

        if (fromLinkDecisions && toLinkDecisions)
        {
            if (fromLinks.equals(toLinks))
            {
                changeIndicatorDecision = "CHECK_MARK";
            }
            else
            {
                changeIndicatorDecision = "PENCIL_MARK";
            }
        }
        else
        {
            if (fromLinkDecisions && !toLinkDecisions)
            {
                changeIndicatorDecision = "MINUS_MARK";
            }
            else if (!fromLinkDecisions && toLinkDecisions)
            {
                changeIndicatorDecision = "PLUS_MARK";
            }
            else if (!fromLinkDecisions && !toLinkDecisions)
            {
                displayDecision = false;
            }
        }

        List fromReturns = new ArrayList();
        List toReturns = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Returns for From Plan
            fromReturns = compareToolDao.selectPlanReturns(fromPlanId,
                                                           fromPlanVersion,
                                                           fromPlanRevision,
                                                           fromPlanAlterations,
                                                           RETURN);

            // Selects the Plan Returns for To Plan
            toReturns = compareToolDao.selectPlanReturns(toPlanId,
                                                         toPlanVersion,
                                                         toPlanRevision,
                                                         toPlanAlterations,
                                                         RETURN);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Order Returns for From Order
                fromReturns = compareToolDao.selectOrderReturns(fromOrderId,
                                                                RETURN,
                                                                NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Order Returns in Alteration for From Order
                fromReturns = compareToolDao.selectOrderReturnsAltLog(fromOrderId,
                                                                      RETURN,
                                                                      NumberUtils.INTEGER_MINUS_ONE,
                                                                      fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                // Selects the Order Returns for To Order
                toReturns = compareToolDao.selectOrderReturns(toOrderId,
                                                              RETURN,
                                                              NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Order Returns in Alteration for To Order
                toReturns = compareToolDao.selectOrderReturnsAltLog(toOrderId,
                                                                    RETURN,
                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                    toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Returns for From Plan
                fromReturns = compareToolDao.selectPlanReturns(fromPlanId,
                                                               fromPlanVersion,
                                                               fromPlanRevision,
                                                               fromPlanAlterations,
                                                               RETURN);

                // Selects the Order Returns for To Order
                toReturns = compareToolDao.selectOrderReturns(toOrderId,
                                                              RETURN,
                                                              NumberUtils.INTEGER_MINUS_ONE);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Returns for From Order
                fromReturns = compareToolDao.selectOrderReturns(fromOrderId,
                                                                RETURN,
                                                                NumberUtils.INTEGER_MINUS_ONE);

                // Selects the Plan Returns for To Plan
                toReturns = compareToolDao.selectPlanReturns(toPlanId,
                                                             toPlanVersion,
                                                             toPlanRevision,
                                                             toPlanAlterations,
                                                             RETURN);
            }
        }

        boolean fromLinkReturns = fromReturns.size() > 0;
        boolean toLinkReturns = toReturns.size() > 0;
        boolean displayReturns = true;
        String changeIndicatorReturn = EMPTY;

        if (fromLinkReturns && toLinkReturns)
        {
            if (fromLinks.equals(toLinks))
            {
                changeIndicatorReturn = "CHECK_MARK";
            }
            else
            {
                changeIndicatorReturn = "PENCIL_MARK";
            }
        }
        else
        {
            if (fromLinkReturns && !toLinkReturns)
            {
                changeIndicatorReturn = "MINUS_MARK";
            }
            else if (!fromLinkReturns && toLinkReturns)
            {
                changeIndicatorReturn = "PLUS_MARK";
            }
            else if (!fromLinkReturns && !toLinkReturns)
            {
                displayReturns = false;
            }
        }

        String changeIndicator = "PENCIL_MARK";
        
        // FND-19579
        if(isEmpty(changeIndicatorLink))
        {
        	changeIndicatorLink = "CHECK_MARK";
        }
        if(isEmpty(changeIndicatorDecision))
        {
        	changeIndicatorDecision = "CHECK_MARK";
        }
        if(isEmpty(changeIndicatorReturn))
        {
        	changeIndicatorReturn = "CHECK_MARK";
        }
        
        // Decide the Change Indicator for OFD
        if (equalsIgnoreCase(changeIndicatorLink, changeIndicatorDecision)
                && equalsIgnoreCase(changeIndicatorDecision,
                                      changeIndicatorReturn))
        {
            changeIndicator = changeIndicatorLink;
        }

        if (displayLink || displayDecision || displayReturns)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", 1);
            returnMap.put("INSTRUCTION_NAME", "OPERATION FLOW");
            returnMap.put("CHANGE_INDICATOR", changeIndicator);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", null);
            returnMap.put("CHILD_KEY", "OPERATION FLOW");
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        if (displayLink)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", 2);
            returnMap.put("INSTRUCTION_NAME", "LINKS");
            returnMap.put("CHANGE_INDICATOR", changeIndicatorLink);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", "OPERATION FLOW");
            returnMap.put("CHILD_KEY", "LINKS");
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        if (displayDecision)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", 2);
            returnMap.put("INSTRUCTION_NAME", "DECISIONS");
            returnMap.put("CHANGE_INDICATOR", changeIndicatorDecision);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", "OPERATION FLOW");
            returnMap.put("CHILD_KEY", "DECISIONS");
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        if (displayReturns)
        {
            returnMap = new ListOrderedMap();
            returnMap.put("LEVEL", 2);
            returnMap.put("INSTRUCTION_NAME", "RETURNS");
            returnMap.put("CHANGE_INDICATOR", changeIndicatorReturn);
            returnMap.put("ALT_NO", null);
            returnMap.put("OBJECT_ID", null);
            returnMap.put("IMAGE", null);
            returnMap.put("PARENT_KEY", "OPERATION FLOW");
            returnMap.put("CHILD_KEY", "RETURNS");
            returnMap.put("PARAMS", params);

            returnList.add(returnMap);
        }

        return returnList;
    }

    /**
     * Compares the Plan Header Data
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param fromPlanUpdateNumber
     *            the From Plan Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param toPlanUpdateNumber
     *            the To Plan Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId from Alteration ID
     * @param toAltId to Alteration ID
     * @return returns true when both From and To Plan header data is same
     *         returns false when both From and To Plan header data is not same
     */
    private boolean comparePlanHeaderData(String fromPlanId,
                                          Number fromPlanVersion,
                                          Number fromPlanRevision,
                                          Number fromPlanAlterations,
                                          Number fromPlanUpdateNumber,
                                          String toPlanId,
                                          Number toPlanVersion,
                                          Number toPlanRevision,
                                          Number toPlanAlterations,
                                          Number toPlanUpdateNumber,
                                          String fromOrderId,
                                          String toOrderId,
                                          String fromType,
                                          String toType,
                                          String fromAltId,
                                          String toAltId)
    {
        boolean compare = false;

        Map fromMap = new ListOrderedMap();
        Map toMap = new ListOrderedMap();
        List fromUidInfo = new ArrayList();
        List toUidInfo = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Header Data for From Plan
            fromMap = compareToolDao.selectPlanHeaderData(fromPlanId,
                                                          fromPlanVersion,
                                                          fromPlanRevision,
                                                          fromPlanAlterations);

            // Selects the Plan Header Data for To Plan
            toMap = compareToolDao.selectPlanHeaderData(toPlanId,
                                                        toPlanVersion,
                                                        toPlanRevision,
                                                        toPlanAlterations);

            //FND-15735
            if (fromMap != null && fromMap.equals(toMap))
            {
	            // Gets the UID Information of From Plan ( This is part of Plan
	            // Header )
	            fromUidInfo = uidEntry.getUidInformation(fromPlanId,
	                                                     fromPlanUpdateNumber,
	                                                     null,
	                                                     "ShowPlanHeader");
	
	            // Gets the UID Information of To Plan ( This is part of Plan Header
	            // )
	            toUidInfo = uidEntry.getUidInformation(toPlanId,
	                                                   toPlanUpdateNumber,
	                                                   null,
	                                                   "ShowPlanHeader");
            }
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Order Information for From Order
                fromMap = compareToolDao.selectOrderInformation(fromOrderId);
    
                // Selects the Order Information for To Order
                toMap = compareToolDao.selectOrderInformation(toOrderId);
    
                if (fromMap != null && fromMap.equals(toMap))
                {
    	            // Gets the UID Information of From Order ( This is part of Order
    	            // Header )
    	            fromUidInfo = uidEntry.getUidInformation(null,
    	                                                     null,
    	                                                     fromOrderId,
    	                                                     "ShowOrderHeader");
    	
    	            // Gets the UID Information of To Order ( This is part of Order
    	            // Header )
    	            toUidInfo = uidEntry.getUidInformation(null,
    	                                                   null,
    	                                                   toOrderId,
    	                                                   "ShowOrderHeader");
                }
            }
            else
            {
                // FND-25028
                // Selects the Order Information for From Order
                fromMap = compareToolDao.selectOrderInformationAltLog(fromOrderId, fromAltId);
    
                // Selects the Order Information for To Order
                toMap = compareToolDao.selectOrderInformationAltLog(toOrderId, toAltId);
    
                if (fromMap != null && fromMap.equals(toMap))
                {
                    // Gets the UID Information of From Order ( This is part of Order
                    // Header )
                    fromUidInfo = uidEntry.getUidInformation(null,
                                                             null,
                                                             fromOrderId,
                                                             "ShowOrderHeader");
        
                    // Gets the UID Information of To Order ( This is part of Order
                    // Header )
                    toUidInfo = uidEntry.getUidInformation(null,
                                                           null,
                                                           toOrderId,
                                                           "ShowOrderHeader");
                }
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Information for From Plan
                fromMap = compareToolDao.selectPlanOrderInformation(fromPlanId,
                                                                    fromPlanVersion,
                                                                    fromPlanRevision,
                                                                    fromPlanAlterations,
                                                                    null);

                // Selects the Order Information for To Order
                toMap = compareToolDao.selectPlanOrderInformation(null,
                                                                  null,
                                                                  null,
                                                                  null,
                                                                  toOrderId);

                if (fromMap != null && fromMap.equals(toMap))
                {
	                // Gets the UID Information of From Plan ( This is part of Plan
	                // Header )
	                fromUidInfo = uidEntry.getUidInformation(fromPlanId,
	                                                         fromPlanUpdateNumber,
	                                                         null,
	                                                         "ShowPlanHeader");
	
	                // Gets the UID Information of To Order ( This is part of Order
	                // Header )
	                toUidInfo = uidEntry.getUidInformation(null,
	                                                       null,
	                                                       toOrderId,
	                                                       "ShowOrderHeader");
                }
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Information for From Order
                fromMap = compareToolDao.selectPlanOrderInformation(null,
                                                                    null,
                                                                    null,
                                                                    null,
                                                                    fromOrderId);

                // Selects the Plan Information for From Plan
                toMap = compareToolDao.selectPlanOrderInformation(toPlanId,
                                                                  toPlanVersion,
                                                                  toPlanRevision,
                                                                  toPlanAlterations,
                                                                  null);

                if (fromMap != null && fromMap.equals(toMap))
                {
                	// Gets the UID Information of To Plan ( This is part of Plan
                	// Header )
                	toUidInfo = uidEntry.getUidInformation(toPlanId,
                			                               toPlanUpdateNumber,
                			                               null,
                	                                       "ShowPlanHeader");

                	// Gets the UID Information of From Order ( This is part of
                	// Order Header )
                	fromUidInfo = uidEntry.getUidInformation(null,
                			                                 null,
                			                                 fromOrderId,
                	                                         "ShowOrderHeader");
                }
            }
        }

        // Compare Maps and Lists
        if (fromMap != null && fromUidInfo != null && fromMap.equals(toMap)
                && fromUidInfo.equals(toUidInfo))
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Plan Header Text
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanUpdateNumber
     *            the From Plan Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanUpdateNumber
     *            the To Plan Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId from Alteration ID
     * @param toAltId to Alteration ID
     * @return returns true when both From and To Plan header Text is same
     *         returns false when both From and To Plan header Text is not same
     */
    private boolean comparePlanHeaderText(String fromPlanId,
                                          Number fromPlanUpdateNumber,
                                          String toPlanId,
                                          Number toPlanUpdateNumber,
                                          String fromOrderId,
                                          String toOrderId,
                                          String fromType,
                                          String toType,
                                          String fromAltId,
                                          String toAltId)
    {
        boolean compare = false;
        String fromText = EMPTY;
        String toText = EMPTY;

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Header Text for From Plan
            fromText = compareToolDao.selectPlanHeaderText(fromPlanId,
                                                           fromPlanUpdateNumber,
                                                           null,
                                                           PLANNING);

            // Selects the Plan Header Text for To Plan
            toText = compareToolDao.selectPlanHeaderText(toPlanId,
                                                         toPlanUpdateNumber,
                                                         null,
                                                         PLANNING);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Order Header Text for From Order
                fromText = compareToolDao.selectPlanHeaderText(null,
                                                               null,
                                                               fromOrderId,
                                                               PLANNING);
            }
            else
            {
                // FND-25023
                // Selects the Order Header Text for From Order
                fromText = compareToolDao.selectPlanHeaderTextAltLog(fromOrderId,
                                                                     PLANNING,
                                                                     fromAltId);
                
            }
            if(isEmpty(toAltId))
            {
                // Selects the Order Header Text for To Order
                toText = compareToolDao.selectPlanHeaderText(null,
                                                             null,
                                                             toOrderId,
                                                             PLANNING);
            }
            else
            {
                // FND-25023
                // Selects the Order Header Text for To Order
                toText = compareToolDao.selectPlanHeaderTextAltLog(toOrderId,
                                                                   PLANNING,
                                                                   toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Header Text for From Plan
                fromText = compareToolDao.selectPlanHeaderText(fromPlanId,
                                                               fromPlanUpdateNumber,
                                                               null,
                                                               PLANNING);

                // Selects the Order Header Text for To Order
                toText = compareToolDao.selectPlanHeaderText(null,
                                                             null,
                                                             toOrderId,
                                                             PLANNING);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Header Text for From Order
                fromText = compareToolDao.selectPlanHeaderText(null,
                                                               null,
                                                               fromOrderId,
                                                               PLANNING);

                // Selects the Plan Header Text for To Plan
                toText = compareToolDao.selectPlanHeaderText(toPlanId,
                                                             toPlanUpdateNumber,
                                                             null,
                                                             PLANNING);
            }
        }

        if (equalsIgnoreCase(fromText, toText))
        {
            compare = true;
        }
        return compare;
    }

    /**
     * Compare the Plan Header Standard Text
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanUpdateNumber
     *            the From Plan Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanUpdateNumber
     *            the To Plan Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId from Alteration ID
     * @param toAltId to Alteration ID
     * @return returns true when both From and To Plan Header Standard Text is
     *         same returns false when both From and To Plan Header Standard
     *         Text is not same
     */
    private boolean comparePlanHeaderStandardText(String fromPlanId,
                                                  Number fromPlanUpdateNumber,
                                                  String toPlanId,
                                                  Number toPlanUpdateNumber,
                                                  String fromOrderId,
                                                  String toOrderId,
                                                  String fromType,
                                                  String toType,
                                                  String fromAltId,
                                                  String toAltId)
    {
    	// FND-28458 : All select methods are changed  
        boolean compare = false;
        List fromStandardText = new ArrayList();
        List toStandardText = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Header Standard Text for From Plan
            fromStandardText = compareToolDao.selectPlanTextObjects(fromPlanId,
                                                                     fromPlanUpdateNumber,
                                                                     null,
                                                                     PLANNING);

            // Selects the Plan Header Standard Text for To Plan
            toStandardText = compareToolDao.selectPlanTextObjects(toPlanId,
                                                                   toPlanUpdateNumber,
                                                                   null,
                                                                   PLANNING);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty(fromAltId))
            {
                // Selects the Order Header Standard Text for From Order
                fromStandardText = compareToolDao.selectPlanTextObjects(null,
                                                                         null,
                                                                         fromOrderId,
                                                                         PLANNING);
            }
            else
            {
                // FND-25023
                // Selects the Order Header Standard Text for From Order Alteration Log
                fromStandardText = compareToolDao.selectPlanTextObjectsAltLog(fromOrderId,
                                                                              PLANNING,
                                                                              fromAltId);
            }
            
            
            if(isEmpty(toAltId))
            {
                // Selects the Order Header Standard Text for To Order
                toStandardText = compareToolDao.selectPlanTextObjects(null,
                                                                       null,
                                                                       toOrderId,
                                                                       PLANNING);
            }
            else
            {
                // FND-25023
                // Selects the Order Header Standard Text for To Order Alteration Log
                toStandardText = compareToolDao.selectPlanTextObjectsAltLog(toOrderId,
                                                                            PLANNING,
                                                                            toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Header Standard Text for From Plan
                fromStandardText = compareToolDao.selectPlanTextObjects(fromPlanId,
                                                                         fromPlanUpdateNumber,
                                                                         null,
                                                                         PLANNING);

                // Selects the Order Header Standard Text for To Order
                toStandardText = compareToolDao.selectPlanTextObjects(null,
                                                                       null,
                                                                       toOrderId,
                                                                       PLANNING);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Order Header Standard Text for From Order
                fromStandardText = compareToolDao.selectPlanTextObjects(null,
                                                                         null,
                                                                         fromOrderId,
                                                                         PLANNING);

                // Selects the Plan Header Standard Text for To Plan
                toStandardText = compareToolDao.selectPlanTextObjects(toPlanId,
                                                                       toPlanUpdateNumber,
                                                                       null,
                                                                       PLANNING);
            }
        }

        compare = compareURLParams(fromStandardText, toStandardText);

        return compare;
    }

    /**
     * Compares the URL Parameters
     * 
     * @param fromList
     *            the From List
     * @param toList
     *            the To List
     * @return returns true when both From and To Text is same returns false
     *         when both From and To Text is not same
     */
    private boolean compareURLParams(List fromList, List toList)
    {
        boolean compare = true;        
        
        
        // FND-25023
        // return true if both are empty. return false if one of the list is empty and other is not.
        boolean isFromListEmpty = (fromList == null || fromList.size() == 0);
        boolean isToListEmpty = (toList == null || toList.size() == 0);
        
        if(isFromListEmpty && isToListEmpty)
        {
            return true;
        }
        else if(isFromListEmpty && !isToListEmpty)
        {
            return false;
        }
        else if(!isFromListEmpty && isToListEmpty)
        {
            return false;
        }
        else
        {
            // If both lists are not empty.
            // Check if all objects are same or not in both from and to list.
            // If a single objectId does not get compared then make compare false.
            
            // If toList is greater than fromList then make toList = fromList
            if(toList.size() > fromList.size())
            {
                List tempList  = toList;
                toList = fromList;
                fromList = tempList;
            }
            
            Iterator fromStandardTextIterator = fromList.listIterator();
            while (fromStandardTextIterator.hasNext() && compare)
            {
                Map fromStandardTextMap = (Map) fromStandardTextIterator.next();
    
                String fromURLParams = (String) fromStandardTextMap.get("URL_PARAMS");
    
                // FND-25023
                // As per new implementation of Std Text, we do not have REF_ID parameter exactly after OBJECT_ID parameter.
                // So endIndex of OBJECT_ID will be "," after start index of OBJECT_ID.
                String objectIdString = "OBJECT_ID=";
                int objectIdStart = fromURLParams.indexOf(objectIdString) + objectIdString.length();
                int objectIdEnd = 0;
                // GE-2535
                if(fromURLParams.indexOf(objectIdString) >= 0)
                {
                    // int objectIdEnd = fromURLParams.indexOf(",REF_ID=");
                    objectIdEnd = indexOf(fromURLParams, ",", objectIdStart);
        
                    // Gets the Object Id of Standard Text
                    String fromObjectId = fromURLParams.substring(objectIdStart,
                                                                  objectIdEnd);
        
                    Iterator toStandardTextIterator = toList.listIterator();
        
                    boolean innerCompare = false;
                    while (toStandardTextIterator.hasNext() && !innerCompare)
                    {
                        Map toStandardTextMap = (Map) toStandardTextIterator.next();
        
                        String toURLParams = (String) toStandardTextMap.get("URL_PARAMS");
        
                        // Check if From Standard Text is there in To Standard Text or
                        // not
                        if (contains(toURLParams, objectIdString + fromObjectId)) // FND-25023
                        {
                            innerCompare = true;
                        }
                    }
                
                    if(!innerCompare)
                    {
                        compare = false;
                    }
                }
            }
        }
        
        return compare;
    }

    /**
     * Compares the Plan Header Illustrations
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanUpdateNumber
     *            the From Plan Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanUpdateNumber
     *            the To Plan Update Number
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @return returns true when both From and To Plan Header Illustration is
     *         same returns false when both From and To Plan Header Illustration
     *         is not same
     */
    private boolean comparePlanHeaderIllustrations(String fromPlanId,
                                                   Number fromPlanUpdateNumber,
                                                   String toPlanId,
                                                   Number toPlanUpdateNumber,
                                                   String fromOrderId,
                                                   String toOrderId,
                                                   String fromType,
                                                   String toType)
    {
        boolean compare = false;
        List fromHeaderIllustration = new ArrayList();
        List toHeaderIllustration = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Header Illustrations for From Plan
            fromHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(fromPlanId,
                                                                                  fromPlanUpdateNumber,
                                                                                  null,
                                                                                  PLANNING,
                                                                                  "SLIDE");

            // Selects the Plan Header Illustrations for To Plan
            toHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(toPlanId,
                                                                                toPlanUpdateNumber,
                                                                                null,
                                                                                PLANNING,
                                                                                "SLIDE");
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Plan Header Illustrations for From Plan
            fromHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(null,
                                                                                  null,
                                                                                  fromOrderId,
                                                                                  PLANNING,
                                                                                  "SLIDE");

            // Selects the Plan Header Illustrations for To Plan
            toHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(null,
                                                                                null,
                                                                                toOrderId,
                                                                                PLANNING,
                                                                                "SLIDE");
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Plan Header Illustrations for From Plan
                fromHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(fromPlanId,
                                                                                      fromPlanUpdateNumber,
                                                                                      null,
                                                                                      PLANNING,
                                                                                      "SLIDE");

                // Selects the Plan Header Illustrations for To Plan
                toHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(null,
                                                                                    null,
                                                                                    toOrderId,
                                                                                    PLANNING,
                                                                                    "SLIDE");
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Plan Header Illustrations for From Plan
                fromHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(null,
                                                                                      null,
                                                                                      fromOrderId,
                                                                                      PLANNING,
                                                                                      "SLIDE");

                // Selects the Plan Header Illustrations for To Plan
                toHeaderIllustration = compareToolDao.selectPlanHeaderIllustrations(toPlanId,
                                                                                    toPlanUpdateNumber,
                                                                                    null,
                                                                                    PLANNING,
                                                                                    "SLIDE");
            }
        }

        // Compare the Lists
        if (fromHeaderIllustration != null
                && fromHeaderIllustration.equals(toHeaderIllustration))
        {
            compare = true;
        }

        return compare;
    }
    
    // FND-22719
    private boolean comparePlanTextImageList( String fromPlanId,
									          Number fromPlanUpdateNumber,
									          String toPlanId,
									          Number toPlanUpdateNumber,
									          String textType,
									          String fromOrderId,
									          String toOrderId,
									          String fromType,
									          String toType,
									          String fromAltId, // FND-25023
									          String toAltId)
	{
		boolean compare = false;
		
		List fromPlanTextImageList = new ArrayList();
		List toPlanTextImageList = new ArrayList();
		
		if (equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(fromType, toType))
		{
			// Selects the Images for From Plan Text
			fromPlanTextImageList = compareToolDao.selectPlanTextImagesfromImageListControl( fromPlanId,
																							 fromPlanUpdateNumber,
																							 textType );
			
			// Selects the Images for To Plan Text
			toPlanTextImageList = compareToolDao.selectPlanTextImagesfromImageListControl( toPlanId,
													                                       toPlanUpdateNumber,
													                                       textType );
		}
		else if (equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(fromType, toType))
		{
		    if(isEmpty(fromAltId))
		    {
			    // Selects the Images for From Order Text
		        fromPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControl( fromOrderId,
					    				                                       					  textType );
		    }
		    else
		    {
		        // FND-25023
		        // Selects the Images for From Order Text Alteration Log
                fromPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControlAltLog( fromOrderId,
                                                                                                        textType,
                                                                                                        fromAltId);
		    }
			
		    if(isEmpty(toAltId))
		    {
			    // Selects the Images for TO Order Text
		        toPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControl( toOrderId,
									                                     					    textType );
		    }
		    else
		    {
		        // FND-25023
                // Selects the Images for To Order Text Alteration Log
                toPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControlAltLog( toOrderId,
                                                                                                      textType,
                                                                                                      toAltId);
		    }
		}
		else
		{
			if (equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(toType, "ORDER"))
			{
				// Selects the Images for From Plan Text
				fromPlanTextImageList = compareToolDao.selectPlanTextImagesfromImageListControl( fromPlanId,
													                                             fromPlanUpdateNumber,
													                                             textType );
			
				// Selects the Images for TO Order Text
				toPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControl( toOrderId,
										                                         				textType );
			}
			else if (equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(toType, "PLAN"))
			{
				// Selects the Images for From Order Text
				fromPlanTextImageList = compareToolDao.selectOrderTextImagesfromImageListControl( fromOrderId,
										                                           				  textType );
				
				// Selects the Images for To Plan Text
				toPlanTextImageList = compareToolDao.selectPlanTextImagesfromImageListControl( toPlanId,
													                                           toPlanUpdateNumber,
													                                           textType );
			}
		}
		
		// Compares the Lists
		compare = compareList(fromPlanTextImageList, toPlanTextImageList);
		
		return compare;
	}

    /**
     * Compares the Operation Header Data
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap
     * @param toStdOperMap
     * @param calledFromChgAck
     * @return returns true when both From and To Operation Header Data is same
     *         returns false when both From and To Operation Header Data is not
     *         same
     */
    private boolean compareOperationHeaderData(String operationNumber ,
                                               String fromPlanId ,
                                               Number fromPlanVersion ,
                                               Number fromPlanRevision ,
                                               Number fromPlanAlterations ,
                                               String toPlanId ,
                                               Number toPlanVersion ,
                                               Number toPlanRevision ,
                                               Number toPlanAlterations ,
                                               String fromOrderId ,
                                               String toOrderId ,
                                               String fromType ,
                                               String toType ,
                                               String fromAltId , // FND-25023
                                               String toAltId , 
                                               Map fromStdOperMap , 
                                               Map toStdOperMap,
                                               String calledFromChgAck ) //GE-1213
    {
        boolean compare = false;

        Map fromOperationHeaderData = null;
        Map toOperationHeaderData = null;

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Header Data for From Plan
            fromOperationHeaderData = compareToolDao.selectOperationHeaderData((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                               fromPlanId,
                                                                               fromPlanVersion,
                                                                               fromPlanRevision,
                                                                               fromPlanAlterations);
            
            //FND-15735
            if(fromOperationHeaderData != null)
            {
                fromOperationHeaderData.put( "OPER_NO", operationNumber );  //set original Plan Operation No again to Map in place of standard Operation oper no
                if(equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))
                {
                	 fromOperationHeaderData.remove("OPER_UPDT_NO");
                }
               
	            // Selects the Operation Header Data for To Plan
	            toOperationHeaderData = compareToolDao.selectOperationHeaderData((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
	                                                                             toPlanId,
	                                                                             toPlanVersion,
	                                                                             toPlanRevision,
	                                                                             toPlanAlterations);
	            if(toOperationHeaderData != null)
	            {
	                toOperationHeaderData.put( "OPER_NO", operationNumber );
	                if(equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))
	                {
	                	toOperationHeaderData.remove("OPER_UPDT_NO");
	                }
	            }
	            
            }
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Header Data for From Order
                fromOperationHeaderData = compareToolDao.selectOperationHeaderData(fromOrderId,
                                                                                   operationNumber,
                                                                                   NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                // FND-25023  Selects the Operation Header Data in Alteration for From Order
                fromOperationHeaderData = compareToolDao.selectOperationHeaderDataAltLog(fromOrderId,
                                                                                         operationNumber,
                                                                                         NumberUtils.INTEGER_MINUS_ONE,
                                                                                         fromAltId);
            }
            
            if(isEmpty( toAltId ))
            {
                
                if(fromOperationHeaderData != null)
                {
                    // Selects the Operation Header Data for To Order
                    toOperationHeaderData = compareToolDao.selectOperationHeaderData(toOrderId,
                                                                                     operationNumber,
                                                                                     NumberUtils.INTEGER_MINUS_ONE);
                }
            }
            else
            {   
                if(fromOperationHeaderData != null)
                {
                    //FND-25023 Selects the Operation Header Data in Alteration for To Order
                    toOperationHeaderData = compareToolDao.selectOperationHeaderDataAltLog(toOrderId,
                                                                                           operationNumber,
                                                                                           NumberUtils.INTEGER_MINUS_ONE,
                                                                                           toAltId);
                }
            }
            

            
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Header Data for From Plan
                fromOperationHeaderData = compareToolDao.selectOperationHeaderData(null,
                                                                                   (!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                                   null,
                                                                                   fromPlanId,
                                                                                   fromPlanVersion,
                                                                                   fromPlanRevision,
                                                                                   fromPlanAlterations);

                if(fromOperationHeaderData != null)
                {
	                // Selects the Operation Header Data for To Order
	                toOperationHeaderData = compareToolDao.selectOperationHeaderData(toOrderId,
	                                                                                 operationNumber,
	                                                                                 NumberUtils.INTEGER_MINUS_ONE,
	                                                                                 null,
	                                                                                 null,
	                                                                                 null,
	                                                                                 null);
                }
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Header Data for From Order
                fromOperationHeaderData = compareToolDao.selectOperationHeaderData(fromOrderId,
                                                                                   operationNumber,
                                                                                   NumberUtils.INTEGER_MINUS_ONE,
                                                                                   null,
                                                                                   null,
                                                                                   null,
                                                                                   null);

                if(fromOperationHeaderData != null)
                {
	                // Selects the Operation Header Data for To Plan
	                toOperationHeaderData = compareToolDao.selectOperationHeaderData(null,
	                                                                                 (!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
	                                                                                 null,
	                                                                                 toPlanId,
	                                                                                 toPlanVersion,
	                                                                                 toPlanRevision,
	                                                                                 toPlanAlterations);
                }
            }
        }

        // Compare the Lists
        if (fromOperationHeaderData != null
                && fromOperationHeaderData.equals(toOperationHeaderData))
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Operation Process
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param booleanMap the Boolean Map object consists Operation state
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap
     * @param toStdOperMap
     * @return returns true when both From and To Operation Process is same
     *         returns false when both From and To Operation Process is not same
     */
    private boolean compareOperationProcess(String operationNumber ,
                                            String fromPlanId ,
                                            Number fromPlanVersion ,
                                            Number fromPlanRevision ,
                                            Number fromPlanAlterations ,
                                            String toPlanId ,
                                            Number toPlanVersion ,
                                            Number toPlanRevision ,
                                            Number toPlanAlterations ,
                                            String fromOrderId ,
                                            String toOrderId ,
                                            String fromType ,
                                            String toType , 
                                            Map booleanMap ,
                                            String fromAltId , // FND-25023
                                            String toAltId , 
                                            Map fromStdOperMap , //GE-1668
                                            Map toStdOperMap )
    {
        boolean compare = false;

        List fromOperationProcess = new ArrayList();
        List toOperationProcess = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Process for From Plan
            fromOperationProcess = compareToolDao.selectOperationProcess((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                         fromPlanId,
                                                                         fromPlanVersion,
                                                                         fromPlanRevision,
                                                                         fromPlanAlterations,
                                                                         null,
                                                                         null);

            // Selects the Operation Process for To Plan
            toOperationProcess = compareToolDao.selectOperationProcess((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                       toPlanId,
                                                                       toPlanVersion,
                                                                       toPlanRevision,
                                                                       toPlanAlterations,
                                                                       null,
                                                                       null);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Process for From Order
                fromOperationProcess = compareToolDao.selectOperationProcess(operationNumber,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             fromOrderId,
                                                                             NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Process in Alteration for From Order
                fromOperationProcess = compareToolDao.selectOperationProcessAltLog(operationNumber,
                                                                                   fromOrderId,
                                                                                   NumberUtils.INTEGER_MINUS_ONE,
                                                                                   fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                // Selects the Operation Process for To Order
                toOperationProcess = compareToolDao.selectOperationProcess(operationNumber,
                                                                           null,
                                                                           null,
                                                                           null,
                                                                           null,
                                                                           toOrderId,
                                                                           NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Process in Alteration for To Order
                toOperationProcess = compareToolDao.selectOperationProcessAltLog(operationNumber,
                                                                                 toOrderId,
                                                                                 NumberUtils.INTEGER_MINUS_ONE,
                                                                                 toAltId);
            }
            
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Process for From Plan
                fromOperationProcess = compareToolDao.selectOperationProcess((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                             fromPlanId,
                                                                             fromPlanVersion,
                                                                             fromPlanRevision,
                                                                             fromPlanAlterations,
                                                                             null,
                                                                             null);

                // Selects the Operation Process for To Order
                toOperationProcess = compareToolDao.selectOperationProcess(operationNumber,
                                                                           null,
                                                                           null,
                                                                           null,
                                                                           null,
                                                                           toOrderId,
                                                                           NumberUtils.INTEGER_MINUS_ONE);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Process for From Order
                fromOperationProcess = compareToolDao.selectOperationProcess(operationNumber,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             fromOrderId,
                                                                             NumberUtils.INTEGER_MINUS_ONE);

                // Selects the Operation Process for To Plan
                toOperationProcess = compareToolDao.selectOperationProcess((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                           toPlanId,
                                                                           toPlanVersion,
                                                                           toPlanRevision,
                                                                           toPlanAlterations,
                                                                           null,
                                                                           null);
            }
        }
        
        // 05/07/2011 Suresh FND-15735
        // Performance: Compare Plan/Order
        booleanMap.put("fromOperationProcessExists", fromOperationProcess.size() > 0);
        booleanMap.put("toOperationProcessExists", toOperationProcess.size() > 0);

        // Compare the Lists
        if (fromOperationProcess != null
                && fromOperationProcess.equals(toOperationProcess))
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Operation Skill
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param booleanMap the Boolean Map object consists Operation state
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap
     * @param toStdOperMap
     * @return returns true when both From and To Operation Skill is same
     *         returns false when both From and To Operation Skill is not same
     */
    private boolean compareOperationSkill(String operationNumber ,
                                          String fromPlanId ,
                                          Number fromPlanVersion ,
                                          Number fromPlanRevision ,
                                          Number fromPlanAlterations ,
                                          String toPlanId ,
                                          Number toPlanVersion ,
                                          Number toPlanRevision ,
                                          Number toPlanAlterations ,
                                          String fromOrderId ,
                                          String toOrderId ,
                                          String fromType ,
                                          String toType , 
                                          Map booleanMap ,
                                          String fromAltId , // FND-25023
                                          String toAltId , 
                                          Map fromStdOperMap , //GE-1668
                                          Map toStdOperMap )
    {
        boolean compare = false;

        List fromOperationSkill = new ArrayList();
        List toOperationSkill = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Skill for From Plan
            fromOperationSkill = compareToolDao.selectOperationSkill((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                     fromPlanId,
                                                                     fromPlanVersion,
                                                                     fromPlanRevision,
                                                                     fromPlanAlterations,
                                                                     null,
                                                                     null);

            // Selects the Operation Skill for To Plan
            toOperationSkill = compareToolDao.selectOperationSkill((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                   toPlanId,
                                                                   toPlanVersion,
                                                                   toPlanRevision,
                                                                   toPlanAlterations,
                                                                   null,
                                                                   null);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Skill for From Order
                fromOperationSkill = compareToolDao.selectOperationSkill(operationNumber,
                                                                         null,
                                                                         null,
                                                                         null,
                                                                         null,
                                                                         fromOrderId,
                                                                         NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Skill in Alteration for From Order
                fromOperationSkill = compareToolDao.selectOperationSkillAltLog(operationNumber,
                                                                               fromOrderId,
                                                                               NumberUtils.INTEGER_MINUS_ONE,
                                                                               fromAltId);
            }
    
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Skill for To Order
                toOperationSkill = compareToolDao.selectOperationSkill(operationNumber,
                                                                       null,
                                                                       null,
                                                                       null,
                                                                       null,
                                                                       toOrderId,
                                                                       NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Skill in Alteration for To Order
                toOperationSkill = compareToolDao.selectOperationSkillAltLog(operationNumber,
                                                                             toOrderId,
                                                                             NumberUtils.INTEGER_MINUS_ONE,
                                                                             toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Skill for From Plan
                fromOperationSkill = compareToolDao.selectOperationSkill((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                         fromPlanId,
                                                                         fromPlanVersion,
                                                                         fromPlanRevision,
                                                                         fromPlanAlterations,
                                                                         null,
                                                                         null);

                // Selects the Operation Skill for To Order
                toOperationSkill = compareToolDao.selectOperationSkill(operationNumber,
                                                                       null,
                                                                       null,
                                                                       null,
                                                                       null,
                                                                       toOrderId,
                                                                       NumberUtils.INTEGER_MINUS_ONE);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Skill for From Order
                fromOperationSkill = compareToolDao.selectOperationSkill(operationNumber,
                                                                         null,
                                                                         null,
                                                                         null,
                                                                         null,
                                                                         fromOrderId,
                                                                         NumberUtils.INTEGER_MINUS_ONE);

                // Selects the Operation Skill for To Plan
                toOperationSkill = compareToolDao.selectOperationSkill((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                       toPlanId,
                                                                       toPlanVersion,
                                                                       toPlanRevision,
                                                                       toPlanAlterations,
                                                                       null,
                                                                       null);
            }
        }

        // 05/07/2011 Suresh FND-15735
        // Performance: Compare Plan/Order
        booleanMap.put("fromOperationSkillExists", fromOperationSkill.size() > 0);
        booleanMap.put("toOperationSkillExists", toOperationSkill.size() > 0);
        
        // Compare the Lists
        if (fromOperationSkill != null
                && fromOperationSkill.equals(toOperationSkill))
        {
            compare = true;
        }

        return compare;
    }
    
	protected boolean compareOperationStandards(String operationNumber ,
											    String fromPlanId ,
											    Number fromPlanVersion ,
											    Number fromPlanRevision ,
											    Number fromPlanAlterations ,
											    String toPlanId ,
											    Number toPlanVersion ,
											    Number toPlanRevision ,
											    Number toPlanAlterations ,
											    String fromOrderId ,
											    String toOrderId ,
											    String fromType ,
											    String toType , 
											    Map booleanMap ,
											    String fromAltId , // FND-25023
											    String toAltId , 
											    Map fromStdOperMap ,  //GE-1668
											    Map toStdOperMap )
	{	
		boolean compare = false;

        //GE-1815
        List fromOperationStandards = new ArrayList();
        List toOperationStandards = new ArrayList();        

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
        	// Selects the Operation Standards for From Plan
        	fromOperationStandards = compareToolDao.selectOperationStandards((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668 
        																	 fromPlanId, 
        																	 fromPlanVersion, 
        																	 fromPlanRevision, 
        																	 fromPlanAlterations);
        	
        	// Selects the Operation Standards for To Plan
        	toOperationStandards = compareToolDao.selectOperationStandards((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668 
        																   toPlanId, 
        																   toPlanVersion, 
        																   toPlanRevision, 
        																   toPlanAlterations);
       	
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Standards for From Order
                fromOperationStandards = compareToolDao.selectOperationStandards(fromOrderId, 
                																 operationNumber, 
                																 NumberUtils.INTEGER_MINUS_ONE);            	
            	
            }
            else
            {
            	 // Selects the Operation Standards for From Order
                fromOperationStandards = compareToolDao.selectOperationStandardsAltLog(fromOrderId, 
                																	   operationNumber, 
                																	   NumberUtils.INTEGER_MINUS_ONE, 
                																	   fromAltId);
            }
    
            if(isEmpty( toAltId ))
            {
            	 // Selects the Operation Standards for To Order
                toOperationStandards = compareToolDao.selectOperationStandards(toOrderId, 
                															   operationNumber, 
                															   NumberUtils.INTEGER_MINUS_ONE);
            	
            }
            else
            {
            	 // Selects the Operation Standards for To Order
                toOperationStandards = compareToolDao.selectOperationStandardsAltLog(toOrderId, 
                																	 operationNumber, 
                																	 NumberUtils.INTEGER_MINUS_ONE, 
                																	 toAltId);
            	
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
            	// Selects the Operation Standards for From Plan
            	fromOperationStandards = compareToolDao.selectOperationStandards((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668 
            																	 fromPlanId,
            																	 fromPlanVersion,
            																	 fromPlanRevision,
            																	 fromPlanAlterations);
            	
                // Selects the Operation Standards for To Order
                toOperationStandards = compareToolDao.selectOperationStandards(toOrderId, 
                															   operationNumber, 
                															   NumberUtils.INTEGER_MINUS_ONE);                

            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
            	
                // Selects the Operation Standards for From Order
                fromOperationStandards = compareToolDao.selectOperationStandards(fromOrderId, 
                																 operationNumber, 
                																 NumberUtils.INTEGER_MINUS_ONE);
            	
            	// Selects the Operation Standards for To Plan                
            	toOperationStandards = compareToolDao.selectOperationStandards((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668 
            																   toPlanId,
            																   toPlanVersion,
            																   toPlanRevision,
            																   toPlanAlterations);
            }
        }

        // 05/07/2011 Suresh FND-15735
        // Performance: Compare Plan/Order
        booleanMap.put("fromOperationStandardsExists", fromOperationStandards.size() > 0);
        booleanMap.put("toOperationStandardsExists", toOperationStandards.size() > 0);
        
        // Compare the Lists
        if (fromOperationStandards != null
                && fromOperationStandards.equals(toOperationStandards))
        {
            compare = true;
        }

        return compare;
	}

    /**
     * Compares the Operation Precedence
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Order
     * @param booleanMap the Boolean Map object consists Operation state
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap
     * @param toStdOperMap
     * @return returns true when both From and To Operation Precedence is same
     *         returns false when both From and To Operation Precedence is not
     *         same
     */
    private boolean compareOperationPrecedence(String operationNumber ,
                                               String fromPlanId ,
                                               Number fromPlanVersion ,
                                               Number fromPlanRevision ,
                                               Number fromPlanAlterations ,
                                               String toPlanId ,
                                               Number toPlanVersion ,
                                               Number toPlanRevision ,
                                               Number toPlanAlterations ,
                                               String fromOrderId ,
                                               String toOrderId ,
                                               String fromType ,
                                               String toType , 
                                               Map booleanMap ,
                                               String fromAltId , // FND-25023
                                               String toAltId , 
                                               Map fromStdOperMap , //GE-1668
                                               Map toStdOperMap )
    {
        boolean compare = false;

        List fromOperationPrecedence = new ArrayList();
        List toOperationPrecedence = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Precedence for From Plan
            fromOperationPrecedence = compareToolDao.selectOperationPrecedence((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                               fromPlanId,
                                                                               fromPlanVersion,
                                                                               fromPlanRevision,
                                                                               fromPlanAlterations,
                                                                               null,
                                                                               null);

            // Selects the Operation Precedence for To Plan
            toOperationPrecedence = compareToolDao.selectOperationPrecedence((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                             toPlanId,
                                                                             toPlanVersion,
                                                                             toPlanRevision,
                                                                             toPlanAlterations,
                                                                             null,
                                                                             null);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Precedence for From Order
                fromOperationPrecedence = compareToolDao.selectOperationPrecedence(operationNumber,
                                                                                   null,
                                                                                   null,
                                                                                   null,
                                                                                   null,
                                                                                   fromOrderId,
                                                                                   NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Precedence in Alteration for From Order
                fromOperationPrecedence = compareToolDao.selectOperationPrecedenceAltLog(operationNumber,
                                                                                   fromOrderId,
                                                                                   NumberUtils.INTEGER_MINUS_ONE,
                                                                                   fromAltId);
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Precedence for To Order
                toOperationPrecedence = compareToolDao.selectOperationPrecedence(operationNumber,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 toOrderId,
                                                                                 NumberUtils.INTEGER_MINUS_ONE);
            }
            else
            {
                //FND-25023 Selects the Operation Precedence in Alteration for To Order
                toOperationPrecedence = compareToolDao.selectOperationPrecedenceAltLog(operationNumber,
                                                                                 toOrderId,
                                                                                 NumberUtils.INTEGER_MINUS_ONE,
                                                                                 toAltId);
                 
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Precedence for From Plan
                fromOperationPrecedence = compareToolDao.selectOperationPrecedence((!fromStdOperMap.isEmpty()) ? (String) fromStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                                   fromPlanId,
                                                                                   fromPlanVersion,
                                                                                   fromPlanRevision,
                                                                                   fromPlanAlterations,
                                                                                   null,
                                                                                   null);

                // Selects the Operation Precedence for To Order
                toOperationPrecedence = compareToolDao.selectOperationPrecedence(operationNumber,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 null,
                                                                                 toOrderId,
                                                                                 NumberUtils.INTEGER_MINUS_ONE);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Precedence for From Order
                fromOperationPrecedence = compareToolDao.selectOperationPrecedence(operationNumber,
                                                                                   null,
                                                                                   null,
                                                                                   null,
                                                                                   null,
                                                                                   fromOrderId,
                                                                                   NumberUtils.INTEGER_MINUS_ONE);

                // Selects the Operation Precedence for To Plan
                toOperationPrecedence = compareToolDao.selectOperationPrecedence((!toStdOperMap.isEmpty()) ? (String) toStdOperMap.get( "STDOPER_OPER_NO" ) : operationNumber, // GE-1668
                                                                                 toPlanId,
                                                                                 toPlanVersion,
                                                                                 toPlanRevision,
                                                                                 toPlanAlterations,
                                                                                 null,
                                                                                 null);
            }
        }

        // 05/07/2011 Suresh FND-15735
        // Performance: Compare Plan/Order
        booleanMap.put("fromOperationPrecedenceExists", fromOperationPrecedence.size() > 0);
        booleanMap.put("toOperationPrecedenceExists", toOperationPrecedence.size() > 0);
        
        // Compare the Lists
        if (fromOperationPrecedence != null
                && fromOperationPrecedence.equals(toOperationPrecedence))
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Operation Header
     * 
     * @param operationNumber
     *            the Operation Number
     * @param fromPlanId
     *            the From Plan Id
     * @param fromPlanVersion
     *            the From Plan Version
     * @param fromPlanRevision
     *            the From Plan Revision
     * @param fromPlanAlterations
     *            the From Plan Alterations
     * @param toPlanId
     *            the To Plan Id
     * @param toPlanVersion
     *            the To Plan Version
     * @param toPlanRevision
     *            the To Plan Revision
     * @param toPlanAlterations
     *            the To Plan Alterations
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param booleanMap the Boolean Map object consists Operation header state 
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @param fromStdOperMap
     * @param toStdOperMap
     * @param calledFromChgAck
     * @return returns true when both From and To Operation Header is same
     *         returns false when both From and To Operation Header is not same
     */
    private boolean compareOperationHeader(String operationNumber ,
                                           String fromPlanId ,
                                           Number fromPlanVersion ,
                                           Number fromPlanRevision ,
                                           Number fromPlanAlterations ,
                                           String toPlanId ,
                                           Number toPlanVersion ,
                                           Number toPlanRevision ,
                                           Number toPlanAlterations ,
                                           String fromOrderId ,
                                           String toOrderId ,
                                           String fromType ,
                                           String toType , 
                                           Map booleanMap ,
                                           String fromAltId , // FND-25023
                                           String toAltId , 
                                           Map fromStdOperMap , //GE-1668
                                           Map toStdOperMap,
                                           String calledFromChgAck ) //GE-1213
    {
        boolean compare = false;

        // Compare Operation Header Data
        boolean compareOperationHeaderData = compareOperationHeaderData(operationNumber,
                                                                        fromPlanId,
                                                                        fromPlanVersion,
                                                                        fromPlanRevision,
                                                                        fromPlanAlterations,
                                                                        toPlanId,
                                                                        toPlanVersion,
                                                                        toPlanRevision,
                                                                        toPlanAlterations,
                                                                        fromOrderId,
                                                                        toOrderId,
                                                                        fromType,
                                                                        toType,
                                                                        fromAltId, // FND-25023
                                                                        toAltId, 
                                                                        fromStdOperMap, //GE-1668
                                                                        toStdOperMap,
                                                                        calledFromChgAck);
        
        booleanMap.put("compareOperationHeaderData", compareOperationHeaderData);
        
        //FND-15735
        boolean compareOperationProcess = false;
        
//        if(compareOperationHeaderData)
//        {
	        // Compare Operation Process
	        compareOperationProcess = compareOperationProcess(operationNumber,
	                                                          fromPlanId,
	                                                          fromPlanVersion,
	                                                          fromPlanRevision,
	                                                          fromPlanAlterations,
	                                                          toPlanId,
	                                                          toPlanVersion,
	                                                          toPlanRevision,
	                                                          toPlanAlterations,
	                                                          fromOrderId,
	                                                          toOrderId,
	                                                          fromType,
	                                                          toType, 
	                                                          booleanMap,
	                                                          fromAltId, // FND-25023
	                                                          toAltId, 
	                                                          fromStdOperMap, //GE-1668 
	                                                          toStdOperMap);
	        
	        booleanMap.put("compareOperationProcess", compareOperationProcess);
//        }
        
        //FND-15735
        boolean compareOperationSkill = false;
        
//        if(compareOperationHeaderData && compareOperationProcess)
//        {
	        // Compare Operation Skill
	        compareOperationSkill = compareOperationSkill(operationNumber,
	                                                      fromPlanId,
	                                                      fromPlanVersion,
	                                                      fromPlanRevision,
	                                                      fromPlanAlterations,
	                                                      toPlanId,
	                                                      toPlanVersion,
	                                                      toPlanRevision,
	                                                      toPlanAlterations,
	                                                      fromOrderId,
	                                                      toOrderId,
	                                                      fromType,
	                                                      toType, 
	                                                      booleanMap,
	                                                      fromAltId, // FND-25023
	                                                      toAltId, 
	                                                      fromStdOperMap, //GE-1668
	                                                      toStdOperMap);
	        
	        booleanMap.put("compareOperationSkill", compareOperationSkill);
//        }
	        boolean compareOperationStandards = false;

	        //GE-1815
	        // Compare Operation Standards
	        compareOperationStandards = compareOperationStandards(operationNumber,
	        													  fromPlanId,
	        													  fromPlanVersion,
	        													  fromPlanRevision,
	        													  fromPlanAlterations,
	        													  toPlanId,
	        													  toPlanVersion,
	        													  toPlanRevision,
	        													  toPlanAlterations,
	        													  fromOrderId,
	        													  toOrderId,
	        													  fromType,
	        													  toType,
	        													  booleanMap,
	        													  fromAltId, // FND-25023
	        													  toAltId, 
	        													  fromStdOperMap, //GE-1668
	        													  toStdOperMap);

	        booleanMap.put("compareOperationStandards", compareOperationStandards);
        
        //FND-15735
        boolean compareOperationPrecedence = false;
        
//        if(compareOperationHeaderData && compareOperationProcess
//        		&& compareOperationSkill)
//        {
	        // Compare Operation Precedence
	        compareOperationPrecedence = compareOperationPrecedence(operationNumber,
	        													    fromPlanId,
	        													    fromPlanVersion,
	        													    fromPlanRevision,
	        													    fromPlanAlterations,
	        													    toPlanId,
	        													    toPlanVersion,
	        													    toPlanRevision,
	        													    toPlanAlterations,
	        													    fromOrderId,
	        													    toOrderId,
	        													    fromType,
	        													    toType, 
	        													    booleanMap,
	        													    fromAltId, // FND-25023
	                                                                toAltId, 
	                                                                fromStdOperMap, //GE-1668
	                                                                toStdOperMap);
	        
	        booleanMap.put("compareOperationPrecedence", compareOperationPrecedence);
//        }

        if (compareOperationHeaderData && compareOperationProcess
                && compareOperationSkill && compareOperationPrecedence)
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Operation Parts
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param textType
     *            the Text Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Parts are same
     *         returns false when both From and To Operation Parts are not same
     */
    private boolean compareOperationParts(String fromPlanId,
                                          Number fromOperationKey,
                                          Number fromStepKey,
                                          Number fromStepUpdateNumber,
                                          Number fromOperationUpdateNumber,
                                          String toPlanId,
                                          Number toOperationKey,
                                          Number toStepKey,
                                          Number toStepUpdateNumber,
                                          Number toOperationUpdateNumber,
                                          String textType,
                                          String tableName,
                                          boolean isOperation,
                                          String fromOrderId,
                                          String toOrderId,
                                          String fromType,
                                          String toType,
                                          String fromAltId, // FND-25023
                                          String toAltId)
    {
        boolean compare = false;

        List fromOperationParts = new ArrayList();
        List toOperationParts = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Parts for From Plan
            fromOperationParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                     fromOperationKey,
                                                                     fromStepKey,
                                                                     fromStepUpdateNumber,
                                                                     fromOperationUpdateNumber,
                                                                     textType,
                                                                     tableName,
                                                                     isOperation);

            // Selects the Operation Parts for To Plan
            toOperationParts = compareToolDao.selectOperationParts(toPlanId,
                                                                   toOperationKey,
                                                                   toStepKey,
                                                                   toStepUpdateNumber,
                                                                   toOperationUpdateNumber,
                                                                   textType,
                                                                   tableName,
                                                                   isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Parts for From Order
                fromOperationParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);
            }
            else
            {
                //FND-25023 Selects the Operation Parts in Alteration for From Order
                fromOperationParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                               fromOperationKey,
                                                                               fromStepKey,
                                                                               textType,
                                                                               fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                //Selects the Operation Parts for TO Order
                toOperationParts = compareToolDao.selectOperationParts(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             textType);
            }
            else
            {
              //FND-25023 Selects the Operation Parts in Alteration for TO Order
                toOperationParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             textType,
                                                                             toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Parts for From Plan
                fromOperationParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

                // Selects the Operation Parts for TO Order
                toOperationParts = compareToolDao.selectOperationParts(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Parts for From Order
                fromOperationParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);

                // Selects the Operation Parts for To Plan
                toOperationParts = compareToolDao.selectOperationParts(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
            }
        }
        
        // Compares the Lists
        compare = compareList(fromOperationParts, toOperationParts);

        return compare;
    }

    // FND-22719
    /**
     * 
     * @param fromPlanId
     * @param fromOperationKey
     * @param fromOperationUpdateNumber
     * @param fromStepKey
     * @param fromStepUpdateNumber
     * @param toPlanId
     * @param toOperationKey
     * @param toOperationUpdateNumber
     * @param toStepKey
     * @param toStepUpdateNumber
     * @param textType
     * @param tableName
     * @param isOperation
     * @param fromOrderId
     * @param toOrderId
     * @param fromType
     * @param toType
     * @param fromAltId
     * @param toAltId
     * @return
     */
    private boolean compareOperationImageList(String fromPlanId,
									          Number fromOperationKey,
									          Number fromOperationUpdateNumber,
									          Number fromStepKey,
									          Number fromStepUpdateNumber,
									          String toPlanId,
									          Number toOperationKey,
									          Number toOperationUpdateNumber,
									          Number toStepKey,
									          Number toStepUpdateNumber,
									          String textType,
									          String tableName,
									          boolean isOperation,
									          String fromOrderId,
									          String toOrderId,
									          String fromType,
									          String toType,
									          String fromAltId, // FND-25023
									          String toAltId)
	{
		boolean compare = false;
		
		List fromOperationImageList = new ArrayList();
		List toOperationImageList = new ArrayList();
		
		if (equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(fromType, toType))
		{
			// Selects the Operation Images for From Plan
			fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( fromPlanId,
														                                       fromOperationKey,
														                                       fromOperationUpdateNumber,
														                                       fromStepKey,
														                                       fromStepUpdateNumber,
														                                       textType,
														                                       tableName,
														                                       isOperation );
			
			// Selects the Operation Images for To Plan
			toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( toPlanId,
														                                     toOperationKey,
														                                     toOperationUpdateNumber,
														                                     toStepKey,
														                                     toStepUpdateNumber,
														                                     textType,
														                                     tableName,
														                                     isOperation );
		}
		else if (equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(fromType, toType))
		{
		    if(isEmpty( fromAltId ))
		    {
    			// Selects the Operation Images for From Order
    			fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( fromOrderId,
    														                                       fromOperationKey,
    														                                       fromStepKey,
    														                                       textType );
		    }
		    else
		    {
		        //FND-25023 Selects the Operation Images in Alteration for From Order
                fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControlAltLog( fromOrderId,
                                                                                                         fromOperationKey,
                                                                                                         fromStepKey,
                                                                                                         textType,
                                                                                                         fromAltId);
		    }
		    
		    if(isEmpty( toAltId ))
		    {
    			// Selects the Operation Images for TO Order
    			toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( toOrderId,
    														                                     toOperationKey,
    														                                     toStepKey,
    														                                     textType );
		    }
		    else
		    {
		        //FND-25023 Selects the Operation Images in Alteration for TO Order
                toOperationImageList = compareToolDao.selectOperationImagesfromImageListControlAltLog( toOrderId,
                                                                                                       toOperationKey,
                                                                                                       toStepKey,
                                                                                                       textType,
                                                                                                       toAltId);
		    }
		}
		else
		{
			if (equalsIgnoreCase(fromType, "PLAN") && equalsIgnoreCase(toType, "ORDER"))
			{
				// Selects the Operation Images for From Plan
				fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( fromPlanId,
														                                           fromOperationKey,
														                                           fromOperationUpdateNumber,
														                                           fromStepKey,
														                                           fromStepUpdateNumber,
														                                           textType,
														                                           tableName,
														                                           isOperation );
				
				// Selects the Operation Images for TO Order
				toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( toOrderId,
														                                         toOperationKey,
														                                         toStepKey,
														                                         textType );
			}
			else if (equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(toType, "PLAN"))
			{
				// Selects the Operation Images for From Order
				fromOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( fromOrderId,
														                                           fromOperationKey,
														                                           fromStepKey,
														                                           textType );
				
				// Selects the Operation Images for To Plan
				toOperationImageList = compareToolDao.selectOperationImagesfromImageListControl( toPlanId,
														                                         toOperationKey,
														                                         toOperationUpdateNumber,
														                                         toStepKey,
														                                         toStepUpdateNumber,
														                                         textType,
														                                         tableName,
														                                         isOperation );
			}
		}
		
		// Compares the Lists
		compare = compareList(fromOperationImageList, toOperationImageList);
		
		return compare;
	}
    
    /**
     * Compares the Operation Tools
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param textType
     *            the Text Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation boolean
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Tools are same
     *         returns false when both From and To Operation Tools are not same
     */
    private boolean compareOperationTools(String fromPlanId,
                                          Number fromOperationKey,
                                          Number fromStepKey,
                                          Number fromStepUpdateNumber,
                                          Number fromOperationUpdateNumber,
                                          String toPlanId,
                                          Number toOperationKey,
                                          Number toStepKey,
                                          Number toStepUpdateNumber,
                                          Number toOperationUpdateNumber,
                                          String textType,
                                          String tableName,
                                          boolean isOperation,
                                          String fromOrderId,
                                          String toOrderId,
                                          String fromType,
                                          String toType,
                                          String fromAltId, // FND-25023
                                          String toAltId)
    {
        boolean compare = false;

        List fromOperationTools = new ArrayList();
        List toOperationTools = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Tools for From Plan
            fromOperationTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                     fromOperationKey,
                                                                     fromStepKey,
                                                                     fromStepUpdateNumber,
                                                                     fromOperationUpdateNumber,
                                                                     textType,
                                                                     tableName,
                                                                     isOperation);

            // Selects the Operation Tools for To Plan
            toOperationTools = compareToolDao.selectOperationTools(toPlanId,
                                                                   toOperationKey,
                                                                   toStepKey,
                                                                   toStepUpdateNumber,
                                                                   toOperationUpdateNumber,
                                                                   textType,
                                                                   tableName,
                                                                   isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Tools for From Order
                fromOperationTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);
            }
            else
            {
                //FND-25023 Selects the Operation Tools in Alteration for From Order
                fromOperationTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                               fromOperationKey,
                                                                               fromStepKey,
                                                                               textType,
                                                                               fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                // Selects the Operation Tools for TO Order
                toOperationTools = compareToolDao.selectOperationTools(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else
            {
                //FND-25023 Selects the Operation Tools in Alteration for TO Order
                toOperationTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType,
                                                                       toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Tools for From Plan
                fromOperationTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

                // Selects the Operation Tools for TO Order
                toOperationTools = compareToolDao.selectOperationTools(toOrderId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Tools for From Order
                fromOperationTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         textType);

                // Selects the Operation Tools for To Plan
                toOperationTools = compareToolDao.selectOperationTools(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
            }
        }
        // Compares the Lists
        compare = compareList(fromOperationTools, toOperationTools);

        return compare;
    }

    /**
     * Compares the Operation Buyoffs
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param textType
     *            the Text Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Buyoffs are same
     *         returns false when both From and To Operation Buyoffs are not
     *         same
     */
    private boolean compareOperationBuyoffs(String fromPlanId,
                                            Number fromOperationKey,
                                            Number fromStepKey,
                                            Number fromStepUpdateNumber,
                                            Number fromOperationUpdateNumber,
                                            String toPlanId,
                                            Number toOperationKey,
                                            Number toStepKey,
                                            Number toStepUpdateNumber,
                                            Number toOperationUpdateNumber,
                                            String textType,
                                            String tableName,
                                            boolean isOperation,
                                            String fromOrderId,
                                            String toOrderId,
                                            String fromType,
                                            String toType,
                                            String fromAltId, // FND-25023
                                            String toAltId)
    {
        boolean compare = false;

        List fromOperationBuyoffs = new ArrayList();
        List toOperationBuyoffs = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Buyoffs for From Plan
            fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                         fromOperationKey,
                                                                         fromStepKey,
                                                                         fromStepUpdateNumber,
                                                                         fromOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         isOperation);

            // Selects the Operation Buyoffs for To Plan
            toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                       toOperationKey,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       toOperationUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Buyoffs for From Order
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             textType);
            }
            else
            {
                //FND-25023 Selects the Operation Buyoffs in Alteration for From Order
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                   fromOperationKey,
                                                                                   fromStepKey,
                                                                                   textType,
                                                                                   fromAltId);
            }

            if(isEmpty( toAltId ))
            {
                //Selects the Operation Buyoffs for TO Order
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           textType);
            }
            else
            {
                //FND-25023 Selects the Operation Buyoffs in Alteration for TO Order
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                 toOperationKey,
                                                                                 toStepKey,
                                                                                 textType,
                                                                                 toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Buyoffs for From Plan
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             fromStepUpdateNumber,
                                                                             fromOperationUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             isOperation);

                // Selects the Operation Buyoffs for TO Order
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Buyoffs for From Order
                fromOperationBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             textType);

                // Selects the Operation Buyoffs for To Plan
                toOperationBuyoffs = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           toStepUpdateNumber,
                                                                           toOperationUpdateNumber,
                                                                           textType,
                                                                           tableName,
                                                                           isOperation);
            }
        }

        // Compares the Lists
        compare = compareList(fromOperationBuyoffs, toOperationBuyoffs);

        return compare;
    }

    /**
     * Compares the Operation Data Collection
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Data Collection are
     *         same returns false when both From and To Operation Data
     *         Collection are not same
     */
    private boolean compareOperationDataCollection(String fromPlanId,
                                                   Number fromOperationKey,
                                                   Number fromOperationUpdateNumber,
                                                   Number fromStepKey,
                                                   Number fromStepUpdateNumber,
                                                   String toPlanId,
                                                   Number toOperationKey,
                                                   Number toOperationUpdateNumber,
                                                   Number toStepKey,
                                                   Number toStepUpdateNumber,
                                                   String textType,
                                                   String tableName,
                                                   boolean isOperation,
                                                   String fromOrderId,
                                                   String toOrderId,
                                                   String fromType,
                                                   String toType,
                                                   String fromAltId, // FND-25023
                                                   String toAltId)
    {
        boolean compare = false;

        List fromOperationDataCollection = new ArrayList();
        List toOperationDataCollection = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Data Collection for From Plan
            fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                       fromOperationKey,
                                                                                       fromStepKey,
                                                                                       fromStepUpdateNumber,
                                                                                       fromOperationUpdateNumber,
                                                                                       textType,
                                                                                       tableName,
                                                                                       isOperation,
                                                                                       null); // GE-1353

            // Selects the Operation Data Collection for To Plan
            toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                     toOperationKey,
                                                                                     toStepKey,
                                                                                     toStepUpdateNumber,
                                                                                     toOperationUpdateNumber,
                                                                                     textType,
                                                                                     tableName,
                                                                                     isOperation,
                                                                                     null); // GE-1353
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           null); // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                 fromOperationKey,
                                                                                                 fromStepKey,
                                                                                                 textType,
                                                                                                 fromAltId,
                                                                                                 null); // GE-1353
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         null); // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                               toOperationKey,
                                                                                               toStepKey,
                                                                                               textType,
                                                                                               toAltId,
                                                                                               null); // GE-1353
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Data Collection for From Plan
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           fromStepUpdateNumber,
                                                                                           fromOperationUpdateNumber,
                                                                                           textType,
                                                                                           tableName,
                                                                                           isOperation,
                                                                                           null); // GE-1353

                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         null); // GE-1353
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           null); // GE-1353

                // Selects the Operation Data Collection for To Plan
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         toStepUpdateNumber,
                                                                                         toOperationUpdateNumber,
                                                                                         textType,
                                                                                         tableName,
                                                                                         isOperation,
                                                                                         null); // GE-1353
            }
        }

        // Compares the Lists
        compare = compareList(fromOperationDataCollection, toOperationDataCollection);

        return compare;
    }
    
    private boolean compareOperationCDC(String fromPlanId,
                                        Number fromOperationKey,
                                        Number fromOperationUpdateNumber,
                                        Number fromStepKey,
                                        Number fromStepUpdateNumber,
                                        String toPlanId,
                                        Number toOperationKey,
                                        Number toOperationUpdateNumber,
                                        Number toStepKey,
                                        Number toStepUpdateNumber,
                                        String textType,
                                        String tableName,
                                        boolean isOperation,
                                        String fromOrderId,
                                        String toOrderId,
                                        String fromType,
                                        String toType,
                                        String fromAltId, // FND-25023
                                        String toAltId)
    {
        boolean compare = false;

        List fromOperationDataCollection = new ArrayList();
        List toOperationDataCollection = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Data Collection for From Plan
            fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                       fromOperationKey,
                                                                                       fromStepKey,
                                                                                       fromStepUpdateNumber,
                                                                                       fromOperationUpdateNumber,
                                                                                       textType,
                                                                                       tableName,
                                                                                       isOperation,
                                                                                       CALCULATED_DC);  // GE-1353

            // Selects the Operation Data Collection for To Plan
            toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                     toOperationKey,
                                                                                     toStepKey,
                                                                                     toStepUpdateNumber,
                                                                                     toOperationUpdateNumber,
                                                                                     textType,
                                                                                     tableName,
                                                                                     isOperation,
                                                                                     CALCULATED_DC);  // GE-1353
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           CALCULATED_DC);  // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                 fromOperationKey,
                                                                                                 fromStepKey,
                                                                                                 textType,
                                                                                                 fromAltId,
                                                                                                 CALCULATED_DC);  // GE-1353
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         CALCULATED_DC);  // GE-1353
            }
            else
            {
                //FND-25023 Selects the Operation Data Collection in Alteration for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                               toOperationKey,
                                                                                               toStepKey,
                                                                                               textType,
                                                                                               toAltId,
                                                                                               CALCULATED_DC);  // GE-1353
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Data Collection for From Plan
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           fromStepUpdateNumber,
                                                                                           fromOperationUpdateNumber,
                                                                                           textType,
                                                                                           tableName,
                                                                                           isOperation,
                                                                                           CALCULATED_DC);  // GE-1353

                // Selects the Operation Data Collection for To Order
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         textType,
                                                                                         CALCULATED_DC);  // GE-1353
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Data Collection for From Order
                fromOperationDataCollection = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                           fromOperationKey,
                                                                                           fromStepKey,
                                                                                           textType,
                                                                                           CALCULATED_DC);  // GE-1353

                // Selects the Operation Data Collection for To Plan
                toOperationDataCollection = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                         toOperationKey,
                                                                                         toStepKey,
                                                                                         toStepUpdateNumber,
                                                                                         toOperationUpdateNumber,
                                                                                         textType,
                                                                                         tableName,
                                                                                         isOperation,
                                                                                         CALCULATED_DC);  // GE-1353
            }
        }

        // Compares the Lists
        compare = compareList(fromOperationDataCollection, toOperationDataCollection);

        return compare;
    }

    /**
     * Compare the Operation Standard Text
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param objectType
     *            the Object Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation Boolean
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Standard Text are
     *         same returns false when both From and To Operation Standard Text
     *         are not same
     */
    private boolean compareOperationStandardText(String fromPlanId,
                                                 Number fromOperationKey,
                                                 Number fromOperationUpdateNumber,
                                                 Number fromStepKey,
                                                 Number fromStepUpdateNumber,
                                                 String toPlanId,
                                                 Number toOperationKey,
                                                 Number toOperationUpdateNumber,
                                                 Number toStepKey,
                                                 Number toStepUpdateNumber,
                                                 String textType,
                                                 String objectType,
                                                 String tableName,
                                                 boolean isOperation,
                                                 String fromOrderId,
                                                 String toOrderId,
                                                 String fromType,
                                                 String toType,
                                                 String fromAltId, // FND-25023
                                                 String toAltId)
    {
        boolean compare = false;
        List fromOperationStandardText = new ArrayList();
        List toOperationStandardText = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Standard Text for From Plan Operation
            fromOperationStandardText = compareToolDao.selectOperationStandardText(fromPlanId,
                                                                                   fromOperationKey,
                                                                                   fromOperationUpdateNumber,
                                                                                   textType,
                                                                                   objectType,
                                                                                   fromStepKey,
                                                                                   fromStepUpdateNumber,
                                                                                   tableName,
                                                                                   isOperation);

            // Selects the Operation Standard Text for To Plan Operation
            toOperationStandardText = compareToolDao.selectOperationStandardText(toPlanId,
                                                                                 toOperationKey,
                                                                                 toOperationUpdateNumber,
                                                                                 textType,
                                                                                 objectType,
                                                                                 toStepKey,
                                                                                 toStepUpdateNumber,
                                                                                 tableName,
                                                                                 isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Standard Text for From Order Operation
                fromOperationStandardText = compareToolDao.selectOperationStandardText(fromOrderId,
                                                                                       fromOperationKey,
                                                                                       fromStepKey,
                                                                                       textType,
                                                                                       objectType);
            }
            else
            {
                //FND-25023 Selects the Operation Standard Text in Alteration for From Order Operation
                fromOperationStandardText = compareToolDao.selectOperationStandardTextAltLog(fromOrderId,
                                                                                             fromOperationKey,
                                                                                             fromStepKey,
                                                                                             textType,
                                                                                             objectType,
                                                                                             fromAltId);
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Standard Text for To Order Operation
                toOperationStandardText = compareToolDao.selectOperationStandardText(toOrderId,
                                                                                     toOperationKey,
                                                                                     toStepKey,
                                                                                     textType,
                                                                                     objectType);
            }
            else
            {
                //FND-25023 Selects the Operation Standard Text in Alteration for To Order Operation
                toOperationStandardText = compareToolDao.selectOperationStandardTextAltLog(toOrderId,
                                                                                           toOperationKey,
                                                                                           toStepKey,
                                                                                           textType,
                                                                                           objectType,
                                                                                           toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Standard Text for From Plan Operation
                fromOperationStandardText = compareToolDao.selectOperationStandardText(fromPlanId,
                                                                                       fromOperationKey,
                                                                                       fromOperationUpdateNumber,
                                                                                       textType,
                                                                                       objectType,
                                                                                       fromStepKey,
                                                                                       fromStepUpdateNumber,
                                                                                       tableName,
                                                                                       isOperation);

                // Selects the Operation Standard Text for To Order Operation
                toOperationStandardText = compareToolDao.selectOperationStandardText(toOrderId,
                                                                                     toOperationKey,
                                                                                     toStepKey,
                                                                                     textType,
                                                                                     objectType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Standard Text for From Order Operation
                fromOperationStandardText = compareToolDao.selectOperationStandardText(fromOrderId,
                                                                                       fromOperationKey,
                                                                                       fromStepKey,
                                                                                       textType,
                                                                                       objectType);

                // Selects the Operation Standard Text for To Plan Operation
                toOperationStandardText = compareToolDao.selectOperationStandardText(toPlanId,
                                                                                     toOperationKey,
                                                                                     toOperationUpdateNumber,
                                                                                     textType,
                                                                                     objectType,
                                                                                     toStepKey,
                                                                                     toStepUpdateNumber,
                                                                                     tableName,
                                                                                     isOperation);
            }
        }

        compare = compareURLParams(fromOperationStandardText,
                                   toOperationStandardText);

        return compare;
    }
    
    /**
     * Compare the Operation Text
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param objectType
     *            the Object Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation Boolean
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param stepNumber TODO
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Operation Text are
     *         same returns false when both From and To Operation Text
     *         are not same
     */
    private boolean compareOperationText(String fromPlanId,
                                         Number fromOperationKey,
                                         Number fromOperationUpdateNumber,
                                         Number fromStepKey,
                                         Number fromStepUpdateNumber,
                                         String toPlanId,
                                         Number toOperationKey,
                                         Number toOperationUpdateNumber,
                                         Number toStepKey,
                                         Number toStepUpdateNumber,
                                         String textType,
                                         String objectType,
                                         String tableName,
                                         boolean isOperation,
                                         String fromOrderId,
                                         String toOrderId,
                                         String fromType,
                                         String toType, 
                                         String stepNumber,  //FND-23086
                                         String fromAltId, // FND-25023
                                         String toAltId)
    {
        boolean compare = false;
        List fromOperationText = new ArrayList();
        List toOperationText = new ArrayList();
        isOperation = false;
        
        tableName = "SFPL_STEP_TEXT";

        if (toStepKey.intValue() == -1 || fromStepKey.intValue() == -1 || equalsIgnoreCase(stepNumber, ELLIPSIS))
        {
            tableName = "SFPL_OPERATION_TEXT";
            isOperation = true;
        }
        
        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Standard Text for From Plan Operation
            fromOperationText = compareToolDao.selectOperationText(fromPlanId,
                                                                   fromOperationKey,
                                                                   fromOperationUpdateNumber,
                                                                   textType,
                                                                   fromStepKey,
                                                                   fromStepUpdateNumber,
                                                                   tableName,
                                                                   isOperation);

            // Selects the Operation Standard Text for To Plan Operation
            toOperationText = compareToolDao.selectOperationText(toPlanId,
                                                                 toOperationKey,
                                                                 toOperationUpdateNumber,
                                                                 textType,
                                                                 toStepKey,
                                                                 toStepUpdateNumber,
                                                                 tableName,
                                                                 isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            if(isEmpty( fromAltId ))
            {
                // Selects the Operation Standard Text for From Order Operation
                fromOperationText = compareToolDao.selectOperationText(fromOrderId,
                                                                       fromOperationKey,
                                                                       fromStepKey,
                                                                       textType);
            }
            else
            {
                //FND-25023 Selects the Operation Standard Text in Alteration for From Order Operation
                fromOperationText = compareToolDao.selectOperationTextAltLog(fromOrderId,
                                                                             fromOperationKey,
                                                                             fromStepKey,
                                                                             textType,
                                                                             fromAltId); 
            }
            
            if(isEmpty( toAltId ))
            {
                // Selects the Operation Standard Text for To Order Operation
                toOperationText = compareToolDao.selectOperationText(toOrderId,
                                                                     toOperationKey,
                                                                     toStepKey,
                                                                     textType);
            }
            else
            {
                //FND-25023 Selects the Operation Standard Text in Alteration for To Order Operation
                toOperationText = compareToolDao.selectOperationTextAltLog(toOrderId,
                                                                           toOperationKey,
                                                                           toStepKey,
                                                                           textType,
                                                                           toAltId);
            }
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Standard Text for From Plan Operation
                fromOperationText = compareToolDao.selectOperationText(fromPlanId,
                                                                       fromOperationKey,
                                                                       fromOperationUpdateNumber,
                                                                       textType,
                                                                       fromStepKey,
                                                                       fromStepUpdateNumber,
                                                                       tableName,
                                                                       isOperation);

                // Selects the Operation Standard Text for To Order Operation
                toOperationText = compareToolDao.selectOperationText(toOrderId,
                                                                     toOperationKey,
                                                                     toStepKey,
                                                                     textType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Standard Text for From Order Operation
                fromOperationText = compareToolDao.selectOperationText(fromOrderId,
                                                                       fromOperationKey,
                                                                       fromStepKey,
                                                                       textType);

                // Selects the Operation Standard Text for To Plan Operation
                toOperationText = compareToolDao.selectOperationText(toPlanId,
                                                                     toOperationKey,
                                                                     toOperationUpdateNumber,
                                                                     textType,
                                                                     toStepKey,
                                                                     toStepUpdateNumber,
                                                                     tableName,
                                                                     isOperation);
            }
        }

        if (equalsIgnoreCase(fromOperationText.toString(), toOperationText.toString()))
        {
            compare = true;
        }
        
        return compare;
    }

    // FND-13569
    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#comparePlanHeader(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number)
     */
    public List comparePlanHeader(String fromPlanId,
                                  Number fromPlanVersion,
                                  Number fromPlanRevision,
                                  Number fromPlanAlterations,
                                  String toPlanId,
                                  Number toPlanVersion,
                                  Number toPlanRevision,
                                  Number toPlanAlterations, 
                                  String calledFromChgAck)
    {
        // Declares the local variable.
        List returnList = new ArrayList();
        Map returnMapPartNo = new ListOrderedMap();
        Map returnMapPartChg = new ListOrderedMap();
        Map returnMapItemType = new ListOrderedMap();
        Map returnMapItemSubtype = new ListOrderedMap();
        Map returnMapProgram = new ListOrderedMap();
        Map returnMapPlanVersion = new ListOrderedMap();
        Map returnMapPlanRevision = new ListOrderedMap();
        Map returnMapPlanAlterations = new ListOrderedMap();
        Map returnMapPlanUpdtNo = new ListOrderedMap();
        Map returnMapPlanState = new ListOrderedMap();
        Map returnMapRevisionStatus = new ListOrderedMap();
        Map returnMapRevisionLockState = new ListOrderedMap();
        Map returnMapRevLockedBy = new ListOrderedMap();
        Map returnMapPlanTitle = new ListOrderedMap();
        Map returnMapPlanStatus = new ListOrderedMap();
        Map returnMapOrderQty = new ListOrderedMap();
        Map returnMapCustId = new ListOrderedMap();
        Map returnMapOrderUOM = new ListOrderedMap();
        Map returnMapModel = new ListOrderedMap();
        Map returnMapPlanningGroup = new ListOrderedMap();
        Map returnMapWorkLoc = new ListOrderedMap();
        Map returnMapEngItem = new ListOrderedMap();
        Map returnMapEngItemRev = new ListOrderedMap();
        Map returnMapEngGroup = new ListOrderedMap();
        Map returnMapInitialStores = new ListOrderedMap();
        Map returnMapFinalStores = new ListOrderedMap();
        Map returnMapPlanType = new ListOrderedMap();
        Map returnMapMBOMRev = new ListOrderedMap();
        Map returnMapProject = new ListOrderedMap();
        Map returnMapNeedsReview = new ListOrderedMap();
        Map returnMapSerialFlag = new ListOrderedMap();
        Map returnMapLotFlag = new ListOrderedMap();
        Map returnMapLtaSendFlag = new ListOrderedMap();
        Map returnMapAuthType = new ListOrderedMap();
        Map returnMapAuthNo = new ListOrderedMap();
        Map returnMapAuthNote = new ListOrderedMap();
        Map returnMapPlanNumber = new ListOrderedMap();
        Map returnMapCondition = new ListOrderedMap();
        Map returnMapDisplaySequence = new ListOrderedMap();
        Map returnMapOperationOverlapFlag = new ListOrderedMap();
        Map returnMapInspectionPlanNo= new ListOrderedMap(); // FND-23467
        // FND-22945
        Map returnMapPPVRequiredFlagMap= new ListOrderedMap();
        Map returnMapPPVTypeMap= new ListOrderedMap();
        Map returnMapPPVQuatityMap= new ListOrderedMap();
        Map returnMapPPVPendingQuatityMap= new ListOrderedMap();
        Map returnMapPPVInProcessQuatityMap= new ListOrderedMap();
        Map returnMapPPVCompleteQuatityMap= new ListOrderedMap();
        Map returnMapLastPPVCompleteDateMap= new ListOrderedMap(); // FND-25218

        // Selects the Plan Header Data for From Plan
        Map fromPlanHeaderMap = compareToolDao.selectPlanHeaderData(fromPlanId,
                                                                    fromPlanVersion,
                                                                    fromPlanRevision,
                                                                    fromPlanAlterations);

        // Selects the Plan Header Data for To Plan
        Map toPlanHeaderMap = compareToolDao.selectPlanHeaderData(toPlanId,
                                                                  toPlanVersion,
                                                                  toPlanRevision,
                                                                  toPlanAlterations);

        String fromSecurityGroup = (String) fromPlanHeaderMap.get("SECURITY_GROUP");
        String toSecurityGroup = (String) toPlanHeaderMap.get("SECURITY_GROUP");
        String fromDocumentType = (String) fromPlanHeaderMap.get("DOC_TYPE");
        
        boolean fromSecurityOk = SQLSecurityManager.getInstance().canAccess(fromSecurityGroup);
        boolean toSecurityOk = SQLSecurityManager.getInstance().canAccess(toSecurityGroup);
        
        if (!fromSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the from plan.");
        }
        else if (!toSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the to plan.");
        }

        // Sets the records of return list.
        
        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK) && //FND-26015
                !equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT) ) //FND-25065
        {
        	returnMapPlanNumber.put("TITLE", "Plan Number");
        	returnMapPlanNumber.put("SECURITY_GROUP", null);
        	returnMapPlanNumber.put("FROM_PLAN", fromPlanHeaderMap.get("PLAN_NO"));
        	returnMapPlanNumber.put("TO_PLAN", toPlanHeaderMap.get("PLAN_NO"));
        	returnList.add(returnMapPlanNumber);
        }
        
        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK) && //FND-26015
                !equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT) ) //FND-25065
        {
        	returnMapPlanTitle.put("TITLE", "Plan Title");
        	returnMapPlanTitle.put("SECURITY_GROUP", null);
        	returnMapPlanTitle.put("FROM_PLAN", fromPlanHeaderMap.get("PLAN_TITLE"));
        	returnMapPlanTitle.put("TO_PLAN", toPlanHeaderMap.get("PLAN_TITLE"));
        	returnList.add(returnMapPlanTitle);
        }
        
        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanVersion.put("TITLE", "Plan Version");
        }
        else
        {
        	returnMapPlanVersion.put("TITLE", "Std Oper Version");
        }
        returnMapPlanVersion.put("SECURITY_GROUP", null);
        returnMapPlanVersion.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("PLAN_VERSION")
                                                  .toString());
        returnMapPlanVersion.put("TO_PLAN", toPlanHeaderMap.get("PLAN_VERSION")
                                                           .toString());
        returnList.add(returnMapPlanVersion);

        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanRevision.put("TITLE", "Plan Revision");
        }
        else
        {
        	returnMapPlanRevision.put("TITLE", "Std Oper Revision");
        }
        returnMapPlanRevision.put("SECURITY_GROUP", null);
        returnMapPlanRevision.put("FROM_PLAN",
                                  fromPlanHeaderMap.get("PLAN_REVISION")
                                                   .toString());
        returnMapPlanRevision.put("TO_PLAN",
                                  toPlanHeaderMap.get("PLAN_REVISION")
                                                 .toString());
        returnList.add(returnMapPlanRevision);

        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanAlterations.put("TITLE", "Plan Alterations");
        }
        else
        {
        	returnMapPlanAlterations.put("TITLE", "Std Oper Alterations");
        }
        returnMapPlanAlterations.put("SECURITY_GROUP",
                                     null);
        returnMapPlanAlterations.put("FROM_PLAN",
                                     fromPlanHeaderMap.get("PLAN_ALTERATIONS")
                                                      .toString());
        returnMapPlanAlterations.put("TO_PLAN",
                                     toPlanHeaderMap.get("PLAN_ALTERATIONS")
                                                    .toString());
        returnList.add(returnMapPlanAlterations);

        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanUpdtNo.put("TITLE", "Plan Update Number");
        }
        else
        {
        	returnMapPlanUpdtNo.put("TITLE", "Std Oper Update Number");
        }
        returnMapPlanUpdtNo.put("SECURITY_GROUP", null);
        returnMapPlanUpdtNo.put("FROM_PLAN",
                                fromPlanHeaderMap.get("PLAN_UPDT_NO")
                                                 .toString());
        returnMapPlanUpdtNo.put("TO_PLAN", toPlanHeaderMap.get("PLAN_UPDT_NO")
                                                          .toString());
        returnList.add(returnMapPlanUpdtNo);
 
        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK) && //FND-26015
                !equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT) ) //FND-25065
        {
        	returnMapProject.put("TITLE", "Project");
        	returnMapProject.put("SECURITY_GROUP", null);
        	returnMapProject.put("FROM_PLAN", fromPlanHeaderMap.get("PROJECT"));
        	returnMapProject.put("TO_PLAN", toPlanHeaderMap.get("PROJECT"));
        	returnList.add(returnMapProject);
        }
        
        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanState.put("TITLE", "Plan State");
        }
        else
        {
        	returnMapPlanState.put("TITLE", "Std Oper State");
        }
        returnMapPlanState.put("SECURITY_GROUP", null);
        returnMapPlanState.put("FROM_PLAN", fromPlanHeaderMap.get("PLAN_STATE"));
        returnMapPlanState.put("TO_PLAN", toPlanHeaderMap.get("PLAN_STATE"));
        returnList.add(returnMapPlanState);

        String fromRevisionStatus = (String) fromPlanHeaderMap.get("REV_STATUS");
        if(equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT) && equalsIgnoreCase(fromRevisionStatus, PLAN_COMPLETE))
        {
        	fromRevisionStatus = "STDOPER COMPLETE";
        }
        String toRevisionStatus = (String) toPlanHeaderMap.get("REV_STATUS");
        if(equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT) && equalsIgnoreCase(toRevisionStatus, PLAN_COMPLETE))
        {
        	toRevisionStatus = "STDOPER COMPLETE";
        }
        
        returnMapRevisionStatus.put("TITLE", "Revision Status");
        returnMapRevisionStatus.put("SECURITY_GROUP", null);
        returnMapRevisionStatus.put("FROM_PLAN", fromRevisionStatus);
        returnMapRevisionStatus.put("TO_PLAN", toRevisionStatus);
        returnList.add(returnMapRevisionStatus);

        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanStatus.put("TITLE", "Plan Status");
        }
        else
        {
        	returnMapPlanStatus.put("TITLE", "Std Oper Status");
        }
        returnMapPlanStatus.put("SECURITY_GROUP", null);
        returnMapPlanStatus.put("FROM_PLAN",
                                fromPlanHeaderMap.get("PLAN_STATUS"));
        returnMapPlanStatus.put("TO_PLAN", toPlanHeaderMap.get("PLAN_STATUS"));
        returnList.add(returnMapPlanStatus);

        returnMapPartNo.put("TITLE", "Item Number");
        returnMapPartNo.put("SECURITY_GROUP", null);
        returnMapPartNo.put("FROM_PLAN", fromPlanHeaderMap.get("PART_NO"));
        returnMapPartNo.put("TO_PLAN", toPlanHeaderMap.get("PART_NO"));
        returnList.add(returnMapPartNo);

        returnMapItemType.put("TITLE", "Item Type");
        returnMapItemType.put("SECURITY_GROUP", null);
        returnMapItemType.put("FROM_PLAN", fromPlanHeaderMap.get("ITEM_TYPE"));
        returnMapItemType.put("TO_PLAN", toPlanHeaderMap.get("ITEM_TYPE"));
        returnList.add(returnMapItemType);

        returnMapItemSubtype.put("TITLE", "Item Subtype");
        returnMapItemSubtype.put("SECURITY_GROUP", null);
        returnMapItemSubtype.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("ITEM_SUBTYPE"));
        returnMapItemSubtype.put("TO_PLAN", toPlanHeaderMap.get("ITEM_SUBTYPE"));
        returnList.add(returnMapItemSubtype);

        returnMapPartChg.put("TITLE", "Item Revision");
        returnMapPartChg.put("SECURITY_GROUP", null);
        returnMapPartChg.put("FROM_PLAN", fromPlanHeaderMap.get("PART_CHG"));
        returnMapPartChg.put("TO_PLAN", toPlanHeaderMap.get("PART_CHG"));
        returnList.add(returnMapPartChg);

        returnMapNeedsReview.put("TITLE", "Needs Review Flag");
        returnMapNeedsReview.put("SECURITY_GROUP", null);
        returnMapNeedsReview.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("NEEDS_REVIEW_FLAG"));
        returnMapNeedsReview.put("TO_PLAN",
                                 toPlanHeaderMap.get("NEEDS_REVIEW_FLAG"));
        returnList.add(returnMapNeedsReview);

        returnMapSerialFlag.put("TITLE", "Serial Flag");
        returnMapSerialFlag.put("SECURITY_GROUP", null);
        returnMapSerialFlag.put("FROM_PLAN",
                                fromPlanHeaderMap.get("SERIAL_FLAG"));
        returnMapSerialFlag.put("TO_PLAN", toPlanHeaderMap.get("SERIAL_FLAG"));
        returnList.add(returnMapSerialFlag);

        returnMapLotFlag.put("TITLE", "Lot Flag");
        returnMapLotFlag.put("SECURITY_GROUP", null);
        returnMapLotFlag.put("FROM_PLAN", fromPlanHeaderMap.get("LOT_FLAG"));
        returnMapLotFlag.put("TO_PLAN", toPlanHeaderMap.get("LOT_FLAG"));
        returnList.add(returnMapLotFlag);

        returnMapLtaSendFlag.put("TITLE", "Labor Send Flag");
        returnMapLtaSendFlag.put("SECURITY_GROUP", null);
        returnMapLtaSendFlag.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("LTA_SEND_FLAG")
                                                  .toString());
        returnMapLtaSendFlag.put("TO_PLAN",
                                 toPlanHeaderMap.get("LTA_SEND_FLAG")
                                                .toString());
        returnList.add(returnMapLtaSendFlag);

        

        returnMapAuthNo.put("TITLE", "Authorization Number");
        returnMapAuthNo.put("SECURITY_GROUP", null);
        returnMapAuthNo.put("FROM_PLAN", null);
        returnMapAuthNo.put("TO_PLAN", null);
        returnList.add(returnMapAuthNo);

        
        returnMapEngGroup.put("TITLE", "Eng Group");
        returnMapEngGroup.put("SECURITY_GROUP", null);
        returnMapEngGroup.put("FROM_PLAN", fromPlanHeaderMap.get("ENG_GROUP"));
        returnMapEngGroup.put("TO_PLAN", toPlanHeaderMap.get("ENG_GROUP"));
        returnList.add(returnMapEngGroup);

        returnMapProgram.put("TITLE", "Program");
        returnMapProgram.put("SECURITY_GROUP", null);
        returnMapProgram.put("FROM_PLAN", fromPlanHeaderMap.get("PROGRAM"));
        returnMapProgram.put("TO_PLAN", toPlanHeaderMap.get("PROGRAM"));
        returnList.add(returnMapProgram);

        returnMapPlanningGroup.put("TITLE", "Planning Group");
        returnMapPlanningGroup.put("SECURITY_GROUP", null);
        returnMapPlanningGroup.put("FROM_PLAN",
                                   fromPlanHeaderMap.get("PLG_GROUP"));
        returnMapPlanningGroup.put("TO_PLAN", toPlanHeaderMap.get("PLG_GROUP"));
        returnList.add(returnMapPlanningGroup);

        if(!equalsIgnoreCase(fromDocumentType, STANDARD_OPERATION_TEXT)) // FND-28715
        {
        	returnMapPlanType.put("TITLE", "Plan Type");
        }
        else
        {
        	returnMapPlanType.put("TITLE", "Std Oper Type");
        }
        returnMapPlanType.put("SECURITY_GROUP", null);
        returnMapPlanType.put("FROM_PLAN", fromPlanHeaderMap.get("PLAN_TYPE"));
        returnMapPlanType.put("TO_PLAN", toPlanHeaderMap.get("PLAN_TYPE"));
        returnList.add(returnMapPlanType);

        returnMapWorkLoc.put("TITLE", "Planned Work Location");
        returnMapWorkLoc.put("SECURITY_GROUP", null);
        returnMapWorkLoc.put("FROM_PLAN",
                             fromPlanHeaderMap.get("PLND_WORK_LOC"));
        returnMapWorkLoc.put("TO_PLAN", toPlanHeaderMap.get("PLND_WORK_LOC"));
        returnList.add(returnMapWorkLoc);

        returnMapEngItem.put("TITLE", "Eng Item Number");
        returnMapEngItem.put("SECURITY_GROUP", null);
        returnMapEngItem.put("FROM_PLAN", fromPlanHeaderMap.get("ENG_PART_NO"));
        returnMapEngItem.put("TO_PLAN", toPlanHeaderMap.get("ENG_PART_NO"));
        returnList.add(returnMapEngItem);

        returnMapEngItemRev.put("TITLE", "Eng Item Revision");
        returnMapEngItemRev.put("SECURITY_GROUP", null);
        returnMapEngItemRev.put("FROM_PLAN",
                                fromPlanHeaderMap.get("ENG_PART_CHG"));
        returnMapEngItemRev.put("TO_PLAN", toPlanHeaderMap.get("ENG_PART_CHG"));
        returnList.add(returnMapEngItemRev);

        returnMapRevisionLockState.put("TITLE", "Revision Lock State");
        returnMapRevisionLockState.put("SECURITY_GROUP",
                                       null);
        returnMapRevisionLockState.put("FROM_PLAN",
                                       fromPlanHeaderMap.get("REV_LOCK_STATE"));
        returnMapRevisionLockState.put("TO_PLAN",
                                       toPlanHeaderMap.get("REV_LOCK_STATE"));
        returnList.add(returnMapRevisionLockState);

        returnMapRevLockedBy.put("TITLE", "Revision Locked By");
        returnMapRevLockedBy.put("SECURITY_GROUP", null);
        returnMapRevLockedBy.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("REV_LOCKED_BY"));
        returnMapRevLockedBy.put("TO_PLAN",
                                 toPlanHeaderMap.get("REV_LOCKED_BY"));
        returnList.add(returnMapRevLockedBy);

        Number planOrderQty = (Number) fromPlanHeaderMap.get("PLND_ORDER_QTY");
        String plndOrderQty = EMPTY;
        if (planOrderQty != null)
        {
            plndOrderQty = planOrderQty.toString();
        }

        Number toPlanOrderQty = (Number) toPlanHeaderMap.get("PLND_ORDER_QTY");
        String toPlndOrderQty = EMPTY;
        if (toPlanOrderQty != null)
        {
            toPlndOrderQty = toPlanOrderQty.toString();
        }

        returnMapOrderQty.put("TITLE", "Planned Order Quantity");
        returnMapOrderQty.put("SECURITY_GROUP", null);
        returnMapOrderQty.put("FROM_PLAN", plndOrderQty);
        returnMapOrderQty.put("TO_PLAN", toPlndOrderQty);
        returnList.add(returnMapOrderQty);

        returnMapCustId.put("TITLE", "Planned Customer Id");
        returnMapCustId.put("SECURITY_GROUP", null);
        returnMapCustId.put("FROM_PLAN", fromPlanHeaderMap.get("PLND_CUST_ID"));
        returnMapCustId.put("TO_PLAN", toPlanHeaderMap.get("PLND_CUST_ID"));
        returnList.add(returnMapCustId);

        returnMapOrderUOM.put("TITLE", "Order UOM");
        returnMapOrderUOM.put("SECURITY_GROUP", null);
        returnMapOrderUOM.put("FROM_PLAN", fromPlanHeaderMap.get("ORDER_UOM"));
        returnMapOrderUOM.put("TO_PLAN", toPlanHeaderMap.get("ORDER_UOM"));
        returnList.add(returnMapOrderUOM);

        returnMapModel.put("TITLE", "Model");
        returnMapModel.put("SECURITY_GROUP", null);
        returnMapModel.put("FROM_PLAN", fromPlanHeaderMap.get("MODEL"));
        returnMapModel.put("TO_PLAN", toPlanHeaderMap.get("MODEL"));
        returnList.add(returnMapModel);

        returnMapInitialStores.put("TITLE", "Initial Stores");
        returnMapInitialStores.put("SECURITY_GROUP", null);
        returnMapInitialStores.put("FROM_PLAN",
                                   fromPlanHeaderMap.get("INITIAL_STORES"));
        returnMapInitialStores.put("TO_PLAN",
                                   toPlanHeaderMap.get("INITIAL_STORES"));
        returnList.add(returnMapInitialStores);

        returnMapFinalStores.put("TITLE", "Final Stores");
        returnMapFinalStores.put("SECURITY_GROUP", null);
        returnMapFinalStores.put("FROM_PLAN",
                                 fromPlanHeaderMap.get("FINAL_STORES"));
        returnMapFinalStores.put("TO_PLAN", toPlanHeaderMap.get("FINAL_STORES"));
        returnList.add(returnMapFinalStores);

        returnMapMBOMRev.put("TITLE", "MBOM Revision");
        returnMapMBOMRev.put("SECURITY_GROUP", null);
        returnMapMBOMRev.put("FROM_PLAN", fromPlanHeaderMap.get("MFG_BOM_CHG"));
        returnMapMBOMRev.put("TO_PLAN", toPlanHeaderMap.get("MFG_BOM_CHG"));
        returnList.add(returnMapMBOMRev);

        returnMapCondition.put("TITLE", "Condition");
        returnMapCondition.put("SECURITY_GROUP", null);
        returnMapCondition.put("FROM_PLAN", fromPlanHeaderMap.get("CONDITION"));
        returnMapCondition.put("TO_PLAN", toPlanHeaderMap.get("CONDITION"));
        returnList.add(returnMapCondition);
        
        returnMapDisplaySequence.put("TITLE", "Display Sequence");
        returnMapDisplaySequence.put("SECURITY_GROUP", null);
        returnMapDisplaySequence.put("FROM_PLAN",
                                        fromPlanHeaderMap.get("DISPLAY_SEQUENCE"));
        returnMapDisplaySequence.put("TO_PLAN", toPlanHeaderMap.get("DISPLAY_SEQUENCE"));
        returnList.add(returnMapDisplaySequence);
        
        // FND-22135
        returnMapOperationOverlapFlag.put("TITLE", "Operation Overlap?");
        returnMapOperationOverlapFlag.put("SECURITY_GROUP", null);
        returnMapOperationOverlapFlag.put("FROM_PLAN",
        									fromPlanHeaderMap.get("OPERATION_OVERLAP_FLAG"));
        returnMapOperationOverlapFlag.put("TO_PLAN", toPlanHeaderMap.get("OPERATION_OVERLAP_FLAG"));
        returnList.add(returnMapOperationOverlapFlag);
        
        // FND-23467
        returnMapInspectionPlanNo.put("TITLE", "Inspection Plan No");
        returnMapInspectionPlanNo.put("SECURITY_GROUP", null);
        returnMapInspectionPlanNo.put("FROM_PLAN",
        									fromPlanHeaderMap.get("INSP_PLAN_NO"));
        returnMapInspectionPlanNo.put("TO_PLAN", toPlanHeaderMap.get("INSP_PLAN_NO"));
        returnList.add(returnMapInspectionPlanNo);
        
        // FND-22945
        returnMapPPVRequiredFlagMap.put("TITLE", "Production Processs Verification req'd?");
        returnMapPPVRequiredFlagMap.put("SECURITY_GROUP", null);
        returnMapPPVRequiredFlagMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_REQ_FLAG"));
        returnMapPPVRequiredFlagMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_REQ_FLAG"));
        returnList.add(returnMapPPVRequiredFlagMap);
        
        returnMapPPVTypeMap.put("TITLE", "PPV Full Or Partial");
        returnMapPPVTypeMap.put("SECURITY_GROUP", null);
        returnMapPPVTypeMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_TYPE"));
        returnMapPPVTypeMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_TYPE"));
        returnList.add(returnMapPPVTypeMap);
        
        returnMapPPVQuatityMap.put("TITLE", "PPV Qty");
        returnMapPPVQuatityMap.put("SECURITY_GROUP", null);
        returnMapPPVQuatityMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_QTY").toString());
        returnMapPPVQuatityMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_QTY").toString());
        returnList.add(returnMapPPVQuatityMap);
        
        returnMapPPVPendingQuatityMap.put("TITLE", "Pending PPV Qty");
        returnMapPPVPendingQuatityMap.put("SECURITY_GROUP", null);
        returnMapPPVPendingQuatityMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_PENDING_QTY").toString());
        returnMapPPVPendingQuatityMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_PENDING_QTY").toString());
        returnList.add(returnMapPPVPendingQuatityMap);
        
        returnMapPPVInProcessQuatityMap.put("TITLE", "In Process PPV Qty");
        returnMapPPVInProcessQuatityMap.put("SECURITY_GROUP", null);
        returnMapPPVInProcessQuatityMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_INPROCESS_QTY").toString());
        returnMapPPVInProcessQuatityMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_INPROCESS_QTY").toString());
        returnList.add(returnMapPPVInProcessQuatityMap);
        
        returnMapPPVCompleteQuatityMap.put("TITLE", "Completed PPV Qty");
        returnMapPPVCompleteQuatityMap.put("SECURITY_GROUP", null);
        returnMapPPVCompleteQuatityMap.put("FROM_PLAN",
        									fromPlanHeaderMap.get("PPV_COMPLETE_QTY").toString());
        returnMapPPVCompleteQuatityMap.put("TO_PLAN", toPlanHeaderMap.get("PPV_COMPLETE_QTY").toString());
        returnList.add(returnMapPPVCompleteQuatityMap);

        String fromLastPpvCompleteDateLocal = EMPTY;
        String toLastPpvCompleteDateLocal = EMPTY;
        
        if(fromPlanHeaderMap.get("LAST_PPV_COMPLETE_DATE") != null)
        {
        	fromLastPpvCompleteDateLocal = fromPlanHeaderMap.get("LAST_PPV_COMPLETE_DATE").toString();
        }
        
        if(toPlanHeaderMap.get("LAST_PPV_COMPLETE_DATE") != null)
        {
        	toLastPpvCompleteDateLocal = toPlanHeaderMap.get("LAST_PPV_COMPLETE_DATE").toString();
        }
        
        // FND-25218
        returnMapLastPPVCompleteDateMap.put("TITLE", "Most Recent PPV Completion Date");
        returnMapLastPPVCompleteDateMap.put("SECURITY_GROUP", null);
        returnMapLastPPVCompleteDateMap.put("FROM_PLAN",fromLastPpvCompleteDateLocal);
        returnMapLastPPVCompleteDateMap.put("TO_PLAN", toLastPpvCompleteDateLocal);
        returnList.add(returnMapLastPPVCompleteDateMap);
        
        return returnList;
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareOrderHeader(java.lang.String,
     *      java.lang.String)
     */
    public List compareOrderHeader(String fromOrderId, String toOrderId)
    {
        return this.compareOrderHeader( fromOrderId, 
                                        toOrderId, 
                                        null, 
                                        null,
                                        null );
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareOrderHeader(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List compareOrderHeader(String fromOrderId,
                                   String toOrderId,
                                   String fromAltId,
                                   String toAltId,
                                   String calledFromChgAck)
    {
        // Declares the local variable.
        List returnList = new ArrayList();
        Map returnMapPartNo = new ListOrderedMap();
        Map returnMapItemType = new ListOrderedMap();
        Map returnMapItemSubtype = new ListOrderedMap();
        Map returnMapPartChg = new ListOrderedMap();
        Map returnMapProgram = new ListOrderedMap();
        Map returnMapPlanVersion = new ListOrderedMap();
        Map returnMapPlanRevision = new ListOrderedMap();
        Map returnMapWorkLocation = new ListOrderedMap();
        Map returnMapOrderTitle = new ListOrderedMap();
        Map returnMapOrderQty = new ListOrderedMap();
        Map returnMapCustId = new ListOrderedMap();
        Map returnMapOrderUOM = new ListOrderedMap();
        Map returnMapModel = new ListOrderedMap();
        Map returnMapUnitNo = new ListOrderedMap();
        Map returnMapUnitType = new ListOrderedMap();
        Map returnMapInitialStores = new ListOrderedMap();
        Map returnMapFinalStores = new ListOrderedMap();
        Map returnMapPlanType = new ListOrderedMap();
        Map returnMapMBOMRev = new ListOrderedMap();
        Map returnMapProject = new ListOrderedMap();
        Map returnMapSerialFlag = new ListOrderedMap();
        Map returnMapLotFlag = new ListOrderedMap();
        Map returnMapLtaSendFlag = new ListOrderedMap();
        Map returnMapOrderNumber = new ListOrderedMap();
        Map returnMapCondition = new ListOrderedMap();
        Map returnMapSplitFlag = new ListOrderedMap();
        Map returnMapBOMNo = new ListOrderedMap();
        Map returnMapCustOrderNo = new ListOrderedMap();
        Map returnMapSchdPriority = new ListOrderedMap();
        Map returnMapContractNo = new ListOrderedMap();
        Map returnMapFAIFlag = new ListOrderedMap();
        Map returnMapSecGroup = new ListOrderedMap();
        Map returnMapDisplaySequence = new ListOrderedMap();
        Map returnMapOperationOverlapFlag = new ListOrderedMap();

        // Selects the Order Header Data for From Order
        Map fromOrderHeaderMap = null;
        
        if(isEmpty( fromAltId ))
        {
            fromOrderHeaderMap = compareToolDaoPW.selectOrderInformation(fromOrderId);
        }
        else
        {
            // FND-25023
            fromOrderHeaderMap = compareToolDaoPW.selectOrderInformationAltLog(fromOrderId,
                                                                             fromAltId);
        }

        // Selects the Order Header Data for To Order
        Map toOrderHeaderMap = null;
        
        if(isEmpty( toAltId ))
        {
            toOrderHeaderMap = compareToolDaoPW.selectOrderInformation(toOrderId);
        }
        else
        {
            // FND-25023
            toOrderHeaderMap = compareToolDaoPW.selectOrderInformationAltLog(toOrderId,
                                                                           toAltId);
        }

        String fromSecurityGroup = (String) fromOrderHeaderMap.get("SECURITY_GROUP");
        String toSecurityGroup = (String) toOrderHeaderMap.get("SECURITY_GROUP");
        
        boolean fromSecurityOk = SQLSecurityManager.getInstance().canAccess(fromSecurityGroup);
        boolean toSecurityOk = SQLSecurityManager.getInstance().canAccess(toSecurityGroup);
        
        if (!fromSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the from Order.");
        }
        else if (!toSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the to Order.");
        }
        
        // Sets the records of return list.
        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))//FND-26015
        {
        	returnMapOrderNumber.put("TITLE", "Order Number");
        	returnMapOrderNumber.put("FROM_ORDER",
        			fromOrderHeaderMap.get("PW_ORDER_NO"));
        	returnMapOrderNumber.put("TO_ORDER", toOrderHeaderMap.get("PW_ORDER_NO")); //Points toOrderHeaderMap to PW_ORDER_NO. Defect 880
        	returnList.add(returnMapOrderNumber);
        }
        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))//FND-26015
        {
        	returnMapOrderTitle.put("TITLE", "Order Title");
        	returnMapOrderTitle.put("FROM_ORDER",
        			fromOrderHeaderMap.get("PLAN_TITLE"));
        	returnMapOrderTitle.put("TO_ORDER", toOrderHeaderMap.get("PLAN_TITLE"));
        	returnList.add(returnMapOrderTitle);
        }

        returnMapOrderQty.put("TITLE", "Order Quantity");
        returnMapOrderQty.put("FROM_ORDER", getNumberValueToLocaleSpecific((Number)fromOrderHeaderMap.get("ORDER_QTY")));
        returnMapOrderQty.put("TO_ORDER", getNumberValueToLocaleSpecific((Number)toOrderHeaderMap.get("ORDER_QTY")));
        returnList.add(returnMapOrderQty);

        returnMapOrderUOM.put("TITLE", "Order UOM");
        returnMapOrderUOM.put("FROM_ORDER", fromOrderHeaderMap.get("ORDER_UOM"));
        returnMapOrderUOM.put("TO_ORDER", toOrderHeaderMap.get("ORDER_UOM"));
        returnList.add(returnMapOrderUOM);

        returnMapWorkLocation.put("TITLE", "Main Work Location");
        returnMapWorkLocation.put("FROM_ORDER",
                                  fromOrderHeaderMap.get("ASGND_WORK_LOC"));
        returnMapWorkLocation.put("TO_ORDER",
                                  toOrderHeaderMap.get("ASGND_WORK_LOC"));
        returnList.add(returnMapWorkLocation);

        returnMapUnitNo.put("TITLE", "End Unit Number");
        returnMapUnitNo.put("FROM_ORDER", fromOrderHeaderMap.get("UNIT_NO"));
        returnMapUnitNo.put("TO_ORDER", toOrderHeaderMap.get("UNIT_NO"));
        returnList.add(returnMapUnitNo);

        returnMapUnitType.put("TITLE", "End Unit Type");
        returnMapUnitType.put("FROM_ORDER", fromOrderHeaderMap.get("UNIT_TYPE"));
        returnMapUnitType.put("TO_ORDER", toOrderHeaderMap.get("UNIT_TYPE"));
        returnList.add(returnMapUnitType);

        returnMapLtaSendFlag.put("TITLE", "Labor Send Flag");
        returnMapLtaSendFlag.put("FROM_ORDER",
                                 fromOrderHeaderMap.get("LTA_SEND_FLAG")
                                                   .toString());
        returnMapLtaSendFlag.put("TO_ORDER",
                                 toOrderHeaderMap.get("LTA_SEND_FLAG")
                                                 .toString());
        returnList.add(returnMapLtaSendFlag);

        returnMapPlanVersion.put("TITLE", "Plan Version");
        if (fromOrderHeaderMap.get("PLAN_VERSION") != null)
        {
            returnMapPlanVersion.put("FROM_ORDER",
                                     fromOrderHeaderMap.get("PLAN_VERSION")
                                                       .toString());
        }
        else
        {
            returnMapPlanVersion.put("FROM_ORDER", EMPTY);
        }
        if (toOrderHeaderMap.get("PLAN_VERSION") != null)
        {
            returnMapPlanVersion.put("TO_ORDER",
                                     toOrderHeaderMap.get("PLAN_VERSION")
                                                     .toString());
        }
        else
        {
            returnMapPlanVersion.put("TO_ORDER", EMPTY);
        }
        returnList.add(returnMapPlanVersion);

        returnMapPlanRevision.put("TITLE", "Plan Revision");
        if (fromOrderHeaderMap.get("PLAN_REVISION") != null)
        {
            returnMapPlanRevision.put("FROM_ORDER",
                                      fromOrderHeaderMap.get("PLAN_REVISION")
                                                        .toString());
        }
        else
        {
            returnMapPlanRevision.put("FROM_ORDER", EMPTY);
        }
        if (toOrderHeaderMap.get("PLAN_REVISION") != null)
        {
            returnMapPlanRevision.put("TO_ORDER",
                                      toOrderHeaderMap.get("PLAN_REVISION")
                                                      .toString());
        }
        else
        {
            returnMapPlanRevision.put("TO_ORDER", EMPTY);
        }
        returnList.add(returnMapPlanRevision);

        returnMapPlanType.put("TITLE", "Plan Type");
        returnMapPlanType.put("FROM_ORDER", fromOrderHeaderMap.get("PLAN_TYPE"));
        returnMapPlanType.put("TO_ORDER", toOrderHeaderMap.get("PLAN_TYPE"));
        returnList.add(returnMapPlanType);

        returnMapPartNo.put("TITLE", "Plan Item Number");
        returnMapPartNo.put("FROM_ORDER", fromOrderHeaderMap.get("PART_NO"));
        returnMapPartNo.put("TO_ORDER", toOrderHeaderMap.get("PART_NO"));
        returnList.add(returnMapPartNo);

        returnMapPartChg.put("TITLE", "Plan Item Revision");
        returnMapPartChg.put("FROM_ORDER", fromOrderHeaderMap.get("PART_CHG"));
        returnMapPartChg.put("TO_ORDER", toOrderHeaderMap.get("PART_CHG"));
        returnList.add(returnMapPartChg);

        returnMapItemType.put("TITLE", "Plan Item Type");
        returnMapItemType.put("FROM_ORDER", fromOrderHeaderMap.get("ITEM_TYPE"));
        returnMapItemType.put("TO_ORDER", toOrderHeaderMap.get("ITEM_TYPE"));
        returnList.add(returnMapItemType);

        returnMapItemSubtype.put("TITLE", "Plan Item Subtype");
        returnMapItemSubtype.put("FROM_ORDER",
                                 fromOrderHeaderMap.get("ITEM_SUBTYPE"));
        returnMapItemSubtype.put("TO_ORDER",
                                 toOrderHeaderMap.get("ITEM_SUBTYPE"));
        returnList.add(returnMapItemSubtype);

        returnMapModel.put("TITLE", "Model");
        returnMapModel.put("FROM_ORDER", fromOrderHeaderMap.get("MODEL"));
        returnMapModel.put("TO_ORDER", toOrderHeaderMap.get("MODEL"));
        returnList.add(returnMapModel);

        returnMapCustId.put("TITLE", "Customer Id");
        returnMapCustId.put("FROM_ORDER",
                            fromOrderHeaderMap.get("ORDER_CUST_ID"));
        returnMapCustId.put("TO_ORDER", toOrderHeaderMap.get("ORDER_CUST_ID"));
        returnList.add(returnMapCustId);

        if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))//FND-26015
        {
        	returnMapProject.put("TITLE", "Project");
        	returnMapProject.put("FROM_ORDER", fromOrderHeaderMap.get("PROJECT"));
        	returnMapProject.put("TO_ORDER", toOrderHeaderMap.get("PROJECT"));
        	returnList.add(returnMapProject);
        }

        returnMapProgram.put("TITLE", "Program");
        returnMapProgram.put("FROM_ORDER", fromOrderHeaderMap.get("PROGRAM"));
        returnMapProgram.put("TO_ORDER", toOrderHeaderMap.get("PROGRAM"));
        returnList.add(returnMapProgram);

        returnMapSerialFlag.put("TITLE", "Serial No?");
        returnMapSerialFlag.put("FROM_ORDER",
                                fromOrderHeaderMap.get("SERIAL_FLAG"));
        returnMapSerialFlag.put("TO_ORDER", toOrderHeaderMap.get("SERIAL_FLAG"));
        returnList.add(returnMapSerialFlag);

        returnMapLotFlag.put("TITLE", "Lot No?");
        returnMapLotFlag.put("FROM_ORDER", fromOrderHeaderMap.get("LOT_FLAG"));
        returnMapLotFlag.put("TO_ORDER", toOrderHeaderMap.get("LOT_FLAG"));
        returnList.add(returnMapLotFlag);

        returnMapSplitFlag.put("TITLE", "Was Split?");
        returnMapSplitFlag.put("FROM_ORDER",
                               fromOrderHeaderMap.get("SPLIT_FLAG"));
        returnMapSplitFlag.put("TO_ORDER", toOrderHeaderMap.get("SPLIT_FLAG"));
        returnList.add(returnMapSplitFlag);

        returnMapMBOMRev.put("TITLE", "MBOM Revision");
        returnMapMBOMRev.put("FROM_ORDER",
                             fromOrderHeaderMap.get("MFG_BOM_CHG"));
        returnMapMBOMRev.put("TO_ORDER", toOrderHeaderMap.get("MFG_BOM_CHG"));
        returnList.add(returnMapMBOMRev);

        returnMapBOMNo.put("TITLE", "BOM Number");
        returnMapBOMNo.put("FROM_ORDER", fromOrderHeaderMap.get("BOM_NO"));
        returnMapBOMNo.put("TO_ORDER", toOrderHeaderMap.get("BOM_NO"));
        returnList.add(returnMapBOMNo);

        returnMapCustOrderNo.put("TITLE", "Customer Order Number");
        returnMapCustOrderNo.put("FROM_ORDER",
                                 fromOrderHeaderMap.get("CUSTOMER_ORDER_NO"));
        returnMapCustOrderNo.put("TO_ORDER",
                                 toOrderHeaderMap.get("CUSTOMER_ORDER_NO"));
        returnList.add(returnMapCustOrderNo);

        returnMapInitialStores.put("TITLE", "Initial Stores");
        returnMapInitialStores.put("FROM_ORDER",
                                   fromOrderHeaderMap.get("INITIAL_STORES"));
        returnMapInitialStores.put("TO_ORDER",
                                   toOrderHeaderMap.get("INITIAL_STORES"));
        returnList.add(returnMapInitialStores);

        returnMapFinalStores.put("TITLE", "Final Stores");
        returnMapFinalStores.put("FROM_ORDER",
                                 fromOrderHeaderMap.get("FINAL_STORES"));
        returnMapFinalStores.put("TO_ORDER",
                                 toOrderHeaderMap.get("FINAL_STORES"));
        returnList.add(returnMapFinalStores);

        returnMapSchdPriority.put("TITLE", "Sched Priority"); //FND-18949
        returnMapSchdPriority.put("FROM_ORDER",
                                  fromOrderHeaderMap.get("SCHED_PRIORITY"));
        returnMapSchdPriority.put("TO_ORDER",
                                  toOrderHeaderMap.get("SCHED_PRIORITY"));
        returnList.add(returnMapSchdPriority);

        returnMapCondition.put("TITLE", "Condition");
        returnMapCondition.put("FROM_ORDER",
                               fromOrderHeaderMap.get("CONDITION"));
        returnMapCondition.put("TO_ORDER", toOrderHeaderMap.get("CONDITION"));
        returnList.add(returnMapCondition);

        returnMapContractNo.put("TITLE", "Contract Number");
        returnMapContractNo.put("FROM_ORDER",
                                fromOrderHeaderMap.get("CONTRACT_NO"));
        returnMapContractNo.put("TO_ORDER", toOrderHeaderMap.get("CONTRACT_NO"));
        returnList.add(returnMapContractNo);
        
        returnMapDisplaySequence.put("TITLE", "Display Sequence");
        returnMapDisplaySequence.put("FROM_ORDER",
                                     fromOrderHeaderMap.get("DISPLAY_SEQUENCE"));
        returnMapDisplaySequence.put("TO_ORDER", toOrderHeaderMap.get("DISPLAY_SEQUENCE"));
        returnList.add(returnMapDisplaySequence);

        //FND-22135
        returnMapOperationOverlapFlag.put("TITLE", "Operation Overlap?");
        returnMapOperationOverlapFlag.put("FROM_ORDER",
        									fromOrderHeaderMap.get("OPERATION_OVERLAP_FLAG"));
        returnMapOperationOverlapFlag.put("TO_ORDER", toOrderHeaderMap.get("OPERATION_OVERLAP_FLAG"));
        returnList.add(returnMapOperationOverlapFlag);

        Iterator itr = returnList.iterator();
        while(itr.hasNext())
        {
            Map map = (Map) itr.next();
            map.put("SECURITY_GROUP", null);
        }
        
        returnList.add(returnMapSecGroup);
        return returnList;
    }
    
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#getParameterValue(java.lang.String[], java.lang.String)
     */
    public String getParameterValue(String[] param, String parameterName)
    {
        String returnValue = "";
        boolean valueFound = false;
        for(int i=0; i < param.length && !valueFound; i++)
        {
            String paramName = param[i];
            if(contains( paramName, parameterName ))
            {
                returnValue = replace( paramName, parameterName + "=", "" );
                valueFound = true;
            }
        }
        
        return returnValue;
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareOperationHeaderMetaData(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public List compareOperationHeaderMetaData(String fromPlanId,
                                               Number fromPlanVersion,
                                               Number fromPlanRevision,
                                               Number fromPlanAlterations,
                                               String toPlanId,
                                               Number toPlanVersion,
                                               Number toPlanRevision,
                                               Number toPlanAlterations,
                                               String fromOrderId,
                                               String toOrderId,
                                               String parameters)
    {
        return this.compareOperationHeaderMetaData( fromPlanId, 
                                                    fromPlanVersion, 
                                                    fromPlanRevision, 
                                                    fromPlanAlterations, 
                                                    toPlanId, 
                                                    toPlanVersion, 
                                                    toPlanRevision, 
                                                    toPlanAlterations, 
                                                    fromOrderId, 
                                                    toOrderId, 
                                                    parameters, 
                                                    null, 
                                                    null, 
                                                    null );
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareOperationHeaderMetaData(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List compareOperationHeaderMetaData(String fromPlanId,
                                               Number fromPlanVersion,
                                               Number fromPlanRevision,
                                               Number fromPlanAlterations,
                                               String toPlanId,
                                               Number toPlanVersion,
                                               Number toPlanRevision,
                                               Number toPlanAlterations,
                                               String fromOrderId,
                                               String toOrderId,
                                               String parameters,
                                               String fromAltId, //FND-25023
                                               String toAltId, 
                                               String calledFromChgAck)
    {
        // Declares the local variable.
        List returnList = new ArrayList();
        Map fromOperationHeaderData = new ListOrderedMap();
        Map toOperationHeaderData = new ListOrderedMap();
        
        Map fromScopeOperationInfo = new ListOrderedMap();  // GE-2747
        Map toScopeOperationInfo = new ListOrderedMap();  // GE-2747

        Map returnMapOperationNumber = new ListOrderedMap();
        Map returnMapOperUpdateNumber = new ListOrderedMap();
        Map returnMapOperationState = new ListOrderedMap();
        Map returnMapOperationTitle = new ListOrderedMap();
        Map returnMapWorkLocation = new ListOrderedMap();
        Map returnMapWorkDept = new ListOrderedMap();
        Map returnMapWorkCenter = new ListOrderedMap();
        Map returnMapMachineNumber = new ListOrderedMap();
        Map returnMapOperationType = new ListOrderedMap();
        Map returnMapOptionalFlag = new ListOrderedMap();
        Map returnMapOSP = new ListOrderedMap();
        Map returnMapAutoStart = new ListOrderedMap();
        Map returnMapAutoComplete = new ListOrderedMap();
        Map returnMapOccurRate = new ListOrderedMap();
        Map returnMapOSPDays = new ListOrderedMap();
        Map returnMapOSPCostPerUnit = new ListOrderedMap();
        Map returnMapSupplierCode = new ListOrderedMap();
        Map returnMapTestType = new ListOrderedMap();
        Map returnMapSeqSteps = new ListOrderedMap();
        Map returnMapAuthType = new ListOrderedMap();
        Map returnMapAuthNo = new ListOrderedMap();
        Map returnMapAuthNote = new ListOrderedMap();
        Map returnMapOperSequenceNum = new ListOrderedMap();
		//Start FND-22137
        Map returnMapUnitProcessing = new ListOrderedMap();
        Map returnMapUnitsPerCycleActual = new ListOrderedMap();
        Map returnMapUnitsPerCycle = new ListOrderedMap();
        Map returnMapAutoBatchFlag = new ListOrderedMap();
        Map returnMapPrintLabel = new ListOrderedMap();
        Map returnMapLabelsPerCycle = new ListOrderedMap();
        Map returnMapReportId = new ListOrderedMap();
        Map returnMapReconcileScrap = new ListOrderedMap();
		//End FND-22137
        Map returnMapCrossOrder = new ListOrderedMap();
        Map returnMapOrientation = new ListOrderedMap();
        Map returnMapMustIssueParts = new ListOrderedMap();

        boolean fromOperationNumberExists =  false;
        boolean toOperationNumberExists =  false;

        //FND-25704
        Map fromOperationNumberMap =  new ListOrderedMap();
        Map toOperationNumberMap =  new ListOrderedMap();

        String fromType = EMPTY;
        String toType = EMPTY;
        
        Number fromOperKey = NumberUtils.INTEGER_ZERO;
        Number toOperKey = NumberUtils.INTEGER_ZERO;
        
        Number fromScopeOperUpdtNo = NumberUtils.INTEGER_ZERO;  // GE-2747
        Number toScopeOperUpdtNo = NumberUtils.INTEGER_ZERO;  // GE-2747

        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);

        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            try
            {
            	fromOperationNumberMap = compareToolDao.selectOperationNumberExists(fromOperationNumber,
                                                                                   fromPlanId,
                                                                                   fromPlanVersion,
                                                                                   fromPlanRevision,
                                                                                   fromPlanAlterations);
            	fromOperKey = (Number)fromOperationNumberMap.get( "OPER_KEY" );
            	fromScopeOperUpdtNo = (Number)fromOperationNumberMap.get( "OPER_UPDT_NO" );
        	}
            catch(NullPointerException e)
            {
            	
            }
        }
        else
        {
            if(isEmpty( fromAltId ))
            {
            	try
            	{
            		fromOperationNumberMap = compareToolDao.selectOperationNumberExists(fromOperationNumber,
                                                                                       fromOrderId,
                                                                                       NumberUtils.INTEGER_MINUS_ONE);
            	}
                catch(NullPointerException e)
                {
                	
                }
            }
            else
            {
            	//FND-25023
            	try
            	{                    
            		fromOperationNumberMap = compareToolDao.selectOperationNumberExistsAltLog2(fromOperationNumber,
                                                                                             fromOrderId,
                                                                                             NumberUtils.INTEGER_MINUS_ONE,
                                                                                             fromAltId);
            	}
                catch(NullPointerException e)
                {
                	
                }
            }
        }

        if (equalsIgnoreCase(toType, "PLAN"))
        {            
        	try
        	{
        		toOperationNumberMap = compareToolDao.selectOperationNumberExists(toOperationNumber,
                                                                                 toPlanId,
                                                                                 toPlanVersion,
                                                                                 toPlanRevision,
                                                                                 toPlanAlterations);
        		toOperKey = (Number) toOperationNumberMap.get( "OPER_KEY" );
        		toScopeOperUpdtNo = (Number)toOperationNumberMap.get( "OPER_UPDT_NO" );
            }
            catch(NullPointerException e)
            {
            	
            }
        }
        else
        {
            if(isEmpty( toAltId ))
            {
                try
                {
                	toOperationNumberMap = compareToolDao.selectOperationNumberExists(toOperationNumber,
                                                                                     toOrderId,
                                                                                     NumberUtils.INTEGER_MINUS_ONE);
                }
                catch(NullPointerException e)
                {
                	
                }
            }
            else
            {            	
                //FND-25023
            	try
            	{
            		toOperationNumberMap = compareToolDao.selectOperationNumberExistsAltLog2(toOperationNumber,
                                                                                           toOrderId,
                                                                                           NumberUtils.INTEGER_MINUS_ONE,
                                                                                           toAltId);
                }
                catch(NullPointerException e)
                {
                	
                }
            }
        }
        
    	if(fromOperationNumberMap != null)
    	{
	    	String frmOper = (String) fromOperationNumberMap.get("DUMMY_COL");
	    	
	    	if(isNotEmpty(frmOper))
	    	{
	    		fromOperationNumberExists = true;
	        	}
	    	}
    	
    	if(toOperationNumberMap != null)
    	{
	    	String toOper = (String) toOperationNumberMap.get("DUMMY_COL");
	    	
	    	if(isNotEmpty(toOper))
	    	{
	    		toOperationNumberExists = true;
	        	}
	    	}
    	
    	//GE-2316
    	Map fromStdOperMap = new HashMap();
    	Map toStdOperMap = new HashMap();
    	String fromMainOrStdOperOperNo = fromOperationNumber;
    	String toMainOrStdOperOperNo = toOperationNumber;
    	
    	//GE-2747
    	String fromScopePlanId = fromPlanId;
    	Number fromScopePlanVer = fromPlanVersion;
    	Number fromScopePlanRev = fromPlanRevision;
    	Number fromScopePlanAlt = fromPlanAlterations;
    	Number fromScopeOperKey = fromOperKey;
    	String toScopePlanId = toPlanId;
    	Number toScopePlanVer = toPlanVersion;
    	Number toScopePlanRev = toPlanRevision;
    	Number toScopePlanAlt = toPlanAlterations;
    	Number toScopeOperKey = toOperKey;
    	
    	//GE-1668 check for operation load from standard Operation if it is Authoring mode standard operation otherwise not.
        if(fromOperationNumberMap != null && !fromOperationNumberMap.isEmpty())
        {
            fromStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(fromPlanId, 
                                                                               fromPlanVersion, 
                                                                               fromPlanRevision, 
                                                                               fromPlanAlterations, 
                                                                               fromOperKey,
                                                                               null);
                    
            if(fromStdOperMap != null && !fromStdOperMap.isEmpty())
            {
                fromPlanId = (String) fromStdOperMap.get( "STDOPER_PLAN_ID" );
                fromPlanVersion = (Number) fromStdOperMap.get( "STDOPER_PLAN_VER" );
                fromPlanRevision = (Number) fromStdOperMap.get( "STDOPER_PLAN_REV" );
                fromPlanAlterations = (Number) fromStdOperMap.get( "STDOPER_PLAN_ALT" );
                fromOperKey =  (Number) fromStdOperMap.get( "STDOPER_OPER_KEY" );
                fromMainOrStdOperOperNo = (String) fromStdOperMap.get("STDOPER_OPER_NO");                
            }
        }
        
        if(toOperationNumberMap != null && !toOperationNumberMap.isEmpty())
        {
            toStdOperMap = operationDao.selectInCompleteStdOperRefInPlanInfo(toPlanId, 
                                                                             toPlanVersion, 
                                                                             toPlanRevision, 
                                                                             toPlanAlterations,
                                                                             toOperKey,
                                                                             null); 
                    
            if(toStdOperMap != null && !toStdOperMap.isEmpty())
            {
                toPlanId = (String) toStdOperMap.get( "STDOPER_PLAN_ID" );
                toPlanVersion = (Number) toStdOperMap.get( "STDOPER_PLAN_VER" );
                toPlanRevision = (Number) toStdOperMap.get( "STDOPER_PLAN_REV" );
                toPlanAlterations = (Number) toStdOperMap.get( "STDOPER_PLAN_ALT" );
                toOperKey =  (Number) toStdOperMap.get( "STDOPER_OPER_KEY" );
                toMainOrStdOperOperNo = (String) toStdOperMap.get("STDOPER_OPER_NO");
            }
        }

        if (fromOperationNumberExists)
        {
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                // Selects the Operation Header Data for From Plan
                fromOperationHeaderData = compareToolDao.selectOperationHeaderData(fromMainOrStdOperOperNo,
                                                                                   fromPlanId,
                                                                                   fromPlanVersion,
                                                                                   fromPlanRevision,
                                                                                   fromPlanAlterations);
                
                // GE-2747
                fromScopeOperationInfo = operationDao.selectOperationDescriptionInfo( fromScopePlanId, 
                                                                                      fromScopeOperKey,
                                                                                      fromScopeOperUpdtNo );
            }
            else
            {
                if(isEmpty( fromAltId ))
                {
                // Selects the Operation Header Data for From Order
                fromOperationHeaderData = compareToolDao.selectOperationHeaderData(fromOrderId,
                                                                                   fromOperationNumber,
                                                                                   NumberUtils.INTEGER_MINUS_ONE);
                }
                else
                {
                    //FND-25023 Selects the Operation Header Data in Alteration for From Order
                    fromOperationHeaderData = compareToolDao.selectOperationHeaderDataAltLog(fromOrderId,
                                                                                             fromOperationNumber,
                                                                                             NumberUtils.INTEGER_MINUS_ONE,
                                                                                             fromAltId);
                    
                }
            }
        }

        if (toOperationNumberExists)
        {
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Header Data for To Plan
                toOperationHeaderData = compareToolDao.selectOperationHeaderData(toMainOrStdOperOperNo,
                                                                                 toPlanId,
                                                                                 toPlanVersion,
                                                                                 toPlanRevision,
                                                                                 toPlanAlterations);
                
                // GE-2747
                toScopeOperationInfo = operationDao.selectOperationDescriptionInfo( toScopePlanId, 
                                                                                    toScopeOperKey,
                                                                                    toScopeOperUpdtNo );
            }
            else
            {
                if(isEmpty( fromAltId ))
                {
                    // Selects the Operation Header Data for To Order
                    toOperationHeaderData = compareToolDao.selectOperationHeaderData(toOrderId,
                                                                                     toOperationNumber,
                                                                                     NumberUtils.INTEGER_MINUS_ONE);
                }
                else
                {
                    // Selects the Operation Header Data for To Order
                    toOperationHeaderData = compareToolDao.selectOperationHeaderDataAltLog(toOrderId,
                                                                                           toOperationNumber,
                                                                                           NumberUtils.INTEGER_MINUS_ONE,
                                                                                           toAltId);
                }
            }
        }

        // When Operation exists in both the Plans
        if (fromOperationNumberExists && toOperationNumberExists)
        {
            // Sets the records of return list.
            returnMapOperationNumber.put("TITLE", "Operation Number");
            returnMapOperationNumber.put("FROM_PLAN", fromOperationNumber); // GE-2316
                                         //fromOperationHeaderData.get("OPER_NO"));
            returnMapOperationNumber.put("TO_PLAN", toOperationNumber); // GE-2316
                                         //toOperationHeaderData.get("OPER_NO"));
            returnList.add(returnMapOperationNumber);
            
            if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))//FND-26015
            {
            	returnMapOperUpdateNumber.put("TITLE", "Operation Update Number");
            	if (fromOperationHeaderData.get("OPER_UPDT_NO") != null)
            	{
            		returnMapOperUpdateNumber.put("FROM_PLAN",
            				fromOperationHeaderData.get("OPER_UPDT_NO")
            				.toString());
            	}
            	else
            	{
            		returnMapOperUpdateNumber.put("FROM_PLAN", EMPTY);
            	}
            	if (toOperationHeaderData.get("OPER_UPDT_NO") != null)
            	{
            		returnMapOperUpdateNumber.put("TO_PLAN",
            				toOperationHeaderData.get("OPER_UPDT_NO")
            				.toString());
            	}
            	else
            	{
            		returnMapOperUpdateNumber.put("TO_PLAN", EMPTY);
            	}
            	returnList.add(returnMapOperUpdateNumber);
            }
            

            returnMapOperationTitle.put("TITLE", "Operation Title");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapOperationTitle.put("FROM_PLAN",
                                            fromOperationHeaderData.get("OPER_TITLE"));
            }
            else
            {
                returnMapOperationTitle.put("FROM_PLAN",
                                            fromOperationHeaderData.get("TITLE"));
            }
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapOperationTitle.put("TO_PLAN",
                                            toOperationHeaderData.get("OPER_TITLE"));
            }
            else
            {
                returnMapOperationTitle.put("TO_PLAN",
                                            toOperationHeaderData.get("TITLE"));
            }
            returnList.add(returnMapOperationTitle);

            returnMapOperationType.put("TITLE", "Operation Type");
            returnMapOperationType.put("FROM_PLAN",
                                       fromOperationHeaderData.get("OPER_TYPE"));
            returnMapOperationType.put("TO_PLAN",
                                       toOperationHeaderData.get("OPER_TYPE"));
            
            returnList.add(returnMapOperationType);
            
            if(!equalsIgnoreCase(calledFromChgAck, CHANGE_ACK))//FND-26015
            {
            	if (equalsIgnoreCase(fromType, "PLAN")
            			&& equalsIgnoreCase(fromType, toType))
            	{
            		returnMapOperationState.put("TITLE", "Operation State");
            		returnMapOperationState.put("FROM_PLAN",
            				fromOperationHeaderData.get("OPER_STATE"));
            		returnMapOperationState.put("TO_PLAN",
            				toOperationHeaderData.get("OPER_STATE"));
            		returnList.add(returnMapOperationState);
            	}
            }

            returnMapWorkLocation.put("TITLE", "Work Location");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkLocation.put("FROM_PLAN",
                                          fromOperationHeaderData.get("PLND_WORK_LOC"));
            }
            else
            {
                returnMapWorkLocation.put("FROM_PLAN",
                                          fromOperationHeaderData.get("ASGND_WORK_LOC"));
            }
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkLocation.put("TO_PLAN",
                                          toOperationHeaderData.get("PLND_WORK_LOC"));
            }
            else
            {
                returnMapWorkLocation.put("TO_PLAN",
                                          toOperationHeaderData.get("ASGND_WORK_LOC"));
            }
            returnList.add(returnMapWorkLocation);

            returnMapWorkDept.put("TITLE", "Work Department");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkDept.put("FROM_PLAN",
                                      fromOperationHeaderData.get("PLND_WORK_DEPT"));
            }
            else
            {
                returnMapWorkDept.put("FROM_PLAN",
                                      fromOperationHeaderData.get("ASGND_WORK_DEPT"));
            }
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkDept.put("TO_PLAN",
                                      toOperationHeaderData.get("PLND_WORK_DEPT"));
            }
            else
            {
                returnMapWorkDept.put("TO_PLAN",
                                      toOperationHeaderData.get("ASGND_WORK_DEPT"));
            }
            returnList.add(returnMapWorkDept);

            returnMapWorkCenter.put("TITLE", "Work Center");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkCenter.put("FROM_PLAN",
                                        fromOperationHeaderData.get("PLND_WORK_CENTER"));
            }
            else
            {
                returnMapWorkCenter.put("FROM_PLAN",
                                        fromOperationHeaderData.get("ASGND_WORK_CENTER"));
            }
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkCenter.put("TO_PLAN",
                                        toOperationHeaderData.get("PLND_WORK_CENTER"));
            }
            else
            {
                returnMapWorkCenter.put("TO_PLAN",
                                        toOperationHeaderData.get("ASGND_WORK_CENTER"));
            }
            returnList.add(returnMapWorkCenter);

            returnMapMachineNumber.put("TITLE", "Machine Number");
            returnMapMachineNumber.put("FROM_PLAN",
                                         fromOperationHeaderData.get("PLND_MACHINE_NO"));
            returnMapMachineNumber.put("TO_PLAN",
                                         toOperationHeaderData.get("PLND_MACHINE_NO"));
            returnList.add(returnMapMachineNumber);

            returnMapOptionalFlag.put("TITLE", "Optional?");
            returnMapOptionalFlag.put("FROM_PLAN",
                                      fromOperationHeaderData.get("OPER_OPT_FLAG"));
            returnMapOptionalFlag.put("TO_PLAN",
                                      toOperationHeaderData.get("OPER_OPT_FLAG"));
            returnList.add(returnMapOptionalFlag);

            returnMapOSP.put("TITLE", "Outside Process (OSP)?");
            returnMapOSP.put("FROM_PLAN",
                             fromOperationHeaderData.get("OSP_FLAG"));
            returnMapOSP.put("TO_PLAN", toOperationHeaderData.get("OSP_FLAG"));
            returnList.add(returnMapOSP);

            returnMapAutoStart.put("TITLE", "Auto Start?");
            returnMapAutoStart.put("FROM_PLAN",
                                   fromOperationHeaderData.get("AUTO_START_FLAG"));
            returnMapAutoStart.put("TO_PLAN",
                                   toOperationHeaderData.get("AUTO_START_FLAG"));
            returnList.add(returnMapAutoStart);

            returnMapAutoComplete.put("TITLE", "Auto Complete?");
            returnMapAutoComplete.put("FROM_PLAN",
                                      fromOperationHeaderData.get("AUTO_COMPLETE_FLAG"));
            returnMapAutoComplete.put("TO_PLAN",
                                      toOperationHeaderData.get("AUTO_COMPLETE_FLAG"));
            returnList.add(returnMapAutoComplete);

            returnMapOccurRate.put("TITLE", "Occur Rate");
            if (fromOperationHeaderData.get("OCCUR_RATE") != null)
            {
                returnMapOccurRate.put("FROM_PLAN",
                                       fromOperationHeaderData.get("OCCUR_RATE")
                                                              .toString());
            }
            else
            {
                returnMapOccurRate.put("FROM_PLAN", EMPTY);
            }
            if (toOperationHeaderData.get("OCCUR_RATE") != null)
            {
                returnMapOccurRate.put("TO_PLAN",
                                       toOperationHeaderData.get("OCCUR_RATE")
                                                            .toString());
            }
            else
            {
                returnMapOccurRate.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOccurRate);

            returnMapOSPDays.put("TITLE", "OSP Days");
            if (fromOperationHeaderData.get("OSP_DAYS") != null)
            {
                returnMapOSPDays.put("FROM_PLAN",
                                     defaultIfEmpty(fromOperationHeaderData.get("OSP_DAYS")
                                                                                       .toString(),
                                                                EMPTY));
            }
            else
            {
                returnMapOSPDays.put("FROM_PLAN", EMPTY);
            }
            if (toOperationHeaderData.get("OSP_DAYS") != null)
            {
                returnMapOSPDays.put("TO_PLAN",
                                     defaultIfEmpty(toOperationHeaderData.get("OSP_DAYS")
                                                                                     .toString(),
                                                                EMPTY));
            }
            else
            {
                returnMapOSPDays.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOSPDays);

            returnMapOSPCostPerUnit.put("TITLE", "OSP Cost Per Unit");
            if (fromOperationHeaderData.get("OSP_COST_PER_UNIT") != null)
            {
                returnMapOSPCostPerUnit.put("FROM_PLAN",
                                            fromOperationHeaderData.get("OSP_COST_PER_UNIT")
                                                                   .toString());
            }
            else
            {
                returnMapOSPCostPerUnit.put("FROM_PLAN", EMPTY);
            }
            if (toOperationHeaderData.get("OSP_COST_PER_UNIT") != null)
            {
                returnMapOSPCostPerUnit.put("TO_PLAN",
                                            toOperationHeaderData.get("OSP_COST_PER_UNIT")
                                                                 .toString());
            }
            else
            {
                returnMapOSPCostPerUnit.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOSPCostPerUnit);

            returnMapSupplierCode.put("TITLE", "Supplier Code"); 
            returnMapSupplierCode.put("FROM_PLAN", 
                                      fromOperationHeaderData.get("SUPPLIER_CODE")); 
            returnMapSupplierCode.put("TO_PLAN", 
                                      toOperationHeaderData.get("SUPPLIER_CODE")); 
            returnList.add(returnMapSupplierCode); 

            returnMapTestType.put("TITLE", "Test Type");
            returnMapTestType.put("FROM_PLAN",
                                  fromOperationHeaderData.get("TEST_TYPE"));
            returnMapTestType.put("TO_PLAN",
                                  toOperationHeaderData.get("TEST_TYPE"));
            returnList.add(returnMapTestType);

            returnMapSeqSteps.put("TITLE", "Execute Steps in Sequence?");
            returnMapSeqSteps.put("FROM_PLAN",
                                  fromOperationHeaderData.get("SEQ_STEPS_FLAG"));
            returnMapSeqSteps.put("TO_PLAN",
                                  toOperationHeaderData.get("SEQ_STEPS_FLAG"));
            returnList.add(returnMapSeqSteps);
            
            
            

            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {   

                returnMapAuthNo.put("TITLE", "Authorization Number");
                returnMapAuthNo.put("FROM_PLAN",
                                    null);
                returnMapAuthNo.put("TO_PLAN",
                        null);
                returnList.add(returnMapAuthNote);
            }
            
            // FND-18081
            returnMapOperSequenceNum.put("TITLE", "Oper Sequence Number");
            //  GE-2747 
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapOperSequenceNum.put("FROM_PLAN",
                                              fromScopeOperationInfo.get( "EXE_ORDER" ).toString());
            }
            else
            {            
                returnMapOperSequenceNum.put("FROM_PLAN",
        								    (fromOperationHeaderData.get("EXE_ORDER")).toString());
            }
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapOperSequenceNum.put("TO_PLAN",
                                             toScopeOperationInfo.get( "EXE_ORDER" ).toString());
            }
            else
            {
                returnMapOperSequenceNum.put("TO_PLAN",
            							     toOperationHeaderData.get("EXE_ORDER").toString());
            }
            returnList.add(returnMapOperSequenceNum);
            
            // Start FND-22137
            returnMapUnitProcessing.put("TITLE", "Unit Processing");
            returnMapUnitProcessing.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("UNIT_PROCESSING"));
            returnMapUnitProcessing.put("TO_PLAN",
                                  			toOperationHeaderData.get("UNIT_PROCESSING"));
            returnList.add(returnMapUnitProcessing);
            
            returnMapUnitsPerCycle.put("TITLE", "Units Per Cycle");
            returnMapUnitsPerCycle.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("UNITS_PER_CYCLE").toString());
            returnMapUnitsPerCycle.put("TO_PLAN",
                                  			toOperationHeaderData.get("UNITS_PER_CYCLE").toString());
            returnList.add(returnMapUnitsPerCycle);
            
            if(equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(toType, "ORDER"))
            {
	            returnMapUnitsPerCycleActual.put("TITLE", "Units Per Cycle Actual");
	            returnMapUnitsPerCycleActual.put("FROM_PLAN",
	            									fromOperationHeaderData.get("UNITS_PER_CYCLE_ACTUAL").toString());
	            returnMapUnitsPerCycleActual.put("TO_PLAN",
            										toOperationHeaderData.get("UNITS_PER_CYCLE_ACTUAL").toString());
	            returnList.add(returnMapUnitsPerCycleActual);
            }
            
            returnMapAutoBatchFlag.put("TITLE", "Auto Cycle?");
            returnMapAutoBatchFlag.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("AUTO_CYCLE_FLAG")); //FND-22934 AUTO_CYCLE_FLAG 
            returnMapAutoBatchFlag.put("TO_PLAN",
                                  			toOperationHeaderData.get("AUTO_CYCLE_FLAG"));
            returnList.add(returnMapAutoBatchFlag);
            
            returnMapPrintLabel.put("TITLE", "Print Label");
            returnMapPrintLabel.put("FROM_PLAN",
            						fromOperationHeaderData.get("PRINT_LABEL"));
            returnMapPrintLabel.put("TO_PLAN",
            						toOperationHeaderData.get("PRINT_LABEL"));
            returnList.add(returnMapPrintLabel);
            
            returnMapLabelsPerCycle.put("TITLE", "Number Of Labels");
            returnMapLabelsPerCycle.put("FROM_PLAN",
            							fromOperationHeaderData.get("NUMBER_OF_LABELS").toString()); //FND-22934 NUMBER_OF_LABELS 
            returnMapLabelsPerCycle.put("TO_PLAN",
            							toOperationHeaderData.get("NUMBER_OF_LABELS").toString());
            returnList.add(returnMapLabelsPerCycle);
            
            returnMapReportId.put("TITLE", "Rport Id");
            returnMapReportId.put("FROM_PLAN",
            						fromOperationHeaderData.get("REPORT_ID"));
            returnMapReportId.put("TO_PLAN",
            						toOperationHeaderData.get("REPORT_ID"));
            returnList.add(returnMapReportId);
            
            returnMapReconcileScrap.put("TITLE", "Reconcile Scrap");
            returnMapReconcileScrap.put("FROM_PLAN",
            							fromOperationHeaderData.get("RECONCILE_SCRAP"));
            returnMapReconcileScrap.put("TO_PLAN",
            							toOperationHeaderData.get("RECONCILE_SCRAP"));
            returnList.add(returnMapReconcileScrap);
			// End FND-22137
            
            // FND-19136
            
            returnMapCrossOrder.put("TITLE", "Cross Order");
            returnMapCrossOrder.put("FROM_PLAN", (fromOperationHeaderData.get("CROSS_ORDER_FLAG")).toString());
            returnMapCrossOrder.put("TO_PLAN", toOperationHeaderData.get("CROSS_ORDER_FLAG").toString());
            returnList.add(returnMapCrossOrder);
            
            returnMapOrientation.put("TITLE", "Orientation");
            returnMapOrientation.put("FROM_PLAN", (fromOperationHeaderData.get("ORIENTATION_FLAG")).toString());
            returnMapOrientation.put("TO_PLAN", (toOperationHeaderData.get("ORIENTATION_FLAG")).toString());
            returnList.add(returnMapOrientation);
            
            returnMapMustIssueParts.put("TITLE", "Must Use Issued Parts");
            returnMapMustIssueParts.put("FROM_PLAN", (fromOperationHeaderData.get("MUST_ISSUE_PARTS_FLAG")).toString());
            returnMapMustIssueParts.put("TO_PLAN", (toOperationHeaderData.get("MUST_ISSUE_PARTS_FLAG")).toString());
            returnList.add(returnMapMustIssueParts);
        }
        else if (fromOperationNumberExists && !toOperationNumberExists)
        {
            // Sets the records of return list.
            returnMapOperationNumber.put("TITLE", "Operation Number");
            returnMapOperationNumber.put("FROM_PLAN",
                                         fromOperationHeaderData.get("OPER_NO"));
            returnMapOperationNumber.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOperationNumber);

            returnMapOperUpdateNumber.put("TITLE", "Operation Update Number");
            if (fromOperationHeaderData.get("OPER_UPDT_NO") != null)
            {
                returnMapOperUpdateNumber.put("FROM_PLAN",
                                              fromOperationHeaderData.get("OPER_UPDT_NO")
                                                                     .toString());
            }
            else
            {
                returnMapOperUpdateNumber.put("FROM_PLAN", EMPTY);
            }
            returnMapOperUpdateNumber.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOperUpdateNumber);

            returnMapOperationTitle.put("TITLE", "Operation Title");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapOperationTitle.put("FROM_PLAN",
                                            fromOperationHeaderData.get("OPER_TITLE"));
            }
            else
            {
                returnMapOperationTitle.put("FROM_PLAN",
                                            fromOperationHeaderData.get("TITLE"));
            }
            returnMapOperationTitle.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOperationTitle);

            returnMapOperationType.put("TITLE", "Operation Type");
            returnMapOperationType.put("FROM_PLAN",
                                       fromOperationHeaderData.get("OPER_TYPE"));
            returnMapOperationType.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOperationType);

            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {
                returnMapOperationState.put("TITLE", "Operation State");
                returnMapOperationState.put("FROM_PLAN",
                                            fromOperationHeaderData.get("OPER_STATE"));
                returnMapOperationState.put("TO_PLAN", EMPTY);
                returnList.add(returnMapOperationState);
            }

            returnMapWorkLocation.put("TITLE", "Work Location");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkLocation.put("FROM_PLAN",
                                          fromOperationHeaderData.get("PLND_WORK_LOC"));
            }
            else
            {
                returnMapWorkLocation.put("FROM_PLAN",
                                          fromOperationHeaderData.get("ASGND_WORK_LOC"));
            }
            returnMapWorkLocation.put("TO_PLAN", EMPTY);
            returnList.add(returnMapWorkLocation);

            returnMapWorkDept.put("TITLE", "Work Department");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkDept.put("FROM_PLAN",
                                      fromOperationHeaderData.get("PLND_WORK_DEPT"));
            }
            else
            {
                returnMapWorkDept.put("FROM_PLAN",
                                      fromOperationHeaderData.get("ASGND_WORK_DEPT"));
            }
            returnMapWorkDept.put("TO_PLAN", EMPTY);
            returnList.add(returnMapWorkDept);

            returnMapWorkCenter.put("TITLE", "Work Center");
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                returnMapWorkCenter.put("FROM_PLAN",
                                        fromOperationHeaderData.get("PLND_WORK_CENTER"));
            }
            else
            {
                returnMapWorkCenter.put("FROM_PLAN",
                                        fromOperationHeaderData.get("ASGND_WORK_CENTER"));
            }
            returnMapWorkCenter.put("TO_PLAN", EMPTY);
            returnList.add(returnMapWorkCenter);

            returnMapMachineNumber.put("TITLE", "Machine Number");
            returnMapMachineNumber.put("FROM_PLAN",
                                         fromOperationHeaderData.get("PLND_MACHINE_NO"));
            returnMapMachineNumber.put("TO_PLAN", EMPTY);
            returnList.add(returnMapMachineNumber);

            returnMapOptionalFlag.put("TITLE", "Optional?");
            returnMapOptionalFlag.put("FROM_PLAN",
                                      fromOperationHeaderData.get("OPER_OPT_FLAG"));
            returnMapOptionalFlag.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOptionalFlag);

            returnMapOSP.put("TITLE", "Outside Process (OSP)?");
            returnMapOSP.put("FROM_PLAN",
                             fromOperationHeaderData.get("OSP_FLAG"));
            returnMapOSP.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOSP);

            returnMapAutoStart.put("TITLE", "Auto Start?");
            returnMapAutoStart.put("FROM_PLAN",
                                   fromOperationHeaderData.get("AUTO_START_FLAG"));
            returnMapAutoStart.put("TO_PLAN", EMPTY);
            returnList.add(returnMapAutoStart);

            returnMapAutoComplete.put("TITLE", "Auto Complete?");
            returnMapAutoComplete.put("FROM_PLAN",
                                      fromOperationHeaderData.get("AUTO_COMPLETE_FLAG"));
            returnMapAutoComplete.put("TO_PLAN", EMPTY);
            returnList.add(returnMapAutoComplete);

            returnMapOccurRate.put("TITLE", "Occur Rate");
            if (fromOperationHeaderData.get("OCCUR_RATE") != null)
            {
                returnMapOccurRate.put("FROM_PLAN",
                                       fromOperationHeaderData.get("OCCUR_RATE")
                                                              .toString());
            }
            else
            {
                returnMapOccurRate.put("FROM_PLAN", EMPTY);
            }
            returnMapOccurRate.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOccurRate);

            returnMapOSPDays.put("TITLE", "OSP Days");
            if (fromOperationHeaderData.get("OSP_DAYS") != null)
            {
                returnMapOSPDays.put("FROM_PLAN",
                                     defaultIfEmpty(fromOperationHeaderData.get("OSP_DAYS")
                                                                                       .toString(),
                                                                EMPTY));
            }
            else
            {
                returnMapOSPDays.put("FROM_PLAN", EMPTY);
            }
            returnMapOSPDays.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOSPDays);

            returnMapOSPCostPerUnit.put("TITLE", "OSP Cost Per Unit");
            if (fromOperationHeaderData.get("OSP_COST_PER_UNIT") != null)
            {
                returnMapOSPCostPerUnit.put("FROM_PLAN",
                                            fromOperationHeaderData.get("OSP_COST_PER_UNIT")
                                                                   .toString());
            }
            else
            {
                returnMapOSPCostPerUnit.put("FROM_PLAN", EMPTY);
            }
            returnMapOSPCostPerUnit.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOSPCostPerUnit);

            returnMapSupplierCode.put("TITLE", "Supplier Code"); 
            returnMapSupplierCode.put("FROM_PLAN", 
                                      fromOperationHeaderData.get("SUPPLIER_CODE")); 
            returnMapSupplierCode.put("TO_PLAN", EMPTY); 
            returnList.add(returnMapSupplierCode); 

            returnMapTestType.put("TITLE", "Test Type");
            returnMapTestType.put("FROM_PLAN",
                                  fromOperationHeaderData.get("TEST_TYPE"));
            returnMapTestType.put("TO_PLAN", EMPTY);
            returnList.add(returnMapTestType);

            returnMapSeqSteps.put("TITLE", "Execute Steps in Sequence?");
            returnMapSeqSteps.put("FROM_PLAN",
                                  fromOperationHeaderData.get("SEQ_STEPS_FLAG"));
            returnMapSeqSteps.put("TO_PLAN", EMPTY);
            returnList.add(returnMapSeqSteps);
            
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {

                returnMapAuthNo.put("TITLE", "Authorization Number");
                returnMapAuthNo.put("FROM_PLAN",EMPTY);
                returnMapAuthNo.put("TO_PLAN", EMPTY);
                returnList.add(returnMapAuthNo);

                returnList.add(returnMapAuthNote);
            }
            
            // FND-18081
            returnMapOperSequenceNum.put("TITLE", "Oper Sequence Number");
            // GE-2747
            if(equalsIgnoreCase( fromType, "PLAN" ))
            {
                returnMapOperSequenceNum.put("FROM_PLAN",
                                             (fromScopeOperationInfo.get( "EXE_ORDER" )).toString());       
            }
            else
            {
                returnMapOperSequenceNum.put("FROM_PLAN",
        								    (fromOperationHeaderData.get("EXE_ORDER")).toString());
            }
            returnMapOperSequenceNum.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOperSequenceNum);
            
            // Start FND-22137
            returnMapUnitProcessing.put("TITLE", "Unit Processing");
            returnMapUnitProcessing.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("UNIT_PROCESSING"));
            returnMapUnitProcessing.put("TO_PLAN", EMPTY);
            returnList.add(returnMapUnitProcessing);
            
            returnMapUnitsPerCycle.put("TITLE", "Units Per Cycle");
            returnMapUnitsPerCycle.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("UNITS_PER_CYCLE").toString());
            returnMapUnitsPerCycle.put("TO_PLAN", EMPTY);
            returnList.add(returnMapUnitsPerCycle);
            
            if(equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(toType, "ORDER"))
            {
            	returnMapUnitsPerCycleActual.put("TITLE", "Units Per Cycle Actual");
            	returnMapUnitsPerCycleActual.put("FROM_PLAN",
	            									fromOperationHeaderData.get("UNITS_PER_CYCLE_ACTUAL").toString());
            	returnMapUnitsPerCycleActual.put("TO_PLAN", EMPTY);
	            returnList.add(returnMapUnitsPerCycleActual);
            }
            
            returnMapAutoBatchFlag.put("TITLE", "Auto Cycle?");
            returnMapAutoBatchFlag.put("FROM_PLAN",
                                  			fromOperationHeaderData.get("AUTO_CYCLE_FLAG")); //FND-22934 AUTO_CYCLE_FLAG
            returnMapAutoBatchFlag.put("TO_PLAN", EMPTY);
            returnList.add(returnMapAutoBatchFlag);
            
            returnMapPrintLabel.put("TITLE", "Print Label");
            returnMapPrintLabel.put("FROM_PLAN",
            						fromOperationHeaderData.get("PRINT_LABEL"));
            returnMapPrintLabel.put("TO_PLAN", EMPTY);
            returnList.add(returnMapPrintLabel);
            
            returnMapLabelsPerCycle.put("TITLE", "Number Of Labels");
            returnMapLabelsPerCycle.put("FROM_PLAN",
            							fromOperationHeaderData.get("NUMBER_OF_LABELS").toString()); //FND-22934 NUMBER_OF_LABELS
            returnMapLabelsPerCycle.put("TO_PLAN", EMPTY);
            returnList.add(returnMapLabelsPerCycle);
            
            returnMapReportId.put("TITLE", "Reprt Id");
            returnMapReportId.put("FROM_PLAN",
            						fromOperationHeaderData.get("REPORT_ID"));
            returnMapReportId.put("TO_PLAN", EMPTY);
            returnList.add(returnMapReportId);
            
            returnMapReconcileScrap.put("TITLE", "Reconcile Scrap");
            returnMapReconcileScrap.put("FROM_PLAN",
            							fromOperationHeaderData.get("RECONCILE_SCRAP"));
            returnMapReconcileScrap.put("TO_PLAN", EMPTY);
            returnList.add(returnMapReconcileScrap);
			// End FND-22137
            
            // FND-19136
            
            returnMapCrossOrder.put("TITLE", "Cross Order");
            returnMapCrossOrder.put("FROM_PLAN", (fromOperationHeaderData.get("CROSS_ORDER_FLAG")).toString());
            returnMapCrossOrder.put("TO_PLAN", EMPTY);
            returnList.add(returnMapCrossOrder);
            
            returnMapOrientation.put("TITLE", "Orientation");
            returnMapOrientation.put("FROM_PLAN", (fromOperationHeaderData.get("ORIENTATION_FLAG")).toString());
            returnMapOrientation.put("TO_PLAN", EMPTY);
            returnList.add(returnMapOrientation);
            
            returnMapMustIssueParts.put("TITLE", "Must Use Issued Parts");
            returnMapMustIssueParts.put("FROM_PLAN", (fromOperationHeaderData.get("MUST_ISSUE_PARTS_FLAG")).toString());
            returnMapMustIssueParts.put("TO_PLAN", EMPTY);
            returnList.add(returnMapMustIssueParts);
        }
        else if (!fromOperationNumberExists && toOperationNumberExists)
        {
            // Sets the records of return list.
            returnMapOperationNumber.put("TITLE", "Operation Number");
            returnMapOperationNumber.put("FROM_PLAN", EMPTY);
            returnMapOperationNumber.put("TO_PLAN",
                                         toOperationHeaderData.get("OPER_NO"));
            returnList.add(returnMapOperationNumber);

            returnMapOperUpdateNumber.put("TITLE", "Operation Update Number");
            returnMapOperUpdateNumber.put("FROM_PLAN", EMPTY);
            if (toOperationHeaderData.get("OPER_UPDT_NO") != null)
            {
                returnMapOperUpdateNumber.put("TO_PLAN",
                                              toOperationHeaderData.get("OPER_UPDT_NO")
                                                                   .toString());
            }
            else
            {
                returnMapOperUpdateNumber.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOperUpdateNumber);

            returnMapOperationTitle.put("TITLE", "Operation Title");
            returnMapOperationTitle.put("FROM_PLAN", EMPTY);
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapOperationTitle.put("TO_PLAN",
                                            toOperationHeaderData.get("OPER_TITLE"));
            }
            else
            {
                returnMapOperationTitle.put("TO_PLAN",
                                            toOperationHeaderData.get("TITLE"));
            }
            returnList.add(returnMapOperationTitle);

            returnMapOperationType.put("TITLE", "Operation Type");
            returnMapOperationType.put("FROM_PLAN", EMPTY);
            returnMapOperationType.put("TO_PLAN",
                                       toOperationHeaderData.get("OPER_TYPE"));
            returnList.add(returnMapOperationType);

            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {
                returnMapOperationState.put("TITLE", "Operation State");
                returnMapOperationState.put("FROM_PLAN", EMPTY);
                returnMapOperationState.put("TO_PLAN",
                                            toOperationHeaderData.get("OPER_STATE"));
                returnList.add(returnMapOperationState);
            }

            returnMapWorkLocation.put("TITLE", "Work Location");
            returnMapWorkLocation.put("FROM_PLAN", EMPTY);
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkLocation.put("TO_PLAN",
                                          toOperationHeaderData.get("PLND_WORK_LOC"));
            }
            else
            {
                returnMapWorkLocation.put("TO_PLAN",
                                          toOperationHeaderData.get("ASGND_WORK_LOC"));
            }
            returnList.add(returnMapWorkLocation);

            returnMapWorkDept.put("TITLE", "Work Department");
            returnMapWorkDept.put("FROM_PLAN", EMPTY);
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkDept.put("TO_PLAN",
                                      toOperationHeaderData.get("PLND_WORK_DEPT"));
            }
            else
            {
                returnMapWorkDept.put("TO_PLAN",
                                      toOperationHeaderData.get("ASGND_WORK_DEPT"));
            }
            returnList.add(returnMapWorkDept);

            returnMapWorkCenter.put("TITLE", "Work Center");
            returnMapWorkCenter.put("FROM_PLAN", EMPTY);
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                returnMapWorkCenter.put("TO_PLAN",
                                        toOperationHeaderData.get("PLND_WORK_CENTER"));
            }
            else
            {
                returnMapWorkCenter.put("TO_PLAN",
                                        toOperationHeaderData.get("ASGND_WORK_CENTER"));
            }
            returnList.add(returnMapWorkCenter);

            returnMapMachineNumber.put("TITLE", "Machine Number");
            returnMapMachineNumber.put("FROM_PLAN", EMPTY);
            returnMapMachineNumber.put("TO_PLAN",
                                         toOperationHeaderData.get("PLND_MACHINE_NO"));
            returnList.add(returnMapMachineNumber);

            returnMapOptionalFlag.put("TITLE", "Optional?");
            returnMapOptionalFlag.put("FROM_PLAN", EMPTY);
            returnMapOptionalFlag.put("TO_PLAN",
                                      toOperationHeaderData.get("OPER_OPT_FLAG"));
            returnList.add(returnMapOptionalFlag);

            returnMapOSP.put("TITLE", "Outside Process (OSP)?");
            returnMapOSP.put("FROM_PLAN", EMPTY);
            returnMapOSP.put("TO_PLAN", toOperationHeaderData.get("OSP_FLAG"));
            returnList.add(returnMapOSP);

            returnMapAutoStart.put("TITLE", "Auto Start?");
            returnMapAutoStart.put("FROM_PLAN", EMPTY);
            returnMapAutoStart.put("TO_PLAN",
                                   toOperationHeaderData.get("AUTO_START_FLAG"));
            returnList.add(returnMapAutoStart);

            returnMapAutoComplete.put("TITLE", "Auto Complete?");
            returnMapAutoComplete.put("FROM_PLAN", EMPTY);
            returnMapAutoComplete.put("TO_PLAN",
                                      toOperationHeaderData.get("AUTO_COMPLETE_FLAG"));
            returnList.add(returnMapAutoComplete);

            returnMapOccurRate.put("TITLE", "Occur Rate");
            returnMapOccurRate.put("FROM_PLAN", EMPTY);
            if (toOperationHeaderData.get("OCCUR_RATE") != null)
            {
                returnMapOccurRate.put("TO_PLAN",
                                       toOperationHeaderData.get("OCCUR_RATE")
                                                            .toString());
            }
            else
            {
                returnMapOccurRate.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOccurRate);

            returnMapOSPDays.put("TITLE", "OSP Days");
            returnMapOSPDays.put("FROM_PLAN", EMPTY);
            if (toOperationHeaderData.get("OSP_DAYS") != null)
            {
                returnMapOSPDays.put("TO_PLAN",
                                     defaultIfEmpty(toOperationHeaderData.get("OSP_DAYS")
                                                                                     .toString(),
                                                                EMPTY));
            }
            else
            {
                returnMapOSPDays.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOSPDays);

            returnMapOSPCostPerUnit.put("TITLE", "OSP Cost Per Unit");
            returnMapOSPCostPerUnit.put("FROM_PLAN", EMPTY);
            if (toOperationHeaderData.get("OSP_COST_PER_UNIT") != null)
            {
                returnMapOSPCostPerUnit.put("TO_PLAN",
                                            toOperationHeaderData.get("OSP_COST_PER_UNIT")
                                                                 .toString());
            }
            else
            {
                returnMapOSPCostPerUnit.put("TO_PLAN", EMPTY);
            }
            returnList.add(returnMapOSPCostPerUnit);

            returnMapSupplierCode.put("TITLE", "Supplier Code"); 
            returnMapSupplierCode.put("FROM_PLAN", EMPTY); 
            returnMapSupplierCode.put("TO_PLAN", 
                                      toOperationHeaderData.get("SUPPLIER_CODE")); 
            returnList.add(returnMapSupplierCode); 

            returnMapTestType.put("TITLE", "Test Type");
            returnMapTestType.put("FROM_PLAN", EMPTY);
            returnMapTestType.put("TO_PLAN",
                                  toOperationHeaderData.get("TEST_TYPE"));
            returnList.add(returnMapTestType);

            returnMapSeqSteps.put("TITLE", "Execute Steps in Sequence?");
            returnMapSeqSteps.put("FROM_PLAN", EMPTY);
            returnMapSeqSteps.put("TO_PLAN",
                                  toOperationHeaderData.get("SEQ_STEPS_FLAG"));
            returnList.add(returnMapSeqSteps);
            
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(fromType, toType))
            {
                returnMapAuthNo.put("TITLE", "Authorization Number");
                returnMapAuthNo.put("FROM_PLAN", EMPTY);
                returnMapAuthNo.put("TO_PLAN",
                        EMPTY);
                returnList.add(returnMapAuthNo);
                returnList.add(returnMapAuthNote);
            }
            
            //	FND-18081
            returnMapOperSequenceNum.put("TITLE", "Oper Sequence Number");
            returnMapOperSequenceNum.put("FROM_PLAN", EMPTY);
            // GE-2747
            if(equalsIgnoreCase( fromType, "PLAN" ))
            {
                returnMapOperSequenceNum.put("TO_PLAN", (toScopeOperationInfo.get( "EXE_ORDER" )).toString());
            }
            else
            {
                returnMapOperSequenceNum.put("TO_PLAN", (toOperationHeaderData.get("EXE_ORDER")).toString());
            }
            returnList.add(returnMapOperSequenceNum);
            
            // Start FND-22137
            returnMapUnitProcessing.put("TITLE", "Unit Processing");
            returnMapUnitProcessing.put("FROM_PLAN", EMPTY);
            returnMapUnitProcessing.put("TO_PLAN",
                                  			toOperationHeaderData.get("UNIT_PROCESSING"));
            returnList.add(returnMapUnitProcessing);
            
            returnMapUnitsPerCycle.put("TITLE", "Units Per Cycle");
            returnMapUnitsPerCycle.put("FROM_PLAN", EMPTY);
            returnMapUnitsPerCycle.put("TO_PLAN",
                                  			toOperationHeaderData.get("UNITS_PER_CYCLE").toString());
            returnList.add(returnMapUnitsPerCycle);
            
            if(equalsIgnoreCase(fromType, "ORDER") && equalsIgnoreCase(toType, "ORDER"))
            {
            	returnMapUnitsPerCycleActual.put("TITLE", "Units Per Cycle Actual");
            	returnMapUnitsPerCycleActual.put("FROM_PLAN", EMPTY);
            	returnMapUnitsPerCycleActual.put("TO_PLAN",
            										toOperationHeaderData.get("UNITS_PER_CYCLE_ACTUAL").toString());
	            returnList.add(returnMapUnitsPerCycleActual);
            }
            
            returnMapAutoBatchFlag.put("TITLE", "Auto Cycle?");
            returnMapAutoBatchFlag.put("FROM_PLAN", EMPTY);
            returnMapAutoBatchFlag.put("TO_PLAN",
                                  			toOperationHeaderData.get("AUTO_CYCLE_FLAG")); //FND-22934 AUTO_CYCLE_FLAG
            returnList.add(returnMapAutoBatchFlag);
            
            returnMapPrintLabel.put("TITLE", "Print Label");
            returnMapPrintLabel.put("FROM_PLAN", EMPTY);
            returnMapPrintLabel.put("TO_PLAN",
            						toOperationHeaderData.get("PRINT_LABEL"));
            returnList.add(returnMapPrintLabel);
            
            returnMapLabelsPerCycle.put("TITLE", "Number Of Labels");
            returnMapLabelsPerCycle.put("FROM_PLAN", EMPTY);
            returnMapLabelsPerCycle.put("TO_PLAN",
            							toOperationHeaderData.get("NUMBER_OF_LABELS").toString()); //FND-22934 NUMBER_OF_LABELS
            returnList.add(returnMapLabelsPerCycle);
            
            returnMapReportId.put("TITLE", "Report Id");
            returnMapReportId.put("FROM_PLAN", EMPTY);
            returnMapReportId.put("TO_PLAN",
            						toOperationHeaderData.get("REPORT_ID"));
            returnList.add(returnMapReportId);
            
            returnMapReconcileScrap.put("TITLE", "Reconcile Scrap");
            returnMapReconcileScrap.put("FROM_PLAN", EMPTY);
            returnMapReconcileScrap.put("TO_PLAN",
            							toOperationHeaderData.get("RECONCILE_SCRAP"));
            returnList.add(returnMapReconcileScrap);
			// End FND-22137
            
            // FND-19136
            returnMapCrossOrder.put("TITLE", "Cross Order");
            returnMapCrossOrder.put("FROM_PLAN", EMPTY);
            returnMapCrossOrder.put("TO_PLAN", (toOperationHeaderData.get("CROSS_ORDER_FLAG")).toString());
            returnList.add(returnMapCrossOrder);
            
            returnMapOrientation.put("TITLE", "Orientation");
            returnMapOrientation.put("FROM_PLAN", EMPTY);
            returnMapOrientation.put("TO_PLAN", (toOperationHeaderData.get("ORIENTATION_FLAG")).toString());
            returnList.add(returnMapOrientation);
            
            returnMapMustIssueParts.put("TITLE", "Must Use Issued Parts");
            returnMapMustIssueParts.put("FROM_PLAN", EMPTY);
            returnMapMustIssueParts.put("TO_PLAN", (toOperationHeaderData.get("MUST_ISSUE_PARTS_FLAG")).toString());
            returnList.add(returnMapMustIssueParts);
        }

        
        Iterator it = returnList.listIterator();
        while(it.hasNext())
        {
         Map map = (Map) it.next();
         map.put("SECURITY_GROUP", null);
        }
        
        return returnList;
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#comparePartDataCollection(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List comparePartDataCollection(String fromPlanId,
                                          Number fromPlanVersion,
                                          Number fromPlanRevision,
                                          Number fromPlanAlterations,
                                          String toPlanId,
                                          Number toPlanVersion,
                                          Number toPlanRevision,
                                          Number toPlanAlterations,
                                          String fromOrderId,
                                          String toOrderId,
                                          String parameters,
                                          String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanParts = new ArrayList();
        List toPlanParts = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);
        
        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );

        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);
        String fromStepNo = substring(param[6], 13);
        String toStepNo = substring(param[9], 11);
        String textType = substring(param[12], 10);
        Number fromOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[1], 14)))
        {
            fromOperationKey = NumberUtils.createNumber(substring(param[1],
                                                                              14));
        }

        Number fromStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[7], 14)))
        {
            fromStepKey = NumberUtils.createNumber(substring(param[7],
                                                                         14));
        }

        Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            if (!isEmpty(substring(param[8], 18)))
            {
                fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8],
                                                                                      18));
            }
            if (!isEmpty(substring(param[2], 18)))
            {
                fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2],
                                                                                           18));
            }
        }

        Number toOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[4], 12)))
        {
            toOperationKey = NumberUtils.createNumber(substring(param[4],
                                                                            12));
        }

        Number toStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[10], 12)))
        {
            toStepKey = NumberUtils.createNumber(substring(param[10],
                                                                       12));
        }

        Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(toType, "PLAN"))
        {
            if (!isEmpty(substring(param[11], 16)))
            {
                toStepUpdateNumber = NumberUtils.createNumber(substring(param[11],
                                                                                    16));
            }
            if (!isEmpty(substring(param[5], 16)))
            {
                toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5],
                                                                                         16));
            }
        }

        String tableName = EMPTY;

        // For Part DC of operation
        if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                || equalsIgnoreCase(toStepNo, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Operation Parts for From Plan
                    fromPlanParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                        fromOperationKey,
                                                                        fromStepKey,
                                                                        fromStepUpdateNumber,
                                                                        fromOperationUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        true);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Parts for From Order
                        fromPlanParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                            fromOperationKey,
                                                                            NumberUtils.INTEGER_MINUS_ONE,
                                                                            textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Parts for From Order in Alteration
                        fromPlanParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                                  fromOperationKey,
                                                                                  NumberUtils.INTEGER_MINUS_ONE,
                                                                                  textType,
                                                                                  fromAltId);
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Operation Parts for To Plan
                    toPlanParts = compareToolDao.selectOperationParts(toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      true);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Parts for TO Order
                        toPlanParts = compareToolDao.selectOperationParts(toOrderId,
                                                                          toOperationKey,
                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                          textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Parts for TO Order
                        toPlanParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                                toOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                textType,
                                                                                toAltId);
                    }
                }
            }
        }
        else
        {
            tableName = "SFFND_HTREF_STEP_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Step Parts for From Plan
                    fromPlanParts = compareToolDao.selectOperationParts(fromPlanId,
                                                                        fromOperationKey,
                                                                        fromStepKey,
                                                                        fromStepUpdateNumber,
                                                                        fromOperationUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        false);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Step Parts for From Order
                        fromPlanParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                            fromOperationKey,
                                                                            fromStepKey,
                                                                            textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Parts for From Order in Alteration
                        fromPlanParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                            fromOperationKey,
                                                                            fromStepKey,
                                                                            textType,
                                                                            fromAltId);
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Step Parts for To Plan
                    toPlanParts = compareToolDao.selectOperationParts(toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      false);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Step Parts for TO Order
                        toPlanParts = compareToolDao.selectOperationParts(toOrderId,
                                                                          toOperationKey,
                                                                          toStepKey,
                                                                          textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Parts for TO Order in Alteration
                        toPlanParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                          toOperationKey,
                                                                          toStepKey,
                                                                          textType,
                                                                          toAltId);
                    }
                }
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
        	List tempFromPlanParts = fromPlanParts;
        	
            // Iterate through the from plan's part to process them
            ListIterator partIterator = tempFromPlanParts.listIterator();
            while (partIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) partIterator.next();

                // Get this part's data
                String partNumber = (String) fromRow.get("PART_NO");
                String partChange = defaultIfEmpty((String) fromRow.get("PART_CHG"),
                                                               "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step Parts for To Plan
                        toPlanParts = compareToolDao.selectComponentPartInToPlan(toPlanId,
                                                                                 toOperationKey,
                                                                                 toOperationUpdateNumber,
                                                                                 toStepKey,
                                                                                 toStepUpdateNumber,
                                                                                 textType,
                                                                                 tableName,
                                                                                 true,
                                                                                 partNumber,
                                                                                 partChange);
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Step Parts for TO Order
                            toPlanParts = compareToolDao.selectOperationParts(toOrderId,
                                                                              toOperationKey,
                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                              textType,
                                                                              partNumber,
                                                                              partChange);
                        }
                        else
                        {
                         // Selects the Step Parts for TO Order
                            toPlanParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                                    toOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                    textType,
                                                                                    partNumber,
                                                                                    partChange,
                                                                                    toAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step Parts for To Plan
                        toPlanParts = compareToolDao.selectComponentPartInToPlan(toPlanId,
                                                                                 toOperationKey,
                                                                                 toOperationUpdateNumber,
                                                                                 toStepKey,
                                                                                 toStepUpdateNumber,
                                                                                 textType,
                                                                                 tableName,
                                                                                 false,
                                                                                 partNumber,
                                                                                 partChange);
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Step Parts for TO Order
                            toPlanParts = compareToolDao.selectOperationParts(toOrderId,
                                                                              toOperationKey,
                                                                              toStepKey,
                                                                              textType,
                                                                              partNumber,
                                                                              partChange);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Parts for TO Order in Alteration
                            toPlanParts = compareToolDao.selectOperationPartsAltLog(toOrderId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    textType,
                                                                                    partNumber,
                                                                                    partChange,
                                                                                    toAltId);
                        }
                    }
                }

                if (toPlanParts.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                	List tempToPlanParts = toPlanParts;
                	
                    ListIterator toPartIterator = tempToPlanParts.listIterator();
                    Map toRow = (Map) toPartIterator.next();

                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }
            
            // FND-14905 Meena
            List tempFromPlanPartList = null;
            
            if(fromPlanParts != null)
            {
            	tempFromPlanPartList = new ArrayList();
            	
            	Iterator fromPlanPartIterator = fromPlanParts.listIterator();
            	
            	while(fromPlanPartIterator.hasNext())
            	{
            		Map fromPlanPartMap = (Map)fromPlanPartIterator.next();
            		
            		String slideId = (String)fromPlanPartMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		fromPlanPartMap.put("IMAGE", image);

            		tempFromPlanPartList.add(fromPlanPartMap);
            	}
            	
            	if(tempFromPlanPartList.size() > 0)
            	{
            		fromPlanParts = tempFromPlanPartList;
            	}
            }

            if (fromPlanParts.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("PART_ACTION", null);
                resultRow.put("PART_NO", null);
                resultRow.put("PART_CHG", null);
                resultRow.put("PART_TITLE", null);
                resultRow.put("REF_DES", null);
                resultRow.put("UOM", null);
                resultRow.put("ITEM_NOTES", null);
                resultRow.put("ITEM_QTY", null);
                resultRow.put("OVER_CONSUMPTION_FLAG", null);
                resultRow.put("SERIAL_FLAG", null);
                resultRow.put("LOT_FLAG", null);
                resultRow.put("SPOOL_FLAG", null);
                resultRow.put("EXP_FLAG", null);
                resultRow.put("OPT_DC1_FLAG", null);
                resultRow.put("OPT_DC2_FLAG", null);
                resultRow.put("OPT_DC3_FLAG", null);
                resultRow.put("OPT_DC4_FLAG", null);
                resultRow.put("FIND_NO", null);
                resultRow.put("REF_DES_PREF_RANK", null);
                resultRow.put("STEP_NO", null);
                resultRow.put("PART_TITLE", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("UID_ITEM_FLAG", null);
                resultRow.put("UID_ENTRY_NAME", null);
                resultRow.put("UNIT_TYPE", null);
                resultRow.put("EFF_FROM", null);
                resultRow.put("EFF_THRU", null);
                resultRow.put("EFF_FROM_DATE", null);
                resultRow.put("EFF_THRU_DATE", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("BOM_LINE_NO", null); // FND-21195
                resultRow.put("SECURITY_GROUP", null);
                resultRow.put("PHANTOM_KIT_PART_NO", null);//FND-22203
                resultRow.put("REPLACEMENT_PART_NO", null); // FND-23249
                resultRow.put("REPLACEMENT_PART_CHG", null); // FND-23249

                fromPlanParts.add(resultRow);
            }
            return fromPlanParts;
        }
        else
        {
        	List tempToPlanParts = toPlanParts;
        	
            // Iterate through the to plan's part to process them
            ListIterator partIterator = tempToPlanParts.listIterator();
            while (partIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) partIterator.next();

                // Get this part's data
                String partNumber = (String) toRow.get("PART_NO");
                String partChange = defaultIfEmpty((String) toRow.get("PART_CHG"),
                                                               "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Operation Parts for From Plan
                        fromPlanParts = compareToolDao.selectComponentPartInToPlan(fromPlanId,
                                                                                   fromOperationKey,
                                                                                   fromOperationUpdateNumber,
                                                                                   fromStepKey,
                                                                                   fromStepUpdateNumber,
                                                                                   textType,
                                                                                   tableName,
                                                                                   true,
                                                                                   partNumber,
                                                                                   partChange);
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Step Parts for From Order
                            fromPlanParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                                fromOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                textType,
                                                                                partNumber,
                                                                                partChange);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Parts for From Order in Alteration
                            fromPlanParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      NumberUtils.INTEGER_MINUS_ONE,
                                                                                      textType,
                                                                                      partNumber,
                                                                                      partChange,
                                                                                      fromAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Step Parts for From Plan
                        fromPlanParts = compareToolDao.selectComponentPartInToPlan(fromPlanId,
                                                                                   fromOperationKey,
                                                                                   fromOperationUpdateNumber,
                                                                                   fromStepKey,
                                                                                   fromStepUpdateNumber,
                                                                                   textType,
                                                                                   tableName,
                                                                                   false,
                                                                                   partNumber,
                                                                                   partChange);
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Step Parts for From Order
                            fromPlanParts = compareToolDao.selectOperationParts(fromOrderId,
                                                                                fromOperationKey,
                                                                                fromStepKey,
                                                                                textType,
                                                                                partNumber,
                                                                                partChange);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Parts for From Order in Alteration
                            fromPlanParts = compareToolDao.selectOperationPartsAltLog(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      textType,
                                                                                      partNumber,
                                                                                      partChange,
                                                                                      fromAltId);
                        }
                    }
                }

                if (fromPlanParts.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                	List tempFromPlanParts = fromPlanParts;
                	
                    ListIterator fromPartIterator = tempFromPlanParts.listIterator();
                    Map fromRow = (Map) fromPartIterator.next();

                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }
            
            // FND-14905 Meena
            List tempToPlanPartList = null;
            
            if(toPlanParts != null)
            {
            	tempToPlanPartList = new ArrayList();
            	
            	Iterator toPlanPartIterator = toPlanParts.listIterator();
            	
            	while(toPlanPartIterator.hasNext())
            	{
            		Map toPlanPartMap = (Map)toPlanPartIterator.next();
            		
            		String slideId = (String)toPlanPartMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		toPlanPartMap.put("IMAGE", image);

            		tempToPlanPartList.add(toPlanPartMap);
            	}
            	
            	if(tempToPlanPartList.size() > 0)
            	{
            		toPlanParts = tempToPlanPartList;
            	}
            }

            if (toPlanParts.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("PART_ACTION", null);
                resultRow.put("PART_NO", null);
                resultRow.put("PART_CHG", null);
                resultRow.put("PART_TITLE", null);
                resultRow.put("REF_DES", null);
                resultRow.put("UOM", null);
                resultRow.put("ITEM_NOTES", null);
                resultRow.put("ITEM_QTY", null);
                resultRow.put("OVER_CONSUMPTION_FLAG", null);
                resultRow.put("SERIAL_FLAG", null);
                resultRow.put("LOT_FLAG", null);
                resultRow.put("SPOOL_FLAG", null);
                resultRow.put("EXP_FLAG", null);
                resultRow.put("OPT_DC1_FLAG", null);
                resultRow.put("OPT_DC2_FLAG", null);
                resultRow.put("OPT_DC3_FLAG", null);
                resultRow.put("OPT_DC4_FLAG", null);
                resultRow.put("FIND_NO", null);
                resultRow.put("REF_DES_PREF_RANK", null);
                resultRow.put("STEP_NO", null);
                resultRow.put("PART_TITLE", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("UID_ITEM_FLAG", null);
                resultRow.put("UID_ENTRY_NAME", null);
                resultRow.put("UNIT_TYPE", null);
                resultRow.put("EFF_FROM", null);
                resultRow.put("EFF_THRU", null);
                resultRow.put("EFF_FROM_DATE", null);
                resultRow.put("EFF_THRU_DATE", null);
                resultRow.put("BOM_LINE_NO", null); // FND-21195
                resultRow.put("SECURITY_GROUP", null);
                resultRow.put("PHANTOM_KIT_PART_NO", null);
                resultRow.put("REPLACEMENT_PART_NO", null); // FND-23249
                resultRow.put("REPLACEMENT_PART_CHG", null); // FND-23249
         
                toPlanParts.add(resultRow);
            }
            return toPlanParts;
        }
    }

    // FND-22719    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareImageWithImageListControl(java.lang.String, 
     * java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String, java.lang.Number, java.lang.Number, 
     * java.lang.Number, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List compareImageWithImageListControl(String fromPlanId,
									             Number fromPlanVersion,
									             Number fromPlanRevision,
									             Number fromPlanAlterations,
									             String toPlanId,
									             Number toPlanVersion,
									             Number toPlanRevision,
									             Number toPlanAlterations,
									             String fromOrderId,
									             String toOrderId,
									             String parameters,
									             String calledFrom)
	{
		String fromType = EMPTY;
		String toType = EMPTY;
		
		// Sets the comparison type
		if (isEmpty(fromOrderId))
		{
			fromType = "PLAN";
		}
		else
		{
			fromType = "ORDER";
		}
		
		if (isEmpty(toOrderId))
		{
			toType = "PLAN";
		}
		else
		{
			toType = "ORDER";
		}
		
		// Declares the local variable.
		List fromPlanImages = new ArrayList();
		List toPlanImages = new ArrayList();
		String objectTitle = EMPTY;
		
		// Parses the parameters an gets an array of parameters.
		String[] param = parse.parse(parameters,
							         SEMI_COLON,
							         false);
		
		// FND-25023
		String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
		String toAltId = getParameterValue( param, "TO_ALT_ID" );
		
		String fromOperationNumber = substring(param[0], 13);
		String toOperationNumber = substring(param[3], 11);
		String fromStepNo = substring(param[6], 13);
		String toStepNo = substring(param[9], 11);
		String textType = substring(param[12], 10);
		Number fromOperationKey = NumberUtils.INTEGER_ZERO;
		if (!isEmpty(substring(param[1], 14)))
		{
			fromOperationKey = NumberUtils.createNumber(substring(param[1],
		                                                14));
		}
		
		Number fromStepKey = NumberUtils.INTEGER_ZERO;
		if (!isEmpty(substring(param[7], 14)))
		{
			fromStepKey = NumberUtils.createNumber(substring(param[7],
		                                           14));
		}
		
		Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
		Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
		if (equalsIgnoreCase(fromType, "PLAN"))
		{
			if (!isEmpty(substring(param[8], 18)))
			{
				fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8], 18));
			}
			if (!isEmpty(substring(param[2], 18)))
			{
				fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2], 18));
			}
		}
		
		Number toOperationKey = NumberUtils.INTEGER_ZERO;
		if (!isEmpty(substring(param[4], 12)))
		{
			toOperationKey = NumberUtils.createNumber(substring(param[4], 12));
		}
		
		Number toStepKey = NumberUtils.INTEGER_ZERO;
		if (!isEmpty(substring(param[10], 12)))
		{
			toStepKey = NumberUtils.createNumber(substring(param[10], 12));
		}
		
		Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
		Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
		if (equalsIgnoreCase(toType, "PLAN"))
		{
			if (!isEmpty(substring(param[11], 16)))
			{
				toStepUpdateNumber = NumberUtils.createNumber(substring(param[11], 16));
			}
			if (!isEmpty(substring(param[5], 16)))
			{
				toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5], 16));
			}
		}
		
		String tableName = EMPTY;
		
		if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
		|| equalsIgnoreCase(toStepNo, ELLIPSIS))
		{
			tableName = "SFFND_HTREF_OPER_TEXT";
		
			if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
			{
				if (equalsIgnoreCase(fromType, "PLAN"))
				{
					// Selects the Operation Images for From Plan
					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromPlanId,
													                                          fromOperationKey,
													                                          fromOperationUpdateNumber,
													                                          fromStepKey,
													                                          fromStepUpdateNumber,
													                                          textType,
													                                          tableName,
													                                          true);
				}
				else
				{
				    if(isEmpty( fromAltId ))
                    {
    					// Selects the Operation Images for From Order
    					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
    													                                          fromOperationKey,
    													                                          NumberUtils.INTEGER_MINUS_ONE,
    													                                          textType);
                    }
				    else
				    {
    					//FND-25023 Selects the Operation Images for From Order in Alteration
    					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(fromOrderId,
    					                                                                                fromOperationKey,
    					                                                                                NumberUtils.INTEGER_MINUS_ONE,
    					                                                                                textType,
    					                                                                                fromAltId);
				    }
				}
			}
			else
			{
				if (equalsIgnoreCase(toType, "PLAN"))
				{
					// Selects the Operation Images for To Plan
					toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toPlanId,
													                                        toOperationKey,
													                                        toOperationUpdateNumber,
													                                        toStepKey,
													                                        toStepUpdateNumber,
													                                        textType,
													                                        tableName,
													                                        true);
				}
				else
				{
				    if(isEmpty( toAltId ))
                    {
    					// Selects the Operation Images for TO Order
    					toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
    													                                        toOperationKey,
    													                                        NumberUtils.INTEGER_MINUS_ONE,
    													                                        textType);
                    }
				    else
				    {
    					//FND-25023 Selects the Operation Images for TO Order in Alteration
    					toPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(toOrderId,
    					                                                                              toOperationKey,
    					                                                                              NumberUtils.INTEGER_MINUS_ONE,
    					                                                                              textType,
    					                                                                              toAltId);
				    }
				}
			}
		}
		else
		{
			tableName = "SFFND_HTREF_STEP_TEXT";
			
			if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
			{
				if (equalsIgnoreCase(fromType, "PLAN"))
				{
					// Selects the Step Images for From Plan
					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromPlanId,
													                                          fromOperationKey,
													                                          fromOperationUpdateNumber,
													                                          fromStepKey,
													                                          fromStepUpdateNumber,
													                                          textType,
													                                          tableName,
													                                          false);
				}
				else
				{
				    if(isEmpty( fromAltId ))
                    {
    					// Selects the Step Images for From Order
    					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
    													                                          fromOperationKey,
    													                                          fromStepKey,
    													                                          textType);
                    }
				    else
				    {
    					//FND-25023 Selects the Step Images for From Order in Alteration
    					fromPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(fromOrderId,
    					                                                                                fromOperationKey,
    					                                                                                fromStepKey,
    					                                                                                textType,
    					                                                                                fromAltId);
				    }
				}
			}
			else
			{
				if (equalsIgnoreCase(toType, "PLAN"))
				{
					// Selects the Step Images for To Plan
					toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toPlanId,
													                                        toOperationKey,
													                                        toOperationUpdateNumber,
													                                        toStepKey,
													                                        toStepUpdateNumber,
													                                        textType,
													                                        tableName,
													                                        false);
				}
				else
				{
				    if(isEmpty( toAltId ))
                    {
    					// Selects the Step Images for TO Order
    					toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
    													                                        toOperationKey,
    													                                        toStepKey,
    													                                        textType);
                    }
				    else
				    {
    					//FND-25023 Selects the Step Images for TO Order in Alteration
    					toPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(toOrderId,
    					                                                                              toOperationKey,
    					                                                                              toStepKey,
    					                                                                              textType,
    					                                                                              toAltId);
				    }
				}
			}
		}
		
		if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
		{
			List tempFromPlanImages = fromPlanImages;
			
			// Iterate through the from plan's Image to process them
			ListIterator imageIterator = tempFromPlanImages.listIterator();
			while (imageIterator.hasNext())
			{
				String changeIndicator = EMPTY;
				
				Map fromRow = (Map) imageIterator.next();
				
				// Get this Image's data
				String objectTag = (String) fromRow.get("OBJECT_TAG");
				Number objectRevision = NumberUtils.createNumber((String) fromRow.get("OBJECT_REV"));
				objectTitle = objectTag + COMMA + objectRevision.toString();
				
				String acquireExpress = (String)fromRow.get("ACQUIRE_EXPRESS");
	    		String objectRev = (String)fromRow.get("OBJECT_REV");
	    		String status = (String)fromRow.get("STATUS");
	    		
				if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
				|| equalsIgnoreCase(toStepNo,
				                ELLIPSIS))
				{
					if (equalsIgnoreCase(toType, "PLAN"))
					{
						// Selects the Step Images for To Plan
						toPlanImages = compareToolDao.selectComponentImageInToPlan(toPlanId,
								                                                   toOperationKey,
								                                                   toOperationUpdateNumber,
								                                                   toStepKey,
								                                                   toStepUpdateNumber,
								                                                   textType,
								                                                   tableName,
								                                                   true,
								                                                   objectTag,
								                                                   objectRevision);
					}
					else
					{
					    if(isEmpty( toAltId ))
	                    {
    						// Selects the Step Images for TO Order
    						toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
    													                                            toOperationKey,
    													                                            NumberUtils.INTEGER_MINUS_ONE,
    													                                            textType,
    													                                            objectTag,
    													                                            objectRevision);
	                    }
					    else
					    {
    						//FND-25023 Selects the Step Images for TO Order in Alteration
    						toPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(toOrderId,
    						                                                                              toOperationKey,
    						                                                                              NumberUtils.INTEGER_MINUS_ONE,
    						                                                                              textType,
    						                                                                              objectTag,
    						                                                                              objectRevision,
    						                                                                              toAltId);
					    }
					}
				}
				else
				{
					if (equalsIgnoreCase(toType, "PLAN"))
					{
						// Selects the Step Images for To Plan
						toPlanImages = compareToolDao.selectComponentImageInToPlan(toPlanId,
								                                                   toOperationKey,
								                                                   toOperationUpdateNumber,
								                                                   toStepKey,
								                                                   toStepUpdateNumber,
								                                                   textType,
								                                                   tableName,
								                                                   false,
								                                                   objectTag,
								                                                   objectRevision);
					}
					else
					{
					    if(isEmpty( toAltId ))
                        {
    						// Selects the Step Images for TO Order
    						toPlanImages = compareToolDao.selectOperationImagesfromImageListControl(toOrderId,
    													                                            toOperationKey,
    													                                            toStepKey,
    													                                            textType,
    													                                            objectTag,
    													                                            objectRevision);
                        }
					    else
					    {
    						//FND-25023 Selects the Step Images for TO Order in Alteration
    						toPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(toOrderId,
    						                                                                              toOperationKey,
    						                                                                              toStepKey,
    						                                                                              textType,
    						                                                                              objectTag,
    						                                                                              objectRevision,
    						                                                                              toAltId);
					    }
					}
				}
		
				if (toPlanImages.isEmpty())
				{
					changeIndicator = "MINUS_MARK";
				}
				else
				{
					List tempToPlanImages = toPlanImages;
					
					ListIterator toImageIterator = tempToPlanImages.listIterator();
					Map toRow = (Map) toImageIterator.next();
					
					if (fromRow.equals(toRow))
					{
						changeIndicator = "CHECK_MARK";
					}
					else
					{
						changeIndicator = "PENCIL_MARK";
					}
				}
		
				// Sets the change indicator
				fromRow.put("CHANGE_INDICATOR", changeIndicator);
				fromRow.put("OBJECT_TITLE", objectTitle);
				
				if(equalsIgnoreCase(acquireExpress, Y))
				{
					fromRow.put("OBJECT_TAG_DISP", null);
					fromRow.put("OBJECT_REV_DISP", null);
					fromRow.put("STATUS_DISP", null);
				}	
				else
				{
					fromRow.put("OBJECT_TAG_DISP", objectTag);
					fromRow.put("OBJECT_REV_DISP", objectRev);
					fromRow.put("STATUS_DISP", status);
					
				}
			}
		
			if (fromPlanImages.isEmpty())
			{
				// there is no record.
				Map resultRow = new ListOrderedMap();
				resultRow.put("CHANGE_INDICATOR", null);
				resultRow.put("THUMBNAIL", null);
				resultRow.put("OBJECT_TITLE", null);
				resultRow.put("OBJECT_ID", null);
				resultRow.put("OBJECT_TAG", null);
				resultRow.put("OBJECT_REV", null);
				resultRow.put("OBJECT_DESC", null);
				resultRow.put("SECURITY_GROUP", null);
				resultRow.put("OBJECT_TAG_DISP", null);
				resultRow.put("OBJECT_REV_DISP", null);
				resultRow.put("STATUS_DISP", null);
	    		
				fromPlanImages.add(resultRow);
			}
			return fromPlanImages;
		}
		else
		{
			List tempToPlanImages = toPlanImages;
			
			// Iterate through the to plan's image to process them
			ListIterator imageIterator = tempToPlanImages.listIterator();
			while (imageIterator.hasNext())
			{
				String changeIndicator = EMPTY;
				
				Map toRow = (Map) imageIterator.next();
				
				// Get this part's data
				String objectTag = (String) toRow.get("OBJECT_TAG");
				Number objectRevision = NumberUtils.createNumber((String) toRow.get("OBJECT_REV"));
				objectTitle = objectTag + COMMA + objectRevision.toString();
				
				String acquireExpress = (String)toRow.get("ACQUIRE_EXPRESS");
	    		String objectRev = (String)toRow.get("OBJECT_REV");
	    		String status = (String)toRow.get("STATUS");
				
				if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
				|| equalsIgnoreCase(toStepNo,
				                ELLIPSIS))
				{
					if (equalsIgnoreCase(fromType, "PLAN"))
					{
						// Selects the Operation Images for From Plan
						fromPlanImages = compareToolDao.selectComponentImageInToPlan(fromPlanId,
								                                                     fromOperationKey,
								                                                     fromOperationUpdateNumber,
								                                                     fromStepKey,
								                                                     fromStepUpdateNumber,
								                                                     textType,
								                                                     tableName,
								                                                     true,
								                                                     objectTag,
								                                                     objectRevision);
					}
					else
					{
					    if(isEmpty( fromAltId ))
                        {
    						// Selects the Step Images for From Order
    						fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
    													                                              fromOperationKey,
    													                                              NumberUtils.INTEGER_MINUS_ONE,
    													                                              textType,
    													                                              objectTag,
    													                                              objectRevision);
                        }
					    else
					    {
    						// Selects the Step Images for From Order
    						fromPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(fromOrderId,
    						                                                                                fromOperationKey,
    						                                                                                NumberUtils.INTEGER_MINUS_ONE,
    						                                                                                textType,
    						                                                                                objectTag,
    						                                                                                objectRevision,
    						                                                                                fromAltId);
					    }
					}
				}
				else
				{
					if (equalsIgnoreCase(fromType, "PLAN"))
					{
						// Selects the Step Images for From Plan
						fromPlanImages = compareToolDao.selectComponentImageInToPlan(fromPlanId,
								                                                     fromOperationKey,
								                                                     fromOperationUpdateNumber,
								                                                     fromStepKey,
								                                                     fromStepUpdateNumber,
								                                                     textType,
								                                                     tableName,
								                                                     false,
								                                                     objectTag,
								                                                     objectRevision);
					}
					else
					{
					    if(isEmpty( fromAltId ))
                        {
    						// Selects the Step Images for From Order
    						fromPlanImages = compareToolDao.selectOperationImagesfromImageListControl(fromOrderId,
    													                                              fromOperationKey,
    													                                              fromStepKey,
    													                                              textType,
    													                                              objectTag,
    													                                              objectRevision);
                        }
					    else
					    {
    						//FND-25023 Selects the Step Images for From Order in Alteration
    						fromPlanImages = compareToolDao.selectOperationImagesfromImageListControlAltLog(fromOrderId,
    						                                                                                fromOperationKey,
    						                                                                                fromStepKey,
    						                                                                                textType,
    						                                                                                objectTag,
    						                                                                                objectRevision,
    						                                                                                fromAltId);
					    }
					}
				}
		
				if (fromPlanImages.isEmpty())
				{
					changeIndicator = "PLUS_MARK";
				}
				else
				{
					List tempFromPlanImages = fromPlanImages;
					
					ListIterator fromImageIterator = tempFromPlanImages.listIterator();
					Map fromRow = (Map) fromImageIterator.next();
					
					if (toRow.equals(fromRow))
					{
						changeIndicator = "CHECK_MARK";
					}
					else
					{
						changeIndicator = "PENCIL_MARK";
					}
				}
		
				// Sets the change indicator
				toRow.put("CHANGE_INDICATOR", changeIndicator);
				toRow.put("OBJECT_TITLE", objectTitle);
				
				if(equalsIgnoreCase(acquireExpress, Y))
				{
					toRow.put("OBJECT_TAG_DISP", null);
					toRow.put("OBJECT_REV_DISP", null);
					toRow.put("STATUS_DISP", null);
				}	
				else
				{
					toRow.put("OBJECT_TAG_DISP", objectTag);
					toRow.put("OBJECT_REV_DISP", objectRev);
					toRow.put("STATUS_DISP", status);
					
				}
		
			}
		
			if (toPlanImages.isEmpty())
			{
				// there is no record.
				Map resultRow = new ListOrderedMap();
				resultRow.put("CHANGE_INDICATOR", null);
				resultRow.put("THUMBNAIL", null);
				resultRow.put("OBJECT_TITLE", null);
				resultRow.put("OBJECT_ID", null);
				resultRow.put("OBJECT_TAG", null);
				resultRow.put("OBJECT_REV", null);
				resultRow.put("OBJECT_DESC", null);
				resultRow.put("SECURITY_GROUP", null);
				resultRow.put("OBJECT_TAG_DISP", null);
				resultRow.put("OBJECT_REV_DISP", null);
				resultRow.put("STATUS_DISP", null);
				
				toPlanImages.add(resultRow);
			}
			return toPlanImages;
		}
	}
	
    // FND-22719
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareImageWithPlanTextImageListControl(
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List compareImageWithPlanTextImageListControl(String fromPlanId,
											             String toPlanId,
											             String fromOrderId,
											             String toOrderId,
											             String parameters,
											             String calledFrom)
	{
		String fromType = EMPTY;
		String toType = EMPTY;
		
		// Sets the comparison type
		// FND-15289 Meena
		if (isEmpty(fromOrderId))
		{
			fromType = "PLAN";
		}
		else
		{
			fromType = "ORDER";
		}
		
		if (isEmpty(toOrderId))
		{
			toType = "PLAN";
		}
		else
		{
			toType = "ORDER";
		}
		
		// Declares the local variable.
		List fromPlanTextImages = new ArrayList();
		List toPlanTextImages = new ArrayList();
		String textType = PLANNING;
		String objectTitle = EMPTY;
		
		// Parses the parameters an gets an array of parameters.
		String[] param = parse.parse(parameters,
									 SEMI_COLON,
									 false);
		Number fromPlanUpdateNumber = NumberUtils.INTEGER_ZERO;
		if (equalsIgnoreCase(fromType, "PLAN"))
		{
			if (!isEmpty(substring(param[12], 18)))
			{
				fromPlanUpdateNumber = NumberUtils.createNumber(substring(param[12], 18));
			}
		}
		
		Number toPlanUpdateNumber = NumberUtils.INTEGER_ZERO;
		if (equalsIgnoreCase(toType, "PLAN"))
		{
			if (!isEmpty(substring(param[13], 16)))
			{
				toPlanUpdateNumber = NumberUtils.createNumber(substring(param[13], 16));
			}
		}
		
		if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
		{
			if (equalsIgnoreCase(fromType, "PLAN"))
			{
				// Selects the Operation Images for From Plan
				fromPlanTextImages = compareToolDao.selectPlanTextImagesfromImageListControl( fromPlanId,
													                                          fromPlanUpdateNumber,
													                                          textType );
			}
			else
			{
				// Selects the Operation Images for From Order
				fromPlanTextImages = compareToolDao.selectOrderTextImagesfromImageListControl(fromOrderId,
													                                          textType);
			}
		}
		else
		{
			if (equalsIgnoreCase(toType, "PLAN"))
			{
				// Selects the Operation Images for To Plan
				toPlanTextImages = compareToolDao.selectPlanTextImagesfromImageListControl( toPlanId,
													                                        toPlanUpdateNumber,
													                                        textType );
			}
			else
			{
				// Selects the Operation Images for TO Order
				toPlanTextImages = compareToolDao.selectOrderTextImagesfromImageListControl(toOrderId,
								                                        					textType);
			}
		}
		
		if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
		{
			List tempFromPlanTextImages = fromPlanTextImages;
			
			// Iterate through the from plan's Image to process them
			ListIterator imageIterator = tempFromPlanTextImages.listIterator();
			while (imageIterator.hasNext())
			{
				String changeIndicator = EMPTY;
				
				Map fromRow = (Map) imageIterator.next();
				
				// Get this Image's data
				String objectTag = (String) fromRow.get("OBJECT_TAG");
				Number objectRevision = NumberUtils.createNumber((String) fromRow.get("OBJECT_REV"));
				objectTitle = objectTag + COMMA + objectRevision.toString();
				
				if (equalsIgnoreCase(toType, "PLAN"))
				{
					// Selects the Step Images for To Plan
					toPlanTextImages = compareToolDao.selectComponentImageInToPlanText(toPlanId,
										                                               toPlanUpdateNumber,
										                                               textType,
										                                               objectTag,
										                                               objectRevision);
				}
				else
				{
					// Selects the Step Images for TO Order
					toPlanTextImages = compareToolDao.selectOrderTextImagesfromImageListControl(toOrderId,
													                                            textType,
													                                            objectTag,
													                                            objectRevision);
				}
			
				if (toPlanTextImages.isEmpty())
				{
					changeIndicator = "MINUS_MARK";
				}
				else
				{
					List tempToPlanTextImages = toPlanTextImages;
					
					ListIterator toImageIterator = tempToPlanTextImages.listIterator();
					Map toRow = (Map) toImageIterator.next();
					
					if (fromRow.equals(toRow))
					{
						changeIndicator = "CHECK_MARK";
					}
					else
					{
						changeIndicator = "PENCIL_MARK";
					}
				}
			
				// Sets the change indicator
				fromRow.put("CHANGE_INDICATOR", changeIndicator);
				fromRow.put("OBJECT_TITLE", objectTitle);
			}
		
			if (fromPlanTextImages.isEmpty())
			{
				// there is no record.
				Map resultRow = new ListOrderedMap();
				resultRow.put("CHANGE_INDICATOR", null);
				resultRow.put("THUMBNAIL", null);
				resultRow.put("OBJECT_TITLE", null);
				resultRow.put("OBJECT_ID", null);
				resultRow.put("OBJECT_TAG", null);
				resultRow.put("OBJECT_REV", null);
				resultRow.put("OBJECT_DESC", null);
				resultRow.put("SECURITY_GROUP", null);
				
				fromPlanTextImages.add(resultRow);
			}
			
			return fromPlanTextImages;
		}
		else
		{
			List tempToPlanTextImages = toPlanTextImages;
			
			// Iterate through the to plan's part to process them
			ListIterator imageIterator = tempToPlanTextImages.listIterator();
			while (imageIterator.hasNext())
			{
				String changeIndicator = EMPTY;
				
				Map toRow = (Map) imageIterator.next();
				
				// Get this part's data
				String objectTag = (String) toRow.get("OBJECT_TAG");
				Number objectRevision = NumberUtils.createNumber((String) toRow.get("OBJECT_REV"));
				objectTitle = objectTag + COMMA + objectRevision.toString();
				
				if (equalsIgnoreCase(fromType, "PLAN"))
				{
					// Selects the Operation Images for From Plan Text
					fromPlanTextImages = compareToolDao.selectComponentImageInToPlanText(fromPlanId,
										                                                 fromPlanUpdateNumber,
										                                                 textType,
										                                                 objectTag,
										                                                 objectRevision);
				}
				else
				{
					// Selects the Step Images for From Order Text
					fromPlanTextImages = compareToolDao.selectOrderTextImagesfromImageListControl(fromOrderId,
													                                              textType,
													                                              objectTag,
													                                              objectRevision);
				}
		
				if (fromPlanTextImages.isEmpty())
				{
					changeIndicator = "PLUS_MARK";
				}
				else
				{
					List tempFromPlanTextImages = fromPlanTextImages;
					
					ListIterator fromImageIterator = tempFromPlanTextImages.listIterator();
					Map fromRow = (Map) fromImageIterator.next();
					
					if (toRow.equals(fromRow))
					{
						changeIndicator = "CHECK_MARK";
					}
					else
					{
						changeIndicator = "PENCIL_MARK";
					}
				}
			
				// Sets the change indicator
				toRow.put("CHANGE_INDICATOR", changeIndicator);
				toRow.put("OBJECT_TITLE", objectTitle);
			}
		
			if (toPlanTextImages.isEmpty())
			{
				// there is no record.
				Map resultRow = new ListOrderedMap();
				resultRow.put("CHANGE_INDICATOR", null);
				resultRow.put("THUMBNAIL", null);
				resultRow.put("OBJECT_TITLE", null);
				resultRow.put("OBJECT_ID", null);
				resultRow.put("OBJECT_TAG", null);
				resultRow.put("OBJECT_REV", null);
				resultRow.put("OBJECT_DESC", null);
				resultRow.put("SECURITY_GROUP", null);
				
				toPlanTextImages.add(resultRow);
			}
			return toPlanTextImages;
		}
	}
    
    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareToolDataCollection(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareToolDataCollection(String fromPlanId,
                                          Number fromPlanVersion,
                                          Number fromPlanRevision,
                                          Number fromPlanAlterations,
                                          String toPlanId,
                                          Number toPlanVersion,
                                          Number toPlanRevision,
                                          Number toPlanAlterations,
                                          String fromOrderId,
                                          String toOrderId,
                                          String parameters,
                                          String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanTools = new ArrayList();
        List toPlanTools = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);
        
        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );

        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);
        String fromStepNo = substring(param[6], 13);
        String toStepNo = substring(param[9], 11);
        String textType = substring(param[12], 10);
        Number fromOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[1], 14)))
        {
            fromOperationKey = NumberUtils.createNumber(substring(param[1],
                                                                              14));
        }

        Number fromStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[7], 14)))
        {
            fromStepKey = NumberUtils.createNumber(substring(param[7],
                                                                         14));
        }

        Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            if (!isEmpty(substring(param[8], 18)))
            {
                fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8],
                                                                                      18));
            }
            if (!isEmpty(substring(param[2], 18)))
            {
                fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2],
                                                                                           18));
            }
        }

        Number toOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[4], 12)))
        {
            toOperationKey = NumberUtils.createNumber(substring(param[4],
                                                                            12));
        }

        Number toStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[10], 12)))
        {
            toStepKey = NumberUtils.createNumber(substring(param[10],
                                                                       12));
        }

        Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(toType, "PLAN"))
        {
            if (!isEmpty(substring(param[11], 16)))
            {
                toStepUpdateNumber = NumberUtils.createNumber(substring(param[11],
                                                                                    16));
            }
            if (!isEmpty(substring(param[5], 16)))
            {
                toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5],
                                                                                         16));
            }
        }

        String tableName = EMPTY;

        // For Tool DC of operation
        if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                || equalsIgnoreCase(toStepNo, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Operation Tools for From Plan
                    fromPlanTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                        fromOperationKey,
                                                                        fromStepKey,
                                                                        fromStepUpdateNumber,
                                                                        fromOperationUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        true);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Tools for From Order
                        fromPlanTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                            fromOperationKey,
                                                                            NumberUtils.INTEGER_MINUS_ONE,
                                                                            textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Tools for From Order in Alteration
                        fromPlanTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                                  fromOperationKey,
                                                                                  NumberUtils.INTEGER_MINUS_ONE,
                                                                                  textType,
                                                                                  fromAltId);
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Operation Tools for To Plan
                    toPlanTools = compareToolDao.selectOperationTools(toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      true);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Tools for TO Order
                        toPlanTools = compareToolDao.selectOperationTools(toOrderId,
                                                                          toOperationKey,
                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                          textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Tools for TO Order in Alteration
                        toPlanTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                                toOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                textType,
                                                                                toAltId);
                    }
                }
            }
        }
        else
        {
            tableName = "SFFND_HTREF_STEP_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Step Tools for From Plan
                    fromPlanTools = compareToolDao.selectOperationTools(fromPlanId,
                                                                        fromOperationKey,
                                                                        fromStepKey,
                                                                        fromStepUpdateNumber,
                                                                        fromOperationUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        false);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Step Tools for From Order
                        fromPlanTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                            fromOperationKey,
                                                                            fromStepKey,
                                                                            textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Tools for From Order in Alteration
                        fromPlanTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                                  fromOperationKey,
                                                                                  fromStepKey,
                                                                                  textType,
                                                                                  fromAltId);
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Step Tools for To Plan
                    toPlanTools = compareToolDao.selectOperationTools(toPlanId,
                                                                      toOperationKey,
                                                                      toStepKey,
                                                                      toStepUpdateNumber,
                                                                      toOperationUpdateNumber,
                                                                      textType,
                                                                      tableName,
                                                                      false);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Step Tools for TO Order
                        toPlanTools = compareToolDao.selectOperationTools(toOrderId,
                                                                          toOperationKey,
                                                                          toStepKey,
                                                                          textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Tools for TO Order in Alteration
                        toPlanTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                                toOperationKey,
                                                                                toStepKey,
                                                                                textType,
                                                                                toAltId);
                    }
                }
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
        	List tempFromPlanTools = fromPlanTools;
        	
            // Iterate through the from plan's tool to process them
            ListIterator partIterator = tempFromPlanTools.listIterator();
            while (partIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) partIterator.next();

                // Get this tool's data
                String toolId = (String) fromRow.get("TOOL_ID");
                String toolNumber = (String) fromRow.get("TOOL_NO");
                String toolChange = defaultIfEmpty((String) fromRow.get("TOOL_CHG"),
                                                               "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Operation tools for To Plan
                        toPlanTools = compareToolDao.selectToolInToPlan(toPlanId,
                                                                        toOperationKey,
                                                                        toOperationUpdateNumber,
                                                                        toStepKey,
                                                                        toStepUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        true,
                                                                        toolNumber,
                                                                        toolChange); //FND-28526
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Operation Tools for TO Order
                            toPlanTools = compareToolDao.selectOperationTools(toOrderId,
                                                                              toOperationKey,
                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                              textType,
                                                                              toolNumber,
                            												  toolChange); //FND-28526
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Tools for TO Order in Alteration
                            toPlanTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                                    toOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                    textType,
                                                                                    toolId,
                                                                                    toAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step tools for To Plan
                        toPlanTools = compareToolDao.selectToolInToPlan(toPlanId,
                                                                        toOperationKey,
                                                                        toOperationUpdateNumber,
                                                                        toStepKey,
                                                                        toStepUpdateNumber,
                                                                        textType,
                                                                        tableName,
                                                                        false,
                                                                        toolNumber,
                                                                        toolChange); //FND-28526
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Step Tools for TO Order
                            toPlanTools = compareToolDao.selectOperationTools(toOrderId,
                                                                              toOperationKey,
                                                                              toStepKey,
                                                                              textType,
                                                                              toolNumber,
                                                                              toolChange); //FND-28526
                        }
                        else
                        {
                            //FND-25023 Selects the Step Tools for TO Order in Alteration
                            toPlanTools = compareToolDao.selectOperationToolsAltLog(toOrderId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    textType,
                                                                                    toolId,
                                                                                    toAltId);
                        }
                    }
                }

                if (toPlanTools.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                	List tempToPlanTools = toPlanTools;
                	
                    ListIterator toToolIterator = tempToPlanTools.listIterator();
                    Map toRow = (Map) toToolIterator.next();

                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    //TOOL_ID should not be compared
                    fromRow.remove("TOOL_ID");
                    toRow.remove("TOOL_ID");

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }
            
            // FND-14905 Meena
            List tempFromPlanToolList = null;
            
            if(fromPlanTools != null)
            {
            	tempFromPlanToolList = new ArrayList();
            	
            	Iterator fromPlanToolIterator = fromPlanTools.listIterator();
            	
            	while(fromPlanToolIterator.hasNext())
            	{
            		Map fromPlanToolMap = (Map)fromPlanToolIterator.next();
            		
            		String slideId = (String)fromPlanToolMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		fromPlanToolMap.put("IMAGE", image);

            		tempFromPlanToolList.add(fromPlanToolMap);
            	}
            	
            	if(tempFromPlanToolList.size() > 0)
            	{
            		fromPlanTools = tempFromPlanToolList;
            	}
            }

            if (fromPlanTools.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("IMAGE", null);
                resultRow.put("TOOL_NO", null);
                resultRow.put("TOOL_CHG", null);
                resultRow.put("TOOL_TITLE", null);
                resultRow.put("TOOL_NOTES", null);
                resultRow.put("SERIAL_FLAG", null);
                resultRow.put("EXP_FLAG", null);
                resultRow.put("QTY", null);
                resultRow.put("ITEM_SUBTYPE", null);
                resultRow.put("MANUFACTURER", null);
                resultRow.put("TOOL_MODEL", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("SECURITY_GROUP", null);

                fromPlanTools.add(resultRow);
            }
            return fromPlanTools;
        }
        else
        {
        	List tempToPlanTools = toPlanTools;

            // Iterate through the to plan's tool to process them
            ListIterator toolIterator = tempToPlanTools.listIterator();
            while (toolIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) toolIterator.next();

                // Get this tool's data
                String toolId = (String) toRow.get("TOOL_ID");
                String toolNumber = (String) toRow.get("TOOL_NO");
                String toolChange = defaultIfEmpty((String) toRow.get("TOOL_CHG"),
                                                               "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Operation tools for From Plan
                        fromPlanTools = compareToolDao.selectToolInToPlan(fromPlanId,
                                                                          fromOperationKey,
                                                                          fromOperationUpdateNumber,
                                                                          fromStepKey,
                                                                          fromStepUpdateNumber,
                                                                          textType,
                                                                          tableName,
                                                                          true,
                                                                          toolNumber,
                                                                          toolChange); //FND-28526
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Operation Tools for From Order
                            fromPlanTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                                fromOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                textType,
                                                                                toolNumber,
                                                                                toolChange); //FND-28526
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Tools for From Order in Alteration
                            fromPlanTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      NumberUtils.INTEGER_MINUS_ONE,
                                                                                      textType,
                                                                                      toolId,
                                                                                      fromAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Step tools for From Plan
                        fromPlanTools = compareToolDao.selectToolInToPlan(fromPlanId,
                                                                          fromOperationKey,
                                                                          fromOperationUpdateNumber,
                                                                          fromStepKey,
                                                                          fromStepUpdateNumber,
                                                                          textType,
                                                                          tableName,
                                                                          false,
                                                                          toolNumber,
                                                                          toolChange); //FND-28526
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Step Tools for From Order
                            fromPlanTools = compareToolDao.selectOperationTools(fromOrderId,
                                                                                fromOperationKey,
                                                                                fromStepKey,
                                                                                textType,
                                                                                toolNumber,
                                                                                toolChange); //FND-28526
                        }
                        else
                        {
                            //FND-25023 Selects the Step Tools for From Order
                            fromPlanTools = compareToolDao.selectOperationToolsAltLog(fromOrderId,
                                                                                  fromOperationKey,
                                                                                  fromStepKey,
                                                                                  textType,
                                                                                  toolId,
                                                                                  fromAltId);
                        }
                    }
                }

                if (fromPlanTools.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                	List tempFromPlanTools = fromPlanTools;
                	
                    ListIterator fromToolIterator = tempFromPlanTools.listIterator();
                    Map fromRow = (Map) fromToolIterator.next();
                    
                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    fromRow.remove("TOOL_ID");
                    toRow.remove("TOOL_ID");

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }
            
            // FND-14905 Meena
            List tempToPlanToolList = null;
            
            if(toPlanTools != null)
            {
            	tempToPlanToolList = new ArrayList();
            	
            	Iterator toPlanToolIterator = toPlanTools.listIterator();
            	
            	while(toPlanToolIterator.hasNext())
            	{
            		Map toPlanToolMap = (Map)toPlanToolIterator.next();
            		
            		String slideId = (String)toPlanToolMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		toPlanToolMap.put("IMAGE", image);

            		tempToPlanToolList.add(toPlanToolMap);
            	}
            	
            	if(tempToPlanToolList.size() > 0)
            	{
            		toPlanTools = tempToPlanToolList;
            	}
            }

            if (toPlanTools.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("IMAGE", null);
                resultRow.put("TOOL_NO", null);
                resultRow.put("TOOL_CHG", null);
                resultRow.put("TOOL_TITLE", null);
                resultRow.put("TOOL_NOTES", null);
                resultRow.put("SERIAL_FLAG", null);
                resultRow.put("EXP_FLAG", null);
                resultRow.put("QTY", null);
                resultRow.put("ITEM_SUBTYPE", null);
                resultRow.put("MANUFACTURER", null);
                resultRow.put("TOOL_MODEL", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("SECURITY_GROUP", null);

                toPlanTools.add(resultRow);
            }
            return toPlanTools;
        }
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareParameterDataCollection(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareParameterDataCollection(String fromPlanId,
                                               Number fromPlanVersion,
                                               Number fromPlanRevision,
                                               Number fromPlanAlterations,
                                               String toPlanId,
                                               Number toPlanVersion,
                                               Number toPlanRevision,
                                               Number toPlanAlterations,
                                               String fromOrderId,
                                               String toOrderId,
                                               String parameters,
                                               String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanParameters = new ArrayList();
        List toPlanParameters = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );

        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);
        String fromStepNo = substring(param[6], 13);
        String toStepNo = substring(param[9], 11);
        String textType = substring(param[12], 10);
        Number fromOperationKey = NumberUtils.INTEGER_ZERO;

        if (!isEmpty(substring(param[1], 14)))
        {
            fromOperationKey = NumberUtils.createNumber(substring(param[1],
                                                                              14));
        }

        Number fromStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[7], 14)))
        {
            fromStepKey = NumberUtils.createNumber(substring(param[7],
                                                                         14));
        }

        Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            if (!isEmpty(substring(param[8], 18)))
            {
                fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8],
                                                                                      18));
            }

            if (!isEmpty(substring(param[2], 18)))
            {
                fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2],
                                                                                           18));
            }
        }

        Number toOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[4], 12)))
        {
            toOperationKey = NumberUtils.createNumber(substring(param[4],
                                                                            12));
        }

        Number toStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[10], 12)))
        {
            toStepKey = NumberUtils.createNumber(substring(param[10],
                                                                       12));
        }

        Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(toType, "PLAN"))
        {
            if (!isEmpty(substring(param[11], 16)))
            {
                toStepUpdateNumber = NumberUtils.createNumber(substring(param[11],
                                                                                    16));
            }

            if (!isEmpty(substring(param[5], 16)))
            {
                toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5],
                                                                                         16));
            }
        }

        String tableName = EMPTY;

        // For Parameter DC of operation
        if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                || equalsIgnoreCase(toStepNo, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Operation Parameter DC for From Plan
                    fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      fromStepUpdateNumber,
                                                                                      fromOperationUpdateNumber,
                                                                                      textType,
                                                                                      tableName,
                                                                                      true,
                                                                                      null);  // GE-1353
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Data Collection for From Order
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                                          textType,
                                                                                          null);  // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                        fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                fromOperationKey,
                                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                                textType,
                                                                                                fromAltId,
                                                                                                null);  // GE-1353
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Operation Parameter DC for To Plan
                    toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    toStepUpdateNumber,
                                                                                    toOperationUpdateNumber,
                                                                                    textType,
                                                                                    tableName,
                                                                                    true,
                                                                                    null);  // GE-1353
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Data Collection for To Order
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                        toOperationKey,
                                                                                        NumberUtils.INTEGER_MINUS_ONE,
                                                                                        textType,
                                                                                        null);  // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                        toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                              toOperationKey,
                                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                                              textType,
                                                                                              toAltId,
                                                                                              null);  // GE-1353
                    }
                }
            }
        }
        else
        {
            tableName = "SFFND_HTREF_STEP_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Step Parameter DC for From Plan
                    fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      fromStepUpdateNumber,
                                                                                      fromOperationUpdateNumber,
                                                                                      textType,
                                                                                      tableName,
                                                                                      false,
                                                                                      null);  // GE-1353
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Data Collection for From Order
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          fromStepKey,
                                                                                          textType,
                                                                                          null);  // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                        fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                fromOperationKey,
                                                                                                fromStepKey,
                                                                                                textType,
                                                                                                fromAltId,
                                                                                                null);  // GE-1353
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Step Parameter DC for To Plan
                    toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    toStepUpdateNumber,
                                                                                    toOperationUpdateNumber,
                                                                                    textType,
                                                                                    tableName,
                                                                                    false,
                                                                                    null);  // GE-1353
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Data Collection for To Order
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                        toOperationKey,
                                                                                        toStepKey,
                                                                                        textType,
                                                                                        null);  // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                        toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                              toOperationKey,
                                                                                              toStepKey,
                                                                                              textType,
                                                                                              toAltId,
                                                                                              null);  // GE-1353
                    }
                }
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
        	List tempFromPlanParameters = fromPlanParameters;
        	
            // Iterate through the from plan's parameter DC to process them
            ListIterator partIterator = tempFromPlanParameters.listIterator();
            while (partIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) partIterator.next();

                // Get this Parameter DC
                String stdParameterDCId = (String) fromRow.get("STD_DATCOL_ID");
                String parameterDCTitle = defaultIfEmpty((String) fromRow.get("DAT_COL_TITLE"),
                                                                     "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Operation parameters for To Plan
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                        toOperationKey,
                                                                                        toOperationUpdateNumber,
                                                                                        toStepKey,
                                                                                        toStepUpdateNumber,
                                                                                        textType,
                                                                                        tableName,
                                                                                        true,
                                                                                        stdParameterDCId,
                                                                                        parameterDCTitle,
                                                                                        null);  // GE-1353
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Operation Data Collection for To Order
                            toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                            toOperationKey,
                                                                                            NumberUtils.INTEGER_MINUS_ONE,
                                                                                            textType,
                                                                                            stdParameterDCId,
                                                                                            parameterDCTitle,
                                                                                            null);  // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                            toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                                  toOperationKey,
                                                                                                  NumberUtils.INTEGER_MINUS_ONE,
                                                                                                  textType,
                                                                                                  stdParameterDCId,
                                                                                                  parameterDCTitle,
                                                                                                  toAltId,
                                                                                                  null);  // GE-1353
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step parameters for To Plan
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                        toOperationKey,
                                                                                        toOperationUpdateNumber,
                                                                                        toStepKey,
                                                                                        toStepUpdateNumber,
                                                                                        textType,
                                                                                        tableName,
                                                                                        false,
                                                                                        stdParameterDCId,
                                                                                        parameterDCTitle,
                                                                                        null);  // GE-1353
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Operation Data Collection for To Order
                            toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                            toOperationKey,
                                                                                            toStepKey,
                                                                                            textType,
                                                                                            stdParameterDCId,
                                                                                            parameterDCTitle,
                                                                                            null);  // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                            toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                                  toOperationKey,
                                                                                                  toStepKey,
                                                                                                  textType,
                                                                                                  stdParameterDCId,
                                                                                                  parameterDCTitle,
                                                                                                  toAltId,
                                                                                                  null);  // GE-1353
                        }
                    }
                }

                if (toPlanParameters.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                	List tempToPlanParameters = toPlanParameters;
                	
                    ListIterator toParameterIterator = tempToPlanParameters.listIterator();
                    Map toRow = (Map) toParameterIterator.next();
                    
                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }
            
            // FND-14905 Meena
            List tempFromPlanParametersList = null;
            
            if(fromPlanParameters != null)
            {
            	tempFromPlanParametersList = new ArrayList();
            	
            	Iterator fromPlanParametersIterator = fromPlanParameters.listIterator();
            	
            	while(fromPlanParametersIterator.hasNext())
            	{
            		Map fromPlanParametersMap = (Map)fromPlanParametersIterator.next();
            		
            		String slideId = (String)fromPlanParametersMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		fromPlanParametersMap.put("IMAGE", image);

            		tempFromPlanParametersList.add(fromPlanParametersMap);
            	}
            	
            	if(tempFromPlanParametersList.size() > 0)
            	{
            		fromPlanParameters = tempFromPlanParametersList;
            	}
            }

            if (fromPlanParameters.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("DAT_COL_TYPE", null);
                resultRow.put("DAT_COL_TITLE", null);
                resultRow.put("DAT_COL_UOM", null);
                resultRow.put("LOWER_LIMIT", null);
                resultRow.put("TARGET_VALUE", null);
                resultRow.put("UPPER_LIMIT", null);
                resultRow.put("DAT_COL_CERT", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("CALC_FLAG", null);  // GE-1353
                resultRow.put("CALC_DC_FLAG", null);  // GE-1353
                resultRow.put("IMAGE", null);  // GE-1353

                fromPlanParameters.add(resultRow);
            }
            return fromPlanParameters;
        }
        else
        {
        	List tempToPlanParameters = toPlanParameters;
        	
            // Iterate through the to plan's parameter to process them
            ListIterator parameterIterator = tempToPlanParameters.listIterator();
            while (parameterIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) parameterIterator.next();

                // Get this parameter's data
                String stdParameterDCId = (String) toRow.get("STD_DATCOL_ID");
                String parameterDCTitle = defaultIfEmpty((String) toRow.get("DAT_COL_TITLE"),
                                                                     "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Operation parameters for From Plan
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                          fromOperationKey,
                                                                                          fromOperationUpdateNumber,
                                                                                          fromStepKey,
                                                                                          fromStepUpdateNumber,
                                                                                          textType,
                                                                                          tableName,
                                                                                          true,
                                                                                          stdParameterDCId,
                                                                                          parameterDCTitle,
                                                                                          null);  // GE-1353
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Operation Data Collection for From Order
                            fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                              fromOperationKey,
                                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                                              textType,
                                                                                              stdParameterDCId,
                                                                                              parameterDCTitle,
                                                                                              null);  // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                            fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                    fromOperationKey,
                                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                                    textType,
                                                                                                    stdParameterDCId,
                                                                                                    parameterDCTitle,
                                                                                                    fromAltId,
                                                                                                    null);  // GE-1353
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Step parameters for From Plan
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                          fromOperationKey,
                                                                                          fromOperationUpdateNumber,
                                                                                          fromStepKey,
                                                                                          fromStepUpdateNumber,
                                                                                          textType,
                                                                                          tableName,
                                                                                          false,
                                                                                          stdParameterDCId,
                                                                                          parameterDCTitle,
                                                                                          null);  // GE-1353
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Operation Data Collection for From Order
                            fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                              fromOperationKey,
                                                                                              fromStepKey,
                                                                                              textType,
                                                                                              stdParameterDCId,
                                                                                              parameterDCTitle,
                                                                                              null);  // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                            fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                    fromOperationKey,
                                                                                                    fromStepKey,
                                                                                                    textType,
                                                                                                    stdParameterDCId,
                                                                                                    parameterDCTitle,
                                                                                                    fromAltId,
                                                                                                    null);  // GE-1353
                        }
                    }
                }

                if (fromPlanParameters.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                	List tempFromPlanParameters = fromPlanParameters;
                	
                    ListIterator fromParameterIterator = tempFromPlanParameters.listIterator();
                    Map fromRow = (Map) fromParameterIterator.next();
                    
                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }
            
            // FND-14905 Meena
            List tempToPlanParametersList = null;
            
            if(toPlanParameters != null)
            {
            	tempToPlanParametersList = new ArrayList();
            	
            	Iterator toPlanParametersIterator = toPlanParameters.listIterator();
            	
            	while(toPlanParametersIterator.hasNext())
            	{
            		Map toPlanParametersMap = (Map)toPlanParametersIterator.next();
            		
            		String slideId = (String)toPlanParametersMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		toPlanParametersMap.put("IMAGE", image);

            		tempToPlanParametersList.add(toPlanParametersMap);
            	}
            	
            	if(tempToPlanParametersList.size() > 0)
            	{
            		toPlanParameters = tempToPlanParametersList;
            	}
            }

            if (toPlanParameters.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("DAT_COL_TYPE", null);
                resultRow.put("DAT_COL_TITLE", null);
                resultRow.put("DAT_COL_UOM", null);
                resultRow.put("LOWER_LIMIT", null);
                resultRow.put("TARGET_VALUE", null);
                resultRow.put("UPPER_LIMIT", null);
                resultRow.put("DAT_COL_CERT", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("CALC_FLAG", null);  // GE-1353
                resultRow.put("CALC_DC_FLAG", null);  // GE-1353
                resultRow.put("IMAGE", null);  // GE-1353

                toPlanParameters.add(resultRow);
            }
            return toPlanParameters;
        }
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareParameterDataCollection(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareCDC(String fromPlanId,
                           Number fromPlanVersion,
                           Number fromPlanRevision,
                           Number fromPlanAlterations,
                           String toPlanId,
                           Number toPlanVersion,
                           Number toPlanRevision,
                           Number toPlanAlterations,
                           String fromOrderId,
                           String toOrderId,
                           String parameters,
                           String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanParameters = new ArrayList();
        List toPlanParameters = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );

        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);
        String fromStepNo = substring(param[6], 13);
        String toStepNo = substring(param[9], 11);
        String textType = substring(param[12], 10);
        Number fromOperationKey = NumberUtils.INTEGER_ZERO;

        if (!isEmpty(substring(param[1], 14)))
        {
            fromOperationKey = NumberUtils.createNumber(substring(param[1],
                                                                              14));
        }

        Number fromStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[7], 14)))
        {
            fromStepKey = NumberUtils.createNumber(substring(param[7],
                                                                         14));
        }

        Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            if (!isEmpty(substring(param[8], 18)))
            {
                fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8],
                                                                                      18));
            }

            if (!isEmpty(substring(param[2], 18)))
            {
                fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2],
                                                                                           18));
            }
        }

        Number toOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[4], 12)))
        {
            toOperationKey = NumberUtils.createNumber(substring(param[4],
                                                                            12));
        }

        Number toStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[10], 12)))
        {
            toStepKey = NumberUtils.createNumber(substring(param[10],
                                                                       12));
        }

        Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(toType, "PLAN"))
        {
            if (!isEmpty(substring(param[11], 16)))
            {
                toStepUpdateNumber = NumberUtils.createNumber(substring(param[11],
                                                                                    16));
            }

            if (!isEmpty(substring(param[5], 16)))
            {
                toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5],
                                                                                         16));
            }
        }

        String tableName = EMPTY;

        // For Parameter DC of operation
        if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                || equalsIgnoreCase(toStepNo, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Operation Parameter DC for From Plan
                    fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      fromStepUpdateNumber,
                                                                                      fromOperationUpdateNumber,
                                                                                      textType,
                                                                                      tableName,
                                                                                      true,
                                                                                      CALCULATED_DC);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Data Collection for From Order
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                                          textType,
                                                                                          CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                        fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                fromOperationKey,
                                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                                textType,
                                                                                                fromAltId,
                                                                                                CALCULATED_DC); // GE-1353
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Operation Parameter DC for To Plan
                    toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    toStepUpdateNumber,
                                                                                    toOperationUpdateNumber,
                                                                                    textType,
                                                                                    tableName,
                                                                                    true,
                                                                                    CALCULATED_DC); // GE-1353
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Data Collection for To Order
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                        toOperationKey,
                                                                                        NumberUtils.INTEGER_MINUS_ONE,
                                                                                        textType,
                                                                                        CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                        toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                              toOperationKey,
                                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                                              textType,
                                                                                              toAltId,
                                                                                              CALCULATED_DC); // GE-1353
                    }
                }
            }
        }
        else
        {
            tableName = "SFFND_HTREF_STEP_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Step Parameter DC for From Plan
                    fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      fromStepUpdateNumber,
                                                                                      fromOperationUpdateNumber,
                                                                                      textType,
                                                                                      tableName,
                                                                                      false,
                                                                                      CALCULATED_DC); // GE-1353
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Data Collection for From Order
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          fromStepKey,
                                                                                          textType,
                                                                                          CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                        fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                fromOperationKey,
                                                                                                fromStepKey,
                                                                                                textType,
                                                                                                fromAltId,
                                                                                                CALCULATED_DC); // GE-1353
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Step Parameter DC for To Plan
                    toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                    toOperationKey,
                                                                                    toStepKey,
                                                                                    toStepUpdateNumber,
                                                                                    toOperationUpdateNumber,
                                                                                    textType,
                                                                                    tableName,
                                                                                    false,
                                                                                    CALCULATED_DC); // GE-1353
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Data Collection for To Order
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                        toOperationKey,
                                                                                        toStepKey,
                                                                                        textType,
                                                                                        CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                        toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                              toOperationKey,
                                                                                              toStepKey,
                                                                                              textType,
                                                                                              toAltId,
                                                                                              CALCULATED_DC); // GE-1353
                    }
                }
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            List tempFromPlanParameters = fromPlanParameters;
            
            // Iterate through the from plan's parameter DC to process them
            ListIterator partIterator = tempFromPlanParameters.listIterator();
            while (partIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) partIterator.next();

                // Get this Parameter DC
                String stdParameterDCId = (String) fromRow.get("STD_DATCOL_ID");
                String parameterDCTitle = defaultIfEmpty((String) fromRow.get("DAT_COL_TITLE"),
                                                                     "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Operation parameters for To Plan
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                        toOperationKey,
                                                                                        toOperationUpdateNumber,
                                                                                        toStepKey,
                                                                                        toStepUpdateNumber,
                                                                                        textType,
                                                                                        tableName,
                                                                                        true,
                                                                                        stdParameterDCId,
                                                                                        parameterDCTitle,
                                                                                        CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Operation Data Collection for To Order
                            toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                            toOperationKey,
                                                                                            NumberUtils.INTEGER_MINUS_ONE,
                                                                                            textType,
                                                                                            stdParameterDCId,
                                                                                            parameterDCTitle,
                                                                                            CALCULATED_DC); // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                            toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                                  toOperationKey,
                                                                                                  NumberUtils.INTEGER_MINUS_ONE,
                                                                                                  textType,
                                                                                                  stdParameterDCId,
                                                                                                  parameterDCTitle,
                                                                                                  toAltId,
                                                                                                  CALCULATED_DC); // GE-1353
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step parameters for To Plan
                        toPlanParameters = compareToolDao.selectOperationDataCollection(toPlanId,
                                                                                        toOperationKey,
                                                                                        toOperationUpdateNumber,
                                                                                        toStepKey,
                                                                                        toStepUpdateNumber,
                                                                                        textType,
                                                                                        tableName,
                                                                                        false,
                                                                                        stdParameterDCId,
                                                                                        parameterDCTitle,
                                                                                        CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Operation Data Collection for To Order
                            toPlanParameters = compareToolDao.selectOperationDataCollection(toOrderId,
                                                                                            toOperationKey,
                                                                                            toStepKey,
                                                                                            textType,
                                                                                            stdParameterDCId,
                                                                                            parameterDCTitle,
                                                                                            CALCULATED_DC); // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for To Order in Alteration
                            toPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(toOrderId,
                                                                                                  toOperationKey,
                                                                                                  toStepKey,
                                                                                                  textType,
                                                                                                  stdParameterDCId,
                                                                                                  parameterDCTitle,
                                                                                                  toAltId,
                                                                                                  CALCULATED_DC); // GE-1353
                        }
                    }
                }

                if (toPlanParameters.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                    List tempToPlanParameters = toPlanParameters;
                    
                    ListIterator toParameterIterator = tempToPlanParameters.listIterator();
                    Map toRow = (Map) toParameterIterator.next();
                    
                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }
            
            // FND-14905 Meena
            List tempFromPlanParametersList = null;
            
            if(fromPlanParameters != null)
            {
                tempFromPlanParametersList = new ArrayList();
                
                Iterator fromPlanParametersIterator = fromPlanParameters.listIterator();
                
                while(fromPlanParametersIterator.hasNext())
                {
                    Map fromPlanParametersMap = (Map)fromPlanParametersIterator.next();
                    
                    String slideId = (String)fromPlanParametersMap.get("SLIDE_ID");
                    
                    String image = null;
                    
                    if(isNotEmpty(slideId))
                    {
                        image = "Image";
                    }
                    
                    fromPlanParametersMap.put("IMAGE", image);

                    tempFromPlanParametersList.add(fromPlanParametersMap);
                }
                
                if(tempFromPlanParametersList.size() > 0)
                {
                    fromPlanParameters = tempFromPlanParametersList;
                }
            }

            // GE-5084
            compareToolHelper.convertCDCToLocale(fromPlanParameters);
            
            if (fromPlanParameters.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put( "IMAGE", null );
                resultRow.put( "CALC_DC_FLAG", null );
                resultRow.put( "CALC_FLAG", null );
                resultRow.put("DAT_COL_TYPE", null);
                resultRow.put("DAT_COL_TITLE", null);
                resultRow.put("DAT_COL_UOM", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("EXECUTION_ORDER", null);
                resultRow.put("USE_RANGE", null);
                resultRow.put("EXTERNAL_FLAG", null);
                resultRow.put("FORMULA", null);

                fromPlanParameters.add(resultRow);
            }
            return fromPlanParameters;
        }
        else
        {
            List tempToPlanParameters = toPlanParameters;
            
            // Iterate through the to plan's parameter to process them
            ListIterator parameterIterator = tempToPlanParameters.listIterator();
            while (parameterIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) parameterIterator.next();

                // Get this parameter's data
                String stdParameterDCId = (String) toRow.get("STD_DATCOL_ID");
                String parameterDCTitle = defaultIfEmpty((String) toRow.get("DAT_COL_TITLE"),
                                                                     "");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Operation parameters for From Plan
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                          fromOperationKey,
                                                                                          fromOperationUpdateNumber,
                                                                                          fromStepKey,
                                                                                          fromStepUpdateNumber,
                                                                                          textType,
                                                                                          tableName,
                                                                                          true,
                                                                                          stdParameterDCId,
                                                                                          parameterDCTitle,
                                                                                          CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Operation Data Collection for From Order
                            fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                              fromOperationKey,
                                                                                              NumberUtils.INTEGER_MINUS_ONE,
                                                                                              textType,
                                                                                              stdParameterDCId,
                                                                                              parameterDCTitle,
                                                                                              CALCULATED_DC); // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                            fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                    fromOperationKey,
                                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                                    textType,
                                                                                                    stdParameterDCId,
                                                                                                    parameterDCTitle,
                                                                                                    fromAltId,
                                                                                                    CALCULATED_DC); // GE-1353
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Step parameters for From Plan
                        fromPlanParameters = compareToolDao.selectOperationDataCollection(fromPlanId,
                                                                                          fromOperationKey,
                                                                                          fromOperationUpdateNumber,
                                                                                          fromStepKey,
                                                                                          fromStepUpdateNumber,
                                                                                          textType,
                                                                                          tableName,
                                                                                          false,
                                                                                          stdParameterDCId,
                                                                                          parameterDCTitle,
                                                                                          CALCULATED_DC); // GE-1353
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Operation Data Collection for From Order
                            fromPlanParameters = compareToolDao.selectOperationDataCollection(fromOrderId,
                                                                                              fromOperationKey,
                                                                                              fromStepKey,
                                                                                              textType,
                                                                                              stdParameterDCId,
                                                                                              parameterDCTitle,
                                                                                              CALCULATED_DC); // GE-1353
                        }
                        else
                        {
                            //FND-25023 Selects the Operation Data Collection for From Order in Alteration
                            fromPlanParameters = compareToolDao.selectOperationDataCollectionAltLog(fromOrderId,
                                                                                                    fromOperationKey,
                                                                                                    fromStepKey,
                                                                                                    textType,
                                                                                                    stdParameterDCId,
                                                                                                    parameterDCTitle,
                                                                                                    fromAltId,
                                                                                                    CALCULATED_DC); // GE-1353
                        }
                    }
                }

                if (fromPlanParameters.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                    List tempFromPlanParameters = fromPlanParameters;
                    
                    ListIterator fromParameterIterator = tempFromPlanParameters.listIterator();
                    Map fromRow = (Map) fromParameterIterator.next();
                    
                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }
            
            // FND-14905 Meena
            List tempToPlanParametersList = null;
            
            if(toPlanParameters != null)
            {
                tempToPlanParametersList = new ArrayList();
                
                Iterator toPlanParametersIterator = toPlanParameters.listIterator();
                
                while(toPlanParametersIterator.hasNext())
                {
                    Map toPlanParametersMap = (Map)toPlanParametersIterator.next();
                    
                    String slideId = (String)toPlanParametersMap.get("SLIDE_ID");
                    
                    String image = null;
                    
                    if(isNotEmpty(slideId))
                    {
                        image = "Image";
                    }
                    
                    toPlanParametersMap.put("IMAGE", image);

                    tempToPlanParametersList.add(toPlanParametersMap);
                }
                
                if(tempToPlanParametersList.size() > 0)
                {
                    toPlanParameters = tempToPlanParametersList;
                }
            }

            // GE-5084
            compareToolHelper.convertCDCToLocale(toPlanParameters);
            
            if (toPlanParameters.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put( "IMAGE", null );
                resultRow.put( "CALC_DC_FLAG", null );
                resultRow.put( "CALC_FLAG", null );
                resultRow.put("DAT_COL_TYPE", null);
                resultRow.put("DAT_COL_TITLE", null);
                resultRow.put("DAT_COL_UOM", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("DISPLAY_LINE_NO", null); // FND-13671
                resultRow.put("EXECUTION_ORDER", null);
                resultRow.put("USE_RANGE", null);
                resultRow.put("EXTERNAL_FLAG", null);
                resultRow.put("FORMULA", null);

                toPlanParameters.add(resultRow);
            }
            return toPlanParameters;
        }
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareBuyoff(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareBuyoff(String fromPlanId,
                              Number fromPlanVersion,
                              Number fromPlanRevision,
                              Number fromPlanAlterations,
                              String toPlanId,
                              Number toPlanVersion,
                              Number toPlanRevision,
                              Number toPlanAlterations,
                              String fromOrderId,
                              String toOrderId,
                              String parameters,
                              String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanBuyoffs = new ArrayList();
        List toPlanBuyoff = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );
        
        String fromOperationNumber = substring(param[0], 13);
        String toOperationNumber = substring(param[3], 11);
        String fromStepNo = substring(param[6], 13);
        String toStepNo = substring(param[9], 11);
        String textType = substring(param[12], 10);
        Number fromOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[1], 14)))
        {
            fromOperationKey = NumberUtils.createNumber(substring(param[1],
                                                                              14));
        }

        Number fromStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[7], 14)))
        {
            fromStepKey = NumberUtils.createNumber(substring(param[7],
                                                                         14));
        }

        Number fromStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number fromOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(fromType, "PLAN"))
        {
            if (!isEmpty(substring(param[8], 18)))
            {
                fromStepUpdateNumber = NumberUtils.createNumber(substring(param[8],
                                                                                      18));
            }

            if (!isEmpty(substring(param[2], 18)))
            {
                fromOperationUpdateNumber = NumberUtils.createNumber(substring(param[2],
                                                                                           18));
            }
        }

        Number toOperationKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[4], 12)))
        {
            toOperationKey = NumberUtils.createNumber(substring(param[4],
                                                                            12));
        }

        Number toStepKey = NumberUtils.INTEGER_ZERO;
        if (!isEmpty(substring(param[10], 12)))
        {
            toStepKey = NumberUtils.createNumber(substring(param[10],
                                                                       12));
        }

        Number toStepUpdateNumber = NumberUtils.INTEGER_ZERO;
        Number toOperationUpdateNumber = NumberUtils.INTEGER_ZERO;
        if (equalsIgnoreCase(toType, "PLAN"))
        {
            if (!isEmpty(substring(param[11], 16)))
            {
                toStepUpdateNumber = NumberUtils.createNumber(substring(param[11],
                                                                                    16));
            }

            if (!isEmpty(substring(param[5], 16)))
            {
                toOperationUpdateNumber = NumberUtils.createNumber(substring(param[5],
                                                                                         16));
            }
        }

        String tableName = EMPTY;

        // For Buyoff of operation
        if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                || equalsIgnoreCase(toStepNo, ELLIPSIS))
        {
            tableName = "SFFND_HTREF_OPER_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Operation Buyoff for From Plan
                    fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                            fromOperationKey,
                                                                            fromStepKey,
                                                                            fromStepUpdateNumber,
                                                                            fromOperationUpdateNumber,
                                                                            textType,
                                                                            tableName,
                                                                            true);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Operation Buyoffs for From Order
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                                fromOperationKey,
                                                                                NumberUtils.INTEGER_MINUS_ONE,
                                                                                textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Buyoffs for From Order in Alteration
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      NumberUtils.INTEGER_MINUS_ONE,
                                                                                      textType,
                                                                                      fromAltId);
                    }

                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Operation Buyoff for To Plan
                    toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                         toOperationKey,
                                                                         toStepKey,
                                                                         toStepUpdateNumber,
                                                                         toOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         true);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Operation Buyoffs for TO Order
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                             toOperationKey,
                                                                             NumberUtils.INTEGER_MINUS_ONE,
                                                                             textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Operation Buyoffs for TO Order in Alteration
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                   toOperationKey,
                                                                                   NumberUtils.INTEGER_MINUS_ONE,
                                                                                   textType,
                                                                                   toAltId);
                    }
                }
            }
        }
        else
        {
            tableName = "SFFND_HTREF_STEP_TEXT";

            if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
            {
                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Step Buyoff for From Plan
                    fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                            fromOperationKey,
                                                                            fromStepKey,
                                                                            fromStepUpdateNumber,
                                                                            fromOperationUpdateNumber,
                                                                            textType,
                                                                            tableName,
                                                                            false);
                }
                else
                {
                    if(isEmpty( fromAltId ))
                    {
                        // Selects the Step Buyoffs for From Order
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                                fromOperationKey,
                                                                                fromStepKey,
                                                                                textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Buyoffs for From Order in Alteration
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                      fromOperationKey,
                                                                                      fromStepKey,
                                                                                      textType,
                                                                                      fromAltId);
                    }
                }
            }
            else
            {
                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Step Buyoff for To Plan
                    toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                         toOperationKey,
                                                                         toStepKey,
                                                                         toStepUpdateNumber,
                                                                         toOperationUpdateNumber,
                                                                         textType,
                                                                         tableName,
                                                                         false);
                }
                else
                {
                    if(isEmpty( toAltId ))
                    {
                        // Selects the Step Buyoffs for TO Order
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                             toOperationKey,
                                                                             toStepKey,
                                                                             textType);
                    }
                    else
                    {
                        //FND-25023 Selects the Step Buyoffs for TO Order in Alteration
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                   toOperationKey,
                                                                                   toStepKey,
                                                                                   textType,
                                                                                   toAltId);
                    }
                }
            }
        }

        String buyoffExclude = APOSTROPHE
                + BUYOFF_ID + APOSTROPHE;

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
        	List tempFromPlanBuyoffs = fromPlanBuyoffs;
        	
            // Iterate through the from plan's Buyoff to process them
            ListIterator buyoffIterator = tempFromPlanBuyoffs.listIterator();
            while (buyoffIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) buyoffIterator.next();

                // Get this Buyoff
                String buyoffType = (String) fromRow.get("BUYOFF_TYPE");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Operation buyoff for To Plan
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             toStepKey,
                                                                             toStepUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             true,
                                                                             buyoffType,
                                                                             buyoffExclude);
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Step Buyoffs for TO Order
                            toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                                 toOperationKey,
                                                                                 NumberUtils.INTEGER_MINUS_ONE,
                                                                                 textType,
                                                                                 buyoffType,
                                                                                 buyoffExclude);
                        }
                        else
                        {
                            // Selects the Step Buyoffs for TO Order
                            toPlanBuyoff = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                       toOperationKey,
                                                                                       NumberUtils.INTEGER_MINUS_ONE,
                                                                                       textType,
                                                                                       buyoffType,
                                                                                       buyoffExclude,
                                                                                       toAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(toType, "PLAN"))
                    {
                        // Selects the Step buyoff for To Plan
                        toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toPlanId,
                                                                             toOperationKey,
                                                                             toOperationUpdateNumber,
                                                                             toStepKey,
                                                                             toStepUpdateNumber,
                                                                             textType,
                                                                             tableName,
                                                                             false,
                                                                             buyoffType,
                                                                             buyoffExclude);
                    }
                    else
                    {
                        if(isEmpty( toAltId ))
                        {
                            // Selects the Step Buyoffs for TO Order
                            toPlanBuyoff = compareToolDao.selectOperationBuyoffs(toOrderId,
                                                                                 toOperationKey,
                                                                                 toStepKey,
                                                                                 textType,
                                                                                 buyoffType,
                                                                                 buyoffExclude);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Buyoffs for TO Order in Alteration
                            toPlanBuyoff = compareToolDao.selectOperationBuyoffsAltLog(toOrderId,
                                                                                       toOperationKey,
                                                                                       toStepKey,
                                                                                       textType,
                                                                                       buyoffType,
                                                                                       buyoffExclude,
                                                                                       toAltId);
                        }
                    }
                }

                if (toPlanBuyoff.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                	List tempToPlanBuyoff = toPlanBuyoff;
                	
                    ListIterator toBuyoffIterator = tempToPlanBuyoff.listIterator();
                    Map toRow = (Map) toBuyoffIterator.next();

                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    // Make the Buyoff Id as same in both the rows
                    fromRow.put("BUYOFF_ID", (String) toRow.get("BUYOFF_ID"));

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                        // Add Buyoff Id in exclude list
                        buyoffExclude = buyoffExclude + COMMA
                                + APOSTROPHE
                                + (String) toRow.get("BUYOFF_ID")
                                + APOSTROPHE;
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                        // Add Buyoff Id in exclude list
                        buyoffExclude = buyoffExclude + COMMA
                                + APOSTROPHE
                                + (String) toRow.get("BUYOFF_ID")
                                + APOSTROPHE;
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }
            
            List tempFromPlanBuyoffList = null;
            
            // FND-14905 Meena
            if(fromPlanBuyoffs != null)
            {
            	tempFromPlanBuyoffList = new ArrayList();
            	
            	Iterator fromPlanBuyoffIterator = fromPlanBuyoffs.listIterator();
            	
            	while(fromPlanBuyoffIterator.hasNext())
            	{
            		Map fromPlanBuyoffMap = (Map)fromPlanBuyoffIterator.next();
            		
            		String slideId = (String)fromPlanBuyoffMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		fromPlanBuyoffMap.put("IMAGE", image);
        			
        			tempFromPlanBuyoffList.add(fromPlanBuyoffMap);
            	}
            	
            	if(tempFromPlanBuyoffList.size() > 0)
            	{
            		fromPlanBuyoffs = tempFromPlanBuyoffList;
            	}
            }

            if (fromPlanBuyoffs.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("BUYOFF_TYPE", null);
                resultRow.put("BUYOFF_TITLE", null);
                resultRow.put("BUYOFF_CERT", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("CROSS_ORDER_FLAG", null);

                fromPlanBuyoffs.add(resultRow);
            }
            return fromPlanBuyoffs;
        }
        else
        {
        	List tempToPlanBuyoffs = toPlanBuyoff;
        	
            // Iterate through the to plan's buyoff to process them
            ListIterator buyoffIterator = tempToPlanBuyoffs.listIterator();
            while (buyoffIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) buyoffIterator.next();

                // Get this buyoff's data
                String buyoffType = (String) toRow.get("BUYOFF_TYPE");

                if (equalsIgnoreCase(fromStepNo, ELLIPSIS)
                        || equalsIgnoreCase(toStepNo,
                                              ELLIPSIS))
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Operation buyoffs for From Plan
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                                fromOperationKey,
                                                                                fromOperationUpdateNumber,
                                                                                fromStepKey,
                                                                                fromStepUpdateNumber,
                                                                                textType,
                                                                                tableName,
                                                                                true,
                                                                                buyoffType,
                                                                                buyoffExclude);
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Step Buyoffs for From Order
                            fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                                    fromOperationKey,
                                                                                    NumberUtils.INTEGER_MINUS_ONE,
                                                                                    textType,
                                                                                    buyoffType,
                                                                                    buyoffExclude);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Buyoffs for From Order in Alteration
                            fromPlanBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          NumberUtils.INTEGER_MINUS_ONE,
                                                                                          textType,
                                                                                          buyoffType,
                                                                                          buyoffExclude,
                                                                                          fromAltId);
                        }
                    }
                }
                else
                {
                    if (equalsIgnoreCase(fromType, "PLAN"))
                    {
                        // Selects the Step buyoffs for From Plan
                        fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromPlanId,
                                                                                fromOperationKey,
                                                                                fromOperationUpdateNumber,
                                                                                fromStepKey,
                                                                                fromStepUpdateNumber,
                                                                                textType,
                                                                                tableName,
                                                                                false,
                                                                                buyoffType,
                                                                                buyoffExclude);
                    }
                    else
                    {
                        if(isEmpty( fromAltId ))
                        {
                            // Selects the Step Buyoffs for From Order
                            fromPlanBuyoffs = compareToolDao.selectOperationBuyoffs(fromOrderId,
                                                                                    fromOperationKey,
                                                                                    fromStepKey,
                                                                                    textType,
                                                                                    buyoffType,
                                                                                    buyoffExclude);
                        }
                        else
                        {
                            //FND-25023 Selects the Step Buyoffs for From Order in Alteration
                            fromPlanBuyoffs = compareToolDao.selectOperationBuyoffsAltLog(fromOrderId,
                                                                                          fromOperationKey,
                                                                                          fromStepKey,
                                                                                          textType,
                                                                                          buyoffType,
                                                                                          buyoffExclude,
                                                                                          fromAltId);
                        }
                    }
                }

                if (fromPlanBuyoffs.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                	List tempFromPlanBuyoffs = fromPlanBuyoffs;
                	
                    ListIterator fromBuyoffIterator = tempFromPlanBuyoffs.listIterator();
                    Map fromRow = (Map) fromBuyoffIterator.next();

                    // Slide Id should not be compared, but it is required to display Camera icon in grid
                    // so do not remove it from the query
                    fromRow.remove("SLIDE_ID");
                    toRow.remove("SLIDE_ID");

                    // Make the Buyoff Id as same in both the rows
                    toRow.put("BUYOFF_ID", (String) fromRow.get("BUYOFF_ID"));

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                        // Add Buyoff Id in exclude list
                        buyoffExclude = buyoffExclude + COMMA
                                + APOSTROPHE
                                + (String) fromRow.get("BUYOFF_ID")
                                + APOSTROPHE;
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                        // Add Buyoff Id in exclude list
                        buyoffExclude = buyoffExclude + COMMA
                                + APOSTROPHE
                                + (String) fromRow.get("BUYOFF_ID")
                                + APOSTROPHE;
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }
            
            // FND-14905 Meena
            List tempToPlanBuyoffList = null;
            
            if(toPlanBuyoff != null)
            {
            	tempToPlanBuyoffList = new ArrayList();
            	
            	Iterator toPlanBuyoffIterator = toPlanBuyoff.listIterator();
            	
            	while(toPlanBuyoffIterator.hasNext())
            	{
            		Map toPlanBuyoffMap = (Map)toPlanBuyoffIterator.next();
            		
            		String slideId = (String)toPlanBuyoffMap.get("SLIDE_ID");
            		
            		String image = null;
            		
            		if(isNotEmpty(slideId))
            		{
            			image = "Image";
            		}
            		
            		toPlanBuyoffMap.put("IMAGE", image);

            		tempToPlanBuyoffList.add(toPlanBuyoffMap);
            	}
            	
            	if(tempToPlanBuyoffList.size() > 0)
            	{
            		toPlanBuyoff = tempToPlanBuyoffList;
            	}
            }

            if (toPlanBuyoff.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("BUYOFF_TYPE", null);
                resultRow.put("BUYOFF_TITLE", null);
                resultRow.put("BUYOFF_CERT", null);
                resultRow.put("OPTIONAL_FLAG", null);
                resultRow.put("CROSS_ORDER_FLAG", null);

                toPlanBuyoff.add(resultRow);
            }
            return toPlanBuyoff;
        }
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareLink(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareLink(String fromPlanId,
                            Number fromPlanVersion,
                            Number fromPlanRevision,
                            Number fromPlanAlterations,
                            String toPlanId,
                            Number toPlanVersion,
                            Number toPlanRevision,
                            Number toPlanAlterations,
                            String fromOrderId,
                            String toOrderId,
                            String parameters,
                            String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanLink = new ArrayList();
        List toPlanLink = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );
        
        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                // Selects the Links for From Plan
                fromPlanLink = compareToolDao.selectPlanLinks(fromPlanId,
                                                              fromPlanVersion,
                                                              fromPlanRevision,
                                                              fromPlanAlterations);
            }
            else
            {
            	if(isEmpty(fromAltId))
            	{
	                // Selects the Order Links for From Order
	                fromPlanLink = compareToolDao.selectOrderLinks(fromOrderId);
            	}            	
            	else
            	{
                	//FND-25023 Selects the Order Links for From Order in Alteration
            		fromPlanLink = compareToolDao.selectOrderLinksAltLog(fromOrderId,
            															 fromAltId);
            	}

            }
        }
        else
        {
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Links for To Plan
                toPlanLink = compareToolDao.selectPlanLinks(toPlanId,
                                                            toPlanVersion,
                                                            toPlanRevision,
                                                            toPlanAlterations);
            }
            else
            {
            	if(isEmpty(toAltId))
            	{
	                // Selects the Order Links for To Order
	                toPlanLink = compareToolDao.selectOrderLinks(toOrderId);
            	}
            	else
            	{
                    // FND-25023 Selects the Order Links for To Order in ALteration
                    toPlanLink = compareToolDao.selectOrderLinksAltLog(toOrderId,
                    												   toAltId);
            	}
            		
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            // Iterate through the from plan's Links to process them
            ListIterator linkIterator = fromPlanLink.listIterator();
            while (linkIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) linkIterator.next();

                // Get this link's data
                String fromNodeNumber = (String) fromRow.get("FROM_NODE_NO");
                String fromNodeType = defaultIfEmpty((String) fromRow.get("FROM_NODE_TYPE"),
                                                                 "");
                String toNodeNumber = (String) fromRow.get("TO_NODE_NO");
                String toNodeType = defaultIfEmpty((String) fromRow.get("TO_NODE_TYPE"),
                                                               "");

                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Links for To Plan
                    toPlanLink = compareToolDao.selectPlanLinks(toPlanId,
                                                                toPlanVersion,
                                                                toPlanRevision,
                                                                toPlanAlterations,
                                                                fromNodeNumber,
                                                                fromNodeType,
                                                                toNodeNumber,
                                                                toNodeType);
                }
                else
                {
                	if(isEmpty(toAltId))
                	{
	                    // Selects the Order Links for To Order
	                    toPlanLink = compareToolDao.selectOrderLinks(toOrderId,
	                                                                 fromNodeNumber,
	                                                                 fromNodeType,
	                                                                 toNodeNumber,
	                                                                 toNodeType);
                	}
                	else
                	{
                        // FND-25023 Selects the Order Links for To Order in Alteration
                        toPlanLink = compareToolDao.selectOrderLinksAltLog(toOrderId,
                                                                     	   fromNodeNumber,
                                                                     	   fromNodeType,
                                                                     	   toNodeNumber,
                                                                     	   toNodeType,
                                                                     	   toAltId);
                	}
                }

                if (toPlanLink.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                    ListIterator toLinkIterator = toPlanLink.listIterator();
                    Map toRow = (Map) toLinkIterator.next();

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }

            if (fromPlanLink.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("FROM_NODE_NO", null);
                resultRow.put("FROM_NODE_TYPE", null);
                // resultRow.put("FROM_NODE_TITLE", null);
                resultRow.put("TO_NODE_NO", null);
                resultRow.put("TO_NODE_TYPE", null);
                // resultRow.put("TO_NODE_TITLE", null);

                fromPlanLink.add(resultRow);
            }
            return fromPlanLink;
        }
        else
        {
            // Iterate through the to plan's link to process them
            ListIterator linkIterator = toPlanLink.listIterator();
            while (linkIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) linkIterator.next();

                // Get this link's data
                String fromNodeNumber = (String) toRow.get("FROM_NODE_NO");
                String fromNodeType = defaultIfEmpty((String) toRow.get("FROM_NODE_TYPE"),
                                                                 "");
                String toNodeNumber = (String) toRow.get("TO_NODE_NO");
                String toNodeType = defaultIfEmpty((String) toRow.get("TO_NODE_TYPE"),
                                                               "");

                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Link for From Plan
                    fromPlanLink = compareToolDao.selectPlanLinks(fromPlanId,
                                                                  fromPlanVersion,
                                                                  fromPlanRevision,
                                                                  fromPlanAlterations,
                                                                  fromNodeNumber,
                                                                  fromNodeType,
                                                                  toNodeNumber,
                                                                  toNodeType);
                }
                else
                {
                	if(isEmpty(fromAltId))
                	{
	                    // Selects the Order Links for From Order
	                    fromPlanLink = compareToolDao.selectOrderLinks(fromOrderId,
	                                                                   fromNodeNumber,
	                                                                   fromNodeType,
	                                                                   toNodeNumber,
	                                                                   toNodeType);
                	}
                	else
                	{
	                    // FND-25023 Selects the Order Links for From Order in ALteration
	                    fromPlanLink = compareToolDao.selectOrderLinksAltLog(fromOrderId,
	                                                                   		 fromNodeNumber,
	                                                                   		 fromNodeType,
	                                                                   		 toNodeNumber,
	                                                                   		 toNodeType,
	                                                                   		 fromAltId);
                	}
                }

                if (fromPlanLink.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                    ListIterator fromLinkIterator = fromPlanLink.listIterator();
                    Map fromRow = (Map) fromLinkIterator.next();

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }

            if (toPlanLink.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("FROM_NODE_NO", null);
                resultRow.put("FROM_NODE_TYPE", null);
                // resultRow.put("FROM_NODE_TITLE", null);
                resultRow.put("TO_NODE_NO", null);
                resultRow.put("TO_NODE_TYPE", null);
                // resultRow.put("TO_NODE_TITLE", null);

                toPlanLink.add(resultRow);
            }
            return toPlanLink;
        }
    }

    /*
     * since4400 (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareDecision(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareDecision(String fromPlanId,
                                Number fromPlanVersion,
                                Number fromPlanRevision,
                                Number fromPlanAlterations,
                                String toPlanId,
                                Number toPlanVersion,
                                Number toPlanRevision,
                                Number toPlanAlterations,
                                String fromOrderId,
                                String toOrderId,
                                String parameters,
                                String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // Declares the local variable.
        List fromPlanDecision = new ArrayList();
        List toPlanDecision = new ArrayList();

        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );
        
        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                // Selects the Decisions for From Plan
                fromPlanDecision = compareToolDao.selectPlanDecisions(fromPlanId,
                                                                      fromPlanVersion,
                                                                      fromPlanRevision,
                                                                      fromPlanAlterations,
                                                                      DECISION);
            }
            else
            {
            	if(isEmpty(fromAltId))
            	{
	                // Selects the Order Decision for From Order
	                fromPlanDecision = compareToolDao.selectOrderDecisions(fromOrderId,
	                                                                       DECISION);
            	}
            	else
            	{
                    // FND-25023 Selects the Order Decision for From Order in Alteration
                    fromPlanDecision = compareToolDao.selectOrderDecisionsAltLog(fromOrderId,
                                                                           	 	 DECISION,
                                                                           	 	 fromAltId);
            	}
            }
        }
        else
        {
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Decisions for To Plan
                toPlanDecision = compareToolDao.selectPlanDecisions(toPlanId,
                                                                    toPlanVersion,
                                                                    toPlanRevision,
                                                                    toPlanAlterations,
                                                                    DECISION);
            }
            else
            {
            	if(isEmpty(toAltId))
            	{
	                // Selects the Order Decision for To Order
	                toPlanDecision = compareToolDao.selectOrderDecisions(toOrderId,
	                                                                     DECISION);
            	}
            	else
            	{
                    // FND-25023 Selects the Order Decision for To Order in Alteration
                    toPlanDecision = compareToolDao.selectOrderDecisionsAltLog(toOrderId,
                                                                         	   DECISION,
                                                                         	   toAltId);
            	}
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            // Iterate through the from plan's Decision to process them
            ListIterator decisionIterator = fromPlanDecision.listIterator();
            while (decisionIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) decisionIterator.next();

                // Get this decision's data
                String decisionNodeNumber = (String) fromRow.get("NODE_NO");

                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Decisions for To Plan
                    toPlanDecision = compareToolDao.selectPlanDecisions(toPlanId,
                                                                        toPlanVersion,
                                                                        toPlanRevision,
                                                                        toPlanAlterations,
                                                                        DECISION,
                                                                        decisionNodeNumber);
                }
                else
                {
                	if(isEmpty(toAltId))
                	{
	                    // Selects the Order Decision for To Order
	                    toPlanDecision = compareToolDao.selectOrderDecisions(toOrderId,
	                                                                         DECISION,
	                                                                         decisionNodeNumber);
                	}
                	else
                	{
                        // FND-25023 Selects the Order Decision for To Order in Alteration
                        toPlanDecision = compareToolDao.selectOrderDecisionsAltLog(toOrderId,
                                                                             	   DECISION,
                                                                             	   decisionNodeNumber,
                                                                             	   toAltId);
                	}
                }

                if (toPlanDecision.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                    ListIterator toDecisionIterator = toPlanDecision.listIterator();
                    Map toRow = (Map) toDecisionIterator.next();

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }

            if (fromPlanDecision.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("NODE_NO", null);
                resultRow.put("DECISION_TYPE", null);
                resultRow.put("NODE_TITLE", null);
                resultRow.put("NODE_DESC", null);

                fromPlanDecision.add(resultRow);
            }
            return fromPlanDecision;
        }
        else
        {
            // Iterate through the to plan's Decision to process them
            ListIterator decisionIterator = toPlanDecision.listIterator();
            while (decisionIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) decisionIterator.next();

                // Get this decision's data
                String decisionNodeNumber = (String) toRow.get("NODE_NO");

                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Decision for From Plan
                    fromPlanDecision = compareToolDao.selectPlanDecisions(fromPlanId,
                                                                          fromPlanVersion,
                                                                          fromPlanRevision,
                                                                          fromPlanAlterations,
                                                                          DECISION,
                                                                          decisionNodeNumber);
                }
                else
                {
                	if(isEmpty(fromAltId))
                	{
	                    // Selects the Order Decision for From Order
	                    fromPlanDecision = compareToolDao.selectOrderDecisions(fromOrderId,
	                                                                           DECISION,
	                                                                           decisionNodeNumber);
                	}
                	else
                	{
                        // FND-25023 Selects the Order Decision for From Order in Alteration
                        fromPlanDecision = compareToolDao.selectOrderDecisionsAltLog(fromOrderId,
                                                                               		 DECISION,
                                                                               		 decisionNodeNumber,
                                                                               		 fromAltId);
                	}
                }

                if (fromPlanDecision.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                    ListIterator fromDecisionIterator = fromPlanDecision.listIterator();
                    Map fromRow = (Map) fromDecisionIterator.next();

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }

            if (toPlanDecision.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("NODE_NO", null);
                resultRow.put("DECISION_TYPE", null);
                resultRow.put("NODE_TITLE", null);
                resultRow.put("NODE_DESC", null);

                toPlanDecision.add(resultRow);
            }
            return toPlanDecision;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#compareReturn(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List compareReturn(String fromPlanId,
                              Number fromPlanVersion,
                              Number fromPlanRevision,
                              Number fromPlanAlterations,
                              String toPlanId,
                              Number toPlanVersion,
                              Number toPlanRevision,
                              Number toPlanAlterations,
                              String fromOrderId,
                              String toOrderId,
                              String parameters,
                              String calledFrom)
    {
        String fromType = EMPTY;
        String toType = EMPTY;

        // Sets the comparison type
        // FND-15289 Meena
        if (isEmpty(fromOrderId))
        {
            fromType = "PLAN";
        }
        else
        {
            fromType = "ORDER";
        }

        if (isEmpty(toOrderId))
        {
            toType = "PLAN";
        }
        else
        {
            toType = "ORDER";
        }

        // FND-25023
        // Parses the parameters an gets an array of parameters.
        String[] param = parse.parse(parameters,
                                     SEMI_COLON,
                                     false);

        // FND-25023
        String fromAltId = getParameterValue( param, "FROM_ALT_ID" );
        String toAltId = getParameterValue( param, "TO_ALT_ID" );
        
        // Declares the local variable.
        List fromPlanReturn = new ArrayList();
        List toPlanReturn = new ArrayList();

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            if (equalsIgnoreCase(fromType, "PLAN"))
            {
                // Selects the Returns for From Plan
                fromPlanReturn = compareToolDao.selectPlanReturns(fromPlanId,
                                                                  fromPlanVersion,
                                                                  fromPlanRevision,
                                                                  fromPlanAlterations,
                                                                  RETURN);
            }
            else
            {
            	if(isEmpty(fromAltId))
            	{
	                // Selects the Order Returns for From Order
	                fromPlanReturn = compareToolDao.selectOrderReturns(fromOrderId,
	                                                                   RETURN,
	                                                                   NumberUtils.INTEGER_MINUS_ONE);
            	}
            	else
            	{
                    // FND-25023 Selects the Order Returns for From Order in Alteration
                    fromPlanReturn = compareToolDao.selectOrderReturnsAltLog(fromOrderId,
                                                                       		 RETURN,
                                                                       		 NumberUtils.INTEGER_MINUS_ONE,
                                                                       		 fromAltId);
            	}
            }
        }
        else
        {
            if (equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Returns for To Plan
                toPlanReturn = compareToolDao.selectPlanReturns(toPlanId,
                                                                toPlanVersion,
                                                                toPlanRevision,
                                                                toPlanAlterations,
                                                                RETURN);
            }
            else
            {
            	if(isEmpty(toAltId))
            	{
	                // Selects the Order Returns for To Order
	                toPlanReturn = compareToolDao.selectOrderReturns(toOrderId,
	                                                                 RETURN,
	                                                                 NumberUtils.INTEGER_MINUS_ONE);
            	}
            	else
            	{
                    // FND-25023 Selects the Order Returns for To Order in Alteration
                    toPlanReturn = compareToolDao.selectOrderReturnsAltLog(toOrderId,
                                                                     	   RETURN,
                                                                     	   NumberUtils.INTEGER_MINUS_ONE,
                                                                     	   toAltId);
            	}
            }
        }

        if (equalsIgnoreCase(calledFrom, "FROM_PLAN"))
        {
            // Iterate through the from plan's Return to process them
            ListIterator returnIterator = fromPlanReturn.listIterator();
            while (returnIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map fromRow = (Map) returnIterator.next();

                // Get this return's data
                String returnNodeNumber = (String) fromRow.get("NODE_NO");

                if (equalsIgnoreCase(toType, "PLAN"))
                {
                    // Selects the Returns for To Plan
                    toPlanReturn = compareToolDao.selectPlanReturns(toPlanId,
                                                                    toPlanVersion,
                                                                    toPlanRevision,
                                                                    toPlanAlterations,
                                                                    RETURN,
                                                                    returnNodeNumber);
                }
                else
                {
                	if(isEmpty(toAltId))
                	{
	                    // Selects the Order Returns for To Order
	                    toPlanReturn = compareToolDao.selectOrderReturns(toOrderId,
	                                                                     RETURN,
	                                                                     NumberUtils.INTEGER_MINUS_ONE,
	                                                                     returnNodeNumber);
                	}
                	else
                	{
                        // FND-25023 Selects the Order Returns for To Order in Alteration
                        toPlanReturn = compareToolDao.selectOrderReturnsAltLog(toOrderId,
                                                                         	   RETURN,
                                                                         	   NumberUtils.INTEGER_MINUS_ONE,
                                                                         	   returnNodeNumber,
                                                                         	   toAltId);
                	}
                }

                if (toPlanReturn.isEmpty())
                {
                    changeIndicator = "MINUS_MARK";
                }
                else
                {
                    ListIterator toReturnIterator = toPlanReturn.listIterator();
                    Map toRow = (Map) toReturnIterator.next();

                    if (fromRow.equals(toRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                fromRow.put("CHANGE_INDICATOR", changeIndicator);
            }

            if (fromPlanReturn.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("NODE_NO", null);
                resultRow.put("OPER_NO", null);
                resultRow.put("OPER_TITLE", null);
                resultRow.put("SECURITY_GROUP", null);

                fromPlanReturn.add(resultRow);
            }
            return fromPlanReturn;
        }
        else
        {
            // Iterate through the to plan's Return to process them
            ListIterator returnIterator = toPlanReturn.listIterator();
            while (returnIterator.hasNext())
            {
                String changeIndicator = EMPTY;

                Map toRow = (Map) returnIterator.next();

                // Get this return's data
                String returnNodeNumber = (String) toRow.get("NODE_NO");

                if (equalsIgnoreCase(fromType, "PLAN"))
                {
                    // Selects the Return for From Plan
                    fromPlanReturn = compareToolDao.selectPlanReturns(fromPlanId,
                                                                      fromPlanVersion,
                                                                      fromPlanRevision,
                                                                      fromPlanAlterations,
                                                                      RETURN,
                                                                      returnNodeNumber);
                }
                else
                {
                	if(isEmpty(fromAltId))
                	{
	                    // Selects the Order Returns for From Order
	                    fromPlanReturn = compareToolDao.selectOrderReturns(fromOrderId,
	                                                                       RETURN,
	                                                                       NumberUtils.INTEGER_MINUS_ONE,
	                                                                       returnNodeNumber);
                	}
                	else
                	{
                        // FND-25023 Selects the Order Returns for From Order in Alteration
                        fromPlanReturn = compareToolDao.selectOrderReturnsAltLog(fromOrderId,
                                                                           		 RETURN,
                                                                           		 NumberUtils.INTEGER_MINUS_ONE,
                                                                           		 returnNodeNumber,
                                                                           		 fromAltId);
                	}
                }

                if (fromPlanReturn.isEmpty())
                {
                    changeIndicator = "PLUS_MARK";
                }
                else
                {
                    ListIterator fromReturnIterator = fromPlanReturn.listIterator();
                    Map fromRow = (Map) fromReturnIterator.next();

                    if (toRow.equals(fromRow))
                    {
                        changeIndicator = "CHECK_MARK";
                    }
                    else
                    {
                        changeIndicator = "PENCIL_MARK";
                    }
                }

                // Sets the change indicator
                toRow.put("CHANGE_INDICATOR", changeIndicator);

            }

            if (toPlanReturn.isEmpty())
            {
                // there is no record.
                Map resultRow = new ListOrderedMap();
                resultRow.put("CHANGE_INDICATOR", null);
                resultRow.put("NODE_NO", null);
                resultRow.put("OPER_NO", null);
                resultRow.put("OPER_TITLE", null);
                resultRow.put("SECURITY_GROUP", null);

                toPlanReturn.add(resultRow);
            }
            return toPlanReturn;
        }
    }

    /**
     * Compares the Operation Illustrations
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step UpdateNumber
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param objectType
     *            the Object Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation boolean
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @return returns true when both From and To Operation Illustration is same
     *         returns false when both From and To Operation Illustration is not
     *         same
     */
    private boolean compareOperationIllustrations(String fromPlanId,
                                                  Number fromOperationKey,
                                                  Number fromOperationUpdateNumber,
                                                  Number fromStepKey,
                                                  Number fromStepUpdateNumber,
                                                  String toPlanId,
                                                  Number toOperationKey,
                                                  Number toOperationUpdateNumber,
                                                  Number toStepKey,
                                                  Number toStepUpdateNumber,
                                                  String textType,
                                                  String objectType,
                                                  String tableName,
                                                  boolean isOperation,
                                                  String fromOrderId,
                                                  String toOrderId,
                                                  String fromType,
                                                  String toType)
    {
        boolean compare = false;

        List fromOperationIllustration = new ArrayList();
        List toOperationIllustration = new ArrayList();

        if (equalsIgnoreCase(fromType, "PLAN")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Illustrations for From Plan
            fromOperationIllustration = compareToolDao.selectOperationIllustrations(fromPlanId,
                                                                                    fromOperationKey,
                                                                                    fromOperationUpdateNumber,
                                                                                    textType,
                                                                                    objectType,
                                                                                    fromStepKey,
                                                                                    fromStepUpdateNumber,
                                                                                    tableName,
                                                                                    isOperation);

            // Selects the Operation Illustrations for To Plan
            toOperationIllustration = compareToolDao.selectOperationIllustrations(toPlanId,
                                                                                  toOperationKey,
                                                                                  toOperationUpdateNumber,
                                                                                  textType,
                                                                                  objectType,
                                                                                  toStepKey,
                                                                                  toStepUpdateNumber,
                                                                                  tableName,
                                                                                  isOperation);
        }
        else if (equalsIgnoreCase(fromType, "ORDER")
                && equalsIgnoreCase(fromType, toType))
        {
            // Selects the Operation Illustrations for From Order
            fromOperationIllustration = compareToolDao.selectOperationIllustrations(fromOrderId,
                                                                                    fromOperationKey,
                                                                                    fromStepKey,
                                                                                    textType,
                                                                                    objectType);

            // Selects the Operation Illustrations for To Order
            toOperationIllustration = compareToolDao.selectOperationIllustrations(toOrderId,
                                                                                  toOperationKey,
                                                                                  toStepKey,
                                                                                  textType,
                                                                                  objectType);
        }
        else
        {
            if (equalsIgnoreCase(fromType, "PLAN")
                    && equalsIgnoreCase(toType, "ORDER"))
            {
                // Selects the Operation Illustrations for From Plan
                fromOperationIllustration = compareToolDao.selectOperationIllustrations(fromPlanId,
                                                                                        fromOperationKey,
                                                                                        fromOperationUpdateNumber,
                                                                                        textType,
                                                                                        objectType,
                                                                                        fromStepKey,
                                                                                        fromStepUpdateNumber,
                                                                                        tableName,
                                                                                        isOperation);

                // Selects the Operation Illustrations for To Order
                toOperationIllustration = compareToolDao.selectOperationIllustrations(toOrderId,
                                                                                      toOperationKey,
                                                                                      toStepKey,
                                                                                      textType,
                                                                                      objectType);
            }
            else if (equalsIgnoreCase(fromType, "ORDER")
                    && equalsIgnoreCase(toType, "PLAN"))
            {
                // Selects the Operation Illustrations for From Order
                fromOperationIllustration = compareToolDao.selectOperationIllustrations(fromOrderId,
                                                                                        fromOperationKey,
                                                                                        fromStepKey,
                                                                                        textType,
                                                                                        objectType);

                // Selects the Operation Illustrations for To Plan
                toOperationIllustration = compareToolDao.selectOperationIllustrations(toPlanId,
                                                                                      toOperationKey,
                                                                                      toOperationUpdateNumber,
                                                                                      textType,
                                                                                      objectType,
                                                                                      toStepKey,
                                                                                      toStepUpdateNumber,
                                                                                      tableName,
                                                                                      isOperation);
            }
        }

        // Compare the Lists
        if (fromOperationIllustration != null
                && fromOperationIllustration.equals(toOperationIllustration))
        {
            compare = true;
        }

        return compare;
    }

    /**
     * Compares the Blocks
     * 
     * @param fromPlanId
     *            the From Plan Id
     * @param fromOperationKey
     *            the From Operation Key
     * @param fromOperationUpdateNumber
     *            the From Operation Update Number
     * @param fromStepKey
     *            the From Step Key
     * @param fromStepUpdateNumber
     *            the From Step Update Number
     * @param toPlanId
     *            the To Plan Id
     * @param toOperationKey
     *            the To Operation Key
     * @param toOperationUpdateNumber
     *            the To Operation Update Number
     * @param toStepKey
     *            the To Step Key
     * @param toStepUpdateNumber
     *            the To Step Update Number
     * @param textType
     *            the Text Type
     * @param tableName
     *            the Table Name
     * @param isOperation
     *            the Is Operation boolean
     * @param fromOrderId
     *            the From Order Id
     * @param toOrderId
     *            the To Order Id
     * @param fromType
     *            the From Type
     * @param toType
     *            the To Type
     * @param stepNumber TODO
     * @param fromAltId 
     *            the From Alteration Id
     * @param toAltId
     *            the To Alteration Id
     * @return returns true when both From and To Blocks are same returns false
     *         when both From and To Blocks are not same
     */
    private boolean compareBlock(String fromPlanId,
                                 Number fromOperationKey,
                                 Number fromOperationUpdateNumber,
                                 Number fromStepKey,
                                 Number fromStepUpdateNumber,
                                 String toPlanId,
                                 Number toOperationKey,
                                 Number toOperationUpdateNumber,
                                 Number toStepKey,
                                 Number toStepUpdateNumber,
                                 String textType,
                                 String tableName,
                                 boolean isOperation,
                                 String fromOrderId,
                                 String toOrderId,
                                 String fromType,
                                 String toType, 
                                 String stepNumber,  //FND-23086
                                 String fromAltId, // FND-25023
                                 String toAltId)
    {
        boolean compare = false;
        
        boolean compareOperationText = compareOperationText(fromPlanId,
                										    fromOperationKey,
                										    fromOperationUpdateNumber,
                										    fromStepKey,
                										    fromStepUpdateNumber,
                										    toPlanId,
                										    toOperationKey,
                										    toOperationUpdateNumber,
                										    toStepKey,
                										    toStepUpdateNumber,
                										    textType,
                										    "TEXT",
                										    tableName,
                										    isOperation,
                										    fromOrderId,
                										    toOrderId,
                										    fromType,
                										    toType, 
                										    stepNumber,
                										    fromAltId, // FND-25023
                										    toAltId); 

        // Compare the Standard Text
        boolean compareStandardText = compareOperationStandardText(fromPlanId,
                                                                   fromOperationKey,
                                                                   fromOperationUpdateNumber,
                                                                   fromStepKey,
                                                                   fromStepUpdateNumber,
                                                                   toPlanId,
                                                                   toOperationKey,
                                                                   toOperationUpdateNumber,
                                                                   toStepKey,
                                                                   toStepUpdateNumber,
                                                                   textType,
                                                                   "STDTEXT",
                                                                   tableName,
                                                                   isOperation,
                                                                   fromOrderId,
                                                                   toOrderId,
                                                                   fromType,
                                                                   toType,
                                                                   fromAltId, //FND-25023
                                                                   toAltId);

//        // Compare the Illustrations
//        boolean compareIllustration = compareOperationIllustrations(fromPlanId,
//                                                                    fromOperationKey,
//                                                                    fromOperationUpdateNumber,
//                                                                    fromStepKey,
//                                                                    fromStepUpdateNumber,
//                                                                    toPlanId,
//                                                                    toOperationKey,
//                                                                    toOperationUpdateNumber,
//                                                                    toStepKey,
//                                                                    toStepUpdateNumber,
//                                                                    textType,
//                                                                    "SLIDE",
//                                                                    tableName,
//                                                                    isOperation,
//                                                                    fromOrderId,
//                                                                    toOrderId,
//                                                                    fromType,
//                                                                    toType);
        
        // Compare the Illustrations
        boolean compareIllustration = compareOperationImageList(fromPlanId,
                                                                fromOperationKey,
                                                                fromOperationUpdateNumber,
                                                                fromStepKey,
                                                                fromStepUpdateNumber,
                                                                toPlanId,
                                                                toOperationKey,
                                                                toOperationUpdateNumber,
                                                                toStepKey,
                                                                toStepUpdateNumber,
                                                                textType,
                                                                tableName,
                                                                isOperation,
                                                                fromOrderId,
                                                                toOrderId,
                                                                fromType,
                                                                toType,
                                                                fromAltId, // FND-25023
                                                                toAltId);

        // Compare the Data Collections
        boolean compareDataCollection = compareOperationDataCollection(fromPlanId,
                                                                       fromOperationKey,
                                                                       fromOperationUpdateNumber,
                                                                       fromStepKey,
                                                                       fromStepUpdateNumber,
                                                                       toPlanId,
                                                                       toOperationKey,
                                                                       toOperationUpdateNumber,
                                                                       toStepKey,
                                                                       toStepUpdateNumber,
                                                                       textType,
                                                                       tableName,
                                                                       isOperation,
                                                                       fromOrderId,
                                                                       toOrderId,
                                                                       fromType,
                                                                       toType,
                                                                       fromAltId, // FND-25023
                                                                       toAltId);

        // GE-1353  Compare CDC
        boolean compareCDC = compareOperationCDC(fromPlanId,
                                                 fromOperationKey,
                                                 fromOperationUpdateNumber,
                                                 fromStepKey,
                                                 fromStepUpdateNumber,
                                                 toPlanId,
                                                 toOperationKey,
                                                 toOperationUpdateNumber,
                                                 toStepKey,
                                                 toStepUpdateNumber,
                                                 textType,
                                                 tableName,
                                                 isOperation,
                                                 fromOrderId,
                                                 toOrderId,
                                                 fromType,
                                                 toType,
                                                 fromAltId, // FND-25023
                                                 toAltId);
        
        // Compare the Tools
        boolean compareTools = compareOperationTools(fromPlanId,
                                                     fromOperationKey,
                                                     fromStepKey,
                                                     fromStepUpdateNumber,
                                                     fromOperationUpdateNumber,
                                                     toPlanId,
                                                     toOperationKey,
                                                     toStepKey,
                                                     toStepUpdateNumber,
                                                     toOperationUpdateNumber,
                                                     textType,
                                                     tableName,
                                                     isOperation,
                                                     fromOrderId,
                                                     toOrderId,
                                                     fromType,
                                                     toType,
                                                     fromAltId, // FND-25023
                                                     toAltId);

        // Compare Parts
        boolean compareParts = compareOperationParts(fromPlanId,
                                                     fromOperationKey,
                                                     fromStepKey,
                                                     fromStepUpdateNumber,
                                                     fromOperationUpdateNumber,
                                                     toPlanId,
                                                     toOperationKey,
                                                     toStepKey,
                                                     toStepUpdateNumber,
                                                     toOperationUpdateNumber,
                                                     textType,
                                                     tableName,
                                                     isOperation,
                                                     fromOrderId,
                                                     toOrderId,
                                                     fromType,
                                                     toType,
                                                     fromAltId, // FND-25023
                                                     toAltId);

        // Compare Buyoffs
        boolean compareBuyoffs = compareOperationBuyoffs(fromPlanId,
                                                         fromOperationKey,
                                                         fromStepKey,
                                                         fromStepUpdateNumber,
                                                         fromOperationUpdateNumber,
                                                         toPlanId,
                                                         toOperationKey,
                                                         toStepKey,
                                                         toStepUpdateNumber,
                                                         toOperationUpdateNumber,
                                                         textType,
                                                         tableName,
                                                         isOperation,
                                                         fromOrderId,
                                                         toOrderId,
                                                         fromType,
                                                         toType,
                                                         fromAltId, // FND-25023
                                                         toAltId);

        // Check if all the elements are same
        if (compareOperationText && compareStandardText && compareIllustration && compareDataCollection
                && compareCDC && compareTools && compareParts && compareBuyoffs)  // GE-1353
        {
            compare = true;
        }

        return compare;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfpl.application.ICompareTool#comparePlanOrderHeader(java.lang.String,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.Number)
     */
    public List comparePlanOrderHeader(String orderId,
                                       String planId,
                                       Number planVersion,
                                       Number planRevision,
                                       Number planAlterations,
                                       String calledFrom)
    {
        List returnList = new ArrayList();

        Map fromMap = new ListOrderedMap();
        Map toMap = new ListOrderedMap();
        
        String fromType = null;
        String toType = null;

        if (equalsIgnoreCase(calledFrom, "ORDER"))
        {
            fromType = "Order";
            toType = "Plan";
            
            // Selects the Order Header Data for Order
            fromMap = compareToolDao.selectOrderInformation(orderId);

            // Selects the Plan Header Data for Plan
            toMap = compareToolDao.selectPlanHeaderData(planId,
                                                        planVersion,
                                                        planRevision,
                                                        planAlterations);
        }
        else
        {
            fromType = "Plan";
            toType = "Order";
            
            // Selects the Plan Header Data for Plan
            fromMap = compareToolDao.selectPlanHeaderData(planId,
                                                          planVersion,
                                                          planRevision,
                                                          planAlterations);

            // Selects the Order Header Data for Order
            toMap = compareToolDao.selectOrderInformation(orderId);
        }

        String fromSecurityGroup = (String) fromMap.get("SECURITY_GROUP");
        String toSecurityGroup = (String) toMap.get("SECURITY_GROUP");
        
        boolean fromSecurityOk = SQLSecurityManager.getInstance().canAccess(fromSecurityGroup);
        boolean toSecurityOk = SQLSecurityManager.getInstance().canAccess(toSecurityGroup);
        
        if (!fromSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the from "+fromType+".");
        }
        else if (!toSecurityOk)
        {
            throw new UnauthorizedAccessException("User is not Authorized to view the to "+toType+".");
        }
        
        Map returnMap = new ListOrderedMap();

        returnMap.put("TITLE", "Item Number");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PART_NO"));
        returnMap.put("TO_ORDER", (String) toMap.get("PART_NO"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PART_NO"));
        returnMap.put("TO_PLAN", (String) toMap.get("PART_NO"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Item Rev");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PART_CHG"));
        returnMap.put("TO_ORDER", (String) toMap.get("PART_CHG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PART_CHG"));
        returnMap.put("TO_PLAN", (String) toMap.get("PART_CHG"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Version");
        if (fromMap.get("PLAN_VERSION") != null)
        {
            returnMap.put("FROM_ORDER", (String) fromMap.get("PLAN_VERSION")
                                                        .toString());
            returnMap.put("FROM_PLAN", (String) fromMap.get("PLAN_VERSION")
                    .toString());
        }
        else
        {
            returnMap.put("FROM_ORDER", EMPTY);
            returnMap.put("FROM_PLAN", EMPTY);
        }

        if (toMap.get("PLAN_VERSION") != null)
        {
            returnMap.put("TO_ORDER", (String) toMap.get("PLAN_VERSION")
                                                    .toString());
            returnMap.put("TO_PLAN", (String) toMap.get("PLAN_VERSION")
            		.toString());
        }
        else
        {
            returnMap.put("TO_ORDER", EMPTY);
            returnMap.put("TO_PLAN", EMPTY);
        }

        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Revision");

        if (fromMap.get("PLAN_REVISION") != null)
        {
            returnMap.put("FROM_ORDER", (String) fromMap.get("PLAN_REVISION")
                                                        .toString());
            returnMap.put("FROM_PLAN", (String) fromMap.get("PLAN_REVISION")
            		.toString());
        }
        else
        {
            returnMap.put("FROM_ORDER", EMPTY);
            returnMap.put("FROM_PLAN", EMPTY);
        }

        if (toMap.get("PLAN_REVISION") != null)
        {
            returnMap.put("TO_ORDER", (String) toMap.get("PLAN_REVISION")
                                                    .toString());
            returnMap.put("TO_PLAN", (String) toMap.get("PLAN_REVISION")
            		.toString());
        }
        else
        {
            returnMap.put("TO_ORDER", EMPTY);
            returnMap.put("TO_PLAN", EMPTY);
        }
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Alterations");

        if (fromMap.get("PLAN_ALTERATIONS") != null)
        {
            returnMap.put("FROM_ORDER",
                          (String) fromMap.get("PLAN_ALTERATIONS").toString());
            returnMap.put("FROM_PLAN",
            		(String) fromMap.get("PLAN_ALTERATIONS").toString());
        }
        else
        {
            returnMap.put("FROM_ORDER", EMPTY);
            returnMap.put("FROM_PLAN", EMPTY);
        }

        if (toMap.get("PLAN_ALTERATIONS") != null)
        {
            returnMap.put("TO_ORDER", (String) toMap.get("PLAN_ALTERATIONS")
                                                    .toString());
            returnMap.put("TO_PLAN", (String) toMap.get("PLAN_ALTERATIONS")
            		.toString());
        }
        else
        {
            returnMap.put("TO_ORDER", EMPTY);
            returnMap.put("TO_PLAN", EMPTY);
        }
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Type");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PLAN_TYPE"));
        returnMap.put("TO_ORDER", (String) toMap.get("PLAN_TYPE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PLAN_TYPE"));
        returnMap.put("TO_PLAN", (String) toMap.get("PLAN_TYPE"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Title");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PLAN_TITLE"));
        returnMap.put("TO_ORDER", (String) toMap.get("PLAN_TITLE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PLAN_TITLE"));
        returnMap.put("TO_PLAN", (String) toMap.get("PLAN_TITLE"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Plan Update Number");
        if (fromMap.get("PLAN_UPDT_NO") != null)
        {
            returnMap.put("FROM_ORDER", (String) fromMap.get("PLAN_UPDT_NO")
                                                        .toString());
            returnMap.put("FROM_PLAN", (String) fromMap.get("PLAN_UPDT_NO")
            		.toString());
        }
        else
        {
            returnMap.put("FROM_ORDER", EMPTY);
            returnMap.put("FROM_PLAN", EMPTY);
        }

        if (toMap.get("PLAN_UPDT_NO") != null)
        {
            returnMap.put("TO_ORDER", (String) toMap.get("PLAN_UPDT_NO")
                                                    .toString());
            returnMap.put("TO_PLAN", (String) toMap.get("PLAN_UPDT_NO")
            		.toString());
        }
        else
        {
            returnMap.put("TO_ORDER", EMPTY);
            returnMap.put("TO_PLAN", EMPTY);
        }
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Item Type");
        returnMap.put("FROM_ORDER", (String) fromMap.get("ITEM_TYPE"));
        returnMap.put("TO_ORDER", (String) toMap.get("ITEM_TYPE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("ITEM_TYPE"));
        returnMap.put("TO_PLAN", (String) toMap.get("ITEM_TYPE"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Item Subtype");
        returnMap.put("FROM_ORDER", (String) fromMap.get("ITEM_SUBTYPE"));
        returnMap.put("TO_ORDER", (String) toMap.get("ITEM_SUBTYPE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("ITEM_SUBTYPE"));
        returnMap.put("TO_PLAN", (String) toMap.get("ITEM_SUBTYPE"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Program");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PROGRAM"));
        returnMap.put("TO_ORDER", (String) toMap.get("PROGRAM"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PROGRAM"));
        returnMap.put("TO_PLAN", (String) toMap.get("PROGRAM"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Project");
        returnMap.put("FROM_ORDER", (String) fromMap.get("PROJECT"));
        returnMap.put("TO_ORDER", (String) toMap.get("PROJECT"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("PROJECT"));
        returnMap.put("TO_PLAN", (String) toMap.get("PROJECT"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Serial Flag");
        returnMap.put("FROM_ORDER", (String) fromMap.get("SERIAL_FLAG"));
        returnMap.put("TO_ORDER", (String) toMap.get("SERIAL_FLAG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("SERIAL_FLAG"));
        returnMap.put("TO_PLAN", (String) toMap.get("SERIAL_FLAG"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Lot Flag");
        returnMap.put("FROM_ORDER", (String) fromMap.get("LOT_FLAG"));
        returnMap.put("TO_ORDER", (String) toMap.get("LOT_FLAG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("LOT_FLAG"));
        returnMap.put("TO_PLAN", (String) toMap.get("LOT_FLAG"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Unit Type");
        returnMap.put("FROM_ORDER", (String) fromMap.get("UNIT_TYPE"));
        returnMap.put("TO_ORDER", (String) toMap.get("UNIT_TYPE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("UNIT_TYPE"));
        returnMap.put("TO_PLAN", (String) toMap.get("UNIT_TYPE"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Engineering Part No");
        returnMap.put("FROM_ORDER", (String) fromMap.get("ENG_PART_NO"));
        returnMap.put("TO_ORDER", (String) toMap.get("ENG_PART_NO"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("ENG_PART_NO"));
        returnMap.put("TO_PLAN", (String) toMap.get("ENG_PART_NO"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "MBOM Rev");
        returnMap.put("FROM_ORDER", (String) fromMap.get("MFG_BOM_CHG"));
        returnMap.put("TO_ORDER", (String) toMap.get("MFG_BOM_CHG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("MFG_BOM_CHG"));
        returnMap.put("TO_PLAN", (String) toMap.get("MFG_BOM_CHG"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Order UOM");
        returnMap.put("FROM_ORDER", (String) fromMap.get("ORDER_UOM"));
        returnMap.put("TO_ORDER", (String) toMap.get("ORDER_UOM"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("ORDER_UOM"));
        returnMap.put("TO_PLAN", (String) toMap.get("ORDER_UOM"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Model");
        returnMap.put("FROM_ORDER", (String) fromMap.get("MODEL"));
        returnMap.put("TO_ORDER", (String) toMap.get("MODEL"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("MODEL"));
        returnMap.put("TO_PLAN", (String) toMap.get("MODEL"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Final Stores");
        returnMap.put("FROM_ORDER", (String) fromMap.get("FINAL_STORES"));
        returnMap.put("TO_ORDER", (String) toMap.get("FINAL_STORES"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("FINAL_STORES"));
        returnMap.put("TO_PLAN", (String) toMap.get("FINAL_STORES"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Initial Stores");
        returnMap.put("FROM_ORDER", (String) fromMap.get("INITIAL_STORES"));
        returnMap.put("TO_ORDER", (String) toMap.get("INITIAL_STORES"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("INITIAL_STORES"));
        returnMap.put("TO_PLAN", (String) toMap.get("INITIAL_STORES"));
        returnList.add(returnMap);

        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Needs Review Flag");
        returnMap.put("FROM_ORDER", (String) fromMap.get("NEEDS_REVIEW_FLAG"));
        returnMap.put("TO_ORDER", (String) toMap.get("NEEDS_REVIEW_FLAG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("NEEDS_REVIEW_FLAG"));
        returnMap.put("TO_PLAN", (String) toMap.get("NEEDS_REVIEW_FLAG"));
        returnList.add(returnMap);
        
        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Display Sequence");
        returnMap.put("FROM_ORDER", (String) fromMap.get("DISPLAY_SEQUENCE"));
        returnMap.put("TO_ORDER", (String) toMap.get("DISPLAY_SEQUENCE"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("DISPLAY_SEQUENCE"));
        returnMap.put("TO_PLAN", (String) toMap.get("DISPLAY_SEQUENCE"));
        returnList.add(returnMap);
        
        //FND-22135
        returnMap = new ListOrderedMap();
        returnMap.put("TITLE", "Operation Overlap?");
        returnMap.put("FROM_ORDER", (String) fromMap.get("OPERATION_OVERLAP_FLAG"));
        returnMap.put("TO_ORDER", (String) toMap.get("OPERATION_OVERLAP_FLAG"));
        returnMap.put("FROM_PLAN", (String) fromMap.get("OPERATION_OVERLAP_FLAG"));
        returnMap.put("TO_PLAN", (String) toMap.get("OPERATION_OVERLAP_FLAG"));
        returnList.add(returnMap);

        Iterator itr = returnList.iterator();
        while(itr.hasNext())
        {
            Map map = (Map) itr.next();
            map.put("SECURITY_GROUP", null);
        }
        
        returnList.add(fromMap);
        
        return returnList;
    }
    
    /**
     * @since 5000
     * @param fromPlanId
     * @param fromOperationKey
     * @param fromOperationUpdateNumber
     * @param fromStepKey
     * @param fromStepUpdateNumber
     * @param toPlanId
     * @param toOperationKey
     * @param toOperationUpdateNumber
     * @param toStepKey
     * @param toStepUpdateNumber
     * @param isOperation
     * @param fromOrderId
     * @param toOrderId
     * @param fromType
     * @param toType
     * @return
     */
    private boolean compareOperationDesc(String fromPlanId,
								         Number fromOperationKey,
								         Number fromOperationUpdateNumber,
								         Number fromStepKey,
								         Number fromStepUpdateNumber,
								         String toPlanId,
								         Number toOperationKey,
								         Number toOperationUpdateNumber,
								         Number toStepKey,
								         Number toStepUpdateNumber,
								         boolean isOperation,
								         String fromOrderId,
								         String toOrderId,
								         String fromType,
								         String toType)
	{
	boolean compare = false;
	List fromOperationDesc = new ArrayList();
	List toOperationDesc = new ArrayList();
	
	String tableName = "SFPL_STEP_DESC";
   
	if (isOperation)
    {
       tableName = "SFPL_OPERATION_DESC";
    }
	if (equalsIgnoreCase(fromType, "PLAN")
	&& equalsIgnoreCase(fromType, toType))
	{
		
	fromOperationDesc = compareToolDao.selectOperationDesc(fromPlanId,
					                                       fromOperationKey,
					                                       fromOperationUpdateNumber,
					                                       fromStepKey,
					                                       fromStepUpdateNumber,
					                                       tableName,
					                                       isOperation);
	
	toOperationDesc = compareToolDao.selectOperationDesc(toPlanId,
														 toOperationKey,
														 toOperationUpdateNumber,
														 toStepKey,
														 toStepUpdateNumber,
														 tableName,
														 isOperation);
	}
	else if (equalsIgnoreCase(fromType, "ORDER")
	&& equalsIgnoreCase(fromType, toType))
	{
		
	fromOperationDesc = compareToolDao.selectOperationDesc(fromOrderId,
														   fromOperationKey,
														   fromStepKey);
	
	toOperationDesc = compareToolDao.selectOperationDesc(toOrderId,
				                                         toOperationKey,
				                                         toStepKey);
	}
	else
	{
	if (equalsIgnoreCase(fromType, "PLAN")
	&& equalsIgnoreCase(toType, "ORDER"))
	{
		
	fromOperationDesc = compareToolDao.selectOperationDesc(fromPlanId,
				                                           fromOperationKey,
				                                           fromOperationUpdateNumber,
				                                           fromStepKey,
				                                           fromStepUpdateNumber,
				                                           tableName,
				                                           isOperation);
		
	//GE-516
	toOperationDesc = compareToolDao.selectStepDesc(toOrderId,
			                                        toOperationKey,
			                                        toStepKey, 
			                                        isOperation);
	}
	else if (equalsIgnoreCase(fromType, "ORDER")
	&& equalsIgnoreCase(toType, "PLAN"))
	{
	//GE-516
	fromOperationDesc = compareToolDao.selectStepDesc(fromOrderId,
												      fromOperationKey,
											          fromStepKey, 
											          isOperation);
	
	toOperationDesc = compareToolDao.selectOperationDesc(toPlanId,
				                                         toOperationKey,
				                                         toOperationUpdateNumber,
				                                         toStepKey,
				                                         toStepUpdateNumber,
				                                         tableName,
				                                         isOperation);
	}
	}
	
	if (equalsIgnoreCase(fromOperationDesc.toString(), toOperationDesc.toString()))
	{
	compare = true;
	}
	
	return compare;
	}
    
    // Compare the List
    private boolean compareList( List fromList,
    		                     List toList )
    {
    	boolean compare = true;
    	
    	if( fromList != null && toList != null && fromList.size() == toList.size())
    	{
    		Iterator fromIterator = fromList.listIterator();
    		Iterator toIterator = toList.listIterator();
    		
    		while (fromIterator.hasNext() && compare)
    		{
    			Map fromMap = (Map) fromIterator.next();
    			Map toMap = (Map) toIterator.next();
    			
    			fromMap.remove("SLIDE_ID");
    			toMap.remove("SLIDE_ID");

    			fromMap.remove("BUYOFF_ID");
    			toMap.remove("BUYOFF_ID");

                fromMap.remove("TOOL_ID");
                toMap.remove("TOOL_ID");
                
                fromMap.remove("OBJECT_TAG");
    			toMap.remove("OBJECT_TAG");
    			
    			fromMap.remove("OBJECT_REV");
    			toMap.remove("OBJECT_REV");
    			
    			fromMap.remove("OBJECT_DESC");
    			toMap.remove("OBJECT_DESC");

    			fromMap.remove("THUMBNAIL");
    			toMap.remove("THUMBNAIL");
    			
    			compare = fromMap.equals(toMap);
    		}
    	}
    	else
    	{
    		compare = false;
    	}
    	
    	
    	return compare;
    }

    /**
     * Sets the ICompareToolDao
     * 
     * @param compareToolDao
     *            the ICompareToolDao to set
     */
    public void setCompareToolDao(ICompareToolDao compareToolDao)
    {
        this.compareToolDao = compareToolDao;
    }

    /**
     * Sets the IUidEntry
     * 
     * @param uidEntry
     *            the IUidEntry to set
     */
    public void setUidEntry(IUidEntry uidEntry)
    {
        this.uidEntry = uidEntry;
    }

    /**
     * Sets the IParse
     * 
     * @param parse
     *            the IParse
     */
    public void setParse(IParse parse)
    {
        this.parse = parse;
    }
    
    /**
     * Sets the IMessage
     * 
     * @param message the IMessage to set
     */
    public void setMessage(IMessage message)
    {
    	this.message = message;
    }


    public void setOperationDao ( IOperationDao operationDao )
    {
        this.operationDao = operationDao;
    }

    public void setStandardOperationDao ( IStandardOperationDao standardOperationDao )
    {
        this.standardOperationDao = standardOperationDao;
    }
    
    public void setComaareToolHelper(ICompareToolHelper comaareToolHelper) 
    {
		this.compareToolHelper = comaareToolHelper;
	}
    
}
