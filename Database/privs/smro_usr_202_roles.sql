---Delete ROLES

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_del('SFMFG','SF$1700_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$6100_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$6150_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$4100_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$1180_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$1350_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$APU1_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$CEC2_PLG_APPR_EXP');
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
      sfcore_roles_del('SFMFG','SF$ESA1_PLG_APPR_EXP');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_MECHANICAL_ENGINEER','MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_MECHANICAL_ENGINEER','MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_MECHANICAL_ENGINEER','MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_MECHANICAL_ENGINEER','SF$MECHANICAL_ENGINEER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
------------------------------------
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_ENGINEER','SF$ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_ENGINEER','SF$ENGINEER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/



---------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_SR_PLANNER','SR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_SR_PLANNER','SR_PLANNER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


----------------------------
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_PLANNER','PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_PLANNER','PLANNER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

---------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_JR_PLANNER','JR_PLANNER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_JR_PLANNER','JR_PLANNER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


-----------------------------------------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_QUALITY_ENGINEER','QUALITY_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_QUALITY_ENGINEER','QUALITY_ENGINEER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

--------------------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_PROCESS_PLAN_MAINTENANCE','PROCESS_PLAN_MAINTENANCE','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
---------------------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_ROUTER_TAILOR','ROUTER_TAILOR','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_ROUTER_TAILOR','ROUTER_TAILOR','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
-------------------------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_TECH_DATA_MAINTENANCE','TECH_DATA_MAINTENANCE','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/




-----------

--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_PLG_APPR_PLAN','SF$PLG_APPR_PLAN','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

-------------


--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_PLG_APPR_EXPT','SF$PLG_APPR_EXPT','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$PAAR_APPROVER','SF$PAAR_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/


--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$1700_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6100_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_QA_APPROVER','SF$QA_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_QA_APPROVER','SF$QA_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$1700_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6100_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_ME_APPROVER','SF$ME_APPROVER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_ME_APPROVER','SF$ME_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/











