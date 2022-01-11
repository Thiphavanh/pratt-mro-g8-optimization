create or replace view pwmroi_manual_latest_rev_v as
(select a.manual_no,a.manual_rev , a.revision_create_date from
        pwmroi_manual_desc a  where
         a.revision_create_Date = 
           (select max(revision_create_date) from pwmroi_manual_desc b where a.manual_no = b.manual_no));
/