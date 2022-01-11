insert into sfcore_privs
  (PRIV, PRIV_DESC, STYPE)
values
  ('ASSIGN_SUPERUSER', 'To assign superuser', null);

insert into sfcore_app_role_privs
  (ROLE, PRIV)
values
  ('SF$SUPERUSER', 'ASSIGN_SUPERUSER');

insert into sfcore_user_privs
  (userid, priv)
  (select distinct userid, 'ASSIGN_SUPERUSER'
     from SFCORE_USER_ROLES
    where role = 'SF$SUPERUSER');

commit;
