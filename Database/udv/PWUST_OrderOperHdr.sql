
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_643EEC13C4DF1E28E05387971F0A954B.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_643EEC13C4DF1E28E05387971F0A954B';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperHdr';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_1002406; defect 1832';
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
  Height = 480
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
    Left = 6
    Top = 4
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
    Top = 40
    Width = 109
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
    Left = 117
    Top = 40
    Width = 333
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
    Left = 154
    Top = 123
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
    TabOrder = 8
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
    Left = 304
    Top = 123
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
    Caption = ''Work Center''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ7: TsfDBEdit
    Left = 73
    Top = 450
    Width = 109
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
    Caption = ''Machine No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ18: TsfDBEdit
    Left = 119
    Top = 74
    Width = 108
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
    Caption = ''Optional Operation?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ19: TsfDBEdit
    Left = 43
    Top = 451
    Width = 108
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
    Caption = ''Occur Rate''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ20: TsfDBEdit
    Left = 4
    Top = 74
    Width = 109
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
  object _OBJ21: TsfDBEdit
    Left = 4
    Top = 123
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
    Caption = ''Work location''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ25: TsfDBEdit
    Left = 141
    Top = 447
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
    TabOrder = 10
    Text = ''''
    Caption = ''Test Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ26: TsfDBEdit
    Left = 156
    Top = 171
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
    TabOrder = 11
    Text = ''''
    Caption = ''Outside Process (OSP)?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ27: TsfDBEdit
    Left = 182
    Top = 445
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
    TabOrder = 12
    Text = ''''
    Caption = ''Supplier Code''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ28: TsfDBEdit
    Left = 195
    Top = 444
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
    TabOrder = 13
    Text = ''''
    Caption = ''OSP Days''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ29: TsfDBEdit
    Left = 220
    Top = 443
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
    TabOrder = 14
    Text = ''''
    Caption = ''OSP Cost Per Unit''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ30: TsfDBEdit
    Left = 306
    Top = 171
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
    TabOrder = 15
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
    Left = 35
    Top = 4
    Width = 120
    Height = 21
    Hint = ''Display Std Oper''
    Caption = ''Display Std Operation''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 16
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display Std Oper,Priv={},Visible=''#39''{STDOPER_OBJECT_ID<>''#39#39#39#39''}'' +
        #39'',TagValue=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=StdOper,'' +
        ''ReportToolId=,Params(''#39''PLAN_ID = :STDOPER_PLAN_ID,''#39'',''#39''PLAN_VERSION'' +
        '' = :STDOPER_PLAN_VER,''#39'',''#39''PLAN_REVISION = :STDOPER_PLAN_REV,''#39'',PLAN'' +
        ''_ALTERATIONS = :STDOPER_PLAN_ALT,@InvokeTab=New)'')
  end
  object _OBJ31: TsfDBEdit
    Left = 4
    Top = 171
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
    TabOrder = 17
    Text = ''''
    Caption = ''Change Significance''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ33: TsfDBEdit
    Left = 278
    Top = 441
    Width = 146
    Height = 19
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
    Caption = ''Unit Processing''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ34: TsfDBEdit
    Left = 395
    Top = 439
    Width = 70
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 19
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
  object _OBJ35: TsfDBEdit
    Left = 253
    Top = 443
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
    TabOrder = 20
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
  object _OBJ36: TsfDBEdit
    Left = 4
    Top = 217
    Width = 149
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
    Caption = ''Must Use Issued Parts?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ37: TsfDBEdit
    Left = 160
    Top = 217
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
    TabOrder = 22
    Text = ''''
    Caption = ''Percentage Complete''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ38: TsfDBEdit
    Left = 4
    Top = 272
    Width = 296
    Height = 52
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
    Caption = ''Completion Comments''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object SequenceNumber: TsfDBEdit
    Left = 159
    Top = 350
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
    TabOrder = 24
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
  object _OBJ39: TsfDBEdit
    Left = 372
    Top = 436
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
    TabOrder = 25
    Text = ''''
    Caption = ''Units Per Cycle''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ40: TsfDBEdit
    Left = 358
    Top = 442
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
    Caption = ''Auto Cycle?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ41: TsfDBEdit
    Left = 382
    Top = 439
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
    TabOrder = 27
    Text = ''''
    Caption = ''Print Label''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ42: TsfDBEdit
    Left = 357
    Top = 452
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
    TabOrder = 28
    Text = ''''
    Caption = ''Number of Labels''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ43: TsfDBEdit
    Left = 60
    Top = 459
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
    TabOrder = 29
    Text = ''''
    Caption = ''Label Id''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ44: TsfDBEdit
    Left = 143
    Top = 455
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
    TabOrder = 30
    Text = ''''
    Caption = ''Units Per Cycle Actual''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ45: TsfDBEdit
    Left = 4
    Top = 350
    Width = 149
    Height = 36
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
    Caption = ''Reconcile Scrap''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ46: TsfDBEdit
    Left = 370
    Top = 443
    Width = 70
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 32
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
  object _OBJ47: TsfDBEdit
    Left = 306
    Top = 448
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
    TabOrder = 33
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
  object _OBJ48: TsfDBEdit
    Left = 306
    Top = 277
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
    TabOrder = 34
    Text = ''''
    Caption = ''Commodity Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ49: TsfDBEdit
    Left = 306
    Top = 315
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
    TabOrder = 35
    Text = ''''
    Caption = ''Commodity Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ50: TsfDBEdit
    Left = 460
    Top = 40
    Width = 176
    Height = 150
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 36
    Text = ''''
    Caption = ''Technical Data''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object OrderOperHdr: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''WidOperSel''
    LinkedControls.Strings = (
      ''_OBJ3~OPER_NO''
      ''_OBJ6~ASGND_WORK_CENTER''
      ''_OBJ7~PLND_MACHINE_NO''
      ''_OBJ4~OPER_TITLE''
      ''_OBJ20~OPER_TYPE''
      ''_OBJ18~OPER_OPT_FLAG''
      ''_OBJ21~ASGND_WORK_LOC''
      ''_OBJ5~ASGND_WORK_DEPT''
      ''_OBJ19~OCCUR_RATE''
      ''_OBJ25~TEST_TYPE''
      ''_OBJ26~OSP_FLAG''
      ''_OBJ27~SUPPLIER_CODE''
      ''_OBJ28~OSP_DAYS''
      ''_OBJ29~OSP_COST_PER_UNIT''
      ''_OBJ30~SEQ_STEPS_FLAG''
      ''_OBJ31~OPER_CHANGE_LEVEL''
      ''_OBJ34~CROSS_ORDER_FLAG''
      ''_OBJ35~ORIENTATION_FLAG''
      ''_OBJ36~MUST_ISSUE_PARTS_FLAG''
      ''_OBJ37~PERCENT_COMPLETE''
      ''_OBJ38~PERCENT_COMPLETE_COMMENTS''
      ''SequenceNumber~EXE_ORDER''
      ''_OBJ39~UNITS_PER_CYCLE''
      ''_OBJ40~AUTO_CYCLE_FLAG''
      ''_OBJ41~PRINT_LABEL''
      ''_OBJ44~UNITS_PER_CYCLE_ACTUAL''
      ''_OBJ45~RECONCILE_SCRAP''
      ''_OBJ43~REPORT_ID_DISP''
      ''_OBJ47~CROSS_ORDER_FLAG2''
      ''_OBJ46~BATCH_FLAG''
      ''_OBJ42~NUMBER_OF_LABELS''
      ''_OBJ33~UNIT_PROCESSING''
      ''_OBJ2~''
      ''_OBJ50~UCF_PLAN_OPER_VCH255_2'')
    ParamsSQLSourceName = ''@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_ID~0~ORDER_ID~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ORDER_NO~0~ORDER_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ITEM_TYPE~0~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      ''PART_CHG~0~PART_CHG~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PROGRAM~0~PROGRAM~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ORDER_STATUS~0~ORDER_STATUS~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''ORDER_HOLD_STATUS~0~ORDER_HOLD_STATUS~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''ASGND_WORK_LOC~0~ASGND_WORK_LOC~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''ORDER_CUST_ID~0~ORDER_CUST_ID~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      ''ORDER_QTY~0~ORDER_QTY~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SCHED_PRIORITY~0~SCHED_PRIORITY~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''ORIG_ORDER_ID~0~ORIG_ORDER_ID~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''PARENT_ORDER_ID~0~PARENT_ORDER_ID~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''SUPERCEDED_ORDER_ID~0~SUPERCEDED_ORDER_ID~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      ''OPER_NO~0~OPER_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~0~UPDT_USERID~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''TIME_STAMP~0~TIME_STAMP~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''LAST_ACTION~0~LAST_ACTION~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PLAN_UPDT_NO~0~PLAN_UPDT_NO~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      ''OPER_KEY~0~OPER_KEY~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''OPER_UPDT_NO~0~OPER_UPDT_NO~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''OPER_STATUS~0~OPER_STATUS~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''OPER_HOLD_STATUS~0~OPER_HOLD_STATUS~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''ASGND_WORK_DEPT~0~ASGND_WORK_DEPT~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''ASGND_WORK_CENTER~0~ASGND_WORK_CENTER~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''SCHED_START_DATE~0~SCHED_START_DATE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''SCHED_END_DATE~0~SCHED_END_DATE~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''REVISED_START_DATE~0~REVISED_START_DATE~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''REVISED_END_DATE~0~REVISED_END_DATE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''ACTUAL_START_DATE~0~ACTUAL_START_DATE~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''ACTUAL_END_DATE~0~ACTUAL_END_DATE~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''REVISED_CREW_QTY_SETUP~0~REVISED_CREW_QTY_SETUP~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''
      
        ''REVISED_CREW_QTY~0~REVISED_CREW_QTY~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''ACTUAL_CREW_QTY_SETUP~0~ACTUAL_CREW_QTY_SETUP~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~~~''
      
        ''ACTUAL_CREW_QTY~0~ACTUAL_CREW_QTY~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      ''OPER_TITLE~0~OPER_TITLE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PLND_MACHINE_NO~0~PLND_MACHINE_NO~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''SCHED_LABOR_HOURS_SETUP~0~SCHED_LABOR_HOURS_SETUP~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_SETUP~0~SCHED_DUR_HOURS_SETUP~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~~~''
      
        ''SCHED_LABOR_HOURS_INSPECT~0~SCHED_LABOR_HOURS_INSPECT~N~N~False~'' +
        ''False~False~N~~~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_INSPECT~0~SCHED_DUR_HOURS_INSPECT~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''SCHED_LABOR_HOURS_PER_UNIT~0~SCHED_LABOR_HOURS_PER_UNIT~N~N~Fals'' +
        ''e~False~False~N~~~~~Default~N~~~''
      
        ''SCHED_DUR_HOURS_PER_UNIT~0~SCHED_DUR_HOURS_PER_UNIT~N~N~False~Fa'' +
        ''lse~False~N~~~~~Default~N~~~''
      
        ''SCHED_CREW_QTY_SETUP~0~SCHED_CREW_QTY_SETUP~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''SCHED_CREW_QTY~0~SCHED_CREW_QTY~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''LOW_NAV_LVL~0~LOW_NAV_LVL~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''ASGND_WORK_PLACE_KEY~0~ASGND_WORK_PLACE_KEY~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      ''OPER_TYPE~0~OPER_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ALT_ID~0~ALT_ID~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ALT_COUNT~0~ALT_COUNT~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SCHED_MACHINE_HOURS_PER_UNIT~0~SCHED_MACHINE_HOURS_PER_UNIT~N~N~'' +
        ''False~False~False~N~~~~~Default~N~~~''
      
        ''SCHED_U';
p2 clob :='NITS_PER_RUN~0~SCHED_UNITS_PER_RUN~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''SCHED_SETUP_TYPE~0~SCHED_SETUP_TYPE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''SCHED_MACHINE_HOURS_SETUP~0~SCHED_MACHINE_HOURS_SETUP~N~N~False~'' +
        ''False~False~N~~~~~Default~N~~~''
      
        ''SCHED_ENG_STD_FLAG~0~SCHED_ENG_STD_FLAG~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      ''OSP_FLAG~0~OSP_FLAG~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUPPLIER_CODE~0~SUPPLIER_CODE~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''ALTER_TYPE~0~ALTER_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''STATUS_CHG_NOTES~0~STATUS_CHG_NOTES~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      ''OCCUR_RATE~0~OCCUR_RATE~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''REWORK_FLAG~0~REWORK_FLAG~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''OSP_DAYS~0~OSP_DAYS~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''OSP_COST_PER_UNIT~0~OSP_COST_PER_UNIT~N~N~True~False~False~N~~~~'' +
        ''~Default~N~~~''
      
        ''AUTO_COMPLETE_FLAG~0~AUTO_COMPLETE_FLAG~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''SCHED_MOVE_HOURS~0~SCHED_MOVE_HOURS~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''PLND_ASGND_WORK_LOC~0~PLND_ASGND_WORK_LOC~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''OPER_OPT_FLAG~0~OPER_OPT_FLAG~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''AUTO_START_FLAG~0~AUTO_START_FLAG~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_PLAN_VCH1~0~UCF_PLAN_VCH1~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH2~0~UCF_PLAN_VCH2~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH3~0~UCF_PLAN_VCH3~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH4~0~UCF_PLAN_VCH4~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH5~0~UCF_PLAN_VCH5~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH6~0~UCF_PLAN_VCH6~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH7~0~UCF_PLAN_VCH7~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH8~0~UCF_PLAN_VCH8~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_NUM1~0~UCF_PLAN_NUM1~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_NUM2~0~UCF_PLAN_NUM2~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_FLAG1~0~UCF_PLAN_FLAG1~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_FLAG2~0~UCF_PLAN_FLAG2~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH1~0~UCF_ORDER_VCH1~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH2~0~UCF_ORDER_VCH2~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH3~0~UCF_ORDER_VCH3~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH4~0~UCF_ORDER_VCH4~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH5~0~UCF_ORDER_VCH5~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_NUM1~0~UCF_ORDER_NUM1~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_NUM2~0~UCF_ORDER_NUM2~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_FLAG1~0~UCF_ORDER_FLAG1~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_PLAN_OPER_VCH1~0~UCF_PLAN_OPER_VCH1~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH2~0~UCF_PLAN_OPER_VCH2~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH3~0~UCF_PLAN_OPER_VCH3~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH4~0~UCF_PLAN_OPER_VCH4~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH5~0~UCF_PLAN_OPER_VCH5~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_NUM1~0~UCF_PLAN_OPER_NUM1~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_NUM2~0~UCF_PLAN_OPER_NUM2~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_FLAG1~0~UCF_PLAN_OPER_FLAG1~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_FLAG2~0~UCF_PLAN_OPER_FLAG2~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH1~0~UCF_ORDER_OPER_VCH1~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH2~0~UCF_ORDER_OPER_VCH2~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_NUM1~0~UCF_ORDER_OPER_NUM1~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_NUM2~0~UCF_ORDER_OPER_NUM2~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_FLAG1~0~UCF_ORDER_OPER_FLAG1~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_DATE1~0~UCF_ORDER_OPER_DATE1~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      ''TEST_TYPE~0~TEST_TYPE~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''EXTERNAL_PLM_NO~0~EXTERNAL_PLM_NO~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''EXTERNAL_ERP_NO~0~EXTERNAL_ERP_NO~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_PLAN_VCH9~0~UCF_PLAN_VCH9~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_VCH10~0~UCF_PLAN_VCH10~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH11~0~UCF_PLAN_VCH11~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH12~0~UCF_PLAN_VCH12~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH13~0~UCF_PLAN_VCH13~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH14~0~UCF_PLAN_VCH14~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH15~0~UCF_PLAN_VCH15~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_NUM3~0~UCF_PLAN_NUM3~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_NUM4~0~UCF_PLAN_NUM4~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_NUM5~0~UCF_PLAN_NUM5~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_PLAN_DATE1~0~UCF_PLAN_DATE1~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_DATE2~0~UCF_PLAN_DATE2~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_DATE3~0~UCF_PLAN_DATE3~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_DATE4~0~UCF_PLAN_DATE4~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_DATE5~0~UCF_PLAN_DATE5~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_FLAG3~0~UCF_PLAN_FLAG3~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_FLAG4~0~UCF_PLAN_FLAG4~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_FLAG5~0~UCF_PLAN_FLAG5~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH255_1~0~UCF_PLAN_VCH255_1~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''UCF_PLAN_VCH255_2~0~UCF_PLAN_VCH255_2~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''UCF_PLAN_VCH255_3~0~UCF_PLAN_VCH255_3~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''UCF_PLAN_VCH4000_1~0~UCF_PLAN_VCH4000_1~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_VCH4000_2~0~UCF_PLAN_VCH4000_2~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH6~0~UCF_ORDER_VCH6~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH7~0~UCF_ORDER_VCH7~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH8~0~UCF_ORDER_VCH8~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH9~0~UCF_ORDER_VCH9~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_VCH10~0~UCF_ORDER_VCH10~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH11~0~UCF_ORDER_VCH11~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH12~0~UCF_ORDER_VCH12~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH13~0~UCF_ORDER_VCH13~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH14~0~UCF_ORDER_VCH14~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH15~0~UCF_ORDER_VCH15~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_NUM3~0~UCF_ORDER_NUM3~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_NUM4~0~UCF_ORDER_NUM4~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_NUM5~0~UCF_ORDER_NUM5~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_ORDER_DATE1~0~UCF_ORDER_DATE1~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_DATE2~0~UCF_ORDER_DATE2~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_DATE3~0~UCF_ORDER_DATE3~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_DATE4~0~UCF_ORDER_DATE4~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_DATE5~0~UCF_ORDER_DATE5~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_FLAG2~0~UCF_ORDER_FLAG2~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_FLAG3~0~UCF_ORDER_FLAG3~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_FLAG4~0~UCF_ORDER_FLAG4~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_FLAG5~0~UCF_ORDER_FLAG5~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''UCF_ORDER_VCH255_1~0~UCF_ORDER_VCH255_1~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH255_2~0~UCF_ORDER_VCH255_2~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH255_3~0~UCF_ORDER_VCH255_3~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH4000_1~0~UCF_ORDER_VCH4000_1~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH4000_2~0~UCF_ORDER_VCH4000_2~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''SEQ_STEPS_FLAG~0~SEQ_STEPS_FLAG~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_OPER_VCH6~0~UCF_PLAN_OPER_VCH6~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH7~0~UCF_PLAN_OPER_VCH7~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH8~0~UCF_PLAN_OPER_VCH8~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH9~0~UCF_PLAN_OPER_VCH9~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH10~0~UCF_PLAN_OPER_VCH10~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH11~0~UCF_PLAN_OPER_VCH11~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH12~0~UCF_PLAN_OPER_VCH12~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH13~0~UCF_PLAN_OPER_VCH13~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH14~0~UCF_PLAN_OPER_VCH14~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH15~0~UCF_PLAN_OPER_VCH15~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_NUM3~0~UCF_PLAN_OPER_NUM3~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_NUM4~0~UCF_PLAN_OPER_NUM4~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_NUM5~0~UCF_PLAN_OPER_NUM5~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_DATE1~0~UCF_PLAN_OPER_DATE1~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_DATE2~0~UCF_PLAN_OPER_DATE2~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_DATE3~0~UCF_PLAN_OPER_DATE3~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_DATE4~0~UCF_PLAN_OPER_DATE4~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_DATE5~0~UCF_PLAN_OPER_DATE5~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_FLAG3~0~UCF_PLAN_OPER_FLAG3~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_FLAG4~0~UCF_PLAN_OPER_FLAG4~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_FLAG5~0~UCF_PLAN_OPER_FLAG5~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH255_1~0~UCF_PLAN_OPER_VCH255_1~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH255_2~0~UCF_PLAN_OPER_VCH255_2~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH255_3~0~UCF_PLAN_OPER_VCH255_3~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH4000_1~0~UCF_PLAN_OPER_VCH4000_1~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_OPER_VCH4000_2~0~UCF_PLAN_OPER_VCH4000_2~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH3~0~UCF_ORDER_OPER_VCH3~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH4~0~UCF_ORDER_OPER_VCH4~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH5~0~UCF_ORDER_OPER_VCH5~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH6~0~UCF_ORDER_OPER_VCH6~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH7~0~UCF_ORDER_OPER_VCH7~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH8~0~UCF_ORDER_OPER_VCH8~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH9~0~UCF_ORDER_OPER_VCH9~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH10~0~UCF_ORDER_OPER_VCH10~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH11~0~UCF_ORDER_OPER_VCH11~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH12~0~UCF_ORDER_OPER_VCH12~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH13~0~UCF_ORDER_OPER_VCH13~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH14~0~UCF_ORDER_OPER_VCH14~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH15~0~UCF_ORDER_OPER_VCH15~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_NUM3~0~UCF_ORDER_OPER_NUM3~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_NUM4~0~UCF_ORDER_OPER_NUM4~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_NUM5~0~UCF_ORDER_OPER_NUM5~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_DATE2~0~UCF_ORDER_OPER_DATE2~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_DATE3~0~UCF_ORDER_OPER_DATE3~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_DATE4~0~UCF_ORDER_OPER_DATE4~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_DATE5~0~UCF_ORDER_OPER_DATE5~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_FLAG2~0~UCF_ORDER_OPER_FLAG2~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_FLAG3~0~UCF_ORDER_OPER_FLAG3~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_FLAG4~0~UCF_ORDER_OPER_FLAG4~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_FLAG5~0~UCF_ORDER_OPER_FLAG5~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH255_1~0~UCF_ORDER_OPER_VCH255_1~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH255_2~0~UCF_ORDER_OPER_VCH255_2~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH255_3~0~UCF_ORDER_OPER_VCH255_3~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH4000_1~0~UCF_ORDER_OPER_VCH4000_1~N~N~False~Fa'' +
        ''lse~False~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH4000_2~0~UCF_ORDER_OPER_VCH4000_2~N~N~False~Fa'' +
        ''lse~False~N~~~~~Default~N~~~''
      
        ''OPER_CHANGE_LEVEL~0~OPER_CHANGE_LEVEL~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''EXE_ORDER~0~EXE_ORDER~N~N~DISPLAY_SEQUENCE<>''#39''Execution Order''#39''~Fa'' +
        ''lse~DISPLAY_SEQUENCE<>''#39''Execution Order''#39''~N~~~~~Default~N~~~''
      
        ''UNIT_PROCESSING~7~UNIT_PROCESSING~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''CROSS_ORDER_FLAG~0~CROSS_ORDER_FLAG~N~N~STDOPER_OBJECT_ID=''#39#39''~Fal'' +
        ''se~False~N~~~~~Default~N~~~''
      
        ''NUMBER_OF_LABELS~0~NUMBER_OF_LABELS~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''PRINT_LABEL~0~PRINT_LABEL~N~N~True~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''RECONCILE_SCRAP~0~RECONCILE_SCRAP~N~N~OPERATION_OVERLAP_FLAG<>''#39''Y'' +
        #39''~False~False~N~~~~~Default~N~~~''
      
        ''CROSS_ORDER_FLAG2~7~Cross Order?~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''BATCH_FLAG~0~Batch?~N~N~STDOPER_OBJECT_ID=''#39#39''~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''ACTUAL_MACHINE_ID~0~ACTUAL_MACHINE_ID~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''ORIENTATION_FLAG~0~ORIENTATION_FLAG~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''UNITS_PER_CYCLE~0~UNITS_PER_CYCLE~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''AUTO_CYCLE_FLAG~0~AUTO_CYCLE_FLAG~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''REPORT_ID_DISP~0~REPORT_ID_DISP~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''UNITS_PER_CYCLE_ACTUAL~0~UNITS_PER_CYCLE_ACTUAL~N~N~True~False~F'' +
        ''alse~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_ORDER_OPER_VCH1~Edit''
      ''UCF_PLAN_OPER_FLAG2~Edit''
      ''STATUS_CHG_NOTES~Edit''
      ''ALTER_TYPE~Edit''
      ''OSP_FLAG~Edit''
      ''SCHED_ENG_STD_FLAG~Edit''
      ''SCHED_MACHINE_HOURS_SETUP~Edit''
      ''SCHED_SETUP_TYPE~Edit''
      ''RECONCILE_SCRAP~Edit''
      ''OPER_KEY~Edit''
      ''BATCH_FLAG~Edit''
      ''SUPPLIER_CODE~Edit''
      ''UNIT_PROCESSING~Edit''
      ''PRINT_LABEL~Edit''
      ''ORDER_ID~Edit''
      ''ORDER_NO~Edit''
      ''ORDER_QTY~Edit''
      ''UCF_ORDER_VCH255_2~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=MFI_98EA8054E5BB6EAED32E5EEE1174BF91''
      ''OPER_KEY=55001'')
    InputUDVId = ''MFI_1003729''
    DataDefinitions.Strings = (
      ''ORDER_ID''
      ''ORDER_NO''
      ''PART_NO''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PART_CHG''
      ''PROGRAM''
      ''ORDER_STATUS''
      ''ORDER_HOLD_STATUS''
      ''ASGND_WORK_LOC''
      ''ORDER_CUST_ID''
      ''ORDER_QTY''
      ''SCHED_PRIORITY''
      ''ORIG_ORDER_ID''
      ''PARENT_ORDER_ID''
      ''SUPERCEDED_ORDER_ID''
      ''OPER_NO''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION''
      ''PLAN_ID''
      ''PLAN_UPDT_NO''
      ''OPER_KEY''
      ''OPER_UPDT_NO''
      ''OPER_STATUS''
      ''OPER_HOLD_STATUS''
      ''ASGND_WORK_DEPT''
      ''ASGND_WORK_CENTER''
      ''SCHED_START_DATE''
      ''SCHED_END_DATE''
      ''REVISED_START_DATE''
      ''REVISED_END_DATE''
      ''ACTUAL_START_DATE''
      ''ACTUAL_END_DATE''
      ''REVISED_CREW_QTY_SETUP''
      ''REVISED_CREW_QTY''
      ''ACTUAL_CREW_QTY_SETUP''
      ''ACTUAL_CREW_QTY''
      ''OPER_TITLE''
      ''PLND_MACHINE_NO''
      ''SCHED_LABOR_HOURS_SETUP''
      ''SCHED_DUR_HOURS_SETUP''
      ''SCHED_LABOR_HOURS_INSPECT''
      ''SCHED_DUR_HOURS_INSPECT''
      ''SCHED_LABOR_HOURS_PER_UNIT''
      ''SCHED_DUR_HOURS_PER_UNIT''
      ''SCHED_CREW_QTY_SETUP''
      ''SCHED_CREW_QTY''
      ''LOW_NAV_LVL''
      ''ASGND_WORK_PLACE_KEY''
      ''OPER_TYPE''
      ''ALT_ID''
      ''ALT_COUNT''
      ''SCHED_MACHINE_HOURS_PER_UNIT''
      ''SCHED_UNITS_PER_RUN''
      ''SCHED_SETUP_TYPE''
      ''SCHED_MACHINE_HOURS_SETUP''
      ''SCHED_ENG_STD_FLAG''
      ''OSP_FLAG''
      ''SUPPLIER_CODE''
      ''ALTER_TYPE''
      ''STATUS_CHG_NOTES''
      ''OCCUR_RATE''
      ''REWORK_FLAG''
      ''OSP_DAYS''
      ''OSP_COST_PER_UNIT''
      ''AUTO_COMPLETE_FLAG''
      ''SCHED_MOVE_HOURS''
      ''PLND_ASGND_WORK_LOC''
      ''OPER_OPT_FLAG''
      ''AUTO_START_FLAG''
      ''UCF_PLAN_VCH1''
      ''UCF_PLAN_VCH2''
      ''UCF_PLAN_VCH3''
      ''UCF_PLAN_VCH4''
      ''UCF_PLAN_VCH5''
      ''UCF_PLAN_VCH6''
      ''UCF_PLAN_VCH7''
      ''UCF_PLAN_VCH8''
      ''UCF_PLAN_NUM1''
      ''UCF_PLAN_NUM2''
      ''UCF_PLAN_FLAG1''
      ''UCF_PLAN_FLAG2''
      ''UCF_ORDER_VCH1''
      ''UCF_ORDER_VCH2''
      ''UCF_ORDER_VCH3''
      ''UCF_ORDER_VCH4''
      ''UCF_ORDER_VCH5''
      ''UCF_ORDER_NUM1''
      ''UCF_ORDER_NUM2''
      ''UCF_ORDER_FLAG1''
      ''UCF_PLAN_OPER_VCH1''
      ''UCF_PLAN_OPER_VCH2''
      ''UCF_PLAN_OPER_VCH3''
      ''UCF_PLAN_OPER_VCH4''
      ''UCF_PLAN_OPER_VCH5''
      ''UCF_PLAN_OPER_NUM1''
      ''UCF_PLAN_OPER_NUM2''
      ''UCF_PLAN_OPER_FLAG1''
      ''UCF_PLAN_OPER_FLAG2''
      ''UCF_ORDER_OPER_VCH1''
      ''UCF_ORDER_OPER_VCH2''
      ''UCF_ORDER_OPER_NUM1''
      ''UCF_ORDER_OPER_NUM2''
      ''UCF_ORDER_OPER_FLAG1''
      ''UCF_ORDER_OPER_DATE1''
      ''TEST_TYPE''
      ''EXTERNAL_PLM_NO''
      ''EXTERNAL_ERP_NO''
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
      ''UCF_PLAN_VCH4000_1''
      ''UCF_PLAN_VCH4000_2''
      ''UCF_ORDER_VCH6''
      ''UCF_ORDER_VCH7''
      ''UCF_ORDER_VCH8''
      ''UCF_ORDER_VCH9''
      ''UCF_ORDER_VCH10''
      ''UCF_ORDER_VCH11''
      ''UCF_ORDER_VCH12''
      ''UCF_ORDER_VCH13''
      ''UCF_ORDER_VCH14''
      ''UCF_ORDER_VCH15''
      ''UCF_ORDER_NUM3''
      ''UCF_ORDER_NUM4''
      ''UCF_ORDER_NUM5''
      ''UCF_ORDER_DATE1''
      ''UCF_ORDER_DATE2''
      ''UCF_ORDER_DATE3''
      ''UCF_ORDER_DATE4''
      ''UCF_ORDER_DATE5''
      ''UCF_ORDER_FLAG2''
      ''UCF_ORDER_FLAG3''
      ''UCF_ORDER_FLAG4''
      ''UCF_ORDER_FLAG5''
      ''UCF_ORDER_VCH255_1''
      ''UCF_ORDER_VCH255_2''
      ''UCF_ORDER_VCH255_3''
      ''UCF_ORDER_VCH4000_1''
      ''UCF_ORDER_VCH4000_2''
      ''SEQ_STEPS_FLAG''
      ''UCF_PLAN_OPER_VCH6''
      ''UCF_PLAN_OPER_VCH7''
      ''UCF_PLAN_OPER_VCH8''
      ''UCF_PLAN_OPER_VCH9''
      ''UCF_PLAN_OPER_VCH10''
      ''UCF_PLAN_OPER_VCH11''
      ''UCF_PLAN_OPER_VCH12''
      ''UCF_PLAN_OPER_VCH13''
      ''UCF_PLAN_OPER_VCH14''
      ''UCF_PLAN_OPER_VCH15''
      ''UCF_PLAN_OPER_NUM3''
      ''UCF_PLAN_OPER_NUM4''
      ''UCF_PLAN_OPER_NUM5''
      ''UCF_PLAN_OPER_DATE1''
      ''UCF_PLAN_OPER_DATE2''
      ''UCF_PLAN_OPER_DATE3''
      ''UCF_PLAN_OPER_DATE4''
      ''UCF_PLAN_OPER_DATE5''
      ''UCF_PLAN_OPER_FLAG3''
      ''UCF_PLAN_OPER_FLAG4''
      ''UCF_PLAN_OPER_FLAG5''
      ''UCF_PLAN_OPER_VCH255_1''
      ''UCF_PLAN_OPER_VCH255_2''
      ''UCF_PLAN_OPER_VCH255_3''
      ''UCF_PLAN_OPER_VCH4000_1''
      ''UCF_PLAN_OPER_VCH4000_2''
      ''UCF_ORDER_OPER_VCH3''
      ''UCF_ORDER_OPER_VCH4''
      ''UCF_ORDER_OPER_VCH5''
      ''UCF_ORDER_OPER_VCH6''
      ''UCF_ORDER_OPER_VCH7''
      ''UCF_ORDER_OPER_VCH8''
      ''UCF_ORDER_OPER_VCH9''
      ''UCF_ORDER_OPER_VCH10''
      ''UCF_ORDER_OPER_VCH11''
      ''UCF_ORDER_OPER_VCH12''
      ''UCF_ORDER_OPER_VCH13''
      ''UCF_ORDER_OPER_VCH14''
      ''UCF_ORDER_OPER_VCH15''
      ''UCF_ORDER_OPER_NUM3''
      ''UCF_ORDER_OPER_NUM4''
      ''UCF_ORDER_OPER_NUM5''
      ''UCF_ORDER_OPER_DATE2''
      ''UCF_ORDER_OPER_DATE3''
      ''UCF_ORDER_OPER_DATE4''
      ''UCF_ORDER_OPER_DATE5''
      ''UCF_ORDER_OPER_FLAG2''
      ''UCF_ORDER_OPER_FLAG3''
      ''UCF_ORDER_OPER_FLAG4''
      ''UCF_ORDER_OPER_FLAG5''
      ''UCF_ORDER_OPER_VCH255_1''
      ''UCF_ORDER_OPER_VCH255_2''
      ''UCF_ORDER_OPER_VCH255_3''
      ''UCF_ORDER_OPER_VCH4000_1''
      ''UCF_ORDER_OPER_VCH4000_2''
      ''OPER_CHANGE_LEVEL''
      ''SECURITY_GROUP''
      ''EXE_ORDER''
      ''UNIT_PROCESSING''
      ''CROSS_ORDER_FLAG''
      ''NUMBER_OF_LABELS''
      ''PRINT_LABEL''
      ''RECONCILE_SCRAP''
      ''CROSS_ORDER_FLAG2''
      ''BATCH_FLAG''
      ''ACTUAL_MACHINE_ID''
      ''ORIENTATION_FLAG''
      ''UNITS_PER_CYCLE''
      ''AUTO_CYCLE_FLAG''
      ''REPORT_ID_DISP''
      ''UNITS_PER_CYCLE_ACTUAL'')
    ConsolidatedQuery = False
  end
  object OrderStdOperInfo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''WidStdOperSel''
    LinkedControls.Strings = (
      ''DisplayStdOper~''
      ''_OBJ48~COMMODITY_JURISDICTION''
      ''_OBJ49~COMMODITY_CLASSIFICATION'')
    ParamsSQLSourceName = ''@CurrentMarker''
    SelectedFields.Strings = (
      
        ''STDOPER_OBJECT_ID~0~STDOPER_OBJECT_ID~N~N~False~False~False~N~~~'' +
        ''~~Default~N~''
      
        ''STDOPER_PLAN_ID~0~STDOPER_PLAN_ID~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''STDOPER_PLAN_VER~0~STDOPER_PLAN_VER~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''STDOPER_PLAN_REV~0~STDOPER_PLAN_REV~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''STDOPER_PLAN_ALT~0~STDOPER_PLAN_ALT~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''COMMODITY_JURISDICTION~0~COMMODITY_JURISDICTION~N~N~STDOPER_OBJE'' +
        ''CT_ID = ''#39#39''~False~False~N~~~~~Default~N~''
      
        ''COMMODITY_CLASSIFICATION~0~COMMODITY_CLASSIFICATION~N~N~STDOPER_'' +
        ''OBJECT_ID = ''#39#39''~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''STDOPER_PLAN_ID~Edit'')
    DataDefinitions.Strings = (
      ''STDOPER_OBJECT_ID''
      ''STDOPER_PLAN_ID''
      ''STDOPER_PLAN_VER''
      ''STDOPER_PLAN_REV''
      ''STDOPER_PLAN_ALT''
      ''SECURITY_GROUP'')
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

