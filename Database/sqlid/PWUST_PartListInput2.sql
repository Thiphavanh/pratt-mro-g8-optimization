
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_57973BB56FEFE826E05387971F0A4AF3.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_57973BB56FEFE826E05387971F0A4AF3';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PartListInput2';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MFI_2DED784A8D9C4CB494A84CFE34B935AB/Defect 365/defect 1598';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 900
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create Plan Item No''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  ClearAuxParams = True
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arRefreshAndFilter
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = True
  BottomMargin = -1
  object PART_NO: TsfDBInputEdit
    Left = 80
    Top = 19
    Width = 165
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
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Part No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PART_TITLE: TsfDBInputEdit
    Left = 81
    Top = 55
    Width = 285
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
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Title*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemSubtype: TsfDBInputEdit
    Left = 478
    Top = 19
    Width = 155
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
    Caption = ''Item;Subtype*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ1: TsfDBInputEdit
    Left = 80
    Top = 128
    Width = 121
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
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''ITEM_TYPE''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ2: TsfDBInputEdit
    Left = 478
    Top = 128
    Width = 121
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
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''uom''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ3: TsfDBInputEdit
    Left = 80
    Top = 93
    Width = 121
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
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''Part_chg''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputEdit
    Left = 267
    Top = 93
    Width = 121
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
    TabOrder = 6
    EntryType = etAlphaNumeric
    Caption = ''ucf_vch1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfDBInputEdit
    Left = 478
    Top = 93
    Width = 154
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
    TabOrder = 7
    EntryType = etAlphaNumeric
    Caption = ''Work Location*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object SERIALLOT: TsfDBInputEdit
    Left = 478
    Top = 55
    Width = 121
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
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''Serial/Lot''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object SERIALLOTOVERHAUL: TsfDBInputEdit
    Left = 479
    Top = 55
    Width = 121
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
    TabOrder = 9
    EntryType = etAlphaNumeric
    Caption = ''Serial/Lot''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemListInput: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PROGRAM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=30''
      '' CharCase=N''
      '' Required=False''
      '' PersistManualValues=Y''
      '' Hidden=False''
      ''PART_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''STOCK_UOM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' DefaultValue=EA''
      ''EXP_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''SPOOL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''PARENT_ENG_PART_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' DefaultValue=ADDED IN SOLUMINA''
      ''UCF_ITEM_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_ITEM_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_ITEM_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''WO_SERIAL_FLAG''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=ITEM_SUBTYPE<>''#39''REPAIR''#39
      '' Required=False''
      '' PersistManualValues=N''
      '' DefaultValue=Serial''
      '' Update=False''
      '' Insert=False''
      ''WO_LOT_FLAG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=ITEM_SUBTYPE=''#39''REPAIR''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''COMP_SERIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''COMP_LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''OPT_DC1_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC2_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC3_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      ''OPT_DC4_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      ''PARENT_ENG_PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=4''
      '' PersistManualValues=Y''
      ''STANDARD_PART_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ITEM_VCH3''
      ''UCF_ITEM_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_VCH6''
      ''UCF_ITEM_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_VCH8''
      ''UCF_ITEM_VCH9''
      ''UCF_ITEM_VCH10''
      ''UCF_ITEM_VCH11''
      ''UCF_ITEM_VCH12''
      ''UCF_ITEM_VCH13''
      ''UCF_ITEM_VCH14''
      ''UCF_ITEM_VCH15''
      ''UCF_ITEM_NUM2''
      ''UCF_ITEM_NUM3''
      ''UCF_ITEM_NUM4''
      ''UCF_ITEM_NUM5''
      ''UCF_ITEM_FLAG2''
      ''UCF_ITEM_FLAG3''
      ''UCF_ITEM_FLAG4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_FLAG5''
      ''UCF_ITEM_DATE1''
      ''UCF_ITEM_DATE2''
      ''UCF_ITEM_DATE3''
      ''UCF_ITEM_DATE4''
      ''UCF_ITEM_DATE5''
      ''FLIGHT_SAFETY_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FLIGHT_ESSENTIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SOURCE_INSP_PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''DEST_INSP_PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''SOURCE_INSP_PLAN_REV''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DEST_INSP_PLAN_REV''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SOURCE_INSP_PLAN_NO''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DEST_INSP_PLAN_NO''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UTILIZATION_RULE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=Any''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''TRACKABLE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UID_ENTRY_NAME''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UID_ACQUISITION_CODE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UID_ITEM_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=20''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' DefaultValue=PART''
      ''ITEM_SUBTYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PART_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' DefaultValue=N/A''
      ''STD_COST''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PHANTOM_KIT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MFG_INSP_PLAN_NO''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MFG_INSP_PLAN_REV''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MFG_INSP_PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''SERIAL_NUM_GEN''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_VCH255_1''
      ''UCF_ITEM_VCH255_2''
      ''UCF_ITEM_VCH255_3''
      ''UCF_ITEM_VCH4000_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ITEM_VCH4000_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''BUY_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DependentFields=AVG_PURCHASE_PRICE_PER_UNIT''
      ''MAKE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''AVG_PURCHASE_PRICE_PER_UNIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=BUY_FLAG<>''#39''Y''#39
      '' Required=BUY_FLAG=''#39''Y''#39
      '' MaxLength=22''
      ''AVG_MATERIAL_COST_PER_UNIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MAKE_FLAG<>''#39''Y''#39
      '' Required=MAKE_FLAG=''#39''Y''#39
      ''AVG_LABOR_COST_PER_UNIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MAKE_FLAG<>''#39''Y''#39
      '' Required=MAKE_FLAG=''#39''Y''#39
      ''PRICE_COST_UNIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''AVG_ORDER_LEAD_TIME_DAYS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''COMMODITY_JURISDICTION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=50''
      ''COMMODITY_CLASSIFICATION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=50''
      ''ASSOCIATE_CHANGE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ASGND_WORK_LOC''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True'')
    InputFieldsEditControlSSL.Strings = (
      ''UCF_ITEM_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPT_DC3_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''OPT_DC4_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''PARENT_ENG_PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DEST_INSP_PLAN_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DEST_INSP_PLAN_ID''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PARENT_ENG_PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EXP_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''SPOOL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''FLIGHT_SAFETY_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FLIGHT_ESSENTIAL_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UTILIZATION_RULE''
      '' CtrlType=List''
      ''  Values=Any~Unit~Contract''
      ''UID_ACQUISITION_CODE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_UidAcquisitionCodeLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  Style=Short''
      ''   Static=N''
      ''UCF_ITEM_FLAG4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UID_ITEM_FLAG''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_UidItemFlagLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''UID_ENTRY_NAME''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_UidEntryLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  VisibleFields''
      ''   UID_ENTRY_NAME=UID Entry Name''
      ''   UID_ENTRY_DESC=UID Entry Desc''
      ''   CONSTRUCT_TYPE=Construct Type''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''SOURCE_INSP_PLAN_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_SQA_SOURCE_INSP_PLAN_SELECT''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SOURCE_INSP_PLAN_NO=Source Insp Plan No''
      ''   SOURCE_INSP_PLAN_REV=Source Insp Plan Rev''
      ''   STATUS=Status''
      ''   DATE_CREATED=Date Created''
      ''   SOURCE_INSP_PLAN_TITLE=Source Insp Plan Title''
      ''   NOTES=Notes''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''DEST_INSP_PLAN_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_SQA_DEST_INSP_PLAN_SELECT''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   DEST_INSP_PLAN_NO=Dest Insp Plan No''
      ''   DEST_INSP_PLAN_REV=Dest Insp Plan Rev''
      ''   STATUS=Status''
      ''   DATE_CREATED=Date Created''
      ''   DEST_INSP_PLAN_TITLE=Dest Insp Plan Title''
      ''   NOTES=Notes''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''SOURCE_INSP_PLAN_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_INSP_PLAN_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_SQA_MFG_INSP_PLAN_SELECT''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   MFG_INSP_PLAN_NO=Manufacturing Insp Plan No''
      ''   MFG_INSP_PLAN_REV=Manufacturing Insp Plan Rev''
      ''   STATUS=Status''
      ''   DATE_CREATED=Date Created''
      ''   MFG_INSP_PLAN_TITLE=Manufacturing Insp Plan Title''
      ''   NOTES=Notes''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''MFG_INSP_PLAN_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_INSP_PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SERIAL_NUM_GEN''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_SELECTSERIALNOGENERATORLIST''
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
      ''SOURCE_INSP_PLAN_ID''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH4000_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH4000_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MAKE_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BUY_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STANDARD_PART_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''UCF_ITEM_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMP_LOT_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''STD_COST''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=2''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPT_DC1_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''OPT_DC2_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''AVG_PURCHASE_PRICE_PER_UNIT''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AVG_MATERIAL_COST_PER_UNIT''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AVG_LABOR_COST_PER_UNIT''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PRICE_COST_UNIT''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TRACKABLE_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AVG_ORDER_LEAD_TIME_DAYS''
      '' CtrlType=Edit';
p2 clob :='''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PHANTOM_KIT_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMMODITY_JURISDICTION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMMODITY_CLASSIFICATION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STOCK_UOM''
      '' CtrlType=Lookup''
      ''  SqlId=UOMLookup''
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
      ''PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
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
      ''PART_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ASSOCIATE_CHANGE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ASGND_WORK_LOC''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_Pl_WorkLocSel''
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
      ''COMP_SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''ITEM_SUBTYPE''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_MFI_PLG_ITEM_SUBTYPE_SEL''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=Pl_ProgramDefSel''
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
      ''WO_LOT_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''WO_SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=Serial~Lot'')
    InsertSqlId = ''PWUST_ItemListIns''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams,,PWUST_SEL_USER_WORK_LOC''
    RefreshParams = False
    LinkedControls.Strings = (
      ''PART_TITLE~PART_TITLE''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''PART_NO~PART_NO''
      ''_OBJ1~ITEM_TYPE''
      ''_OBJ2~STOCK_UOM''
      ''_OBJ4~UCF_ITEM_VCH1''
      ''_OBJ3~PART_CHG''
      ''_OBJ5~ASGND_WORK_LOC''
      ''SERIALLOT~WO_SERIAL_FLAG''
      ''SERIALLOTOVERHAUL~WO_LOT_FLAG'')
    RefreshedSQLSourceName = ''DispatchSqlSource''
    DataDefinitions.Strings = (
      ''PROGRAM=''
      ''PART_TITLE=''
      ''STOCK_UOM=''
      ''EXP_FLAG=''
      ''SPOOL_FLAG=''
      ''PARENT_ENG_PART_NO=''
      ''UCF_ITEM_VCH1=''
      ''UCF_ITEM_VCH2=''
      ''UCF_ITEM_NUM1=''
      ''UCF_ITEM_FLAG1=''
      ''WO_SERIAL_FLAG=''
      ''WO_LOT_FLAG=''
      ''COMP_SERIAL_FLAG=''
      ''COMP_LOT_FLAG=''
      ''OPT_DC1_FLAG=''
      ''OPT_DC2_FLAG=''
      ''OPT_DC3_FLAG=''
      ''OPT_DC4_FLAG=''
      ''PARENT_ENG_PART_CHG=''
      ''STANDARD_PART_FLAG=''
      ''UCF_ITEM_VCH3''
      ''UCF_ITEM_VCH4=''
      ''UCF_ITEM_VCH6''
      ''UCF_ITEM_VCH7=''
      ''UCF_ITEM_VCH8''
      ''UCF_ITEM_VCH9''
      ''UCF_ITEM_VCH10''
      ''UCF_ITEM_VCH11''
      ''UCF_ITEM_VCH12''
      ''UCF_ITEM_VCH13''
      ''UCF_ITEM_VCH14''
      ''UCF_ITEM_VCH15''
      ''UCF_ITEM_NUM2''
      ''UCF_ITEM_NUM3''
      ''UCF_ITEM_NUM4''
      ''UCF_ITEM_NUM5''
      ''UCF_ITEM_FLAG2''
      ''UCF_ITEM_FLAG3''
      ''UCF_ITEM_FLAG4=''
      ''UCF_ITEM_FLAG5''
      ''UCF_ITEM_DATE1''
      ''UCF_ITEM_DATE2''
      ''UCF_ITEM_DATE3''
      ''UCF_ITEM_DATE4''
      ''UCF_ITEM_DATE5''
      ''FLIGHT_SAFETY_FLAG=''
      ''FLIGHT_ESSENTIAL_FLAG=''
      ''SOURCE_INSP_PLAN_ID=''
      ''DEST_INSP_PLAN_ID=''
      ''UTILIZATION_RULE=''
      ''TRACKABLE_FLAG=''
      ''UID_ENTRY_NAME=''
      ''UID_ACQUISITION_CODE=''
      ''UID_ITEM_FLAG=''
      ''ITEM_TYPE=''
      ''ITEM_SUBTYPE=''
      ''PART_NO=''
      ''PART_CHG=''
      ''STD_COST=''
      ''PHANTOM_KIT_FLAG=''
      ''MFG_INSP_PLAN_ID=''
      ''SERIAL_NUM_GEN=''
      ''UCF_ITEM_VCH255_1''
      ''UCF_ITEM_VCH255_2''
      ''UCF_ITEM_VCH255_3''
      ''UCF_ITEM_VCH4000_1=''
      ''UCF_ITEM_VCH4000_2=''
      ''BUY_FLAG=''
      ''MAKE_FLAG=''
      ''AVG_PURCHASE_PRICE_PER_UNIT=''
      ''AVG_MATERIAL_COST_PER_UNIT=''
      ''AVG_LABOR_COST_PER_UNIT=''
      ''PRICE_COST_UNIT=''
      ''AVG_ORDER_LEAD_TIME_DAYS=''
      ''COMMODITY_JURISDICTION=''
      ''COMMODITY_CLASSIFICATION=''
      ''ASSOCIATE_CHANGE=''
      ''ASGND_WORK_LOC='')
    PassthroughParamsForInsert = False
  end
  object PWUST_SEL_USER_WORK_LOC: TsfTransactionParamSource
    SelectSqlId = ''PWUST_Pl_WorkLocSel''
    PublishParams = True
  end
  object TOVCController
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

