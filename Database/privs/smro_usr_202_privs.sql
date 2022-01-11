--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','STANDARD_NETWORK_UPDATE','STANDARD_NETWORK_UPDATE','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PLG_APPROVALS','PLG_APPROVALS','');
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
      sfcore_privs_ins('SFMFG','CAN_CREATE_PAAR_REQUEST','CAN_CREATE_PAAR_REQUEST','');
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
      sfcore_privs_ins('SFMFG','CAN_VIEW_ALL_PAAR_REQUESTS','CAN_VIEW_ALL_PAAR_REQUESTS','');
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
      sfcore_privs_ins('SFMFG','CAN_VIEW_PAAR_REQUEST','CAN_VIEW_PAAR_REQUEST','');
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
      sfcore_privs_ins('SFMFG','CAN_VIEW_PAAR_APPROVAL','CAN_VIEW_PAAR_APPROVAL','');
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
      sfcore_privs_ins('SFMFG','CAN_PROCESS_PAAR_APPROVAL','CAN_PROCESS_PAAR_APPROVAL','');
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
      sfcore_privs_ins('SFMFG','PAAR_APPROVAL-CAN_AUTHORIZE_ADDS','PAAR_APPROVAL-CAN_AUTHORIZE_ADDS','');
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
      sfcore_privs_ins('SFMFG','PAAR_APPROVAL-CAN_AUTHORIZE_DELS','PAAR_APPROVAL-CAN_AUTHORIZE_DELS','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

----------------
--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','1700_PW_EXPT_APPROVER','1700_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','6100_PW_EXPT_APPROVER','6100_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','6150_PW_EXPT_APPROVER','6150_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','4100_PW_EXPT_APPROVER','4100_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','1180_PW_EXPT_APPROVER','1180_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','1350_PW_EXPT_APPROVER','1350_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','APU1_PW_EXPT_APPROVER','APU1_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','CEC2_PW_EXPT_APPROVER','CEC2_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','ESA1_PW_EXPT_APPROVER','ESA1_PW_EXPT_APPROVER','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

