
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_643EBD958FE20CD9E05387971F0A2CD9.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_643EBD958FE20CD9E05387971F0A2CD9';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperAdd';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_1003729/Defect 631;defect 760,1044, 1832, 1852,1863';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFCA';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 883
  Height = 660
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = '':@TransactionType Operation''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  IgnoreAuxParams = True
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
  object _OBJ5: TsfDBInputEdit
    Left = 146
    Top = 515
    Width = 478
    Height = 31
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
    Caption = ''Operation Title*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputEdit
    Left = 150
    Top = 32
    Width = 130
    Height = 23
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
    Caption = ''Operation No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ14: TsfDBInputEdit
    Left = 151
    Top = 118
    Width = 130
    Height = 23
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
    Caption = ''Machine No''
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
    Left = 452
    Top = 178
    Width = 130
    Height = 23
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
    Caption = ''Supplier Code''
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
    Left = 150
    Top = 205
    Width = 130
    Height = 23
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
    Caption = ''OSP Days''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ21: TsfDBInputEdit
    Left = 453
    Top = 214
    Width = 130
    Height = 23
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
    Caption = ''OSP Cost Per Unit''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ29: TsfDBInputEdit
    Left = 453
    Top = 32
    Width = 130
    Height = 23
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
    Caption = ''Operation Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ32: TsfDBInputEdit
    Left = 150
    Top = 6
    Width = 277
    Height = 23
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
    Caption = ''Order No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ33: TsfDBInputEdit
    Left = 776
    Top = 417
    Width = 29
    Height = 23
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
    Caption = ''Work Location''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ34: TsfDBInputEdit
    Left = 150
    Top = 60
    Width = 130
    Height = 23
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
    Caption = ''Work Dept*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ35: TsfDBInputEdit
    Left = 453
    Top = 60
    Width = 130
    Height = 23
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
    Caption = ''Work Center*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ36: TsfDBInputEdit
    Left = 456
    Top = 459
    Width = 130
    Height = 23
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
    Caption = ''SuperNet''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = True
    EnterForOkay = False
  end
  object _OBJ37: TsfDBInputEdit
    Left = 456
    Top = 487
    Width = 130
    Height = 23
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
    Caption = ''Sub Net''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ38: TsfDBInputEdit
    Left = 686
    Top = 377
    Width = 36
    Height = 23
    BorderColor = clBlack
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
    Caption = ''Order Oper NUM1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ39: TsfDBInputEdit
    Left = 681
    Top = 29
    Width = 54
    Height = 23
    BorderColor = clBlack
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
    Caption = ''Order Oper NUM2''
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
    Left = 687
    Top = 182
    Width = 26
    Height = 23
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
    Caption = ''Order Oper FLAG1''
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
    Left = 672
    Top = 427
    Width = 42
    Height = 23
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
    Caption = ''Order Oper DATE1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ45: TsfDBInputEdit
    Left = 453
    Top = 118
    Width = 130
    Height = 23
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
    Caption = ''Occur Rate''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_VCH1: TsfDBInputEdit
    Left = 687
    Top = 78
    Width = 42
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 18
    EntryType = etAlphaNumeric
    Caption = ''Plan Oper VCH1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_VCH2: TsfDBInputEdit
    Left = 694
    Top = 341
    Width = 27
    Height = 23
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
    Caption = ''Plan Oper VCH2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_VCH3: TsfDBInputEdit
    Left = 687
    Top = 104
    Width = 39
    Height = 23
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
    Caption = ''Plan Oper VCH3''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_VCH4: TsfDBInputEdit
    Left = 694
    Top = 228
    Width = 33
    Height = 23
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
    Caption = ''Plan Oper VCH4''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_VCH5: TsfDBInputEdit
    Left = 687
    Top = 130
    Width = 37
    Height = 23
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
    Caption = ''Plan Oper VCH5''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_NUM1: TsfDBInputEdit
    Left = 694
    Top = 254
    Width = 38
    Height = 23
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
    Caption = ''Plan Oper NUM1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_NUM2: TsfDBInputEdit
    Left = 687
    Top = 156
    Width = 38
    Height = 23
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
    Caption = ''Plan Oper NUM2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_FLAG1: TsfDBInputEdit
    Left = 694
    Top = 280
    Width = 37
    Height = 23
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
    Caption = ''Plan Oper FLAG1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PLAN_OPER_FLAG2: TsfDBInputEdit
    Left = 672
    Top = 404
    Width = 57
    Height = 23
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
    Caption = ''Plan Oper FLAG2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ46: TsfDBInputEdit
    Left = 150
    Top = 241
    Width = 130
    Height = 23
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
    Caption = ''Test Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ48: TsfDBInputEdit
    Left = 151
    Top = 274
    Width = 130
    Height = 23
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
    Caption = ''Change Significance''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ49: TsfInputCheckBox
    Left = 93
    Top = 147
    Width = 76
    Height = 20
    Caption = ''Auto Start?''
    ReturnIsTab = False
    TabOrder = 10
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
  object _OBJ50: TsfInputCheckBox
    Left = 42
    Top = 178
    Width = 129
    Height = 20
    Caption = ''Outside Process(OSP)?''
    ReturnIsTab = False
    TabOrder = 12
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
    Left = 346
    Top = 90
    Width = 119
    Height = 20
    Caption = ''Optional Operation?''
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
  object _OBJ52: TsfInputCheckBox
    Left = 367
    Top = 148
    Width = 99
    Height = 20
    Caption = ''Auto Complete?''
    ReturnIsTab = False
    TabOrder = 11
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
  object _OBJ56: TsfInputCheckBox
    Left = 307
    Top = 243
    Width = 159
    Height = 20
    Caption = ''Execute Steps in Sequence?''
    ReturnIsTab = False
    TabOrder = 33
    Version = ''1.4.0.3''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    Alignment = taRightJustify
    Hidden = False
    PersistManualValues = False
  end
  object EXE_ORDER: TsfDBInputEdit
    Left = 453
    Top = 294
    Width = 130
    Height = 23
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
    Caption = ''Oper Sequence No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ57: TsfDBInputEdit
    Left = 152
    Top = 308
    Width = 130
    Height = 23
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
    Caption = ''Orientation''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ58: TsfInputCheckBox
    Left = 382
    Top = 323
    Width = 85
    Height = 20
    Caption = ''Cross Order?''
    ReturnIsTab = False
    TabOrder = 36
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
  object _OBJ59: TsfInputCheckBox
    Left = 336
    Top = 266
    Width = 130
    Height = 20
    Caption = ''Must Use Issued Parts?''
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
  object _OBJ60: TsfDBInputEdit
    Left = 151
    Top = 88
    Width = 130
    Height = 23
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
    Caption = ''Unit Processing*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ61: TsfDBInputEdit
    Left = 150
    Top = 341
    Width = 130
    Height = 23
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
    Caption = ''Units Per Cycle''
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
    Left = 387
    Top = 350
    Width = 80
    Height = 20
    Caption = ''Auto Cycle?''
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
  object _OBJ63: TsfDBInputEdit
    Left = 522
    Top = 350
    Width = 59
    Height = 23
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
    Caption = ''Report;Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ64: TsfDBInputEdit
    Left = 150
    Top = 373
    Width = 130
    Height = 23
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
    Caption = ''Print Label''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ65: TsfDBInputEdit
    Left = 453
    Top = 379
    Width = 130
    Height = 23
    BorderColor = clBlack
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
    Caption = ''Number of Labels''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ66: TsfDBInputEdit
    Left = 454
    Top = 433
    Width = 130
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 44
    EntryType = etAlphaNumeric
    Caption = ''Reconcile Scrap''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ67: TsfDBInputEdit
    Left = 453
    Top = 407
    Width = 130
    Height = 23
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 45
    EntryType = etAlphaNumeric
    Caption = ''Label Id''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ68: TsfDBInputEdit
    Left = 685
    Top = 310
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
    TabOrder = 46
    EntryType = etAlphaNumeric
    Caption = ''Units Per Cycle Actual''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWin';
