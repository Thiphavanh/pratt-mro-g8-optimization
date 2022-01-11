
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_62E7EE15AA709C9EE05387971F0AFFE3.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_62E7EE15AA709C9EE05387971F0AFFE3';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_BuyoffExeScript';
v_udv_type sfcore_udv_lib.udv_type%type  :='SCRIPT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202; SMRO_TR_T203;Defect 572;Defect 478;Defect 750;Defect 884;Defect860;defect 947;SMRO_WOE_302;1714';
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
    ParamsSqlSourceName = ''@Scriptparams,@Toolscope''
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
    BranchTo = ''AuxDummy''
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
  object AuxDummy: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''dummy''
    ParamsSqlSourceName = ''@AuxParams''
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
  object CheckOpenStatus: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''STATUS = ''#39''REOPEN''#39
    BranchTo = ''InitBadgeSwipeParams''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object BranchToBadgeSwipe: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
  object CheckCertAndPrivAndMT2: TIfScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 160
    Width = 100
    Height = 50
    Color = clBlack
    Expression = 
      ''(STATUS = ''#39''SKIP''#39'' OR VALIDCERT = ''#39''Y''#39'') AND V_PRIV = ''#39''Y''#39'' AND MFGAND'' +
      ''MFG2 = ''#39''N''#39
    BranchTo = ''MFI_WID_REMOVE_PRIOR_COLLECTED_MFG_BUYOFF_UNITS''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object SelectOldUserId: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_FirstUserIdSel''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SecondLogin: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''MFI_54768B8B6FC138FAE0440003BA041A64''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetBuyoffRefId: TSelectScriptStatement
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
  object DoesMFG2UserClear: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_MFG2_VALID_USER''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object DoesSecondCertificationExist: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_BUYOFF_VALIDATE_CERT''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object DoesSecondPrivilegeExist: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_BUYOFF_VALIDATE_PRIV''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetSkills: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_GET_SKILLS_REQUIRED''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckSkillsRequired: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''SKILLS_REQ = ''#39#39
    MsgId = 
      ''The user :@userId needs the following skills to complete this op'' +
      ''eration :SKILLS_REQ''
    MsgKind = mkWarning
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
  object CheckMFG2User: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 235
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''MFG2_CHK_RES <> ''#39''N''#39
    MsgId = 
      ''The same user has previously completed the :REVERSE_BUYOFF_TYPE '' +
      ''buyoff for this Operation or Step.  A different user must comple'' +
      ''te the :BUYOFF_TYPE buyoff''
    MsgKind = mkException
    OkButton = False
    RetryButton = False
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object CheckSecondPriv: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 85
    Width = 100
    Height = 50
    Color = clBlack
    Expression = ''V_PRIV = ''#39''N''#39
    MsgId = ''The user does not have the privilege BUYOFF_:BUYOFF_TYPE''
    MsgKind = mkException
    OkButton = False
    RetryButton = False
    CancelButton = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    RetryBranchLinkStyle = 3
    RetryBranchLinkStartPoint = 6
    RetryBranchLinkEndPoint = 14
  end
  object MultiUnitSkip: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''MFI_14E95BBF2AE44D7AE0440003BA560E35''
    ShowPrevious = False
    ShowOk = False
    ShowUdvExpression = ''STATUS=''#39''SKIP''#39
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
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
  object MultUnitsNonSkip: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''STATUS<>''#39''SKIP''#39
    BranchTo = ''CheckGroupJobNoExists''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object CrossOrderUnitCount1: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_WID_SKIP_MULTIUNITSELECTION_BUYOFF''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object ValueCountSkip: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''STATUS=''#39''SKIP''#39'' AND (VALUE_CNT=''#39''Y''#39'' OR GROUP_JOB_NO<>''#39#39'')''
    BranchTo = ''MultiUnitSkipIM''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object MultiUnitSkipIM: TUdvScriptStatement
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
    ShowUdvExpression = ''STATUS=''#39''SKIP''#39
    Scrollbars = False
    UdvTransaction = uttUpdate
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SkipExit: TExitScriptStatement
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
    SqlId = ''MFI_GROUPJOBSERIALSELECT''
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
  object ValidPartialBuyoff: TSelectScriptStatement
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
  object IsPercentCompleteOk: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 400
    Top = 235
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''SelectUnits''
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
  object IsQtyCompleteOk: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 25
    Top = 310
    Width = 100
    Height = 50
    Color = clBlack
    RetryBranchTo = ''SelectUnits''
    Expression = ''(GREATER_LESS_THAN<>''#39''Y''#39'' OR PARTIAL_COMPLETE<>''#39''QTY''#39'')''
    MsgId = ''MFI_5452F774D3AB33A1E0440003BA041A64''
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
  object SelectPartialBuyoffParam: TSelectScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''MFI_BUYOFFPARTIALSHOWACCEPTUDVCHK''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckPartialBuyoffParam: TIfScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Expression = ''BUYOFF_ACCEPT_REQUIRED = ''#39''Y''#39
    BranchTo = ''LaunchAcceptBuyoff''
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
    BranchLinkStyle = 3
    BranchLinkStartPoint = 6
    BranchLinkEndPoint = 14
  end
  object UpdatePartialBuyoff: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 275
    Top = 235
    Width = 100
    Height = 50
    Color = clBlack
    Caption = ''''
    UdvId = ''MFI_1CE9B087FA816FC3E0440003BA041A64''
    ShowPrevious = False
    ShowNext = False
    ShowOk = True
    ShowUdvExpression = ''BUYOFF_ACCEPT_REQUIRED = ''#39''N''#39
    Scrollbars = False
    UdvTransaction = uttInsert
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object PartialBuyoffExit: TExitScriptStatement
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
  object LaunchAcceptBuyoff: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''Confirm Buyoff Accept''
    UdvId = ''MFI_546B139FC5AC1050E0440003BA041A64''
    ShowPrevious = False
    ShowOk = False
    Scrollbars = False
    UdvTransaction = uttUpdate
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
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
  object GetReopenComments: TUdvScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    Caption = ''''
    UdvId = ''PWUST_7A8CBB0202F9F679E05387971F0AE961''
    ShowPrevious = False
    ShowOk = False
    ShowUdvExpression = ''STATUS = ''#39''REOPEN''#39
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object GetBuyoffComments: TUdvScriptStatement
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
    ShowUdvExpression = ''STATUS=''#39''PARTIAL''#39
    Scrollbars = False
    UdvTransaction = uttUnassigned
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object SelectUnits: TUdvScriptStatement
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
    ShowUdvExpression = ''ORDER_CONTROL_TYPE=''#39''SERIAL''#39'' AND STATUS <> ''#39''REOPEN''#39
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
  object TypeUseridMultiple: TUdvScriptStatement
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
  object CheckCUSTBuyoff: TIfScriptStatement
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
  object CheckQABuyoff: TIfScriptStatement
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
  object InvalidBuyoffType: TAssertScriptStatement
    ShapeType = fcsNone
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    RetryBranchTo = ''ReturnToBody''
    Expression = #39''1''#39''=''#39''2''#39
    MsgId = ''Buyoff Type is not valid''
    MsgKind = mkWarning
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
  object CheckForTEC';
p2 clob :='H2Priv: TAssertScriptStatement
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    MsgId = ''User does not have BUYOFF_INSP  privilege''
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    ParamsSqlSourceName = ''@ScriptParams''
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
    Color = clBlack
    SqlId = ''PWUST_SelectBuyoffICustPriv''
    ParamsSqlSourceName = ''@ScriptParams''
    PublishAsCookie = False
    NextLinkStyle = 3
    NextLinkStartPoint = 6
    NextLinkEndPoint = 14
  end
  object CheckforCUSTPriv: TAssertScriptStatement
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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
    Left = 0
    Top = 0
    Width = 0
    Height = 0
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

