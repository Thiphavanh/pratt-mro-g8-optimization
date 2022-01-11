--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by c079222 on 10/12/2017, 4:56:09 PM --
--------------------------------------------------

--spool PWUST_EXPTCTRL_APPRVL_MESSAGES.log

prompt
prompt Creating function PWUST_EXPTCTRL_APPRVL_MESSAGES
prompt ================================================
prompt
create or replace function PWUST_ExptCtrl_Apprvl_Messages(vi_called_from  varchar2)
return varchar2 is
-- *********************************************************************************
-- Copyright 2013 CSC, Inc.
-- Unpublished rights reserved under the Copyright Laws of the United States.
-- File content is considered confidential information.
-- *********************************************************************************
-- Created By:  Ron Cohen - SAEP_APP_04 - 08/14/2014
-- Description: Return the appropriate message text for the calling udv
-- *********************************************************************************

vo_return         varchar2(750);

BEGIN

  if vi_called_from = 'CERTIFICATION' then

     vo_return := 'Do you agree with the following revision under review:' || CHR(10)
                  ||CHR(32)||CHR(32)||'No new materials or coatings (e.g. changing the material from INCO17 to INCOXX)'|| CHR(10)
                  ||'were introduced into the Process Plan' || CHR(10)
                  ||CHR(32)||CHR(32)||'No new cooling technologies (e.g. adding film cooling to a previously uncooled part)'|| CHR(10)
                  ||'were introduced into the Process Plan' || CHR(10)
                  ||CHR(32)||CHR(32)||'No new manufacturing techniques (e.g. welding, grinding, etc.) were introduced into the'|| CHR(10)
                  ||'Process Plan' || CHR(10)
                  ||CHR(32)||CHR(32)||'No new pictures or other media that could change the J' || CHR(38) || 'C were added to the Process'|| CHR(10)
                  ||'Plan' || CHR(10)
                  ||'Your certification of the J' || CHR(38) || 'C will be recorded in the xClass system';

  else

    if vi_called_from = 'NEWRTC' then

     vo_return := 'You have indicated that a new J' || CHR(38) || 'C may be required. ' || CHR(10)
                  ||'In order to process this request, the following steps are required:'|| CHR(10)
                  ||'1) A ' || CHR(34) || 'Request to Classify' || CHR(34) || '  will automatically be created for this plan in xClass'|| CHR(10)
                  ||'2) No export approvals will be allowed until the J' || CHR(38) || 'C has been completed in xClass' || CHR(10)
                  ||'3) If it becomes necessary to make a change to the Process Plan, the ' || CHR(34) || 'Request to Classify' || CHR(34) || CHR(10)
                  ||'must be canceled by rejecting the Approval Communication or Return To Authoring.' || CHR(10)
                  ||'Continue with RTC Request?';




    else

       if vi_called_from = 'UEA' then

           vo_return := 'Warning: This Plan contains operations that have been JCL2 approved, but have not been released. ' || CHR(10)
                  ||'This condition must be resolved before a new ' || CHR(34) || 'Request to Classify' || CHR(34) || ' can be created in eClass. '|| CHR(10)
                  ||'You have two options: Cancel this request and ' || CHR(10)
                  ||'Option 1) Approve/release any open operations that have been JCL2 approved and not released. '|| CHR(10)
                  ||'Option 2) Reject those operations that have been JCL2 approved but not released from the approval cycle. ';

       end if;

    end if;

  end if;

  return(vo_return);

END PWUST_ExptCtrl_Apprvl_Messages;
/
grant execute on PWUST_EXPTCTRL_APPRVL_MESSAGES to SF$CONNECTION_POOL;



--spool off
