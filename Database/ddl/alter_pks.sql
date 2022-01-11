begin
update pwmroi_plan_subject_authority set authority_Detail='NOT NULL' where authority_detail is null;
update pwmroi_order_subject_authority set authority_Detail='NOT NULL' where authority_detail is null;
commit;
end;
/
alter table pwmroi_plan_subject_authority drop constraint pwmroi_plan_subj_auth_pk drop index;


alter table pwmroi_plan_subject_authority
add constraint pwmroi_plan_subj_auth_pk PRIMARY KEY (PLAN_ID, PLAN_REVISION, SUBJECT_NO, SUBJECT_REV, AUTHORITY_TYPE, AUTHORITY_DETAIL, 
                                                     MANUAL_NO, MANUAL_REV) ;

alter table pwmroi_order_subject_authority drop constraint pwmroi_wosubauth_pk drop index;

alter table pwmroi_order_subject_authority
add constraint pwmroi_order_subj_auth_pk PRIMARY KEY (ORDER_ID, SUBJECT_NO, SUBJECT_REV, AUTHORITY_TYPE, AUTHORITY_DETAIL, 
                                                     MANUAL_NO, MANUAL_REV);
