BEGIN
DECLARE
seqval NUMBER;
  BEGIN
  SELECT (MAX(seqno)+1)
  INTO seqval
  FROM sfmfg.PWMROI_PLAN_SUBJECT_AUTHORITY;

  execute immediate('CREATE SEQUENCE PWMROI_PLAN_SUBJ_AUTH_SEQ MINVALUE 0 START WITH '||seqval||' INCREMENT BY 1 CACHE 20');
  END;
END;
/