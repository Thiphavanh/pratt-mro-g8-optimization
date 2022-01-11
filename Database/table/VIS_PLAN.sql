-- Drop constraint
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -02443);
  BEGIN
    EXECUTE IMMEDIATE 'alter table VIS_PLAN
                        drop constraint VIS_PLAN_UK1';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create primary and unique key constraints
alter table VIS_PLAN
  add constraint VIS_PLAN_UK1 unique (PART_NO);

-- 2019-05-22 Added new column for plan header text
ALTER TABLE VIS_PLAN
  ADD plan_hdr_text varchar2(4000);