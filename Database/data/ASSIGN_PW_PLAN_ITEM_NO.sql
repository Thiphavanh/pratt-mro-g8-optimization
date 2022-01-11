--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_DCE_APPROVER','6100_DCE_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PW_PLAN_ITEM_NO','Priv needed to see/use create plan item no','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

-- Assign Created Priv to Role
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_DCE_APPROVER','PW_PLAN_ITEM_NO');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

--Assign Created Priv to User who has the Role
insert into sfcore_user_privs
  (userid, priv)
  (select distinct userid, 'PW_PLAN_ITEM_NO'
     from SFCORE_USER_ROLES
    where role = 'SF$6100_DCE_APPROVER');

commit;
