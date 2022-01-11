
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_777DA6B4A76A22A6E05387971F0A726C.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_777DA6B4A76A22A6E05387971F0A726C';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanStartRevision_R2';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_PLG_301, defect 1899';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 250
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create Revision''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
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
  object Plan_ID_EDIT: TsfDBInputEdit
    Left = 133
    Top = 14
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Item No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Plan_Version_Edit: TsfDBInputEdit
    Left = 395
    Top = 182
    Width = 41
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    EntryType = etAlphaNumeric
    Caption = ''Version''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Plan_revision_edit: TsfDBInputEdit
    Left = 440
    Top = 14
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Item Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ1: TsfDBInputEdit
    Left = 133
    Top = 102
    Width = 170
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''Release Package*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Program: TsfDBInputEdit
    Left = 133
    Top = 72
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Engine Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object part_chg: TsfDBInputEdit
    Left = 373
    Top = 185
    Width = 49
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Item Rev*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object MFG_BOM_CHG: TsfDBInputEdit
    Left = 441
    Top = 136
    Width = 168
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    EntryType = etAlphaNumeric
    Caption = ''MBOM Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PlanNo: TsfDBInputEdit
    Left = 133
    Top = 43
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Plan No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ2: TsfDBInputEdit
    Left = 440
    Top = 43
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Item Subtype''
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
    Left = 440
    Top = 103
    Width = 169
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    EntryType = etAlphaNumeric
    Caption = ''MBOM No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object bom_id: TsfDBInputEdit
    Left = 441
    Top = 181
    Width = 10
    Height = 25
    BorderColor = clBlack
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
    Caption = ''BOM Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfInputCheckBox
    Left = 460
    Top = 185
    Width = 110
    Height = 20
    Caption = ''Associate Change?''
    ReturnIsTab = False
    TabOrder = 11
    Version = ''1.4.0.3''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ6: TsfDBInputEdit
    Left = 440
    Top = 72
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Work Flow*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ7: TsfDBInputEdit
    Left = 203
    Top = 177
    Width = 170
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Location''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object LOCATION_ID: TsfDBInputEdit
    Left = 326
    Top = 156
    Width = 39
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 14
    EntryType = etAlphaNumeric
    Caption = ''''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ8: TsfDBInputMemo
    Left = 133
    Top = 132
    Width = 223
    Height = 93
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    TabOrder = 15
    UseDockManager = True
    Version = ''2.5.1.0''
    Buffered = False
    Caption = ''''
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -11
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    MemoCaption = ''Chg Level Notes*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    FullHeight = 75
  end
  object PL_Start_Revision: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PLAN_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_VERSION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      
        '' Hidden=PLAN_TYPE = ''#39''REPAIR''#39'' OR PLAN_TYPE = ''#39''OVERHAUL''#39'' OR PLAN_T'' +
        ''YPE = ''#39''GENERAL PROCEDURES''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''PWP_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_REVISION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_ALTERATIONS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''MFG_BOM_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=4''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Required=False''
      '' Hidden=False''
      ''EXT_PLAN_CHECK''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG=''#39''N''#39
      '' Required=False''
      ''PLAN_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_TYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_SUBTYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''BOM_NO''
      '' Update=PLAN_TYPE = ''#39''REPAIR''#39
      '' Insert=PLAN_TYPE = ''#39''REPAIR''#39
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' MaxLength=70''
      '' Hidden=False''
      ''ITEM_REV''
      
        '' Update=EXTERNAL_ERP_NO<>''#39#39'' OR EXTERNAL_PLM_NO<>''#39#39'' OR EXTERNAL_P'' +
        ''LAN_REV_ACTUAL<>''#39#39
      
        '' Insert=EXTERNAL_ERP_NO<>''#39#39'' OR EXTERNAL_PLM_NO<>''#39#39'' OR EXTERNAL_P'' +
        ''LAN_REV_ACTUAL<>''#39#39
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      
        '' Hidden=PLAN_TYPE = ''#39''REPAIR''#39'' OR PLAN_TYPE = ''#39''OVERHAUL''#39'' OR PLAN_T'' +
        ''YPE = ''#39''GENERAL PROCEDURES''#39
      '' Required=False''
      ''ITEM_NO''
      
        '' Update=PLAN_TYPE = ''#39''REPAIR''#39'' OR EXTERNAL_ERP_NO <> ''#39#39'' OR EXTERNA'' +
        ''L_PLM_NO <> ''#39#39
      
        '' Insert=PLAN_TYPE = ''#39''REPAIR''#39'' OR EXTERNAL_ERP_NO <> ''#39#39'' OR EXTERNA'' +
        ''L_PLM_NO <> ''#39#39
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=50''
      ''BOM_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''ASSOCIATE_CHANGE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=''#39''N''#39
      '' PersistManualValues=Y''
      
        '' Hidden=CHANGE_REQUEST_ID<>''#39#39'' OR PLAN_TYPE = ''#39''REPAIR''#39'' OR PLAN_TY'' +
        ''PE = ''#39''OVERHAUL''#39
      '' Required=False''
      ''WORK_FLOW''
      
        '' Update=PLAN_TYPE = ''#39''REPAIR''#39'' OR EXTERNAL_ERP_NO <> ''#39#39'' OR EXTERNA'' +
        ''L_PLM_NO <> ''#39#39
      
        '' Insert=PLAN_TYPE = ''#39''REPAIR''#39'' OR EXTERNAL_ERP_NO <> ''#39#39'' OR EXTERNA'' +
        ''L_PLM_NO <> ''#39#39
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=55''
      ''PLAN_LOCATION_ID''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''ASGND_WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      
        '' Hidden=PLAN_TYPE = ''#39''REPAIR''#39'' OR PLAN_TYPE = ''#39''OVERHAUL''#39'' OR PLAN_T'' +
        ''YPE = ''#39''GENERAL PROCEDURES''#39
      '' Required=False''
      ''PROGRAM''
      '' Update=PLAN_TYPE = ''#39''REPAIR''#39
      '' Insert=PLAN_TYPE = ''#39''REPAIR''#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=PLAN_TYPE = ''#39''OVERHAUL''#39
      '' DependentFields=MFG_BOM_CHG''
      ''CHG_LEVEL_NOTES''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=2000''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True'')
    InputFieldsEditControlSSL.Strings = (
      ''CHG_LEVEL_NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PWP_ID''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=SfplPWPSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   pwp_id=Release Package(15)''
      ''   PWP_DESC=Description(20)''
      ''   pwp_status=Status(15)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''PLAN_REVISION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_ALTERATIONS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EXT_PLAN_CHECK''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_SUBTYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_NO''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_WORKPACKAGE_PART_NO_LOOKUP''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
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
      ''ASSOCIATE_CHANGE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WORK_FLOW''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_PROCESSPLANWORKFLOWLOOKUP''
      ''  ParamsSrc=PlanMbomSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''BOM_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_BOM_CHG''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_PLANREVMBOMLOOKUP''
      
        ''  ParamsSrc=PlanMbomSel,PartNoToItemNo,@ToolScope,@UDVUserFieldV'' +
        ''alues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   BOM_NO=Mfg BOM No''
      ''   MFG_BOM_REV=Mfg BOM Rev''
      ''   MBOM_REV_DATE=MBOM Rev Date''
      ''   PROGRAM_TO_DISPLAY=Program''
      ''   WORK_LOC_TO_DISPLAY=Location''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PlanProgramSel''
      ''  ParamsSrc=@ToolScope, @UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
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
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_REV''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_WORKPACKAGE_PART_CHG_LOOKUP''
      ''  ParamsSrc=@UDVUserFieldValue''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
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
      ''PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BOM_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ASGND_WORK_LOC''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=Pl_WorkLocSel''
      ''  ParamsSrc=PlanMbomSel, @UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
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
      ''PLAN_LOCATION_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''PWUST_PL_START_REVISION_R2''
    UpdateSqlId = ''PWUST_PL_START_REVISION_R2''
    ParamsSQLSourceName = ''PlanPwpSel,PartNoToItemNo,PlanMbomSel,@AuxParams,@TOOLSCOPE''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ1~PWP_ID''
      ''Plan_Version_Edit~PLAN_VERSION''
      ''MFG_BOM_CHG~MFG_BOM_CHG''
      ''PlanNo~PLAN_NO''
      ''Plan_revision_edit~ITEM_TYPE''
      ''_OBJ2~ITEM_SUBTYPE''
      ''_OBJ4~BOM_NO''
      ''Plan_ID_EDIT~ITEM_NO''
      ''part_chg~ITEM_REV''
      ''bom_id~BOM_ID''
      ''_OBJ5~ASSOCIATE_CHANGE''
      ''_OBJ6~WORK_FLOW''
      ''_OBJ7~ASGND_WORK_LOC''
      ''Program~PROGRAM''
      ''LOCATION_ID~PLAN_LOCATION_ID''
      ''_OBJ8~CHG_LEVEL_NOTES'')
    DataDefinitions.Strings = (
      ''PLAN_ID=''
      ''PLAN_VERSION=''
      ''PWP_ID=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATIONS=''
      ''MFG_BOM_CHG=''
      ''EXT_PLAN_CHECK=''
      ''PLAN_NO=''
      ''BOM_NO=''
      ''ITEM_REV=''
      ''ITEM_NO=''
      ''BOM_ID=''
      ''ASSOCIATE_CHANGE=''
      ''WORK_FLOW=''
      ''ASGND_WORK_LOC=''
      ''PROGRAM=''
      ''CHG_LEVEL_NOTES='')
  end
  object PlanPwpSel: TsfTransactionParamSource
    SelectSqlId = ''PlanPwpSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
  end
  object PlanMbomSel: TsfTransactionParamSource
    SelectSqlId = ''PlanItemMBOMChgSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
  end
  object PartNoToItemNo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_SELECT_ITEM''
    ParamsSQLSourceName = ''@TOOLSCOPE''
    PublishParams = True
    SelectedFields.Strings = (
      ''ITEM_NO~0~ITEM_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_REV~0~ITEM_REV~N~N~False~False~False~N~~~~~Default~N~''
      ''NEW_ITEM_NO~0~NEW_ITEM_NO~N~N~False~False~False~N~~~~~Default~N~''
      
        ''NEW_ITEM_REV~0~NEW_ITEM_REV~N~N~False~False~False~N~~~~~Default~'' +
        ''N~'')
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

