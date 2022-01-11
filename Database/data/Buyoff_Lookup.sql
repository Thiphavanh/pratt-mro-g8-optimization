---  Buyoff Lookup List
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','MFG','MECH/Technician/Work By','1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','INSP','INSP/Auth. Sig./QC','2');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','QA','QA INSP/OP INSP/QCA','3');   
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','CUST','BINSP','4');   
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','MFG2','Operator','5');   
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','TECH','Welder','6');   
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO PWUST_GENERAL_LOOKUP (DATA_FIELD, DATA_VALUE, DATA_DESC, DATA_VALUE_1)
	                           VALUES ('BUYOFF','TECH2','Engineering','7');   
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/







