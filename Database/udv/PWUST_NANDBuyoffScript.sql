
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_63EE4F28DC3E5840E05387971F0AB9A0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_63EE4F28DC3E5840E05387971F0AB9A0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_NANDBuyoffScript';
v_udv_type sfcore_udv_lib.udv_type%type  :='SCRIPT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;750;712;884;732;884;860;SMRO_WOE_302;1386;1555;1683;1696;1714;1708;1711;1734;1753;1714;1775';
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
  object StepBuyoffSerialSelect: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_MFI_WID_GetCurrentUnits''
    ParamsSqlSourceName = ''@Toolscope,@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckTag: TIfScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''TAG_TYPE = ''#39''ADDSIG''#39
    BranchTo = ''GetAddSigBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckTrainingTag: TIfScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''TAG_TYPE = ''#39''TRAINING''#39
    BranchTo = ''GetTrainingBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckPartialTag: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''TAG_TYPE = ''#39''PARTIAL''#39
    BranchTo = ''GetPartialBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object GetPriorBuyoffStatus: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_GetNANDBuyoffStatus''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object PriorNONE: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 10
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''PRIOR_BUYOFF_STATUS=''#39''NONE''#39
    BranchTo = ''GetBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object PriorNAError: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''Exit''
    Expression = ''TAG_TYPE=PRIOR_BUYOFF_STATUS''
    MsgId = 
      ''Selected Status :TAG_TYPE is not the same as previous Status of '' +
      '':PRIOR_BUYOFF_STATUS.''
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
  object _OBJ1: TCommentScriptStatement
    Comment = ''----------------GetBuyoffComments''
    ShapeType = fcsNone
    Left = 275
    Top = 160
    Width = 100
    Height = 50
    Color = clBlack
    Caption = ''----------------GetBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetBuyoffComments: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_65067B37B61D11D2E05387971F0AC8D7''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GotoBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 160
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''BadgeSwipe''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object _OBJ1A: TCommentScriptStatement
    Comment = ''-----------GetAddSignatureBuyoffComments''
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
  object GetAddSigBuyoffComments: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUSD_9FF7D3EFCB759687E05387971F0A4021''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object AddSigGotoBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''BadgeSwipe''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
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
  object BranchtoBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''BadgeSwipe''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object _OBJ3: TCommentScriptStatement
    Comment = ''-----------GetPartialBuyoffComments''
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''-----------GetPartialBuyoffComments''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetPartialBuyoffComments: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_GetNANDBuyoffStatus''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object _OBJ12: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''PRIOR_BUYOFF_STATUS=''#39''NONE''#39
    BranchTo = ''GetPartialBuyoffComments11''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object _OBJ13: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    Expression = ''TAG_TYPE=PRIOR_BUYOFF_STATUS''
    MsgId = 
      ''Selected Status :TAG_TYPE is not the same as previous Status of '' +
      '':PRIOR_BUYOFF_STATUS.''
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
  object GetPartialBuyoffComments11: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_63879500E0E2B275E05387971F0A24A6''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object ValidatePartialBuyoff: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''BuyoffPartialValidChk''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object IsPercentCompleteOK: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''GetPartialBuyoffComments''
    Expression = ''(GREATER_LESS_THAN<>''#39''Y''#39'' OR PARTIAL_COMPLETE<>''#39''PERCENT''#39'')''
    MsgId = ''MFI_5452F774D3A633A1E0440003BA041A64''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
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
  object CheckNANDPriv: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''(TAG_TYPE<>''#39''N/A''#39'' AND TAG_TYPE<>''#39''N/D''#39'')''
    BranchTo = ''CheckMFGBuyoffType''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectNANDPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectNANDPriv''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckforNANDPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_NA_ND privilege''
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
  object CheckMFGBuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''MFG''#39
    BranchTo = ''SelectMFGPriv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckTECH2BuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''TECH2''#39
    BranchTo = ''SelectTECH2Priv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckMFG2BuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''MFG2''#39
    BranchTo = ''SelectMFG2Priv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckINSPBuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''INSP''#39
    BranchTo = ''SelectINSPPriv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckQABuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''QA''#39
    BranchTo = ''SelectQAPriv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckTECHBuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''TECH''#39
    BranchTo = ''SelectTECHPriv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckCUSTBuyoffType: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_TYPE=''#39''CUST''#39
    BranchTo = ''SelectCUSTPriv''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object InvalidBuyoffType: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = #39''1''#39''=''#39''2''#39
    MsgId = ''Buyoff Type is not valid''
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
  object _OBJ6: TCommentScriptStatement
    Comment = ''-------  SelectMFGPriv''
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
  object SelectMFGPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffMfgPriv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckforMFGPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_MFG privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object MFGBranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 985
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectTECH2Priv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffTech2Priv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForTECH2Priv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_TECH2 privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object TECH2BranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectMFG2Priv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffMfg2Priv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForMFG2Priv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_MFG2 privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object MFG2BranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 1135
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectINSPPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffInspPriv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForINSPPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_INSP privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object INSPBranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 1135
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39'' = ''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectQAPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffIQAPriv''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForQAPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_QA privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object QABranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 610
    Width = 1';
p2 clob :='00
    Height = 50
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectCUSTPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 610
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffICustPriv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForCUSTPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_CUST privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object CUSTBranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 685
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectTECHPriv: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffITechPriv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckForTECHPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''InitBadgeSwipeParams''
    Expression = ''BUYOFF_PRIV = ''#39''Y''#39
    MsgId = ''User does not have BUYOFF_TECH privilege''
    MsgKind = mkError
    OkButton = True
    RetryButton = True
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object TECHBranchToBuyoffAuth: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 685
    Width = 100
    Height = 50
    Color = clBlack
    Expression = #39''1''#39''=''#39''1''#39
    BranchTo = ''SelectBuyoffAuthParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectBuyoffAuthParams: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 910
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_InitBuyoffAuthParams''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckforTrainingTag: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''TAG_TYPE = ''#39''TRAINING''#39
    BranchTo = ''CheckNANDBuyoff''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckforTagTrainingAddSignature: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''TAG_TYPE = ''#39''ADDSIG''#39
    BranchTo = ''SelectUsersWorkCenterCerts''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CheckForPartialTag: TIfScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 985
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''TAG_TYPE <> ''#39''PARTIAL''#39
    BranchTo = ''BuyoffNAND''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object DoesCertificationExist: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    SqlId = ''PWUST_MFI_WID_BUYOFF_VALIDATE_CERT''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckCert: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 150
    Top = 1060
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''Exit''
    Expression = ''(VALIDCERT = ''#39''Y''#39'' AND TAG_TYPE = ''#39''PARTIAL''#39'')''
    MsgId = ''The user does not have the certification :BUYOFF_CERT''
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
  object checkfinalholdpartial: TExecScriptStatement
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
  object ProcessPartial: TExecScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 985
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_ProcessPartialBuyoffPW''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object UpdateBadgeSwipeLog1: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
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
  object InsertLaborRecordPartial: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_InsertLaborRecordOneOper''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object ExitPartial: TExitScriptStatement
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
  object _OBJ8: TCommentScriptStatement
    Comment = ''-------- BuyoffNAND''
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
  object BuyoffNAND: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_MFI_WID_BUYOFF_VALIDATE_CERT''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object BuyoffNAND111: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''Exit''
    SqlId = ''PWUST_BuyoffNAND''
    ParamsSqlSourceName = ''@Scriptparams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object UpdateBadgeSwipeLog2: TExecScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
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
  object ExitNAND: TExitScriptStatement
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
  object SelectUsersWorkCenterCerts: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_CheckUsersWorkCenterCerts''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckBuyoffAuth: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
v_iclob := p1||p2;
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

