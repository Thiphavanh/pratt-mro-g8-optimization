begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into SFWID_ORDER_TYPE_DEF
      (ORDER_TYPE, DEFAULT_DISC_DOC_TYPE, DEFAULT_DISC_TYPE, DEFAULT_DISC_WORK_FLOW, UPDT_USERID, TIME_STAMP, 
       LAST_ACTION, LIEN_NUM_GEN_NAME, LIEN_WO_STOP_TYPE, LIEN_WO_INHERITED_STOP_TYPE, LIEN_DISC_STOP_TYPE,
       LIEN_DISC_INHERITED_STOP_TYPE,LIEN_IO_INHERITED_STOP_TYPE)
      values
      ('REPAIR', 'Discrepancy', 'Process Order', 'PR', 'SFMFG', sysdate, 'INSERTED', '',
       'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION');
   exception 
      WHEN DUP_VAL_ON_INDEX then null;                                                                                                   
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into SFWID_ORDER_TYPE_DEF
      (ORDER_TYPE, DEFAULT_DISC_DOC_TYPE, DEFAULT_DISC_TYPE, DEFAULT_DISC_WORK_FLOW, UPDT_USERID, TIME_STAMP, 
       LAST_ACTION, LIEN_NUM_GEN_NAME, LIEN_WO_STOP_TYPE, LIEN_WO_INHERITED_STOP_TYPE, LIEN_DISC_STOP_TYPE,
       LIEN_DISC_INHERITED_STOP_TYPE,LIEN_IO_INHERITED_STOP_TYPE)
      values
      ('SUB ORDER', 'Discrepancy', 'Process Order', 'PR', 'SFMFG', sysdate, 'INSERTED', '',
       'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION');
   exception 
      WHEN DUP_VAL_ON_INDEX then null;                                                                                                 
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      insert into SFWID_ORDER_TYPE_DEF
      (ORDER_TYPE, DEFAULT_DISC_DOC_TYPE, DEFAULT_DISC_TYPE, DEFAULT_DISC_WORK_FLOW, UPDT_USERID, TIME_STAMP, 
       LAST_ACTION, LIEN_NUM_GEN_NAME, LIEN_WO_STOP_TYPE, LIEN_WO_INHERITED_STOP_TYPE, LIEN_DISC_STOP_TYPE,
       LIEN_DISC_INHERITED_STOP_TYPE,LIEN_IO_INHERITED_STOP_TYPE)
      values
      ('OVERHAUL', 'Discrepancy', 'Process Order', 'PR', 'SFMFG', sysdate, 'INSERTED', '',
       'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION', 'ORDER COMPLETION');
   exception 
      WHEN DUP_VAL_ON_INDEX then null;                                                                                                  
   end;
end;
/
commit;
/
