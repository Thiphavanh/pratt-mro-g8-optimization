
ALTER TABLE pwperf_stats_trend
   DROP column snap_duration;
   
ALTER TABLE pwperf_stats_trend
   MODIFY db_cpu_aps NUMBER(9,1);
   
ALTER TABLE pwperf_stats_trend
   MODIFY os_load NUMBER(9,1);
   
ALTER TABLE pwperf_stats_trend
   MODIFY host_cpu NUMBER(9,1);
   
TRUNCATE TABLE pwperf_stats_trend

ALTER TABLE pwperf_stats_trend
   MODIFY network_vol NUMBER(9,1);
