package com.utas.solumina.sfwid.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.utas.solumina.common.UtasConstants;

public abstract class UtasOrderDaoImplExt extends ImplementationOf<IOrderDao> implements IOrderDao
{

    @Override
    public String selectOrderSecurityGroup(String tableName,
                                           String columnName,
                                           String columnName2,
                                           String partNumber,
                                           Number planUpdateNumber) 
    

    

    {
        String calledFrom = (String) ContextUtil.getUser().getContext().get("CALLED_FROM_ORDER");
        
        if (StringUtils.equalsIgnoreCase(calledFrom, UtasConstants.BLANK_ORDER) && StringUtils.equalsIgnoreCase(tableName, "SFPL_ITEM_DESC_MASTER_ALL"))
        {
            return StringUtils.EMPTY;
        }
        
        return Super.selectOrderSecurityGroup(tableName, columnName, columnName2,partNumber,planUpdateNumber);
    }
}
