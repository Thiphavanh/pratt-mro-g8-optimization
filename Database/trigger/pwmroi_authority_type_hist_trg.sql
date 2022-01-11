create or replace trigger sfmfg.pwmroi_authority_type_hist_trg after update or insert or delete on pwmroi_authority_type
FOR EACH ROW
  BEGIN
  IF INSERTING  THEN
    INSERT INTO PWMROI_AUTHORITY_TYPE_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,AUTHORITY_TYPE, OLD_AUTHORITY_REV_RULE, NEW_AUTHORITY_REV_RULE,
     OLD_AUTHORITY_DATE_RULE, NEW_AUTHORITY_DATE_RULE,OLD_SB_PART_NO_RULE,NEW_SB_PART_NO_RULE,OLD_OBSOLETE_RECORD_FLAG, 
     NEW_OBSOLETE_RECORD_FLAG)
     (SELECT SFDB_GUID(),'INSERTED',USER,SYSDATE,:NEW.AUTHORITY_TYPE,NULL,:NEW.AUTHORITY_REV_RULE,
     NULL,:NEW.AUTHORITY_DATE_RULE,NULL,:NEW.SB_PART_NO_RULE,NULL,'N' FROM DUAL);
  end if;
  IF UPDATING  THEN
    INSERT INTO PWMROI_AUTHORITY_TYPE_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,AUTHORITY_TYPE, OLD_AUTHORITY_REV_RULE, NEW_AUTHORITY_REV_RULE,
     OLD_AUTHORITY_DATE_RULE, NEW_AUTHORITY_DATE_RULE,OLD_SB_PART_NO_RULE,NEW_SB_PART_NO_RULE,OLD_OBSOLETE_RECORD_FLAG, 
     NEW_OBSOLETE_RECORD_FLAG)
     (SELECT SFDB_GUID(),'INSERTED',USER,SYSDATE,:NEW.AUTHORITY_TYPE,:OLD.AUTHORITY_REV_RULE,:NEW.AUTHORITY_REV_RULE,
     :OLD.AUTHORITY_DATE_RULE,:NEW.AUTHORITY_DATE_RULE,:OLD.SB_PART_NO_RULE,:NEW.SB_PART_NO_RULE,:OLD.OBSOLETE_RECORD_FLAG,
     :NEW.OBSOLETE_RECORD_FLAG FROM DUAL);
  end if;
  IF DELETING THEN
        INSERT INTO PWMROI_AUTHORITY_TYPE_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,AUTHORITY_TYPE)
     (SELECT SFDB_GUID(),'DELETED',USER,SYSDATE,:OLD.AUTHORITY_TYPE FROM DUAL);
  END IF;   
END;
/

