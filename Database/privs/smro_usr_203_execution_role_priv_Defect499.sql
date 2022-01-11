
--Remove priv from role
EXEC sfcore_roles_priv_del('SFMFG','SF$SYSTEM_ADMINISTRATION','SECURITY_GROUP_ASSIGNMENT');
EXEC sfcore_roles_priv_del('SFMFG','SF$USER_ADMINISTRATION','SECURITY_GROUP_ASSIGNMENT');

EXEC sfcore_roles_priv_del('SFMFG','SF$6100_TRADESPERSON','ORDER_CREATE');
EXEC sfcore_roles_priv_del('SFMFG','SF$6100_TRADESPERSON','SFOR_ORDER_SUBJECT_INCLUDE');
EXEC sfcore_roles_priv_del('SFMFG','SF$6100_TRADESPERSON','SERIAL_LOT_CHG');
EXEC sfcore_roles_priv_del('SFMFG','SF$6100_TRADESPERSON','WORK_SCOPE_AUTHORIZATION');
EXEC sfcore_roles_priv_del('SFMFG','SF$4100_LEADMAN','SFOR_ORDER_SUBJECT_INCLUDE');


--Assign prv to role

EXEC SFCORE_ROLES_PRIV_INS('SFMFG','SF$6100_USER_ADMIN', 'SYSADMIN_VIEW');  
EXEC SFCORE_ROLES_PRIV_INS('SFMFG','SF$6150_USER_ADMIN', 'SYSADMIN_VIEW');  