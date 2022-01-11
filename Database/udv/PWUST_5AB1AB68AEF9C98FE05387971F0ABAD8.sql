
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5AB1AB68AEF9C98FE05387971F0ABAD8.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5AB1AB68AEF9C98FE05387971F0ABAD8';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ExportControlApprovalScript';
v_udv_type sfcore_udv_lib.udv_type%type  :='SCRIPT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_EXPT_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
  AutoScroll = False
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctScript
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDefault
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = False
  BottomMargin = -1
  object JCValidDecision: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    UdvId = ''PWUST_5AB1A7D4AD41C993E05387971F0A27DC''
    ShowPrevious = False
    ShowNext = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object ExportCert: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    UdvId = ''PWUST_5A92ABA507DB681FE05387971F0A8C40''
    ShowPrevious = False
    ShowNext = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SetJCInfo: TExecScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_ExportControl_SetJCInfo''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object CompleteApproval: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''CommAcknowledgePreCmd''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object Exit1: TExitScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Accept = True
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object NewRTCDecision: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    UdvId = ''PWUST_5A92ABA507F6681FE05387971F0A8C40''
    ShowPrevious = False
    ShowNext = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object ReplaceRTC: TExecScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_ExportControl_ReplaceRTC''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object Exit2: TExitScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Accept = True
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
end';
v_iclob clob;
begin
v_iclob := p1;
begin
  insert into sfcore_udv_lib(udv_id,udv_tag,updt_userid,time_stamp,udv_type,stype,udv_desc,state,load_ref,tool_version,object_rev,owner_group,udv_definition)
    values(v_udv_id,v_udv_tag,user,sysdate,v_udv_type,v_stype,v_udv_desc,v_state,v_load_ref,v_tool_version,v_object_rev,v_owner_group,
    replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)));
  commit;
exception when dup_val_on_index then
  begin
    update sfcore_udv_lib
    set udv_definition=replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_desc = v_udv_desc,state=v_state,object_rev=v_object_rev,owner_group=v_owner_group,
      udv_type=v_udv_type,stype=v_stype
    where udv_id=v_udv_id;
    commit;
  exception when others then
  	 sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' update sfcore_udv_lib ');
  end;
when others then
  sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' insert into sfcore_udv_lib ');
end;
begin
 if v_folder_id is not null then
  insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
  values(v_udv_id,v_folder_id,user,sysdate);
  commit;
 end if;
exception when others then 
 sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' insert into sfcore_udv_folder ');
end;
end;
/

set define on

