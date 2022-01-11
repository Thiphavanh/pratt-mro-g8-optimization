create or replace trigger sfmfg.pwmroi_wid_auth_ins_trg after  insert on sfwid_order_desc
FOR EACH ROW
DECLARE
 v_authority varchar2(2000);
  BEGIN
   for a in (select subject_no, subject_rev,subject_descrIption,authority_type,authority_detail,
             manual_no,manual_rev,manual_section,manual_type,manual_provider,sb_part_no,authority_rev,authority_rev_date
        from PWMROI_PLAN_SUBJECT_AUTHORITY 
        where plan_id = :NEW.PLAN_ID and plan_revision = :NEW.PLAN_REVISION ) 
        LOOP             
             select authority into v_authority from sfor_sfpl_plan_subject where
                    plan_id = :NEW.PLAN_ID and plan_updt_no = :new.plan_updt_no and subject_no = a.subject_no;
                    
      INSERT INTO PWMROI_ORDER_SUBJECT_AUTHORITY
    (order_id, 
    subject_no, 
    subject_rev, 
    subject_description, 
    authority_type, 
    authority_detail, 
    manual_no, 
    manual_rev, 
    manual_provider, 
    manual_type, 
    manual_section, 
    sb_part_no, 
    authority_rev, 
    authority_rev_date,
    authority,
    arc_authority)
    
     (SELECT :new.order_id,a.subject_no,a.subject_rev,a.subject_description,a.authority_type,
              a.authority_detail,a.manual_no,a.manual_rev,a.manual_provider,a.manual_type,a.manual_section,
              a.sb_part_no,a.authority_rev,a.authority_rev_date,v_authority,
              a.authority_type||a.manual_section||a.authority_detail FROM DUAL);
  END LOOP;
END;
/

