
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_566E4A57C2B7F5BCE05387971F0A5473.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_566E4A57C2B7F5BCE05387971F0A5473';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanHeaderUpd';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MF1_1000025;Defect97;Defect194;SMRO_PLG_301;Defect1879';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 962
  Height = 800
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = '':@TransactionType Work Plan Header''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
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
  object _OBJ5: TsfDBInputEdit
    Left = 475
    Top = 45
    Width = 130
    Height = 21
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
    Caption = ''Engine Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ9: TsfDBInputEdit
    Left = 475
    Top = 157
    Width = 130
    Height = 21
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
    Caption = ''Main Work Loc *''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ10: TsfDBInputEdit
    Left = 155
    Top = 129
    Width = 450
    Height = 21
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
    Caption = ''Plan Title *''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ13: TsfDBInputEdit
    Left = 578
    Top = 482
    Width = 19
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 16
    EntryType = etAlphaNumeric
    Caption = ''Order UOM*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ15: TsfDBInputEdit
    Left = 595
    Top = 441
    Width = 21
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 15
    EntryType = etAlphaNumeric
    Caption = ''Mfg Index No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ17: TsfDBInputEdit
    Left = 470
    Top = 472
    Width = 31
    Height = 21
    BorderColor = clBlack
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
    Caption = ''Eng Item No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ18: TsfDBInputEdit
    Left = 540
    Top = 458
    Width = 22
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 14
    EntryType = etAlphaNumeric
    Caption = ''Eng Item Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ27: TsfDBInputEdit
    Left = 475
    Top = 394
    Width = 130
    Height = 21
    BorderColor = clBlack
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
    Caption = ''Mfg BOM Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ30: TsfDBInputEdit
    Left = 155
    Top = 641
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 20
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ31: TsfDBInputEdit
    Left = 475
    Top = 641
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 21
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ32: TsfDBInputEdit
    Left = 155
    Top = 608
    Width = 51
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 18
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN FLAG1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ36: TsfDBInputEdit
    Left = 475
    Top = 664
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 22
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH4''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ37: TsfDBInputEdit
    Left = 155
    Top = 685
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 23
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH5''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ38: TsfDBInputEdit
    Left = 475
    Top = 685
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 24
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH6''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ39: TsfDBInputEdit
    Left = 155
    Top = 709
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 25
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH7''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ40: TsfDBInputEdit
    Left = 475
    Top = 709
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 26
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN VCH8''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ41: TsfDBInputEdit
    Left = 475
    Top = 608
    Width = 52
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 19
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN FLAG2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ42: TsfDBInputEdit
    Left = 155
    Top = 733
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 27
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN NUM1''
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
    Left = 475
    Top = 733
    Width = 130
    Height = 20
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 28
    EntryType = etAlphaNumeric
    Caption = ''UCF PLAN NUM2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ47: TsfDBInputEdit
    Left = 155
    Top = 99
    Width = 130
    Height = 21
    BorderColor = clBlack
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
  object Plan_type: TsfDBInputEdit
    Left = 475
    Top = 101
    Width = 130
    Height = 21
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
    Caption = ''Plan Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object TsfLabel
    Left = 490
    Top = 413
    Width = 3
    Height = 13
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ50: TsfInputCheckBox
    Left = 396
    Top = 336
    Width = 90
    Height = 20
    Caption = ''Serial Control?''
    ReturnIsTab = False
    TabOrder = 6
    Version = ''1.4.0.3''
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
  object _OBJ51: TsfInputCheckBox
    Left = 407
    Top = 363
    Width = 79
    Height = 20
    Caption = ''Lot Control?''
    ReturnIsTab = False
    TabOrder = 7
    Version = ''1.4.0.3''
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
  object _OBJ52: TsfBevel
    Left = 36
    Top = 330
    Width = 581
    Height = 60
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0xA0A0A0''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ53: TsfDBInputEdit
    Left = 155
    Top = 45
    Width = 130
    Height = 21
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
    Caption = ''Plan No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Condition: TsfDBInputEdit
    Left = 475
    Top = 500
    Width = 27
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 17
    EntryType = etAlphaNumeric
    Caption = ''Condition''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UID_ITEM_FLAG: TsfDBInputEdit
    Left = 156
    Top = 336
    Width = 130
    Height = 21
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
    Caption = ''UID Item Flag''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UID_ENTRY_NAME: TsfDBInputEdit
    Left = 156
    Top = 363
    Width = 130
    Height = 21
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
    Caption = ''UID Entry Name''
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
    Left = 475
    Top = 449
    Width = 21
    Height = 21
    BorderColor = clBlack
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
  object BOM_NO: TsfDBInputEdit
    Left = 476
    Top = 417
    Width = 66
    Height = 21
    BorderColor = clBlack
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
    Caption = ''BOM No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ54: TsfDBInputEdit
    Left = 475
    Top = 73
    Width = 130
    Height = 21
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
  object _OBJ55: TsfDBInputEdit
    Left = 155
    Top = 73
    Width = 130
    Height = 21
    BorderColor = clBlack
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
    Caption = ''Item No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ56: TsfInputCheckBox
    Left = 614
    Top = 484
    Width = 84
    Height = 20
    Caption = ''Send Labor?*''
    ReturnIsTab = False
    TabOrder = 31
    Version = ''1.4.0.3''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object DispSeq: TsfInputRadioGroup
    Left = 155
    Top = 2
    Width = 450
    Height = 35
    Caption = ''Display Sequence''
    Columns = 4
    Ctl3D = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Items.Strings = (
      ''Item 1''
      ''Item 2'')
    ParentCtl3D = False
    ParentFont = True
    TabOrder = 32
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    ReadOnly = False
  end
  object ContFlowFlag: TsfInputCheckBox
    Left = 56
    Top = 492
    Width = 113
    Height = 20
    Caption = ''Operation Overlap?''
    ReturnIsTab = False
    TabOrder = 33
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
  object insp_plan_no: TsfDBInputEdit
    Left = 475
    Top = 522
    Width = 130
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 34
    EntryType = etAlphaNumeric
    Caption = ''Inspection Plan No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object insp_plan_id: TsfDBInputEdit
    Left = 582
    Top = 608
    Width = 20
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 35
    EntryType = etAlphaNumeric
    Caption = ''Insp Plan;Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object bom_id: TsfDBInputEdit
    Left = 591
    Top = 418
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
    TabOrder = 36
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
  object _OBJ57: TsfDBInputEdit
    Left = 155
    Top = 520
    Width = 130
    Height = 20
    BorderColor = clBlack
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
    Caption = ''Declared Language''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object LANGUAGE_CODE: TsfDBInputEdit
    Left = 290
    Top = 550
    Width = 10
    Height = 20
    BorderColor = clBlack
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
    Caption = ''Declared Language Code''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ58: TsfDBInputEdit
    Left = 475
    Top = 550
    Width = 130
    Height = 21
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 39
    EntryType = etAlphaNumeric
    Caption = ''PPV Full Or Partial*''
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ59: TsfInputCheckBox
    Left = 56
    Top = 547
    Width = 112
    Height = 29
    Caption = ''Production Process Verification Req''#39''d?''
    ReturnIsTab = False
    TabOrder = 40
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
  object _OBJ60: TsfDBInputEdit
    Left = 475
    Top = 577
    Width = 130
    Height = 21
    BorderColor = clBlack
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
    Caption = ''PPV Qty*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object WORKPKG_OBJ: TsfDBInputEdit
    Left = 155
    Top = 577
    Width = 121
    Height = 21
    BorderColor = clBlack
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
    Caption = ''Release Package*''
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ63: TsfDBInputMemo
    Left = 155
    Top = 161
    Width = 185
    Height = 113
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = False
    PopupMenu = UDVEditForm_2.UDVControlPopupMenu
    TabOrder = 43
    UseDockManager = True
    Version = ''2.5.1.0''
    Buffered = False
    Caption = ''''
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -13
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    FullHeight = 0
  end
  object _OBJ64: TsfLabel
    Left = 88
    Top = 206
    Width = 60
    Height = 17
    Caption = ''Authority''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ65: TsfDBInputMemo
    Left = 155
    Top = 398
    Width = 239
    Height = 93
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    F';
p2 clob :='ont.Color = clWindowText
    Font.Height = -13
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = False
    PopupMenu = UDVEditForm_2.UDVControlPopupMenu
    TabOrder = 44
    UseDockManager = True
    Version = ''2.5.1.0''
    Buffered = False
    Caption = ''''
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -13
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    FullHeight = 0
  end
  object _OBJ66: TsfLabel
    Left = 30
    Top = 439
    Width = 107
    Height = 17
    Caption = ''Chg Level Notes *''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ67: TsfDBInputEdit
    Left = 475
    Top = 186
    Width = 130
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 45
    EntryType = etAlphaNumeric
    Caption = ''US Jurisdiction''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ68: TsfDBInputEdit
    Left = 476
    Top = 218
    Width = 130
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 46
    EntryType = etAlphaNumeric
    Caption = ''US Classification''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ69: TsfDBInputEdit
    Left = 477
    Top = 250
    Width = 130
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 47
    EntryType = etAlphaNumeric
    Caption = ''Export License''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ70: TsfDBInputEdit
    Left = 155
    Top = 285
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
    TabOrder = 48
    EntryType = etAlphaNumeric
    Caption = ''Module Code''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PlanHdrUpd: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PLAN_ID''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=40''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_VERSION''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_REVISION''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''PLAN_ALTERATIONS''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''MODEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=True''
      '' PersistManualValues=Y''
      '' DependentFields=MFG_BOM_REV,BOM_NO''
      '' MaxLength=10''
      ''PROGRAM''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''LOW_NAV_LVL''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''PLAN_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''PLND_ORDER_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLND_CUST_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=40''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''MFG_INDEX_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLG_GROUP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ENG_PART_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''ENG_PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''ENG_GROUP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PROJECT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLND_MAX_ORDER_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLND_MIN_ORDER_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''INITIAL_STORES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FINAL_STORES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PART_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UNIT_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=5''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_VCH6''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_VCH8''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=True''
      '' Required=False''
      ''UCF_PLAN_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=2''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' HiddenMatrix=N''
      ''UCF_PLAN_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      ''PLND_WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=40''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''SERIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=True''
      '' MaxLength=1''
      '' PersistManualValues=Y''
      ''LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=True''
      '' MaxLength=1''
      '' PersistManualValues=Y''
      ''CONTINUE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLAN_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=20''
      '' PersistManualValues=Y''
      ''PLAN_CHANGE_LEVEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''ENGINE_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''SECURITY_GROUP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''LTA_SEND_FLAG''
      '' Update=LTA_SYSTEM_FLAG = ''#39''N''#39
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=LTA_ENABLED_FLAG=''#39''N''#39
      '' Required=LTA_ENABLED_FLAG=''#39''Y''#39
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH9''
      ''UCF_PLAN_VCH10''
      ''UCF_PLAN_VCH11''
      ''UCF_PLAN_VCH12''
      ''UCF_PLAN_VCH13''
      ''UCF_PLAN_VCH14''
      ''UCF_PLAN_VCH15''
      ''UCF_PLAN_NUM3''
      ''UCF_PLAN_NUM4''
      ''UCF_PLAN_NUM5''
      ''UCF_PLAN_DATE1''
      ''UCF_PLAN_DATE2''
      ''UCF_PLAN_DATE3''
      ''UCF_PLAN_DATE4''
      ''UCF_PLAN_DATE5''
      ''UCF_PLAN_FLAG3''
      ''UCF_PLAN_FLAG4''
      ''UCF_PLAN_FLAG5''
      ''UCF_PLAN_VCH255_1''
      ''UCF_PLAN_VCH255_2''
      ''UCF_PLAN_VCH255_3''
      ''UCF_PLAN_VCH4000_2''
      ''PLAN_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ORDER_UOM''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=True''
      ''CONDITION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''MFG_BOM_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4''
      '' CharCase=N''
      '' PersistManualValues=Y''
      
        '' Hidden=MRO_FLAG = ''#39''Y''#39'' AND (INST_TYPE = ''#39''MRO-Upgrade''#39'' OR PLAN_TY'' +
        ''PE = ''#39''OVERHAUL''#39'')''
      '' Required=False''
      ''UID_ENTRY_NAME''
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
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_SUBTYPE''
      '' Update=False''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      ''BOM_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=PLAN_TYPE = ''#39''OVERHAUL''#39
      '' Required=False''
      '' MaxLength=70''
      ''DISPLAY_SEQUENCE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' Hidden=True''
      '' DefaultValue=Execution Order''
      ''OPERATION_OVERLAP_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''INSP_PLAN_NO''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=False''
      '' DependentFields=INSP_PLAN_ID''
      ''INSP_PLAN_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''BOM_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''LANGUAGE_NAME''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''LANGUAGE_CODE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=2''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''PLND_LOCATION_ID''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PPV_REQ_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=False''
      ''PPV_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=INSTRUCTIONS_TYPE = ''#39''MFG''#39
      ''PPV_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=INSTRUCTIONS_TYPE = ''#39''MFG''#39
      '' MaxLength=3''
      ''COMMODITY_JURISDICTION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' MaxLength=50''
      ''COMMODITY_CLASSIFICATION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' MaxLength=50''
      ''TO_PWP_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''CHANGE_LEVEL_NOTE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' DefaultValue=Initial Revision''
      '' MaxLength=100'')
    InputFieldsEditControlSSL.Strings = (
      ''LOW_NAV_LVL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''PLAN_REVISION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''UCF_PLAN_VCH2''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH6''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH8''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_FLAG2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM1''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM2''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_CHANGE_LEVEL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ENGINE_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SECURITY_GROUP''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LTA_SEND_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''ENG_PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_INDEX_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH4''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''INSP_PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LANGUAGE_NAME''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_DECLAREDLANGUAGESEL''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   LANGUAGE_NAME=Language Name''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''LANGUAGE_CODE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH1''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_ALTERATIONS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CONTINUE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_UOM''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_uom_lookup''
      ''  ParamsSrc=PlanHdrSel''
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
      ''PPV_TYPE''
      '' CtrlType=List''
      ''  Values=Full~Partial''
      ''PPV_REQ_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''ENG_PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROJECT''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_project_type_lookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   Project=Project(10)''
      ''   PROJECT_DESC=Project Name(20)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''PLG_GROUP''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_plg_group_lookup''
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
      ''UNIT_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=EndUnitTypeLookup''
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
      ''PLND_CUST_ID''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_customer_lookup''
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
      ''ENG_GROUP''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_eng_group_lookup''
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
      ''MODEL''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_model_lookup''
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
      ''INITIAL_STORES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_MAX_ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_MIN_ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOT_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''SERIAL_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''CONDITION''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_CONDITIONLOOKUP''
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
      ''PLAN_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPERATION_OVERLAP_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''PART_CHG''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_part_no_chg_lookup''
      ''  ParamsSrc=PlanHdrSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''PART_NO''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_item_master_lookup''
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
      ''PLAN_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Dec';
p3 clob :='imal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FINAL_STORES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  SqlId=PlanProgramSel''
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
      ''PLND_LOCATION_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_PL_WORKLOCSEL2''
      ''  ParamsSrc=PlanHdrSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   plnd_work_loc=Main Work Location(20)''
      ''   loc_title=Location Title(24)''
      ''   COMPANY=Company''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UCF_PLAN_VCH3''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_TYPE''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_ITEM_TYPE_DEF_SEL''
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
      ''  Currency=N''
      ''  SqlId=MFI_PLG_ITEM_SUBTYPE_SEL''
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
      ''    SearchType=stAnyWords''
      ''UID_ITEM_FLAG''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_FND_UidItemFlagLkup''
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
      ''UID_ENTRY_NAME''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_FND_UidEntryLkup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
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
      ''MFG_BOM_REV''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PlMbomLookup''
      ''  ParamsSrc=PlanHdrSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   BOM_NO=Mfg BOM No''
      ''   MFG_BOM_REV=Mfg BOM Rev''
      ''   MBOM_REV_DATE=MBOM Rev Date''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''TO_PWP_ID''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_PLANPACKAGELKUP''
      ''  ParamsSrc=WorkPkgSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TO_PWP_ID=Release Package''
      ''   TO_PWP_STATUS=To Release Package Status''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''COMMODITY_CLASSIFICATION''
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
      ''PPV_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
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
      ''INSP_PLAN_NO''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_SQA_MfgInspPlanLkup''
      ''  ParamsSrc=PlanHdrSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   INSP_PLAN_NO=Insp Plan No''
      ''   INSP_PLAN_REV=Insp Plan Rev''
      ''   INSP_PLAN_TITLE=Insp Plan Title''
      ''   STATUS=Startus''
      ''   DATE_CREATED=Date Created''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''BOM_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISPLAY_SEQUENCE''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_DisplaySequenceLookUp''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CHANGE_LEVEL_NOTE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    UpdateSqlId = ''PWUST_PlanHdrUpd''
    ParamsSQLSourceName = 
      ''PlanHdrSel,GlobalParamsGet,MFI_FND_LtaEnabledFlag,UidInfoSel,Wor'' +
      ''kPkgSel,DUMMY''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ15~MFG_INDEX_NO''
      ''_OBJ17~ENG_PART_NO''
      ''_OBJ18~ENG_PART_CHG''
      ''_OBJ10~PLAN_TITLE''
      ''_OBJ13~ORDER_UOM''
      ''_OBJ5~PROGRAM''
      ''_OBJ31~UCF_PLAN_VCH2''
      ''_OBJ30~UCF_PLAN_VCH1''
      ''_OBJ36~UCF_PLAN_VCH4''
      ''_OBJ37~UCF_PLAN_VCH5''
      ''_OBJ38~UCF_PLAN_VCH6''
      ''_OBJ39~UCF_PLAN_VCH7''
      ''_OBJ40~UCF_PLAN_VCH8''
      ''_OBJ41~UCF_PLAN_FLAG2''
      ''_OBJ32~UCF_PLAN_FLAG1''
      ''_OBJ42~UCF_PLAN_NUM1''
      ''_OBJ43~UCF_PLAN_NUM2''
      ''_OBJ9~PLND_WORK_LOC''
      ''Plan_type~PLAN_TYPE''
      ''_OBJ51~LOT_FLAG''
      ''_OBJ50~SERIAL_FLAG''
      ''_OBJ53~PLAN_NO''
      ''Condition~CONDITION''
      ''_OBJ27~MFG_BOM_REV''
      ''UID_ITEM_FLAG~UID_ITEM_FLAG''
      ''UID_ENTRY_NAME~UID_ENTRY_NAME''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''_OBJ47~ITEM_TYPE''
      ''BOM_NO~BOM_NO''
      ''_OBJ54~PART_CHG''
      ''_OBJ55~PART_NO''
      ''_OBJ56~LTA_SEND_FLAG''
      ''DispSeq~DISPLAY_SEQUENCE''
      ''ContFlowFlag~OPERATION_OVERLAP_FLAG''
      ''insp_plan_no~INSP_PLAN_NO''
      ''insp_plan_id~INSP_PLAN_ID''
      ''bom_id~BOM_ID''
      ''_OBJ57~LANGUAGE_NAME''
      ''LANGUAGE_CODE~LANGUAGE_CODE''
      ''_OBJ59~PPV_REQ_FLAG''
      ''_OBJ58~PPV_TYPE''
      ''_OBJ60~PPV_QTY''
      ''WORKPKG_OBJ~TO_PWP_ID''
      ''_OBJ63~UCF_PLAN_VCH255_2''
      ''_OBJ65~CHANGE_LEVEL_NOTE''
      ''_OBJ67~UCF_PLAN_VCH10''
      ''_OBJ68~UCF_PLAN_VCH11''
      ''_OBJ69~UCF_PLAN_VCH12''
      ''_OBJ70~UCF_PLAN_VCH3'')
    DataDefinitions.Strings = (
      ''PLAN_ID=''
      ''PLAN_VERSION=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATIONS=''
      ''MODEL=''
      ''PART_CHG=''
      ''PROGRAM=''
      ''LOW_NAV_LVL=''
      ''PLAN_TITLE=''
      ''PLND_ORDER_QTY=''
      ''PLND_CUST_ID=''
      ''MFG_INDEX_NO=''
      ''PLG_GROUP=''
      ''ENG_PART_NO=''
      ''ENG_PART_CHG=''
      ''ENG_GROUP=''
      ''PROJECT=''
      ''PLND_MAX_ORDER_QTY=''
      ''PLND_MIN_ORDER_QTY=''
      ''INITIAL_STORES=''
      ''FINAL_STORES=''
      ''PART_NO=''
      ''UNIT_TYPE=''
      ''UCF_PLAN_VCH1=''
      ''UCF_PLAN_VCH2=''
      ''UCF_PLAN_FLAG1=''
      ''UCF_PLAN_VCH3=''
      ''UCF_PLAN_VCH4=''
      ''UCF_PLAN_VCH5=''
      ''UCF_PLAN_VCH6=''
      ''UCF_PLAN_VCH7=''
      ''UCF_PLAN_VCH8=''
      ''UCF_PLAN_FLAG2=''
      ''UCF_PLAN_NUM1=''
      ''UCF_PLAN_NUM2=''
      ''PLND_WORK_LOC=''
      ''SERIAL_FLAG=''
      ''LOT_FLAG=''
      ''CONTINUE=''
      ''PLAN_TYPE=''
      ''UCF_PLAN_FLAG2=''
      ''PLAN_REVISION''
      ''UCF_PLAN_NUM1=''
      ''LTA_SEND_FLAG=''
      ''PLND_WORK_LOC=''
      ''SERIAL_FLAG=''
      ''LOT_FLAG=''
      ''CONTINUE=''
      ''PLAN_TYPE=''
      ''PLAN_CHANGE_LEVEL=''
      ''ENGINE_TYPE=''
      ''SECURITY_GROUP=''
      ''LTA_SEND_FLAG=''
      ''UCF_PLAN_VCH9''
      ''UCF_PLAN_VCH10''
      ''UCF_PLAN_VCH11''
      ''UCF_PLAN_VCH12''
      ''UCF_PLAN_VCH13''
      ''UCF_PLAN_VCH14''
      ''UCF_PLAN_VCH15''
      ''UCF_PLAN_NUM3''
      ''UCF_PLAN_NUM4''
      ''UCF_PLAN_NUM5''
      ''UCF_PLAN_DATE1''
      ''UCF_PLAN_DATE2''
      ''UCF_PLAN_DATE3''
      ''UCF_PLAN_DATE4''
      ''UCF_PLAN_DATE5''
      ''UCF_PLAN_FLAG3''
      ''UCF_PLAN_FLAG4''
      ''UCF_PLAN_FLAG5''
      ''UCF_PLAN_VCH255_1''
      ''UCF_PLAN_VCH255_2''
      ''UCF_PLAN_VCH255_3''
      ''UCF_PLAN_VCH4000_2''
      ''PLAN_NO=''
      ''CONDITION=''
      ''MFG_BOM_REV=''
      ''UID_ENTRY_NAME=''
      ''UID_ITEM_FLAG=''
      ''ITEM_TYPE=''
      ''ITEM_SUBTYPE=''
      ''BOM_NO=''
      ''DISPLAY_SEQUENCE=''
      ''OPERATION_OVERLAP_FLAG=''
      ''INSP_PLAN_ID=''
      ''BOM_ID=''
      ''LANGUAGE_CODE=''
      ''ORDER_UOM=''
      ''PPV_REQ_FLAG=''
      ''PPV_TYPE=''
      ''PPV_QTY=''
      ''COMMODITY_JURISDICTION=''
      ''COMMODITY_CLASSIFICATION=''
      ''TO_PWP_ID=''
      ''CHANGE_LEVEL_NOTE='')
  end
  object MFI_FND_LtaEnabledFlag: TsfTransactionParamSource
    SelectSqlId = ''MFI_FND_LtaEnabledFlag''
    PublishParams = True
  end
  object GlobalParamsGet: TsfTransactionParamSource
    SelectSqlId = ''GlobalParamsGet''
    ParamsSQLSourceName = ''PlanHdrSel''
    PublishParams = True
  end
  object UidInfoSel: TsfTransactionParamSource
    SelectSqlId = ''MFI_PLG_UidEntrySel''
    ParamsSQLSourceName = ''PlanHdrSel''
    PublishParams = True
  end
  object WorkPkgSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_PlanWorkPkgSel''
    ParamsSQLSourceName = ''PlanHdrSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''TO_PWP_ID~0~TO_PWP_ID~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PWP_ID~0~PWP_ID~N~N~False~False~False~N~~~~~Default~N~~~'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''WorkPkgSel''
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


