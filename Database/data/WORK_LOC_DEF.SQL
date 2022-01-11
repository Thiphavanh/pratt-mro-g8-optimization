-- CREATE WORK_LOC_DEF
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'1180','OKC F119','Military Engines','1180','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'CEC2','CEC Plant 1','Commercial Engines and Aftermarket','CEC2','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'APU1','West Palm Beach','Commercial Engines and Aftermarket','APU1','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'4100','CEC Plant 2 (F100)','Military Engines','4100','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'6100','NZ','Commercial Engines and Aftermarket','6100','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'6150','TEC','Commercial Engines and Aftermarket','6150','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'1700','OKC F117','Military Engines','1700','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'1350','OKC F135','Military Engines','1350','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      INSERT INTO SFFND_WORK_LOC_DEF (LOCATION_ID, WORK_LOC, LOC_TITLE, UCF_WORK_LOC_VCH1, UCF_WORK_LOC_VCH2, COMPANY_ID) VALUES (SFDB_GUID,'ESA1','ESA','Commercial Engines and Aftermarket','ESA1','MFI_1');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
