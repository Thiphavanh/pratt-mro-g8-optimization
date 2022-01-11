
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