set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_56E18E3545EEDDE9E05387971F0A63A0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_56E18E3545EEDDE9E05387971F0A63A0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanHeaderTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MF1_1000775; Defect97; Defect194; Defect70; SMRO_WOE_208; Defect 821;SMRO_PLG_301; Defect 1373; Defect 1879';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1100
  Height = 500
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
  IMPopulateFromParams = False
  UdvControlType = uctPanel
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acHorizontal
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
  object _OBJ7: TsfUDVNavigator
    Left = 4
    Top = 4
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ29: TsfDBEdit
    Left = 375
    Top = 77
    Width = 265
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 2
    Text = ''''
    Caption = ''Plan Title''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ34: TsfDBEdit
    Left = 775
    Top = 114
    Width = 125
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 8
    Text = ''''
    Caption = ''End Unit Model''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ38: TsfDBEdit
    Left = 193
    Top = 114
    Width = 175
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 6
    Text = ''''
    Caption = ''Planned for Work Location''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ42: TsfDBEdit
    Left = 7
    Top = 374
    Width = 125
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 12
    Text = ''''
    Caption = ''Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ43: TsfDBEdit
    Left = 7
    Top = 114
    Width = 179
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 4
    Text = ''''
    Caption = ''Engine Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ44: TsfLabel
    Left = 957
    Top = 257
    Width = 4
    Height = 13
    Alignment = taRightJustify
    Caption = ''.''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ48: TsfDBEdit
    Left = 375
    Top = 114
    Width = 83
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 10
    Text = ''''
    Caption = ''Initial Stores''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ49: TsfDBEdit
    Left = 468
    Top = 114
    Width = 95
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 14
    Text = ''''
    Caption = ''Final Stores''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ52: TsfDBEdit
    Left = 647
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 7
    Text = ''''
    Caption = ''End Unit Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ54: TsfDBEdit
    Left = 7
    Top = 188
    Width = 179
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 11
    Text = ''''
    Caption = ''Track Unit By Serial No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ55: TsfDBEdit
    Left = 193
    Top = 188
    Width = 175
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 15
    Text = ''''
    Caption = ''Track Unit By Lot No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ56: TsfDBEdit
    Left = 181
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 22
    Text = ''''
    Caption = ''UCF PLAN VCH1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ57: TsfDBEdit
    Left = 703
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 25
    Text = ''''
    Caption = ''UCF PLAN VCH2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ58: TsfDBEdit
    Left = 540
    Top = 147
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 16
    Text = ''''
    Caption = ''SME''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ60: TsfDBEdit
    Left = 790
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 26
    Text = ''''
    Caption = ''UCF PLAN VCH4''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ61: TsfDBEdit
    Left = 355
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 23
    Text = ''''
    Caption = ''UCF PLAN VCH5''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ62: TsfDBEdit
    Left = 877
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 27
    Text = ''''
    Caption = ''UCF PLAN VCH6''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ63: TsfDBEdit
    Left = 442
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 24
    Text = ''''
    Caption = ''UCF PLAN VCH7''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ64: TsfDBEdit
    Left = 964
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 28
    Text = ''''
    Caption = ''UCF PLAN VCH8''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ65: TsfDBEdit
    Left = 94
    Top = 511
    Width = 80
    Height = 21
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 17
    Text = ''''
    Caption = ''UCF PLAN FLAG2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ66: TsfDBEdit
    Left = 529
    Top = 512
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 18
    Text = ''''
    Caption = ''UCF PLAN NUM1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ67: TsfDBEdit
    Left = 636
    Top = 147
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 19
    Text = ''''
    Caption = ''XClass Rec No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Plan_Type: TsfDBEdit
    Left = 7
    Top = 77
    Width = 179
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 5
    Text = ''''
    Caption = ''Plan Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object LTA_SEND_FLAG: TsfDBEdit
    Left = 576
    Top = 188
    Width = 95
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 3
    Text = ''''
    Caption = ''Labor Send Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ69: TsfDBEdit
    Left = 193
    Top = 77
    Width = 175
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Text = ''''
    Caption = ''Plan No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Condition: TsfDBEdit
    Left = 375
    Top = 188
    Width = 194
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 9
    Text = ''''
    Caption = ''Item Condition at Completion''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ITEM_FLAG: TsfDBEdit
    Left = 292
    Top = 374
    Width = 71
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 20
    Text = ''''
    Caption = ''Item UID Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ENTRY_NAME: TsfDBEdit
    Left = 382
    Top = 374
    Width = 135
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 21
    Text = ''''
    Caption = ''UID Entry Name''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ71: TsfDBEdit
    Left = 155
    Top = 374
    Width = 125
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 13
    Text = ''''
    Caption = ''Item Subtype''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object ContFlowFlag: TsfDBEdit
    Left = 678
    Top = 188
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 29
    Text = ''''
    Caption = ''Operation Overlap?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ73: TsfBevel
    Left = 8
    Top = 343
    Width = 900
    Height = 2
    Hint = ''Item/Engineering Information''
    Shape = bsFrame
    HighColor = ''clBtnShadow''
    LowColor = ''$997050''
    LineWidth = 2
  end
  object _OBJ74: TsfStdMarkupImage
    Left = 12
    Top = 327
    Width = 294
    Height = 22
    Hint = ''Item/Engineering Information''
    Picture.Data = {07544269746D617000000000}
    StdMarkup = ''LabelItemEngineeringInformation''
  end
  object _OBJ75: TsfStdMarkupImage
    Left = 12
    Top = 30
    Width = 294
    Height = 22
    Hint = ''Work Plan Information''
    Picture.Data = {07544269746D617000000000}
    StdMarkup = ''LabelWorkPlanInformation''
  end
  object _OBJ76: TsfBevel
    Left = 12
    Top = 52
    Width = 900
    Height = 2
    Shape = bsFrame
    HighColor = ''clBtnShadow''
    LowColor = ''$997050''
    LineWidth = 2
  end
  object _OBJ78: TsfDBEdit
    Left = 7
    Top = 147
    Width = 88
    Height = 16
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 30
    Text = ''''
    Caption = ''Plan Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object declaredLang: TsfDBEdit
    Left = 647
    Top = 77
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 31
    Text = ''''
    Caption = ''Declared Language''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object updateBuildPart: TsfCommandButton
    Left = 60
    Top = 4
    Width = 100
    Height = 21
    Hint = ''Update Build Item''
    Caption = ''Update Build Item''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 32
    OtherCommands.Strings = (
      
        ''Desc=Update Build Item,Priv=''#39''{REV_STATUS<>''#39#39''PLAN RELEASED''#39#39''}''#39'',Vi'' +
        ''sible={},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=I'' +
        ''nsert,UDVID=PWUST_5983E4A3DF8793F4E05387971F0A0AC3'')
  end
  object _OBJ85: TsfCommandButton
    Left = 163
    Top = 4
    Width = 100
    Height = 21
    Hint = ''update PPV Info''
    Caption = ''Update PPV Info''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 33
    OtherCommands.Strings = (
      
        ''Desc=update PPV Info,Priv={1=1},Visible=''#39''{@TOOLSTATE<>''#39#39''EditMode'' +
        ''s.Edit_PL''#39#39'' AND INST_TYPE = ''#39#39''MFG''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc='' +
        ''@Default,Action=UDV,UDVType=Update,UDVID=MFI_5D1E1A7F79A0468DBE2'' +
        ''0E62BBF878615'')
  end
  object _OBJ87: TsfDBEdit
    Left = 193
    Top = 147
    Width = 175
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 34
    Text = ''''
    Caption = ''Commodity Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ88: TsfDBEdit
    Left = 375
    Top = 147
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 35
    Text = ''''
    Caption = ''Commodity Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ89: TsfDBEdit
    Left = 7
    Top = 229
    Width = 179
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 36
    Text = ''''
    Caption = ''US Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ90: TsfDBEdit
    Left = 193
    Top = 228
    Width = 175
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 37
    Text = ''''
    Caption = ''US Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ91: TsfDBEdit
    Left = 375
    Top = 228
    Width = 179
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 38
    Text = ''''
    Caption = ''Export License''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ92: TsfDBMemo
    Left = 571
    Top = 226
    Width = 304
    Height = 80
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssVertical
    ShowHint = False
    TabOrder = 39
    Zoom = 100
    MemoCaption = ''Chg Level Notes''
    CaptionPos = cpAbove
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object _OBJ93: TsfDBEdit
    Left = 746
    Top = 146
    Width = 121
    Height = 21
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 40
    Text = ''''
    Caption = ''Module Code''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Authority: TsfDBEdit
    Left = 572
    Top = 371
    Width = 304
    Height = 80
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 41
    Text = ''''
    Caption = ''Authority''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ94: TsfDBEdit
    Left = 6
    Top = 417
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 42
    Text = ''''
    Caption = ''Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object MBOM_NO: TsfDBEdit
    Left = 7
    Top = 270
    Width = 178
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 43
    Text = ''''
    Caption = ''MBOM No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object MBOM_REV: TsfDBEdit
    Left = 193
    Top = 270
    Width = 172
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 44
    Text = ''''
    Caption = ''MBOM Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object dispSeq: TsfDBEdit
    Left = 375
    Top = 270
    Width = 179
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 45
    Text = ''''
    Caption = ''Display Sequence''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object PlanHdrSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PlanHdrSel''
    LinkedControls.Strings = (
      ''_OBJ7~''
      ''_OBJ43~PROGRAM''
      ''_OBJ34~MODEL''
      ''_OBJ38~PLND_WORK_LOC''
      ''_OBJ49~FINAL_STORES''
      ''_OBJ48~INITIAL_STORES''
      ''_OBJ52~UNIT_TYPE''
      ''_OBJ29~PLAN_TITLE''
      ''_OBJ54~SERIAL_FLAG''
      ''_OBJ55~LOT_FLAG''
      ''_OBJ56~UCF_PLAN_VCH1''
      ''_OBJ57~UCF_PLAN_VCH2''
      ''_OBJ58~UCF_PLAN_FLAG1''
      ''_OBJ60';
p2 clob :='~UCF_PLAN_VCH4''
      ''_OBJ61~UCF_PLAN_VCH5''
      ''_OBJ62~UCF_PLAN_VCH6''
      ''_OBJ63~UCF_PLAN_VCH7''
      ''_OBJ64~UCF_PLAN_VCH8''
      ''_OBJ65~UCF_PLAN_FLAG2''
      ''_OBJ66~UCF_PLAN_NUM1''
      ''_OBJ67~UCF_PLAN_NUM2''
      ''Plan_Type~PLAN_TYPE''
      ''LTA_SEND_FLAG~LTA_SEND_FLAG''
      ''_OBJ69~PLAN_NO''
      ''Condition~CONDITION''
      ''_OBJ71~ITEM_SUBTYPE''
      ''_OBJ42~ITEM_TYPE''
      ''ContFlowFlag~OPERATION_OVERLAP_FLAG''
      ''_OBJ78~PLAN_REVISION''
      ''declaredLang~LANGUAGE_NAME''
      ''updateBuildPart~''
      ''_OBJ85~''
      ''_OBJ87~COMMODITY_JURISDICTION''
      ''_OBJ88~COMMODITY_CLASSIFICATION''
      ''_OBJ89~UCF_PLAN_VCH10''
      ''_OBJ90~UCF_PLAN_VCH11''
      ''_OBJ91~UCF_PLAN_VCH12''
      ''_OBJ92~UCF_PLAN_VCH4000_1''
      ''_OBJ93~UCF_PLAN_VCH3''
      ''Authority~UCF_PLAN_VCH255_2''
      ''_OBJ94~PART_NO''
      ''dispSeq~DISPLAY_SEQUENCE'')
    ParamsSQLSourceName = ''@TOOLSCOPE''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_ID~40~PLAN_ID~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''PART_NO~50~PART_NO~N~N~PLAN_TYPE = ''#39''OVERHAUL''#39''~N~False~N~~~~~Defa'' +
        ''ult~N~~~''
      ''ITEM_TYPE~5~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~10~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''PART_CHG~4~PART_CHG~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~Default~N~~'' +
        ''~''
      ''PROGRAM~30~PROGRAM~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''PLAN_VERSION~10~PLAN_VERSION~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~De'' +
        ''fault~N~~~''
      ''PLAN_REVISION~10~PLAN_REVISION~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''PLAN_ALTERATIONS~10~PLAN_ALTERATIONS~N~N~N~N~False~N~~~~~Default'' +
        ''~N~~~''
      ''PLAN_UPDT_NO~10~PLAN_UPDT_NO~N~N~N~N~False~N~~~~~Default~N~~~''
      ''PLAN_STATE~20~PLAN_STATE~N~N~N~N~False~N~~~~~Default~N~~~''
      ''REV_STATUS~20~REV_STATUS~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''REV_LOCK_STATE~20~REV_LOCK_STATE~N~N~N~N~False~N~~~~~Default~N~~'' +
        ''~''
      ''REV_LOCKED_BY~30~REV_LOCKED_BY~N~N~N~N~False~N~~~~~Default~N~~~''
      ''PLAN_TITLE~80~PLAN_TITLE~N~N~N~N~False~N~~~~~Default~N~~~''
      ''PLAN_STATUS~20~PLAN_STATUS~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''PLND_ORDER_QTY~10~PLND_ORDER_QTY~N~N~N~N~False~N~~~~~Default~N~~'' +
        ''~''
      ''PLND_CUST_ID~40~PLND_CUST_ID~N~N~True~N~False~N~~~~~Default~N~~~''
      
        ''ORDER_UOM~10~ORDER_UOM~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~Default~'' +
        ''N~~~''
      ''MODEL~20~MODEL~N~N~True~N~False~N~~~~~Default~N~~~''
      
        ''MFG_INDEX_NO~10~MFG_INDEX_NO~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~De'' +
        ''fault~N~~~''
      ''PLG_GROUP~20~PLG_GROUP~N~N~True~N~False~N~~~~~Default~N~~~''
      ''PLND_WORK_LOC~30~PLND_WORK_LOC~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''ENG_PART_NO~50~ENG_PART_NO~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''ENG_PART_CHG~4~ENG_PART_CHG~N~N~MRO_FLAG = ''#39''Y''#39''~N~False~N~~~~~Def'' +
        ''ault~N~~~''
      ''ENG_GROUP~20~ENG_GROUP~N~N~True~N~False~N~~~~~Default~N~~~''
      ''LOW_NAV_LVL~4~LOW_NAV_LVL~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''INITIAL_STORES~20~INITIAL_STORES~N~N~True~N~False~N~~~~~Default~'' +
        ''N~~~''
      ''FINAL_STORES~20~FINAL_STORES~N~N~True~N~False~N~~~~~Default~N~~~''
      ''PLAN_TYPE~20~PLAN_TYPE~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~30~UPDT_USERID~N~N~N~N~False~N~MFI_2869~~~~Default~N'' +
        ''~~~''
      ''TIME_STAMP~18~TIME_STAMP~N~N~N~N~False~N~~~~~Default~N~~~''
      ''NEXT_PLAN_ID~40~NEXT_PLAN_ID~N~N~N~N~False~N~~~~~Default~N~~~''
      ''UNIT_TYPE~30~UNIT_TYPE~N~N~True~N~False~N~~~~~Default~N~~~''
      ''SEQ_FLAG~1~SEQ_FLAG~N~N~N~N~False~N~~~~~Default~N~~~''
      ''ITEM_ID~40~ITEM_ID~N~N~N~N~False~N~~~~~Default~N~~~''
      ''PROJECT~30~PROJECT~N~N~True~N~False~N~~~~~Default~N~~~''
      
        ''PLND_MAX_ORDER_QTY~10~PLND_MAX_ORDER_QTY~N~N~True~N~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''PLND_MIN_ORDER_QTY~10~PLND_MIN_ORDER_QTY~N~N~True~N~False~N~~~~~'' +
        ''Default~N~~~''
      ''LOCK_STATE~20~LOCK_STATE~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''NEEDS_REVIEW_FLAG~1~NEEDS_REVIEW_FLAG~N~N~N~N~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH1~4~Classification;Code~N~N~Y~N~False~N~~~~~Default~'' +
        ''N~~~''
      ''UCF_PLAN_VCH2~30~Mission~N~N~Y~N~False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_VCH3~10~Module Code~N~N~False~N~False~N~~~~~Default~N~~'' +
        ''~''
      ''UCF_PLAN_VCH4~15~Use Level~N~N~Y~N~False~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH5~30~ADC Title Desc~N~N~Y~N~False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_VCH6~50~Source;Document~N~N~Y~N~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''UCF_PLAN_VCH7~50~Source Doc Issue Date~N~N~Y~N~False~N~~~~~Defau'' +
        ''lt~N~~~''
      ''UCF_PLAN_VCH8~50~UCF_PLAN_VCH8~N~N~Y~N~False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_FLAG1~1~UCF_PLAN_FLAG1~N~N~False~N~False~N~~~~~Default~'' +
        ''N~~~''
      ''UCF_PLAN_FLAG2~1~UCF_PLAN_FLAG2~N~N~Y~N~False~N~~~~~Default~N~~~''
      ''UCF_PLAN_NUM1~10~Sigma;Category~N~N~Y~N~N~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_NUM2~10~UCF_PLAN_NUM2~N~N~False~N~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''INSP_PLAN_NO~10~INSP_PLAN_NO~N~N~INST_TYPE <> ''#39''MFG''#39''~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''PLND_LOCATION_ID~0~PLND_LOCATION_ID~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~~''
      
        ''EXPLICIT_BOM_LINK_FLAG~1~Explicit BOM Link~N~N~MRO_FLAG = ''#39''Y''#39'' AN'' +
        ''D INST_TYPE = ''#39''MRO-Part''#39''~False~False~N~~~~~Default~N~~~''
      
        ''PPV_REQ_FLAG~1~PPV_REQ_FLAG~N~N~INST_TYPE <> ''#39''MFG''#39''~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''PPV_TYPE~5~PPV_TYPE~N~N~INST_TYPE <> ''#39''MFG''#39''~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''PPV_QTY~3~PPV_QTY~N~N~INST_TYPE <> ''#39''MFG''#39''~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''PPV_INPROCESS_QTY~3~PPV_INPROCESS_QTY~N~N~INST_TYPE <> ''#39''MFG''#39''~Fal'' +
        ''se~False~N~~~~~Default~N~~~''
      
        ''PPV_PENDING_QTY~3~PPV_PENDING_QTY~N~N~INST_TYPE <> ''#39''MFG''#39''~False~F'' +
        ''alse~N~~~~~Default~N~~~''
      
        ''PPV_COMPLETE_QTY~3~PPV_COMPLETE_QTY~N~N~INST_TYPE <> ''#39''MFG''#39''~False'' +
        ''~False~N~~~~~Default~N~~~''
      
        ''LAST_PPV_COMPLETE_DATE~15~LAST_PPV_COMPLETE_DATE~N~N~INST_TYPE <'' +
        ''> ''#39''MFG''#39''~False~False~N~~~~~Default~N~~~''
      
        ''COMMODITY_JURISDICTION~15~Commodity Jurisdiction~N~N~False~False'' +
        ''~False~N~~~~~Default~N~~~''
      
        ''COMMODITY_CLASSIFICATION~15~Commodity Classification~N~N~False~F'' +
        ''alse~False~N~~~~~Default~N~~~''
      
        ''LTA_SEND_FLAG~0~LTA_SEND_FLAG~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''OPERATION_OVERLAP_FLAG~0~OPERATION_OVERLAP_FLAG~N~N~True~False~F'' +
        ''alse~N~~~~~Default~N~~~''
      
        ''DISPLAY_SEQUENCE~0~DISPLAY_SEQUENCE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''UCF_PLAN_VCH255_2~0~Authority~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH4000_1~0~Chg Level Notes~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~~''
      
        ''CONDITION~0~Item Condition at Completion~N~N~True~False~True~N~~'' +
        ''~~~Default~N~~~''
      ''LOT_FLAG~0~LOT_FLAG~N~N~True~False~True~N~~~~~Default~N~~~''
      ''SERIAL_FLAG~0~SERIAL_FLAG~N~N~True~False~True~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_PLAN_VCH2~Edit''
      ''UCF_PLAN_FLAG1~Edit''
      ''UCF_PLAN_FLAG2~Edit''
      ''PPV_QTY~Edit''
      ''PPV_INPROCESS_QTY~Edit''
      ''PPV_COMPLETE_QTY~Edit''
      ''ORDER_UOM~Edit''
      ''MODEL~Edit''
      ''PLG_GROUP~Edit''
      ''ENG_GROUP~Edit''
      ''LOW_NAV_LVL~Edit''
      ''FINAL_STORES~Edit''
      ''UPDT_USERID~Edit''
      ''TIME_STAMP~Edit''
      ''NEXT_PLAN_ID~Edit''
      ''UNIT_TYPE~Edit''
      ''PLND_MAX_ORDER_QTY~Edit''
      ''PLND_MIN_ORDER_QTY~Edit''
      ''LOCK_STATE~Edit''
      ''PROGRAM~Edit''
      ''PLAN_UPDT_NO~Edit''
      ''PLAN_STATE~Edit''
      ''REV_LOCKED_BY~Edit''
      ''PLAN_TITLE~Edit''
      ''PLAN_STATUS~Edit''
      ''UCF_PLAN_VCH3~Edit''
      ''ITEM_SUBTYPE~Edit''
      ''PART_NO~Edit''
      ''DISPLAY_SEQUENCE~Edit'')
    PrintTitle = ''Plan Header''
    InputUDVId = ''MF1_1000025''
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PART_NO''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PART_CHG''
      ''PROGRAM''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''PLAN_UPDT_NO''
      ''PLAN_STATE''
      ''REV_STATUS''
      ''REV_LOCK_STATE''
      ''REV_LOCKED_BY''
      ''PLAN_TITLE''
      ''PLAN_STATUS''
      ''PLND_ORDER_QTY''
      ''PLND_CUST_ID''
      ''ORDER_UOM''
      ''MODEL''
      ''MFG_INDEX_NO''
      ''PLG_GROUP''
      ''PLND_WORK_LOC''
      ''ENG_PART_NO''
      ''ENG_PART_CHG''
      ''ENG_GROUP''
      ''LOW_NAV_LVL''
      ''INITIAL_STORES''
      ''FINAL_STORES''
      ''PLAN_TYPE''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''NEXT_PLAN_ID''
      ''UNIT_TYPE''
      ''SEQ_FLAG''
      ''ITEM_ID''
      ''PROJECT''
      ''PLND_MAX_ORDER_QTY''
      ''PLND_MIN_ORDER_QTY''
      ''LOCK_STATE''
      ''NEEDS_REVIEW_FLAG''
      ''UCF_PLAN_VCH1''
      ''UCF_PLAN_VCH2''
      ''UCF_PLAN_VCH3''
      ''UCF_PLAN_VCH4''
      ''UCF_PLAN_VCH5''
      ''UCF_PLAN_VCH6''
      ''UCF_PLAN_VCH7''
      ''UCF_PLAN_VCH8''
      ''UCF_PLAN_FLAG1''
      ''UCF_PLAN_FLAG2''
      ''UCF_PLAN_NUM1''
      ''UCF_PLAN_NUM2''
      ''INSP_PLAN_NO''
      ''PLND_LOCATION_ID''
      ''EXPLICIT_BOM_LINK_FLAG''
      ''PPV_REQ_FLAG''
      ''PPV_TYPE''
      ''PPV_QTY''
      ''PPV_INPROCESS_QTY''
      ''PPV_PENDING_QTY''
      ''PPV_COMPLETE_QTY''
      ''LAST_PPV_COMPLETE_DATE''
      ''COMMODITY_JURISDICTION''
      ''COMMODITY_CLASSIFICATION''
      ''LTA_SEND_FLAG''
      ''OPERATION_OVERLAP_FLAG''
      ''DISPLAY_SEQUENCE''
      ''UCF_PLAN_VCH255_2''
      ''UCF_PLAN_VCH4000_1''
      ''CONDITION''
      ''LOT_FLAG''
      ''SERIAL_FLAG'')
    ConsolidatedQuery = False
  end
  object UidInfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_UidInfoSel''
    LinkedControls.Strings = (
      ''UID_ITEM_FLAG~UID_ITEM_FLAG''
      ''UID_ENTRY_NAME~UID_ENTRY_NAME'')
    ParamsSQLSourceName = ''PlanHdrSel''
    SelectedFields.Strings = (
      
        ''UID_ENTRY_NAME~0~UID_ENTRY_NAME~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''UID_ITEM_FLAG~0~UID_ITEM_FLAG~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''ENTERPRISE_ID~0~ENTERPRISE_ID~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''DATA_QUALIFIER~0~DATA_QUALIFIER~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''CONSTRUCT_TYPE~0~CONSTRUCT_TYPE~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''ISSUE_AGENCY_CODE~0~ISSUE_AGENCY_CODE~N~N~True~False~False~N~~~~'' +
        ''~Default~N~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''SECURITY_GROUP~Edit''
      ''UID_ENTRY_NAME~Edit'')
    DataDefinitions.Strings = (
      ''UID_ENTRY_NAME''
      ''UID_ITEM_FLAG''
      ''ENTERPRISE_ID''
      ''DATA_QUALIFIER''
      ''CONSTRUCT_TYPE''
      ''ISSUE_AGENCY_CODE''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
  end
  object PLAN_ITEM_MBOM_SEL: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PlanItemMBOMChgSel''
    LinkedControls.Strings = (
      ''MBOM_NO~BOM_NO''
      ''MBOM_REV~MFG_BOM_CHG'')
    ParamsSQLSourceName = ''PlanHdrSel''
    SelectedFields.Strings = (
      
        ''MFG_BOM_CHG~0~MFG_BOM_CHG~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''BOM_NO~0~BOM_NO~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''BOM_NO~Edit'')
    DataDefinitions.Strings = (
      ''MFG_BOM_CHG''
      ''ITEM_ID2''
      ''TEMP_BOM_ID''
      ''BOM_NO''
      ''SECURITY_GROUP''
      ''NEW_ITEM_NO''
      ''NEW_ITEM_REV''
      ''EXPLICIT_BOM_LINK_FLAG''
      ''PLAN_TYPE''
      ''WORK_FLOW''
      ''ASGND_WORK_LOC'')
    ConsolidatedQuery = False
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

