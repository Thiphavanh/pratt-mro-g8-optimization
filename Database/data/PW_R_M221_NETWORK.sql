DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
select 'DUMMY' INTO V_DUMMY from sfbis_service_def t where t.service_name = 'PW_R_M221_NETWORK';
EXCEPTION
WHEN NO_DATA_FOUND THEN
sfbis_create_service_entry('PW_R_M221_NETWORK', 
                            'INBOUND', 
                            'Y', 
                            'N', 
                            'Y',
                            'com.ibaset.receive.request.stdnetwork',
                            'com.ibaset.send.reply.stdnetwork',1,
                            'PWUST_NETWORK_DEF',
                            'PW MRO standard network interface');
end;
/


