
--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DATA_CONTROL_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$6150_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1700_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$4100_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1180_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$1350_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$APU1_PROCESS_ENGINEER','PROCESS_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','TECH_DATA_ENGINEER','');
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
      SFCORE_ROLES_INS('SFMFG','SF$ESA1_PROCESS_ENGINEER','PROCESS_ENGINEER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
---------------------------------------------

------Assign priv to Roles SF$1700_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1700_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
------


------Assign priv to Roles SF$1180_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
------


------Assign priv to Roles SF$1350_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
----

------Assign priv to Roles SF$4100_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
----

------Assign priv to Roles SF$6150_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
-----

------Assign priv to Roles SF$APU1_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
----

------Assign priv to Roles SF$ESA1_PROCESS_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_PROCESS_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
----

------Assign priv to Roles SF$6100_DATA_CONTROL_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
----

------Assign priv to Roles SF$CEC2_TECH_DATA_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CA_REQUEST_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CA_VERIFY');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CHANGE_REQUEST_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CHANGE_REQUEST_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','COMM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DAT_COL_VARIABLE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DESCRIPTION');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISC_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_COMM');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_TOLL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','@OldLockedObjectsView');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','EBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','EBOM_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','EDIT_FILE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','FILE_SUPECEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','IMPACT_ANALYSIS');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','INSPECTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','INSP_DEF_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ITEM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','J2EE_CONNECT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MAIN_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MANDATE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MBOM_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MBOM_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ME_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_ALT_INITIATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_HOLD_CLOSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_HOLD_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_NOTE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_NOTE_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_NOTE_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLANNER_HOMEPAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_DELETE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLG_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLIST_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLIST_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PRODUCTION_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PWP_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RELEASE_PACKAGE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RELEASE_PACKAGE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RESOLVED (ENG)');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SERIAL_LOT_CHG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFSQA_DISPATCH_ALL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFSQA_INSP_ORDER_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFSQA_INSP_PLAN_CREATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFSQA_INSP_STEP_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SLIDE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SLIDE_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SLIDE_EDIT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SLIDE_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SOLUMINA_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','STATUS_CHANGE_ORDER_NOTES');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','STDDC_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','STDOTER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','STDOTER_SUPERCEDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','STDTEXT_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SUBMITTED');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','UNIT_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','WORK_SCOPE_AUTHORIZATION');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','EXPORT_DISPATCH');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','INVENTORY_CONTROL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MES_REPORTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MGR_REV');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','OPER_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','OPER_AUTHORING2');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLG_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','REPORT_BROWSE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','REPORT_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RPT_BACKLOG_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RPT_CYCLETIME_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','RPT_SCHEDPERF_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFSQA_INSP_PLAN_APPROVE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISC_ITEM_CANCEL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPLAY_RELATED_INSP_WORK_ORDER');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ENG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','HOLD_CLOSE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','HOLD_UPDATE_REUSED_SERIAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CAN_CREATE_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CAN_VIEW_ALL_PAAR_REQUESTS');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CAN_VIEW_PAAR_REQUEST');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CAN_VIEW_PAAR_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ACTION_PLAN');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CLOSE_INSP_ORDER-LIEN_HOLD');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MFG_UPDATE_SCHEDULE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','UPDATE_WORK_ORDER_PACKAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_ALTPART_FROMITEMMASTER');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','TOOL_VIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','UPDATE_WORK_ORDER_PACHAGE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','BUYOFF_OVERRRIDE_MFG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','ORDER_IE_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLG_REVIEW');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','CA_FACILITATOR');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','DISPATCH_SUPP_MGMT');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_DOCSUBTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_MACHINE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_MODEL_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_NOTE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_PARTTYPE_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','LOOKUP_VENDOR_UPDATE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','MI_CONFIG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','QA_APPROVAL');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLG_APPROVALS');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','EXPT_ACCEPTANCE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','PLAN_QA_AUTHORING');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','SFOR_ORDER_SUBJECT_INCLUDE');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_TECH_DATA_ENGINEER','IMPLEMENTING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

