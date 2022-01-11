/*
 *  This unpublished work, first created in 2014 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2014.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    com.pw.solumina.export_control.XClassWebServiceCallResult.java
 * 
 *  Created: 2014-06-18
 * 
 *  Author:  T. Damble
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------  ------------ 	---------------------------------------------------
 *  2014-06-18  T. Damble		Created new bean for returning information from
 *  							XClass - SAIP_MD_M103.
 *  
 */

package com.pw.solumina.export_control;

import java.util.List;

public class XClassWebServiceCallResult {

	private int returnCode = 0;			//   0=success, >1=error
	private String returnMsg = null;
	private List<XClassItemInfoBean> itemInfoBean = null;

	/**
	 * @return the returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the errorMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setReturnMsg(String errorMsg) {
		this.returnMsg = errorMsg;
	}
	/**
	 * @return the itemInfoBean
	 */
	public List<XClassItemInfoBean> getItemInfoBean() {
		return itemInfoBean;
	}
	/**
	 * @param itemInfoBean the itemInfoBean to set
	 */
	public void setItemInfoBean(List<XClassItemInfoBean> itemInfoBean) {
		this.itemInfoBean = itemInfoBean;
	}

}
