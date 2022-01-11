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
 *  File:    PlanEffectivityImplPW.java
 * 
 *  Created: 2018-02-06
 * 
 *  Author:  c079222
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2018-02-06 	c079222		    Initial Release SMRO_PLG_209
 * 2018-06-27   R. Thorpe       SMRO_PLG_209  Defect 868  replaced priv check from ORDER CREATE to PLAN_PLG_AUTH
 * 2018-07-02   R. Thorpe       SMRO_PLG_209  Defect 868  part 2 - copied in private methods from OOB to change priv check 
 *                                            from ORDER CREATE to PLAN_PLG_AUTH on Plan Effectivity Insert
 */
package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.FrameworkConstants.DATE_MASK;
import static com.ibaset.common.FrameworkConstants.DATE_MASK_NAME;
import static com.ibaset.common.SoluminaConstants.*;
import static org.apache.commons.lang.StringUtils.*;
import static com.ibaset.common.util.SoluminaUtils.*;
import static org.apache.commons.lang.math.NumberUtils.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IDateUtils;
import com.ibaset.solumina.sffnd.application.IParameters;
import com.ibaset.solumina.sffnd.dao.IPlanObjectDao;
import com.ibaset.solumina.sfpl.application.IBom;
import com.ibaset.solumina.sfpl.application.IPlanEffectivity;
import com.ibaset.solumina.sfpl.dao.IPlanEffectivityDao;
import com.ibaset.solumina.sffnd.application.IEvent;

import com.pw.solumina.sfpl.application.IPlanEffectivityPW;

public abstract class PlanEffectvityImplPW extends ImplementationOf<IPlanEffectivity> implements IPlanEffectivity,IPlanEffectivityPW {

	@Reference 
    private IBom bom = null;
	
	@Reference
    private IDateUtils dateUtils = null;
	
	@Reference
	private IMessage message = null;
	
	@Reference
    private IParameters parameters = null;

	@Reference
	private IPlanEffectivityDao planEffectivityDao = null;

	@Reference
	private IValidator validator = null;

	@Reference
	private IPlanObjectDao planObjectDao = null;
	
	@Reference
	private IPlanEffectivityPW planEffectivityPW = null;
	
	@Reference
	private IEvent event = null;
	
	public void deletePlanEffectivities(String planId,
			Number planVersion,
			Number planRevision,
			Number planAlterations,
			String effType,
			String effFrom)
	{
		Date effectiveFromDate = null;
		String serverEffectiveFrom = null;
		//FND-22640
		List effectivityChangePlanList = new ArrayList();
		Map effMap = null;
		ContextUtil.getUser().getContext().set("PlanKeyInfoList", effectivityChangePlanList);

		//validator.checkUserPrivilege(ORDER_CREATE_WITH_UNDERSCORE);
		validator.checkUserPrivilege("PLAN_PLG_AUTHORING");  //Defect 868

		try
		{
			Map planInfoMap = planObjectDao.selectPlan(planId,
					planVersion,
					planRevision,
					planAlterations);

			// SMRO_PLG_209 
			if (stringEquals(planInfoMap.get("INSTRUCTIONS_TYPE").toString(), MRO_UPGRADE))
/*			if (stringEquals(planInfoMap.get("INSTRUCTIONS_TYPE").toString(), MRO_PART)
					|| stringEquals(planInfoMap.get("INSTRUCTIONS_TYPE").toString(), MRO_UPGRADE))*/
			{
				message.raiseError("MFI_79211AF0EC4D2DA1E04400144FA7B7D2");
			}
		}
		catch (EmptyResultDataAccessException erdae)
		{

		}

		if (stringEquals(effType, DATE))
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_MASK);

			try
			{
				effectiveFromDate = parseDate(effFrom);
			}
			catch (ParseException pe)
			{
				message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
			}

