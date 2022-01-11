--Defect 632
alter table pwust_sfwid_order_desc add  PW_ORDER_TYPE   VARCHAR2(40) not null;
alter table pwust_sfwid_order_desc add  SAP_SERIAL_NO   VARCHAR2(20);