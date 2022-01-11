
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_C9B065C85294AA3CE05387971F0A1584.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_C9B065C85294AA3CE05387971F0A1584';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_BUYOFF_STATUS_TRAINING_SCRIPT';
v_udv_type sfcore_udv_lib.udv_type%type  :='SCRIPT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1965';
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
  object InitTagType: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 50
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_InitBuyoffTags''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object StepBuyoffSerialSelect: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_MFI_WID_GetCurrentUnits''
    ParamsSqlSourceName = ''@Toolscope,@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object InitBadgeSwipeParams: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 910
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_InitBadgeSwipeParamsNAND''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object _OBJ2: TCommentScriptStatement
    Comment = ''-----------GetTrainingBuyoffComments''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''-----------GetTrainingBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetTrainingBuyoffComments: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 310
    Width = 100
    Height = 50
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUSD_A3D4C0B47ADF6BA3E05387971F0A482B''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object _OBJ4: TCommentScriptStatement
    Comment = ''-----------BadgeSwipe''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''-----------BadgeSwipe''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BadgeSwipe: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_order_open_holds''
    ParamsSqlSourceName = ''@ToolScope,@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object orderOpenHoldError2: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 460
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''Exit''
    Expression = ''OPEN_HOLDS=''#39''0''#39
    MsgId = ''Open hold exists for selected S/Ns, all holds must be closed''
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
  object checkHolds2: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUSD_PWUST_CHECKFINALHOLDS''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object BadgeSwipeUDV: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
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
    Left = 25
    Top = 835
    Width = 100
    Height = 50
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
    Left = 400
    Top = 310
    Width = 100
    Height = 50
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
  object TypeUseridMultiple: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
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
  object _OBJ5: TCommentScriptStatement
    Comment = ''----------GetBuyoffType''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''----------GetBuyoffType''
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
  object sqlToCheckUserPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_BuyoffPrivCheckPW''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = True
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
    RetryBranchTo = ''Exit''
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
  object CheckNANDBuyoff: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_isNANDBuyoff''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object AddSignoffExec: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_BuyoffAddSignoff''
    Expression = ''TAG_TYPE = ''#39''ADDSIG''#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object InsertLaborRecordFinal: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_InsertLaborRecordOneOperFinal''
    Expression = ''NAND = ''#39''N''#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object TraineeBuyoffAccept: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUSD_PWUST_CHECKFINALHOLDS''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object TraineeBuyoffAccept2: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_BuyoffTraineeAccept2''
    Expression = ''TAG_TYPE = ''#39''TRAINING''#39
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object UpdateBadgeSwipeLog3: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_BadgeSwipeLogUpd''
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
  object Exit: TExitScriptStatement
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