p2 clob :='dowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ69: TsfInputCheckBox
    Left = 531
    Top = 323
    Width = 53
    Height = 20
    Caption = ''Batch?''
    ReturnIsTab = False
    TabOrder = 47
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
  object _OBJ70: TsfDBInputEdit
    Left = 590
    Top = 31
    Width = 37
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
  object _OBJ71: TsfDBInputMemo
    Left = 149
    Top = 411
    Width = 202
    Height = 87
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    PopupMenu = UDVEditForm.UDVControlPopupMenu
    TabOrder = 49
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
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    FullHeight = 200
  end
  object _OBJ72: TsfLabel
    Left = 73
    Top = 411
    Width = 75
    Height = 17
    Caption = ''Technical Data''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object WidOperAltIns: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=False''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_KEY''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=22''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_OPT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=False''
      ''OPER_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''PLND_MACHINE_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''REWORK_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OSP_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''SUPPLIER_CODE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' Hidden=STDOPER_OBJECT_ID<>''#39#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''OSP_DAYS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=9''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OSP_COST_PER_UNIT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=9''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''AUTO_COMPLETE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' DefaultValue=N''
      '' Hidden=OPERATION_OVERLAP_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_NO''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ASGND_WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=40''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      
        '' DefaultValue=@GetParamSourceValue(WidOperAltIns2,''#39''PLND_ASGND_WO'' +
        ''RK_LOC''#39'')''
      '' PersistManualValues=Y''
      ''ASGND_WORK_DEPT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''ASGND_WORK_CENTER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''STEP_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ORDER_OPER_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=9''
      ''UCF_ORDER_OPER_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ORDER_OPER_DATE1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ORDER_OPER_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' MaxLength=9''
      '' PersistManualValues=Y''
      ''AUTO_START_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' DefaultValue=N''
      '' Hidden=OPERATION_OVERLAP_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''OCCUR_RATE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4''
      '' CharCase=N''
      '' DefaultValue=1''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PLND_ASGND_WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=9''
      ''UCF_PLAN_OPER_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=9''
      ''UCF_PLAN_OPER_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PLAN_OPER_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TEST_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=OPER_TYPE_ROLE<>''#39''Test''#39
      '' Required=False''
      '' PersistManualValues=Y''
      '' MaxLength=0''
      ''SAP_REF1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''AUTHORITY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''COMMENTS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ORDER_OPER_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=ORDER_TYPE=''#39''REPAIR''#39
      '' Required=False''
      ''UCF_ORDER_OPER_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH6''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH8''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH9''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH10''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH11''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH12''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH13''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH14''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH15''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_NUM3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_NUM4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_NUM5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_DATE2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_DATE3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_DATE4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_DATE5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_FLAG3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_FLAG4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_FLAG5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH255_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH255_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH255_3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH4000_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH4000_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH6''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH8''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH9''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH10''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH11''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH12''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH13''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH14''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH15''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_NUM3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_NUM4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_NUM5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_DATE1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_DATE2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_DATE3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_DATE4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_DATE5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_FLAG3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_FLAG4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_FLAG5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH255_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH255_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH255_3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH4000_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_OPER_VCH4000_2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SEQ_STEPS_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''OPER_CHANGE_LEVEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''EXE_ORDER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' DefaultValue=0''
      '' PersistManualValues=Y''
      '' Hidden=DISPLAY_SEQUENCE<>''#39''Execution Order''#39
      '' Required=DISPLAY_SEQUENCE<>''#39''Execution Order''#39
      '' MaxLength=9''
      ''ORIENTATION_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=12''
      '' CharCase=N''
      '' DefaultValue=Unit Centric''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MUST_ISSUE_PARTS_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UNIT_PROCESSING''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' DefaultValue=Normal''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''UNITS_PER_CYCLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=9''
      '' CharCase=N''
      '' DefaultValue=1''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_PROCESSING<>''#39''Cycle''#39
      '' Required=False''
      ''PRINT_LABEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=20''
      '' CharCase=N''
      '' DefaultValue=Off''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''REPORT_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''RECONCILE_SCRAP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=Off''
      '' PersistManualValues=Y''
      '' Hidden=OPERATION_OVERLAP_FLAG<>''#39''Y''#39
      '' Required=False''
      ''UNITS_PER_CYCLE_ACTUAL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=9''
      '' CharCase=N''
      '' DefaultValue=1''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_PROCESSING<>''#39''Cycle''#39
      '' Required=False''
      ''REPORT_ID_DISP''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''BATCH_FLAG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=STDOPER_OBJECT_ID=''#39#39
      '' Required=False''
      ''AUTO_CYCLE_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=N''
      '' DefaultValue=N''
      '' PersistManualValues=Y''
      '' Hidden=UNIT_PROCESSING<>''#39''Cycle''#39
      '' Required=False''
      ''NUMBER_OF_LABELS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' DefaultValue=1''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''OPER_TYPE_ROLE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''UCF_ORDER_OPER_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' Hidden=ORDER_TYPE=''#39''REPAIR''#39
      ''UCF_ORDER_OPER_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=ORDER_TYPE=''#39''REPAIR''#39
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''OPER_OPT_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''PLND_MACHINE_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_MACHINE_NO_LKUP''
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
      ''OPER_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=sfpl_oper_type_def''
      ''  ParamsSrc=WidOperTypeSel''
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
      ''REWORK_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''OSP_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''SUPPLIER_CODE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_SUPPLIER_LOOKUP''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SUPPLIER_CODE=Supplier Code(20)''
      ''   Supplier=Supplier Name(24)''
      ''   COMPANY=Company''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''OSP_DAYS''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=2''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OSP_COST_PER_UNIT''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=2''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AUTO_COMPLETE_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''OPER_NO''
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
      ''UCF_ORDER_OPER_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_FLAG1''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''UCF_ORDER_OPER_DATE1''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_NU';
p3 clob :='M2''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AUTO_START_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''OCCUR_RATE''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=2''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLND_ASGND_WORK_LOC''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_NUM2''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_FLAG1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_FLAG2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TEST_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=TestTypeLookup''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TEST_TYPE=''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''SAP_REF1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AUTHORITY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COMMENTS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH6''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH8''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH9''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH10''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH11''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH12''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH13''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH14''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH15''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_NUM3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_NUM4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_NUM5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_DATE2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_DATE3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_DATE4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_DATE5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_FLAG2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_FLAG3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_FLAG4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_FLAG5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH255_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH255_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH255_3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH4000_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH4000_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH6''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH8''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH9''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH10''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH11''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH12''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH13''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH14''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH15''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_NUM3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_NUM4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_NUM5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_DATE1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_DATE2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_DATE3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_DATE4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_DATE5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_FLAG3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_FLAG4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_FLAG5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH255_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH255_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH255_3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH4000_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH4000_2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SEQ_STEPS_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''OPER_CHANGE_LEVEL''
      '' CtrlType=List''
      ''  Values=Major~Minor''
      ''EXE_ORDER''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORIENTATION_FLAG''
      '' CtrlType=List''
      ''  Values=Unit Centric~Data Collection Centric''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MUST_ISSUE_PARTS_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UNIT_PROCESSING''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_MATERIALHANDLINGLOOKUP''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''UNITS_PER_CYCLE''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PRINT_LABEL''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_PrintLabelLookup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''REPORT_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''RECONCILE_SCRAP''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_RECONCILESCRAPLOOKUP''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''UNITS_PER_CYCLE_ACTUAL''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REPORT_ID_DISP''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_LabelReportLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   REPORT_ID_DISP=Report Id Disp''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''BATCH_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AUTO_CYCLE_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''NUMBER_OF_LABELS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_TYPE_ROLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ASGND_WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=widworkloc_lookup''
      ''  ParamsSrc=WidOperAltIns2''
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
      ''ASGND_WORK_DEPT''
      '' CtrlType=Lookup''
      ''  SqlId=widworkdept_lookup''
      ''  ParamsSrc=WidOperAltIns2''
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
      ''ASGND_WORK_CENTER''
      '' CtrlType=Lookup''
      ''  SqlId=widworkcenter_lookup''
      ''  ParamsSrc=WidOperAltIns2''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   asgnd_work_center=Work Center''
      ''   Center_Title=Work Center Title''
      ''   Unit_Processing=Unit Processing''
      ''   Orientation_Flag=Orientation Flag''
      ''   Cross_Order_Flag=Cross Order Flag''
      ''   Must_Issue_Parts_Flag=Must Issue Parts Flag''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''OPER_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PLAN_OPER_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH3''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_SEL_SUB_NET_ADD_OPER''
      ''  ParamsSrc=@toolscope''
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
      ''UCF_ORDER_OPER_VCH1''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_sel_sup_net_add_oper''
      ''  ParamsSrc=@toolscope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_OPER_VCH2''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_sel_sup_net_add_oper''
      ''  ParamsSrc=@toolscope''
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
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''pwust_MFI_WID_OrderOperationInsert''
    UpdateSqlId = ''pwust_OrderOperHdrAltUpd''
    ParamsSQLSourceName = ''@ToolScope,WidOperAltIns2,WidOperTypeSel''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ4~OPER_NO''
      ''_OBJ29~OPER_TYPE''
      ''_OBJ14~PLND_MACHINE_NO''
      ''_OBJ33~ASGND_WORK_LOC''
      ''_OBJ34~ASGND_WORK_DEPT''
      ''_OBJ35~ASGND_WORK_CENTER''
      ''_OBJ38~UCF_ORDER_OPER_NUM1''
      ''_OBJ39~UCF_ORDER_OPER_NUM2''
      ''_OBJ40~UCF_ORDER_OPER_FLAG1''
      ''_OBJ41~UCF_ORDER_OPER_DATE1''
      ''_OBJ17~SUPPLIER_CODE''
      ''_OBJ18~OSP_DAYS''
      ''_OBJ21~OSP_COST_PER_UNIT''
      ''_OBJ45~OCCUR_RATE''
      ''UCF_PLAN_OPER_VCH1~UCF_PLAN_OPER_VCH1''
      ''UCF_PLAN_OPER_VCH2~UCF_PLAN_OPER_VCH2''
      ''UCF_PLAN_OPER_VCH3~UCF_PLAN_OPER_VCH3''
      ''UCF_PLAN_OPER_VCH4~UCF_PLAN_OPER_VCH4''
      ''UCF_PLAN_OPER_VCH5~UCF_PLAN_OPER_VCH5''
      ''UCF_PLAN_OPER_NUM1~UCF_PLAN_OPER_NUM1''
      ''UCF_PLAN_OPER_NUM2~UCF_PLAN_OPER_NUM2''
      ''UCF_PLAN_OPER_FLAG1~UCF_PLAN_OPER_FLAG1''
      ''UCF_PLAN_OPER_FLAG2~UCF_PLAN_OPER_FLAG2''
      ''_OBJ46~TEST_TYPE''
      ''_OBJ5~OPER_TITLE''
      ''_OBJ48~OPER_CHANGE_LEVEL''
      ''_OBJ49~AUTO_START_FLAG''
      ''_OBJ50~OSP_FLAG''
      ''_OBJ51~OPER_OPT_FLAG''
      ''_OBJ52~AUTO_COMPLETE_FLAG''
      ''_OBJ56~SEQ_STEPS_FLAG''
      ''_OBJ57~ORIENTATION_FLAG''
      ''_OBJ58~CROSS_ORDER_FLAG''
      ''_OBJ59~MUST_ISSUE_PARTS_FLAG''
      ''_OBJ60~UNIT_PROCESSING''
      ''_OBJ61~UNITS_PER_CYCLE''
      ''_OBJ62~AUTO_CYCLE_FLAG''
      ''_OBJ63~REPORT_ID''
      ''_OBJ64~PRINT_LABEL''
      ''_OBJ65~NUMBER_OF_LABELS''
      ''_OBJ66~RECONCILE_SCRAP''
      ''_OBJ68~UNITS_PER_CYCLE_ACTUAL''
      ''_OBJ67~REPORT_ID_DISP''
      ''_OBJ69~BATCH_FLAG''
      ''EXE_ORDER~EXE_ORDER''
      ''_OBJ70~OPER_TYPE_ROLE''
      ''_OBJ32~ORDER_NO''
      ''_OBJ36~UCF_ORDER_OPER_VCH2''
      ''_OBJ37~UCF_ORDER_OPER_VCH3''
      ''_OBJ71~UCF_PLAN_OPER_VCH255_2'')
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''OPER_TYPE=''
      ''OPER_OPT_FLAG=''
      ''OPER_TITLE=''
      ''PLND_MACHINE_NO=''
      ''REWORK_FLAG=''
      ''OSP_FLAG=''
      ''SUPPLIER_CODE=''
      ''OSP_DAYS=''
      ''OSP_COST_PER_UNIT=''
      ''AUTO_COMPLETE_FLAG=''
      ''ASGND_WORK_LOC=''
      ''ASGND_WORK_LOC=''
      ''ASGND_WORK_CENTER=''
      ''OPER_KEY=''
      ''OPER_NO=''
      ''UCF_ORDER_OPER_NUM1=''
      ''UCF_ORDER_OPER_FLAG1=''
      ''UCF_ORDER_OPER_DATE1=''
      ''UCF_ORDER_OPER_NUM2=''
      ''AUTO_START_FLAG=''
      ''OCCUR_RATE=''
      ''AUTO_START_FLAG=''
      ''UCF_PLAN_OPER_VCH1=''
      ''UCF_PLAN_OPER_VCH2=''
      ''UCF_PLAN_OPER_VCH3=''
      ''UCF_PLAN_OPER_VCH4=''
      ''UCF_PLAN_OPER_VCH5=''
      ''UCF_PLAN_OPER_NUM1=''
      ''UCF_PLAN_OPER_NUM2=''
      ''UCF_PLAN_OPER_FLAG1=''
      ''UCF_PLAN_OPER_FLAG2=''
      ''TEST_TYPE=''
      ''UCF_PLAN_OPER_NUM2=''
      ''UCF_PLAN_OPER_FLAG1=''
      ''UCF_PLAN_OPER_FLAG2=''
      ''ASGND_WORK_DEPT=''
      ''STEP_KEY=''
      ''PLND_ASGND_WORK_LOC=''
      ''TEST_TYPE=''
      ''SAP_REF1=''
      ''AUTHORITY=''
      ''COMMENTS=''
      ''UCF_ORDER_OPER_VCH3=''
      ''UCF_ORDER_OPER_VCH4=''
      ''UCF_ORDER_OPER_VCH5=''
      ''UCF_ORDER_OPER_VCH6=''
      ''UCF_ORDER_OPER_VCH7=''
      ''UCF_ORDER_OPER_VCH8=''
      ''UCF_ORDER_OPER_VCH9=''
      ''UCF_ORDER_OPER_VCH10=''
      ''UCF_ORDER_OPER_VCH11=''
      ''UCF_ORDER_OPER_VCH12=''
      ''UCF_ORDER_OPER_VCH13=''
      ''UCF_ORDER_OPER_VCH14=''
      ''UCF_ORDER_OPER_VCH15=''
      ''UCF_ORDER_OPER_NUM3=''
      ''UCF_ORDER_OPER_NUM4=''
      ''UCF_ORDER_OPER_NUM5=''
      ''UCF_ORDER_OPER_DATE2=''
      ''UCF_ORDER_OPER_DATE3=''
      ''UCF_ORDER_OPER_DATE4=''
      ''UCF_ORDER_OPER_DATE5=''
      ''UCF_ORDER_OPER_FLAG2=''
      ''UCF_ORDER_OPER_FLAG3=''
      ''UCF_ORDER_OPER_FLAG4=''
      ''UCF_ORDER_OPER_FLAG5=''
      ''UCF_ORDER_OPER_VCH255_1=''
      ''UCF_ORDER_OPER_VCH255_2=''
      ''UCF_ORDER_OPER_VCH255_3=''
      ''UCF_ORDER_OPER_VCH4000_1=''
      ''UCF_ORDER_OPER_VCH4000_2=''
      ''UCF_PLAN_OPER_VCH6=''
      ''UCF_PLAN_OPER_VCH7=''
      ''UCF_PLAN_OPER_VCH8=''
      ''UCF_PLAN_OPER_VCH9=''
      ''UCF_PLAN_OPER_VCH10=''
      ''UCF_PLAN_OPER_VCH11=''
      ''UCF_PLAN_OPER_VCH12=''
      ''UCF_PLAN_OPER_VCH13=''
      ''UCF_PLAN_OPER_VCH14=''
      ''UCF_PLAN_OPER_VCH15=''
      ''UCF_PLAN_OPER_NUM3=''
      ''UCF_PLAN_OPER_NUM4=''
      ''UCF_PLAN_OPER_NUM5=''
      ''UCF_PLAN_OPER_DATE1=''
      ''UCF_PLAN_OPER_DATE2=''
      ''UCF_PLAN_OPER_DATE3=''
      ''UCF_PLAN_OPER_DATE4=''
      ''UCF_PLAN_OPER_DATE5=''
      ''UCF_PLAN_OPER_FLAG3=''
      ''UCF_PLAN_OPER_FLAG4=''
      ''UCF_PLAN_OPER_FLAG5=''
      ''UCF_PLAN_OPER_VCH255_1=''
      ''UCF_PLAN_OPER_VCH255_2=''
      ''UCF_PLAN_OPER_VCH255_3=''
      ''UCF_PLAN_OPER_VCH4000_1=''
      ''UCF_PLAN_OPER_VCH4000_2=''
      ''SEQ_STEPS_FLAG=''
      ''OPER_CHANGE_LEVEL=''
      ''EXE_ORDER=''
      ''ORIENTATION_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''MUST_ISSUE_PARTS_FLAG=''
      ''UNIT_PROCESSING=''
      ''UNITS_PER_CYCLE=''
      ''PRINT_LABEL=''
      ''REPORT_ID=''
      ''RECONCILE_SCRAP=''
      ''UNITS_PER_CYCLE_ACTUAL=''
      ''BATCH_FLAG=''
      ''AUTO_CYCLE_FLAG=''
      ''NUMBER_OF_LABELS=''
      ''UCF_ORDER_OPER_VCH1=''
      ''UCF_ORDER_OPER_VCH2='')
  end
  object WidOperAltIns2: TsfTransactionParamSource
    SelectSqlId = ''WidOperSel''
    ParamsSQLSourceName = ''@ToolScope,@ScriptParams''
    PublishParams = True
  end
  object WidOperTypeSel: TsfTransactionParamSource
    SelectSqlId = ''MFI_WID_OperTypeSel''
    ParamsSQLSourceName = ''WidOperAltIns2''
    PublishParams = True
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

