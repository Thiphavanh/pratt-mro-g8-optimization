DECLARE
V_DUMMY VARCHAR2(10);
BEGIN
select 'DUMMY' INTO V_DUMMY from sfbis_service_def t where t.service_name = 'PW_S_T308_ORDER_RELEASE';
EXCEPTION
WHEN NO_DATA_FOUND THEN
	IF (DBID = 'SODMRO1') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'Y',
								'Y',
								'Y',
								'd10900.mus.com.pw.send.request.t308OrderRelease', 
								'd10900.mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');
	END IF;
  
    IF (DBID = 'SOQMROUT') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mus.com.pw.send.request.t308OrderRelease', 
								'q10900.mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');
	END IF;
  
    IF (DBID = 'SOQMROQT') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mus.com.pw.send.request.t308OrderRelease', 
								'q10900.mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
	
    IF (DBID = 'SOQMROQ3') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q10900.mnz.com.pw.send.request.t308OrderRelease', 
								'q10900.mnz.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;

    IF (DBID = 'SODMRO2') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'Y',
								'Y',
								'Y',
								'd22900.mus.com.pw.send.request.t308OrderRelease', 
								'd22900.mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
	
	IF (DBID = 'SOQMROQ2') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'q22900.mus.com.pw.send.request.t308OrderRelease', 
								'q22900.mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
	
	IF (DBID = 'SOPMROUS') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'mus.com.pw.send.request.t308OrderRelease', 
								'mus.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
	
	IF (DBID = 'SOPMROEU') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'meu.com.pw.send.request.t308OrderRelease', 
								'meu.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');		
	END IF;
	
	IF (DBID = 'SOPMRONZ') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'mnz.com.pw.send.request.t308OrderRelease', 
								'mnz.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
	
	IF (DBID = 'SOPMROSG') THEN
		sfbis_create_service_entry('PW_S_T308_ORDER_RELEASE',
								'OUTBOUND',
								'N',
								'Y',
								'Y',
								'msg.com.pw.send.request.t308OrderRelease', 
								'msg.com.pw.receive.reply.t308OrderRelease',
								1,
								'Sfwid_Order_Desc',
								'Send released order to SAP');	
	END IF;
end;
/

declare
V_DUMMY VARCHAR2(10);
begin
      Select 1 into V_DUMMY from PWUST_GLOBAL_CONFIG TT WHERE TT.PARAMETER_NAME = 'T308_OUTBOUND_JMS_EXPIRATION_SECONDS';
Exception 
      when no_data_found then
        INSERT INTO PWUST_GLOBAL_CONFIG T   (PARAMETER_NAME, 
                                             PARAMETER_VALUE, 
                                             PARAMETER_DESC, 
                                             UPDT_USERID, 
                                             TIME_STAMP, 
                                             ACTION)
        VALUES  ('T308_OUTBOUND_JMS_EXPIRATION_SECONDS', 
                 '20', 
                 'T308  MessageProducer.setTimeToLive() value', 
                 'SFMFG',  
                 SYSDATE,  
                 'INSERTED');
end;
/


declare
V_DUMMY VARCHAR2(10);
begin
      Select 1 into V_DUMMY from PWUST_GLOBAL_CONFIG TT WHERE TT.PARAMETER_NAME = 'T308_REPLY_JMS_TIMEOUT_SECONDS';
Exception 
      when no_data_found then
        INSERT INTO PWUST_GLOBAL_CONFIG T  (PARAMETER_NAME, 
                                            PARAMETER_VALUE, 
                                            PARAMETER_DESC, 
                                            UPDT_USERID, 
                                            TIME_STAMP, 
                                            ACTION)
          VALUES  ('T308_REPLY_JMS_TIMEOUT_SECONDS',
                   '40',  
                   'T308 wait time in seconds for a reply',  
                   'SFMFG',  
                    SYSDATE, 
                   'INSERTED');
end;
/
ALTER TABLE PW_S_T308_ORDER_RELEASE_LOG  MODIFY  (MESSAGE_ID  VARCHAR2(51) );
ALTER TABLE PW_S_T308_ORDER_RELEASE_RPLY  MODIFY (MESSAGE_ID  VARCHAR2(51),ORIGINAL_MESSAGE_ID VARCHAR2(51));