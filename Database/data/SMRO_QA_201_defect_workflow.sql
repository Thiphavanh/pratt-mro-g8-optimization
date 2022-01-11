
prompt Loading SFFND_DOC_TYPE_DEF...
insert into SFFND_DOC_TYPE_DEF (doc_type, 
                               doc_sub_type, 
							   work_flow, 
							   instructions_type, 
							   task_type,  
							   description, 
							   reserved_flag, 
							   is_visible, 
							   obsolete_record_flag, 
							   num_gen_name, 
							   is_default, 
							   work_flow_desc, 
							   setup_status, 
							   gen_task_proc, 
							   comp_task_proc)
values ('Discrepancy', 
        'Defect', 
        'DEFECT_REVIEW', 
		'WO', 
		'Discrepancy_Defect_DEFECT_REVIEW',  
		'Defect Discrepancy', 
		'N', 
		'Y', 
		'N', 
		'DISC_NO_GEN', 
		'N', 
		'Defect Discrepancy', 
		'COMPLETE', 
		'com.ibaset.solumina.task.sfqa.DispositionTaskHandler', 
		'com.ibaset.solumina.task.sfqa.DiscItemTaskHandler');
commit;
prompt 1 records loaded

prompt Loading SFFND_WORK_FLOW...
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'ACTIVE',  null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'IN QUEUE', 'Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'COMPLETE',  null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'ACTIVE', 'Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'COMPLETE',  null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'COMPLETE', 'Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'IN QUEUE',  'INSERT', null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'IN QUEUE', 'Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'ACTIVE',  null, null, null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'IN QUEUE', 'Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'COMPLETE',  null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'ACTIVE', 'Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'COMPLETE',  null, 'Y', null, null);
insert into SFFND_WORK_FLOW (from_task_type, from_queue_type, from_status, to_task_type, to_queue_type, to_status,  new_task_action, ending_task_flag, new_task_proc_name, starting_task_flag)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'INITIAL', 'INITIAL', 'Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'IN QUEUE',  null, null, null, null);
commit;
prompt 8 records loaded

ALTER TRIGGER SFFND_DOC_TYPE_DEF_HIST_TRIG DISABLE;
prompt Loading SFFND_WORK_FLOW_SETUP...
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class,  next_queue, reject_queue)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'ACTION',  'ME_APPROVAL', null);
insert into SFFND_WORK_FLOW_SETUP (task_type, queue, task_class,  next_queue, reject_queue)
values ('Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'ACTION',  null, null);
commit;
prompt 2 records loaded
ALTER TRIGGER SFFND_DOC_TYPE_DEF_HIST_TRIG ENABLE;

prompt Loading SFQA_DISC_TYPE_DEF...
insert into SFQA_DISC_TYPE_DEF (disc_type, default_ca_type,  auto_create_ca_flag, default_ca_work_flow, default_ca_doc_type, disc_desc_udv_id, item_lot_ser_udv_id, comp_lot_ser_udv_id, defect_class_udv_id, disp_action_udv_id, corr_action_udv_id, next_disc_desc_udv_id, next_item_lot_ser_udv_id, next_comp_lot_ser_udv_id, next_defect_class_udv_id, next_disp_action_udv_id, next_corr_action_udv_id, lien_num_gen_name)
values ('Defect', 'Simple', 'N', 'CA_SIMPLE', 'Corrective Action', null, null, null, null, null, null, null, null, null, null, null, null, null);
commit;
prompt 1 records loaded

prompt Loading SFFND_EDITMODE_STATUS...

insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Mandatory', 'Discrepancy Item Description',  null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Mandatory', 'Component Lot/Serials Without Disposition',  null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialWithoutDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Item Lot/Serials',  null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Cause Analysis',  null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Defect Classification - Cause Description',  null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Disposition Instructions',  null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Disposition Work Instruction',  null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Disposition Action - Engineering Disposition',  null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Hidden', 'Corrective Action',  null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Readonly', 'Disposition Action',  null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'DISC_INITIATION', 'DEFECT_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition',  null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Mandatory', 'Discrepancy Item Description',  null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Mandatory', 'Disposition Action',  null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Mandatory', 'Disposition Instructions',  null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Hidden', 'Item Lot/Serials',  null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Optional', 'Cause Analysis',  null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Mandatory', 'Disposition Work Instruction',  null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Hidden', 'Corrective Action',  null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Hidden', 'Affected Components',  null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition',  null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Optional', 'Defect Classification - Cause Description',  null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id,  userid, block_validator_proc)
values ('Discrepancy', 'Defect', 'ME_APPROVAL', 'DEFECT_REVIEW', 'Optional', 'Disposition Action - Engineering Disposition',  null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
commit;

prompt 22 records loaded

