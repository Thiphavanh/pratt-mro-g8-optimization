prompt PL/SQL Developer import file
prompt Created on Monday, November 27, 2017 by m112328
set feedback off
set define off
prompt Loading SFFND_DOC_TYPE_DEF...
insert into SFFND_DOC_TYPE_DEF (doc_type, doc_sub_type, work_flow, instructions_type, task_type, updt_userid, time_stamp, last_action, description, reserved_flag, is_visible, obsolete_record_flag, num_gen_name, is_default, work_flow_desc, setup_status, gen_task_proc, comp_task_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'PCR_REVIEW', 'WO', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'M112328', to_date('20-11-2017 17:08:08', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'PCR - Process Plan Change Request', 'N', 'Y', 'N', 'DISC_NO_GEN', 'N', 'PCR Review', 'COMPLETE', 'com.ibaset.solumina.task.sfqa.DispositionTaskHandler', 'com.ibaset.solumina.task.sfqa.DiscItemTaskHandler');
insert into SFFND_DOC_TYPE_DEF (doc_type, doc_sub_type, work_flow, instructions_type, task_type, updt_userid, time_stamp, last_action, description, reserved_flag, is_visible, obsolete_record_flag, num_gen_name, is_default, work_flow_desc, setup_status, gen_task_proc, comp_task_proc)
values ('Discrepancy', 'Work Stop', 'WORKSTOP_REVIEW', 'WO', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'M112328', to_date('20-11-2017 16:32:56', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'Work Stop', 'N', 'Y', 'N', 'DISC_NO_GEN', 'N', 'Workstop Review', 'COMPLETE', 'com.ibaset.solumina.task.sfqa.DispositionTaskHandler', 'com.ibaset.solumina.task.sfqa.DiscItemTaskHandler');
commit;
prompt 2 records loaded

prompt Loading SFFND_WORK_FLOW...
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'ACTIVE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'ACTIVE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'IN QUEUE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'INSERT', null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'IN QUEUE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'ACTIVE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'IN QUEUE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'COMPLETE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'ACTIVE', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'COMPLETE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'INITIAL', 'INITIAL', 'Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'M112328', to_date('22-11-2017 08:34:19', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'ACTIVE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'ACTIVE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'IN QUEUE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'INSERT', null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'IN QUEUE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'ACTIVE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'IN QUEUE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'COMPLETE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'ACTIVE', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'COMPLETE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status, updt_userid, time_stamp, last_action, new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'INITIAL', 'INITIAL', 'Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'M112328', to_date('22-11-2017 08:33:50', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, null, null, null);
commit;
prompt 16 records loaded

prompt Loading SFFND_WORK_FLOW_SETUP...
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'DISC_INITIATION', 'ACTION', 'M112328', to_date('20-11-2017 17:08:36', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'ME_APPROVAL', null);
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'DISC_INITIATION', 'ACTION', 'M112328', to_date('20-11-2017 16:34:46', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'ENG_MR', null);
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
values ('Discrepancy_Work Stop_WORKSTOP_REVIEW', 'ENG_MR', 'ACTION', 'M112328', to_date('22-11-2017 08:34:16', 'dd-mm-yyyy hh24:mi:ss'), 'UPDATED', null, null);
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
values ('Discrepancy_PCR - Process Plan Chg Request_PCR_REVIEW', 'ME_APPROVAL', 'ACTION', 'M112328', to_date('22-11-2017 08:33:47', 'dd-mm-yyyy hh24:mi:ss'), 'UPDATED', null, null);
commit;
prompt 4 records loaded

prompt Loading SFQA_DISC_TYPE_DEF...
insert into SFQA_DISC_TYPE_DEF (disc_type, default_ca_type, updt_userid, time_stamp, last_action, auto_create_ca_flag, default_ca_work_flow, default_ca_doc_type, disc_desc_udv_id, item_lot_ser_udv_id, comp_lot_ser_udv_id, defect_class_udv_id, disp_action_udv_id, corr_action_udv_id, next_disc_desc_udv_id, next_item_lot_ser_udv_id, next_comp_lot_ser_udv_id, next_defect_class_udv_id, next_disp_action_udv_id, next_corr_action_udv_id, lien_num_gen_name)
values ('PCR - Process Plan Chg Request', 'Simple', 'M112328', to_date('20-11-2017 17:08:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'N', 'CA_SIMPLE', 'Corrective Action', null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into SFQA_DISC_TYPE_DEF (disc_type, default_ca_type, updt_userid, time_stamp, last_action, auto_create_ca_flag, default_ca_work_flow, default_ca_doc_type, disc_desc_udv_id, item_lot_ser_udv_id, comp_lot_ser_udv_id, defect_class_udv_id, disp_action_udv_id, corr_action_udv_id, next_disc_desc_udv_id, next_item_lot_ser_udv_id, next_comp_lot_ser_udv_id, next_defect_class_udv_id, next_disp_action_udv_id, next_corr_action_udv_id, lien_num_gen_name)
values ('Work Stop', 'Simple', 'M112328', to_date('20-11-2017 16:33:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', 'N', 'CA_SIMPLE', 'Corrective Action', null, null, null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 2 records loaded

ALTER TRIGGER SFFND_DOC_TYPE_DEF_HIST_TRIG DISABLE;

Update SFFND_DOC_TYPE_DEF t
set t.obsolete_record_flag = 'Y',
    t.is_default = 'N'
where t.doc_type = 'Discrepancy'
and t.doc_sub_type in ('Process Order', 'SQUAWK Build Part', 'SQUAWK Component');

ALTER TRIGGER SFFND_DOC_TYPE_DEF_HIST_TRIG ENABLE;


set feedback on
set define on
prompt Done.