-- Create sequence 
create sequence PWUST_PLANNED_ORDERS_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 500
order;

grant select on PWUST_PLANNED_ORDERS_SEQ to sf$connection_pool 