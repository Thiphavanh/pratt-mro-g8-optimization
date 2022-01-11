/*
*  This unpublished work, first created in 2021 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanImplPW.java
* 
*  Created: 2017-08-24
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	-----------------------------------------------------------------
*  2021-05      D.Miron         Defect 1921 - Changed PLND_WORK_LOC to PLND_LOCATION_ID in importPlan
*
*/
package com.utas.solumina.sfpl.application.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.client.SoluminaServiceLocator;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.common.sql.Column;
import com.ibaset.common.sql.ColumnHeader;
import com.ibaset.common.sql.IPassThroughQuery;
import com.ibaset.common.sql.ResultObject;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.data.DataMigrationService;
import com.ibaset.solumina.data.DataSet;
import com.ibaset.solumina.data.Row;
import com.ibaset.solumina.data.TableData;
import com.ibaset.solumina.domain.dao.DAO;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IDataMigrationLog;
import com.ibaset.solumina.sffnd.application.IGlobalConfiguration;
import com.ibaset.solumina.sffnd.application.IQueue;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.application.ITask;
import com.ibaset.solumina.sffnd.application.IUser;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sffnd.dao.ITaskDao;
import com.ibaset.solumina.sffnd.domain.DataMigrationLog;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.thoughtworks.xstream.XStream;

public abstract class UtasDataMigrationServiceImplExt extends ImplementationOf<DataMigrationService> implements DataMigrationService
{
	@Reference
	private ISecurityGroupDao securityGroupDao;
	
	@Reference
	private ISecurityGroup securityGroupApp;
	
	@Reference
	private IDataMigrationLog dataMigrationLog;
	
	@Reference
	private IMessage message;
	
	@Reference
	private IGlobalConfiguration globalConfiguration;
	
	@Override
	public List getFileListForImport() 
	{
		ArrayList<Map> result =new ArrayList<Map>();
		ArrayList<Map> resultTemp = (ArrayList<Map>) Super.getFileListForImport();
		
		Iterator itr = resultTemp.iterator();
		while(itr.hasNext())
		{
			Map record = (Map) itr.next();
			record.put("FILE", record.get("FILE"));
			record.put("SIZE", record.get("SIZE"));
			record.put("MODIFIED", record.get("MODIFIED"));
			record.put("SELECTED", record.get("SELECTED"));
			record.put("RESTRICTED", "N");
			result.add(record);
		}
		return result;
	}
	
