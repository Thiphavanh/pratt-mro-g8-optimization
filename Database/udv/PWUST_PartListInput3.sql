
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6A88C1EA0221FC31E05387971F0A7302.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6A88C1EA0221FC31E05387971F0A7302';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PartListInput3';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of PartListInput2/Defect 365';
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
  Height = 900
  AutoScroll = False
  Caption = ''Insert Part''
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
    Left = 84
    Top = 19
    Width = 165
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
  object PART_CHG: TsfDBInputEdit
    Left = 311
    Top = 19
    Width = 58
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
    Caption = ''Part Rev*''
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
    Left = 84
    Top = 81
    Width = 285
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
    Caption = ''Title''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object STOCK_UOM: TsfDBInputEdit
    Left = 84
    Top = 50
    Width = 165
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
    Caption = ''UOM*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PARENT_ENG_PART_NO: TsfDBInputEdit
    Left = 453
    Top = 50
    Width = 129
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
    Caption = ''Eng;Part No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_ITEM_VCH1: TsfDBInputEdit
    Left = 91
    Top = 709
    Width = 130
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 31
    EntryType = etAlphaNumeric
    Caption = ''UCF;ITEM VCH1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_ITEM_VCH2: TsfDBInputEdit
    Left = 389
    Top = 709
    Width = 130
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 32
    EntryType = etAlphaNumeric
    Caption = ''UCF;ITEM VCH2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_ITEM_NUM1: TsfDBInputEdit
    Left = 91
    Top = 759
    Width = 130
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 33
    EntryType = etAlphaNumeric
    Caption = ''UCF;ITEM NUM1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_ITEM_FLAG1: TsfDBInputEdit
    Left = 389
    Top = 759
    Width = 50
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 34
    EntryType = etAlphaNumeric
    Caption = ''UCF;ITEM FLAG1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ19: TsfLabel
    Left = 12
    Top = 229
    Width = 115
    Height = 13
    Caption = ''Data Collection Controls''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ20: TsfDBInputEdit
    Left = 453
    Top = 81
    Width = 129
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
    Caption = ''Eng;Part Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object DCBevel: TsfBevel
    Left = 6
    Top = 221
    Width = 577
    Height = 144
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object SERIAL_FLAG: TsfInputCheckBox
    Left = 54
    Top = 280
    Width = 94
    Height = 20
    Caption = '' WO Serial No?''
    ReturnIsTab = False
    TabOrder = 14
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object LOT_FLAG: TsfInputCheckBox
    Left = 232
    Top = 280
    Width = 76
    Height = 20
    Caption = ''WO Lot No?''
    ReturnIsTab = False
    TabOrder = 15
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ21: TsfInputCheckBox
    Left = 379
    Top = 280
    Width = 82
    Height = 20
    Caption = ''Std Part No?''
    ReturnIsTab = False
    TabOrder = 16
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ22: TsfInputCheckBox
    Left = 23
    Top = 307
    Width = 125
    Height = 20
    Caption = ''Component Serial No?''
    ReturnIsTab = False
    TabOrder = 17
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ23: TsfInputCheckBox
    Left = 192
    Top = 307
    Width = 116
    Height = 20
    Caption = ''Component Lot No?''
    ReturnIsTab = False
    TabOrder = 18
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ24: TsfInputCheckBox
    Left = 362
    Top = 307
    Width = 99
    Height = 20
    Caption = ''Expiration Date?''
    ReturnIsTab = False
    TabOrder = 19
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ25: TsfInputCheckBox
    Left = 77
    Top = 333
    Width = 71
    Height = 20
    Caption = ''Spool No?''
    ReturnIsTab = False
    TabOrder = 20
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ26: TsfInputCheckBox
    Left = 12
    Top = 680
    Width = 94
    Height = 20
    Caption = ''Opt DC Flag 1?''
    ReturnIsTab = False
    TabOrder = 25
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ27: TsfInputCheckBox
    Left = 158
    Top = 680
    Width = 94
    Height = 20
    Caption = ''Opt DC Flag 2?''
    ReturnIsTab = False
    TabOrder = 26
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ28: TsfInputCheckBox
    Left = 308
    Top = 680
    Width = 94
    Height = 20
    Caption = ''Opt DC Flag 3?''
    ReturnIsTab = False
    TabOrder = 27
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ29: TsfInputCheckBox
    Left = 458
    Top = 680
    Width = 94
    Height = 20
    Caption = ''Opt DC Flag 4?''
    ReturnIsTab = False
    TabOrder = 28
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ30: TsfLabel
    Left = 14
    Top = 374
    Width = 77
    Height = 13
    Caption = ''Quality Controls''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ31: TsfBevel
    Left = 6
    Top = 368
    Width = 577
    Height = 130
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ32: TsfInputCheckBox
    Left = 63
    Top = 399
    Width = 85
    Height = 20
    Caption = ''Flight Safety?''
    ReturnIsTab = False
    TabOrder = 21
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ33: TsfInputCheckBox
    Left = 51
    Top = 429
    Width = 97
    Height = 20
    Caption = ''Flight Essential?''
    ReturnIsTab = False
    TabOrder = 22
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ36: TsfDBInputEdit
    Left = 291
    Top = 410
    Width = 273
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 23
    EntryType = etAlphaNumeric
    Caption = ''Source;Inspection Plan''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ37: TsfDBInputEdit
    Left = 291
    Top = 441
    Width = 273
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 24
    EntryType = etAlphaNumeric
    Caption = ''Receiving;Inspection Plan''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object SRC_INSP_PLN_ID: TsfDBInputEdit
    Left = 524
    Top = 709
    Width = 10
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 29
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object DEST_INSP_PLN_ID: TsfDBInputEdit
    Left = 539
    Top = 709
    Width = 10
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 30
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UTILIZATION_RULE: TsfDBInputEdit
    Left = 453
    Top = 112
    Width = 129
    Height = 25
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
    Caption = ''Utilization Rule''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object TRACKABLE: TsfInputCheckBox
    Left = 242
    Top = 142
    Width = 127
    Height = 20
    Caption = ''Display in Unit Status?''
    ReturnIsTab = False
    TabOrder = 9
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object _OBJ39: TsfDBInputEdit
    Left = 132
    Top = 248
    Width = 50
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    EntryType = etAlphaNumeric
    Caption = ''UID;Part Flag?''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ40: TsfDBInputEdit
    Left = 291
    Top = 248
    Width = 50
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    EntryType = etAlphaNumeric
    Caption = ''UID; Acquisition Code''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ41: TsfDBInputEdit
    Left = 441
    Top = 248
    Width = 123
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    EntryType = etAlphaNumeric
    Caption = ''UID; Entry Name''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemSubtype: TsfDBInputEdit
    Left = 453
    Top = 19
    Width = 129
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
    Caption = ''Part;Subtype*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ42: TsfDBInputEdit
    Left = 311
    Top = 112
    Width = 58
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 10
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
  object _OBJ43: TsfDBInputEdit
    Left = 95
    Top = 526
    Width = 160
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
    Caption = ''Standard Cost''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ44: TsfInputCheckBox
    Left = 383
    Top = 142
    Width = 83
    Height = 20
    Caption = ''Phantom/Kit?''
    ReturnIsTab = False
    TabOrder = 35
    Version = ''1.3.6.0''
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
  object MFG_INSP_PLAN_ID: TsfDBInputEdit
    Left = 555
    Top = 709
    Width = 10
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 36
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
  object _OBJ45: TsfDBInputEdit
    Left = 291
    Top = 379
    Width = 273
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 37
    EntryType = etAlphaNumeric
    Caption = ''Manufacuting; Inspection Plan''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ46: TsfDBInputEdit
    Left = 84
    Top = 112
    Width = 133
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 38
    EntryType = etAlphaNumeric
    Caption = ''Serial Number;Generator''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ48: TsfBevel
    Left = 6
    Top = 502
    Width = 577
    Height = 130
    Shape = bsFrame
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ49: TsfDBInputEdit
    Left = 404
    Top = 526
    Width = 160
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWhite
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 39
    EntryType = etAlphaNumeric
    Caption = ''Avg Purchase; Price per Unit*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ50: TsfDBInputEdit
    Left = 404
    Top = 555
    Width = 160
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 40
    EntryType = etAlphaNumeric
    Caption = ''Avg Material ;Cost per Unit*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ51: TsfDBInputEdit
    Left = 404
    Top = 585
    Width = 160
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 41
    EntryType = etAlphaNumeric
    Caption = ''Avg Labor; Cost per Unit*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ52: TsfDBInputEdit
    Left = 95
    Top = 555
    Width = 160
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 42
    EntryType = etAlphaNumeric
    Caption = ''Avg Order; Lead Time Days''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ53: TsfDBInputEdit
    Left = 95
    Top = 585
    Width = 160
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 43
    EntryType = etAlphaNumeric
    Caption = ''Price Cost Unit''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ54: TsfLabel
    Left = 13
    Top = 510
    Width = 22
    Height = 13
    Caption = ''Cost''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ55: TsfInputCheckBox
    Left = 409
    Top = 333
    Width = 52
    Height = 20
    Caption = ''Make?''
    ReturnIsTab = False
    TabOrder = 44
    Version = ''1.3.6.0''
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
  object _OBJ56: TsfInputCheckBox
    Left = 263
    Top = 333
    Width = 45
    Height = 20
    Caption = ''Buy?''
    ReturnIsTab = False
    TabOrder = 45
    Version = ''1.3.6.0''
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
  object _OBJ59: TsfBevel
    Left = 6
    Top = 636
    Width = 577
    Height = 38
    Shape = bsFrame
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ60: TsfDBInputEdit
    Left = 84
    Top = 143
    Width = 133
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 46
    EntryType = etAlphaNumeric
    Caption = ''Commodity;Jurisdiction''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ61: TsfDBInputEdit
    Left = 84
    Top = 174
    Width = 133
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 47
    EntryType = etAlphaNumeric
    Caption = ''Commodity;Classification''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ62: TsfInputCheckBox
    Left = 19
    Top = 645
    Width = 110
    Height = 20
    Caption = ''Associate Change?''
    ReturnIsTab = False
    TabOrder = 48
    Version = ''1.3.6.0''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    F';
