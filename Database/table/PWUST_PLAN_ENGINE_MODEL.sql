DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
SELECT 'DUMMY' INTO V_DUMMY FROM USER_TABLES WHERE TABLE_NAME = 'PWUST_PLAN_ENGINE_MODEL';
EXCEPTION
WHEN NO_DATA_FOUND THEN
EXECUTE IMMEDIATE ('CREATE TABLE PWUST_PLAN_ENGINE_MODEL
(
  PLAN_ID      VARCHAR2(40) NOT NULL,
  PLAN_UPDT_NO NUMBER DEFAULT 1 NOT NULL,
  ENGINE_MODEL VARCHAR2(20) NOT NULL,
  UPDT_USERID  VARCHAR2(30) DEFAULT USER NOT NULL,
  TIME_STAMP   DATE DEFAULT SYSDATE NOT NULL,
  LAST_ACTION  VARCHAR2(20) DEFAULT ''INSERTED''
)TABLESPACE SFPL_DATA');

EXECUTE IMMEDIATE ('ALTER TABLE PWUST_PLAN_ENGINE_MODEL   ADD CONSTRAINT PWUST_PLAN_ENGINE_MODEL_PK PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, ENGINE_MODEL)  USING INDEX   TABLESPACE SFPL_INDEXES');
EXECUTE IMMEDIATE ('ALTER TABLE PWUST_PLAN_ENGINE_MODEL  ADD CONSTRAINT PWUST_PLAN_ENGINE_MODEL_FK_1 FOREIGN KEY (PLAN_ID, PLAN_UPDT_NO)  REFERENCES SFPL_PLAN_DESC (PLAN_ID, PLAN_UPDT_NO) ON DELETE CASCADE');
EXECUTE IMMEDIATE ('GRANT SELECT ON PWUST_PLAN_ENGINE_MODEL TO PRATT_READ_ONLY');
EXECUTE IMMEDIATE ('GRANT SELECT, INSERT, UPDATE, DELETE ON PWUST_PLAN_ENGINE_MODEL TO SF$CONNECTION_POOL');
end;
/