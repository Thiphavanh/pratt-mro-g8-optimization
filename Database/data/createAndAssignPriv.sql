-- Add new priv
 EXEC SFCORE_PRIVS_INS(:@userid,
                        'PW_USER_WORK_LOC_CHG',
                        'Privilege for Assigning Work Locations',
                        'SFFND');
 
-- Assign priv to SUPERUSER
 EXEC SFCORE_ROLES_PRIV_INS(:@userid,'SF$SUPERUSER','PW_USER_WORK_LOC_CHG');
   
  