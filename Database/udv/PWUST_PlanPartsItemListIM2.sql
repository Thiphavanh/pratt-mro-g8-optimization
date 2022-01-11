
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_737E6A992CFAC996E05387971F0A20E9.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_737E6A992CFAC996E05387971F0A20E9';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanPartsItemListIM2';
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
  Width = 746
  Height = 397
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:Insert/Update/Delete Parts,I'' +
    ''nsert:Insert Parts,Update:Update Parts,InputMatrix:Insert Parts''#39 +
    '',''#39''Insert/Update/Delete Parts''#39'')''
  TransactionType = ttCached
  IMSqlTransactionName = ''PlanOperItemIns''
  IMSqlSourceName = ''RealPlanEmbPartsSelect''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMNoSelectionLoadsAllExpr = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:true,InputMatrix:false''#39'',''#39''fal'' +
    ''se''#39'')''
  IMFixedColCount = -1
  IMPopulateFromParams = False
  IMDeleteSqlId = ''MFI_PLG_PlanPartsDelInputMatrix''
  IMEndExecSqlId = ''MFI_PLG_CheckDifferentOrientationPart''
  UdvControlType = uctInputMatrix
  ParentNavigator = True
  RequireLogonExpression = ''False''
  IgnoreAuxParams = True
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
  object _OBJ1: TsfDBInputEdit
    Left = 652
    Top = 16
    Width = 29
    Height = 25
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
    Left = 545
    Top = 16
    Width = 29
    Height = 25
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
    Left = 619
    Top = 16
    Width = 29
    Height = 25
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
    Left = 583
    Top = 16
    Width = 29
    Height = 25
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
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ6: TsfDBInputEdit
    Left = 159
    Top = 12
    Width = 76
    Height = 25
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
    Caption = ''Oper No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object STEP_NO: TsfDBInputEdit
    Left = 311
    Top = 14
    Width = 81
    Height = 25
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
    Caption = ''Step No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object REF_ID: TsfDBInputEdit
    Left = 466
    Top = 13
    Width = 42
    Height = 25
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
    Caption = ''REf Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ7: TsfDBInputEdit
    Left = 698
    Top = 16
    Width = 22
    Height = 25
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
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ8: TsfDBInputEdit
    Left = 698
    Top = 68
    Width = 22
    Height = 25
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
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PlanOperItemIns: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_VERSION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_REVISION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_ALTERATIONS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''REF_DES''
      '' Update=BOM_COMP_ID<>''#39#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''REF_DES_PREF_RANK''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FIND_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=30''
      ''ITEM_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' DefaultValue=1''
      '' PersistManualValues=Y''
      ''PART_ACTION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''ITEM_NOTES''
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
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''EXP_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''SPOOL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC1_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC2_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC3_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPT_DC4_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''UCF_PLAN_ITEM_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''UCF_PLAN_ITEM_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''OVER_CONSUMPTION_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
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
      '' MaxLength=30''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=5''
      ''STORE_LOC''
      '' Update=MRO_FLAG=''#39''N''#39
      '' Insert=MRO_FLAG=''#39''N''#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UNLOADING_POINT''
      '' Update=MRO_FLAG=''#39''N''#39
      '' Insert=MRO_FLAG=''#39''N''#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ITEM_CATEGORY''
      '' Update=MRO_FLAG=''#39''N''#39
      '' Insert=MRO_FLAG=''#39''N''#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ITEM_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''STEP_NO''
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
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_ITEM_VCH6''
      ''UCF_PLAN_ITEM_VCH7''
      ''UCF_PLAN_ITEM_VCH8''
      ''UCF_PLAN_ITEM_VCH9''
      ''UCF_PLAN_ITEM_VCH10''
      ''UCF_PLAN_ITEM_VCH11''
      ''UCF_PLAN_ITEM_VCH12''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_ITEM_VCH13''
      ''UCF_PLAN_ITEM_VCH14''
      ''UCF_PLAN_ITEM_VCH15''
      ''UCF_PLAN_ITEM_NUM3''
      ''UCF_PLAN_ITEM_NUM4''
      ''UCF_PLAN_ITEM_NUM5''
      ''UCF_PLAN_ITEM_DATE1''
      ''UCF_PLAN_ITEM_DATE2''
      ''UCF_PLAN_ITEM_DATE3''
      ''UCF_PLAN_ITEM_DATE4''
      ''UCF_PLAN_ITEM_DATE5''
      ''UCF_PLAN_ITEM_FLAG3''
      ''UCF_PLAN_ITEM_FLAG4''
      ''UCF_PLAN_ITEM_FLAG5''
      ''UCF_PLAN_ITEM_VCH255_1''
      ''UCF_PLAN_ITEM_VCH255_2''
      ''UCF_PLAN_ITEM_VCH255_3''
      ''UCF_PLAN_ITEM_VCH4000_1''
      ''UCF_PLAN_ITEM_VCH4000_2''
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
      ''BLOCK_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''REMOVE_ACTION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=PART_ACTION <> ''#39''REMOVE''#39
      '' Required=False''
      ''UTILIZATION_RULE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=PART_ACTION <> ''#39''REMOVE''#39
      '' Required=False''
      '' DefaultValue=Any''
      ''TRACKABLE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=PART_ACTION <> ''#39''REMOVE''#39
      '' Required=False''
      '' DefaultValue=N''
      ''UID_ENTRY_NAME''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UID_ITEM_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UNIT_TYPE''
      '' Update=BOM_COMP_ID<>''#39#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''EFF_FROM''
      '' Update=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' Insert=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39
      '' Required=False''
      ''EFF_THRU''
      '' Update=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' Insert=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_TYPE=''#39#39'' OR UNIT_TYPE=''#39''DATE''#39
      '' Required=False''
      ''EFF_FROM_DATE''
      '' Update=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' Insert=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39
      '' Required=False''
      ''EFF_THRU_DATE''
      '' Update=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' Insert=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39'' OR BOM_COMP_ID<>''#39#39
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_TYPE=''#39#39'' OR UNIT_TYPE<>''#39''DATE''#39
      '' Required=False''
      ''PART_TITLE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''STANDARD_PART_FLAG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DISPLAY_LINE_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=10''
      ''BOM_LINE_NO''
      '' Update=BOM_COMP_ID<>''#39#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=10''
      ''ITEM_SUB_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PHANTOM_KIT_PART_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''COMP_PART_NO''
      '' Update=BOM_COMP_ID<>''#39#39
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''COMP_PART_CHG''
      '' Update=BOM_COMP_ID<>''#39#39
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PART_DAT_COL_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CALLED_FROM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DROP_DISPLAY_LINE_NO''
      ''REPLACEMENT_PART_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      
        '' Hidden=PART_ACTION <> ''#39''REMOVE''#39'' OR REPLACE_WITH_DIFF_PART_FLAG <'' +
        ''> ''#39''Y''#39
      
        '' Required=PART_ACTION = ''#39''REMOVE''#39'' AND REPLACE_WITH_DIFF_PART_FLAG'' +
        '' = ''#39''Y''#39
      ''REPLACEMENT_PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      
        '' Hidden=PART_ACTION <> ''#39''REMOVE''#39'' OR REPLACE_WITH_DIFF_PART_FLAG <'' +
        ''> ''#39''Y''#39
      
        '' Required=PART_ACTION = ''#39''REMOVE''#39'' AND REPLACE_WITH_DIFF_PART_FLAG'' +
        '' = ''#39''Y''#39
      ''BOM_COMP_ID''
      ''STOCK_UOM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True'')
    InputFieldsEditControlSSL.Strings = (
      ''UCF_PLAN_ITEM_NUM2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STORE_LOC''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UNLOADING_POINT''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_CATEGORY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_VCH3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OVER_CONSUMPTION_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
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
      ''OPER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PlanOperations''
      ''  ParamsSrc=@ToolScope''
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
      ''ITEM_NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOT_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''EXP_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''SPOOL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''OPT_DC1_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPT_DC2_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPT_DC4_FLAG''
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
      ''OPTIONAL_FLAG''
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
      ''BLOCK_TYPE''
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
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''UID_ENTRY_NAME''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_UidEntryLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
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
      ''UTILIZATION_RULE''
      '' CtrlType=List''
      ''  Values=Any~Unit~Contract''
      ''TRACKABLE_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''ITEM_SUB_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPT_DC3_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PART_ACTION''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PARTACTIONSELDRAGNDROPLKUP''
      ''  ParamsSrc=@ScriptParams''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=Y''
      ''COMP_PART_CHG''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_COMPPARTCHGLOOKUP''
      ''  ParamsSrc=@ToolScope''
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
      ''CALLED_FROM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REF_DES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BOM_LINE_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLANBOMLINENOLOOKUP''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   BOM_LINE_NO=BOM Line No''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''STANDARD_PART_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''PART_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PART_DAT_COL_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_ITEM_VCH5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  Al';
p2 clob :='lowManaging=N''
      ''  SelectFolder=N''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''REPLACEMENT_PART_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_REPCOMPPARTNOLOOKUPIM''
      ''  ParamsSrc=@ToolScope,@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   REPLACEMENT_PART_NO=Replacement Part No(8)''
      ''   REPLACEMENT_PART_CHG=Replacement Part Rev(8)''
      ''   REPLACEMENT_PART_TITLE=Replacement Part Title(8)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''REPLACEMENT_PART_CHG''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_REPCOMPPARTCHGLOOKUP''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   REPLACEMENT_PART_CHG=Replacement Part Rev(8)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''REMOVE_ACTION''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_QA_SelectCompDispostionLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   COMP_DISP_TYPE=Remove Action''
      ''   COMP_DISC_TYPE_DESC=Description''
      ''   SCRAP_FLAG=Scrap?''
      ''   REPLACE_WITH_SAME_PART_FLAG=Replace with same part?''
      ''   DISP_INSTR_FLAG=Require disposition instruction?''
      ''   RETURN_TO_VENDOR=Return to supplier?''
      ''   RETURN_TO_INVENTORY_FLAG=Return to inventory?''
      ''   REINSTALL_SAME_UNIT_FLAG=Reinstall same unit?''
      ''   REPLACE_WITH_DIFF_PART_FLAG=Replace with different part?''
      ''   REQUIRE_EXT_PROCESSING_FLAG=Require external processing?''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UNIT_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=EffTypeLookup''
      ''  ParamsSrc=@ToolScope,@UserUdvFieldValues''
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
      ''EFF_FROM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EFF_THRU''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EFF_FROM_DATE''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Beginning Of Day''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EFF_THRU_DATE''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=End Of Day''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PHANTOM_KIT_PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REF_DES_PREF_RANK''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_ID''
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
      ''UCF_PLAN_ITEM_FLAG2''
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
      ''UCF_PLAN_ITEM_VCH12''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''ORIENTATION_FLAG''
      '' CtrlType=List''
      ''  Values=Unit Centric~Data Collection Centric''
      ''DISPLAY_LINE_NO''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FIND_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=9''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMP_PART_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_PLG_PlanPartNoLookupIM''
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
    InsertSqlId = ''MFI_PLANOPERCOMPITEMINS''
    UpdateSqlId = ''MFI_PLANOPERITEMINSUPD''
    ParamsSQLSourceName = ''RealPlanEmbPartsSelect,@ScriptParams,@ToolScope,@CurrentMarker''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ1~PLAN_ID''
      ''_OBJ2~PLAN_VERSION''
      ''_OBJ3~PLAN_REVISION''
      ''_OBJ4~PLAN_ALTERATIONS''
      ''_OBJ6~OPER_NO''
      ''STEP_NO~STEP_NO''
      ''REF_ID~REF_ID''
      ''_OBJ7~CROSS_ORDER_FLAG''
      ''_OBJ8~ORIENTATION_FLAG'')
    MultiRecordTransaction = mruTrue
    RefreshedSQLSourceName = ''RealPlanEmbPartsSelect''
    DataDefinitions.Strings = (
      ''PLAN_ID=''
      ''PLAN_VERSION=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATIONS=''
      ''OPER_NO=''
      ''REF_DES=''
      ''REF_DES_PREF_RANK=''
      ''FIND_NO=''
      ''ITEM_QTY=''
      ''PART_ACTION=''
      ''ITEM_NOTES=''
      ''SERIAL_FLAG=''
      ''LOT_FLAG=''
      ''EXP_FLAG=''
      ''SPOOL_FLAG=''
      ''OPT_DC1_FLAG=''
      ''OPT_DC2_FLAG=''
      ''OPT_DC3_FLAG=''
      ''OPT_DC4_FLAG=''
      ''UCF_PLAN_ITEM_VCH1=''
      ''UCF_PLAN_ITEM_VCH2=''
      ''UCF_PLAN_ITEM_VCH3=''
      ''UCF_PLAN_ITEM_VCH4=''
      ''UCF_PLAN_ITEM_VCH5=''
      ''UCF_PLAN_ITEM_FLAG1=''
      ''UCF_PLAN_ITEM_FLAG2=''
      ''UCF_PLAN_ITEM_NUM1=''
      ''UCF_PLAN_ITEM_NUM2=''
      ''OVER_CONSUMPTION_FLAG=''
      ''ORIENTATION_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''STORE_LOC=''
      ''UNLOADING_POINT=''
      ''ITEM_CATEGORY=''
      ''ITEM_ID=''
      ''STEP_NO=''
      ''OPTIONAL_FLAG=''
      ''ITEM_ID=''
      ''OPTIONAL_FLAG=''
      ''UCF_PLAN_ITEM_VCH6''
      ''UCF_PLAN_ITEM_VCH7''
      ''UCF_PLAN_ITEM_VCH8''
      ''UCF_PLAN_ITEM_VCH9''
      ''UCF_PLAN_ITEM_VCH10''
      ''UCF_PLAN_ITEM_VCH11''
      ''UCF_PLAN_ITEM_VCH12=''
      ''UCF_PLAN_ITEM_VCH13''
      ''UCF_PLAN_ITEM_VCH14''
      ''UCF_PLAN_ITEM_VCH15''
      ''UCF_PLAN_ITEM_NUM3''
      ''UCF_PLAN_ITEM_NUM4''
      ''UCF_PLAN_ITEM_NUM5''
      ''UCF_PLAN_ITEM_DATE1''
      ''UCF_PLAN_ITEM_DATE2''
      ''UCF_PLAN_ITEM_DATE3''
      ''UCF_PLAN_ITEM_DATE4''
      ''UCF_PLAN_ITEM_DATE5''
      ''UCF_PLAN_ITEM_FLAG3''
      ''UCF_PLAN_ITEM_FLAG4''
      ''UCF_PLAN_ITEM_FLAG5''
      ''UCF_PLAN_ITEM_VCH255_1''
      ''UCF_PLAN_ITEM_VCH255_2''
      ''UCF_PLAN_ITEM_VCH255_3''
      ''UCF_PLAN_ITEM_VCH4000_1''
      ''UCF_PLAN_ITEM_VCH4000_2''
      ''REF_ID=''
      ''@BLOCKID=''
      ''BLOCK_TYPE=''
      ''REMOVE_ACTION=''
      ''UTILIZATION_RULE=''
      ''TRACKABLE_FLAG=''
      ''UID_ENTRY_NAME=''
      ''UID_ITEM_FLAG=''
      ''UNIT_TYPE=''
      ''EFF_FROM=''
      ''EFF_THRU=''
      ''EFF_FROM_DATE=''
      ''EFF_THRU_DATE=''
      ''PART_TITLE=''
      ''STANDARD_PART_FLAG=''
      ''DISPLAY_LINE_NO=''
      ''BOM_LINE_NO=''
      ''ITEM_SUB_TYPE=''
      ''PHANTOM_KIT_PART_NO=''
      ''COMP_PART_NO=''
      ''COMP_PART_CHG=''
      ''PART_DAT_COL_ID=''
      ''CALLED_FROM=''
      ''DROP_DISPLAY_LINE_NO''
      ''REPLACEMENT_PART_NO=''
      ''REPLACEMENT_PART_CHG=''
      ''BOM_COMP_ID''
      ''STOCK_UOM='')
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

