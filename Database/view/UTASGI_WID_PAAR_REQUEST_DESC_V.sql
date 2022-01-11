create or replace view utasgi_wid_paar_request_desc_v as
select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.order_id,
       b.order_no,
       a.reply_required_date,
       a.prior_request_id,
       b.plan_title,
       b.plan_type,
       b.order_status,
       c.part_no,
       c.part_chg,
       c.part_title,
       a.updt_userid,
	   a.approving_user_id,
       a.time_stamp,
       a.last_action,
       c.security_group
  from utasgi_wid_paar_desc a, sfwid_order_v b, sfpl_item_desc_master_all c
 where b.order_id = a.order_id
   and c.item_id = b.item_id
   and sfcore_has_priv(sfcore_get_solumina_user,'CAN_VIEW_ALL_PAAR_REQUESTS') = 'Y'
union
select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.order_id,
       b.order_no,
       a.reply_required_date,
       a.prior_request_id,
       b.plan_title,
       b.plan_type,
       b.order_status,
       c.part_no,
       c.part_chg,
       c.part_title,
       a.updt_userid,
	   a.approving_user_id,
       a.time_stamp,
       a.last_action,
       c.security_group
  from utasgi_wid_paar_desc a, sfwid_order_v b, sfpl_item_desc_master_all c
 where b.order_id = a.order_id
   and c.item_id = b.item_id
   and a.request_userid = sfcore_get_solumina_user;
/
