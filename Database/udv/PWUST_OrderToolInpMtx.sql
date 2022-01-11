
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_738FD639B06ADE72E05387971F0AA1B7.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_738FD639B06ADE72E05387971F0AA1B7';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderToolInpMtx';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 933.';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 827
  Height = 397
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:Insert/Update/Delete Tools,I'' +
    ''nsert:Insert Tools,Update:Update Tools,InputMatrix:Insert Tools''#39 +
    '',''#39''Insert/Update/Delete Tools''#39'')''
  CachedTransaction = True
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderToolEmbInpMtx''
  IMSqlSourceName = ''WidOperToolImbed''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMNoSelectionLoadsAllExpr = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:true,InputMatrix:false''#39'',''#39''fal'' +
    ''se''#39'')''
  IMFixedColCount = -1
  IMPopulateFromParams = True
  IMDeleteSqlId = ''MFI_WID_tool_alt_del_input_matrix''
  IMEndExecSqlId = ''MFI_WID_CheckDifferentOrientationTool''
  IMPopulateFromTransactionParams = True
  UdvControlType = uctInputMatrix
  ParentNavigator = True
  RequireLogonExpression = ''False''
  IgnoreAuxParams = True
  ClearAuxParams = True
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDisabled
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = True
  BottomMargin = -1
  object _OBJ2: TsfDBInputEdit
    Left = 188
    Top = 7
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Insert/Update Tools for Oper''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ3: TsfDBInputEdit
    Left = 187
    Top = 47
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Order Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputEdit
    Left = 387
    Top = 47
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Oper Key''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfDBInputEdit
    Left = 574
    Top = 47
    Width = 12
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Step Key''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ6: TsfDBInputEdit
    Left = 389
    Top = 7
    Width = 119
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Step No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object REF_ID: TsfDBInputEdit
    Left = 574
    Top = 7
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''Ref Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderToolEmbInpMtx: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' MaxLength=40''
      '' PersistManualValues=Y''
      ''OPER_KEY''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''STEP_KEY''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      '' MaxLength=0''
      ''TOOL_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''TOOL_NOTES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=255''
      '' PersistManualValues=Y''
      ''SERIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''EXP_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=N''
      '' Required=N''
      '' MaxLength=1''
      ''QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' DefaultValue=1''
      '' Hidden=N''
      '' Required=Y''
      ''UCF_PLAN_TOOL_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''UCF_PLAN_TOOL_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''UCF_PLAN_TOOL_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''UCF_PLAN_TOOL_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_TOOL_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''UCF_PLAN_TOOL_DATE1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ORIENTATION_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=20''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=5''
      ''STEP_NO''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPTIONAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=N''
      ''REF_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''@BLOCKID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_VCH5''
      ''UCF_PLAN_TOOL_VCH6''
      ''UCF_PLAN_TOOL_VCH7''
      ''UCF_PLAN_TOOL_VCH8''
      ''UCF_PLAN_TOOL_VCH9''
      ''UCF_PLAN_TOOL_VCH10''
      ''UCF_PLAN_TOOL_VCH11''
      ''UCF_PLAN_TOOL_VCH12''
      ''UCF_PLAN_TOOL_VCH13''
      ''UCF_PLAN_TOOL_VCH14''
      ''UCF_PLAN_TOOL_VCH15''
      ''UCF_PLAN_TOOL_NUM2''
      ''UCF_PLAN_TOOL_NUM3''
      ''UCF_PLAN_TOOL_NUM4''
      ''UCF_PLAN_TOOL_NUM5''
      ''UCF_PLAN_TOOL_DATE2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_DATE3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_DATE4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_DATE5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_TOOL_FLAG2''
      ''UCF_PLAN_TOOL_FLAG3''
      ''UCF_PLAN_TOOL_FLAG4''
      ''UCF_PLAN_TOOL_FLAG5''
      ''UCF_PLAN_TOOL_VCH255_1''
      ''UCF_PLAN_TOOL_VCH255_2''
      ''UCF_PLAN_TOOL_VCH255_3''
      ''UCF_PLAN_TOOL_VCH4000_1''
      ''UCF_PLAN_TOOL_VCH4000_2''
      ''TOOL_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''DISPLAY_LINE_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''OVERUSE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''TOOL_ID''
      ''DROP_DISPLAY_LINE_NO''
      ''CALLED_FROM''
      ''TOOL_TITLE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_VCH3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EXP_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''OPER_KEY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STEP_KEY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPTIONAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''REF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STEP_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=OrderOperNoLookup''
      ''  ParamsSrc=WidOperToolImbed''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''DISPLAY_LINE_NO''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OVERUSE_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''@BLOCKID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TOOL_REV''
      '' CtrlType=Lookup''
      ''  SqlId=ToolChgLkup''
      ''  ParamsSrc=WidOperToolImbed''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''TOOL_NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TOOL_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORIENTATION_FLAG''
      '' CtrlType=List''
      ''  Values=Unit Centric~Data Collection Centric''
      ''UCF_PLAN_TOOL_DATE1''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_DATE2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_DATE3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_DATE4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_TOOL_DATE5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TOOL_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_WID_RealOrderToolNoLkup''
      ''  ParamsSrc=@InputMatrixFieldValues,WidOperToolImbed''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TOOL_NO=Tool No''
      ''   TOOL_REV=Tool Rev''
      ''   TOOL_TITLE=Tool Title''
      ''   ITEM_TYPE=Item Type''
      ''   ITEM_SUBTYPE=Item Subtype''
      ''   SERIAL_FLAG=Serial Flag''
      ''   EXP_FLAG=Exp Flag''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''sfwid_tool_alt_ins''
    UpdateSqlId = ''MFI_sfwid_tool_alt_insupd''
    ParamsSQLSourceName = ''WidOperToolImbed,@ToolScope,OrderOperFlagSel,@CurrentMarker''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~OPER_NO''
      ''_OBJ3~ORDER_ID''
      ''_OBJ4~OPER_KEY''
      ''_OBJ5~STEP_KEY''
      ''_OBJ6~STEP_NO''
      ''REF_ID~REF_ID'')
    RefreshedSQLSourceName = ''WidOperToolImbed''
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''STEP_KEY=''
      ''TOOL_NO=''
      ''TOOL_NOTES=''
      ''SERIAL_FLAG=''
      ''EXP_FLAG=''
      ''QTY=''
      ''UCF_PLAN_TOOL_VCH1=''
      ''UCF_PLAN_TOOL_VCH2=''
      ''UCF_PLAN_TOOL_VCH3=''
      ''UCF_PLAN_TOOL_FLAG1=''
      ''UCF_PLAN_TOOL_NUM1=''
      ''UCF_PLAN_TOOL_DATE1=''
      ''OPER_NO=''
      ''ORIENTATION_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''OPTIONAL_FLAG''
      ''REF_ID=''
      ''@BLOCKID=''
      ''UCF_PLAN_TOOL_VCH4=''
      ''UCF_PLAN_TOOL_VCH5''
      ''UCF_PLAN_TOOL_VCH6''
      ''UCF_PLAN_TOOL_VCH7''
      ''UCF_PLAN_TOOL_VCH8''
      ''UCF_PLAN_TOOL_VCH9''
      ''UCF_PLAN_TOOL_VCH10''
      ''UCF_PLAN_TOOL_VCH11''
      ''UCF_PLAN_TOOL_VCH12''
      ''UCF_PLAN_TOOL_VCH13''
      ''UCF_PLAN_TOOL_VCH14''
      ''UCF_PLAN_TOOL_VCH15''
      ''UCF_PLAN_TOOL_NUM2''
      ''UCF_PLAN_TOOL_NUM3''
      ''UCF_PLAN_TOOL_NUM4''
      ''UCF_PLAN_TOOL_NUM5''
      ''UCF_PLAN_TOOL_DATE2=''
      ''UCF_PLAN_TOOL_DATE3=''
      ''UCF_PLAN_TOOL_DATE4=''
      ''UCF_PLAN_TOOL_DATE5=''
      ''UCF_PLAN_TOOL_FLAG2''
      ''UCF_PLAN_TOOL_FLAG3''
      ''UCF_PLAN_TOOL_FLAG4''
      ''UCF_PLAN_TOOL_FLAG5''
      ''UCF_PLAN_TOOL_VCH255_1''
      ''UCF_PLAN_TOOL_VCH255_2''
      ''UCF_PLAN_TOOL_VCH255_3''
      ''UCF_PLAN_TOOL_VCH4000_1''
      ''UCF_PLAN_TOOL_VCH4000_2''
      ''TOOL_REV=''
      ''DISPLAY_LINE_NO=''
      ''OVERUSE_FLAG=''
      ''TOOL_ID''
      ''DROP_DISPLAY_LINE_NO''
      ''CALLED_FROM''
      ''TOOL_TITLE='')
  end
  object OrderOperFlagSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_OrderOperFlagsSel''
    ParamsSQLSourceName = ''WidOperToolImbed''
    PublishParams = True
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''OrderOperFlagSel''
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

