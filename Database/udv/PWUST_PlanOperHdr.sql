
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5746CB80DB5CB13FE05387971F0A7FB0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5746CB80DB5CB13FE05387971F0A7FB0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanOperHdr';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MF1_1000709, defect 381, defect 1713, defect 1832';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 675
  Height = 425
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctPanel
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
  object _OBJ2: TsfUDVNavigator
    Left = 4
    Top = 2
    Width = 20
    Height = 22
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ3: TsfDBEdit
    Left = 4
    Top = 41
    Width = 83
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Oper No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 92
    Top = 41
    Width = 261
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Oper Title''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 155
    Top = 105
    Width = 146
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Work Dept''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ6: TsfDBEdit
    Left = 305
    Top = 105
    Width = 162
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Work Center''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ17: TsfDBEdit
    Left = 92
    Top = 73
    Width = 84
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Auto Start?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ19: TsfDBEdit
    Left = 4
    Top = 73
    Width = 83
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Oper Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ20: TsfDBEdit
    Left = 181
    Top = 72
    Width = 84
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Auto Complete?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ21: TsfDBEdit
    Left = 23
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper VCH1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ22: TsfDBEdit
    Left = 71
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper VCH2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ23: TsfDBEdit
    Left = 135
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper VCH3''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ24: TsfDBEdit
    Left = 44
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper VCH4''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ25: TsfDBEdit
    Left = 95
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper VCH5''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ26: TsfDBEdit
    Left = 217
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper NUM1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ27: TsfDBEdit
    Left = 314
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper; FLAG2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ28: TsfDBEdit
    Left = 288
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper FLAG1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ29: TsfDBEdit
    Left = 187
    Top = 264
    Width = 21
    Height = 20
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Plan Oper NUM2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ30: TsfDBEdit
    Left = 4
    Top = 105
    Width = 146
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Work Location''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ37: TsfDBEdit
    Left = 4
    Top = 136
    Width = 146
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Execute Steps in Sequence?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object DisplayStdOper: TsfCommandButton
    Left = 30
    Top = 4
    Width = 150
    Height = 21
    Hint = ''Display Std Operation''
    Caption = ''Display Std Operation''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 19
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display Std Operation,Priv={},Visible=''#39''{STDOPER_OBJECT_ID<>'' +
        #39#39#39#39''  AND ITEM_SUBTYPE<>''#39#39''STDOPER''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc='' +
        ''@Default,Action=Invoke,Tool=StdOper,ReportToolId=,Params(''#39''PLAN_I'' +
        ''D = :STDOPER_PLAN_ID,''#39'',''#39''PLAN_VERSION = :STDOPER_PLAN_VER,''#39'',''#39''PLAN'' +
        ''_REVISION = :STDOPER_PLAN_REV,''#39'',''#39''PLAN_ALTERATIONS = :STDOPER_PLA'' +
        ''N_ALT,''#39'',''#39''@InvokeToolMode=EDITMODES.Edit_PL,''#39'',@InvokeTab=New)'')
  end
  object _OBJ43: TsfDBEdit
    Left = 362
    Top = 41
    Width = 104
    Height = 19
    EmptyTextStyle = []
    FocusColor = clBtnFace
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
    Caption = ''Optional Operation?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object SeqNum: TsfDBEdit
    Left = 305
    Top = 136
    Width = 90
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 21
    Text = ''''
    Caption = ''Oper Sequence No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ46: TsfDBEdit
    Left = 114
    Top = 230
    Width = 102
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 22
    Text = ''''
    Caption = ''Cross Order?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ47: TsfDBEdit
    Left = 161
    Top = 136
    Width = 120
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 23
    Text = ''''
    Caption = ''Must Use Issued Parts?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ53: TsfDBEdit
    Left = 226
    Top = 230
    Width = 102
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 24
    Text = ''''
    Caption = ''Reconcile Scrap''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ54: TsfDBEdit
    Left = 4
    Top = 230
    Width = 102
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 25
    Text = ''''
    Caption = ''Batch?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ56: TsfDBEdit
    Left = 4
    Top = 198
    Width = 146
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 26
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
  object _OBJ57: TsfDBEdit
    Left = 158
    Top = 198
    Width = 143
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 27
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
  object _OBJ58: TsfDBEdit
    Left = 273
    Top = 72
    Width = 194
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 28
    Text = ''''
    Caption = ''eMMP Reference''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ59: TsfDBEdit
    Left = 4
    Top = 167
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
    TabOrder = 29
    Text = ''''
    Caption = ''Unit Processing''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ60: TsfDBEdit
    Left = 161
    Top = 167
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
    TabOrder = 30
    Text = ''''
    Caption = ''Machine No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ61: TsfDBEdit
    Left = 305
    Top = 167
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
    TabOrder = 31
    Text = ''''
    Caption = ''Orientation''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ62: TsfDBMemo
    Left = 475
    Top = 41
    Width = 185
    Height = 146
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 32
    Zoom = 100
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object _OBJ63: TsfLabel
    Left = 477
    Top = 27
    Width = 95
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
  object PlanOperHdr: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PlanOperSel''
    LinkedControls.Strings = (
      ''_OBJ3~OPER_NO''
      ''_OBJ4~OPER_TITLE''
      ''_OBJ5~PLND_WORK_DEPT''
      ''_OBJ6~PLND_WORK_CENTER''
      ''_OBJ19~OPER_TYPE''
      ''_OBJ17~AUTO_START_FLAG''
      ''_OBJ20~AUTO_COMPLETE_FLAG''
      ''_OBJ21~UCF_PLAN_OPER_VCH1''
      ''_OBJ22~UCF_PLAN_OPER_VCH2''
      ''_OBJ23~UCF_PLAN_OPER_VCH3''
      ''_OBJ24~UCF_PLAN_OPER_VCH4''
      ''_OBJ25~UCF_PLAN_OPER_VCH5''
      ''_OBJ26~UCF_PLAN_OPER_NUM1''
      ''_OBJ29~UCF_PLAN_OPER_NUM2''
      ''_OBJ28~UCF_PLAN_OPER_FLAG1''
      ''_OBJ27~UCF_PLAN_OPER_FLAG2''
      ''_OBJ30~PLND_WORK_LOC''
      ''_OBJ37~seQ_STEPS_FLAG''
      ''_OBJ2~''
      ''DisplayStdOper~''
      ''_OBJ43~OPER_OPT_FLAG''
      ''_OBJ46~CROSS_ORDER_FLAG''
      ''_OBJ47~MUST_ISSUE_PARTS_FLAG''
      ''_OBJ53~RECONCILE_SCRAP''
      ''_OBJ54~BATCH_FLAG''
      ''_OBJ56~COMMODITY_JURISDICTION''
      ''_OBJ57~COMMODITY_CLASSIFICATION''
      ''_OBJ58~UCF_PLAN_OPER_VCH255_1''
      ''_OBJ59~UNIT_PROCESSING''
      ''_OBJ61~ORIENTATION_FLAG''
      ''_OBJ60~PLND_MACHINE_NO''
      ''_OBJ62~UCF_PLAN_OPER_VCH255_2'')
    ParamsSQLSourceName = ''@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_ID~40~PLAN_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_VERSION~10~PLAN_VERSION~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_REVISION~10~PLAN_REVISION~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''PLAN_ALTERATIONS~10~PLAN_ALTERATIONS~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''OPER_NO~10~OPER_NO~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_KEY~10~OPER_KEY~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_UPDT_NO~10~OPER_UPDT_NO~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_STATE~20~OPER_STATE~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_TITLE~80~OPER_TITLE~N~N~N~False~N~N~~~~~Default~N~~~''
      
        ''PLND_WORK_DEPT~30~PLND_WORK_DEPT~N~N~N~False~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''PLND_WORK_CENTER~30~PLND_WORK_CENTER~N~N~N~False~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''PLND_MACHINE_NO~30~PLND_MACHINE_NO~N~N~False~N~N~N~~~~~Default~N'' +
        ''~~~''
      
        ''SCHED_LABOR_HOURS_SETUP~10~SCHED_LABOR_HOURS_SETUP~N~N~N~N~N~N~~'' +
        ''~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_SETUP~10~SCHED_DUR_HOURS_SETUP~N~N~N~N~N~N~~~~~D'' +
        ''efault~N~~~''
      
        ''SCHED_LABOR_HOURS_INSPECT~10~SCHED_LABOR_HOURS_INSPECT~N~N~N~N~N'' +
        ''~N~~~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_INSPECT~10~SCHED_DUR_HOURS_INSPECT~N~N~N~N~N~N~~'' +
        ''~~~Default~N~~~''
      
        ''SCHED_LABOR_HOURS_PER_UNIT~10~SCHED_LABOR_HOURS_PER_UNIT~N~N~N~N'' +
        ''~N~N~~~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_PER_UNIT~10~SCHED_DUR_HOURS_PER_UNIT~N~N~N~N~N~N'' +
        ''~~~~~Default~N~~~''
      
        ''SCHED_CREW_QTY_SETUP~10~SCHED_CREW_QTY_SETUP~N~N~N~N~N~N~~~~~Def'' +
        ''ault~N~~~''
      ''SCHED_CREW_QTY~10~SCHED_CREW_QTY~N~N~N~N~N~N~~~~~Default~N~~~''
      ''REV_LOCK_STATE~20~REV_LOCK_STATE~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PART_NO~50~PART_NO~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PART_CHG~4~PART_CHG~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PROGRAM~30~PROGRAM~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ITEM_TYPE~5~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~15~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''LOW_NAV_LVL~4~LOW_NAV_LVL~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''PLND_WORK_PLACE_KEY~61~PLND_WORK_PLACE_KEY~N~N~N~N~N~N~~~~~Defau'' +
        ''lt~N~~~''
      ''OPER_TYPE~30~OPER_TYPE~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_OPT_FLAG~1~OPER_OPT_FLAG~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TIME_STAMP~18~TIME_STAMP~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLND_WORK_LOC~30~PLND_WORK_LOC~N~N~N~False~N~N~~~~~Default~N~~~''
      
        ''SCHED_MACHINE_HOURS_PER_UNIT~10~SCHED_MACHINE_HOURS_PER_UNIT~N~N'' +
        ''~N~N~N~N~~~~~Default~N~~~''
      
        ''SCHED_UNITS_PER_RUN~10~SCHED_UNITS_PER_RUN~N~N~N~N~N~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''SCHED_SETUP_TYPE~30~SCHED_SETUP_TYPE~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''SCHED_MACHINE_HOURS_SETUP~10~SCHED_MACHINE_HOURS_SETUP~N~N~N~N~N'' +
        ''~N~~~~~Default~N~~~''
      
        ''SCHED_ENG_STD_FLAG~1~SCHED_ENG_STD_FLAG~N~N~N~N~N~N~~~~~Default~'' +
        ''N~~~''
      ''OSP_FLAG~1~OSP_FLAG~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''SUPPLIER_CODE~40~SUPPLIER_CODE~N~N~True~N~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''AUTO_COMPLETE_FLAG~1~AUTO_COMPLETE_FLAG~N~N~N~N~N~N~~~~~Default~'' +
        ''N~~~''
      ''AUTO_START_FLAG~1~AUTO_START_FLAG~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OCCUR_RATE~10~OCCUR_RATE~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UPDT_USERID~30~UPDT_USERID~N~N~N~N~N~N~MFI_2869~~~~Default~N~~~''
      ''OSP_DAYS~10~OSP_DAYS~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''OSP_COST_PER_UNIT~10~OSP_COST_PER_UNIT~N~N~True~N~N~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''SCHED_MOVE_HOURS~10~SCHED_MOVE_HOURS~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''REV_STATUS~20~REV_STATUS~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH1~50~UCF_PLAN_OPER_VCH1~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_VCH2~50~UCF_PLAN_OPER_VCH2~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_VCH3~50~UCF_PLAN_OPER_VCH3~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_VCH4~50~UCF_PLAN_OPER_VCH4~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_VCH5~50~UCF_PLAN_OPER_VCH5~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_NUM1~10~UCF_PLAN_OPER_NUM1~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_NUM2~10~UCF_PLAN_OPER_NUM2~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''UCF_PLAN_OPER_FLAG1~1~UCF_PLAN_OPER_FLAG1~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_OPER_FLAG2~1~UCF_PLAN_OPER_FLAG2~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~~''
      ''TEST_TYPE~0~TEST_TYPE~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''SEQ_STEPS_FLAG~1~SEQ_STEPS_FLAG~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''CHANGE_AUTHORITY~0~CHANGE_AUTHORITY~N~N~STDOPER_OBJECT_ID<>''#39#39'' OR'' +
        '' RESULT<>''#39''Y''#39''~False~False~N~~~~~Default~N~~~''
      
        ''CHANGE_COMMENTS~0~CHANGE_COMMENTS~N~N~STDOPER_OBJECT_ID<>''#39#39'' OR R'' +
        ''ESULT<>''#39''Y''#39''~False~False~N~~~~~Default~N~~~''
      
        ''ORIENTATION_FLAG~18~Orientation~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''CROSS_ORDER_FLAG~3~Cross Order?~N~N~STDOPER_OBJECT_ID=''#39#39''~False~F'' +
        ''alse~N~~~~~Default~N~~~''
      
        ''MUST_ISSUE_PARTS_FLAG~8~Must Issue Parts?~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''REPORT_ID_DISP~0~REPORT_ID_DISP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''CROSS_ORDER_FLAG2~3~Cross Order?~N~N~STDOPER_OBJECT_ID<>''#39#39''~False'' +
        ''~False~N~~~~~Default~N~~~''
      
        ''BATCH_FLAG~0~Batch?~N~N~STDOPER_OBJECT_ID=''#39#39''~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''COMMODITY_JURISDICTION~50~COMMODITY_JURISDICTION~N~N~STDOPER_OBJ'' +
        ''ECT_ID=''#39#39''~False~False~N~~~~~Default~N~~~''
      
        ''COMMODITY_CLASSIFICATION~50~COMMODITY_CLASSIFICATION~N~N~STDOPER'' +
        ''_OBJECT_ID=''#39#39''~False~False~N~~~~~Default~N~~~''
      
        ''RECONCILE_SCRAP~0~RECONCILE_SCRAP~N~N~OPERATION_OVERLAP_FLAG<>''#39''Y'' +
        #39''~False~False~N~~~~~Default~N~~~''
      
        ''OPER_CHANGE_LEVEL~0~OPER_CHANGE_LEVEL~N~N~True~False~False~N~~~~'' +
        ''~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH255_1~255~eMMP_Reference~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH255_2~0~UCF_PLAN_OPER_VCH255_2~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''PLAN_PLND_WORK_LOC~Edit''
      ''REV_USERID~Edit''
      ''DESC_USERID~Edit''
      ''PLAN_VERSION~Edit''
      ''PLAN_REVISION~Edit''
      ''PLAN_ALTERATIONS~Edit''
      ''OPER_NO~Edit''
      ''OPER_KEY~Edit''
      ''CHG_AUTH_NUM~Edit''
      ''CHG_AUTH_NOTES~Edit''
      ''CHANGE_COMMENTS~Edit''
      ''CHANGE_AUTHORITY~Edit''
      ''UCF_PLAN_OPER_FLAG2~Edit''
      ''UCF_PLAN_OPER_NUM1~Edit''
      ''UCF_PLAN_OPER_VCH1~Edit''
    ';
p2 clob :='  ''REV_STATUS~Edit''
      ''SCHED_MOVE_HOURS~Edit''
      ''UPDT_USERID~Edit''
      ''AUTO_START_FLAG~Edit''
      ''AUTO_COMPLETE_FLAG~Edit''
      ''SCHED_ENG_STD_FLAG~Edit''
      ''SCHED_MACHINE_HOURS_SETUP~Edit''
      ''SCHED_SETUP_TYPE~Edit''
      ''SCHED_UNITS_PER_RUN~Edit''
      ''OPER_STATE~Edit''
      ''SCHED_LABOR_HOURS_SETUP~Edit''
      ''SCHED_DUR_HOURS_SETUP~Edit''
      ''SCHED_LABOR_HOURS_INSPECT~Edit''
      ''SCHED_DUR_HOURS_PER_UNIT~Edit''
      ''REV_LOCK_STATE~Edit''
      ''PART_NO~Edit''
      ''OPER_TYPE~Edit''
      ''ITEM_TYPE~Edit''
      ''PART_CHG~Edit''
      ''SCHED_LABOR_HOURS_PER_UNIT~Edit''
      ''SCHED_CREW_QTY~Edit''
      ''EXE_ORDER~Edit''
      ''ORIENTATION_FLAG~Edit''
      ''SCHED_CREW_QTY_SETUP~Edit''
      ''SCHED_DUR_HOURS_INSPECT~Edit''
      ''OCCUR_RATE~Edit''
      ''OSP_FLAG~Edit''
      ''OSP_DAYS~Edit''
      ''OSP_COST_PER_UNIT~Edit''
      ''COMMODITY_JURISDICTION~Edit''
      ''OPER_TITLE~Edit''
      ''PLND_WORK_DEPT~Edit''
      ''PLND_WORK_CENTER~Edit''
      ''PLND_MACHINE_NO~Edit''
      ''PLAN_ID~Edit'')
    TestParamValues.Strings = (
      ''plan_id=MFI_11''
      ''plan_version=1''
      ''plan_revision=1''
      ''plan_alterations=0''
      ''oper_key=1''
      ''scope_oper_no=10''
      ''SCOPE_PLAN_ID=MFI_5BD352F9C975C370D1CAEF7715766B77''
      ''SCOPE_PLAN_UPDT_NO=1'')
    InputUDVId = ''MFI_7601B92E1A570672E04400144FA7B7D2''
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''OPER_NO''
      ''OPER_KEY''
      ''OPER_UPDT_NO''
      ''OPER_STATE''
      ''OPER_TITLE''
      ''PLND_WORK_DEPT''
      ''PLND_WORK_CENTER''
      ''PLND_MACHINE_NO''
      ''SCHED_LABOR_HOURS_SETUP''
      ''SCHED_DUR_HOURS_SETUP''
      ''SCHED_LABOR_HOURS_INSPECT''
      ''SCHED_DUR_HOURS_INSPECT''
      ''SCHED_LABOR_HOURS_PER_UNIT''
      ''SCHED_DUR_HOURS_PER_UNIT''
      ''SCHED_CREW_QTY_SETUP''
      ''SCHED_CREW_QTY''
      ''REV_LOCK_STATE''
      ''PART_NO''
      ''PART_CHG''
      ''PROGRAM''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''LOW_NAV_LVL''
      ''PLND_WORK_PLACE_KEY''
      ''OPER_TYPE''
      ''OPER_OPT_FLAG''
      ''TIME_STAMP''
      ''PLND_WORK_LOC''
      ''SCHED_MACHINE_HOURS_PER_UNIT''
      ''SCHED_UNITS_PER_RUN''
      ''SCHED_SETUP_TYPE''
      ''SCHED_MACHINE_HOURS_SETUP''
      ''SCHED_ENG_STD_FLAG''
      ''OSP_FLAG''
      ''SUPPLIER_CODE''
      ''AUTO_COMPLETE_FLAG''
      ''AUTO_START_FLAG''
      ''OCCUR_RATE''
      ''UPDT_USERID''
      ''OSP_DAYS''
      ''OSP_COST_PER_UNIT''
      ''SCHED_MOVE_HOURS''
      ''REV_STATUS''
      ''UCF_PLAN_OPER_VCH1''
      ''UCF_PLAN_OPER_VCH2''
      ''UCF_PLAN_OPER_VCH3''
      ''UCF_PLAN_OPER_VCH4''
      ''UCF_PLAN_OPER_VCH5''
      ''UCF_PLAN_OPER_NUM1''
      ''UCF_PLAN_OPER_NUM2''
      ''UCF_PLAN_OPER_FLAG1''
      ''UCF_PLAN_OPER_FLAG2''
      ''TEST_TYPE''
      ''SEQ_STEPS_FLAG''
      ''CHANGE_AUTHORITY''
      ''CHANGE_COMMENTS''
      ''ORIENTATION_FLAG''
      ''CROSS_ORDER_FLAG''
      ''MUST_ISSUE_PARTS_FLAG''
      ''REPORT_ID_DISP''
      ''CROSS_ORDER_FLAG2''
      ''BATCH_FLAG''
      ''COMMODITY_JURISDICTION''
      ''COMMODITY_CLASSIFICATION''
      ''RECONCILE_SCRAP''
      ''OPER_CHANGE_LEVEL''
      ''UCF_PLAN_OPER_VCH255_1'')
    ConsolidatedQuery = False
  end
  object PlanOperSeq: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_FND_PlanOperSeqNoSel''
    LinkedControls.Strings = (
      ''SeqNum~EXE_ORDER'')
    ParamsSQLSourceName = ''@CurrentMarker,PlanOperHdr''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''EXE_ORDER~0~EXE_ORDER~N~N~DISPLAY_SEQUENCE<>''#39''Execution Order''#39''~Fa'' +
        ''lse~DISPLAY_SEQUENCE<>''#39''Execution Order''#39''~N~~~~~Default~N~'')
    TestParamValues.Strings = (
      ''SCOPE_PLAN_ID=MFI_FFBCE87DA7C221E785CF401C6A470A7''
      ''SCOPE_OPER_KEY=1295470''
      ''SCOPE_OPER_UPDT_NO=1'')
    DataDefinitions.Strings = (
      ''EXE_ORDER'')
    ConsolidatedQuery = False
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

