-- VIS_CONV_PLAN
alter table VIS_CONV_PLAN
  add constraint VIS_CONV_PLAN_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_CONV_PLAN
  add constraint VIS_CONV_PLAN_FK2 foreign key (CLAIMED_BY)
  references VIS_CONV_USERS (USERCODE);

-- VIS_CONV_PLAN_PROGRESS
alter table VIS_CONV_PLAN_PROGRESS
  add constraint VIS_CONV_PLAN_PROGRESS_FK1 foreign key (PLAN_NAME)
  references VIS_CONV_PLAN (PLAN_NAME);

-- VIS_CONV_ROLES
alter table VIS_CONV_ROLES
  add constraint VIS_CONV_ROLES_FK1 foreign key (USERCODE)
  references VIS_CONV_USERS (USERCODE);

-- VIS_LEGACY_OPERATION
alter table VIS_LEGACY_OPERATION
  add constraint VIS_LEGACY_OPERATION_FK1 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;

-- VIS_LEGACY_PLAN
alter table VIS_LEGACY_PLAN
  add constraint VIS_LEGACY_PLAN_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID) on delete cascade;

-- VIS_LEGACY_SUBJECT
alter table VIS_LEGACY_SUBJECT
  add constraint VIS_LEGACY_SUBJECT_FK1 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_OPERATION
alter table VIS_OPERATION
  add constraint VIS_OPERATION_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID) on delete cascade;

-- VIS_OPERATION_SUBJECT
alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJECT_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJECT_FK2 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;
alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJECT_FK3 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_STEP
alter table VIS_STEP
  add constraint VIS_STEP_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_STEP
  add constraint VIS_STEP_FK2 foreign key (OPER_KEY)
  references VIS_OPERATION (OPER_KEY) on delete cascade;
alter table VIS_STEP
  add constraint VIS_STEP_FK3 foreign key (STDOPER_TAG)
  references VIS_STANDARD_OPERATION (STDOPER_TAG) on delete cascade;

-- VIS_SUBJECT_CUSTOMER_EXCLUSION
alter table VIS_SUBJECT_CUSTOMER_EXCLUSION
  add constraint VIS_SUBJ_CUST_EXCL_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_SUBJECT_CUSTOMER_EXCLUSION
  add constraint VIS_SUBJ_CUST_EXCL_FK2 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_SUBJECT_CUSTOMER_INCLUSION
alter table VIS_SUBJECT_CUSTOMER_INCLUSION
  add constraint VIS_SUBJ_CUST_INCL_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_SUBJECT_CUSTOMER_INCLUSION
  add constraint VIS_SUBJ_CUST_INCL_FK2 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_SUBJECT_SUB_NETWORK
alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NETWORK_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NETWORK_FK2 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_SUBJECT_SUPERIOR_NETWORK
alter table VIS_SUBJECT_SUPERIOR_NETWORK
  add constraint VIS_SUPERIOR_NETWORK_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_SUBJECT_SUPERIOR_NETWORK
  add constraint VIS_SUPERIOR_NETWORK_FK2 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;

-- VIS_SUBJECT_WORKSCOPE
alter table VIS_SUBJECT_WORKSCOPE
  add constraint VIS_SUBJ_WORKSCOPE_FK1 foreign key (PLAN_ID)
  references VIS_PLAN (PLAN_ID);
alter table VIS_SUBJECT_WORKSCOPE
  add constraint VIS_SUBJ_WORKSCOPE_FK2 foreign key (SUBJECT_ID)
  references VIS_SUBJECT (SUBJECT_ID) on delete cascade;
