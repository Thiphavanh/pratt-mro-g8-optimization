--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by X006640 on 9/23/2020, 10:10:31 AM --
--------------------------------------------------

set define off

prompt
prompt Creating function PWUST_SFWID_PW_ORDER_NO_GET
prompt =============================================
prompt
create or replace function sfmfg.PWUST_SFWID_PW_ORDER_NO_GET(v_order_id sfwid_orders.order_id%type)
  return varchar2 as
  -- Copyright 1995-2002 iBASEt, Inc.
  -- Unpublished-rights reserved under the Copyright Laws of the United States.
  -- US Government Procurements:
  --         Commercial Software licensed with Restricted Rights.
  -- Use, reproduction, or disclosure is subject to restrictions
  -- set forth in license agreement and purchase contract.
  -- iBASEt, Inc. 20162 Windrow Dr., Lake Forest, CA 92630
  -- ****************************************************************************
  -- Date Created and Created By: 08-MAY-2002
  -- Description: Used for view performance to return the order_no for a given
  --              order_id.
  -- Modification History:
  -- 10/18/2012 Niyati Shah
  --            FND-21430 Changed hardcoded length size of variables to Database Colume size
  -- 09/18/2020 Scott Edgerly
  --            Defect 1765: Copied from SFWID_ORDER_NO_GET() and modifed to to return PW_ORDER_NO
  -- ****************************************************************************

  v_pw_order_no pwust_sfwid_order_desc.pw_order_no%TYPE;
begin
  begin
    select pw_order_no
      into v_pw_order_no
      from pwust_sfwid_order_desc
     where order_id = v_order_id;
  exception
    when no_data_found then
      v_pw_order_no := null;
  end;
  return v_pw_order_no;
end;
/
grant execute on SFMFG.PWUST_SFWID_PW_ORDER_NO_GET to SF$CONNECTION_POOL;
