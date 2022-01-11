CREATE OR REPLACE VIEW PWUST_OPER_BUYOFF_V
AS
SELECT A.ORDER_ID,
       A.BUYOFF_ID,
       A.OPER_NO,
       A.STEP_NO,
       A.BUYOFF_TYPE,
       A.BUYOFF_CERT,
       A.OPTIONAL_FLAG,
       A.CROSS_ORDER_FLAG,
       CASE WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN' ) AND  B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN  NULL 
            WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN') AND  NVL(B.UCF_SRLOPBUYOFF_FLAG1,'X') ='P'  AND B.UCF_SRLOPBUYOFF_VCH5 IS NOT NULL THEN B.UCF_SRLOPBUYOFF_VCH5
            ELSE B.UPDT_USERID
       END AS UPDT_USERID,
       CASE WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN' ) AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN  NULL
            WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN' ,'REOPEN') AND  NVL(B.UCF_SRLOPBUYOFF_FLAG1,'X') ='P' AND B.UCF_SRLOPBUYOFF_DATE1 IS NOT NULL  THEN B.UCF_SRLOPBUYOFF_DATE1
            ELSE B.TIME_STAMP
       END AS TIME_STAMP,
       CASE  WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN' ) AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL
             WHEN B.BUYOFF_STATUS IN ('PENDING', 'OPEN' ) AND  NVL(B.UCF_SRLOPBUYOFF_FLAG1,'X') ='P' AND  B.UCF_SRLOPBUYOFF_VCH5 IS NOT NULL THEN SFFND_USER_NAME_GET( B.UCF_SRLOPBUYOFF_VCH5)
             ELSE SFFND_USER_NAME_GET(B.UPDT_USERID)
       END AS USER_NAME,
      A.SLIDE_ID,
      A.SLIDE_EMBEDDED_REF_ID,
      A.BUYOFF_TITLE,
      CASE  WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P'      THEN  'PARTIAL'
            WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL     THEN  B.BUYOFF_STATUS
            WHEN B.UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL' THEN  B.BUYOFF_STATUS
            ELSE 'Closed-' || B.UCF_SRLOPBUYOFF_VCH1
       END AS BUYOFF_STATUS,
      A.OPER_KEY,
      A.STEP_KEY,
      b.COMMENTS
FROM SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF B, SFWID_SERIAL_DESC C 
WHERE A.ORDER_ID = B.ORDER_ID 
AND   A.OPER_KEY = B.OPER_KEY 
AND   A.STEP_KEY = B.STEP_KEY 
AND   A.BUYOFF_ID = B.BUYOFF_ID 
AND B.ORDER_ID = C.ORDER_ID 
AND B.LOT_ID = C.LOT_ID 
AND B.SERIAL_ID = C.SERIAL_ID 
AND C.SERIAL_STATUS NOT IN ( 'STOP' , 'SCRAP' )
AND (A.LAST_ACTION <> 'SUSPECTEDI' AND A.LAST_ACTION <> 'SUSPECTEDU');
/


GRANT SELECT ON PWUST_OPER_BUYOFF_V TO PRATT_READ_ONLY ;
GRANT SELECT ON PWUST_OPER_BUYOFF_V TO SF$CONNECTION_POOL;