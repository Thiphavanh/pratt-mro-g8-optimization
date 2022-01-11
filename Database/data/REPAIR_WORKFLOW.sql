--2018-12-17
--Fred Ettefagh   
--Defect 1044, fix repair order create 
Begin 
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_DISP', 'DiscItemDispInstrQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_IE', 'OrderOperIEText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_PL', 'OrderOperText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_PL', 'OrderPlanText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_QA', 'OrderOperQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/

Begin
insert into sffnd_editmode_status (DOC_TYPE, DOC_SUB_TYPE, STATUS, WORK_FLOW, EDIT_MODE, BLOCK_ID)
values ('Process Order', 'REPAIR', 'ORDER_AUTHORING', 'REPAIR_ALTERATION', 'EDITMODES.EDIT_QA', 'OrderStepQAText');
Exception
    When DUP_VAL_ON_INDEX then null;
End;
/
commit;