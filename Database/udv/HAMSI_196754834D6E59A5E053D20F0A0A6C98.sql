set define off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='HAMSI_196754834D6E59A5E053D20F0A0A6C98';
v_udv_tag varchar2(40) :='HAMSI_WID_OrderPaarPnl';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='HAMSI WID Order Paar Panel';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20130124~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
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
  object navMain: TsfUDVNavigator
    Left = 2
    Top = 2
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object grdMain: TsfDBGrid
    Left = 2
    Top = 25
    Width = 480
    Height = 260
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object btnDisplayRequest: TsfCommandButton
    Left = 100
    Top = 2
    Width = 110
    Height = 21
    Hint = ''Permissions Sought''
    Caption = ''Display Request''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Permissions Sought,''POUND39_HOLDER''Priv={@HasPriv(''POUND39_HOLDERPOUND39_HOLDER''CAN_VIEW_PAAR_REQUEST''POUND39_HOLDER +
        POUND39_HOLDER'') OR @HasPriv(''POUND39_HOLDERPOUND39_HOLDER''CAN_CREATE_PAAR_REQUEST''POUND39_HOLDERPOUND39_HOLDER'')}''POUND39_HOLDER'',Visible={},TagValu'' +
        ''e=,FKey=,Action=Invoke,Tool=WIDPaarPermissionSght,ReportToolId=,'' +
        ''Params(''POUND39_HOLDER''REQUEST_ID = :REQUEST_ID,''POUND39_HOLDER'',''POUND39_HOLDER''@InvokeToolMode=EDITMODES.Edit,''POUND39_HOLDER'',@InvokeTab=New)'')
  end
  object btnSendRequest: TsfCommandButton
    Left = 375
    Top = 3
    Width = 110
    Height = 21
    Hint = ''Send for Review''
    Caption = ''Send Request''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Send for Review,''POUND39_HOLDER''Priv={@HasPriv(''POUND39_HOLDERPOUND39_HOLDER''CAN_CREATE_PAAR_REQUEST''POUND39_HOLDERPOUND39_HOLDER +
        '') AND (REQUEST_STATUS=''POUND39_HOLDERPOUND39_HOLDER''OPEN''POUND39_HOLDERPOUND39_HOLDER'')}''POUND39_HOLDER'',Visible={},TagValue=,FKey=,Act'' +
        ''ion=ExecSql,ParamsSqlSourceName=sqlMain,RefreshedSQLSourceName=,SqlID=HAMSI_WID_FIREPAARREVIEW'')
  end
  object btnModifyRequest: TsfCommandButton
    Left = 240
    Top = 2
    Width = 110
    Height = 21
    Hint = ''Create New Request''
    Caption = ''New Request''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Create New Request,''POUND39_HOLDER''Priv={@HasPriv(''POUND39_HOLDERPOUND39_HOLDER''CAN_CREATE_PAAR_REQUES'' +
        ''T''POUND39_HOLDERPOUND39_HOLDER'') AND (@GetQueryValue(HAMSI_WID_PAAROPENREQUESTEXISTSQRY) = ''POUND39_HOLDER +
        POUND39_HOLDER''N''POUND39_HOLDERPOUND39_HOLDER'')}''POUND39_HOLDER'',Visible={},TagValue=PAAR_REQUESTS_TAB,'' +
        ''FKey=,Action=ExecSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=HAMSI_WID_PAARREQUESTINS'')
  end
  object sqlMain: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PaarReqHdrByOrderQry''
    LinkedControls.Strings = (
      ''grdMain~''
      ''navMain~''
      ''btnDisplayRequest''
      ''btnSendRequest~''
      ''btnModifyRequest~''
      ''btnDisplayRequest~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''REQUEST_ID~25~Request Id~N~N~False~False~False~N~~~~~Default~N~''
      ''REQUEST_STATUS~10~Status~N~N~False~False~False~N~~~~~Default~N~''      
      ''REQUEST_USERID~12~Request User Id~N~N~False~False~False~N~~~~~Default~N~''    
      ''REQUEST_CREATION_DATE~14~Request Date~N~N~False~False~False~N~~~~~Default~N~''   
      ''REQUEST_REASON~25~Reason Text~N~N~False~False~False~N~~~~~Default~N~''   
      ''REPLY_REQUIRED_DATE~14~Reply Required Date~N~N~False~False~False~N~~~~~Default~N~''    
      ''PRIOR_REQUEST_ID~25~Prior Request Id~N~N~False~False~False~N~~~~~Default~N~''    
      ''UPDT_USERID~12~Update User Id~N~N~False~False~False~N~~~~~Default~N~''    
      ''TIME_STAMP~14~Update Timestamp~N~N~False~False~False~N~~~~~Default~N~''    
      ''LAST_ACTION~10~Last Action~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''REQUEST_ID~Edit'')
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''REQUEST_STATUS''
      ''REQUEST_USERID''
      ''REQUEST_CREATION_DATE''
      ''REQUEST_REASON''
      ''REPLY_REQUIRED_DATE''
      ''PRIOR_REQUEST_ID''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION'')
    ConsolidatedQuery = False
  end
  object sqlOpenRequestExists: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PaarOpenRequestExistsQry''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object PAARRequestExists: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PAARREQUESTEXISTS''
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

