DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
SELECT 'DUMMY' INTO V_DUMMY FROM USER_TABLES WHERE TABLE_NAME = 'PWUST_REPAIR_ORDER_T308_DATA';
EXCEPTION
WHEN NO_DATA_FOUND THEN
  
EXECUTE IMMEDIATE ('create table PWUST_REPAIR_ORDER_T308_DATA(
  order_id       VARCHAR2(80) not null,
  oper_part_flag VARCHAR2(10) not null,
  oper_no        VARCHAR2(10) not null,
  updt_userid    VARCHAR2(50) default USER not null,
  time_stamp     DATE default SYSDATE not null,
  oper_center_id VARCHAR2(40),
  part_no        VARCHAR2(50) not null,
  plnd_item_qty  NUMBER
)tablespace SFWID_DATA');

EXECUTE IMMEDIATE ('alter table PWUST_REPAIR_ORDER_T308_DATA  add constraint PWUST_REPAIR_ORDER_T308_PK primary key (ORDER_ID, OPER_PART_FLAG, OPER_NO, PART_NO)  using index  tablespace SFUPGRADE');
EXECUTE IMMEDIATE ('GRANT SELECT ON PWUST_REPAIR_ORDER_T308_DATA TO PRATT_READ_ONLY');
EXECUTE IMMEDIATE ('grant select, insert, update, delete on PWUST_REPAIR_ORDER_T308_DATA to SF$CONNECTION_POOL');
end;
/