	public void importPlan(String pwpId, 
						   String fileName) 
	{
		String secPlanNo = StringUtils.EMPTY;
		String plndWorkLoc = StringUtils.EMPTY;
		StringWriter sw=new StringWriter();
		PrintWriter log =new PrintWriter(sw);
		String planNos = null;
		DataMigrationLog logRecord=new DataMigrationLog();
		logRecord.setDirection("IMPORT");
		logRecord.setStartDate(new Date());
		String[] refs=null;
		SoluminaException exception= null;
		boolean failed = true;
		try
		{
			File file=new File(getImportDirectory(), fileName);
			log.println("Reading from "+file.getAbsolutePath());
			DataSet ds = readDataSet(file);
			final int currentRelease = parseReleaseInfo(getExportInfo());
			final int importRelease = parseReleaseInfo(ds.getHeader());
			final int diff = currentRelease / 100 - importRelease / 100; 
			if(diff!=0)
			{
				log.println("Solumina compatible release: "+currentRelease);
				log.println("File release: "+importRelease);
				log.println("WARNING: Correctness of data migration between releases is not guaranteed.");
			}
			log.println("Importing plan into PWP: "+pwpId);
			ds.replaceValues("PWP_ID", pwpId);
			
			importDataSet(ds, log);
			TableData planDescData= ds.getEntityData("SFPL_PLAN_DESC", null);
			TableData planRevData = ds.getEntityData("SFPL_PLAN_REV", null);
			ArrayList<Row> descs = planDescData.getRows();
			ArrayList<Row> revs = planRevData.getRows();
			for(Row desc : descs)
			{
				// Defect 1921
				String planInfo[] = planDescData.getRowValues(desc, new String[]{"PLAN_ID","PLAN_NO", "PLAN_TYPE","DOC_TYPE","WORK_FLOW", "PLND_LOCATION_ID"});
				String planId = planInfo[0];
				String planNo=planInfo[1];
				String planType = planInfo[2];
				String documentType = planInfo[3];
				String workFlow = planInfo[4];
				//UTAS-435
				secPlanNo = planNo;
				plndWorkLoc = planInfo[5];
				for(Row rev:revs)
				{
					refs=planRevData.getRowValues(rev, new String[]{
							"PLAN_ID", 
							"PLAN_VERSION", 
							"PLAN_REVISION", 
							"PLAN_ALTERATIONS"});
					if(refs[0].equals(planId)) 
					{
						insertTask(documentType, planType, refs[0], refs[1], refs[2], refs[3], SoluminaConstants.PWP, pwpId,workFlow);
						log.println("Successfully imported plan "+planNo);
						if(planNos == null)planNos = planNo;
						else planNos+=", "+planNo;
						break;
					}
				}
			}
			//check security group of items
			TableData itemData = ds.getReferenceData("SFPL_ITEM_DESC_MASTER_ALL", null);
			ArrayList<Row> items = itemData.getRows();
			if(items!=null && items.size()>0)
			{
				for(Row r:items)
				{
					String partNumber = itemData.getRowValues(r, new String[]{"PART_NO"})[0];
					String securityGroup = securityGroupDao.selectSecurityGroupOfItem(partNumber);
					if(securityGroup!=null && securityGroup.trim().length()>0)
					{
						log.println("Updating security groups for part number "+partNumber);
						//propagate security groups to all objects
						String userName = ContextUtil.getUsername();
						String[] groups = SoluminaUtils.splitSecurityGroups(securityGroup);
						for(String sg:groups)
						{
							securityGroupApp.insertSecurityGroupAsynchronous(sg, partNumber, userName);
						}
					}
				}
			}
			failed = false;
		}
		catch (SoluminaException e) 
		{
			exception = e;
			e.printStackTrace(log);
		}
		catch (Throwable e) 
		{
			e.printStackTrace(log);
		}
		log.flush();
		logRecord.setStatus(failed?"FAILED":"SUCCESS");
		logRecord.setEndDate(new Date());
		logRecord.setFileName(fileName);
		logRecord.setTableName("SFPL_PLAN_REV");
		if(refs!=null)
		{
			logRecord.setRef1(refs[0]);
			logRecord.setRef2(refs[1]);
			logRecord.setRef3(refs[2]);
			logRecord.setRef4(refs[3]);
		}
		logRecord.setUserid(ContextUtil.getUsername());
		DAO dao = SoluminaServiceLocator.getDAO();
		logRecord.setLogText(dao.createClob(sw.toString()));
		dataMigrationLog.insert(logRecord);
		//UTAS-435
		ContextUtil.getUser().getContext().set("IMPORT_PLAN_NO", secPlanNo);
		ContextUtil.getUser().getContext().set("IMPORT_PLAN_PLND_WORK_LOC", plndWorkLoc);
		//
		if(failed)
		{
			if(exception != null) throw exception; // rethrow solumina exception
			//Operation has failed. Please refer to import/export log for details.
			message.raiseError("MFI_6EC9798872964774A29CA32CC1592699");
		}
		else
		{
			//Plan %V1 has been successfully imported
			message.showMessage("MFI_F96BB4A403944B0CB1D56A3511359132", planNos);
		}
	}
	
	public String getImportDirectory() 
	{
		String importDirectory = globalConfiguration.getParameterValue(SoluminaConstants.FOUNDATION, "PLAN_IMPORT_PATH");
		return importDirectory;
	}
	
