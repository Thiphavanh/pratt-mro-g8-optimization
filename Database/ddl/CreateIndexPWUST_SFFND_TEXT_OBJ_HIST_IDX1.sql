--- Defect 1353 - create index to fix performance issue

CREATE INDEX sfmfg.pwust_sffnd_text_obj_hist_idx1 
  ON sffnd_text_object_hist (object_id,  language_code)
TABLESPACE sfhistory_indexes;
