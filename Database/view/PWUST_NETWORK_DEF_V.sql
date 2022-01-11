CREATE OR REPLACE VIEW PWUST_NETWORK_DEF_V AS
SELECT T.SUPERIOR_NETWORK_ACT || '  , ' || T.SUPERIOR_NETWORK_ACT_DESC as SUP_NET_ACT_PLUS_DESC,
       T.SUB_NETWORK_ACT      || '  , ' || T.SUB_NETWORK_ACT_DESC AS SUB_NET_ACT_PLUS_DESC,
       T.ENGINE_TYPE,
       T.SUPERIOR_NETWORK_ACT,
       T.SUB_NETWORK_ACT
FROM PWUST_NETWORK_DEF T
ORDER BY T.SUPERIOR_NETWORK_ACT;
/
grant select on pwust_network_def_v to pratt_read_only ;
grant select on pwust_network_def_v to sf$connection_pool ;
