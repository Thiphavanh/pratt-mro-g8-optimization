DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
select 'DUMMY' INTO V_DUMMY from sfbis_service_def t where t.service_name = 'PW_S_T319_ORDER_SPLIT';
EXCEPTION
WHEN NO_DATA_FOUND THEN
	IF (DBID = 'SODMRO1') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'Y',
								'Y',
								'Y',
								'd10900.mus.com.pw.send.request.t319', 
								'd10900.mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');
	END IF;
  
    IF (DBID = 'SOQMROUT') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mus.com.pw.send.request.t319', 
								'q10900.mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');
	END IF;
  
    IF (DBID = 'SOQMROQT') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mus.com.pw.send.request.t319', 
								'q10900.mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
	
    IF (DBID = 'SOQMROQ3') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mnz.com.pw.send.request.t319', 
								'q10900.mnz.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;

    IF (DBID = 'SODMRO2') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'Y',
								'Y',
								'Y',
								'd22900.mus.com.pw.send.request.t319', 
								'd22900.mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
	
	IF (DBID = 'SOQMROQ2') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q22900.mus.com.pw.send.request.t319', 
								'q22900.mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
	
	IF (DBID = 'SOPMROUS') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'mus.com.pw.send.request.t319', 
								'mus.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
	
	IF (DBID = 'SOPMROEU') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'meu.com.pw.send.request.t319', 
								'meu.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');		
	END IF;
	
	IF (DBID = 'SOPMRONZ') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'mnz.com.pw.send.request.t319', 
								'mnz.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
	
	IF (DBID = 'SOPMROSG') THEN
		sfbis_create_service_entry('PW_S_T319_ORDER_SPLIT',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'msg.com.pw.send.request.t319', 
								'msg.com.pw.receive.reply.t319',
								1,
								'Sfwid_Order_Desc',
								'Get Split order No From SAP');	
	END IF;
end;
/

declare
V_DUMMY VARCHAR2(10);
begin
      Select 1 into V_DUMMY from PWUST_GLOBAL_CONFIG TT WHERE TT.PARAMETER_NAME = 'T319_OUTBOUND_JMS_EXPIRATION_SECONDS';
Exception 
      when no_data_found then
        INSERT INTO PWUST_GLOBAL_CONFIG T   (PARAMETER_NAME, 
                                             PARAMETER_VALUE, 
                                             PARAMETER_DESC, 
                                             UPDT_USERID, 
                                             TIME_STAMP, 
                                             ACTION)
        VALUES  ('T319_OUTBOUND_JMS_EXPIRATION_SECONDS', 
                 '20', 
                 'T319 MessageProducer.setTimeToLive() value', 
                 'SFMFG',  
                 SYSDATE,  
                 'INSERTED');
end;
/


declare
V_DUMMY VARCHAR2(10);
begin
      Select 1 into V_DUMMY from PWUST_GLOBAL_CONFIG TT WHERE TT.PARAMETER_NAME = 'T319_REPLY_JMS_TIMEOUT_SECONDS';
Exception 
      when no_data_found then
        INSERT INTO PWUST_GLOBAL_CONFIG T  (PARAMETER_NAME, 
                                            PARAMETER_VALUE, 
                                            PARAMETER_DESC, 
                                            UPDT_USERID, 
                                            TIME_STAMP, 
                                            ACTION)
          VALUES  ('T319_REPLY_JMS_TIMEOUT_SECONDS',
                   '40',  
                   'T319 wait time in seconds for a reply',  
                   'SFMFG',  
                    SYSDATE, 
                   'INSERTED');
end;
/
ALTER TABLE PW_S_T319_ORDER_SPLIT_LOG  MODIFY  (MESSAGE_ID  VARCHAR2(51) );
ALTER TABLE PW_S_T319_ORDER_SPLIT_RPLY  MODIFY (MESSAGE_ID  VARCHAR2(51),ORIGINAL_MESSAGE_ID VARCHAR2(51));
