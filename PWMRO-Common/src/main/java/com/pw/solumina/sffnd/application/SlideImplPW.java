/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SlideImplPW.java
 * 
 *  Created: 2021-05-28
 * 
 *  Author:  X007084
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2021-05-28 		X007084		    Initial Release defect 1906
 */

package com.pw.solumina.sffnd.application;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.util.LanguageUtils;
import com.ibaset.solumina.sffnd.application.impl.SlideImpl;
import com.ibaset.solumina.sffnd.application.ISlide;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.impl.SlideDaoPW;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;

import static com.ibaset.common.SoluminaConstants.CREATE_REV;
import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.upperCase;

import static com.ibaset.common.SoluminaConstants.*;
import static com.ibaset.common.DbColumnNameConstants.*;
import static org.apache.commons.lang.StringUtils.*;
import static com.ibaset.common.util.SoluminaUtils.*;
import static org.apache.commons.lang.math.NumberUtils.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.util.LanguageUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IMultiMediaObject;
import com.ibaset.solumina.sffnd.application.ICommunication;
import com.ibaset.solumina.sffnd.application.IDirectNumericControl;
import com.ibaset.solumina.sffnd.application.IDrawing;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IProcess;
import com.ibaset.solumina.sffnd.application.ISlide;
import com.ibaset.solumina.sffnd.application.IStandardText;
import com.ibaset.solumina.sffnd.application.IThreeDModels;
import com.ibaset.solumina.sffnd.dao.IDocumentTypeDao;
import com.ibaset.solumina.sffnd.dao.IDrawingDao;
import com.ibaset.solumina.sffnd.dao.ISlideDao;
import com.ibaset.solumina.sffnd.dao.IStandardTextDao;
import com.ibaset.solumina.sfpl.application.IChangeRequest;
import com.ibaset.solumina.sfpl.application.IChangeRequestHelper;
import com.ibaset.solumina.sfpl.application.IFile;
import com.ibaset.solumina.sfpl.application.IPlanNotification;
import com.ibaset.solumina.sfpl.dao.IChangeRequestDao;
import com.ibaset.solumina.sfpl.dao.IReleasePackageDao;
import com.ibaset.solumina.sfpl.dao.IWorkPackageDao;

import java.util.Map;

public class SlideImplPW  {

	@Reference
	ISlide iSlide;

	@Reference
	ISecurityGroup iSecurityGroup;


	@Reference
	SlideDaoPW slideDaoPW;

	@Reference
	private IEvent event;

	@Reference
	private IMessage message;

	@Reference
	private ISlideDao slideDao;

	@Reference
	private IValidator validator;

	@Reference
	private DataFieldMaxValueIncrementer soluminaSequence;

	@Reference
	private IReleasePackageDao releasePackageDao;

	@Reference
	private IWorkPackageDao workPackageDao;

	@Reference
	private IDrawing drawing;

	@Reference
	private IChangeRequestHelper changeRequestHelper;

	@Reference
	private IChangeRequest changeRequest;

	@Reference
	private IProcess process;

	@Reference
	private IThreeDModels threeDModels;

	@Reference
	private IDirectNumericControl directNumericControl;

	private Log logger = LogFactory.getLog(this.getClass());

	@Reference
	CommonDaoPW commonDaoPW;

	//defect 1906
	public Map insertIllustrations( String objectTag,
			String objectDescription,
			String classification,
			String releasePackage,
			String slideModified, 
			String calledFrom,
			String commodityJurisdiction,
			String commodityClassification, 
			String associateChange, //FND-25579
			String workFlow,
			String workLocation) {
		System.out.println("objectTag - " + objectTag);
		System.out.println("objectDescription - " + objectDescription);
		System.out.println("releasePackage - " + releasePackage);
		System.out.println("calledFrom - " + calledFrom);

		Map objectInfo = iSlide.insertIllustrations(objectTag, objectDescription, classification, releasePackage, slideModified, calledFrom, commodityJurisdiction, commodityClassification, associateChange, workFlow);

		String objectId = (String)objectInfo.get("OBJECT_ID");
		System.out.println("objectId - " + objectId );
		System.out.println("workLocation - " + workLocation );
		iSecurityGroup.insertMultiMediaSecurityGroup(objectId, workLocation, calledFrom);

		//slideDaoPW.insertSlideMMObject(objectId, objectTag,"SLIDE",workLocation);

		return objectInfo;
	}

