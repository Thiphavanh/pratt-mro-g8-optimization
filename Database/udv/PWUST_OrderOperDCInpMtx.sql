
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6A40F5A1945C5460E05387971F0A5093.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6A40F5A1945C5460E05387971F0A5093';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperDCInpMtx';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_USR_205;Defect 697';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 815
  Height = 397
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:Insert/Update/Delete Data Co'' +
    ''llections,Insert:Insert Data Collections,Update:Update Data Coll'' +
    ''ections,InputMatrix:Insert Data Collections''#39'',''#39''Insert/Update/Dele'' +
    ''te Data Collections''#39'')''
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderOperAltDcIns''
  IMSqlSourceName = ''OrderOperDcEmbed''
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
  IMDeleteSqlId = ''MFI_WID_OrderOperDatColDelInputMatrix''
  IMEndExecSqlId = ''MFI_WID_CheckDifferentOrientationDC''
  IMPopulateFromTransactionParams = True
  UdvControlType = uctInputMatrix
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
  DynamicFieldAttributes = True
  BottomMargin = -1
  object ORDER_ID: TsfDBInputEdit
    Left = 449
    Top = 26
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
    Caption = ''Order  ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_KEY: TsfDBInputEdit
    Left = 449
    Top = 59
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
  object STEP_KEY: TsfDBInputEdit
    Left = 641
    Top = 26
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
  object REF_ID: TsfDBInputEdit
    Left = 641
    Top = 59
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
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Ref ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderOperAltDcIns: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_KEY''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''STEP_KEY''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''DAT_COL_CERT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=20''
      '' PersistManualValues=Y''
      ''DAT_COL_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=255''
      '' PersistManualValues=Y''
      ''TARGET_VALUE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''DAT_COL_UOM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''UPPER_LIMIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''LOWER_LIMIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''@TOOLSTATE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''DAT_COL_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''REF_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''DISPLAY_LINE_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ORIENTATION_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=30''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
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
      ''@BLOCKID''
      ''NUM_DECIMAL_DIGITS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=11''
      ''VARIABLE_NAME''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''VISIBILITY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''CALC_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=1''
      '' DefaultValue=N''
      ''UCF_OPER_DC_VCH1''
      ''UCF_OPER_DC_VCH2''
      ''UCF_OPER_DC_VCH3''
      ''UCF_OPER_DC_VCH4''
      ''UCF_OPER_DC_VCH5''
      ''UCF_OPER_DC_VCH6''
      ''UCF_OPER_DC_VCH7''
      ''UCF_OPER_DC_VCH8''
      ''UCF_OPER_DC_VCH9''
      ''UCF_OPER_DC_VCH10''
      ''UCF_OPER_DC_VCH11''
      ''UCF_OPER_DC_VCH12''
      ''UCF_OPER_DC_VCH13''
      ''UCF_OPER_DC_VCH14''
      ''UCF_OPER_DC_VCH15''
      ''UCF_OPER_DC_NUM1''
      ''UCF_OPER_DC_NUM2''
      ''UCF_OPER_DC_NUM3''
      ''UCF_OPER_DC_NUM4''
      ''UCF_OPER_DC_NUM5''
      ''UCF_OPER_DC_DATE1''
      ''UCF_OPER_DC_DATE2''
      ''UCF_OPER_DC_DATE3''
      ''UCF_OPER_DC_DATE4''
      ''UCF_OPER_DC_DATE5''
      ''UCF_OPER_DC_FLAG1''
      ''UCF_OPER_DC_FLAG2''
      ''UCF_OPER_DC_FLAG3''
      ''UCF_OPER_DC_FLAG4''
      ''UCF_OPER_DC_FLAG5''
      ''UCF_OPER_DC_VCH255_1''
      ''UCF_OPER_DC_VCH255_2''
      ''UCF_OPER_DC_VCH255_3''
      ''UCF_OPER_DC_VCH4000_1''
      ''UCF_OPER_DC_VCH4000_2''
      ''AUDIT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''STD_DATCOL_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DAT_COL_TYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''CALLED_FROM''
      ''RESULT_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''VALID_RESULT_TYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FILE1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=FORMAT <> ''#39''Template File''#39
      '' Required=False''
      ''FILE1_DATA''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DROP_DISPLAY_LINE_NO'')
    InputFieldsEditControlSSL.Strings = (
      ''@TOOLSTATE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STEP_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DAT_COL_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPTIONAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''VISIBILITY''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_DatColVariableVisibilityLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  Style=Short''
      ''   Static=N''
      ''CALC_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''VARIABLE_NAME''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_DatColVariableLkup''
      ''  Validate=N''
      ''  DefaultToSingle=N''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NUM_DECIMAL_DIGITS''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UPPER_LIMIT''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=5''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOWER_LIMIT''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=5''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TARGET_VALUE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=5''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DAT_COL_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISPLAY_LINE_NO''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORIENTATION_FLAG''
      '' CtrlType=List''
      ''  Values=Data Collection Centric~Unit Centric''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''RESULT_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''VALID_RESULT_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_SFPL_VALIDRESULTTYPESEL''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   VALID_RESULT_TYPE=Validation Result Type''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''AUDIT_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''STD_DATCOL_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DAT_COL_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=OrderOperDCTypeLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   dat_col_type=Data Collection Type''
      ''   dat_col_title=Data Collection Title''
      ''   dat_col_type_desc=Data Collection Type Desc''
      ''   dat_col_uom=UOM''
      ''   format=Format''
      ''   num_decimal_digits=Number Decimal Digits''
      ''   lower_limit=LSL''
      ''   upper_limit=USL''
      ''   dat_col_cert=Req''#39''d Cert''
      ''   FILE_DISP=Template File''
      ''   VALID_RESULT_TYPE=Validation Result Type''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''DAT_COL_UOM''
      '' CtrlType=Lookup''
      ''  SqlId=DatColUOMLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''FILE1_DATA''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FILE1''
      '' CtrlType=Edit''
      ''  DataType=Filename With Data''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DAT_COL_CERT''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_OrderCertLookup''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''MFI_SFWID_DC_INSERT''
    UpdateSqlId = ''MFI_SFWID_DC_UPDATE''
    ParamsSQLSourceName = 
      ''OrderOperDcEmbed,@ScriptParams,@ToolScope,OrderOperFlagSel,@Curr'' +
      ''entMarker''
    RefreshParams = False
    LinkedControls.Strings = (
      ''ORDER_ID~ORDER_ID''
      ''OPER_KEY~OPER_KEY''
      ''STEP_KEY~STEP_KEY''
      ''REF_ID~REF_ID'')
    RefreshedSQLSourceName = ''OrderOperDcEmbed''
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''STEP_KEY=''
      ''DAT_COL_CERT=''
      ''DAT_COL_TITLE=''
      ''TARGET_VALUE=''
      ''DAT_COL_UOM=''
      ''UPPER_LIMIT=''
      ''LOWER_LIMIT=''
      ''@TOOLSTATE=''
      ''DAT_COL_ID=''
      ''REF_ID=''
      ''DISPLAY_LINE_NO=''
      ''ORIENTATION_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''OPTIONAL_FLAG=''
      ''@BLOCKID''
      ''NUM_DECIMAL_DIGITS=''
      ''VARIABLE_NAME=''
      ''VISIBILITY=''
      ''CALC_FLAG=''
      ''UCF_OPER_DC_VCH1''
      ''UCF_OPER_DC_VCH2''
      ''UCF_OPER_DC_VCH3''
      ''UCF_OPER_DC_VCH4''
      ''UCF_OPER_DC_VCH5''
      ''UCF_OPER_DC_VCH6''
      ''UCF_OPER_DC_VCH7''
      ''UCF_OPER_DC_VCH8''
      ''UCF_OPER_DC_VCH9''
      ''UCF_OPER_DC_VCH10''
      ''UCF_OPER_DC_VCH11''
      ''UCF_OPER_DC_VCH12''
      ''UCF_OPER_DC_VCH13''
      ''UCF_OPER_DC_VCH14''
      ''UCF_OPER_DC_VCH15''
      ''UCF_OPER_DC_NUM1''
      ''UCF_OPER_DC_NUM2''
      ''UCF_OPER_DC_NUM3''
      ''UCF_OPER_DC_NUM4''
      ''UCF_OPER_DC_NUM5''
      ''UCF_OPER_DC_DATE1''
      ''UCF_OPER_DC_DATE2''
      ''UCF_OPER_DC_DATE3''
      ''UCF_OPER_DC_DATE4''
      ''UCF_OPER_DC_DATE5''
      ''UCF_OPER_DC_FLAG1''
      ''UCF_OPER_DC_FLAG2''
      ''UCF_OPER_DC_FLAG3''
      ''UCF_OPER_DC_FLAG4''
      ''UCF_OPER_DC_FLAG5''
      ''UCF_OPER_DC_VCH255_1''
      ''UCF_OPER_DC_VCH255_2''
      ''UCF_OPER_DC_VCH255_3''
      ''UCF_OPER_DC_VCH4000_1''
      ''UCF_OPER_DC_VCH4000_2''
      ''AUDIT_FLAG=''
      ''STD_DATCOL_ID=''
      ''CALLED_FROM''
      ''RESULT_ID=''
      ''FILE1=''
      ''FILE1_DATA=''
      ''DROP_DISPLAY_LINE_NO'')
  end
  object OrderOperFlagSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_OrderOperFlagsSel''
    ParamsSQLSourceName = ''OrderOperDcEmbed''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''ORIENTATION_FLAG~0~ORIENTATION_FLAG~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''CROSS_ORDER_FLAG~0~CROSS_ORDER_FLAG~N~N~False~False~False~N~~~~~'' +
        ''Default~N~'')
    ConsolidatedQuery = False
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

