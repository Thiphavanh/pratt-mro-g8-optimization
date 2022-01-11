
prompt Loading SFFND_EDITMODE_STATUS...

insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Mandatory', 'Discrepancy Item Description', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Mandatory', 'Component Lot/Serials Without Disposition', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialWithoutDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Optional', 'Item Lot/Serials', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Cause Analysis', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Defect Classification - Cause Description', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Disposition Instructions', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Disposition Work Instruction', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Disposition Action - Engineering Disposition', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Hidden', 'Corrective Action', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Readonly', 'Disposition Action', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'DISC_INITIATION', 'PCR_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition', to_date('22-11-2017 08:30:53', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Mandatory', 'Discrepancy Item Description', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Mandatory', 'Disposition Action', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Mandatory', 'Disposition Instructions', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Mandatory', 'Item Lot/Serials', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Optional', 'Cause Analysis', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Optional', 'Disposition Work Instruction', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Hidden', 'Corrective Action', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Hidden', 'Affected Components', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Optional', 'Defect Classification - Cause Description', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'PCR - Process Plan Chg Request', 'ME_APPROVAL', 'PCR_REVIEW', 'Optional', 'Disposition Action - Engineering Disposition', to_date('22-11-2017 08:31:17', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Cause Analysis', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Corrective Action', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Defect Classification - Cause Description', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Disposition Action - Engineering Disposition', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Disposition Instructions', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Hidden', 'Disposition Work Instruction', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Mandatory', 'Component Lot/Serials Without Disposition', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialWithoutDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Mandatory', 'Discrepancy Item Description', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Optional', 'Item Lot/Serials', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'DISC_INITIATION', 'WORKSTOP_REVIEW', 'Readonly', 'Disposition Action', to_date('22-11-2017 08:37:01', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Hidden', 'Affected Components', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ComponentLotSerialValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Hidden', 'Corrective Action', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CorrectiveActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Mandatory', 'Discrepancy Item Description', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Mandatory', 'Disposition Action', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Mandatory', 'Disposition Instructions', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionInstructionsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Mandatory', 'Item Lot/Serials', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.ItemLotSerialsValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Optional', 'Cause Analysis', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.CauseAnalysisValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Optional', 'Defect Classification - Cause Description', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DefectClassificationCauseDescriptionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Optional', 'Discrepancy Description - Actual Condition', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DiscrepancyDescriptionActualConditionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Optional', 'Disposition Action - Engineering Disposition', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionActionEngineeringDispositionValidator');
insert into SFFND_EDITMODE_STATUS (doc_type, doc_sub_type, status, work_flow, edit_mode, block_id, time_stamp, last_action, userid, block_validator_proc)
values ('Discrepancy', 'Work Stop', 'ENG_MR', 'WORKSTOP_REVIEW', 'Optional', 'Disposition Work Instruction', to_date('22-11-2017 08:37:15', 'dd-mm-yyyy hh24:mi:ss'), 'INSERTED', null, 'com.ibaset.solumina.block.sfqa.DispositionWorkInstructionValidator');
commit;

prompt 44 records loaded

