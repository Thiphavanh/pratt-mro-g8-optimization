create or replace trigger sfmfg.pwmroi_plg_auth_ins_trg after insert on sfpl_plan_rev
FOR EACH ROW
DECLARE
  BEGIN
   for a in (select subject_no, subject_rev,subject_descrIption,authority_type,authority_detail,
             manual_no,manual_rev,manual_section,manual_type,manual_provider,sb_part_no,authority_rev,authority_rev_date
        from PWMROI_PLAN_SUBJECT_AUTHORITY 
        where plan_id = :NEW.PLAN_ID and plan_revision = :NEW.PLAN_REVISION - 1) 
        LOOP             
                    
      INSERT INTO PWMROI_PLAN_SUBJECT_AUTHORITY
    (plan_id, 
    plan_revision,
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
    authority_rev_date)
    
     (SELECT :new.plan_id,:new.plan_revision,a.subject_no,a.subject_rev,a.subject_description,a.authority_type,
              a.authority_detail,a.manual_no,a.manual_rev,a.manual_provider,a.manual_type,a.manual_section,
              a.sb_part_no,a.authority_rev,a.authority_rev_date FROM DUAL);
  END LOOP;
END;
/

