-- Create new custom table via sql
create table SFWID_BUYOFF_HIST_PW as
SELECT SFWID_BUYOFF_HIST_PW_SEQ.Nextval as BUYOFF_HIST_pk,
       order_id,
       BUYOFF_ID,
       OPER_NO,
       OPER_KEY,
       STEP_NO,
       STEP_KEY,
       BUYOFF_TYPE,
       BUYOFF_TITLE,
       SERIAL_NO,
       BUYOFF_STATUS,
       TIME_STAMP,
       substr(USER_NAME,1,30) as USER_NAME,
       UPDT_USERID,
       PERCENT_COMPLETE,
       COMPLETE_QTY,
       BUYOFF_CERT,
       COMMENTS,
       OPTIONAL_FLAG,
       UCF_SRLOPBUYOFF_VCH4000_1 as signoffs
  FROM (SELECT a.order_id,
               A.BUYOFF_ID,
               A.OPER_NO,
               A.OPER_KEY,
               A.STEP_NO,
               A.STEP_KEY,
               A.BUYOFF_TYPE,
               A.BUYOFF_TITLE,
               SERIAL_NO,
               CASE  WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P'      THEN 'PARTIAL'
                     WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL     THEN B.BUYOFF_STATUS
                     WHEN B.UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL' THEN B.BUYOFF_STATUS
                     ELSE 'Closed-' || B.UCF_SRLOPBUYOFF_VCH1
               END AS BUYOFF_STATUS,
               B.TIME_STAMP,
               SFFND_USER_NAME_GET(B.UPDT_USERID) AS USER_NAME,
               B.UPDT_USERID,
               (TO_CHAR(CAST(b.PERCENT_COMPLETE AS FLOAT) / 100)) AS PERCENT_COMPLETE,
               TO_CHAR(CAST(B.COMPLETE_QTY AS INTEGER)) AS COMPLETE_QTY,
               A.BUYOFF_CERT,
               B.COMMENTS,
               A.OPTIONAL_FLAG,
               UCF_SRLOPBUYOFF_VCH4000_1
          FROM SFWID_OPER_BUYOFF        A,
               SFWID_SERIAL_OPER_BUYOFF B,
               SFWID_SERIAL_DESC        S
         WHERE A.ORDER_ID = B.ORDER_ID
           AND A.OPER_KEY = B.OPER_KEY
           AND A.STEP_KEY = B.STEP_KEY
           AND A.BUYOFF_ID = B.BUYOFF_ID
           AND B.ORDER_ID = S.ORDER_ID
           AND B.SERIAL_ID = S.SERIAL_ID
           AND B.LOT_ID = S.LOT_ID
           AND ((B.BUYOFF_STATUS NOT IN ('PENDING', 'OPEN')) OR  (NVL(B.UCF_SRLOPBUYOFF_FLAG1, 'x') = 'P'))
        UNION
        SELECT a.order_id,
               A.BUYOFF_ID,
               A.OPER_NO,
               A.OPER_KEY,
               A.STEP_NO,
               A.STEP_KEY,
               A.BUYOFF_TYPE,
               A.BUYOFF_TITLE,
               SERIAL_NO,
               CASE  WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P' THEN 'PARTIAL'
                     WHEN B.UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL' THEN B.BUYOFF_STATUS
                     WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL THEN B.BUYOFF_STATUS
                     ELSE 'Closed-' || B.UCF_SRLOPBUYOFF_VCH1
               END AS BUYOFF_STATUS,
               B.TIME_STAMP,
               SFFND_USER_NAME_GET(B.UPDT_USERID) AS USER_NAME,
               B.UPDT_USERID,
               TO_CHAR(CAST(PERCENT_COMPLETE AS FLOAT) / 100) AS PERCENT_COMPLETE,
               TO_CHAR(CAST(B.COMPLETE_QTY AS INTEGER)) AS COMPLETE_QTY,
               A.BUYOFF_CERT,
               B.COMMENTS,
               A.OPTIONAL_FLAG,
               UCF_SRLOPBUYOFF_VCH4000_1
          FROM SFWID_OPER_BUYOFF             A,
               SFWID_SERIAL_OPER_BUYOFF_HIST B,
               SFWID_SERIAL_DESC             S
         WHERE A.ORDER_ID = B.ORDER_ID
           AND A.OPER_KEY = B.OPER_KEY
           AND A.STEP_KEY = B.STEP_KEY
           AND A.BUYOFF_ID = B.BUYOFF_ID
           AND B.ORDER_ID = S.ORDER_ID
           AND B.SERIAL_ID = S.SERIAL_ID
           AND B.LOT_ID = S.LOT_ID
           AND ((B.BUYOFF_STATUS NOT IN ('PENDING', 'OPEN')) OR (NVL(B.UCF_SRLOPBUYOFF_FLAG1, 'x') = 'P')))


alter table SFWID_BUYOFF_HIST_PW
add constraint SFWID_BUYOFF_HIST_PW_PK primary key (BUYOFF_HIST_pk,order_id)
using index  tablespace SFHISTORY_INDEXES

grant select, insert, update, delete on SFWID_BUYOFF_HIST_PW to SF$CONNECTION_POOL;
grant select on SFWID_BUYOFF_HIST_PW to PRATT_READ_ONLY;  
