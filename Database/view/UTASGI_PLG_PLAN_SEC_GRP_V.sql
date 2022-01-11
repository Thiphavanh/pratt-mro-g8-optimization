create or replace view utasgi_plg_plan_sec_grp_v as
select a.plan_id,
       a.plan_version,
       a.plan_revision,
       a.plan_alterations,
       b.plan_updt_no,
       b.plan_no,
       d.security_group,
       e.security_group_type,
       e.issuing_agency,
       d.obsolete_record_flag,
       d.updt_userid,
       d.time_stamp,
       e.security_group_desc,
       e.ucf_sec_grp_flag1,
       e.expiration_date
  from sffnd_security_group_def e,
       sfpl_plan_desc_sec_grp d,
       sfpl_plan_desc b,
       table(sffnd_parse_fcn(b.security_group,',')) c,
       sfpl_plan_rev a
 where b.plan_id = a.plan_id
   and b.plan_updt_no = a.plan_updt_no
   and d.security_group = c.column_value
   and e.security_group = c.column_value;
/
