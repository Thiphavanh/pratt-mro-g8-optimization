Prompt ... Creating AWRTrend ROLE

CREATE ROLE awrtrend_role;

Prompt ... Creating AWRTrend Grants

GRANT SELECT, INSERT, UPDATE, DELETE ON pwperf_space_trend TO awrtrend_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON pwperf_stats_trend TO awrtrend_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON pwperf_event_trend TO awrtrend_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON pwperf_lios_trend TO awrtrend_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON pwperf_errlog TO awrtrend_role;
GRANT awrtrend_role TO xdbd144, solperfreporter_only;
GRANT EXECUTE ON pwperf_awrtrend TO xdbd144;

Prompt ... Creating AWRTrend public synonymns

CREATE PUBLIC SYNONYM pwperf_space_trend FOR pwperf_space_trend;
CREATE PUBLIC SYNONYM pwperf_stats_trend FOR pwperf_stats_trend;
CREATE PUBLIC SYNONYM pwperf_event_trend FOR pwperf_event_trend;
CREATE PUBLIC SYNONYM pwperf_lios_trend FOR pwperf_lios_trend;
CREATE PUBLIC SYNONYM pwperf_awrtrend FOR pwperf_awrtrend;
CREATE PUBLIC SYNONYM pwperf_errlog FOR pwperf_errlog;