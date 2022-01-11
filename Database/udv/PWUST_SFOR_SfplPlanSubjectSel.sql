
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5658B31457E15029E05387971F0AB2C1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5658B31457E15029E05387971F0AB2C1';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_SFOR_SfplPlanSubjectSel';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_PLG_205';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 480
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
  object _OBJ1: TsfDBGrid
    Left = 2
    Top = 27
    Width = 390
    Height = 100
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39
      ''@Row~Red~Default~SUBJECT_STATUS=''#39''HOLD''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 2
    Top = 4
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Other commands '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object _OBJ3: TsfDBGrid
    Left = 3
    Top = 156
    Width = 390
    Height = 100
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39
      ''@Row~Red~Default~SUBJECT_STATUS=''#39''HOLD''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ4: TsfUDVNavigator
    Left = 3
    Top = 133
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ7: TsfLabel
    Left = 123
    Top = 10
    Width = 59
    Height = 13
    Caption = ''Task Groups''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ8: TsfLabel
    Left = 123
    Top = 135
    Width = 110
    Height = 13
    Caption = ''Task Group Operations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ10: TsfCommandButton
    Left = 259
    Top = 4
    Width = 74
    Height = 21
    Hint = ''Hold''
    Caption = ''Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Hold,Priv=,Visible=''#39''SUBJECT_STATUS<>''#39#39''HOLD''#39#39#39'',TagValue=,FKe'' +
        ''y=,Action=UDV,UDVType=Update,UDVID=SFOR_137'')
  end
  object _OBJ15: TsfCommandButton
    Left = 259
    Top = 4
    Width = 74
    Height = 21
    Hint = ''Un-Hold''
    Caption = ''Un-Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Un-Hold,Priv={},Visible=''#39''{SUBJECT_STATUS=''#39#39''HOLD''#39#39''}''#39'',TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=SFOR'' +
        ''_137'')
  end
  object _OBJ19: TsfDBMemo
    Left = 397
    Top = 27
    Width = 390
    Height = 100
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 6
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object _OBJ20: TsfLabel
    Left = 494
    Top = 11
    Width = 60
    Height = 13
    Caption = ''Authority''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ21: TsfDBGrid
    Left = 397
    Top = 156
    Width = 390
    Height = 100
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 7
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ22: TsfLabel
    Left = 494
    Top = 134
    Width = 126
    Height = 17
    Caption = ''Task Group Work Scopes''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ23: TsfDBGrid
    Left = 3
    Top = 281
    Width = 390
    Height = 80
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 8
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ24: TsfLabel
    Left = 123
    Top = 259
    Width = 136
    Height = 17
    Caption = ''Task Group Customer''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ25: TsfDBGrid
    Left = 397
    Top = 281
    Width = 390
    Height = 80
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 9
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ26: TsfLabel
    Left = 494
    Top = 262
    Width = 189
    Height = 17
    Caption = ''Task Group Customer Exceptions''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ27: TsfUDVNavigator
    Left = 397
    Top = 133
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 10
  end
  object _OBJ28: TsfUDVNavigator
    Left = 397
    Top = 258
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 11
  end
  object _OBJ29: TsfUDVNavigator
    Left = 3
    Top = 258
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 12
  end
  object _OBJ30: TsfDBGrid
    Left = 2
    Top = 393
    Width = 390
    Height = 70
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 13
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ31: TsfUDVNavigator
    Left = 2
    Top = 370
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 14
  end
  object _OBJ32: TsfLabel
    Left = 123
    Top = 374
    Width = 182
    Height = 17
    Caption = ''Task Group Superior Network Activity''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ33: TsfDBGrid
    Left = 397
    Top = 393
    Width = 390
    Height = 70
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 15
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ34: TsfUDVNavigator
    Left = 397
    Top = 370
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 16
  end
  object _OBJ35: TsfLabel
    Left = 494
    Top = 373
    Width = 174
    Height = 17
    Caption = ''Task Group Sub Network Activity''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object PWUST_SFOR_SfplPlanSubjectSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SFOR_SfplPlanSubject_Sel''
    DeleteSqlId = ''PWUST_SFOR_SfplPlanSubjectDel''
    KeyFieldNames = ''subject_no''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ10~''
      ''_OBJ15~''
      ''_OBJ1~''
      ''_OBJ19~AUTHORITY'')
    ParamsSQLSourceName = ''@ToolScope''
    RefreshedSQLSourceName = ''MFI_PLG_SFOR_TASK_GROUPS_REV''
    PublishParams = True
    SelectedFields.Strings = (
      ''SUBJECT_NO~6~Task Group;No~N~N~N~N~N~N~~~~~Default~N~~''
      ''SUBJECT_REV~6~Task Group;Rev~N~N~N~N~N~N~~~~~Default~N~~''
      ''SUBJECT_STATUS~6~Task Group;Status~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''IS_DEFAULT~1~Auto;Include?~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      ''MANDATORY~1~Mandatory?~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''STATUS~1~ ~N~N~False~False~False~N~~@StdMarkup(STATUS)~~~Default'' +
        ''~N~~''
      
        ''TITLE~29~Task Group Description~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''UPDT_USERID~10~Update;User ID~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''TIME_STAMP~13~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''SUBJECT_NO~Edit''
      ''TITLE~Edit''
      ''STATUS~Edit'')
    InputUDVId = ''PWUST_550FF0D87F4241A0E05387971F0AD3EA''
    InsertUDVId = ''PWUST_550FF0D87F4241A0E05387971F0AD3EA''
    OtherCommands.Strings = (
      
        ''Desc=Task Group Copy,Priv=,Visible=,TagValue=,Action=UDV,UDVType'' +
        ''=Update,UDVID=SFOR_41''
      
        ''Desc=Obsolete Task Group,Priv=,Visible=,TagValue=,Action=UDV,UDV'' +
        ''Type=Update,UDVID=SFOR_55''
      
        ''Desc=Un-Obsolete Task Group,Priv=,Visible=,TagValue=,Action=UDV,'' +
        ''UDVType=Update,UDVID=SFOR_56''
      
        ''Desc=Task Group HOLD History Report,Priv=,Visible=,TagValue=,Act'' +
        ''ion=Invoke,Tool=TabReport,ReportToolId=SFOR_Subject_Hold_History'' +
        '',Params(''#39''PLAN_ID=:PLAN_ID,''#39'',''#39''PLAN_UPDT_NO=:PLAN_UPDT_NO,''#39'',SUBJEC'' +
        ''T_NO=:SUBJECT_NO)'')
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''SUBJECT_STATUS''
      ''IS_DEFAULT''
      ''MANDATORY''
      ''STATUS''
      ''TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfplPlanSubjectOperSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfplPlanSubjectOperSel''
    DeleteSqlId = ''SFOR_SfplPlanSubjectOperDel''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~6~Oper No~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPER_UPDT_NO~4~Oper Rev~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPER_TITLE~31~Oper Title~N~N~N~N~N~N~~~~~Default~N~~''
      ''UPDT_USERID~12~Update;User ID~N~N~N~N~N~N~~~~~Default~N~~''
      ''TIME_STAMP~10~Update;Time~N~N~N~N~N~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''TIME_STAMP~Edit'')
    InsertUDVId = ''SFOR_23''
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''OPER_UPDT_NO''
      ''OPER_TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object PWUST_TaskGroupCustomerExceptionSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_TaskGroupCustomerExceptionSel''
    DeleteSqlId = ''PWUST_SfplPlanSubjectCustomerExceptionDel''
    KeyFieldNames = ''subject_no,cust_id,cust_id_exception''
    LinkedControls.Strings = (
      ''_OBJ25~''
      ''_OBJ28~'')
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel,PWUST_TaskGroupCustomerSel''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''CUST_ID_EXCEPTION~10~Customer;ID~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~''
      
        ''CUST_DESC~40~Customer Description~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''TIME_STAMP~10~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''UPDT_USERID~Edit'')
    InsertUDVId = ''PWUST_57DD71F88E334927E05387971F0A0F77''
    DataDefinitions.Strings = (
      ''CUST_ID_EXCEPTION''
      ''CUST_DESC''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object PWUST_TaskGroupSubNetworkSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_TaskGroupSubNetworkSel''
    DeleteSqlId = ''PWUST_SfplPlanSubjectSubNetworkDel''
    KeyFieldNames = ''subject_no,SUPERIOR_NETWORK_ACT,sub_network_act''
    LinkedControls.Strings = (
      ''_OBJ33~''
      ''_OBJ34~'')
    ParamsSQLSourceName = ''PWUST_TaskGroupSuperiorNetworkSel,PWUST_SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SUB_NETWORK_ACT~6~Sub;Activity No~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      
        ''SUB_NETWORK_ACT_DESC~40~Sub Activity Description~N~N~False~False'' +
        ''~False~N~~~~~Default~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''TIME_STAMP~10~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''UPDT_USERID~Edit'')
    InsertUDVId = ''PWUST_57EB0288B84F5329E05387971F0A2208''
    DataDefinitions.Strings = (
      ''SUB_NETWORK_ACT''
      ''SUB_NETWORK_ACT_DESC''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object PWUST_TaskGroupSuperiorNetworkSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_TaskGroupSuperiorNetworkSel''
    DeleteSqlId = ''PWUST_SfplPlanSubjectSuperiorNetworkDel''
    KeyFieldNames = ''subject_no,SUPERIOR_NETWORK_ACT''
    LinkedControls.Strings = (
      ''_OBJ31~''
      ''_OBJ30~'')
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel,@Toolscope''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SUPERIOR_NETWORK_ACT~6~Activity;No~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~''
      
        ''SUPERIOR_NETWORK_ACT_DESC~40~Activity Description~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''TIME_STAMP~10~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''UPDT_USERID~Edit''
      ''SUPERIOR_NETWORK_ACT_DESC~Edit''
      ''SUPERIOR_NETWORK_ACT~Edit'')
    InsertUDVId = ''PWUST_57D611B4D730B4BDE05387971F0A0D7D''
    DataDefinitions.Strings = (
      ''SUPERIOR_NETWORK_ACT''
      ''SUPERIOR_NETWORK_ACT_DESC''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object PWUST_TaskGroupCustomerSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_TaskGroupCustomerSel''
    DeleteSqlId = ''PWUST_SfplPlanSubjectCustomerDel''
    KeyFieldNames = ''subject_no.cust_id''
    LinkedControls.Strings = (
      ''_OBJ23~''
      ''_OBJ29~'')
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''CUST_ID~10~Customer;ID~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''CUST_DESC~40~Customer Description~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''TIME_STAMP~10~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''CUST_DESC~Edit'')
    InsertUDVId = ''PWUST_5751138501317AA7E05387971F0A8915''
    DataDefinitions.Strings = (
      ''CUST_ID''
      ''CUST_DESC''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object PWUST_TaskGroupWorkScopeSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_TaskGroupWorkScopeSel''
    DeleteSqlId = ''PWUST_SfplPlanSubjectWorkScopeDel''
    KeyFieldNames = ''subject_no,workscope''
    LinkedControls.Strings = (
      ''_OBJ27~''
      ''_OBJ21~'')
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''WORKSCOPE~4~Workscope~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''WORKSCOPE_TITLE~40~Workscope Title~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''TIME_STAMP~10~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''UPDT_USERID~Edit''
      ''TIME_STAMP~Edit''
      ''WORKSCOPE~Edit'')
    InsertUDVId = ''PWUST_5658B4DE6360508DE05387971F0A7694''
    DataDefinitions.Strings = (
      ''WORKSCOPE''
      ''WORKSCOPE_TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
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

