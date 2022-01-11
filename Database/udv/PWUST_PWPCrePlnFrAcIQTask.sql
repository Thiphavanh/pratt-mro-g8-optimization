
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5654E83DD5D8D71DE05387971F0ACC0E.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5654E83DD5D8D71DE05387971F0ACC0E';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PWPCrePlnFrAcIQTask';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MFI_1003829; SMRO_WOE_208,SMRO_APP_301,SMRO_PLG_301';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = -390
  Width = 800
  Height = 680
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create Plan''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
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
  AutoRefresh = arRefresh
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = True
  BottomMargin = -1
  object Sect_Edit: TsfDBInputEdit
    Left = 451
    Top = 353
    Width = 133
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
    TabOrder = 15
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
  object PROGRAM: TsfDBInputEdit
    Left = 124
    Top = 263
    Width = 133
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -12
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
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
  object Plan_Name_Edit: TsfDBInputEdit
    Left = 124
    Top = 203
    Width = 460
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -12
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
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
  object Plan_ID_Edit: TsfDBInputEdit
    Left = 124
    Top = 173
    Width = 200
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
  object _OBJ2: TsfDBInputEdit
    Left = 33
    Top = 572
    Width = 220
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
    TabOrder = 19
    EntryType = etAlphaNumeric
    Caption = ''Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object WorkLocEdit: TsfDBInputEdit
    Left = 451
    Top = 263
    Width = 133
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
    Caption = ''Work Location *''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Model_Edit: TsfDBInputEdit
    Left = 299
    Top = 320
    Width = 21
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
    TabOrder = 11
    EntryType = etAlphaNumeric
    Caption = ''Model''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object EngPartChg_Edit: TsfDBInputEdit
    Left = 451
    Top = 383
    Width = 133
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
    TabOrder = 16
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
  object EngGroupEdit: TsfDBInputEdit
    Left = 451
    Top = 413
    Width = 133
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
    TabOrder = 17
    EntryType = etAlphaNumeric
    Caption = ''Eng Group''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Customer: TsfDBInputEdit
    Left = 121
    Top = 323
    Width = 135
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
    TabOrder = 14
    EntryType = etAlphaNumeric
    Caption = ''customer ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ10: TsfDBInputEdit
    Left = 124
    Top = 293
    Width = 133
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
    TabOrder = 10
    EntryType = etAlphaNumeric
    Caption = ''Project''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Part_Chg: TsfDBInputEdit
    Left = 451
    Top = 173
    Width = 133
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
  object Plan_type: TsfDBInputEdit
    Left = 124
    Top = 143
    Width = 200
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
    Caption = ''Plan Type*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ11: TsfDBInputEdit
    Left = 257
    Top = 572
    Width = 66
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
    TabOrder = 20
    EntryType = etAlphaNumeric
    Caption = ''Item Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ12: TsfDBInputEdit
    Left = 458
    Top = 625
    Width = 117
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
    TabOrder = 28
    EntryType = etAlphaNumeric
    Caption = ''Program''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ14: TsfDBInputEdit
    Left = 327
    Top = 572
    Width = 122
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
    TabOrder = 21
    EntryType = etAlphaNumeric
    Caption = ''Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ15: TsfDBInputEdit
    Left = 269
    Top = 625
    Width = 60
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
    TabOrder = 25
    EntryType = etAlphaNumeric
    Caption = ''Plan;Version''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ16: TsfDBInputEdit
    Left = 332
    Top = 625
    Width = 60
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
    TabOrder = 26
    EntryType = etAlphaNumeric
    Caption = ''Plan;Revision''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ17: TsfDBInputEdit
    Left = 395
    Top = 625
    Width = 60
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
    TabOrder = 27
    EntryType = etAlphaNumeric
    Caption = ''Plan;Alterations''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ18: TsfLabel
    Left = 28
    Top = 528
    Width = 126
    Height = 13
    Caption = ''Copy From Plan (Optional)''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ21: TsfDBInputEdit
    Left = 124
    Top = 233
    Width = 133
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
  object _OBJ23: TsfBevel
    Left = 19
    Top = 543
    Width = 567
    Height = 125
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object SourcePlanNo: TsfDBInputEdit
    Left = 151
    Top = 625
    Width = 115
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
    TabOrder = 24
    EntryType = etAlphaNumeric
    Caption = ''Plan; No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object FromPlanType: TsfDBInputEdit
    Left = 549
    Top = 596
    Width = 23
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
    TabOrder = 23
    EntryType = etAlphaNumeric
    Caption = ''''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object INSTR_TYPE: TsfDBInputEdit
    Left = 330
    Top = 144
    Width = 29
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
    Caption = ''''
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
    Left = 451
    Top = 233
    Width = 133
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
  object FromItemSubtype: TsfDBInputEdit
    Left = 453
    Top = 572
    Width = 122
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
    TabOrder = 22
    EntryType = etAlphaNumeric
    Caption = ''Item Subtype''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ24: TsfInputCheckBox
    Left = 27
    Top = 619
    Width = 120
    Height = 30
    Caption = ''Copy Component Parts?''
    ReturnIsTab = False
    TabOrder = 29
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
  object dispseq: TsfInputRadioGroup
    Left = 124
    Top = 98
    Width = 460
    Height = 39
    Caption = ''Display Sequence''
    Columns = 4
    Ctl3D = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Items.Strings = (
      ''Item 1''
      ''Item 2'')
    ParentCtl3D = False
    ParentFont = True
    TabOrder = 30
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    ReadOnly = False
  end
  object Mbom_chg_edit: TsfDBInputEdit
    Left = 451
    Top = 323
    Width = 133
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
    Caption = ''MBOM Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object Bom_no_edit: TsfDBInputEdit
    Left = 450
    Top = 292
    Width = 133
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
    Caption = ''MBOM No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ25: TsfDBInputEdit
    Left = 451
    Top = 143
    Width = 133
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
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Work Flow*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object insp_plan_no: TsfDBInputEdit
    Left = 451
    Top = 443
    Width = 133
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
    TabOrder = 18
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
    Left = 597
    Top = 443
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
    TabOrder = 31
    EntryType = etAlphaNumeric
    Caption = ''Insp Plan Id''
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
    Left = 315
    Top = 290
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
    TabOrder = 32
    EntryType = etAlphaNumeric
    Caption = ''bom id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PWP_ID: TsfDBInputEdit
    Left = 124
    Top = 353
    Width = 133
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
    TabOrder = 33
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
  object _OBJ26: TsfInputCheckBox
    Left = 608
    Top = 151
    Width = 36
    Height = 35
    Caption = ''Production Process Verification Req''#39''d?''
    ReturnIsTab = False
    TabOrder = 34
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
  object _OBJ27: TsfDBInputEdit
    Left = 451
    Top = 472
    Width = 133
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
    TabOrder = 35
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
  object _OBJ28: TsfDBInputEdit
    Left = 372
    Top = 507
    Width = 32
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
    Caption = ''PPV Qty*''
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ29: TsfInputCheckBox
    Left = 605
    Top = 219
    Width = 37
    Height = 20
    Caption = ''Associate Change?''
    ReturnIsTab = False
    TabOrder = 37
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
  object _OBJ30: TsfDBInputEdit
    Left = 625
    Top = 179
    Width = 17
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
    TabOrder = 38
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
  object _OBJ31: TsfDBInputMemo
    Left = 122
    Top = 419
    Width = 185
    Height = 91
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = False
    TabOrder = 39
    UseDockManager = True
    Version = ''2.5.1.0''
    Buffered = False
    Caption = ''''
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -13
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    PlainText = True
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    FullHeight = 0
  end
  object _OBJ32: TsfLabel
    Left = 45
    Top = 433
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
  object Module: TsfDBInputEdit
    Left = 124
    Top = 385
    Width = 133
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
    TabOrder = 40
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
  object PWUST_PL_Create_Plan: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''MODEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TASK_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PROGRAM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=PLAN_TYPE=''#39''OVERHAUL''#39
      '' PersistManualValues=Y''
      ''PLND_CUST_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''LOW_NAV_LVL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ENG_PART_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39'' OR INSTRUCTIONS_TYPE=''#39#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''ENG_PART_CHG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39'' OR INSTRUCTIONS_TYPE=''#39#39
      '' Required=False''
      '' PersistManualValues=Y''
     ';
p2 clob :='''ENG_GROUP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39'' OR INSTRUCTIONS_TYPE=''#39#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''CHANGE_DOC_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PROJECT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
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
      ''ASGND_WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=40''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=N''
      ''PLAN_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=20''
      '' PersistManualValues=Y''
      '' DependentFields=WORK_FLOW''
      ''COPY_COMPONENTS_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=Y''
      '' PersistManualValues=Y''
      ''FROM_PROGRAM''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FROM_VER''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FROM_REV''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FROM_ALT''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=10''
      ''UCF_PLAN_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH6''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH8''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH9''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH10''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH11''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH12''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH13''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH14''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH15''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_NUM3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_NUM4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_NUM5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_FLAG3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_FLAG4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_FLAG5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_DATE1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_DATE2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_DATE3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_DATE4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_DATE5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH255_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH255_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=254''
      ''UCF_PLAN_VCH255_3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH4000_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=Initial Revision''
      ''UCF_PLAN_VCH4000_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FROM_PLAN_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''NEW_ITEM_NO''
      '' Update=IS_AFFECTED_TO_RESULTING_MAPPING=''#39''Y''#39
      '' Insert=IS_AFFECTED_TO_RESULTING_MAPPING=''#39''Y''#39
      '' PopulateForInsert=Y''
      '' MaxLength=50''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=255''
      ''FROM_ITEM_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FROM_ITEM_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''NEW_ITEM_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' DefaultValue=N/A''
      ''FROM_ITEM_REV''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=10''
      ''FROM_PLAN_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''ITEM_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=Part''
      ''INSTRUCTIONS_TYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' DependentFields=PLAN_TYPE''
      ''ITEM_SUBTYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FROM_ITEM_SUBTYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DISPLAY_SEQUENCE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      '' DefaultValue=Execution Order''
      ''FROM_DISPLAY_SEQUENCE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MFG_BOM_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' MaxLength=10''
      '' Hidden=PLAN_TYPE=''#39''OVERHAUL''#39
      ''BOM_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' MaxLength=70''
      '' Hidden=PLAN_TYPE=''#39''OVERHAUL''#39
      ''WORK_FLOW''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=50''
      ''FINAL_STORES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''BOM_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''INSP_PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
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
      '' Required=False''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' DependentFields=INSP_PLAN_ID''
      ''LOCATION_ID''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=True''
      ''PWP_ID''
      '' Update=PWP_ID<>''#39#39'' OR CHANGE_REQUEST_ID=''#39#39
      '' Insert=PWP_ID<>''#39#39'' OR CHANGE_REQUEST_ID=''#39#39
      '' PopulateForInsert=Y''
      '' MaxLength=20''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''CHANGE_REQUEST_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PLANNED_ACTION_ID''
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
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=False''
      '' MaxLength=1''
      '' DefaultValue=Y''
      ''PPV_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=INSTRUCTIONS_TYPE = ''#39''MFG''#39
      '' DefaultValue=Full''
      ''ASSOCIATE_CHANGE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=''#39''N''#39
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''PPV_QTY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=3''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE <> ''#39''MFG''#39
      '' Required=INSTRUCTIONS_TYPE = ''#39''MFG''#39
      ''ORDER_UOM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      
        '' Required=INSTRUCTIONS_TYPE<>''#39''MRO-Part''#39'' AND INSTRUCTIONS_TYPE<>''#39 +
        ''MRO-Upgrade''#39)
    InputFieldsEditControlSSL.Strings = (
      ''UCF_PLAN_VCH4000_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH255_3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH255_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_DATE5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_DATE4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_DATE3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_DATE2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_DATE1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_FLAG5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_FLAG4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_FLAG3''
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
      ''UCF_PLAN_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH15''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH14''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH12''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH11''
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
      ''UCF_PLAN_VCH6''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ALT''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ENG_PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_PLAN_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ENG_PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLANNED_ACTION_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PPV_REQ_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''PPV_TYPE''
      '' CtrlType=List''
      ''  Values=Full~Partial''
      ''CHANGE_REQUEST_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH10''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ITEM_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_PLAN_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FINAL_STORES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
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
      ''PPV_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH9''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
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
      ''FROM_ITEM_SUBTYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOW_NAV_LVL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CHANGE_DOC_TYPE''
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
      ''PLND_CUST_ID''
      '' CtrlType=Lookup''
      ''  SqlId=SFPL_CUSTOMER_LOOKUP''
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
      ''COPY_COMPONENTS_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''FROM_VER''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_SUBTYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_ITEM_SUBTYPE_SEL''
      ''  ParamsSrc=@UDVUserFieldValues''
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
      ''PROJECT''
      '' CtrlType=Lookup''
      ''  SqlId=PlanProjectDefSel''
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
      ''FROM_PROGRAM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_DISPLAY_SEQUENCE''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ASSOCIATE_CHANGE''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_UOM''
      '' CtrlType=Lookup''
      ''  SqlId=SFPL_UOM_LOOKUP''
      ''  ParamsSrc=@UDVUserFieldValues''
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
      ''PLAN_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_MFI_FND_PLANTYPE''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   PLAN_TYPE=Plan Type''
      ''   INSTRUCTIONS_TYPE=Instructions Type''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''WORK_FLOW''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_PROCESSPLANWORKFLOWLOOKUP''
      ''  ParamsSrc=@UDVUserFieldValues''
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
      ''FROM_ITEM_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PL_PLAN_LOOKUP''
      ''  ParamsSrc=@UDVUserFieldValues''
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
      ''UCF_PLAN_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Curren';
p3 clob :='cy=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISPLAY_SEQUENCE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_DisplaySequenceLookUp''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''ITEM_TYPE''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PWP_ID''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_ChangeRequestPWPSel''
      ''  ParamsSrc=@ToolScope,@AuxParams''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   PWP_ID=Release Package''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UCF_PLAN_VCH13''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_VCH255_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NEW_ITEM_REV''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_part_chg_lookup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   New_Item_Rev=Item Rev (5)''
      ''   Item_Type=Item Type (6)''
      ''   Item_Subtype=Item Subtype (5)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_ITEM_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_MFI_PLG_PLANPARTLOOKUP''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   New_Item_no=Item No (12)''
      ''   New_Item_Rev=Item Rev(6)''
      ''   New_Item_Title=Item Title (12)''
      ''   Item_Type=Item Type (6)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UCF_PLAN_VCH3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TASK_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  Currency=N''
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
      ''  SqlId=PWUST_Pl_WorkLocSel_1''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   asgnd_work_loc=Work Location(20)''
      ''   loc_title=Location Title(24)''
      ''   COMPANY=Company''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''INSTRUCTIONS_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''INSP_PLAN_NO''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_SQA_MfgInspPlanLkup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   INSP_PLAN_NO=Insp Plan No''
      ''   INSP_PLAN_REV=Insp Plan Rev''
      ''   INSP_PLAN_TITLE=Insp Plan Title''
      ''   STATUS=Status''
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
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOCATION_ID''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_BOM_REV''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=MFI_PLG_PlanMbomLookup''
      
        ''  ParamsSrc=@AuxParams,@UDVUserFieldValues,@ToolScope,PWUST_SEL_'' +
        ''USER_WORK_LOC''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
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
      ''MODEL''
      '' CtrlType=Lookup''
      ''  Currency=N''
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
      ''UCF_PLAN_VCH4000_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''PL_Create_Plan''
    UpdateSqlId = ''PL_Create_Plan''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams,PWUST_SEL_USER_WORK_LOC''
    RefreshParams = False
    LinkedControls.Strings = (
      ''Model_Edit~MODEL''
      ''Sect_Edit~ENG_PART_NO''
      ''EngGroupEdit~ENG_GROUP''
      ''EngPartChg_Edit~ENG_PART_CHG''
      ''Customer~PLND_CUST_ID''
      ''_OBJ10~PROJECT''
      ''WorkLocEdit~ASGND_WORK_LOC''
      ''Plan_type~PLAN_TYPE''
      ''_OBJ12~FROM_PROGRAM''
      ''_OBJ15~FROM_VER''
      ''_OBJ16~FROM_REV''
      ''_OBJ17~FROM_ALT''
      ''SourcePlanNo~FROM_PLAN_NO''
      ''Plan_Name_Edit~TITLE''
      ''_OBJ2~FROM_ITEM_NO''
      ''_OBJ14~FROM_ITEM_TYPE''
      ''_OBJ11~FROM_ITEM_REV''
      ''Part_Chg~NEW_ITEM_REV''
      ''FromPlanType~FROM_PLAN_TYPE''
      ''INSTR_TYPE~INSTRUCTIONS_TYPE''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''FromItemSubtype~FROM_ITEM_SUBTYPE''
      ''PROGRAM~PROGRAM''
      ''_OBJ24~COPY_COMPONENTS_FLAG''
      ''Bom_no_edit~BOM_NO''
      ''_OBJ25~WORK_FLOW''
      ''insp_plan_no~INSP_PLAN_NO''
      ''insp_plan_id~INSP_PLAN_ID''
      ''Plan_ID_Edit~NEW_ITEM_NO''
      ''_OBJ21~ITEM_TYPE''
      ''bom_id~BOM_ID''
      ''Mbom_chg_edit~MFG_BOM_REV''
      ''_OBJ26~PPV_REQ_FLAG''
      ''_OBJ27~PPV_TYPE''
      ''_OBJ28~PPV_QTY''
      ''PWP_ID~PWP_ID''
      ''_OBJ29~ASSOCIATE_CHANGE''
      ''_OBJ30~ORDER_UOM''
      ''dispseq~DISPLAY_SEQUENCE''
      ''_OBJ31~UCF_PLAN_VCH255_2''
      ''Module~UCF_PLAN_VCH3'')
    RefreshedSQLSourceName = ''DispatchSqlSource''
    DataDefinitions.Strings = (
      ''MODEL=''
      ''TASK_ID=''
      ''PROGRAM=''
      ''PLND_CUST_ID=''
      ''LOW_NAV_LVL=''
      ''ENG_PART_NO=''
      ''ENG_PART_CHG=''
      ''ENG_GROUP=''
      ''CHANGE_DOC_TYPE=''
      ''PROJECT=''
      ''PLND_ORDER_QTY=''
      ''ASGND_WORK_LOC=''
      ''PLAN_TYPE=''
      ''COPY_COMPONENTS_FLAG=''
      ''FROM_PROGRAM=''
      ''FROM_VER=''
      ''FROM_REV=''
      ''FROM_ALT=''
      ''FROM_VER=''
      ''FROM_REV=''
      ''FROM_ALT=''
      ''UCF_PLAN_VCH1=''
      ''UCF_PLAN_VCH2=''
      ''UCF_PLAN_VCH3=''
      ''UCF_PLAN_VCH4=''
      ''UCF_PLAN_VCH5=''
      ''UCF_PLAN_VCH6=''
      ''UCF_PLAN_VCH7=''
      ''UCF_PLAN_VCH8=''
      ''UCF_PLAN_VCH9=''
      ''UCF_PLAN_VCH10=''
      ''UCF_PLAN_VCH11=''
      ''UCF_PLAN_VCH12=''
      ''UCF_PLAN_VCH13=''
      ''UCF_PLAN_VCH14=''
      ''UCF_PLAN_VCH15=''
      ''UCF_PLAN_NUM1=''
      ''UCF_PLAN_NUM2=''
      ''UCF_PLAN_NUM3=''
      ''UCF_PLAN_NUM4=''
      ''UCF_PLAN_NUM5=''
      ''UCF_PLAN_FLAG1=''
      ''UCF_PLAN_FLAG2=''
      ''UCF_PLAN_FLAG3=''
      ''UCF_PLAN_FLAG4=''
      ''UCF_PLAN_FLAG5=''
      ''UCF_PLAN_DATE1=''
      ''UCF_PLAN_DATE2=''
      ''UCF_PLAN_DATE3=''
      ''UCF_PLAN_DATE4=''
      ''UCF_PLAN_DATE5=''
      ''UCF_PLAN_VCH255_1=''
      ''UCF_PLAN_VCH255_2=''
      ''UCF_PLAN_VCH255_3=''
      ''UCF_PLAN_VCH4000_1=''
      ''UCF_PLAN_VCH4000_2=''
      ''FROM_PLAN_NO=''
      ''NEW_ITEM_NO=''
      ''TITLE=''
      ''FROM_ITEM_NO=''
      ''FROM_ITEM_TYPE=''
      ''NEW_ITEM_REV=''
      ''FROM_ITEM_REV=''
      ''FROM_PLAN_TYPE=''
      ''ITEM_TYPE=''
      ''ITEM_SUBTYPE=''
      ''FROM_ITEM_SUBTYPE=''
      ''DISPLAY_SEQUENCE=''
      ''MFG_BOM_REV=''
      ''BOM_NO=''
      ''WORK_FLOW=''
      ''FINAL_STORES=''
      ''BOM_ID=''
      ''INSP_PLAN_ID=''
      ''PWP_ID=''
      ''CHANGE_REQUEST_ID=''
      ''PLANNED_ACTION_ID=''
      ''PPV_REQ_FLAG=''
      ''PPV_TYPE=''
      ''ASSOCIATE_CHANGE=''
      ''PPV_QTY=''
      ''ORDER_UOM='')
  end
  object PWUST_SEL_USER_WORK_LOC: TsfTransactionParamSource
    SelectSqlId = ''PWUST_Pl_WorkLocSel''
    PublishParams = True
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

