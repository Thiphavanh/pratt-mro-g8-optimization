
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5A9D866D829F3FE5E05387971F0ADE00.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5A9D866D829F3FE5E05387971F0ADE00';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_AssociatedPlanComm';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SRMO_EXPT_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 525
  Height = 1000
  AutoScroll = False
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
  object grid: TsfDBGrid
    Left = 99
    Top = 408
    Width = 388
    Height = 107
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ1: TsfLabel
    Left = 39
    Top = 419
    Width = 42
    Height = 13
    Alignment = taRightJustify
    Caption = ''          To''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object UCF_Comm_Vch8: TsfDBEdit
    Left = 360
    Top = 701
    Width = 128
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
    TabOrder = 1
    Caption = ''UCF Comm Vch8''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch9: TsfDBEdit
    Left = 106
    Top = 654
    Width = 128
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
    TabOrder = 2
    Caption = ''UCF Comm Vch9''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch10: TsfDBEdit
    Left = 112
    Top = 731
    Width = 128
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
    TabOrder = 3
    Caption = ''UCF Comm Vch10''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch11: TsfDBEdit
    Left = 360
    Top = 731
    Width = 128
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
    TabOrder = 4
    Caption = ''UCF Comm Vch11''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch12: TsfDBEdit
    Left = 106
    Top = 684
    Width = 128
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
    TabOrder = 5
    Caption = ''UCF Comm Vch12''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch14: TsfDBEdit
    Left = 360
    Top = 761
    Width = 128
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
    TabOrder = 7
    Caption = ''UCF Comm Vch14''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch13: TsfDBEdit
    Left = 112
    Top = 761
    Width = 128
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
    TabOrder = 6
    Caption = ''UCF Comm Vch13''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Vch15: TsfDBEdit
    Left = 106
    Top = 714
    Width = 128
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
    TabOrder = 8
    Caption = ''UCF Comm Vch15''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Num1: TsfDBEdit
    Left = 112
    Top = 791
    Width = 128
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
    TabOrder = 9
    Caption = ''UCF Comm Num1''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Num2: TsfDBEdit
    Left = 360
    Top = 791
    Width = 128
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
    TabOrder = 10
    Caption = ''UCF Comm Num2''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Num3: TsfDBEdit
    Left = 106
    Top = 744
    Width = 128
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
    TabOrder = 11
    Caption = ''UCF Comm Num3''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Num4: TsfDBEdit
    Left = 112
    Top = 821
    Width = 128
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
    TabOrder = 12
    Caption = ''UCF Comm Num4''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Num5: TsfDBEdit
    Left = 360
    Top = 821
    Width = 128
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
    TabOrder = 13
    Caption = ''UCF Comm Num5''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Flag1: TsfDBEdit
    Left = 112
    Top = 851
    Width = 128
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
    TabOrder = 14
    Caption = ''UCF Comm Flag1''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Flag2: TsfDBEdit
    Left = 360
    Top = 739
    Width = 128
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
    TabOrder = 15
    Caption = ''UCF Comm Flag2''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Flag3: TsfDBEdit
    Left = 106
    Top = 804
    Width = 128
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
    TabOrder = 16
    Caption = ''UCF Comm Flag3''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Flag4: TsfDBEdit
    Left = 112
    Top = 769
    Width = 128
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
    TabOrder = 17
    Caption = ''UCF Comm Flag4''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Flag5: TsfDBEdit
    Left = 360
    Top = 769
    Width = 128
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
    Caption = ''UCF Comm Flag5''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Date1: TsfDBEdit
    Left = 112
    Top = 799
    Width = 128
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
    TabOrder = 19
    Caption = ''UCF Comm Date1''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Date2: TsfDBEdit
    Left = 360
    Top = 799
    Width = 128
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
    TabOrder = 20
    Caption = ''UCF Comm Date2''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Date3: TsfDBEdit
    Left = 106
    Top = 864
    Width = 128
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
    TabOrder = 21
    Caption = ''UCF Comm Date3''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Date4: TsfDBEdit
    Left = 112
    Top = 829
    Width = 128
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
    TabOrder = 22
    Caption = ''UCF Comm Date4''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UCF_Comm_Date5: TsfDBEdit
    Left = 360
    Top = 829
    Width = 128
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
    TabOrder = 23
    Caption = ''UCF Comm Date5''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object partno: TsfDBEdit
    Left = 99
    Top = 100
    Width = 109
    Height = 22
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
    Caption = ''Item No''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object parttype: TsfDBEdit
    Left = 114
    Top = 608
    Width = 100
    Height = 22
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
    Caption = ''Item Type''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planver: TsfDBEdit
    Left = 367
    Top = 575
    Width = 38
    Height = 22
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
    Caption = ''Plan Ver''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planrev: TsfDBEdit
    Left = 466
    Top = 577
    Width = 31
    Height = 22
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
    Caption = ''Plan Rev''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object partchg: TsfDBEdit
    Left = 275
    Top = 577
    Width = 31
    Height = 22
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
    Caption = ''Item Rev''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object program: TsfDBEdit
    Left = 466
    Top = 609
    Width = 76
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 29
    Caption = ''Program''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object DisplayWorkPlan: TsfCommandButton
    Left = 300
    Top = 8
    Width = 104
    Height = 25
    Hint = ''Display Work Plan''
    Caption = ''Display Work Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 33
    OtherCommands.Strings = (
      
        ''Desc=Display Work Plan,Priv=,Visible=,TagValue=,Action=Invoke,To'' +
        ''ol=PrPlg Instructions,ReportToolId=,Params(''#39''PLAN_ID=:PLAN_ID,PLA'' +
        ''N_VERSION=:PLAN_VERSION,PLAN_REVISION=:PLAN_REVISION,PLAN_ALTERA'' +
        ''TIONS=:PLAN_ALTERATIONS''#39'')'')
  end
  object from: TsfDBEdit
    Left = 99
    Top = 42
    Width = 141
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
    TabOrder = 30
    Caption = ''From''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object priority: TsfDBEdit
    Left = 74
    Top = 574
    Width = 121
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
    TabOrder = 31
    Caption = ''Priority''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object subject: TsfDBEdit
    Left = 99
    Top = 70
    Width = 388
    Height = 21
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 32
    Caption = ''Subject''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object acknowledge: TsfCommandButton
    Left = 109
    Top = 8
    Width = 89
    Height = 25
    Hint = ''Acknowledge''
    Caption = ''Approve''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 34
    OtherCommands.Strings = (
      
        ''Desc=Acknowledge,Priv={},''#39''Visible={(ack=''#39#39''Y''#39#39'') AND (COMM_STATUS '' +
        ''<> ''#39#39''COMPLETE''#39#39'') AND (COMM_STATUS <> ''#39#39''CANCEL''#39#39'')}''#39'',TagValue=,FKe'' +
        ''y=,Action=UDV,UDVType=Update,UDVID=MFI_2011070'')
  end
  object send: TsfCommandButton
    Left = 12
    Top = 8
    Width = 89
    Height = 25
    Hint = ''Send''
    Caption = ''Send''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 35
    OtherCommands.Strings = (
      
        ''Desc=Send,Priv=,''#39''Visible=(send=''#39#39''Y''#39#39'') AND (COMM_STATUS <> ''#39#39''COMP'' +
        ''LETE''#39#39'')''#39'',TagValue=,FKey=,Action=UDV,UDVType=Update,UDVID=MFI_201'' +
        ''1066'')
  end
  object delete: TsfCommandButton
    Left = 109
    Top = 8
    Width = 89
    Height = 25
    Hint = ''Delete''
    Caption = ''Delete''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 36
    OtherCommands.Strings = (
      
        ''Desc=Delete,Priv=,''#39''Visible=(delete=''#39#39''Y''#39#39'') AND (COMM_STATUS <> ''#39#39 +
        ''COMPLETE''#39#39'')''#39'',TagValue=,FKey=,Action=UDV,UDVType=Update,UDVID=MFI'' +
        ''_2011067'')
  end
  object ITEM_SUBTYPE: TsfDBEdit
    Left = 275
    Top = 610
    Width = 130
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
    TabOrder = 37
    Caption = ''Item;Subtype''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Reject: TsfCommandButton
    Left = 206
    Top = 8
    Width = 88
    Height = 25
    Hint = ''Reject''
    Caption = ''Reject''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 38
    OtherCommands.Strings = (
      
        ''Desc=Reject,Priv={},''#39''Visible={(COMM_STATUS <> ''#39#39''COMPLETE''#39#39'') AND '' +
        ''(COMM_STATUS <> ''#39#39''CANCEL''#39#39'')}''#39'',TagValue=,FKey=,ParamsSrc=@Default'' +
        '',Action=UDV,UDVType=Update,UDVID=PWUST_5AB1A7D4AD35C993E05387971'' +
        ''F0A27DC'')
  end
  object planType: TsfDBEdit
    Left = 301
    Top = 42
    Width = 66
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 39
    Caption = ''Plan Type''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object PlanNo: TsfDBEdit
    Left = 414
    Top = 42
    Width = 72
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 40
    Caption = ''Plan No''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object CHG_HIST: TsfDBMemo
    Left = 99
    Top = 220
    Width = 388
    Height = 180
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 41
    MemoCaption = ''Change History''
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object _OBJ3: TsfDBEdit
    Left = 421
    Top = 100
    Width = 66
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 42
    Caption = ''Plan Rev''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 99
    Top = 190
    Width = 125
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
    Caption = ''PWP''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 99
    Top = 160
    Width = 125
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 44
    Caption = ''Program''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Plan_Title: TsfDBEdit
    Left = 99
    Top = 130
    Width = 388
    Height = 22
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 45
    Caption = ''Plan Title''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object CommFromSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''CommFromSel''
    LinkedControls.Strings = (
      ''UCF_Comm_Vch8~UCF_COMM_VCH8''
      ''UCF_Comm_Vch9~UCF_COMM_VCH9''
      ''UCF_Comm_Vch10~UCF_COMM_VCH10''
      ''UCF_Comm_Vch11~UCF_COMM_VCH11''
      ''UCF_Comm_Vch12~UCF_COMM_VCH12''
      ''UCF_Comm_Vch14~UCF_COMM_VCH14''
      ''UCF_Comm_Num1~UCF_COMM_NUM1''
      ''UCF_Comm_Num2~UCF_COMM_NUM2''
      ''UCF_Comm_Num3~UCF_COMM_NUM3''
      ''UCF_Comm_Num4~UCF_COMM_NUM4''
      ''UCF_Comm_Num5~UCF_COMM_NUM5''
      ''UCF_Comm_Flag1~UCF_COMM_FLAG1''
      ''UCF_Comm_Flag2~UCF_COMM_FLAG2''
      ''UCF_Comm_Flag3~UCF_COMM_FLAG3''
      ''UCF_Comm_Flag4~UCF_COMM_FLAG4''
      ''UCF_Comm_Flag5~UCF_COMM_FLAG5''
      ''UCF_Comm_Date1~UCF_COMM_DATE1''
      ''UCF_Comm_Date2~UCF_COMM_DATE2''
      ''UCF_Comm_Date3~UCF_COMM_DATE3''
      ''UCF_Comm_Date4~UCF_COMM_DATE4''
      ''UCF_Comm_Date5~UCF_COMM_DATE5''
      ''UCF_Comm_Vch13~UCF_COMM_VCH13''
      ''UCF_Comm_Vch15~UCF_COMM_VCH15''
      ''priority~PRIORITY''
      ''from~UCF_COMM_VCH2''
      ''subject~SUBJECT'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''UCF_COMM_VCH1~0~UCF_COMM_VCH1~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH2~0~UCF_COMM_VCH2~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH3~0~UCF_COMM_VCH3~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH4~0~UCF_COMM_VCH4~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH5~0~UCF_COMM_VCH5~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH6~0~UCF_COMM_VCH6~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH7~0~UCF_COMM_VCH7~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH8~0~UCF_COMM_VCH8~N~N~True~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH9~0~UCF_COMM_VCH9~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH10~0~UCF_COMM_VCH10~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH11~0~UCF_COMM_VCH11~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH12~0~UCF_COMM_VCH12~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH13~0~UCF_COMM_VCH13~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH14~0~UCF_COMM_VCH14~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_VCH15~0~UCF_COMM_VCH15~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_NUM1~0~UCF_COMM_NUM1~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_NUM2~0~UCF_COMM_NUM2~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_NUM3~0~UCF_COMM_NUM3~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_NUM4~0~UCF_COMM_NUM4~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_NUM5~0~UCF_COMM_NUM5~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_FLAG1~0~UCF_COMM_FLAG1~N~N~Y~N~N~N~~~~~Defau';
p2 clob :='lt~N~''
      ''UCF_COMM_FLAG2~0~UCF_COMM_FLAG2~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_FLAG3~0~UCF_COMM_FLAG3~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_FLAG4~0~UCF_COMM_FLAG4~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_FLAG5~0~UCF_COMM_FLAG5~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_DATE1~0~UCF_COMM_DATE1~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_DATE2~0~UCF_COMM_DATE2~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_DATE3~0~UCF_COMM_DATE3~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_DATE4~0~UCF_COMM_DATE4~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_COMM_DATE5~0~UCF_COMM_DATE5~N~N~Y~N~N~N~~~~~Default~N~''
      ''PRIORITY~0~PRIORITY~N~N~True~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_COMM_VCH8~Edit''
      ''UCF_COMM_VCH9~Edit''
      ''UCF_COMM_VCH13~Edit''
      ''UCF_COMM_NUM2~Edit''
      ''UCF_COMM_NUM4~Edit''
      ''UCF_COMM_DATE4~Edit''
      ''UCF_COMM_DATE5~Edit''
      ''UCF_COMM_VCH3~Edit''
      ''UCF_COMM_VCH4~Edit''
      ''UCF_COMM_VCH5~Edit''
      ''UCF_COMM_VCH2~Edit''
      ''UCF_COMM_VCH6~Edit'')
    InputUDVId = ''MFI_2011074''
    InsertUDVId = ''MFI_2011074''
    DataDefinitions.Strings = (
      ''UCF_COMM_VCH1''
      ''UCF_COMM_VCH2''
      ''UCF_COMM_VCH3''
      ''UCF_COMM_VCH4''
      ''UCF_COMM_VCH5''
      ''UCF_COMM_VCH6''
      ''UCF_COMM_VCH7''
      ''UCF_COMM_VCH8''
      ''UCF_COMM_VCH9''
      ''UCF_COMM_VCH10''
      ''UCF_COMM_VCH11''
      ''UCF_COMM_VCH12''
      ''UCF_COMM_VCH13''
      ''UCF_COMM_VCH14''
      ''UCF_COMM_VCH15''
      ''UCF_COMM_NUM1''
      ''UCF_COMM_NUM2''
      ''UCF_COMM_NUM3''
      ''UCF_COMM_NUM4''
      ''UCF_COMM_NUM5''
      ''UCF_COMM_FLAG1''
      ''UCF_COMM_FLAG2''
      ''UCF_COMM_FLAG3''
      ''UCF_COMM_FLAG4''
      ''UCF_COMM_FLAG5''
      ''UCF_COMM_DATE1''
      ''UCF_COMM_DATE2''
      ''UCF_COMM_DATE3''
      ''UCF_COMM_DATE4''
      ''UCF_COMM_DATE5''
      ''PRIORITY'')
    ConsolidatedQuery = False
  end
  object CommToRoutingSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_CommToRoutingSel''
    DeleteSqlId = ''CommRoutingDel''
    LinkedControls.Strings = (
      ''grid~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ROUTING_QUEUE~10~Queue~N~N~N~N~N~N~~~~~Default~N~''
      ''REPLY_STATUS~10~Response;Status~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UCF_COMM_VCH1~0~Approved/rejected By~N~N~False~False~False~N~~~~'' +
        ''~Default~N~''
      
        ''UCF_COMM_DATE1~0~Approved/rejected Time~N~N~False~False~False~N~'' +
        ''~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''TO_QUEUE~Edit''
      ''ROUTING_QUEUE~Edit'')
    DataDefinitions.Strings = (
      ''ROUTING_QUEUE''
      ''REPLY_REQMT''
      ''REPLY_STATUS''
      ''ACTUAL_START_DATE''
      ''ACTUAL_END_DATE''
      ''SCHED_START_DATE''
      ''SCHED_END_DATE''
      ''SCH_DAYS'')
    ConsolidatedQuery = False
  end
  object CommStatus: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_CommStatus''
    LinkedControls.Strings = (
      ''acknowledge~''
      ''Reject~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''COMM_STATUS~0~COMM_STATUS~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    ConsolidatedQuery = False
  end
  object CommEmbedDocTask: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''CommEmbedPlanTaskSel''
    LinkedControls.Strings = (
      ''planver~PLAN_VERSION''
      ''partchg~PART_CHG''
      ''planrev~PLAN_REVISION''
      ''program~PROGRAM''
      ''DisplayWorkPlan~''
      ''send~''
      ''delete~''
      ''parttype~ITEM_TYPE''
      ''ITEM_SUBTYPE~ITEM_SUBTYPE''
      ''partno~PART_NO'')
    ParamsSQLSourceName = ''@CurrentMarker,@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~~Default~N~''
      
        ''PLAN_VERSION~0~PLAN_VERSION~N~N~True~False~False~N~~~~~Default~N'' +
        ''~''
      
        ''PLAN_REVISION~0~PLAN_REVISION~N~N~True~False~False~N~~~~~Default'' +
        ''~N~''
      
        ''PLAN_ALTERATIONS~0~PLAN_ALTERATIONS~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_CHG~0~PART_CHG~N~N~True~False~False~N~~~~~Default~N~''
      ''ITEM_TYPE~0~ITEM_TYPE~N~N~True~False~False~N~~~~~Default~N~''
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~True~False~False~N~~~~~Default~N'' +
        ''~''
      ''PROGRAM~0~PROGRAM~N~N~True~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PLAN_ID~Edit'')
    TestParamValues.Strings = (
      ''plan_id=1''
      ''plan_version=1''
      ''plan_revision=1''
      ''plan_alterations=1''
      ''part_no=1''
      ''part_chg=1''
      ''item_type=1''
      ''item_subtype=1''
      ''program=1'')
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''PART_NO''
      ''PART_CHG''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PROGRAM'')
    ConsolidatedQuery = False
  end
  object PlanInfo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SelectAdditionalPlanInformation''
    LinkedControls.Strings = (
      ''planType~PLAN_TYPE''
      ''PlanNo~PLAN_NO''
      ''_OBJ5~PROGRAM''
      ''CHG_HIST~UCF_PLAN_VCH4000_1''
      ''_OBJ4~PWP_ID''
      ''_OBJ3~PLAN_REVISION''
      ''Plan_Title~PLAN_TITLE'')
    ParamsSQLSourceName = ''@CurrentMarker,@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~''
      
        ''UCF_PLAN_FLAG2~0~UCF_PLAN_FLAG2~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_PLAN_VCH1~0~UCF_PLAN_VCH1~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_PLAN_VCH2~0~UCF_PLAN_VCH2~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_PLAN_VCH11~0~UCF_PLAN_VCH11~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_PLAN_VCH6~0~UCF_PLAN_VCH6~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_PLAN_VCH7~0~UCF_PLAN_VCH7~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      ''MFG_BOM_CHG~0~MFG_BOM_CHG~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PLAN_ID~Edit'')
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PART_NO''
      ''UCF_PLAN_FLAG2''
      ''UCF_PLAN_VCH1''
      ''UCF_PLAN_VCH2''
      ''UCF_PLAN_VCH11''
      ''UCF_PLAN_VCH6''
      ''UCF_PLAN_VCH7'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanInfo''
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