	public void updateIllustrations(String objectId, 
			String objectDescription,
			String releasePackage,
			String commodityJurisdiction,
			String commodityClassification, 
			String associateChange,
			String workLocation)  
	{

		iSlide.updateIllustrations(objectId, objectDescription, releasePackage, commodityJurisdiction, commodityClassification, associateChange);

	}	
	//defect 1906
	public void mmObjectCreate( String oldObjectId,
			String objectTag,
			String objectDescription,
			String classification,
			String releasePackage,
			String slideModified,
			String calledFrom,
			String workLocation)
	{

		if(stringEquals(calledFrom, CREATE_REV))
		{
			event.showUdv("PWUST_C46C7F511FFFAADBE05387971F0AB628",
					upperCase(INSERT),
					LanguageUtils.getMessage("create.revision"), // GE-7326
					"",
					"OBJECT_ID=" + oldObjectId 
					+",OBJECT_TAG=" + defaultIfEmpty(objectTag,"")
					+",OBJECT_DESC=" + defaultIfEmpty(objectDescription,"")
					+",CLASSIFICATION=" + defaultIfEmpty(classification,"")
					+",RELEASE_PACKAGE=" + defaultIfEmpty(releasePackage,"")
					+",CALLED_FROM=" + defaultIfEmpty(calledFrom,"")
					+",WORKLOCATION=" + defaultIfEmpty(workLocation,""));

			//	iSecurityGroup.insertMultiMediaSecurityGroup(oldObjectId, workLocation, calledFrom);

		}
		else
		{
			iSlide.mmObjectCreate(oldObjectId, objectTag, objectDescription, classification, releasePackage, slideModified, calledFrom);
		}

		//need ton insert work location for this	

	}

	public void createIllustrationsRevision(String oldObjectId,
			String objectTag,
			String objectDescription,
			String classification,
			String releasePackage,
			String slideModified, 
			String oldObjectRev,
			String calledFrom, 
			String associateChange, //FND-25579 
			String workFlow,
			String workLocation)  //FND-25063
	{
		validator.checkUserPrivilege(upperCase(SoluminaConstants.SLIDE_EDIT));

		String nextObjectRev = getNextObjectRevisionOrRaiseError(oldObjectId, calledFrom);

		String newObjectId = soluminaSequence.nextStringValue();
		String userId = ContextUtil.getUsername();

		try
		{
			// Create New
			slideDao.createIllustrationRevision(oldObjectId, objectTag, nextObjectRev, classification, objectDescription, releasePackage, newObjectId, userId, workFlow); // FND-25063

			raiseErrorOrUpdateReleasePackageStatus(releasePackage);
		}
		catch (DataIntegrityViolationException e)
		{
			logger.debug(e.getMessage());
			// [%V1] Tag already exists. Please choose a different tag name
			message.raiseError("MFI_6890CDD0CD6D478CB2BBC14C0F39A929", objectTag);
		}

		// Call exec sfcore_mm_obj_del_all_embd_obj(:@userid,:OBJECT_ID)
		slideDao.deleteAllEmbededMultiMediaObject(userId, newObjectId);

		// get List from sfcore_mm_object using old object id and call
		// exec
		// sfcore_mm_obj_ins_embd_obj(:@userid,:OBJECT_ID,:EMBEDDED_OBJECT_ID,:EMBEDDED_OBJECT_TYPE,:BLOCK_ID,
		// :URL_PREFIX,:URL_PARAMS,:URL_SUFFIX,:REF_ID, null)

		insertEmbededMultiMediaObject(oldObjectId, newObjectId, userId);

		// insert entry with New OBJECT_ID, OBJECT_TAG = OBJECT_ID and
		// OBJECT_TYPE= PAGEIMAGE
		String newObjectId2 = soluminaSequence.nextStringValue();

		slideDao.insertIllustrationPageImage(userId, newObjectId2, "PAGEIMAGE",// objectType,
				newObjectId,// objectTag,
				nextObjectRev,
				"JPG",
				oldObjectRev,
				oldObjectId);

		//1906 - this is why the entire method was copied , to get the new object id and populate the security group
		//get the security groups tied to the original object id and tie them to the new one
		System.out.println("oldObjectId " + oldObjectId);
		System.out.println("calledFrom " + calledFrom);
		System.out.println("workLocation " + workLocation);
		System.out.println("newObjectId " + newObjectId);
		
		//if illustration is being copied
		if (StringUtils.equals("COPY_REV", calledFrom))
		{
			iSecurityGroup.insertMultiMediaSecurityGroup(newObjectId, workLocation, calledFrom);
		}
		else
		{
			List securityGroups = commonDaoPW.getMMobjectSecurityGroups(oldObjectId,objectTag);
			ListIterator securityGroupsIt = securityGroups.listIterator();
			while (securityGroupsIt.hasNext()){
				Map securityGroupsKeys = (Map) securityGroupsIt.next();
				String securityGroup = (String) securityGroupsKeys.get("SECURITY_GROUP");
				System.out.println("security group " + securityGroup);
				System.out.println("newObjectId " + newObjectId);
				iSecurityGroup.insertMultiMediaSecurityGroup(newObjectId, securityGroup, calledFrom);
			} 
		}

		insertMultimediaObjectsBasedOnClassification(oldObjectId, classification, newObjectId);

		// FND-24480
		String changeRequestIdSession = (String) ContextUtil.getUser().getContext().get("CHANGE_REQUEST_ID");
		String plannedActionIdSession = (String) ContextUtil.getUser().getContext().get("PLANNED_ACTION_ID");

		if (isNotEmpty(changeRequestIdSession))
		{
			// FND-25267
			String calledFrom1 = COMPLETE_ACTION;
			changeRequestHelper.updatePlannedActionAndChangeRequest(changeRequestIdSession,
					plannedActionIdSession,
					releasePackage,
					null,
					null,
					newObjectId,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					calledFrom1);

			// GE-1308
			// Push the File to Slide if planned action is to Push the Latest
			// Object.
			changeRequestHelper.pushFileToSlideViaTakeAction(changeRequestIdSession, plannedActionIdSession);
		}

		event.launchSlide(newObjectId);

		// FND-25579
		if (isEmpty(changeRequestIdSession) && stringEquals(associateChange, Y))
		{
			changeRequest.showChangeNotificationUdv(null, null, newObjectId, null, null, null, null, null, null, null, null, null, null, null);
		}

		ContextUtil.getUser().getContext().set("CHANGE_REQUEST_ID", null);
		ContextUtil.getUser().getContext().set("PLANNED_ACTION_ID", null);
		ContextUtil.getUser().getContext().set("PWP_ID", null);

	}

