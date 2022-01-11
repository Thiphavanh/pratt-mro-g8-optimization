begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into pwue_general_lookup select * from pwust_general_lookup;
   exception 
      when duplicate_entry then null;   
      when DUP_VAL_ON_INDEX then null;                                                                                                 
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into pwue_general_lookup (data_field, data_value, data_desc) values ('MaxMsgCount','50','Number of messages to get at once for T3 Processing');
   exception 
      when duplicate_entry then null;  
      when DUP_VAL_ON_INDEX then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into pwue_general_lookup (data_field, data_value, data_desc) values ('MaxMsgPerTransaction','50','Number of T3 messages per transaction');
   exception 
      when duplicate_entry then null; 
      when DUP_VAL_ON_INDEX then null;                                                                                                    
   end;
end;
/
commit;
/

drop table PWUST_GENERAL_LOOKUP;