			serverEffectiveFrom = dateFormatter.format(dateUtils.tzShiftToServer((effectiveFromDate)));
		}

		else
		{
			serverEffectiveFrom = effFrom;
		}

		planEffectivityDao.deleteEffectivity(planId,
				planVersion,
				planRevision,
				planAlterations,
				effType,
				serverEffectiveFrom);
		addPlanToEffectivityChangeList(planId,
				planVersion,
				planRevision,
				planAlterations,
				effectivityChangePlanList);
		planEffectivityPW.generateRoutingOutboundXml();
		
		// FND-25775
		bom.resetBomLineCompsForNewEffectivity(planId, 
				planVersion,
				planRevision,
				planAlterations); 
	}
    
	protected void validatePlanEffectivityPre(String planId,
			Number planVersion,
			Number planRevision,
			Number planAlterations,
			String effectivityType)
	{
		String unitType = null;
		String instructionsType = null;
	//	validator.checkUserPrivilege(ORDER_CREATE_WITH_UNDERSCORE)
		validator.checkUserPrivilege("PLAN_PLG_AUTHORING");  //Defect 868
		
		// Effectivity Type is required input
		validator.checkMandatoryField("effectivity type", effectivityType);

		// Validate plan version and plan revision
		if (!planEffectivityDao.doesPlanExist(planId,
				planVersion,
				planRevision,
				planAlterations))
		{
			// Plan version or revision not found
			message.raiseError("MFI_51623"); 
		}

		if (!planEffectivityDao.hasExistingEffectivity(planId, effectivityType))
		{
			// Too many effectivity types for this plan
			message.raiseError("MFI_51561");
		}

		try
		{
			Map planInfoMap = planObjectDao.selectPlan(planId,
					planVersion,
					planRevision,
					planAlterations);

			instructionsType = (String)planInfoMap.get("INSTRUCTIONS_TYPE");
			// SMRO_PLG_209
/*			if (stringEquals(instructionsType,MRO_PART)
					|| stringEquals(instructionsType,MRO_UPGRADE))*/
			if (stringEquals(instructionsType,MRO_UPGRADE))
			{
				message.raiseError("MFI_79211AF0EC4D2DA1E04400144FA7B7D2");
			}
			unitType = (String)planInfoMap.get("UNIT_TYPE");
		}
		catch (EmptyResultDataAccessException erdae)
		{

		}
		if (!SERIAL.equals(effectivityType)
				&& !LOT.equals(effectivityType)
				&& !DATE.equals(effectivityType))
		{
			if (isNotEmpty(unitType)
					&& !stringEquals(unitType, effectivityType))
			{
				// Effectivity Type must match End Unit Type
				message.raiseError("MFI_54733");  
			}
		}

		if (stringEquals(effectivityType, SERIAL))
		{
			List planInfo = planEffectivityDao.selectPlanSerialFlagInformation( planId,
					planVersion,
					planRevision,
					planAlterations);
			ListIterator planInfoIt = planInfo.listIterator();

			if (planInfoIt.hasNext())
			{
				// Cannot create SERIAL effectivity. Plan ver: %V1 rev: %V2 is not serial control
				message.raiseError("MFI_1068",planVersion.toString(),planRevision.toString());
			}            
		}
		else if (stringEquals(effectivityType, LOT))
		{
			List planInfo = planEffectivityDao.selectPlanLotFlagInformation(planId,
					planVersion,
					planRevision,
					planAlterations);
			ListIterator planInfoIt = planInfo.listIterator();
			if (planInfoIt.hasNext())
			{
				// Cannot create LOT effectivity. Plan ver: %V1 rev: %V2 is not lot control
				message.raiseError("MFI_1069",planVersion.toString(),planRevision.toString());
			}
		}
	}

	private void addPlanToEffectivityChangeList(String planId,
			Number planVersion,
			Number planRevision,
			Number planAlterations,
			List effectivityChangePlanList)
	{
		Map effMap;
		effMap = planEffectivityPW.preparePlanKeyMap(planId, planVersion, planRevision, planAlterations);
		if(!effectivityChangePlanList.contains(effMap))
		{
			effectivityChangePlanList.add(effMap);
		}
	}
	// Begin Defect 868 part 2
	public void typeCheckInsert(String planId,
			Number planVersion,
			Number planRevision,
			Number planAlterations,
			String effectivityType,
			String effectivityFrom,
			String effectivityThru,
			String effectivityFromDateString,
			String effectivityThruDateString,
			String notes)
	{
		String localEffectivityFrom = null;
		String localEffectivityThru = null;
		Number localPlanVersion = null;
		Number localPlanRevision = null;
		Date effectivityFromDate = null;
		String vpEffectivityFrom = null;
		String vpEffectivityThru = null;
		Date effectivityThruDate = null;
		Date inputEffectivityFromDate = null;
		Date inputEffectivityThruDate = null;
		Number numberEffectivityFrom = new Integer(0);
		Number numberEffectivityThru = new Integer(0);
		Number inputEffectivityFrom = new Integer(0);
		Number inputEffectivityThru = new Integer(0);
		String memo = NO;
		String messageText = "";
		Number maxLimit = new Integer(Integer.MAX_VALUE);
		String isDistinctEffectivityFrom = null;
		String isDistinctEffectivityThru = null;
		String udvCaption = null;
		boolean isEffectivityThruString = false;
		boolean continueResetLineComps = true;
		List effectivityChangePlanList = new ArrayList();
		Map effMap = null;
		ContextUtil.getUser().getContext().set("PlanKeyInfoList", effectivityChangePlanList);

		//validator.checkUserPrivilege(ORDER_CREATE_WITH_UNDERSCORE);
		validator.checkUserPrivilege("PLAN_PLG_AUTHORING");  //Defect 868
		

		validator.checkMandatoryField("effectivityType", effectivityType);

		if (!stringEquals(effectivityType, DATE))
		{
			validator.checkMandatoryField("effectivityFrom", effectivityFrom);

			inputEffectivityFrom = new Integer(toInt(effectivityFrom));

			if (!isEmpty(effectivityThru))
			{
				if(SoluminaUtils.isNumber(effectivityThru) && SoluminaUtils.isNumber(effectivityFrom))
				{
					inputEffectivityThru = new Integer(toInt(effectivityThru));

					if (inputEffectivityFrom.intValue() > inputEffectivityThru.intValue())
					{
						message.raiseError("MFI_51621",
								inputEffectivityFrom.toString(),
								inputEffectivityThru.toString()); // Message
						// Text: From effectivity: %V1 is greater than through
						// effectivity: %V2
					}
				}
				else
				{
					isEffectivityThruString = true;
				}

			}
			else
			{
				if(SoluminaUtils.isNumber(effectivityFrom))
					inputEffectivityThru = maxLimit;
				else
					isEffectivityThruString = true;
			}

		}
		else
		{
			validator.checkIsEmpty(effectivityFromDateString, "MFI_53568"); // Message
			// Text: Must provide all key fields
		}

		if (!stringEquals(effectivityType, DATE)
				&& (isEffectivityThruString || !SoluminaUtils.isNumber(effectivityFrom)))
		{
			List list = planEffectivityDao.selectDistinctEffectivity(planId);
			ListIterator listIt = list.listIterator();
			if (list.size() > 1)
			{
				message.raiseError("MFI_50187");
			}

			if (listIt.hasNext())
			{
				Map row = (Map) listIt.next();
				isDistinctEffectivityFrom = (String) row.get("EFF_FROM");
				isDistinctEffectivityThru = (String) row.get("EFF_THRU");
			}

			if (stringEquals(isDistinctEffectivityFrom,
					YES)
					&& stringEquals(isDistinctEffectivityThru,
							YES))
			{
				message.raiseError("MFI_50187"); // Message Text:
				// Cannot use both numeric and alpha-numeric effectivity ranges
				// within the same plan
			}

			if (!SoluminaUtils.isNumber(effectivityFrom)
					&& !SoluminaUtils.isNumber(effectivityThru))
			{
				String tempEffectivityFrom = defaultIfEmpty(effectivityFrom, x);
				String tempEffectivityThru = defaultIfEmpty(effectivityThru, x);

				tempEffectivityFrom = leftPad(tempEffectivityFrom, 40, BLANK);
				if(effectivityThru != null)
					tempEffectivityThru = leftPad(tempEffectivityThru, 40, BLANK);

				if (tempEffectivityFrom.compareTo(tempEffectivityThru) > 0)
				{
					message.raiseError("MFI_51621",
							effectivityFrom,
							effectivityThru); // Message Text:
					// From effectivity: %V1 is greater than through
					// effectivity: %V2
				}
			}

			updateUnit(planId,
					planVersion,
					planRevision,
					planAlterations,
					effectivityType,
					effectivityFrom,
					effectivityThru,
					notes);

			addPlanToEffectivityChangeList(planId,
					planVersion,
					planRevision,
					planAlterations,
					effectivityChangePlanList);

			generateRoutingOutboundXml();
		}
		else
		{
			// Check DATE range or UNIT , SHIP , SERIAL , etc with numeric
			// range.
			List planInfo = planEffectivityDao.selectPlanInfo(planId,
					effectivityType);
			ListIterator planInfoIt = planInfo.listIterator();
			while (planInfoIt.hasNext())
			{
				Map row = (Map) planInfoIt.next();
				localPlanVersion = (Number) row.get("PLAN_VERSION");
				localPlanRevision = (Number) row.get("PLAN_REVISION");
				localEffectivityFrom = (String) row.get("EFF_FROM");
				localEffectivityThru = (String) row.get("EFF_THRU");
				effectivityFromDate = (Date) row.get("EFF_FROM_DATE");
				effectivityThruDate = (Date) row.get("EFF_THRU_DATE");

				if (!stringEquals(effectivityType, DATE))
				{
					numberEffectivityFrom = new Integer(toInt(localEffectivityFrom));

					if (localEffectivityThru != null)
					{
						numberEffectivityThru = new Integer(toInt(localEffectivityThru));
					}
					else
					{
						numberEffectivityThru = maxLimit;
					}

					if (!SoluminaUtils.isNumber(localEffectivityFrom)
							&& SoluminaUtils.isNumber(effectivityFrom))
					{
						message.raiseError("MFI_50187"); // Message
						// Text: Cannot use both numeric and alpha-numeric
						// effectivity ranges within the same plan
					}
					if (isNotEmpty(localEffectivityThru)
							&& !SoluminaUtils.isNumber(localEffectivityThru)
							&& SoluminaUtils.isNumber(effectivityThru))
					{
						// Text: Cannot use both numeric and alpha-numeric
						// effectivity ranges within the same plan
						message.raiseError("MFI_50187"); // Message
					}

					boolean isBetween = selectBetween(inputEffectivityFrom,
							numberEffectivityFrom,
							numberEffectivityThru,
							inputEffectivityThru);

					if (isBetween)
					{
						// this is for non dates
						messageText = buildNonDateMessage(messageText,
								localPlanVersion.toString(),
								localPlanRevision.toString(),
								effectivityType,
								inputEffectivityFrom,
								inputEffectivityThru,
								numberEffectivityFrom,
								numberEffectivityThru);

						memo = YES;

						if (contains(messageText, "VALUE_ERROR"))
						{
							message.raiseError("MFI_50187");
						}
					}
				}

				else
				{
					if (effectivityThruDate == null)
					{
						effectivityThruDate = MAXIMUM_DATE;
					}

					if (stringEquals(dateUtils.validateDate(effectivityFromDateString),NO))
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME)); // Message
					}
					if (isEmpty(effectivityThruDateString)
							|| effectivityThruDateString.indexOf("/  /") > -1)
					{
						inputEffectivityThruDate = MAXIMUM_DATE;
					}
					else if (stringEquals(dateUtils.validateDate(effectivityThruDateString),NO))
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME)); // Message
					}
					else
					{
						try
						{
							inputEffectivityThruDate = parseDate(effectivityThruDateString);
						}
						catch (ParseException e)
						{
							message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
						} 
					}

					try
					{
						inputEffectivityFromDate = parseDate(effectivityFromDateString);
					}
					catch (ParseException e)
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
					}

					if(!isEmpty(effectivityThruDateString))
					{
						try
						{
							Date d1 = parseDate(effectivityFromDateString);
							Date d2 = parseDate(effectivityThruDateString);
							if (d1.compareTo(d2) > 0)
							{
								message.raiseError("MFI_51621",
										new SimpleDateFormat(parameters.getClientSessionParameter(DATE_MASK_NAME)).format(d1),
										new SimpleDateFormat(parameters.getClientSessionParameter(DATE_MASK_NAME)).format(d2)); // Message
								// Text: From effectivity: %V1 is greater than through
								// effectivity: %V2
							}
						}
						catch(ParseException e)
						{
							message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
						}
					}

					// use selectbetween for this one too
					boolean isBetween = selectBetweenDate(inputEffectivityFromDate,
							effectivityFromDate,
							effectivityThruDate,
							inputEffectivityThruDate);

					if (isBetween)
					{
						messageText = buildMessage(messageText,
								localPlanVersion.toString(),
								localPlanRevision.toString(),
								effectivityType,
								inputEffectivityFromDate,
								inputEffectivityThruDate,
								effectivityFromDate,
								effectivityThruDate);
						memo = YES;
					}
				}
			}

			if (stringEquals(memo, YES))
			{
				if (isNotEmpty(effectivityThruDateString))
				{
					if (effectivityThruDateString.indexOf("/  /") > -1)
					{
						localEffectivityThru = null;
					}
					else
					{
						localEffectivityThru = effectivityThruDateString;
					}
				}

				if (stringEquals(effectivityType, DATE))
				{
					try
					{
						SimpleDateFormat clientFormat = new SimpleDateFormat(parameters.getClientSessionParameter(DATE_TIME_MASK_NAME));

						vpEffectivityFrom = clientFormat.format(dateUtils.tzShiftToClient(parseDate(effectivityFromDateString)));

						if (!isEmpty(effectivityThruDateString))
						{
							vpEffectivityThru = clientFormat.format(dateUtils.tzShiftToClient(parseDate(effectivityThruDateString)));
						}
						else
						{
							vpEffectivityThru = "";
						}

					}
					catch (ParseException e)
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
					}

					// Corrected the Caption for Date Effectivity
					udvCaption = "Plan Effectivity Date Input";
				}
				else
				{
					vpEffectivityFrom = effectivityFrom;
					vpEffectivityThru = defaultIfEmpty(effectivityThru,
							"");

					// Corrected the Caption for Serial, Lot Effectivity
					udvCaption = "Plan Effectivity Unit Input";
				}

				if (isEmpty(notes))
				{
					notes = "";
				}
				setShowUdvFlag();

				event.showUdv("MFI_1007443",
						upperCase(INSERT),
						udvCaption,
						null,
						"plan_id=" + planId + ",plan_version="
								+ planVersion + ",plan_revision="
								+ planRevision + ",plan_alterations="
								+ planAlterations + ",eff_type="
								+ effectivityType + ",eff_from="
								+ vpEffectivityFrom + ",eff_thru="
								+ vpEffectivityThru + ",notes=" + notes +
								",messages=" + messageText);

				continueResetLineComps = false;
			}
			else if (!stringEquals(effectivityType,
					DATE))
			{
				boolean effectivityExists = planEffectivityDao.doesEffectivityExist(planId,
						planVersion,
						planRevision,
						planAlterations,
						effectivityType,
						effectivityFrom);

				if (effectivityExists)
				{
					planEffectivityDao.updateExistingEffectivity(effectivityThru,
							planId,
							planVersion,
							planRevision,
							planAlterations,
							effectivityType,
							effectivityFrom);
				}

				else
				{
					planEffectivityDao.insertToReplaceMissingEffectivity(planId,
							planVersion,
							planRevision,
							planAlterations,
							effectivityType,
							effectivityFrom,
							effectivityThru,
							notes);
				}
				addPlanToEffectivityChangeList(planId,
						planVersion,
						planRevision,
						planAlterations,
						effectivityChangePlanList);
				generateRoutingOutboundXml();

			}
			else
			{
				if (dateUtils.validateDate(effectivityFromDateString)
						.equals(NO))
				{
					message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
				}

				if (isEmpty(effectivityThruDateString)
						|| effectivityThruDateString.indexOf("/  /") > -1)
				{
					//
					inputEffectivityThruDate = MAXIMUM_DATE;
					//
					localEffectivityThru = null;
				}
				else if (stringEquals(dateUtils.validateDate(effectivityThruDateString),NO))
				{
					message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME)); // Message Text:
				}
				else
				{
					try
					{
						inputEffectivityThruDate = parseDate(effectivityThruDateString);
					}
					catch (ParseException e)
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
					} 
					localEffectivityThru = effectivityThruDateString;
				}

				try
				{
					inputEffectivityFromDate = parseDate(effectivityFromDateString);
				}
				catch (ParseException e)
				{
					message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
				}

				if(!isEmpty(effectivityThruDateString))
				{
					try
					{
						Date d1 = parseDate(effectivityFromDateString);
						Date d2 = parseDate(effectivityThruDateString);
						if (d1.compareTo(d2) > 0)
						{
							message.raiseError("MFI_51621",
									new SimpleDateFormat(parameters.getClientSessionParameter(DATE_MASK_NAME)).format(d1),
									new SimpleDateFormat(parameters.getClientSessionParameter(DATE_MASK_NAME)).format(d2)); // Message
							// Text: From effectivity: %V1 is greater than through
							// effectivity: %V2
						}
					}
					catch(ParseException e)
					{
						message.raiseError("MFI_F7713D3EF37F4D8D8D8E96B8EB45A655" , parameters.getClientSessionParameter(DATE_MASK_NAME));
					}
				}
				//
				if (isEmpty(localEffectivityThru))
				{
					inputEffectivityThruDate = null;
				}
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_MASK);

				try
				{
					planEffectivityDao.insertTypeCheckWithDates(planId,
							planVersion,
							planRevision,
							planAlterations,
							effectivityType,
							formatter.format(inputEffectivityFromDate),
							inputEffectivityThruDate != null ? formatter.format(inputEffectivityThruDate)
									: null,
									inputEffectivityFromDate,
									inputEffectivityThruDate,
									notes);
					addPlanToEffectivityChangeList(planId,
							planVersion,
							planRevision,
							planAlterations,
							effectivityChangePlanList);
					generateRoutingOutboundXml();
				}
				catch (DataIntegrityViolationException e)
				{
					message.raiseError("MFI_87635",
							inputEffectivityFromDate.toString(),
							"This Plan's Effectivity");
				}
			}
		}

		// FND-25775
		// Reset BOM Line components as per new Effectivity.
		if(continueResetLineComps)
		{
			bom.resetBomLineCompsForNewEffectivity(planId, 
					planVersion, 
					planRevision, 
					planAlterations);
		}
	}
	

    /**
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    private boolean selectBetween(Number A, Number B, Number C, Number D)
    {
        return (validator.isBetweenNumber(A, B, C, A.getClass())
                || validator.isBetweenNumber(D, B, C, D.getClass()) || validator.isBetweenNumber(B,
                                                                                                 A,
                                                                                                 D,
                                                                                                 B.getClass()));
    }
    

    /**
     * @param newEffectivityFromDate
     * @param oldEffectivityFromDate
     * @param oldEffectivityThruDate
     * @param newEffectivityThruDate
     * @return
     */
    private boolean selectBetweenDate(Date newEffectivityFromDate,
                                      Date oldEffectivityFromDate,
                                      Date oldEffectivityThruDate,
                                      Date newEffectivityThruDate)
    {
        long newEffectivityFrom = newEffectivityFromDate.getTime();
        long oldEffectivityFrom = oldEffectivityFromDate.getTime();
        long oldEffectivityThru = oldEffectivityThruDate.getTime();
        long newEffectivityThru = newEffectivityThruDate.getTime();

        // 1/5/2007 FND-7173 added equality criteria
		// for different date effectivities.
		
		if (( newEffectivityFrom > oldEffectivityFrom && newEffectivityFrom < oldEffectivityThru )
		      || newEffectivityFromDate.equals(oldEffectivityFromDate)||
	             newEffectivityFromDate.equals(oldEffectivityThruDate) )
		{
			return true;
		}

		if (( newEffectivityThru > oldEffectivityFrom && newEffectivityThru < oldEffectivityThru )
		      || newEffectivityThruDate.equals(oldEffectivityFromDate)||
		         newEffectivityThruDate.equals(oldEffectivityThruDate) )
		{
			return true;
		}
		
		if (( oldEffectivityFrom > newEffectivityFrom && oldEffectivityFrom < newEffectivityThru )
		      || oldEffectivityFromDate.equals(newEffectivityFromDate)||
	             oldEffectivityFromDate.equals(newEffectivityThruDate) )
		{
			return true;
		}

        return false;
    }
    
    public void setShowUdvFlag()
    {
    }
    
    /**
     * @param planOfd
     *            the planOfd to set
     */
    public String buildNonDateMessage(String errorText,
                                      String localPlanVersion,
                                      String localPlanRevision,
                                      String effectivityType,
                                      Number newRangeStart,
                                      Number newRangeEnd,
                                      Number oldRangeStart,
                                      Number oldRangeEnd)
    {
        String vgResult = "";
        String vgs = "";
        String messageText = "";

        if ((newRangeStart.intValue() > oldRangeStart.intValue() && newRangeStart.intValue() <= oldRangeEnd.intValue())
                && newRangeEnd.intValue() >= oldRangeEnd.intValue())
        {
            try
            {
            	// GE-5943
                Map messageMap = selectMessageInformation(oldRangeEnd, "MFI_53385");
                messageText = (String) messageMap.get("MSG_TEXT");
                vgs = (String) messageMap.get("RANGE_END");
                vgResult = replace(messageText,
                                               "%V1",
                                               localPlanVersion);
                vgResult = replace(vgResult,
                                               "%V2",
                                               localPlanRevision);
                vgResult = replace(vgResult,
                                               "%V3",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult, "%V4", vgs);
                vgResult = replace(vgResult,
                                               "%V5",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult,
                                               "%V6",
                                               new String((newRangeStart.intValue() - 1)
                                                       + ""));

                errorText += vgResult + "\n";
            }
            catch (IncorrectResultSizeDataAccessException e)
            {
                message.raiseError("MFI_54249", "MFI_53385");
            }
        }

        else if (newRangeEnd.intValue() >= oldRangeStart.intValue()
                && newRangeEnd.intValue() < oldRangeEnd.intValue()
                && newRangeStart.intValue() <= oldRangeStart.intValue())
        {
            try
            {
            	// GE-5943
                Map m = selectMessageInformation(oldRangeEnd, "MFI_53385");
                messageText = (String) m.get("MSG_TEXT");
                vgs = (String) m.get("RANGE_END");
                vgResult = replace(messageText,
                                               "%V1",
                                               localPlanVersion);
                vgResult = replace(vgResult,
                                               "%V2",
                                               localPlanRevision);
                vgResult = replace(vgResult,
                                               "%V3",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult, "%V4", vgs);
                vgResult = replace(vgResult,
                                               "%V5",
                                               new String(newRangeEnd.intValue()
                                                       + 1 + ""));
                vgResult = replace(vgResult, "%V6", vgs);

                errorText += vgResult + "\n";
            }
            catch (IncorrectResultSizeDataAccessException e)
            {
                message.raiseError("MFI_54249", "MFI_53385");
            }

        }

        else if (oldRangeStart.intValue() >= newRangeStart.intValue()
                && oldRangeStart.intValue() <= newRangeEnd.intValue()
                && oldRangeEnd.intValue() >= newRangeStart.intValue()
                && oldRangeEnd.intValue() <= newRangeEnd.intValue())
        {
            try
            {
            	// GE-5943
                Map m = selectMessageInformation(oldRangeEnd, "MFI_54230");
                messageText = (String) m.get("MSG_TEXT");
                vgs = (String) m.get("RANGE_END");
                vgResult = replace(messageText,
                                               "%V1",
                                               localPlanVersion);
                vgResult = replace(vgResult,
                                               "%V2",
                                               localPlanRevision);
                vgResult = replace(vgResult,
                                               "%V3",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult, "%V4", vgs);

                errorText += vgResult + "\n";
            }
            catch (IncorrectResultSizeDataAccessException e)
            {
                message.raiseError("MFI_54249", "MFI_54230");
            }

        }
        // new start in between old && new end inbetween old
        else if (newRangeStart.intValue() >= oldRangeStart.intValue()
                && newRangeStart.intValue() <= oldRangeEnd.intValue()
                && newRangeEnd.intValue() >= oldRangeStart.intValue()
                && newRangeEnd.intValue() <= oldRangeEnd.intValue())
        {
            try
            {
            	// GE-5943
                Map m = selectMessageInformation(oldRangeEnd, "MFI_54229");
                messageText = (String) m.get("MSG_TEXT");
                vgs = (String) m.get("RANGE_END");
                vgResult = replace(messageText,
                                               "%V1",
                                               localPlanVersion);
                vgResult = replace(vgResult,
                                               "%V2",
                                               localPlanRevision);
                vgResult = replace(vgResult,
                                               "%V3",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult, "%V4", vgs);
                vgResult = replace(vgResult,
                                               "%V5",
                                               oldRangeStart.toString());
                vgResult = replace(vgResult,
                                               "%V6",
                                               new String(newRangeStart.intValue()
                                                       - 1 + ""));
                vgResult = replace(vgResult,
                                               "%V7",
                                               new String(newRangeEnd.intValue()
                                                       + 1 + ""));
                vgResult = replace(vgResult, "%V8", vgs);

                errorText += vgResult + "\n";
            }
            catch (IncorrectResultSizeDataAccessException e)
            {
                message.raiseError("MFI_54249", "MFI_54229");
            }

        }

        return errorText;
    }
    
    public Map selectMessageInformation(Number oldRangeEnd, String messageId)
    {
        Map<String, String> m = new HashMap<>();
        m.put("MSG_TEXT", message.buildMessage(EMPTY, messageId));
        m.put("RANGE_END", getRangeEndStringValue(oldRangeEnd));
        return m;
    }
    
    // GE-5943
	public String getRangeEndStringValue(Number oldRangeEnd) {
		String x;
        if (oldRangeEnd.intValue() == Integer.MAX_VALUE){
            x = "<UP>";
        } else {
            x = new String(oldRangeEnd.toString());
        }
		return x;
	}
	// END Defect 868 part 2
}