	String getNextObjectRevisionOrRaiseError(String oldObjectId, String calledFrom)
	{
		String nextObjectRev = INTEGER_ONE.toString();
		if(stringEquals(calledFrom, CREATE_REV))
		{
			Map<String, Object> illustrationInfoMap = slideDao.getNextRevisionForIllustartion(oldObjectId); 

			// FND-27078 check that latest revision is complete or not
			if(!illustrationInfoMap.isEmpty())
			{
				String status = (String) illustrationInfoMap.get(STATUS);
				if(!equalsIgnoreCase(status, COMPLETE))
				{
					message.raiseError("MFI_98DA94F215AE439AB7875082EADF01E6");
				}

				nextObjectRev =  String.valueOf( illustrationInfoMap.get(NEXT_OBJECT_REV));
			}
		}
		return nextObjectRev;
	}

	void raiseErrorOrUpdateReleasePackageStatus(String releasePackage)
	{
		// FND-24822
		if (isNotEmpty(releasePackage))
		{
			String releasePackageStatus = workPackageDao.selectWorkPackageStatus(releasePackage);

			if (stringEquals(releasePackageStatus, RELEASED) || stringEquals(releasePackageStatus, CANCELED)) // FND-24822
			{
				message.raiseError("MFI_52541", releasePackageStatus);
			}

			if (stringEquals(releasePackageStatus, PENDING) || stringEquals(releasePackageStatus, IN_QUEUE))
			{
				releasePackageDao.updateReleasePackageStatus(releasePackageStatus, ACTIVE_STATUS, releasePackage, ContextUtil.getUsername());
			}
		}
	}

	void insertEmbededMultiMediaObject(String oldObjectId, String newObjectId, String userId)
	{
		List<Map <String, Object>> allEmbedObjectsList = slideDao.selectHtrefMultiMediaObject(oldObjectId);

		Iterator<Map<String,Object>> allEmbedObjectsListIterator = allEmbedObjectsList.listIterator();

		while(allEmbedObjectsListIterator.hasNext())
		{
			Map <String, Object> allEmbedObjectsMap = allEmbedObjectsListIterator.next();
			String htrefEmbeddedObjectId = (String) allEmbedObjectsMap.get(EMBEDDED_OBJECT_ID);
			String htrefRefId = (String) allEmbedObjectsMap.get(REF_ID);
			String htrefEmbedObjectType = (String) allEmbedObjectsMap.get(EMBEDDED_OBJECT_TYPE);
			String htrefBlockId = (String) allEmbedObjectsMap.get(BLOCK_ID);
			String htrefUrlPrefix = (String) allEmbedObjectsMap.get(URL_PREFIX);
			String htrefUrlParams = (String) allEmbedObjectsMap.get(URL_PARAMS);
			String htrefUrlSuffix = (String) allEmbedObjectsMap.get(URL_SUFFIX);

			slideDao.insertAllEmbededMultiMediaObject(userId, 
					newObjectId, 
					htrefEmbeddedObjectId, 
					htrefEmbedObjectType, 
					htrefBlockId, 
					htrefUrlPrefix, 
					htrefUrlParams, 
					htrefUrlSuffix, 
					htrefRefId,
					null);
		}
	}

	void insertMultimediaObjectsBasedOnClassification(String oldObjectId, String classification, String newObjectId)
	{
		if (stringEquals(classification, DRAWINGS))
		{
			// insert drawing attributes in classification
			drawing.insertDrawing(newObjectId, null, null, null, null, null, null, null, oldObjectId, null, null);
		}
		if (stringEquals(classification, THREE_D_MODELS))
		{
			// insert 3D Models attributes in classification
			threeDModels.insert3DModels(newObjectId, null, null, null, null, null, null, null, null, null, oldObjectId, null, null, null);
		}
		if (stringEquals(classification, DNC))
		{
			// insert DNC attributes in classification
			directNumericControl.insertDirectNumericControl(newObjectId, null, null, oldObjectId, null, null);
		}
		if (stringEquals(classification, PROCESS_SPECS))
		{
			// insert Process Specs attributes in classification
			process.insertProcessSpecification(newObjectId,
					null,
					null,
					oldObjectId,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
	}

}

