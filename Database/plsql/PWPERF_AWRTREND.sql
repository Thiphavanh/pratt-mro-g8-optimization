--------------------------------------------------------
--  DDL for Package PWPERF_AWRTREND
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "SFMFG"."PWPERF_AWRTREND" AUTHID CURRENT_USER AS
    /*
    || Author  : Laurent Lafosse
    || Created : 10/14/11 9:19:10 AM
    || Purpose : Collect Statistics for ODM in Oracle
    || Modifications:
    ||    Date   Author    Description
    || 25-APR-18 Lafosse   Modified the collection of get_dbinfo and get_file_space
    ||                        to run on the 1st day of the month
    || 09-APR-15 Lafosse   Modified get_dbinfo, get_top5 procs and get_most_buffer_gets
    ||                        to include a whole day of data.  Used a collection.
    || 08-AUG-13 Lafosse   Changed the date for overseas databases that are hosted in the USA
    || 15-DEC-11 Lafosse   1st version
    */
    -- Public variable declarations
    g_nls_timestamp VARCHAR2(200);
    g_begin_snap    NUMBER(6);
    g_end_snap      NUMBER(6);
    g_start_date    DATE;
    g_check_day     DATE;
    g_end_date      DATE;
    g_dbid          dba_hist_database_instance.dbid%TYPE;
    g_instance_no   dba_hist_database_instance.instance_number%TYPE;
    g_db_name       dba_hist_database_instance.db_name%TYPE;
    g_host_name     dba_hist_database_instance.host_name%TYPE;
    g_start         NUMBER;
    g_secs          NUMBER(6, 2);

    TYPE snapshot_rec_t IS RECORD(
        snap_id         dba_hist_snapshot.snap_id%TYPE,
        dbid            dba_hist_database_instance.dbid%TYPE,
        instance_number dba_hist_database_instance.instance_number%TYPE,
        db_name         dba_hist_database_instance.db_name%TYPE,
        host_name       dba_hist_database_instance.host_name%TYPE,
        begin_interval  dba_hist_snapshot.begin_interval_time%TYPE,
        end_interval    dba_hist_snapshot.end_interval_time%TYPE,
        --elapsed_time        pwperf_stats_trend.snap_duration%TYPE,
        hard_parses_sec     dba_hist_sysmetric_summary.average%TYPE,
        execs_sec           dba_hist_sysmetric_summary.average%TYPE,
        trans_sec           dba_hist_sysmetric_summary.average%TYPE,
        library_hit_ratio   dba_hist_sysmetric_summary.average%TYPE,
        soft_parse_ratio    dba_hist_sysmetric_summary.average%TYPE,
        exec_wo_parse_ratio dba_hist_sysmetric_summary.average%TYPE,
        os_load             dba_hist_sysmetric_summary.average%TYPE,
        db_cpu_aps          dba_hist_sysmetric_summary.average%TYPE,
        host_cpu            dba_hist_sysmetric_summary.average%TYPE,
        network_vol         dba_hist_sysmetric_summary.average%TYPE,
        phy_reads_rqs       dba_hist_sysmetric_summary.average%TYPE,
        phy_reads_bps       dba_hist_sysmetric_summary.average%TYPE,
        phy_writes_rqs      dba_hist_sysmetric_summary.average%TYPE,
        phy_writes_bps      dba_hist_sysmetric_summary.average%TYPE,
        redo_iops           dba_hist_sysmetric_summary.average%TYPE,
        redo_bps            dba_hist_sysmetric_summary.average%TYPE);

    TYPE snapshot_tab IS TABLE OF snapshot_rec_t;
    daily_snaps snapshot_tab;

    TYPE rc IS REF CURSOR RETURN snapshot_rec_t;

    -- Public function and procedure declarations
    PROCEDURE main(p_date IN DATE DEFAULT SYSDATE);

    PROCEDURE get_dbinfo;

    PROCEDURE get_top5;

    PROCEDURE get_most_buffer_gets;

    PROCEDURE get_file_space;

END pwperf_awrtrend;
/

