create or replace trigger pwmroi_subj_auth_upd_trg after update on sfor_sfpl_plan_subject
FOR EACH ROW
DECLARE
 v_plan_rev number;
  BEGIN
    if :new.subject_rev > 1 and :new.subject_rev != :old.subject_rev then
      select max(plan_revision) into v_plan_rev
           from sfpl_plan_v
           where plan_id =  :NEW.PLAN_ID and plan_updt_no = :NEW.PLAN_UPDT_NO;

   for a in (select  subject_description,authority_type,authority_detail,
             manual_no,manual_rev,manual_section,manual_type,manual_provider,sb_part_no,authority_rev,authority_rev_date,seqno --defect 1964
        from PWMROI_PLAN_SUBJECT_AUTHORITY
        where plan_id = :NEW.PLAN_ID and plan_revision = v_plan_rev and subject_no = :new.subject_no and
                        subject_rev = :new.subject_rev -1)
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
    authority_rev_date,
    SEQNO)  --DEFECT 1964

     (SELECT :new.plan_id,v_plan_rev,:new.subject_no,:new.subject_rev,a.subject_description,a.authority_type,
              a.authority_detail,a.manual_no,a.manual_rev,a.manual_provider,a.manual_type,a.manual_section,
              a.sb_part_no,a.authority_rev,a.authority_rev_date,PWMROI_PLAN_SUBJ_AUTH_SEQ.nextval FROM DUAL);  --defect 1964
  END LOOP;
  DELETE FROM PWMROI_PLAN_SUBJECT_AUTHORITY WHERE PLAN_ID = :NEW.PLAN_ID AND PLAN_REVISION = V_PLAN_REV AND
              SUBJECT_NO = :NEW.SUBJECT_NO AND SUBJECT_REV = :OLD.SUBJECT_REV;
  end if;
END;
/