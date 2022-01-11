EXEC sfbis_create_service_entry(vi_service_name => 'PW_R_LO_NOTIFICATION', vi_direction_indicator => 'INBOUND',   vi_enabled_flag => 'N', vi_reply_enabled_flag => 'N',vi_logging_flag => 'Y',vi_queue_name => 'com.pw.receive.request.lonotification', vi_reply_queue_name => 'com.pw.receive.reply.lonotification',vi_concurrent_consumers => 1,vi_solumina_ref_table_name => 'SFFND_SECURITY_GROUP_DEF', vi_description => 'LO NOTIFICATION FROM SAP');


ALTER TABLE 
   SFMFG.PW_R_LO_NOTIFICATION_LOG 
MODIFY 
   ( 
   MESSAGE_ID         VARCHAR2(51)
   )
;


ALTER TABLE 
   SFMFG.PW_R_LO_NOTIFICATION_RPLY
MODIFY
   (
   MESSAGE_ID          VARCHAR2(51),
   ORIGINAL_MESSAGE_ID VARCHAR2(51)
   )
;