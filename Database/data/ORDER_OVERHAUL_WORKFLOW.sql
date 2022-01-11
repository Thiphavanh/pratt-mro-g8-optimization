exec sfdb_login;

alter trigger SFFND_WORK_FLOW_SETUP_AIUD disable;  

-- make OOTB Overhaul not default and not visible
UPDATE sffnd_doc_type_def
SET is_visible = 'N',
    is_default = 'N'
WHERE doc_type = 'Process Order'
AND doc_sub_type = 'Overhaul'
AND work_flow = 'OVERHAUL_ALTERATION';

Begin
INSERT INTO sffnd_doc_type_def(doc_type,
                               doc_sub_type,
                               instructions_type,
                               task_type,
                               description,
                               reserved_flag,
                               is_visible,
                               num_gen_name,
                               is_default,
                               work_flow,
                               work_flow_desc,
                               setup_status,
                               gen_task_proc,
                               comp_task_proc)
                        values('Process Order',
                               'OVERHAUL',
                               'MRO-Part',
                               'ALTERATION',
                               'PW OVERHAUL',
                               'Y'
                               'Y',
                               'PLAN_NO_GEN',
                               'Y',
                               'OVERHAUL_ALTERATION',
                               'PW Work Order Alteration',
                               'COMPLETE',
                               'com.ibaset.solumina.task.sfwid.AlterationTaskHandler',
                               'com.ibaset.solumina.task.sfwid.AlterationTaskHandler');
Exception
    When DUP_VAL_ON_INDEX 
        UPDATE sffnd_doc_type_def T
           set t.work_flow = 'OVERHAUL_ALTERATION',
               /*t.task_type = 'ALTERATION',*/
               t.setup_status = 'COMPLETE',
               t.reserved_flag = 'Y'
         WHERE T.DOC_TYPE = 'Process Order'
           and t.doc_sub_type = 'OVERHAUL';
End;
/
                 
/*Begin
INSERT INTO SFFND_WORK_FLOW_SETUP
  (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
   VALUES ('Process Order_OVERHAUL_OVERHAUL_ALTERATION','ORDER_AUTHORING','ACTION','ORDER_AUTHORING2','');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/
  
Begin
INSERT INTO SFFND_WORK_FLOW_SETUP
  (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
 VALUES ('Process Order_OVERHAUL_OVERHAUL_ALTERATION','ORDER_AUTHORING2','ACTION','','ORDER_AUTHORING');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/*/

Begin 
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_DISP','DiscItemDispInstrQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_IE','OrderOperIEText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_QA','OrderStepQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

---

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_PL','OrderOperText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_PL','OrderPlanText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/
Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Order','OVERHAUL','ORDER_AUTHORING', 'OVERHAUL_ALTERATION', 'EDITMODES.EDIT_QA','OrderOperQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

alter trigger SFFND_DOC_TYPE_DEF_HIST_TRIG enable; 
alter trigger SFFND_WORK_FLOW_SETUP_AIUD enable;  

exec sfdb_logout;


