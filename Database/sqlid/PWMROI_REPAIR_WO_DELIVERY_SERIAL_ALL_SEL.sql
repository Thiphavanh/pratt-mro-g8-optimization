
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_REPAIR_WO_DELIVERY_SERIAL_ALL_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_REPAIR_WO_DELIVERY_SERIAL_ALL_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_REPAIR_WO_DELIVERY_SERIAL_ALL_SEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='select open +delivered serials of selected work order;Defect1611, defect 1885, DEFECT 1886';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT S.SERIAL_NO,s.serial_id,S.SERIAL_STATUS,SC.SCRAP_TYPE,D.ENGINE_MANUAL,
CASE 
WHEN S.SERIAL_STATUS = ''SCRAP''
THEN (SELECT L2.TIME_STAMP
FROM SFWID_SERIAL_DESC S2,PWMROI_SERIAL_DELIVERY D2,PW_S_T314_DISPOSITION_LOG L2,PWMROI_SERIAL_SCRAP SC2
WHERE  S2.ORDER_ID = D2.ORDER_ID(+)
AND    S2.SERIAL_ID = D2.SERIAL_ID(+)
AND    S2.ORDER_ID =  SC2.ORDER_ID(+)
AND    S2.SERIAL_ID = SC2.SERIAL_ID(+)
AND    nvl(sc2.INTERFACE_TRANSACTION_ID,d2.INTERFACE_TRANSACTION_ID) = L2.MESSAGE_ID(+)
AND    S2.ORDER_ID in (SELECT T.ORDER_ID
FROM SFWID_ORDER_DESC T,PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND ((T.UCF_ORDER_VCH15 IS NULL AND NVL('''',NULL) IS NULL) OR
T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15,NULL))
AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12
and l2.message_id is not null
and sc2.interface_transaction_id != ''X''
)
AND S2.SERIAL_NO NOT LIKE ''%-SPLIT%''
and s2.serial_id = s.serial_id
and sc2.serial_id = sc.serial_id
) ELSE L.TIME_STAMP  
END AS INTDT,
CASE 
WHEN S.SERIAL_STATUS = ''SCRAP''
THEN (SELECT R3.TIME_STAMP
FROM SFWID_SERIAL_DESC S3,PWMROI_SERIAL_DELIVERY D3,PW_S_T314_DISPOSITION_LOG L3,PWMROI_SERIAL_SCRAP SC3,PW_S_T314_DISPOSITION_RPLY R3
WHERE S3.ORDER_ID = D3.ORDER_ID(+)
AND S3.SERIAL_ID = D3.SERIAL_ID(+)
AND S3.ORDER_ID =  SC3.ORDER_ID(+)
AND S3.SERIAL_ID = SC3.SERIAL_ID(+)
AND    L3.MESSAGE_ID = R3.MESSAGE_ID(+)
AND nvl(sc3.INTERFACE_TRANSACTION_ID,d3.INTERFACE_TRANSACTION_ID) = L3.MESSAGE_ID(+)
AND S3.ORDER_ID in (SELECT T.ORDER_ID
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND ((T.UCF_ORDER_VCH15 IS NULL AND NVL('''',NULL) IS NULL) OR
T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15,NULL))
AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12
and l3.time_stamp is not null
and sc3.interface_transaction_id != ''X''
)
AND    S3.SERIAL_NO NOT LIKE ''%-SPLIT%''
and s3.serial_id = s.serial_id
and sc3.serial_id = sc.serial_id
) ELSE L.TIME_STAMP  
END as INT_RESPDT,
CASE
WHEN S.SERIAL_STATUS = ''SCRAP''
THEN (SELECT sc4.error_message
FROM SFWID_SERIAL_DESC S4, PWMROI_SERIAL_DELIVERY D4,PW_S_T314_DISPOSITION_LOG L4,PW_S_T314_DISPOSITION_RPLY R4,PWMROI_SERIAL_SCRAP SC4
WHERE  S4.ORDER_ID = D4.ORDER_ID(+)
AND S4.SERIAL_ID = D4.SERIAL_ID(+)
AND S4.ORDER_ID =  SC4.ORDER_ID(+)
AND S4.SERIAL_ID = SC4.SERIAL_ID(+)
AND nvl(sc4.INTERFACE_TRANSACTION_ID,d4.INTERFACE_TRANSACTION_ID) = L4.MESSAGE_ID(+)
AND L4.MESSAGE_ID = R4.MESSAGE_ID(+)
AND S4.ORDER_ID in (SELECT T.ORDER_ID
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND ((T.UCF_ORDER_VCH15 IS NULL AND NVL('''',NULL) IS NULL) OR
T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15,NULL))
AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12
and l4.message_id is not null
and sc4.interface_transaction_id != ''X''
)
AND S4.SERIAL_NO NOT LIKE ''%-SPLIT%''
and s4.serial_id = s.serial_id
and sc4.serial_id = sc.serial_id
)
WHEN d.order_id is null  and sc.order_id  is null and r.time_stamp is null THEN null
WHEN (d.order_id is not null or sc.order_id is not null) and r.time_stamp is not null and (d.error_message is null and sc.error_message is null)
then ''SUCCESS''
WHEN d.order_id is not null and d.error_message is not null then d.error_message
WHEN sc.order_id is not null and sc.error_message is not null then sc.error_message
END  as INT_RESPONSE,
s.serial_id || '','' || s.serial_no AS serial,
NVL(SC.UPDT_USERID,D.UPDT_USERID) AS USERID,
T1.security_group
FROM SFWID_SERIAL_DESC S, PWMROI_SERIAL_DELIVERY D,PW_S_T314_DISPOSITION_LOG L,PW_S_T314_DISPOSITION_RPLY  R,PWMROI_SERIAL_SCRAP SC,SFWID_ORDER_DESC T1
WHERE  S.ORDER_ID = D.ORDER_ID(+)
AND    S.SERIAL_ID = D.SERIAL_ID(+)
AND    S.ORDER_ID =  SC.ORDER_ID(+)
AND    S.SERIAL_ID = SC.SERIAL_ID(+)
AND    nvl(sc.INTERFACE_TRANSACTION_ID,d.INTERFACE_TRANSACTION_ID) = L.MESSAGE_ID(+)
AND    L.MESSAGE_ID = R.MESSAGE_ID(+)
AND    S.ORDER_ID = :ORDER_ID
AND    S.ORDER_ID = T1.ORDER_ID
AND    S.SERIAL_NO NOT LIKE ''%-SPLIT%''';
begin
begin
  insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype,description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype,v_description,v_sql_text);
  commit;
exception when dup_val_on_index then
  begin
    update sfcore_sql_lib
    set sql_text=v_sql_text,updt_userid=user,time_stamp=sysdate,sql_id_displ = v_sql_id_displ,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,
description=v_description
    where sql_id=v_sql_id;
    commit;
  exception when others then
  	 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' update sfcore_sql_lib ');
  end;
when others then
  sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib ');
end;
begin
 if v_folder_id is not null then
  insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
  values(v_sql_id,v_folder_id,user,sysdate);
  commit;
 end if;
exception when others then 
 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib_folder ');
end;
end;
/

set define on

