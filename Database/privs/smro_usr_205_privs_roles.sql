
--Create ROLES 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6150_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$1700_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$4100_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$1180_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$1350_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_CERT_LEVEL','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
-----------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

-------


------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
--------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
-----------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
--------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
--------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
-------------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
---------------

------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


------Assign priv to Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_CERT_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_LEVEL_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_LEVEL_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_OVERINSPECTION_VIEW');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_USER_CERT_LEVEL','USER_OVERINSPECTION_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
