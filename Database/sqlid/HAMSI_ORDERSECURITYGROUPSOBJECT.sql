set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_ORDERSECURITYGROUPSOBJECT';
v_sql_id_displ varchar2(85) :='HAMSI_OrderSecurityGroupsObject';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Order Security Group Objects';
v_sql_text varchar2(4000) :='SELECT OBJECT_TYPE, OBJECT_TAG, OBJECT_DESC, SECURITY_GROUP AS SECURITY_GROUPS, NULL AS SECURITY_GROUP
  FROM (SELECT B.OBJECT_TYPE, B.OBJECT_TAG, B.OBJECT_DESC, B.SECURITY_GROUP
   FROM SFFND_HTREF_WID_OPER_TEXT A,
        SFCORE_MM_OBJECT      B,
        SFCORE_MM_HTREF       C
  WHERE A.OBJECT_ID = C.OBJECT_ID
    AND B.OBJECT_ID = C.EMBEDDED_OBJECT_ID
    AND B.ACQUIRE_EXPRESS = ''N''
    AND B.SECURITY_GROUP IS NOT NULL
    AND A.ORDER_ID=:ORDER_ID
 UNION ALL
 SELECT ''PART'' AS OBJECT_TYPE,
        A.PART_NO AS OBJECT_TAG,
        A.PART_TITLE AS OBJECT_DESC,
        A.SECURITY_GROUP
   FROM SFWID_OPER_ITEMS A
  WHERE A.SECURITY_GROUP IS NOT NULL
    AND A.ORDER_ID=:ORDER_ID
 UNION ALL
 SELECT ''TOOL'' AS OBJECT_TYPE,
        A.TOOL_NO AS OBJECT_TAG,
        A.TOOL_TITLE AS OBJECT_DESC,
        A.SECURITY_GROUP
   FROM SFWID_OPER_TOOL A
  WHERE A.SECURITY_GROUP IS NOT NULL
    AND A.ORDER_ID=:ORDER_ID
 UNION ALL
 SELECT B.OBJECT_TYPE, B.OBJECT_TAG, B.OBJECT_DESC, B.SECURITY_GROUP
   FROM SFFND_HTREF_WID_OPER_TEXT A, SFCORE_MM_OBJECT B
  WHERE A.OBJECT_ID = B.OBJECT_ID
    AND B.ACQUIRE_EXPRESS = ''N''
    AND B.SECURITY_GROUP IS NOT NULL
    AND A.ORDER_ID=:ORDER_ID
 UNION ALL
 SELECT B.OBJECT_TYPE, B.OBJECT_TAG, B.OBJECT_DESC, B.SECURITY_GROUP
   FROM SFWID_OPER_DESC A, SFCORE_MM_OBJECT B
  WHERE A.STDOPER_OBJECT_ID = B.OBJECT_ID
 AND B.ACQUIRE_EXPRESS = ''N''
 AND B.SECURITY_GROUP IS NOT NULL
 AND A.ORDER_ID=:ORDER_ID
 UNION ALL
 SELECT B.OBJECT_TYPE, B.OBJECT_TAG, B.OBJECT_DESC, B.SECURITY_GROUP
   FROM SFFND_HTREF_ORDER_TEXT A,
  SFCORE_MM_OBJECT      B,
  SFCORE_MM_HTREF       C
  WHERE A.OBJECT_ID = C.OBJECT_ID
    AND B.OBJECT_ID = C.EMBEDDED_OBJECT_ID
    AND B.ACQUIRE_EXPRESS = ''N''
    AND B.SECURITY_GROUP IS NOT NULL
AND A.ORDER_ID=:ORDER_ID
UNION ALL
SELECT B.OBJECT_TYPE, B.OBJECT_TAG, B.OBJECT_DESC, B.SECURITY_GROUP
   FROM SFFND_HTREF_ORDER_TEXT A, SFCORE_MM_OBJECT B
  WHERE A.OBJECT_ID = B.OBJECT_ID
    AND B.ACQUIRE_EXPRESS = ''N''
    AND B.SECURITY_GROUP IS NOT NULL
    AND A.ORDER_ID=:ORDER_ID
) X
 WHERE 1 = 1 /*+ @Filter */
 ORDER BY X.OBJECT_TYPE';
begin
begin
insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype, description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype, v_description,replace(v_sql_text,chr(10),chr(13)||chr(10)));
commit;
exception when dup_val_on_index then
  update sfcore_sql_lib
  set sql_text=replace(v_sql_text,chr(10),chr(13)||chr(10)),updt_userid=user,time_stamp=sysdate,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,  
description=v_description
  where sql_id=v_sql_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
values(v_sql_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on

