set echo on
set define off

prompt ---------------------------------------;
prompt Executing ... insertPW_DATCOL_UPDATE_PRIV.sql
prompt ---------------------------------------;

EXEC SFCORE_PRIVS_INS('SFMFG','PW_PART_ADD_EDIT_DEL','Insert/Update/Delete Items into Item List',null);


set define on