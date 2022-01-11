/*
* This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PWUST_SFWID_BUYOFF_PERCENT_GET.sql
* 
 *  Created: 2020-06-04
* 
 *  Author:  Fred Ettefagh
* 
 *  Revision History
* 
 *  Date        Who             Description
*  ----------  ---------------  ------------------------------------------------------------------
*  2020-06-04  Fred Ettefagh    Initial Release Defect 1582 changed oob function by adding  sfwid_serial_desc to "partial_serials" cursor to exclude serial_status !='STOP'
*  2020-06-10  Fred Ettefagh    Defect 1582 changed function to get order qty from sfwid_order_Desc for serial control orders
*/
create or replace function PWUST_SFWID_BUYOFF_PERCENT_GET(vi_order_id sfwid_order_desc.order_id%type,
                                                          vi_oper_key  sfwid_oper_buyoff.oper_key%type,
                                                          vi_step_key  sfwid_oper_buyoff.step_key%type,
                                                          vi_buyoff_id sfwid_oper_buyoff.buyoff_id%type,
                                                          vi_order_qty sfwid_order_desc.order_qty%type)
                                                          return varchar as
--
--**************************************************************************
-- Copyright 1995-2004 iBASEt, Inc.
-- Unpublished-rights reserved under the Copyright Laws of the United States.
-- US Government Procurements:
-- Commercial Software licensed with Restricted Rights.
-- Use, reproduction, or disclosure is subject to restrictions
-- set forth in license agreement and purchase contract.
-- File content is considered confidential information.
-- iBASEt, Inc. 27442 Portola Parkway, Suite 300, Foothill Ranch, CA 92610
--******************************************************************************
-- Date Created: 08/13/2008
-- Created By:   Suresh Prajapati
-- Description:  Returns a Buyoff Percent/Qty Complete value.
--******************************************************************************
-- Modification History:
--
-- 04/17/2015 Neha GE-1057 Added COMPLETE status in where clasue for lot control order
--			      to populate correct value in Percentage complete field in Buyoff embedded UDV.
-- 06/23/2014 Pravin FND-22934 Rename QTY_COMPLETE to COMPLETE_QTY for Buyoff Table SFWID_SERIAL_OPER_BUYOFF
-- 07/02/2013 Ankur FND-22801 Added COMPLETE status in where clasue for serialized order
--			    to populate correct value in Percentage complete field in Buyoff embedded UDV.
-- 10/18/2012 Niyati Shah
--            FND-21430 Changed hardcoded length size of variables to Database Colume size
-- 02/28/2012 Kartik FND-20126 Update Complete qty properly in buyoff grid after
--            partially scrap and spliting work order.
-- 02/22/2011 Suresh FND-16348 Display percentage/qty complete while...
--            Buyoff partial done or accepted
--            Buyoff partial done then rejected
--            Buyoff partial done or accepted then reopened
-- 04/26/2010 Prasad Gorthy
--            FND-13873.Get only the in queue and active status units
--
-- 06/24/2009 Suresh FND-11263 increased v_temp_number variable range from (5, 2) to (10, 2).
-- 10/22/2008 Suresh Prajapati FND-9345
--            For multi units serial control order, consider both partial and accepted serials
--            for automatically calculating over all %Complete vaue for buyoff
-- 08/26/2008 Suresh Prajapati FND-8375
--            1) Changed function return type from number to string
--            2) Returns QTY complete when order is lot/none control and order_qty > 1
--            3) Returns PERCENT complete when order is (serial control) OR (lot/none control and order_qty = 1)
--******************************************************************************
v_percent_qty_complete sfwid_serial_oper_buyoff.complete_qty%type;
v_partial_complete varchar(10);
v_serial_flag sfwid_order_desc.serial_flag%type;
-- 06/24/2009 Suresh FND-11263
v_temp_number number(10, 2);
-- 10/22/2008 Suresh Prajapati FND-9345
v_partial_serial_overall_pc number(5, 2);
--04/26/2010 Prasad FND-13873.Get only the in queue and active status units
v_order_qty  sfwid_order_desc.order_qty%type;
v_order_scrap_qty sfwid_order_desc.order_scrap_qty%type;
--02/28/2012 FND-20126
cursor partial_serials is
select percent_complete
from sfwid_serial_oper_buyoff a , sfwid_serial_oper b, sfwid_serial_desc c  -- Fred Ettefagh defect 1582
WHERE a.order_id   = b.order_id
AND a.oper_key  = b.oper_key
AND b.step_key  = -1
AND a.lot_id    = b.lot_id
AND a.serial_id = b.serial_id
and b.order_id = c.order_id
and b.lot_id = c.lot_id
and b.serial_id = c.serial_id
AND a.order_id = vi_order_id
and a.oper_key = vi_oper_key
and a.step_key = vi_step_key
and buyoff_id = vi_buyoff_id
-- 02/21/2011 Suresh FND-16348
-- Removed status checking because percent_complete updated based on status in code. So no need to check status.
--and (buyoff_status = 'PARTIAL' or buyoff_status = 'ACCEPT' or buyoff_status = 'REJECT' or buyoff_status = 'REOPEN')
and percent_complete is not NULL
--  07/02/2013 Ankur FND-22801
--  Added 'COMPLETE' status in checking serial_oper_status
AND b.serial_oper_status IN ('ACTIVE','IN QUEUE','COMPLETE')
and c.serial_status !='STOP';  -- Fred Ettefagh defect 1582
--
begin
   --
   select serial_flag, order_scrap_qty,order_qty 
   into v_serial_flag, v_order_scrap_qty,v_order_qty
   from sfwid_order_desc
   where order_id = vi_order_id;
   --
   --02/28/2012 FND-20126
   if  v_serial_flag='N' then
       v_order_qty := vi_order_qty - v_order_scrap_qty;
   end if;
   --  
   if v_order_qty > 1 and v_serial_flag = 'N' then
   --
       --04/26/2010 Prasad FND-13873.Get only the in queue and active status units
       select complete_qty into v_percent_qty_complete
       from sfwid_serial_oper_buyoff a , sfwid_serial_oper b
       where  a.order_id   = b.order_id
       AND a.oper_key  = b.oper_key
       AND b.step_key  = -1
       AND a.lot_id    = b.lot_id
       AND a.serial_id = b.serial_id
       AND a.order_id = vi_order_id
       and a.oper_key = vi_oper_key
       and a.step_key = vi_step_key
       and buyoff_id = vi_buyoff_id
       --  04/17/2015 Neha GE-1057
       --  Added 'COMPLETE' status in checking serial_oper_status
       AND b.serial_oper_status IN ('ACTIVE','IN QUEUE','COMPLETE');


       v_partial_complete := v_percent_qty_complete || '';

    -- When Order is single unit
     elsif v_order_qty = 1 then

         --02/28/2012 FND-20126
        select percent_complete into v_percent_qty_complete
       from sfwid_serial_oper_buyoff a,
            sfwid_serial_oper b
       where a.order_id = vi_order_id
       and a.oper_key = vi_oper_key
       and a.step_key = vi_step_key
       and a.buyoff_id = vi_buyoff_id
       and a.order_id = b.order_id
       and a.oper_key = b.oper_key
       and b.step_key = -1
       and a.serial_id = b.serial_id
       and a.lot_id = b.lot_id
       and b.serial_oper_status != 'CANCEL';

       if v_percent_qty_complete is not null then
          v_partial_complete := (v_percent_qty_complete/100) || ' %';
       end if;

   -- When Order is multi unit and serial control
     elsif v_order_qty > 1 then

       -- 10/22/2008 Suresh Prajapati FND-9345
       -- For multi units serial control order, consider both partial and
       -- accepted serials for automatically calculating overall %Complete value
       -- for buyoff.
       open partial_serials;
       loop
       fetch partial_serials
        into v_percent_qty_complete;
       exit when partial_serials%notfound;
           v_partial_serial_overall_pc := v_percent_qty_complete/100;
           v_temp_number := nvl(v_temp_number,0) + v_partial_serial_overall_pc;
       end loop;
       close partial_serials;

       if v_temp_number is not null then
          v_temp_number := v_temp_number/v_order_qty;
          v_partial_complete := v_temp_number || ' %';
       end if;

     end if;

  return v_partial_complete;

exception
  when others then
    return v_partial_complete;
end;
/

grant execute on PWUST_SFWID_BUYOFF_PERCENT_GET to SF$CONNECTION_POOL;
grant execute on PWUST_SFWID_BUYOFF_PERCENT_GET to PRATT_READ_ONLY;

