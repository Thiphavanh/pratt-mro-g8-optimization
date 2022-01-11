
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_B61F043F3E232259E05387971F0A9895.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUSD_B61F043F3E232259E05387971F0A9895';
v_udv_tag sfcore_udv_lib.udv_tag%type :='BuyoffExeScript_Lean';
v_udv_type sfcore_udv_lib.udv_type%type  :='SCRIPT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1003;1965';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 820
  Height = 397
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctScript
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  ConsistentDialogSize = False
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
  object GetPWOrderNumber: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectPWOrderNumber''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GroupJobNoSelect: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_GroupJobNoSel''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BranchMultiUnitSkip: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    Expression = 
      ''((MULTI_UNIT_FLAG=''#39''Y''#39'' AND CROSS_ORDER_UNITS_COUNT=''#39''Y''#39''  ) OR (CRO'' +
      ''SS_ORDER_FLAG=''#39''Y''#39'' AND CROSS_ORDER_UNITS_COUNT=''#39''Y''#39'' ))''
    BranchTo = ''PWGetBuyoffRefId''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object StepBuyoffSerialSelect: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''WID_StepBuyoffSerialSelect''
    ParamsSqlSourceName = ''@ToolScope,@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object PWGetBuyoffRefId: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_GET_REF_ID''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BranchToBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''InitBadgeSwipeParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object MFI_WID_REMOVE_PRIOR_COLLECTED_MFG_BUYOFF_UNITS: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''MFI_WID_REMOVE_PRIOR_COLLECTED_MFG_BUYOFF_UNITS''
    SqlId = ''MFI_WID_REMOVE_PRIOR_COLLECTED_MFG_BUYOFF_UNITS''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SetUserIDnSecondLogintoCustom: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SetUserIDnSecondLoginToCustom''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckGroupJobNoExists: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''GROUP_JOB_NO<>''#39#39'' AND STATUS<>''#39''REJECT''#39
    BranchTo = ''LaunchBuyoff''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object GroupJobSerials: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_GroupJobSerialSelect''
    Expression = ''GROUP_JOB_NO<>''#39#39'' AND STATUS=''#39''REJECT''#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckPartialBuyoff: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''STATUS=''#39''PARTIAL''#39
    BranchTo = ''ValidPartialBuyoff''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object LaunchBuyoff: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''MFI_1CE9B087FA816FC3E0440003BA041A64''
    ShowPrevious = False
    ShowNext = False
    ShowOk = True
    Scrollbars = False
    UdvTransaction = uttUpdate
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BuyoffExit: TExitScriptStatement
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
  object GetBuyoffSignoffPW: TCommentScriptStatement
    Comment = 
      ''------------------------------- Begin Badge Swipe Section ------'' +
      ''-------------------------''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object InitBadgeSwipeParams: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_InitBadgeSwipeParamsProd2''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SelectUnits: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 310
    Width = 100
    Height = 50
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_B67AA3449DB94DA8E053EF9F1F0A2219''
    ShowPrevious = False
    ShowOk = False
    ShowUdvExpression = 
      ''STATUS = ''#39''REOPEN''#39'' OR STATUS = ''#39''PARTIAL''#39'' OR ORDER_CONTROL_TYPE=''#39''S'' +
      ''ERIAL''#39'' ''
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BadgeSwipe: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 100
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_62E7E823A73E9CA4E05387971F0A6130''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttInsert
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object LogErrorMsg1: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_BadgeSwipeLogError1''
    Expression = ''SWIPED_BADGE_ID = ''#39#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckNullBadgeId: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''BadgeSwipe''
    Expression = ''SWIPED_BADGE_ID <> ''#39#39
    MsgId = ''Swipe Badge or Type Id''
    MsgKind = mkError
    OkButton = True
    RetryButton = False
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object GetSwipedBadgeId: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''SelectUserid''
    SqlId = ''PWUST_GetSwipedBadgeId''
    Expression = ''SWIPED_BADGE_ID <> ''#39#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object LogErrorMsg2: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_BadgeSwipeLogError2''
    Expression = ''CLOCK_NO = ''#39#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckNullSwipe: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''BadgeSwipe''
    Expression = ''CLOCK_NO <> ''#39#39
    MsgId = ''Userid not found for swiped badge''
    MsgKind = mkError
    OkButton = True
    RetryButton = False
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object SelectSwipedBadgeId: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_AddBadgeIdToClockNoList''
    Expression = ''SWIPED_BADGE_ID <> ''#39#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object LogSwipedId: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_BadgeSwipeLogIns''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetNextMultipleBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''GetBuyoffType''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object TypeUserIdMultiple: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 100
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_62E7E823ACF99C9CE05387971F0A59FD''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttInsert
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SelectUserid: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_Pass_ClockNo_List''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object _OBJ1: TCommentScriptStatement
    Comment = ''------------------------------------GetBuyoffType''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetBuyoffType: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_GetBuyoffType''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object initBuyoffAcceptPaarams: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_initBuyoffAccept''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object sqlToCheckUserPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_BuyoffPrivCheckPW''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object ErrorUserHasNoPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''EndBuyoffSignoffPW''
    Expression = ''BUYOFF_PRIV_CHECK=''#39''Y''#39
    MsgId = '':BUYOFF_PRIV_CHECK''
    MsgKind = mkError
    OkButton = True
    RetryButton = False
    CancelButton = True
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object _OBJ2: TCommentScriptStatement
    Comment = ''-----------------------------SelectBuyoffAuthParams''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SelectBuyoffAuthParams: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_InitBuyoffAuthParams''
    ParamsSqlSourceName = ''@scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SelectUsersWorkCenterCerts: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_CheckUsersWorkCenterCerts''
    ParamsSqlSourceName = ''@toolscope,@scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckBuyoffAuth: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 1510
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''Exit''
    Expression = ''BUYOFF_AUTH=''#39''Y''#39
    MsgId = ''Certified signature required to complete Buyoff''
    MsgKind = mkError
    OkButton = True
    RetryButton = False
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object InsertLaborRecord: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_InsertLaborRecordOneOperFinal''
    Expression = ''STATUS <> ''#39''PARTIAL''#39'' AND STATUS <> ''#39''REOPEN''#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object InsertLaborRecordPartial: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_InsertLaborRecordOneOper''
    Expression = ''STATUS = ''#39''PARTIAL''#39
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object ReturnToBody: TIfScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 1735
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''MFI_WID_REMOVE_PRIOR_COLLECTED_MFG_BUYOFF_UNITS''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object EndBuyoffSignoffPW: TCommentScriptStatement
    Comment = 
      ''------------------------------- End Badge Swipe Section --------'' +
      ''-----------------------''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
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

