EXEC SFCORE_PRIVS_INS('SFMFG','PW_DELIVERY_STATUS_UPDATE','Allows user to reverse Delivered Serial Numbers', NULL); 
exec sfcore_roles_priv_ins('SFMFG','SF$SUPERUSER','PW_DELIVERY_STATUS_UPDATE');
grant select on PWUST_COCKPIT_HIST_SEQ to SF$CONNECTION_POOL;
grant select on PWUST_DELIVERY_COCKPIT_HIST to PRATT_READ_ONLY; 
grant select, insert, update, delete on PWUST_DELIVERY_COCKPIT_HIST to PRATT_WRITE_ONLY;