-- Create New Privs PWUST_STD_VIEW
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_STD_VIEW', 'Superuser-unique priv for viewing standard objects', null);
EXEC SFCORE_ROLES_PRIV_INS('SFMFG', 'SF$SUPERUSER', 'PWUST_STD_VIEW');

-- defect 577 create new priv PWUST_LONOTE_BYPASS and new role SF$LONOTE_BYPASS
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_LONOTE_BYPASS', 'BY PASS LO notification check', 'SFWID');
EXEC SFCORE_ROLES_INS('SFMFG','SF$LONOTE_BYPASS','BY PASS LO notification check', 'SFWID');
EXEC SFCORE_ROLES_PRIV_INS('SFMFG', 'SF$LONOTE_BYPASS', 'PWUST_LONOTE_BYPASS');

-- defect 468 create new priv for sub and supplemental orders 
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUB_ORDER_CREATE',       'Create PW sub orders',           'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUB_ORDER_START_ALT',    'Start PW sub order alteration',  'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUB_ORDER_REL',          'Release PW sub order',           'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUB_ORDER_DEL',          'Delete PW sub order',            'SFWID');
--
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUPPLEM_ORDER_CREATE',       'Create PW Supplemental orders', 'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUPPLEM_ORDER_ALT_ST',       'Start PW Supplemental order alteration', 'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUPPLEM_ORDER_REL',          'Release PW Supplemental order',          'SFWID');
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_SUPPLEM_ORDER_DEL',          'Delete PW Supplemental order',           'SFWID');

-- Defect 851: Create New Priv PW_SECURITY_MENU and assign to SUPERUSER.
EXEC SFCORE_PRIVS_INS('SFMFG','PW_SECURITY_MENU', 'Superuser-unique priv for viewing Users Security Groups', null);
EXEC SFCORE_ROLES_PRIV_INS('SFMFG', 'SF$SUPERUSER', 'PW_SECURITY_MENU');

-- Bahram J SMRO_TR_308, added role check to bypass t308 interface
EXEC SFCORE_PRIVS_INS('SFMFG','PWUST_T308_BYPASS', 'BY PASS T308 INTERFACE FOR REPAIR ORDER REL', 'SFWID');
EXEC SFCORE_ROLES_INS('SFMFG','SF$T308_BYPASS','BY PASS T308 INTERFACE', 'SFWID');
EXEC SFCORE_ROLES_PRIV_INS('SFMFG', 'SF$T308_BYPASS', 'PWUST_T308_BYPASS');

commit;