set echo on
set define off

prompt ---------------------------------------;
prompt Executing ... insert PW_GENERAL_PROCEDURES.sql
prompt ---------------------------------------;

EXEC SFCORE_PRIVS_INS('SFMFG','PW_GENERAL_PROCEDURES','Allows user to view GENERAL PROCEDURES dispatch',null);


set define on