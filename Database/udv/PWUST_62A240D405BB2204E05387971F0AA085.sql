
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_62A240D405BB2204E05387971F0AA085.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_62A240D405BB2204E05387971F0AA085';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_DiscItemDispUpd';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect278';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 150
  AutoScroll = False
  Caption = ''Update Disposition Action''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  IgnoreAuxParams = True
  ClearAuxParams = True
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
  object _OBJ65: TsfDBInputEdit
    Left = 10
    Top = 24
    Width = 152
    Height = 23
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
    Caption = ''Disposition Type*''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ67: TsfDBInputEdit
    Left = 322
    Top = 24
    Width = 150
    Height = 23
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
    Caption = ''Doc Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ68: TsfDBInputEdit
    Left = 477
    Top = 24
    Width = 150
    Height = 23
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
    Caption = ''Doc No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ70: TsfDBInputEdit
    Left = 633
    Top = 24
    Width = 150
    Height = 23
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
    Caption = ''Document Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ71: TsfInputCheckBox
    Left = 12
    Top = 90
    Width = 37
    Height = 20
    Caption = ''Yes''
    ReturnIsTab = False
    TabOrder = 5
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taLeftJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ72: TsfDBInputEdit
    Left = 167
    Top = 24
    Width = 150
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Disposition Instructions''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ75: TsfLabel
    Left = 12
    Top = 75
    Width = 108
    Height = 13
    Caption = ''Customer Notification?''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object _OBJ82: TsfDBInputEdit
    Left = 167
    Top = 90
    Width = 150
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    EntryType = etAlphaNumeric
    Caption = ''Reject?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ83: TsfDBInputEdit
    Left = 322
    Top = 90
    Width = 150
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    EntryType = etAlphaNumeric
    Caption = ''Scrap?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ84: TsfDBInputEdit
    Left = 477
    Top = 90
    Width = 150
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''Return to Supplier?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ85: TsfDBInputEdit
    Left = 633
    Top = 90
    Width = 150
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    EntryType = etAlphaNumeric
    Caption = ''Return to Inventory?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ86: TsfDBInputEdit
    Left = 94
    Top = 132
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 10
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ87: TsfDBInputEdit
    Left = 243
    Top = 131
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ88: TsfDBInputEdit
    Left = 374
    Top = 136
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ89: TsfDBInputEdit
    Left = 524
    Top = 130
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object DiscItemUpd: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PART_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''PART_CHG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=Y''
      ''ORDER_NO''
      '' Update=DISC_CATEGORY=''#39''OOC''#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''OPER_NO''
      '' Update=DISC_CATEGORY=''#39''OOC''#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''DISC_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''DISC_LINE_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''DISP_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      '' DependentFields=DISP_INSTR_TYPE''
      ''DISP_DOC_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''DISP_DOC_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''CUST_NOTIF_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_DISC_ITEM_VCH1''
      ''UCF_DISC_ITEM_VCH2''
      ''UCF_DISC_ITEM_VCH3''
      ''UCF_DISC_ITEM_VCH4''
      ''UCF_DISC_ITEM_VCH5''
      ''UCF_DISC_ITEM_VCH6''
      ''UCF_DISC_ITEM_VCH7''
      ''UCF_DISC_ITEM_VCH8''
      ''UCF_DISC_ITEM_VCH9''
      ''UCF_DISC_ITEM_VCH10''
      ''UCF_DISC_ITEM_VCH11''
      ''UCF_DISC_ITEM_VCH12''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_DISC_ITEM_VCH13''
      ''UCF_DISC_ITEM_VCH14''
      ''UCF_DISC_ITEM_VCH15''
      ''UCF_DISC_ITEM_NUM1''
      ''UCF_DISC_ITEM_NUM2''
      ''UCF_DISC_ITEM_NUM3''
      ''UCF_DISC_ITEM_NUM4''
      ''UCF_DISC_ITEM_NUM5''
      ''UCF_DISC_ITEM_FLAG1''
      ''UCF_DISC_ITEM_FLAG2''
      ''UCF_DISC_ITEM_FLAG3''
      ''UCF_DISC_ITEM_FLAG4''
      ''UCF_DISC_ITEM_FLAG5''
      ''UCF_DISC_ITEM_DATE1''
      ''UCF_DISC_ITEM_DATE2''
      ''UCF_DISC_ITEM_DATE3''
      ''UCF_DISC_ITEM_DATE4''
      ''UCF_DISC_ITEM_DATE5''
      ''UCF_DISC_ITEM_VCH255_1''
      ''UCF_DISC_ITEM_VCH255_2''
      ''UCF_DISC_ITEM_VCH255_3''
      ''UCF_DISC_ITEM_VCH4000_1''
      ''UCF_DISC_ITEM_VCH4000_2''
      ''DISP_DOC_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=30''
      ''DISP_INSTR_TYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''REJECT_FLAG''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      ''SCRAP_FLAG''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      ''RETURN_TO_INVENTORY_FLAG''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      ''RETURN_TO_VENDOR''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      ''ORDER_ID''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''DISP_INSTR_FLAG''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''MRO_FLAG''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''DISC_TYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''ORDER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=QaOrderNoLookup''
      ''  ParamsSrc=DiscItemUpdSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''PART_CHG''
      '' CtrlType=Lookup''
      ''  SqlId=PartChgLookUp''
      ''  ParamsSrc=DiscItemsSelect''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''DISP_DOC_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=DiscItemDispDocTypeLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''UCF_DISC_ITEM_VCH12''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=WidOperNoLookup''
      ''  ParamsSrc=DiscItemUpdSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
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
      ''DISC_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISC_LINE_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISP_DOC_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CUST_NOTIF_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''SCRAP_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''RETURN_TO_INVENTORY_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REJECT_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''RETURN_TO_VENDOR''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MRO_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISC_TYPE''
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
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISP_DOC_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISP_INSTR_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISP_INSTR_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_QA_DispInstTypeLkup''
      ''  ParamsSrc=DiscItemUpdSel,@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   DISP_INSTR_TYPE=Disposition Instruction Type''
      ''   DISP_INSTR_DESC=Description''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''PART_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PartNoLookup''
      ''  ParamsSrc=DiscItemsSelect''
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
      ''    SearchType=stAnyWords''
      ''DISP_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_QA_DISPTYPEDEFLOOKUP''
      ''  ParamsSrc=DiscItemsSelect''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   DISP_TYPE=Disp Type''
      ''   Description=Description''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    CancelSqlId = ''MFI_CLEARSESSIONPARAMSINDI''
    UpdateSqlId = ''MFI_QA_DiscItemDispAndInstrUpd''
    ParamsSQLSourceName = 
      ''@CurrentMarker,@AuxParams,DiscItemUpdSel,DiscItemIDPSel,DiscItem'' +
      ''sSelect''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ68~DISP_DOC_NO''
      ''_OBJ67~DISP_DOC_TYPE''
      ''_OBJ65~DISP_TYPE''
      ''_OBJ70~DISP_DOC_REV''
      ''_OBJ71~CUST_NOTIF_FLAG''
      ''_OBJ82~REJECT_FLAG''
      ''_OBJ83~SCRAP_FLAG''
      ''_OBJ84~RETURN_TO_VENDOR''
      ''_OBJ85~RETURN_TO_INVENTORY_FLAG''
      ''_OBJ72~DISP_INSTR_TYPE''
      ''_OBJ86~MRO_FLAG''
      ''_OBJ87~DISC_TYPE''
      ''_OBJ88~DISP_INSTR_FLAG''
      ''_OBJ89~ORDER_ID'')
    RefreshedSQLSourceName = ''DiscItemsSelect''
    DataDefinitions.Strings = (
      ''PART_NO=''
      ''PART_CHG=''
      ''ORDER_NO=''
      ''OPER_NO=''
      ''DISC_ID=''
      ''DISC_LINE_NO=''
      ''DISP_TYPE=''
      ''DISP_DOC_TYPE=''
      ''DISP_DOC_NO=''
      ''CUST_NOTIF_FLAG=''
      ''UCF_DISC_ITEM_VCH1''
      ''UCF_DISC_ITEM_VCH2''
      ''UCF_DISC_ITEM_VCH3''
      ''UCF_DISC_ITEM_VCH4''
      ''UCF_DISC_ITEM_VCH5''
      ''UCF_DISC_ITEM_VCH6''
      ''UCF_DISC_ITEM_VCH7''
      ''UCF_DISC_ITEM_VCH8''
      ''UCF_DISC_ITEM_VCH9''
      ''UCF_DISC_ITEM_VCH10''
      ''UCF_DISC_ITEM_VCH11''
      ''UCF_DISC_ITEM_VCH12=''
      ''UCF_DISC_ITEM_VCH13''
      ''UCF_DISC_ITEM_VCH14''
      ''UCF_DISC_ITEM_VCH15''
      ''UCF_DISC_ITEM_NUM1''
      ''UCF_DISC_ITEM_NUM2''
      ''UCF_DISC_ITEM_NUM3''
      ''UCF_DISC_ITEM_NUM4''
      ''UCF_DISC_ITEM_NUM5''
      ''UCF_DISC_ITEM_FLAG1''
      ''UCF_DISC_ITEM_FLAG2''
      ''UCF_DISC_ITEM_FLAG3''
      ''UCF_DISC_ITEM_FLAG4''
      ''UCF_DISC_ITEM_FLAG5''
      ''UCF_DISC_ITEM_DATE1''
      ''UCF_DISC_ITEM_DATE2''
      ''UCF_DISC_ITEM_DATE3''
      ''UCF_DISC_ITEM_DATE4''
      ''UCF_DISC_ITEM_DATE5''
      ''UCF_DISC_ITEM_VCH255_1''
      ''UCF_DISC_ITEM_VCH255_2''
      ''UCF_DISC_ITEM_VCH255_3''
      ''UCF_DISC_ITEM_VCH4000_1''
      ''UCF_DISC_ITEM_VCH4000_2''
      ''DISP_DOC_REV=''
      ''DISP_INSTR_TYPE='')
  end
  object DiscItemUpdSel: TsfTransactionParamSource
    SelectSqlId = ''DiscItemSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
  end
  object DiscItemIDPSel: TsfTransactionParamSource
    SelectSqlId = ''MFI_SQA_DISC_ITEM_IDP_SELECT''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
  end
  object TOVCController
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

