ALTER TABLE 
   SFMFG.PW_S_T314_DISPOSITION_LOG
MODIFY 
   ( 
   MESSAGE_ID         VARCHAR2(51)
   )
;


ALTER TABLE 
   SFMFG.PW_S_T314_DISPOSITION_RPLY
MODIFY
   (
   MESSAGE_ID          VARCHAR2(51),
   ORIGINAL_MESSAGE_ID VARCHAR2(51)
   )
;