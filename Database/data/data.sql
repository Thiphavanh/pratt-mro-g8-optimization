--1
INSERT INTO SFFND_DOC_TYPE_DEF
(doc_type, doc_sub_type, work_flow,instructions_type, task_type, updt_userid, time_stamp, last_action, description, reserved_flag, is_visible, obsolete_record_flag, num_gen_name, is_default, work_flow_desc, setup_status, gen_task_proc, comp_task_proc)
VALUES
('Discrepancy', 'Defect', 'DEFECT_REVIEW', 'WO', 'Discrepancy_Defect_DEFECT_REVIEW', 'SFMFG', sysdate, 'INSERTED', 'Defect Discrepancy', 'N', 'Y', 'N', 'DISC_NO_GEN', 'N', 'Defect Discrepancy', 'COMPLETE', 'com.ibaset.solumina.task.sfqa.DispositionTaskHandler', 'com.ibaset.solumina.task.sfqa.DiscItemTaskHandler');

--2
INSERT INTO SFQA_DISC_TYPE_DEF
(disc_type, default_ca_type, updt_userid, time_stamp, last_action, auto_create_ca_flag, default_ca_work_flow, default_ca_doc_type, disc_desc_udv_id, item_lot_ser_udv_id, comp_lot_ser_udv_id, defect_class_udv_id, disp_action_udv_id, corr_action_udv_id, next_disc_desc_udv_id, next_item_lot_ser_udv_id, next_comp_lot_ser_udv_id, next_defect_class_udv_id, next_disp_action_udv_id, next_corr_action_udv_id, lien_num_gen_name)
VALUES
('Defect', 'Simple', 'SFMFG', sysdate, 'INSERTED', 'N', 'CA_SIMPLE', 'Corrective Action', '', '', '', '', '', '', '', '', '', '', '', '', '');

--3
INSERT INTO SFFND_WORK_FLOW_SETUP
(task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
VALUES
('Discrepancy_Defect_DEFECT_REVIEW', 'DISC_INITIATION', 'ACTION', 'SFMFG', sysdate, 'INSERTED', 'ME_APPROVAL', '');

INSERT INTO SFFND_WORK_FLOW_SETUP
(task_type, queue, task_class, updt_userid, time_stamp, last_action, next_queue, reject_queue)
VALUES
('Discrepancy_Defect_DEFECT_REVIEW', 'ME_APPROVAL', 'ACTION', 'SFMFG', sysdate, 'INSERTED', '', '');

--4
--5
--6
INSERT INTO SFQA_DISP_TYPE_DEF
(disc_type, disp_type, updt_userid, time_stamp, last_action, obsolete_record_flag, reject_flag, scrap_flag, disp_instr_flag, disp_desc, sqa_action_type, insp_order_type, reserved_flag, is_visible, return_to_vendor, return_to_inventory_flag, di_workflow, enable_verify_rework_hold)
VALUES
('Defect', 'As is', 'CONVERSION', sysdate, 'INSERTED', 'N', 'N', 'N', 'N', 'Continue as is', '', '', 'N', 'Y', 'N', 'N', '', 'N');

INSERT INTO SFQA_DISP_TYPE_DEF
(disc_type, disp_type, updt_userid, time_stamp, last_action, obsolete_record_flag, reject_flag, scrap_flag, disp_instr_flag, disp_desc, sqa_action_type, insp_order_type, reserved_flag, is_visible, return_to_vendor, return_to_inventory_flag, di_workflow, enable_verify_rework_hold)
VALUES
('Defect', 'Repair', 'CONVERSION', sysdate, 'INSERTED', 'N', 'Y', 'N', 'Y', 'Repair', '', '', 'N', 'Y', 'N', 'N', '', 'N');

INSERT INTO SFQA_DISP_TYPE_DEF
(disc_type, disp_type, updt_userid, time_stamp, last_action, obsolete_record_flag, reject_flag, scrap_flag, disp_instr_flag, disp_desc, sqa_action_type, insp_order_type, reserved_flag, is_visible, return_to_vendor, return_to_inventory_flag, di_workflow, enable_verify_rework_hold)
VALUES
('Defect', 'Replace', 'CONVERSION', sysdate, 'INSERTED', 'N', 'Y', 'N', 'Y', 'Replace', '', '', 'N', 'Y', 'N', 'N', '', 'N');

INSERT INTO SFQA_DISP_TYPE_DEF
(disc_type, disp_type, updt_userid, time_stamp, last_action, obsolete_record_flag, reject_flag, scrap_flag, disp_instr_flag, disp_desc, sqa_action_type, insp_order_type, reserved_flag, is_visible, return_to_vendor, return_to_inventory_flag, di_workflow, enable_verify_rework_hold)
VALUES
('Work Stop', 'Replaced', 'CONVERSION', sysdate, 'INSERTED', 'N', 'N', 'N', 'N', 'Part has been replaced', '', '', 'N', 'Y', 'N', 'N', '', 'N');

INSERT INTO SFQA_DISP_TYPE_DEF
(disc_type, disp_type, updt_userid, time_stamp, last_action, obsolete_record_flag, reject_flag, scrap_flag, disp_instr_flag, disp_desc, sqa_action_type, insp_order_type, reserved_flag, is_visible, return_to_vendor, return_to_inventory_flag, di_workflow, enable_verify_rework_hold)
VALUES
('Defect', 'Rework', 'CONVERSION', sysdate, 'INSERTED', 'N', 'Y', 'N', 'Y', 'Rework', '', '', 'N', 'Y', 'N', 'N', '', 'N');

--7
INSERT INTO SFQA_DISP_DOC_TYPE_DEF
(disp_doc_type, updt_userid, time_stamp, last_action, obsolete_record_flag, disp_doc_desc)
VALUES
('Defect', 'CONVERSION', sysdate, 'INSERTED', 'N', 'Defect');