--------------------------------------------------------
--  DDL for Package Body PWPERF_AWRTREND
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "SFMFG"."PWPERF_AWRTREND" AS
    /*-- Private constant declarations*/
    c_max_buffer_gets CONSTANT NUMBER := 100000;
    c_tsql_max        CONSTANT NUMBER := 10;
    /*-- Private variable declarations*/
    /*--p_elapsed_time        pwperf_stats_trend.snap_duration%TYPE;*/
    l_value    VARCHAR2(200) := NULL;
    l_message  VARCHAR2(200) := NULL;
    l_last_rec NUMBER(9);

    PROCEDURE main(p_date IN DATE DEFAULT SYSDATE) AS
    BEGIN

        g_start_date := TO_CHAR(p_date, 'DD-MON-YYYY');
        g_start      := dbms_utility.get_time;

        SELECT NAME INTO g_db_name FROM v$database;

        pwperf_awrtrend.get_dbinfo;
        pwperf_awrtrend.get_top5;
        pwperf_awrtrend.get_most_buffer_gets;

        /* Save 1st day of the month */
        SELECT TO_CHAR(TRUNC(SYSDATE, 'MM'), 'DD-MON-YYYY')
          INTO g_check_day
          FROM dual;

        /* compare today with 1st day of the month */
        IF g_start_date = g_check_day
        THEN
            pwperf_awrtrend.get_file_space;
        END IF;

        g_secs := (dbms_utility.get_time - g_start) / 100;
        --dbms_output.put_line('Completed in ' || g_secs || ' secs');

    END main;

    PROCEDURE get_dbinfo AS
        snap_cv rc;
        bulk_errors EXCEPTION;
        PRAGMA EXCEPTION_INIT(bulk_errors, -24381);

    BEGIN
        g_start := dbms_utility.get_time;
        --DBMS_OUTPUT.new_line;
        --DBMS_OUTPUT.put_line('Starting get_dbinfo');
        l_value := 'awrtrend.get_dbinfo errored for input date of: ' ||
                   TO_CHAR(g_start_date, 'DD-MON-YYYY HH24:MI');

        /*  Calculates the Elapsed_Time in Seconds
        SELECT SUM((EXTRACT(hour FROM end_interval_time) -
        EXTRACT(hour FROM begin_interval_time)) * 3600 +
        (EXTRACT(minute FROM end_interval_time) -
        EXTRACT(minute FROM begin_interval_time)) * 60 +
        EXTRACT(SECOND FROM end_interval_time) -
        EXTRACT(SECOND FROM begin_interval_time))
        INTO p_elapsed_time
        FROM dba_hist_snapshot
        WHERE snap_id = g_begin_snap
        AND dbid = g_dbid
        AND instance_number = g_instance_no;
        */
        OPEN snap_cv FOR
            SELECT s.snap_id, di.dbid, di.instance_number, di.db_name,
                   di.host_name, MIN(s.begin_interval_time) AS start_date,
                   MIN(s.end_interval_time) AS end_date,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Hard Parse Count Per Sec' THEN
                                   average
                              END),
                          1) hard_parses_sec,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Executions Per Sec' THEN
                                   average
                              END),
                          1) execs_sec,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'User Transaction Per Sec' THEN
                                   average
                              END),
                          1) trans_sec,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Library Cache Hit Ratio' THEN
                                   average
                              END),
                          1) library_hit_ratio,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Soft Parse Ratio' THEN
                                   average
                              END),
                          1) soft_parse_ratio,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Execute Without Parse Ratio' THEN
                                   average
                              END),
                          1) exec_wo_parse_ratio,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Current OS Load' THEN
                                   average
                              END),
                          1) os_load,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'CPU Usage Per Sec' THEN
                                   average
                              END),
                          1) db_cpu_aps,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Host CPU Utilization (%)' THEN
                                   average
                              END),
                          1) host_cpu,
                   /*-- note 100% = 1 loaded rac node SUM(CASE metric_name*/
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Network Traffic Volume Per Sec' THEN
                                   average
                              END)) network_vol,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Physical Read Total IO Requests Per Sec' THEN
                                   average
                              END)) phy_reads,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Physical Write Total IO Requests Per Sec' THEN
                                   average
                              END)) phy_writes,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Redo Writes Per Sec' THEN
                                   average
                              END)) physical_redo_iops,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Physical Read Total Bytes Per Sec' THEN
                                   average
                              END)) physical_read_total_bps,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Physical Write Total Bytes Per Sec' THEN
                                   average
                              END)) physical_write_total_bps,
                   ROUND(SUM(CASE metric_name
                                  WHEN 'Redo Generated Per Sec' THEN
                                   average
                              END)) redo_bytes_per_sec
              FROM dba_hist_database_instance di
              JOIN dba_hist_snapshot s
                ON di.dbid = s.dbid
               AND di.instance_number = s.instance_number
               AND di.startup_time = s.startup_time
              JOIN dba_hist_sysmetric_summary h
                ON s.snap_id = h.snap_id
               AND s.instance_number = h.instance_number
               AND TRUNC(s.begin_interval_time) =
                   TO_DATE(g_start_date, 'DD-MON-YY')
             GROUP BY s.snap_id, di.dbid, di.instance_number, di.db_name,
                      di.host_name
             ORDER BY s.snap_id;

        FETCH snap_cv BULK COLLECT
            INTO daily_snaps;
        CLOSE snap_cv;

        l_last_rec := daily_snaps.last;
        g_end_snap := g_begin_snap + 1;

        FORALL i IN daily_snaps.first .. l_last_rec
            INSERT INTO pwperf_stats_trend
                (snap_id, dbid, instance_number, db_name, begin_snap_time,
                 end_snap_time, hard_parses_sec, execs_sec, trans_sec,
                 library_hit_ratio, soft_parse_ratio, exec_wo_parse_ratio,
                 os_load, db_cpu_aps, host_cpu, network_vol, phy_reads_rqs,
                 phy_reads_bps, phy_writes_rqs, phy_writes_bps, redo_iops,
                 redo_bps)
            VALUES
                (daily_snaps(i).snap_id, daily_snaps(i).dbid,
                 daily_snaps(i).instance_number, daily_snaps(i).db_name,
                 daily_snaps(i).begin_interval, daily_snaps(i).end_interval,
                 daily_snaps(i).hard_parses_sec, daily_snaps(i).execs_sec,
                 daily_snaps(i).trans_sec, daily_snaps(i).library_hit_ratio,
                 daily_snaps(i).soft_parse_ratio,
                 daily_snaps(i).exec_wo_parse_ratio, daily_snaps(i).os_load,
                 daily_snaps(i).db_cpu_aps, daily_snaps(i).host_cpu,
                 daily_snaps(i).network_vol, daily_snaps(i).phy_reads_rqs,
                 daily_snaps(i).phy_reads_bps, daily_snaps(i).phy_writes_rqs,
                 daily_snaps(i).phy_writes_bps, daily_snaps(i).redo_iops,
                 daily_snaps(i).redo_bps);

        COMMIT;

        g_secs := (dbms_utility.get_time - g_start) / 100;
        --DBMS_OUTPUT.put_line('Get dbinfo: ' || g_secs || ' secs');

    EXCEPTION
        WHEN bulk_errors THEN
            FOR indx IN 1 .. SQL%bulk_exceptions.count
            LOOP
                dbms_output.put_line('Error ' || indx ||
                                     ' occurred during ' || 'iteration ' || SQL%BULK_EXCEPTIONS(indx)
                                     .error_index ||
                                     ' inserting into pwperf_stats_trend ' || SQL%BULK_EXCEPTIONS(indx)
                                     .error_index);
                dbms_output.put_line('Oracle error is ' ||
                                     SQLERRM(-1 * SQL%BULK_EXCEPTIONS(indx)
                                             .error_code));
            END LOOP;
        WHEN no_data_found THEN
            pwperf_errpkg.report_and_stop(NULL, NULL, l_value);
        WHEN OTHERS THEN
            pwperf_errpkg.report_and_stop(NULL, NULL, l_value);
    END get_dbinfo;

    PROCEDURE get_top5 AS
        l_cpu_time NUMBER;
        l_db_time  NUMBER;

        CURSOR top5_cur IS
            SELECT *
              FROM (SELECT event, wtfg AS waits, tmfg AS TIME,
                            ROUND(DECODE(wtfg,
                                          0,
                                          TO_NUMBER(NULL),
                                          tmfg / wtfg) / 1000) avwait,
                            ROUND((tmfg / (l_db_time + l_cpu_time) * 100), 2) AS pctwtt
                       FROM (SELECT e.event_name event,
                                     CASE
                                         WHEN e.total_waits_fg IS NOT NULL THEN
                                          e.total_waits_fg -
                                          NVL(b.total_waits_fg, 0)
                                         ELSE
                                          (e.total_waits - NVL(b.total_waits, 0)) -
                                          GREATEST(0,
                                                   (NVL(ebg.total_waits, 0) -
                                                   NVL(bbg.total_waits, 0)))
                                     END wtfg,
                                     CASE
                                         WHEN e.time_waited_micro_fg IS NOT NULL THEN
                                          ROUND((e.time_waited_micro_fg -
                                                NVL(b.time_waited_micro_fg, 0)) /
                                                1000000)
                                         ELSE
                                          (e.time_waited_micro -
                                          NVL(b.time_waited_micro, 0)) -
                                          GREATEST(0,
                                                   (NVL(ebg.time_waited_micro, 0) -
                                                   NVL(bbg.time_waited_micro, 0)))
                                     END tmfg,
                                     CASE
                                         WHEN e.total_timeouts_fg IS NOT NULL THEN
                                          e.total_timeouts_fg -
                                          NVL(b.total_timeouts_fg, 0)
                                         ELSE
                                          (e.total_timeouts -
                                          NVL(b.total_timeouts, 0)) -
                                          GREATEST(0,
                                                   (NVL(ebg.total_timeouts, 0) -
                                                   NVL(bbg.total_timeouts, 0)))
                                     END ttofg
                                FROM dba_hist_system_event b,
                                     dba_hist_system_event e,
                                     dba_hist_bg_event_summary bbg,
                                     dba_hist_bg_event_summary ebg
                               WHERE b.snap_id(+) = g_begin_snap
                                 AND e.snap_id = g_end_snap
                                 AND bbg.snap_id(+) = g_begin_snap
                                 AND ebg.snap_id(+) = g_end_snap
                                 AND e.dbid = g_dbid
                                 AND e.dbid = b.dbid(+)
                                 AND e.instance_number = g_instance_no
                                 AND e.instance_number = b.instance_number(+)
                                 AND e.event_id = b.event_id(+)
                                 AND e.dbid = ebg.dbid(+)
                                 AND e.instance_number = ebg.instance_number(+)
                                 AND e.event_id = ebg.event_id(+)
                                 AND e.dbid = bbg.dbid(+)
                                 AND e.instance_number = bbg.instance_number(+)
                                 AND e.event_id = bbg.event_id(+)
                                 AND e.total_waits > NVL(b.total_waits, 0)
                                 AND e.wait_class <> 'Idle'
                              UNION ALL
                              SELECT 'DB CPU' event, TO_NUMBER(NULL) wtfg,
                                     l_cpu_time tmfg, TO_NUMBER(NULL) ttofg
                                FROM dual
                               WHERE l_cpu_time > 0)
                      ORDER BY tmfg DESC, wtfg DESC)
             WHERE rownum <= 5;

        top5_rec top5_cur%ROWTYPE;

    BEGIN
        g_start    := dbms_utility.get_time;
        l_last_rec := daily_snaps.last;
        --DBMS_OUTPUT.new_line;
        --DBMS_OUTPUT.put_line('Begin Snap: '||g_begin_snap ||', End Snap: '||g_end_snap);
        l_value := 'awrtrend.get_top5_ errored for input date of: ' ||
                   TO_CHAR(g_start_date, 'DD-MON-YYYY HH24:MI');

        FOR i IN daily_snaps.first .. l_last_rec
        LOOP
            --DBMS_OUTPUT.PUT_LINE(daily_snaps(i).snap_id || ', ' || daily_snaps(i).begin_interval);
            g_begin_snap  := daily_snaps(i).snap_id;
            g_end_snap    := daily_snaps(i).snap_id + 1;
            g_dbid        := daily_snaps(i).dbid;
            g_instance_no := daily_snaps(i).instance_number;
            g_start_date  := daily_snaps(i).begin_interval;

            SELECT ROUND(((b.value - a.value) / 1000000), 0) AS "CPU"
              INTO l_cpu_time
              FROM dba_hist_sys_time_model a, dba_hist_sys_time_model b
             WHERE a.snap_id = g_begin_snap
               AND b.snap_id = g_end_snap
               AND a.dbid = b.dbid
               AND a.dbid = daily_snaps(i).dbid
               AND a.instance_number = b.instance_number
               AND b.instance_number = daily_snaps(i).instance_number
               AND a.stat_name = b.stat_name
               AND a.stat_name = 'DB CPU';
            --
            SELECT ROUND(((b.value - a.value) / 1000000), 0)
              INTO l_db_time
              FROM dba_hist_sys_time_model a, dba_hist_sys_time_model b
             WHERE a.snap_id = g_begin_snap
               AND b.snap_id = g_end_snap
               AND a.dbid = b.dbid
               AND a.instance_number = b.instance_number
               AND a.dbid = daily_snaps(i).dbid
               AND b.instance_number = daily_snaps(i).instance_number
               AND a.stat_name = b.stat_name
               AND a.stat_name = 'DB time';
            /*
            DBMS_OUTPUT.PUT_LINE(' Snapshot =' || daily_snaps(i)
                                 .snap_id || ', Start Date =' || daily_snaps(i)
                                 .begin_interval || ', CPU =' || l_cpu_time ||
                                 ', DB Time =' || l_db_time);
            */
            FOR top5_rec IN top5_cur
            LOOP
                INSERT INTO pwperf_event_trend
                    (snap_id, dbid, instance_number, event, waits, TIME,
                     pctwtt, begin_snap_time, db_name)
                VALUES
                    (g_begin_snap, g_dbid, g_instance_no, top5_rec.event,
                     top5_rec.waits, top5_rec.time, top5_rec.pctwtt,
                     g_start_date, g_db_name);
            END LOOP;

        END LOOP;
        COMMIT;

        g_secs := (dbms_utility.get_time - g_start) / 100;
        --DBMS_OUTPUT.put_line('Get top5: ' || g_secs || ' secs');

    EXCEPTION
        WHEN no_data_found THEN
            pwperf_errpkg.report_and_go(NULL, NULL, l_value);
        WHEN OTHERS THEN
            dbms_output.put_line('Error stack at top level:');
            pwperf_errpkg.report_and_stop(NULL, NULL, l_value);

    END get_top5;

    PROCEDURE get_most_buffer_gets AS
        CURSOR buffer_cur IS
            SELECT *
              FROM (SELECT sqt.sql_id AS sql_id, sqt.bget AS buffer_gets,
                            sqt.dget AS disk_reads, sqt.exec AS executions,
                            ROUND(DECODE(sqt.exec,
                                          0,
                                          TO_NUMBER(NULL),
                                          (sqt.bget / sqt.exec)),
                                   2) AS bgets_exec,
                            NVL((sqt.cput / 1000000), TO_NUMBER(NULL)) AS cpu_time,
                            ROUND(NVL((sqt.elap / 1000000), TO_NUMBER(NULL)),
                                   2) elapsed_time, sqt.module AS module,
                            NVL(dbms_lob.substr(st.sql_text, 500, 1),
                                 '** SQL Text Not Available **') AS sql_text
                       FROM (SELECT sql_id, MAX(module) module,
                                     SUM(buffer_gets_delta) bget,
                                     SUM(disk_reads_delta) dget,
                                     SUM(executions_delta) exec,
                                     SUM(cpu_time_delta) cput,
                                     SUM(elapsed_time_delta) elap
                                FROM dba_hist_sqlstat
                               WHERE dbid = g_dbid
                                 AND instance_number = g_instance_no
                                 AND g_begin_snap < snap_id
                                 AND snap_id <= g_end_snap
                               GROUP BY sql_id) sqt, dba_hist_sqltext st
                      WHERE st.sql_id(+) = sqt.sql_id
                        AND st.dbid(+) = g_dbid
                        AND sqt.bget >= c_max_buffer_gets
                      ORDER BY NVL(sqt.bget, -1) DESC, sqt.sql_id)
             WHERE rownum <= c_tsql_max;

    BEGIN
        g_start := dbms_utility.get_time;
        --DBMS_OUTPUT.new_line;
        --DBMS_OUTPUT.put_line('Starting most_buffer_gets');
        l_value := 'awrtrend.get_most_buffer_gets errored for input date of: ' ||
                   TO_CHAR(g_start_date, 'DD-MON-YYYY HH24:MI');

        FOR i IN daily_snaps.first .. l_last_rec
        LOOP
            /*--DBMS_OUTPUT.PUT_LINE(daily_snaps(i).snap_id || ', ' ||
            -- daily_snaps(i).begin_interval);*/
            g_begin_snap  := daily_snaps(i).snap_id;
            g_end_snap    := daily_snaps(i).snap_id + 1;
            g_dbid        := daily_snaps(i).dbid;
            g_instance_no := daily_snaps(i).instance_number;
            g_start_date  := daily_snaps(i).begin_interval;

            FOR buffer_rec IN buffer_cur
            LOOP
                INSERT INTO pwperf_lios_trend
                    (snap_id, dbid, instance_number, sql_id, module,
                     buffer_gets, executions, bgets_per_exec, disk_reads,
                     cpu_time, elapsed_time, sql_text, begin_snap_time,
                     db_name)
                VALUES
                    (g_begin_snap, g_dbid, g_instance_no, buffer_rec.sql_id,
                     buffer_rec.module, buffer_rec.buffer_gets,
                     buffer_rec.executions, buffer_rec.bgets_exec,
                     buffer_rec.disk_reads, buffer_rec.cpu_time,
                     buffer_rec.elapsed_time, buffer_rec.sql_text,
                     g_start_date, g_db_name);
            END LOOP;
        END LOOP;
        COMMIT;

        g_secs := (dbms_utility.get_time - g_start) / 100;
        --DBMS_OUTPUT.put_line('Get most buffer gets: ' || g_secs || ' secs');

    EXCEPTION
        WHEN no_data_found THEN
            pwperf_errpkg.report_and_go(NULL, NULL, l_value);
        WHEN OTHERS THEN
            pwperf_errpkg.report_and_stop(NULL, NULL, l_value);
    END get_most_buffer_gets;

    PROCEDURE get_file_space AS
        CURSOR space_cur IS
            SELECT ROUND(SUM(bytes / power(1024, 3)), 1) AS used_gb,
                   SUM(blocks) AS used_blocks
              FROM (SELECT bytes, blocks
                       FROM dba_data_files
                     UNION ALL
                     SELECT bytes, blocks
                       FROM dba_temp_files);

        used_gb     pwperf_space_trend.used_gb%TYPE;
        used_blocks pwperf_space_trend.used_blocks%TYPE;

    BEGIN
        g_start := dbms_utility.get_time;
        --DBMS_OUTPUT.new_line;
        --DBMS_OUTPUT.put_line('Start get_file_space');

        l_value := 'awrtrend.get_file_space errored for input date of: ' ||
                   TO_CHAR(g_start_date, 'DD-MON-YYYY HH24:MI');

        SELECT NAME, dbid INTO g_db_name, g_dbid FROM v$database;
        SELECT host_name INTO g_host_name FROM v$instance;

        FOR space_rec IN space_cur
        LOOP
            INSERT INTO pwperf_space_trend
                (snap_date, dbid, db_name, host_name, used_blocks, used_gb)
            VALUES
                (g_start_date, g_dbid, g_db_name, g_host_name,
                 space_rec.used_blocks, space_rec.used_gb);

        END LOOP;
        COMMIT;

        g_secs := (dbms_utility.get_time - g_start) / 100;
        --DBMS_OUTPUT.put_line('Get file space: ' || g_secs || ' secs');

    EXCEPTION
        WHEN no_data_found THEN
            pwperf_errpkg.report_and_go(NULL, l_message, l_value);
        WHEN OTHERS THEN
            pwperf_errpkg.report_and_stop(NULL, l_message, l_value);
    END get_file_space;

END pwperf_awrtrend;
/