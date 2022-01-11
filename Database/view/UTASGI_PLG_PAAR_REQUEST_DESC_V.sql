create or replace view utasgi_plg_paar_request_desc_v as
select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.plan_id,
       a.plan_version,
       a.plan_revision,
       a.plan_alterations,
       a.reply_required_date,
       a.prior_request_id,
       b.plan_no,
       b.plan_title,
       b.plan_type,
       b.rev_status,
       c.part_no,
       c.part_chg,
       c.part_title,
       a.updt_userid,
	   a.approving_user_id,
       a.time_stamp,
       a.last_action,
       c.security_group
  from utasgi_plg_paar_desc a, sfpl_plan_v b, sfpl_item_desc_master_all c
 where b.plan_id = a.plan_id
   and b.plan_version = a.plan_version
   and b.plan_revision = a.plan_revision
   and b.plan_alterations = a.plan_alterations
   and c.item_id = b.item_id
   and sfcore_has_priv(sfcore_get_solumina_user,'CAN_VIEW_ALL_PAAR_REQUESTS') = 'Y'
union
select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.plan_id,
       a.plan_version,
       a.plan_revision,
       a.plan_alterations,
       a.reply_required_date,
       a.prior_request_id,
       b.plan_no,
       b.plan_title,
       b.plan_type,
       b.rev_status,
       c.part_no,
       c.part_chg,
       c.part_title,
       a.updt_userid,
	   a.approving_user_id,
       a.time_stamp,
       a.last_action,
       c.security_group
  from utasgi_plg_paar_desc a, sfpl_plan_v b, sfpl_item_desc_master_all c
 where b.plan_id = a.plan_id
   and b.plan_version = a.plan_version
   and b.plan_revision = a.plan_revision
   and b.plan_alterations = a.plan_alterations
   and c.item_id = b.item_id
  and a.request_userid = sfcore_get_solumina_user;
/
