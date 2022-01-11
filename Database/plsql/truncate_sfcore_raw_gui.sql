CREATE OR REPLACE PROCEDURE truncate_sfcore_raw_gui
AS
  -- ****************************************************************************
  -- Copyright 2018 CSC
  -- Unpublished-rights reserved under the Copyright Laws of the United States.
  -- US Government Procurements:
  --         Commercial Software licensed with Restricted Rights.
  -- Use, reproduction, or disclosure is subject to restrictions
  -- set forth in license agreement and purchase contract.
  -- ****************************************************************************
  -- Date Created and Created By:
  -- 2018-06-27	R. Thorpe	
  -- Copied from G7 OEM 
  -- ****************************************************************************
  crsor INTEGER;
  rval  INTEGER;
BEGIN
  crsor := DBMS_SQL.open_cursor;
  DBMS_SQL.parse(crsor,
                 'truncate table SFMFG.sfcore_raw_gui_event_data',
                 DBMS_SQL.native);
  rval := DBMS_SQL.EXECUTE(crsor);
END truncate_sfcore_raw_gui;
/
