begin
  sfbis_create_service_entry('S_PAAR_REPLY',
                             'OUTBOUND',
                             'Y',
                             'Y',
                             'Y',
                             'com.utas.receive.reply.paarrequest',
                             'com.utas.temp',
                             1,
                             'UTASGE_PLG_PAAR_DESC',
                             'Send Plan Access Authorization Review request reply');
 commit;
end;
/