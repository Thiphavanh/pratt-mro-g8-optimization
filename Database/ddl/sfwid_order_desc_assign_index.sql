CREATE UNIQUE INDEX sfwid_order_desc_pk ON sfwid_order_desc (order_id) TABLESPACE sfwid_indexes;

ALTER TABLE sfwid_order_desc MODIFY CONSTRAINT sfwid_order_desc_pk USING INDEX sfwid_order_desc_pk ; 
