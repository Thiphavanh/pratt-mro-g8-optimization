
------Assign priv to PLANNER Roles 
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_del('SFMFG','SF$1700_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1180_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1350_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$4100_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6100_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6150_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$APU1_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$CEC2_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$ESA1_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$PRODUCTION_CONTROL_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$SHOPFLOOR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1180_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1350_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$APU1_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$CEC2_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$ESA1_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1700_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6100_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$4100_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6150_SR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1350_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$APU1_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$CEC2_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$ESA1_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1700_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6100_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$4100_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$6150_JR_PLANNER','PLG_AUTHORING');
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
      sfcore_roles_priv_del('SFMFG','SF$1180_JR_PLANNER','PLG_AUTHORING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/





