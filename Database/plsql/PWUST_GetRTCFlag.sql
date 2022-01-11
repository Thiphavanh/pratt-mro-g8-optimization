--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by c079222 on 10/12/2017, 4:55:49 PM --
--------------------------------------------------

--spool PWUST_GetRTCFlag.log

prompt
prompt Creating function PWUST_GETRTCFLAG
prompt ==================================
prompt
create or replace function PWUST_GetRTCFlag(vi_plan_id     varchar2,
                                                      vi_plan_version    number,
                                                      vi_plan_revision  number,
                                                      vi_plan_alterations number)
return varchar2 is
-- *********************************************************************************
-- Copyright 2014 CSC, Inc.
-- Unpublished rights reserved under the Copyright Laws of the United States.
-- File content is considered confidential information.
-- *********************************************************************************
-- Created By:  Ron Cohen - SAEP_APP_04 - 08/14/2014
-- Description: Get the Export Control Request To Classify Flag for the Plan
-- Modifications:
--
-- *********************************************************************************

v_return      varchar2(1);

BEGIN

     SELECT decode(count(UCF_COMM_FLAG3), 0, 'N', 'Y') as UCF_COMM_FLAG3
       into v_return
     FROM SFFND_COMM
     WHERE TASK_ID IN (SELECT TASK_ID
                     FROM SFFND_PLG_TASK
                    WHERE PLAN_ID = vi_plan_id
                      AND PLAN_VERSION = vi_plan_version
                      AND PLAN_REVISION = vi_plan_revision
                      AND PLAN_ALTERATIONS = vi_plan_alterations)
     AND COMM_STATUS IN ('IN PROCESS', 'COMPLETE')
     AND UCF_COMM_FLAG3 = 'Y'
     AND subject LIKE '%EXPT%';

  return(v_return);

END PWUST_GetRTCFlag;
/
grant execute on PWUST_GETRTCFLAG to SF$CONNECTION_POOL;



--spool off
