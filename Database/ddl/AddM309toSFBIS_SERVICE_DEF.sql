-- Use procedure SFBIS_CREATE_SERVICE_ENTRY to add entries to SFBIS_SERVICE_DEF table for M309

EXEC sfbis_remove_service_entry(vi_service_name => 'PW_R_M309_TOOL');

declare

  DBID  varchar2(10);

BEGIN

  SELECT VALUE INTO DBID FROM V$SPPARAMETER WHERE NAME = 'db_name';
  
    IF (DBID = 'SODMRO1') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'Y', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'd10900.mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'd10900.mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;
  
    IF (DBID = 'SOQMROUT') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'q10900.mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'q10900.mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;
  
    IF (DBID = 'SOQMROQT') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'q10900.mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'q10900.mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;
	
    IF (DBID = 'SOQMROQ3') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'q10900.mnz.com.pw.receive.request.m309tool', vi_reply_queue_name => 'q10900.mnz.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;

    IF (DBID = 'SODMRO2') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'd22900.mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'd22900.mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;
	
	IF (DBID = 'SOQMROQ2') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'q22900.mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'q22900.mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');

	END IF;
	
	IF (DBID = 'SOPMROUS') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'mus.com.pw.receive.request.m309tool', vi_reply_queue_name => 'mus.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');
		
	END IF;
	
	IF (DBID = 'SOPMROEU') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'meu.com.pw.receive.request.m309tool', vi_reply_queue_name => 'meu.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');
		
	END IF;
	
	IF (DBID = 'SOPMRONZ') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'mnz.com.pw.receive.request.m309tool', vi_reply_queue_name => 'mnz.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');
	
	END IF;
	
	IF (DBID = 'SOPMROSG') THEN
	
		sfbis_create_service_entry(vi_service_name => 'PW_R_M309_TOOL', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'msg.com.pw.receive.request.m309tool', vi_reply_queue_name => 'msg.com.pw.receive.reply. m309tool',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFPL_ITEM_DESC_MASTER_ALL', vi_description => 'Receive Tool Number');
		
	END IF;
	
END;
/
commit;
/
	