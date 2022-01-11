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
 *  File:    com.pw.solumina.export_control.XClassItemInfoSaxHandler.java
 * 
 *  Created: 2014-06-18
 * 
 *  Author:  T. Damble
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------  ------------ 	---------------------------------------------------
 *  2014-06-18  T. Damble		New Development for SAIP_MD_M103.	
 *  
 */

package com.pw.solumina.export_control;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibaset.common.Reference;

import java.util.*;

public class XClassItemInfoSaxHandler extends DefaultHandler {
	
	@Reference 
	XClassWebServiceCallResult xcwscResult;
	
	@Reference
	private XClassItemInfoBean itemInfoBean;

	public List<XClassItemInfoBean>  itemInfoBeans  = new ArrayList<XClassItemInfoBean>();

    private Stack<String> elementStack = new Stack<String>();

    public XClassItemInfoSaxHandler()
	{
		xcwscResult   = new XClassWebServiceCallResult();
		itemInfoBean  = new XClassItemInfoBean();
		itemInfoBeans = new ArrayList<XClassItemInfoBean>();
		elementStack  = new Stack<String>();
	}
	
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {

//		System.out.println("In startElement qName = '" + qName + "'");
        this.elementStack.push(qName);

        if("Item".equals(qName)){
        	itemInfoBean  = new XClassItemInfoBean();
        	xcwscResult.setReturnCode(0);
        } else if("RESPONSE".equals(qName)){
        	xcwscResult.setReturnCode(1);
        }
    }

    public void endElement(String uri, String localName,
        String qName) throws SAXException {

//		System.out.println("In endElement qName = '" + qName + "'");
        this.elementStack.pop();

        if("Item".equals(qName)){
            this.itemInfoBeans.add(itemInfoBean);
            xcwscResult.setItemInfoBean(this.itemInfoBeans);
        }
    }

    public void characters(char ch[], int start, int length)
        throws SAXException {

        String value = new String(ch, start, length).trim();
//        String value = new String(ch, start, length);
//        System.out.println("In characters sValue = '" + value + "'");
        
        if(value.length() == 0) return; // ignore white space

        if("RECNUMBER".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setRecnumber(value);
        } else if("REQ_CLOCK".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setReqClock(value);
        } else if("REQ_NAME".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setReqName(value);
        } else if("REQ_PHONE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setReqPhone(value);
        } else if("REQ_ORG".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setReqOrg(value);
        } else if("REQ_EMAIL".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setReqEmail(value);
        } else if("RECDATE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setRecDate(value);
        } else if("BAER_NAME".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setBaerName(value);
        } else if("BAER_EMAIL".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setBaerEmail(value);
        } else if("ITEM_IDENTIFIER".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setItemIdentifier(value);
        } else if("CAGE_CD".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setCageCode(value);
        } else if("CLASS_CATEGORY".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setClassCategory(value);
        } else if("CLASS_TYPE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setClassType(value);
        } else if("SFT_VERSION".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setSftVersion(value);
        } else if("NOMENCLATURE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setNomenclature(value);
        } else if("JURISDICTION".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setJurisdiction(value);
        } else if("CLASSIFICATION".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setClassification(value);
        } else if("CLASSIFICATION_DATE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setClassificationDate(value);
        } else if("EXPORT_VERIFICATION".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setExportVerification(value);
        } else if("VERIFICATION_DATE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setVerificationDate(value);
        } else if("ITEM_DESCRIPTION".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setItemDescription(value);
        } else if("IS_SME".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setIsSme(value);
        } else if("DESIGN_PW".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setDesignPW(value);
        } else if("JC_SOURCE".equals(currentElement())&&
                "Item".equals(currentElementParent())){
            itemInfoBean.setJcSource(value);
        } else if("MESSAGE".equals(currentElement())&&
                "RESPONSE".equals(currentElementParent())){
        	xcwscResult.setReturnMsg(value);
        } else {
//        	System.out.println("CurrentElement '" + currentElement() + "'");
        }
    }

    public synchronized void processingInstruction(String target, String data) throws SAXException    
    {   
    	System.out.println("ProcessingInstruction:"+target+" "+data);   
    }   

    private String currentElement() {
        return this.elementStack.peek();
    }

    private String currentElementParent() {
        if(this.elementStack.size() < 2) return null;
        return this.elementStack.get(this.elementStack.size()-2);
    }

}    
