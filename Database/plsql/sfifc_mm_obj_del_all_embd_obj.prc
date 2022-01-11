create or replace procedure sfifc_mm_obj_del_all_embd_obj(vi_userid    sfcore_mm_object.updt_userid%type,
                                                          vi_object_id sfcore_mm_object.object_id%type) as
-- ****************************************************************************
-- Copyright 1995-2005 iBASEt, Inc.
-- Unpublished-rights reserved under the Copyright Laws of the United States.
-- US Government Procurements:
--         Commercial Software licensed with Restricted Rights.
-- Use, reproduction, or disclosure is subject to restrictions
-- set forth in license agreement and purchase contract.
-- iBASEt, Inc. 27442 Portola Pkwy.  Suite 300, Foothill Ranch , CA 92610
-- ****************************************************************************
-- Date Created and Created By: Shelley
-- Description:
-- Modification History:
-- 10/19/2012 Niyati Shah
--            FND-21430 Changed hardcoded length size of variables to Database Colume size
-- 03-AUG-2010 Ashish FND-15094 Update security group to null when
--             embedded object is deleted
-- 30-DEC-2005 Shelley middle tier support
-- 2018-06-05  Bob Preston SMRO_EXPT_203 Defect 826, Don't reset security_group for STDTEXT, STDOPER
-- ****************************************************************************

--pragma autonomous_transaction;
cursor c1 is select * from SFCORE_MM_HTREF
     where object_id = vi_object_id;
--v_status   sfcore_mm_object.status%type;
begin
-- 08/03/2010 Ashish FND-15094
-- Update security group to null when embedded object is deleted
update sfcore_mm_object
       set security_group = null,
       updt_userid = vi_userid
       where
       object_id = vi_object_id
       and security_group is not null
       -- Don't reset security_group for STDTEXT, STDOPER
       and object_type <> 'STDTEXT'
       and object_type <> 'STDOPER';

update sffnd_object_classification
       set security_group = null,
       updt_userid = vi_userid
       where
       object_id = vi_object_id
       and security_group is not null;

delete
  from sfcore_mm_object_sec_grp
 where object_id = vi_object_id;

end;
/
