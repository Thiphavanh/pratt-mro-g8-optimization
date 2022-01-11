
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CFFF1F776C0D455FE05387971F0A258B.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CFFF1F776C0D455FE05387971F0A258B';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperSignOn';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 2016';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 300
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Work Order Operation - Sign On/Off''
  AutoAccept = True
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
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
  object on_off_flag: TsfDBInputEdit
    Left = 152
    Top = 56
    Width = 52
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
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Sign On/Off*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ6: TsfDBInputMemo
    Left = 152
    Top = 93
    Width = 150
    Height = 26
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    TabOrder = 1
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
    MemoCaption = ''Skills Required''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    ParentCtl3D = False
    FullHeight = 26
  end
  object _OBJ7: TsfDBInputMemo
    Left = 152
    Top = 131
    Width = 150
    Height = 26
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    TabOrder = 2
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
    MemoCaption = ''Certifications Required''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    ParentCtl3D = False
    FullHeight = 26
  end
  object _OBJ8: TsfDBInputMemo
    Left = 152
    Top = 169
    Width = 460
    Height = 98
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    TabOrder = 3
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
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    WordWrap = False
    ParentCtl3D = False
    FullHeight = 98
  end
  object _OBJ11: TsfLabel
    Left = 73
    Top = 169
    Width = 71
    Height = 13
    Alignment = taRightJustify
    Caption = ''Assigned Crew''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object OPER_SIGNON_VCH1: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    Caption = ''UCF;Oper Signon Vch1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_VCH2: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''UCF;Oper Signon Vch2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_VCH3: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    Caption = ''UCF;Oper Signon Vch3''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_VCH4: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    Caption = ''UCF;Oper Signon Vch4''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_VCH5: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''UCF;Oper Signon Vch5''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_NUM1: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    Caption = ''UCF;Oper Signon Num1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_NUM2: TsfDBInputEdit
    Left = 464
    Top = 134
    Width = 121
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
    Caption = ''UCF;Oper Signon Num2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_FLAG1: TsfDBInputEdit
    Left = 536
    Top = 134
    Width = 49
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
    Caption = ''UCF;Oper Signon Flag1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_SIGNON_FLAG2: TsfDBInputEdit
    Left = 536
    Top = 134
    Width = 49
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
    TabOrder = 12
    EntryType = etAlphaNumeric
    Caption = ''UCF;Oper Signon Flag2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ12: TsfDBInputEdit
    Left = 462
    Top = 93
    Width = 150
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
    TabOrder = 13
    EntryType = etAlphaNumeric
    Caption = ''Labor Sign On Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ13: TsfBevel
    Left = 11
    Top = 37
    Width = 648
    Height = 242
    Shape = bsSpacer
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ14: TsfInputCheckBox
    Left = 290
    Top = 56
    Width = 323
    Height = 20
    Caption = 
      ''Sign off all open user work order activities before sign on/off?'' +
      ''*''
    ReturnIsTab = False
    TabOrder = 14
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
  object SffndUserActivityIU: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''UCF_OPER_SIGNON_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_VCH3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_VCH4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_VCH5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_OPER_SIGNON_FLAG2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ON_OFF_FLAG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=3''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''@USERID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SIGNOFF_ALL_NON_ATTENDANCE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''LABOR_SIGNON_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_OPER_SIGNON_VCH6''
      ''UCF_OPER_SIGNON_VCH7''
      ''UCF_OPER_SIGNON_VCH8''
      ''UCF_OPER_SIGNON_VCH9''
      ''UCF_OPER_SIGNON_VCH10''
      ''UCF_OPER_SIGNON_VCH11''
      ''UCF_OPER_SIGNON_VCH12''
      ''UCF_OPER_SIGNON_VCH13''
      ''UCF_OPER_SIGNON_VCH14''
      ''UCF_OPER_SIGNON_VCH15''
      ''UCF_OPER_SIGNON_FLAG3''
      ''UCF_OPER_SIGNON_FLAG4''
      ''UCF_OPER_SIGNON_FLAG5''
      ''UCF_OPER_SIGNON_NUM3''
      ''UCF_OPER_SIGNON_NUM4''
      ''UCF_OPER_SIGNON_NUM5''
      ''UCF_OPER_SIGNON_DATE1''
      ''UCF_OPER_SIGNON_DATE2''
      ''UCF_OPER_SIGNON_DATE3''
      ''UCF_OPER_SIGNON_DATE4''
      ''UCF_OPER_SIGNON_DATE5''
      ''UCF_OPER_SIGNON_VCH255_1''
      ''UCF_OPER_SIGNON_VCH255_2''
      ''UCF_OPER_SIGNON_VCH255_3''
      ''UCF_OPER_SIGNON_VCH4000_1''
      ''UCF_OPER_SIGNON_VCH4000_2''
      ''GROUP_JOB_NO'')
    InputFieldsEditControlSSL.Strings = (
      ''OPER_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_VCH3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_VCH4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_VCH5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_NUM1''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_OPER_SIGNON_NUM2''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SIGNOFF_ALL_NON_ATTENDANCE''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''UCF_OPER_SIGNON_FLAG1''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''UCF_OPER_SIGNON_FLAG2''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''@USERID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ON_OFF_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LABOR_SIGNON_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_LABORSIGNONOFFLKP''
      ''  ParamsSrc=skillcertcrewmemo,getLaborType''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N'')
    InsertSqlId = ''SffndUserActivityIU''
    CancelSqlId = ''MFI_WID_USER_OPER_SESSION_DELETE''
    UpdateSqlId = ''SffndUserActivityIU''
    ParamsSQLSourceName = ''skillcertcrewmemo,getLaborType''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ6~skill''
      ''_OBJ7~cert''
      ''_OBJ8~crew''
      ''OPER_SIGNON_VCH2~UCF_OPER_SIGNON_VCH2''
      ''OPER_SIGNON_VCH3~UCF_OPER_SIGNON_VCH3''
      ''OPER_SIGNON_VCH4~UCF_OPER_SIGNON_VCH4''
      ''OPER_SIGNON_VCH5~UCF_OPER_SIGNON_VCH5''
      ''OPER_SIGNON_NUM1~UCF_OPER_SIGNON_NUM1''
      ''OPER_SIGNON_NUM2~UCF_OPER_SIGNON_NUM2''
      ''OPER_SIGNON_FLAG1~UCF_OPER_SIGNON_FLAG1''
      ''OPER_SIGNON_FLAG2~UCF_OPER_SIGNON_FLAG2''
      ''OPER_SIGNON_VCH1~UCF_OPER_SIGNON_VCH1''
      ''on_off_flag~ON_OFF_FLAG''
      ''_OBJ12~LABOR_SIGNON_TYPE''
      ''_OBJ14~SIGNOFF_ALL_NON_ATTENDANCE'')
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''UCF_OPER_SIGNON_VCH1=''
      ''UCF_OPER_SIGNON_VCH2=''
      ''UCF_OPER_SIGNON_VCH3=''
      ''UCF_OPER_SIGNON_VCH4=''
      ''UCF_OPER_SIGNON_VCH5=''
      ''UCF_OPER_SIGNON_NUM1=''
      ''UCF_OPER_SIGNON_NUM2=''
      ''UCF_OPER_SIGNON_FLAG1=''
      ''UCF_OPER_SIGNON_FLAG2=''
      ''ON_OFF_FLAG=''
      ''OPER_KEY''
      ''SIGNOFF_ALL_NON_ATTENDANCE=''
      ''@USERID=''
      ''SIGNOFF_ALL_NON_ATTENDANCE=''
      ''ON_OFF_FLAG=''
      ''LABOR_SIGNON_TYPE=''
      ''UCF_OPER_SIGNON_VCH6''
      ''UCF_OPER_SIGNON_VCH7''
      ''UCF_OPER_SIGNON_VCH8''
      ''UCF_OPER_SIGNON_VCH9''
      ''UCF_OPER_SIGNON_VCH10''
      ''UCF_OPER_SIGNON_VCH11''
      ''UCF_OPER_SIGNON_VCH12''
      ''UCF_OPER_SIGNON_VCH13''
      ''UCF_OPER_SIGNON_VCH14''
      ''UCF_OPER_SIGNON_VCH15''
      ''UCF_OPER_SIGNON_FLAG3''
      ''UCF_OPER_SIGNON_FLAG4''
      ''UCF_OPER_SIGNON_FLAG5''
      ''UCF_OPER_SIGNON_NUM3''
      ''UCF_OPER_SIGNON_NUM4''
      ''UCF_OPER_SIGNON_NUM5''
      ''UCF_OPER_SIGNON_DATE1''
      ''UCF_OPER_SIGNON_DATE2''
      ''UCF_OPER_SIGNON_DATE3''
      ''UCF_OPER_SIGNON_DATE4''
      ''UCF_OPER_SIGNON_DATE5''
      ''UCF_OPER_SIGNON_VCH255_1''
      ''UCF_OPER_SIGNON_VCH255_2''
      ''UCF_OPER_SIGNON_VCH255_3''
      ''UCF_OPER_SIGNON_VCH4000_1''
      ''UCF_OPER_SIGNON_VCH4000_2''
      ''GROUP_JOB_NO'')
  end
  object getLaborType: TsfTransactionParamSource
    SelectSqlId = ''MFI_WID_LABOR_SIGNON_TYPE_GET''
    ParamsSQLSourceName = ''@ToolScope,skillcertcrewmemo,getOperType''
    PublishParams = True
  end
  object skillcertcrewmemo: TsfTransactionParamSource
    SelectSqlId = ''SkillCertCrew''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
  end
  object getOperType: TsfTransactionParamSource
    SelectSqlId = ''MFI_WID_OPERTYPESELECT''
    ParamsSQLSourceName = ''@ToolScope,@AUXPARAMS''
    PublishParams = True
  end
end';
v_iclob clob;
begin
v_iclob := p1;
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

