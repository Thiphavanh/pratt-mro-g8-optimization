
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SEL_OVERHAUL_PARTS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SEL_OVERHAUL_PARTS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SEL_OVERHAUL_PARTS';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 63/890/772, defect 964/1034.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT distinct A.PART_NO, A.PLAN_TITLE as ITEM_NO_DESCRIPTION, '''' AS SECURITY_GROUP
    FROM SFPL_PLAN_V A,
         SFFND_DOC_TYPE_DEF B,
         SFOR_SFPL_PLAN_SUBJECT_PART C,
         SFPL_PLAN_EFF D,
         PWUST_PLAN_ENGINE_MODEL F
    WHERE A.DOC_TYPE = B.DOC_TYPE
    AND A.PLAN_TYPE = B.DOC_SUB_TYPE
    AND B.INSTRUCTIONS_TYPE = ''MRO-Part''
    AND A.REV_STATUS IN  (''PLAN RELEASED'',''PLAN COMPLETE'')
    AND D.EFF_TYPE = ''DATE''
    AND TRUNC(SYSDATE) BETWEEN TRUNC(D.EFF_FROM_DATE) AND TRUNC(NVL(D.EFF_THRU_DATE,TO_DATE(''01/01/9999'',''MM/DD/YYYY'')))
    AND A.PLAN_ID = C.PLAN_ID
    AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO
    AND A.PLAN_ID = D.PLAN_ID
    AND A.PLAN_VERSION = D.PLAN_VERSION
    AND A.PLAN_REVISION = D.PLAN_REVISION
    AND A.PLAN_ALTERATIONS = D.PLAN_ALTERATIONS
    AND A.PLAN_ID = F.PLAN_ID
    AND A.PLAN_UPDT_NO = F.PLAN_UPDT_NO
    AND A.DOC_TYPE = ''Process Plan''
    AND UPPER(A.PLAN_TYPE) = ''OVERHAUL''
    AND A.PROGRAM = :NEW_ENGINE_TYPE
    AND A.PLND_WORK_LOC = :NEW_SAP_WORK_LOC
    AND F.ENGINE_MODEL = :NEW_ENGINE_MODEL
    AND (:NEW_WORK_SCOPE IS NULL OR EXISTS (SELECT 1 FROM PWUST_PL_SUBJ_WORKSCOPE C1 WHERE C1.PLAN_ID = A.PLAN_ID AND C1.PLAN_UPDT_NO = A.PLAN_UPDT_NO AND C1.WORKSCOPE = :NEW_WORK_SCOPE))
    AND A.PART_CHG = ''N/A''
    AND (:NEW_CUSTOMER_NO IS NULL OR EXISTS (select 1
        from SFOR_SFPL_PLAN_SUBJECT c1,
             PWUST_PL_SUBJ_CUSTOMER  c2
        where  c1.plan_id = a.plan_id
        and    c1.plan_updt_no = a.plan_updt_no
        and    c1.plan_id = c2.plan_id
        and    c1.plan_updt_no =c2.plan_updt_no
        and    c1.subject_no = c2.subject_no
        and    c2.cust_id in (''ALL'',:NEW_CUSTOMER_NO)))

    and
        ((0 = (select count(*) from sfpl_item_desc_sec_grp h where h.part_no = a.part_no))
        or exists
        (select 1 from utasgi_user_sec_grp_xref i, sfpl_item_desc_sec_grp g
        where i.hr_loc = a.plnd_work_loc
        and g.part_no = a.part_no
        and g.security_group = i.security_group))

    ORDER BY A.PART_NO';
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

