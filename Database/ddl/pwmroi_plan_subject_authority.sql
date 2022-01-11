ALTER TABLE PWMROI_PLAN_SUBJECT_AUTHORITY 
ADD SEQNO NUMBER NULL;
COMMIT;

CREATE SEQUENCE PWMROI_PLAN_SUBJ_AUTH_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
COMMIT;

grant select on sfmfg.PWMROI_PLAN_SUBJ_AUTH_SEQ to SF$CONNECTION_POOL;
commit;

UPDATE PWMROI_PLAN_SUBJECT_AUTHORITY
   SET SEQNO = PWMROI_PLAN_SUBJ_AUTH_SEQ.nextval;
COMMIT;

ALTER TABLE  PWMROI_PLAN_SUBJECT_AUTHORITY MODIFY SEQNO NOT NULL;
COMMIT;

ALTER TABLE PWMROI_PLAN_SUBJECT_AUTHORITY DROP CONSTRAINT PWMROI_PLAN_SUBJ_AUTH_PK;

ALTER TABLE PWMROI_PLAN_SUBJECT_AUTHORITY ADD CONSTRAINT PWMROI_PLAN_SUBJ_AUTH_PK PRIMARY KEY (PLAN_ID, PLAN_REVISION, SUBJECT_NO, SUBJECT_REV, AUTHORITY_TYPE, AUTHORITY_DETAIL, MANUAL_NO, MANUAL_REV,SEQNO);
COMMIT;

create or replace trigger pwmroi_plg_auth_ins_trg after insert on sfpl_plan_rev
FOR EACH ROW
DECLARE
  BEGIN
   for a in (select subject_no, subject_rev,subject_descrIption,authority_type,authority_detail,
             manual_no,manual_rev,manual_section,manual_type,manual_provider,sb_part_no,authority_rev,authority_rev_date,seqno  --defect 1890
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
    authority_rev_date,
    SEQNO) --defect 1890

     (SELECT :new.plan_id,:new.plan_revision,a.subject_no,a.subject_rev,a.subject_description,a.authority_type,
              a.authority_detail,a.manual_no,a.manual_rev,a.manual_provider,a.manual_type,a.manual_section,
              a.sb_part_no,a.authority_rev,a.authority_rev_date,PWMROI_PLAN_SUBJ_AUTH_SEQ.nextval FROM DUAL); --defect 1890
  END LOOP;
END;
commit;