p2 clob :='ont.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object ItemListInput: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PROGRAM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PART_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''STOCK_UOM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
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
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
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
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''WO_LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      ''WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
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
      ''UCF_ITEM_VCH5''
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
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PART_CHG''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
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
      ''ASSOCIATE_CHANGE'')
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
      ''STOCK_UOM''
      '' CtrlType=Lookup''
      ''  SqlId=UOMLookup''
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
      ''PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
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
      ''WORK_LOC''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ITEM_VCH5''
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
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WO_SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''WO_LOT_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''UCF_ITEM_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMP_SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''PHANTOM_KIT_FLAG''
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
      ''PROGRAM''
      '' CtrlType=Lookup''
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
      ''ITEM_SUBTYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_ITEM_SUBTYPE_SEL''
      ''  ParamsSrc=@AuxParams''
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
    InsertSqlId = ''ItemListIns''
    UpdateSqlId = ''ItemListIns''
    ParamsSQLSourceName = ''@AuxParams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''UCF_ITEM_VCH1~UCF_ITEM_VCH1''
      ''UCF_ITEM_VCH2~UCF_ITEM_VCH2''
      ''UCF_ITEM_NUM1~UCF_ITEM_NUM1''
      ''UCF_ITEM_FLAG1~UCF_ITEM_FLAG1''
      ''PART_TITLE~PART_TITLE''
      ''PARENT_ENG_PART_NO~PARENT_ENG_PART_NO''
      ''_OBJ20~PARENT_ENG_PART_CHG''
      ''STOCK_UOM~STOCK_UOM''
      ''_OBJ21~STANDARD_PART_FLAG''
      ''LOT_FLAG~WO_LOT_FLAG''
      ''_OBJ22~COMP_SERIAL_FLAG''
      ''_OBJ23~COMP_LOT_FLAG''
      ''_OBJ24~EXP_FLAG''
      ''_OBJ25~SPOOL_FLAG''
      ''_OBJ26~OPT_DC1_FLAG''
      ''_OBJ27~OPT_DC2_FLAG''
      ''_OBJ28~OPT_DC3_FLAG''
      ''_OBJ29~OPT_DC4_FLAG''
      ''_OBJ32~FLIGHT_SAFETY_FLAG''
      ''_OBJ33~FLIGHT_ESSENTIAL_FLAG''
      ''DEST_INSP_PLN_ID~DEST_INSP_PLAN_ID''
      ''SRC_INSP_PLN_ID~SOURCE_INSP_PLAN_ID''
      ''_OBJ36~SOURCE_INSP_PLAN_NO''
      ''_OBJ37~DEST_INSP_PLAN_NO''
      ''SERIAL_FLAG~WO_SERIAL_FLAG''
      ''UTILIZATION_RULE~UTILIZATION_RULE''
      ''TRACKABLE~TRACKABLE_FLAG''
      ''_OBJ39~UID_ITEM_FLAG''
      ''_OBJ40~UID_ACQUISITION_CODE''
      ''_OBJ41~UID_ENTRY_NAME''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''PART_NO~PART_NO''
      ''PART_CHG~PART_CHG''
      ''_OBJ42~ITEM_TYPE''
      ''_OBJ43~STD_COST''
      ''_OBJ44~PHANTOM_KIT_FLAG''
      ''_OBJ45~MFG_INSP_PLAN_NO''
      ''MFG_INSP_PLAN_ID~MFG_INSP_PLAN_ID''
      ''_OBJ46~SERIAL_NUM_GEN''
      ''_OBJ49~AVG_PURCHASE_PRICE_PER_UNIT''
      ''_OBJ51~AVG_LABOR_COST_PER_UNIT''
      ''_OBJ50~AVG_MATERIAL_COST_PER_UNIT''
      ''_OBJ52~AVG_ORDER_LEAD_TIME_DAYS''
      ''_OBJ53~PRICE_COST_UNIT''
      ''_OBJ55~MAKE_FLAG''
      ''_OBJ56~BUY_FLAG''
      ''_OBJ60~COMMODITY_JURISDICTION''
      ''_OBJ61~COMMODITY_CLASSIFICATION''
      ''_OBJ62~ASSOCIATE_CHANGE'')
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
      ''WORK_LOC=''
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
      ''UCF_ITEM_VCH5=''
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
      ''PRICE_COS';
p3 clob :='T_UNIT=''
      ''AVG_ORDER_LEAD_TIME_DAYS=''
      ''COMMODITY_JURISDICTION=''
      ''COMMODITY_CLASSIFICATION=''
      ''ASSOCIATE_CHANGE'')
    PassthroughParamsForInsert = False
  end
  object TOVCController
  end
end';
v_iclob clob;
begin
v_iclob := p1||p2||p3;
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

