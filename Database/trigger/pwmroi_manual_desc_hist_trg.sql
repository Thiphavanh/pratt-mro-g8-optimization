create or replace trigger sfmfg.pwmroi_manual_desc_hist_trg after update or insert or delete on pwmroi_manual_desc
FOR EACH ROW
  BEGIN
  IF INSERTING  THEN
    INSERT INTO PWMROI_MANUAL_DESC_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,MANUAL_NO,MANUAL_REV, OLD_MANUAL_TRANSMITTAL_NO, NEW_MANUAL_TRANSMITTAL_NO,
     OLD_MANUAL_REMARKS, NEW_MANUAL_REMARKS,OLD_MANUAL_PROVIDER,NEW_MANUAL_PROVIDER,OLD_MANUAL_TITLE,NEW_MANUAL_TITLE,
     OLD_MANUAL_RELEASE_DATE,NEW_MANUAL_RELEASE_DATE,OLD_ENGINE_SERIES,NEW_ENGINE_SERIES,
     OLD_OBSOLETE_RECORD_FLAG,NEW_OBSOLETE_RECORD_FLAG)
     (SELECT SFDB_GUID(),'INSERTED',:NEW.UPDT_USERID,SYSDATE,:NEW.MANUAL_NO,:NEW.MANUAL_REV,NULL,:NEW.MANUAL_TRANSMITTAL_NO,
     NULL,:NEW.MANUAL_REMARKS,NULL,:NEW.MANUAL_PROVIDER,NULL,:NEW.MANUAL_TITLE,NULL,:NEW.MANUAL_RELEASE_DATE,
     NULL,:NEW.ENGINE_SERIES,NULL,'N' FROM DUAL);
  end if;
    IF UPDATING  THEN
    INSERT INTO PWMROI_MANUAL_DESC_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,MANUAL_NO,MANUAL_REV, OLD_MANUAL_TRANSMITTAL_NO, NEW_MANUAL_TRANSMITTAL_NO,
     OLD_MANUAL_REMARKS, NEW_MANUAL_REMARKS,OLD_MANUAL_PROVIDER,NEW_MANUAL_PROVIDER,OLD_MANUAL_TITLE,NEW_MANUAL_TITLE,
     OLD_MANUAL_RELEASE_DATE,NEW_MANUAL_RELEASE_DATE,OLD_ENGINE_SERIES,NEW_ENGINE_SERIES,
     OLD_OBSOLETE_RECORD_FLAG,NEW_OBSOLETE_RECORD_FLAG)
     (SELECT SFDB_GUID(),'UPDATED',:NEW.UPDT_USERID,SYSDATE,:NEW.MANUAL_NO,:NEW.MANUAL_REV,
     :OLD.MANUAL_TRANSMITTAL_NO,:NEW.MANUAL_TRANSMITTAL_NO,:OLD.MANUAL_REMARKS,:NEW.MANUAL_REMARKS,
     :OLD.MANUAL_PROVIDER,:NEW.MANUAL_PROVIDER,:OLD.MANUAL_TITLE,:NEW.MANUAL_TITLE,:OLD.MANUAL_RELEASE_DATE,:NEW.MANUAL_RELEASE_DATE,
     :OLD.ENGINE_SERIES,:NEW.ENGINE_SERIES,:OLD.OBSOLETE_RECORD_FLAG,:NEW.OBSOLETE_RECORD_FLAG FROM DUAL);
  end if;
  IF DELETING THEN 
     INSERT INTO PWMROI_MANUAL_DESC_HIST
    (HIST_ID,HIST_ACTION,HIST_UPDT_USERID, HIST_TIME_STAMP,MANUAL_NO,MANUAL_REV)
    (SELECT SFDB_GUID(),'DELETED',:NEW.UPDT_USERID,SYSDATE,:NEW.MANUAL_NO,:NEW.MANUAL_REV FROM DUAL);
  END IF;
END;
/
