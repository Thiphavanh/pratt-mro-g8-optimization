set define off
declare
v_folder_id varchar2(40) :='HAMSI_0910B86429E34BFEE053D20F0A0A3E96';
v_udv_id varchar2(85) :='HAMSI_1681B1E5796B4EC2E053D20F0A0A659A';
v_udv_tag varchar2(40) :='HAMSI_PAARReviewPanel';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='Panel UDV for PAAR Review Process';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = -81
  Width = 750
  Height = 780
  AutoScroll = False
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctPanel
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
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
  object headerNavigator: TsfUDVNavigator
    Left = 8
    Top = 10
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 0
  end
  object requestId: TsfDBEdit
    Left = 148
    Top = 13
    Width = 190
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    Caption = ''Request Id:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object createdDate: TsfDBEdit
    Left = 428
    Top = 13
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    Caption = ''Created Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planNo: TsfDBEdit
    Left = 148
    Top = 40
    Width = 130
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    Caption = ''Plan No:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planVersion: TsfDBEdit
    Left = 363
    Top = 40
    Width = 60
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    Caption = ''Plan Version:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planRevision: TsfDBEdit
    Left = 488
    Top = 40
    Width = 60
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    Caption = ''Plan Rev:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planTitle: TsfDBEdit
    Left = 148
    Top = 66
    Width = 400
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    Caption = ''Plan Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemNo: TsfDBEdit
    Left = 148
    Top = 92
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    Caption = ''Item No:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemRev: TsfDBEdit
    Left = 307
    Top = 92
    Width = 56
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = [fsUnderline]
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = [fsUnderline]
    Hidden = False
  end
  object subType: TsfDBEdit
    Left = 427
    Top = 92
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    Caption = ''Subtype:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemTitle: TsfDBEdit
    Left = 148
    Top = 117
    Width = 400
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 10
    Caption = ''Item Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object requestReason: TsfDBMemo
    Left = 148
    Top = 141
    Width = 400
    Height = 91
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 11
    MemoCaption = ''Request Reason''
    CaptionPos = cpLeft
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object profileBevel: TsfBevel
    Left = 21
    Top = 272
    Width = 700
    Height = 460
    Shape = bsFrame
  end
  object usersGrid: TsfDBGrid
    Left = 37
    Top = 343
    Width = 650
    Height = 100
    TabStop = False
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 12
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object suggestedActionLable: TsfLabel
    Left = 290
    Top = 283
    Width = 205
    Height = 19
    Caption = ''Suggested Permission Profile''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object usersLabel: TsfLabel
    Left = 325
    Top = 312
    Width = 32
    Height = 16
    Caption = ''Users''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object userNavigator: TsfUDVNavigator
    Left = 37
    Top = 318
    Width = 115
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 13
  end
  object locationLabel: TsfLabel
    Left = 320
    Top = 451
    Width = 53
    Height = 16
    Caption = ''Locations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object locationGrid: TsfDBGrid
    Left = 37
    Top = 478
    Width = 650
    Height = 100
    TabStop = False
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 14
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object locationNavigator: TsfUDVNavigator
    Left = 37
    Top = 453
    Width = 115
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 15
  end
  object securityGroups: TsfLabel
    Left = 305
    Top = 593
    Width = 90
    Height = 16
    Caption = ''Security Groups''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object securityGroupsGrid: TsfDBGrid
    Left = 37
    Top = 620
    Width = 650
    Height = 100
    TabStop = False
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 16
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object securityGroupNavigator: TsfUDVNavigator
    Left = 37
    Top = 595
    Width = 115
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 17
  end
  object slashLabel: TsfLabel
    Left = 285
    Top = 95
    Width = 4
    Height = 13
    Caption = ''/''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object requestReplyDate: TsfDBEdit
    Left = 148
    Top = 237
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 18
    Caption = ''Reply Required Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object requestPAARReview: TsfCommandButton
    Left = 8
    Top = 37
    Width = 120
    Height = 21
    Hint = ''Request PAAR Review''
    Caption = ''Request PAAR Review''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 19
    OtherCommands.Strings = (
      
        ''Desc=Request PAAR Review,Priv=''POUND39_HOLDER''{REQUEST_ID<>''POUND39_HOLDERPOUND39_HOLDERPOUND39_HOLDERPOUND39_HOLDER''}''POUND39_HOLDER'',Visible={1<>1'' +
        ''},TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=LatestReque'' +
        ''stSel,RefreshedSQLSourceName=,SqlID=HAMSI_FIREPAARREVIEW'')
  end
  object LatestRequestSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_SuggestedPermissionLatestRequestInfoSel''
    LinkedControls.Strings = (
      ''headerNavigator~''
      ''requestId~REQUEST_ID''
      ''createdDate~REQUEST_CREATION_DATE''
      ''planNo~PLAN_NO''
      ''planVersion~PLAN_VERSION''
      ''planRevision~PLAN_REVISION''
      ''planTitle~PLAN_TITLE''
      ''itemNo~PART_NO''
      ''itemRev~PART_CHG''
      ''subType~ITEM_SUBTYPE''
      ''itemTitle~PART_TITLE''
      ''requestReason~REQUEST_REASON''
      ''requestReplyDate~REPLY_REQUIRED_DATE''
      ''requestPAARReview~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''REQUEST_ID~0~REQUEST_ID~N~N~False~False~False~N~~~~~Default~N~''
      
        ''REQUEST_CREATION_DATE~0~REQUEST_CREATION_DATE~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~''
      
        ''REQUEST_REASON~0~REQUEST_REASON~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''REPLY_REQUIRED_DATE~0~REPLY_REQUIRED_DATE~N~N~False~False~False~'' +
        ''N~~~~~Default~N~''
      ''PART_TITLE~0~PART_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      ''PLAN_NO~0~PLAN_NO~N~N~False~False~False~N~~~~~Default~N~''
      
        ''PLAN_VERSION~0~PLAN_VERSION~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      
        ''PLAN_REVISION~0~PLAN_REVISION~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_CHG~0~PART_CHG~N~N~False~False~False~N~~~~~Default~N~''
      ''PLAN_TITLE~0~PLAN_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~'')
    SelectedFieldsEditControl.Strings = (
      ''REQUEST_ID~Edit''
      ''REQUEST_CREATION_DATE~Edit'')
    InputUDVId = ''HAMSI_170C85D8E6FD4BC9E053D20F0A0AE19C''
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''LatestRequestSel''
  end
  object UserProfile: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_SuggestedPermissionUserProfileSel''
    DeleteSqlId = ''HAMSI_SUGGESTEDPERMISSIONPROFILEDEL''
    LinkedControls.Strings = (
      ''usersGrid~''
      ''userNavigator~'')
    ParamsSQLSourceName = ''LatestRequestSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''PMN_TYPE_ENTITY~10~User Id~N~N~False~False~False~N~~~~~Default~N'' +
        ''~''
      ''NAME~18~User Description~N~N~False~False~False~N~~~~~Default~Y''
      
        ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~''
      
        ''PMN_EFF_THRU_DATE~14~Effective Thrru Date~N~N~False~False~False~'' +
        ''N~~~~~Default~N~'')
    InputUDVId = ''HAMSI_169690B190C06C4CE053D20F0A0AE7C0''
    InsertUDVId = ''HAMSI_169690B190C06C4CE053D20F0A0AE7C0''
    InsertEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DeleteEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''PMN_TYPE_ENTITY''
      ''NAME''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''UserProfile''
  end
  object LocationProfile: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_SuggestedPermissionLocationProfileSel''
    DeleteSqlId = ''HAMSI_SUGGESTEDPERMISSIONPROFILEDEL''
    LinkedControls.Strings = (
      ''locationGrid~''
      ''locationNavigator~'')
    ParamsSQLSourceName = ''LatestRequestSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''PMN_TYPE_ENTITY~10~Location~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      
        ''LOC_TITLE~18~Location Description~N~N~False~False~False~N~~~~~De'' +
        ''fault~Y~''
      
        ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~''
      
        ''PMN_EFF_THRU_DATE~14~Effective Thru Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PMN_TYPE_ENTITY~Edit''
      ''LOC_TITLE~Edit''
      ''PMN_EFF_FROM_DATE~Edit'')
    InputUDVId = ''HAMSI_16F88F81A8F477B1E053D20F0A0AF30C''
    InsertUDVId = ''HAMSI_16F88F81A8F477B1E053D20F0A0AF30C''
    InsertEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DeleteEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''SGHT_ID''
      ''REQUEST_ID''
      ''AUTH_ID''
      ''PMN_TYPE_ENTITY''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE''
      ''LOC_TITLE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''LocationProfile''
  end
  object SecurityGroupProfile: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_SuggestedPermissionSecurityGroupProfileSel''
    DeleteSqlId = ''HAMSI_SUGGESTEDPERMISSIONPROFILEDEL''
    LinkedControls.Strings = (
      ''securityGroupsGrid~''
      ''securityGroupNavigator~'')
    ParamsSQLSourceName = ''LatestRequestSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''PMN_TYPE_ENTITY~10~Security Group~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''SECURITY_GROUP_DESC~18~Security Group Description~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~Y~''
      
        ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~''
      
        ''PMN_EFF_THRU_DATE~14~Effective Thru Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PMN_EFF_THRU_DATE~Edit'')
    InputUDVId = ''HAMSI_16F8DE2E1B317EC8E053D20F0A0A270B''
    InsertUDVId = ''HAMSI_16F8DE2E1B317EC8E053D20F0A0A270B''
    InsertEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DeleteEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''SGHT_ID''
      ''AUTH_ID''
      ''PMN_TYPE_ENTITY''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE''
      ''SECURITY_GROUP_DESC'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''SecurityGroupProfile''
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
  update sfcore_udv_lib
  set udv_definition=replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_desc = v_udv_desc,state=v_state,object_rev=v_object_rev,owner_group=v_owner_group,
      udv_type=v_udv_type,stype=v_stype
  where udv_id=v_udv_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
values(v_udv_id,v_folder_id,user,sysdate);
commit;
end if;
exception 
when others then null;
end;
end;
/

set define on

