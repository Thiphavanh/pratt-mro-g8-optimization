set echo on
set define off

prompt ---------------------------------------;
prompt Executing ... insertPW_DATCOL_UPDATE_PRIV.sql
prompt ---------------------------------------;

EXEC SFCORE_PRIVS_INS('SFMFG','PW_DATCOL_UPDATE','Allows user to edit completed Data Collection(s)',null);


set define on