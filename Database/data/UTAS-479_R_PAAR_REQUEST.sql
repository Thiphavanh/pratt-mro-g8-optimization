begin
  sfbis_create_service_entry('R_PAAR_REQUEST',
                             'INBOUND',
                             'Y',
                             'N',
                             'Y',
                             'com.utas.send.request.paarrequest',
                             'com.utas.receive.reply.paarrequest',
                             1,
                             'UTASGE_PLG_PAAR_DESC',
                             'Receive Plan Access Authorization Review request');
 commit;
end;
/