	private DataSet readDataSet(File file) throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream(file);
		try
		{
			if(file.getPath().toLowerCase().endsWith(".pln"))
			{
				ObjectInputStream ois = new ObjectInputStream(fis);
				return (DataSet)ois.readObject();
			}
			return (DataSet)createXStream().fromXML(fis);
		} 
		finally
		{
			fis.close();
		}
	}
	
	private String getExportInfo()
	{
		IPassThroughQuery ptq = (IPassThroughQuery) SoluminaServiceLocator.locateService(IPassThroughQuery.class);
		ResultObject ro = ptq.executeQuery("SELECT RELEASE, VERSION FROM SFMFG.SFDB_INFO ORDER BY TIME_STAMP", 1, 100, new java.util.HashMap(), new java.util.HashMap());
		StringWriter sw = new StringWriter();
		PrintWriter out=new PrintWriter(sw);
		out.println("Created on: "+new Date());
		out.print("UserId: ");
		out.println(ContextUtil.getUsername());
		IUser user = (IUser)SoluminaServiceLocator.locateService(IUser.class);
		out.print("Full name: ");
		String fullName = user.getUserName(ContextUtil.getUsername()); 
		if(fullName !=null) out.println(fullName);
		out.println();
		if(ro.getErrors() == null || ro.getErrors().size()==0)
		{
			out.println("Database Information");
			List columnHeadersList = ro.getColumnHeaders();
			List rowsList = ro.getRows();
			
			ArrayList<String> columnNames = new ArrayList<String>();
			Iterator columnHeadersIterator = columnHeadersList.iterator();
			while (columnHeadersIterator.hasNext())
			{
				ColumnHeader columnHeader = (ColumnHeader) columnHeadersIterator.next();
				columnNames.add(columnHeader.getColumnName());
			}
			
			Iterator rowsIterator = rowsList.iterator();
			while (rowsIterator.hasNext())
			{
				com.ibaset.common.sql.Row row = (com.ibaset.common.sql.Row) rowsIterator.next();
				List<Column> rowColumnsList = row.getColumns();
				for(int i=0;i<rowColumnsList.size();++i)
				{
					Column column = rowColumnsList.get(i);
					String columnName = columnNames.get(i);
					out.print(columnName);
					out.print(": ");
					out.println(column.getValue().toString());
				}
			}
			out.flush();
		}
		return sw.toString();
	}
	
	private static int parseReleaseInfo(String info)
	{
		int release=0; 
		if(info!=null){
			Pattern p = Pattern.compile("Database Information[^\\d]+([\\d]{4})");
			Matcher m = p.matcher(info); 
			if(m.find())
			{
				release = Integer.parseInt(m.group(1));
			}
		}
		return release;
	}
	
	private void insertTask(String documentType,
							String planType, 
							String planId, 
							String planVersion, 
							String planRevision, 
							String planAlterations, 
							String changeDocType, 
							String changeDocId,
							String workFlow)
	{
        String priority = null; // FND-18942
        ITask task = (ITask)SoluminaServiceLocator.locateService(ITask.class);
        IQueue queue = (IQueue)SoluminaServiceLocator.locateService(IQueue.class);
        ITaskDao taskDao = (ITaskDao)SoluminaServiceLocator.locateService(ITaskDao.class);
        String taskTypeForPlan = taskDao.selectTaskTypeInfo(documentType, planType, workFlow);
        String queueType = queue.selectQueueType(taskTypeForPlan,
                                                 taskTypeForPlan);
        task.insertTask(null,
        		        taskTypeForPlan,
                        queueType,
                        SoluminaConstants.ACTIVE_STATUS,
                        priority,
                        ContextUtil.getUsername(),
                        null,
                        changeDocType,
                        changeDocId,
                        planId,
                        planVersion,
                        planRevision,
                        planAlterations,
                        documentType);
        IPlanDao planDao = (IPlanDao)SoluminaServiceLocator.locateService(IPlanDao.class);
        planDao.updatePlanRevisionStatus(planId, 
        								 new BigDecimal(planVersion), 
        								 new BigDecimal(planRevision), 
        								 new BigDecimal(planAlterations), 
        								 queueType);
		
	}
	
	private XStream createXStream()
	{
		XStream xstream = new XStream();
		xstream.alias("dataSet", DataSet.class);
		xstream.alias("tableData", TableData.class);
		xstream.alias("row", Row.class);
		return xstream;
	}
		
}
