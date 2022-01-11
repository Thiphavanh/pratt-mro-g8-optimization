-- Create the user
create user splunk identified by "solumina_2021splunk" default tablespace USERS temporary tablespace TEMP profile SERV_PROFILE;

-- Grant/Revoke object privileges 
grant select on sfmfg.sfcore_connection_desc_hist to splunk;
grant select on sfmfg.sfcore_login_attempt to splunk;
grant select on sfmfg.sfcore_connection_desc to splunk;
grant select on sfmfg.sfcore_error_log to splunk;
grant select on sfmfg.sfcore_error_log to splunk;
grant select on sfmfg.sfcore_user_roles to splunk;
grant select on sfmfg.sfcore_user_roles_hist to splunk;
grant select on sfmfg.sfcore_user_privs to splunk;
grant select on sfmfg.sfcore_user_privs_hist to splunk;

-- Splunk
grant connect to splunk;