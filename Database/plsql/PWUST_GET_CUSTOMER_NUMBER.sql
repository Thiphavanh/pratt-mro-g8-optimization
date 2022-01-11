--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by c079222 on 3/17/2021, 11:01:23 AM --
--------------------------------------------------


prompt
prompt Creating function PWUST_GET_CUSTOMER_NUMBER
prompt ===========================================
prompt
create or replace function pwust_get_customer_number(vi_order_id pwust_sfwid_order_desc.order_id%type)
  return varchar2 as
------------------------------------------------------------------------------
-- This unpublished work, first created in 2021 and updated thereafter,
-- is protected under the copyright laws of the United States and of
-- other countries throughout the world.  Use, disassembly, reproduction,
-- distribution, etc. without the express written consent of United
-- Technologies Corporation is prohibited.  Copyright United Technologies
-- Corporation 2021.  All rights reserved.  Information contained herein
-- is proprietary and confidential and improper use or disclosure may
-- result in civil and penal sanctions.
--
-- Function: pwust_get_customer_number
--
-- Created: 2021-03-17
--
-- Author:  David Miron
--
-- Revision History
--
-- Date      	Who       Description
-- ----------	-------	  ----------------------------------
-- 2021-03-17 D.Miron   Defect 1882 - Initial Release
-------------------------------------------------------------------------------
  v_customer_no pwust_int_sap_sm_order.customer_number%type;
begin
  begin
    select cc.customer_number
      into v_customer_no
      from sfmfg.pwust_int_sap_sm_order cc, sfmfg.pwust_sfwid_order_desc tt
     where tt.order_id = vi_order_id
       and cc.sales_order = tt.sales_order
       and rownum = 1;
  exception
    when no_data_found then
      v_customer_no := null;
  end;

  return v_customer_no;

end pwust_get_customer_number;
/
grant execute on PWUST_GET_CUSTOMER_NUMBER to PRATT_READ_ONLY;
grant execute on PWUST_GET_CUSTOMER_NUMBER to SF$CONNECTION_POOL;



