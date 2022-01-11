
set define off
prompt ---------------------------------------;
prompt Executing ... HAMSI_169B981980F17C70E053D20F0A0A7CDD.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='';
v_udv_id sfcore_udv_lib.udv_id%type  :='HAMSI_169B981980F17C70E053D20F0A0A7CDD';
v_udv_tag sfcore_udv_lib.udv_tag%type :='HAMSI_PLG_PlanPaarPnl';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='HAMSI PLG Plan Paar Panel';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
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
    RowSelection = True
  end
  object btnDisplayRequest: TsfCommandButton
    Left = 100
    Top = 2
    Width = 110
    Height = 21
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
      
        ''Desc=Permissions Sought,''#39''Priv={@HasPriv(''#39#39''CAN_VIEW_PAAR_REQUEST''#39 +
        #39'') OR @HasPriv(''#39#39''CAN_CREATE_PAAR_REQUEST''#39#39'')}''#39'',Visible={},TagValu'' +
        ''e=,FKey=,Action=Invoke,Tool=PaarPermissionSght,ReportToolId=,Par'' +
        ''ams(''#39''REQUEST_ID = :REQUEST_ID,''#39'',''#39''@InvokeToolMode=EDITMODES.Edit,'' +
        #39'',@InvokeTab=New)'')
  end
  object btnSendRequest: TsfCommandButton
    Left = 375
    Top = 3
    Width = 110
    Height = 21
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
      
        ''Desc=Send for Review,''#39''Priv={@HasPriv(''#39#39''CAN_CREATE_PAAR_REQUEST''#39#39 +
        '') AND (REQUEST_STATUS=''#39#39''OPEN''#39#39'')}''#39'',Visible={},TagValue=,FKey=,Act'' +
        ''ion=ExecSql,ParamsSqlSourceName=sqlMain,RefreshedSQLSourceName=,'' +
        ''SqlID=HAMSI_FIREPAARREVIEW'')
  end
  object btnModifyRequest: TsfCommandButton
    Left = 240
    Top = 2
    Width = 110
    Height = 21
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
      
        ''Desc=Create New Request,''#39''Priv={@HasPriv(''#39#39''CAN_CREATE_PAAR_REQUES'' +
        ''T''#39#39'') AND (@GetQueryValue(HAMSI_PLG_PaarOpenRequestExistsQry) = ''#39 +
        #39''N''#39#39'')}''#39'',''#39''Visible={REV_STATUS = ''#39#39''PLAN COMPLETE''#39#39'' OR REV_STATUS ='' +
        '' ''#39#39''PLAN RELEASED''#39#39'' OR (@GetQueryValue(PWUST_CHECKCOMPLETEEXPTCOM'' +
        ''M) = ''#39#39''Y''#39#39'')}''#39'',TagValue=,FKey=,ParamsSrc=@ToolScope,Action=ExecSq'' +
        ''l,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=P'' +
        ''WUST_REQUESTPAARINSERT'')
  end
  object sqlMain: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_PLG_PaarReqHdrByPlanQry''
    LinkedControls.Strings = (
      ''grdMain~''
      ''navMain~''
      ''btnDisplayRequest~''
      ''btnSendRequest~''
      ''btnModifyRequest~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''REQUEST_ID~25~Request Id~N~N~False~False~False~N~~~~~Default~N~''
      ''REQUEST_STATUS~10~Status~N~N~False~False~False~N~~~~~Default~N~''
      ''PLAN_REV~6~Plan Rev~N~N~False~False~False~N~~~~~Default~N~''
      
        ''REQUEST_USERID~12~Request User Id~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''REQUEST_CREATION_DATE~14~Request Date~N~N~False~False~False~N~~~'' +
        ''~~Default~N~''
      
        ''REQUEST_REASON~25~Reason Text~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''REPLY_REQUIRED_DATE~14~Reply Required Date~N~N~False~False~False'' +
        ''~N~~~~~Default~N~''
      
        ''PRIOR_REQUEST_ID~25~Prior Request Id~N~N~False~False~False~N~~~~'' +
        ''~Default~N~''
      
        ''UPDT_USERID~12~Update User Id~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''TIME_STAMP~14~Update Timestamp~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''LAST_ACTION~10~Last Action~N~N~False~False~False~N~~~~~Default~N'' +
        ''~'')
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

