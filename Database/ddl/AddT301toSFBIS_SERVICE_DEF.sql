DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
select 'DUMMY' INTO V_DUMMY from sfbis_service_def t where t.service_name = 'PW_R_T301_ORDER';
EXCEPTION
WHEN NO_DATA_FOUND THEN
	IF (DBID = 'SODMRO1') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'Y',
								'N',
								'Y',
								'd10900.mus.com.pw.receive.request.t301order', 
								'd10900.mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');
	END IF;
  
    IF (DBID = 'SOQMROUT') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'q10900.mus.com.pw.receive.request.t301order', 
								'q10900.mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');
	END IF;
  
      IF (DBID = 'SOQMROQT') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'q10900.mus.com.pw.receive.request.t301order', 
								'q10900.mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
	
    IF (DBID = 'SOQMROQ3') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'q10900.mnz.com.pw.receive.request.t301order', 
								'q10900.mnz.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;

    IF (DBID = 'SODMRO2') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'Y',
								'N',
								'Y',
								'd22900.mus.com.pw.receive.request.t301order', 
								'd22900.mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
	
	IF (DBID = 'SOQMROQ2') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'q22900.mus.com.pw.receive.request.t301order', 
								'q22900.mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
	
	IF (DBID = 'SOPMROUS') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'mus.com.pw.receive.request.t301order', 
								'mus.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
	
	IF (DBID = 'SOPMROEU') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'meu.com.pw.receive.request.t301order', 
								'meu.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');		
	END IF;
	
	IF (DBID = 'SOPMRONZ') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'mnz.com.pw.receive.request.t301order', 
								'mnz.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
	
	IF (DBID = 'SOPMROSG') THEN
		sfbis_create_service_entry('PW_R_T301_ORDER',
								'INBOUND',
								'N',
								'N',
								'Y',
								'msg.com.pw.receive.request.t301order', 
								'msg.com.pw.receive.reply.t301order',
								1,
								'PWUST_INT_SAP_SM_ORDER',
								'Receive tool number');	
	END IF;
end;
/
