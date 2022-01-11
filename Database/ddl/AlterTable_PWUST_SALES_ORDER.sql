-- Add/modify columns 
alter table PWUST_SALES_ORDER rename column MODULE_CODE_CODE to MODULE_CODE;
alter table PWUST_SALES_ORDER add DELETE_FLAG varchar2(1);