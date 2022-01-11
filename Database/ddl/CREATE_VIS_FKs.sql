--VIS_LEGACY_OPERATION
alter table VIS_LEGACY_OPERATION
  add constraint VIS_LEGACY_OPERATION_FK1 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;

--VIS_LEGACY_PLAN
alter table VIS_LEGACY_PLAN
  add constraint VIS_LEGACY_PLAN_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID) on delete cascade;

--VIS_LEGACY_SUBJECT
alter table VIS_LEGACY_SUBJECT
  add constraint VIS_LEGACY_SUBJECT_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_OPERATION
alter table VIS_OPERATION
  add constraint VIS_OPERATION_STDOPER_ID_FK1 foreign key (STDOPER_ID)
  references VIS_STANDARD_OPERATION (STDOPER_ID) on delete cascade;

--VIS_OPERATION_SUBJECT
alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJ_OPER_ID_FK1 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;

alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJ_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_STEP
alter table VIS_STEP
  add constraint VIS_STEP_OPER_ID_FK1 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;

alter table VIS_STEP
  add constraint VIS_STEP_STDOPER_ID_FK1 foreign key (STDOPER_ID)
  references VIS_STANDARD_OPERATION (STDOPER_ID) on delete cascade;

--VIS_SUBJECT_CUSTOMER_EXCLUSION
alter table VIS_SUBJECT_CUSTOMER_EXCLUSION
  add constraint VIS_SUBJ_CUST_EXC_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_SUBJECT_CUSTOMER_INCLUSION
alter table VIS_SUBJECT_CUSTOMER_INCLUSION
  add constraint VIS_SUBJ_CUST_INC_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_SUBJECT_SUB_NETWORK
alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NET_UNIQ_FK1 unique (SUBJECT_ID, ACTIVITY_NUMBER, SUB_ACTIVITY_NUMBER);

alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NET_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_SUBJECT_SUPERIOR_NETWORK
alter table VIS_SUBJECT_SUPERIOR_NETWORK
  add constraint VIS_SUP_NET_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

--VIS_SUBJECT_WORKSCOPE
alter table VIS_SUBJECT_WORKSCOPE
  add constraint VIS_SUBJ_WORKSCOPE_SUBJ_ID_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;
