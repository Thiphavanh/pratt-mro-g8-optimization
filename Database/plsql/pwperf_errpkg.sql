--------------------------------------------------------
--  DDL for Package PWPERF_ERRPKG
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "SFMFG"."PWPERF_ERRPKG" AUTHID CURRENT_USER AS
    c_table  CONSTANT PLS_INTEGER := 1;-- Default
    c_file   CONSTANT PLS_INTEGER := 2;
    c_screen CONSTANT PLS_INTEGER := 3;

    PROCEDURE handle(errcode IN PLS_INTEGER := NULL,
                     errmsg  IN VARCHAR2 := NULL,
                     value_in IN VARCHAR2 := NULL,
                     logerr  IN BOOLEAN := TRUE,
                     reraise IN BOOLEAN := FALSE);

    PROCEDURE report_and_stop(err_in   IN INTEGER := SQLCODE,
                              msg_in   IN VARCHAR2 := NULL,
                              value_in IN VARCHAR2);

    PROCEDURE report_and_go(err_in   IN INTEGER := SQLCODE,
                            msg_in   IN VARCHAR2 := NULL,
                            value_in IN VARCHAR2);

    PROCEDURE RAISE(errcode IN PLS_INTEGER := NULL,
                    errmsg  IN VARCHAR2 := NULL);

    PROCEDURE log(errcode IN PLS_INTEGER := NULL,
                  errmsg  IN VARCHAR2 := NULL,
                  value_in IN VARCHAR2);

    PROCEDURE logto(target IN PLS_INTEGER,
                    dir    IN VARCHAR2 := NULL,
                    file   IN VARCHAR2 := NULL);

    FUNCTION logging_to RETURN PLS_INTEGER;
END pwperf_errpkg;
/

--------------------------------------------------------
--  DDL for Package Body PWPERF_ERRPKG
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "SFMFG"."PWPERF_ERRPKG" IS
    g_target PLS_INTEGER := c_table;
    g_file   VARCHAR2(2000) := 'errpkg.log';
    g_dir    VARCHAR2(2000) := NULL;

    PROCEDURE handle(errcode IN PLS_INTEGER := NULL,
                     errmsg  IN VARCHAR2 := NULL,
                     value_in IN VARCHAR2 := NULL,
                     logerr  IN BOOLEAN := TRUE,
                     reraise IN BOOLEAN := FALSE) IS
    BEGIN
        IF logerr
        THEN
            log(errcode,
                errmsg,
                value_in);
        END IF;

        IF reraise
        THEN
            pwperf_errpkg.RAISE(errcode,
                         errmsg);
        END IF;
    END;

    PROCEDURE report_and_stop(err_in   IN INTEGER := SQLCODE,
                              msg_in   IN VARCHAR2 := NULL,
                              value_in IN VARCHAR2) IS
    BEGIN
        handle(err_in,
               msg_in,
               value_in,
               TRUE,
               TRUE);
    END report_and_stop;

    PROCEDURE report_and_go(err_in   IN INTEGER := SQLCODE,
                            msg_in   IN VARCHAR2 := NULL,
                            value_in IN VARCHAR2) IS
    BEGIN
        handle(err_in,
               msg_in,
               value_in,
               TRUE,
               FALSE);
    END report_and_go;

    PROCEDURE RAISE(errcode IN PLS_INTEGER := NULL,
                    errmsg  IN VARCHAR2 := NULL) IS
        l_errcode PLS_INTEGER := NVL(errcode,
                                     SQLCODE);
        l_errmsg  VARCHAR2(1000) := NVL(errmsg,
                                        SQLERRM);
    BEGIN
        IF l_errcode BETWEEN - 20999 AND - 20000
        THEN
            raise_application_error(l_errcode,
                                    l_errmsg);
            /* Use positive error numbers -- lots to choose from! */
        ELSIF l_errcode > 0
              AND l_errcode NOT IN (1, 100)
        THEN
            raise_application_error(-20000,
                                    l_errcode || '-' || l_errmsg);
            /* Can't EXCEPTION_INIT -1403 */
        ELSIF l_errcode IN (100, -1403)
        THEN
            RAISE no_data_found;
            /* Re-raise any other exception. */
        ELSIF l_errcode != 0
        THEN
            EXECUTE IMMEDIATE 'DECLARE myexc EXCEPTION; ' ||
                              '   PRAGMA EXCEPTION_INIT (myexc, ' ||
                              TO_CHAR(l_errcode) || ');' ||
                              'BEGIN  RAISE myexc; END;';
        END IF;
    END;

    PROCEDURE log(errcode IN PLS_INTEGER := NULL,
                  errmsg  IN VARCHAR2 := NULL,
                  value_in IN VARCHAR2) IS
        PRAGMA AUTONOMOUS_TRANSACTION;

        l_sqlcode PLS_INTEGER := NVL(errcode,
                                     SQLCODE);
        l_sqlerrm VARCHAR2(1000) := NVL(errmsg,
                                        SQLERRM);

        CURSOR sess IS
            SELECT machine, program
              FROM sys.v_$session
             WHERE audsid = userenv('SESSIONID');
        rec sess%ROWTYPE;

    BEGIN
        OPEN sess;
        FETCH sess
            INTO rec;

        IF g_target = c_table
        THEN
            INSERT INTO pwperf_errlog
                (errcode, errmsg, errval, created_on, created_by, machine, program)
            VALUES
                (l_sqlcode, l_sqlerrm, value_in, SYSDATE, USER, rec.machine,
                 rec.program);

        ELSIF g_target = c_file
        THEN
            DECLARE
                fid utl_file.file_type;
            BEGIN
                fid := utl_file.fopen(g_dir,
                                      g_file,
                                      'A');
                utl_file.put_line(fid,
                                  'Error log by ' || USER || ' at  ' ||
                                  TO_CHAR(SYSDATE,
                                          'mm/dd/yyyy'));
                utl_file.put_line(fid,
                                  NVL(errmsg,
                                      SQLERRM));
                utl_file.fclose(fid);
            EXCEPTION
                WHEN OTHERS THEN
                    utl_file.fclose(fid);
            END;
        ELSIF g_target = c_screen
        THEN
            DBMS_OUTPUT.put_line('Error log by ' || USER || ' at  ' ||
                                 TO_CHAR(SYSDATE,
                                         'mm/dd/yyyy'));
            DBMS_OUTPUT.put_line(NVL(errmsg,
                                     SQLERRM));
        END IF;

        COMMIT;
        CLOSE sess;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
    END;

    PROCEDURE logto(target IN PLS_INTEGER,
                    dir    IN VARCHAR2 := NULL,
                    file   IN VARCHAR2 := NULL) IS
    BEGIN
        g_target := target;
        g_file   := file;
        g_dir    := dir;
    END;

    FUNCTION logging_to RETURN PLS_INTEGER IS
    BEGIN
        RETURN g_target;
    END;
END pwperf_errpkg;
/
