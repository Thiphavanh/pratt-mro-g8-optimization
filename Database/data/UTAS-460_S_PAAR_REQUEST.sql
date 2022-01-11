begin
  sfbis_create_service_entry('S_PAAR_REQUEST',
                             'OUTBOUND',
                             'Y',
                             'Y',
                             'Y',
                             'com.utas.send.request.paarrequest',
                             'com.utas.receive.reply.paarrequest',
                             1,
                             'UTASGI_PLG_PAAR_DESC',
                             'Send Plan Access Authorization Review request');
 commit;
end;
/