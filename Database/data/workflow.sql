exec sfdb_login;

alter trigger SFFND_WORK_FLOW_SETUP_AIUD disable;  

Begin
INSERT INTO sffnd_doc_type_def(doc_type,
                               doc_sub_type,
                               instructions_type,
                               task_type,
                               description,
                 is_visible,
                               num_gen_name,
                               is_default,
                               work_flow,
                               work_flow_desc,
                               setup_status,
                               gen_task_proc,
                               comp_task_proc)
                        values('Process Plan',
                               'OVERHAUL',
                               'MRO-Part',
                 'Process Plan_OVERHAUL_OH_EXPT_AUTH',
                               'PW OVERHAUL',
                 'Y',
                               'PLAN_NO_GEN',
                               'Y',
                               'OH_EXPT_AUTH',
                               'PW MRO Work Flow',
                               'IN PROCESS',
                               'com.ibaset.solumina.task.sfpl.PlanTaskHandler',
                               'com.ibaset.solumina.task.sfpl.PlanTaskHandler');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/
                 
Begin
INSERT INTO SFFND_WORK_FLOW_SETUP
  (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
   VALUES ('Process Plan_OVERHAUL_OH_EXPT_AUTH','PLG_AUTHORING','ACTION','ME_APPROVAL','');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/
  
Begin
INSERT INTO SFFND_WORK_FLOW_SETUP
  (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
 VALUES ('Process Plan_OVERHAUL_OH_EXPT_AUTH','ME_APPROVAL','APPROVAL','QA_APPROVAL','PLG_AUTHORING');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
INSERT INTO SFFND_WORK_FLOW_SETUP
  (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
   VALUES ('Process Plan_OVERHAUL_OH_EXPT_AUTH','QA_APPROVAL','APPROVAL','EXPT_ACCEPTANCE','PLG_AUTHORING');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/ 

Begin  
INSERT INTO SFFND_WORK_FLOW_SETUP
 (TASK_TYPE, QUEUE, TASK_CLASS, NEXT_QUEUE, REJECT_QUEUE) 
  VALUES ('Process Plan_OVERHAUL_OH_EXPT_AUTH','EXPT_ACCEPTANCE','APPROVAL','','PLG_AUTHORING');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin 
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Plan','OVERHAUL','PLG_AUTHORING', 'OH_EXPT_AUTH', 'EDITMODES.EDIT_PL','PlanOperText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Plan','OVERHAUL','PLG_AUTHORING', 'OH_EXPT_AUTH', 'EDITMODES.EDIT_PL','PlanText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
 INSERT INTO SFFND_EDITMODE_STATUS 
  (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID) 
   VALUES ('Process Plan','OVERHAUL','PLG_AUTHORING', 'OH_EXPT_AUTH', 'EDITMODES.EDIT_PL','PlanStepText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

alter trigger SFFND_DOC_TYPE_DEF_HIST_TRIG enable; 
alter trigger SFFND_WORK_FLOW_SETUP_AIUD enable;  

exec sfdb_logout;


