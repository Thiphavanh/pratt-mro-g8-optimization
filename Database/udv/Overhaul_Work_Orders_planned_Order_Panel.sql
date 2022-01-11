
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_651B6FA3E252720FE05387971F0A75FC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_651B6FA3E252720FE05387971F0A75FC';
v_udv_tag sfcore_udv_lib.udv_tag%type :='Overhaul_Work_Orders_planned_Order_Panel';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 427;Defect1589;defect380,defect 1844';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1000
  Height = 1000
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
  object NAV: TsfUDVNavigator
    Left = 7
    Top = 5
    Width = 191
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Other commands ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object _OBJ1: TsfCommandButton
    Left = 186
    Top = 4
    Width = 125
    Height = 21
    Hint = ''Move Planned Order''
    Caption = ''Move Planned Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Move Planned Order,Priv=''#39''{ORDER_NO <>''#39#39#39#39''}''#39'',Visible={},TagV'' +
        ''alue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=GroupInsert,Fi'' +
        ''eldName=PRIMERY_KEY,Delim=;,UDVID=PWUST_651ED675EE46050EE0538797'' +
        ''1F0A7AF8'')
  end
  object _OBJ2: TsfDBGrid
    Left = 7
    Top = 36
    Width = 1000
    Height = 300
    TabStop = False
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
    Hidden = ''False''
    RowSelection = True
  end
  object Overhaul_planned_orders: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLANNED_ORDERS_SEL''
    DeleteSqlId = ''PWUST_PLANNED_ORDERS_DEL''
    KeyFieldNames = ''PRIMERY_KEY''
    LinkedControls.Strings = (
      ''NAV''
      ''_OBJ2~''
      ''NAV~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_NO~20~Order No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PLAN_NO~20~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PLAN_REVISION~4~Plan Rev~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      ''PLAN_TITLE~20~Plan Tile~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SALES_ORDER~40~Sales Order~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''PART_NO~29~Part No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~10~Create By~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''TIME_STAMP~10~Create Date~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''ENGINE_TYPE~8~Engine Type~N~N~True~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''ENGINE_MODEL~18~Engine Model~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''SAPWORKLOC~4~SAP Work Loc~N~N~True~False~False~N~~~~~Default~N~~'' +
        ''~''
      ''WORK_SCOPE~10~Work Scope~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''WORK_SCOPE_DESC~40~Work Scope Desc~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''ITEM_NO_SEL~40~Part No~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''SUPERIORNET~4~Network No~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''SUPERIORNET_DESC~40~Network No Desc~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      ''SUBNET~8~Sub Network No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUBNET_DESC~40~Sub Network No Desc~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''CUSTOMER~10~Customer~N~N~True~False~False~N~~~~~Default~N~~~''
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''NW_WORK_SCOPE~Edit''
      ''ALT_NO~Edit''
      ''ALT_STATUS~Edit''
      ''ORDER_HOLD_STATUS~Edit''
      ''ASASGND_WORK_LOC~Edit''
      ''CREATE_DATE~Edit''
      ''CREATE_BY~Edit''
      ''RELEASE_DATE~Edit''
      ''CREATE_BY_1~Edit''
      ''ORDER_TYPE~Edit''
      ''PARENT_ORDER_ID~Edit''
      ''SAP_WORK_LOC~Edit''
      ''ENGINE_MODEL1~Edit''
      ''PLANNED_ORDER_FLAG~Edit''
      ''NEW_SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''NEW_SUB_NETWORK_ACTIVITY~Edit''
      ''NEW_CUSTOMER~Edit''
      ''NEW_PLANNED_ORDER_FLAG~Edit''
      ''NEW_ENGINE_MODEL~Edit''
      ''SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''SUB_NETWORK_ACTIVITY~Edit''
      ''ORDER_STATUS~Edit''
      ''CUSTOMER~Edit''
      ''SUBNET_DESC~Edit''
      ''SUPERIORNET_DESC~Edit''
      ''SAPWORKLOC~Edit''
      ''ENGINE_TYPE~Edit''
      ''TIME_STAMP~Edit''
      ''PLAN_TITLE~Edit''
      ''ORDER_NO~Edit''
      ''PART_NO~Edit'')
    TestParamValues.Strings = (
      ''SALES_ORDER=1''
      ''NEW_SALES_ORDER=1''
      ''NEW_ENGINE_TYPE=1''
      ''NEW_ENGINE_MODEL=1''
      ''NEW_SAP_WORK_LOC=1''
      ''NEW_WORK_SCOPE=1'')
    OtherCommands.Strings = (
      
        ''Desc=Move Planned Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=GroupInsert,F'' +
        ''ieldName=ORDER_NO,Delim=;,UDVID=PWUST_651ED675EE46050EE05387971F'' +
        ''0A7AF8''
      
        ''Desc=Export List to CSV,''#39''Priv={@HasPriv(''#39#39''EXPORT_DISPATCH''#39#39'') and'' +
        '' ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defaul'' +
        ''t,Action=Print,Name=File.CSV,Params()'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''PLAN_NO''
      ''PLAN_REVISION''
      ''PLAN_TITLE''
      ''SALES_ORDER''
      ''PART_NO''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''ENGINE_TYPE''
      ''ENGINE_MODEL''
      ''SAPWORKLOC''
      ''WORK_SCOPE''
      ''WORK_SCOPE_DESC''
      ''ITEM_NO_SEL''
      ''SUPERIORNET''
      ''SUPERIORNET_DESC''
      ''SUBNET''
      ''SUBNET_DESC''
      ''CUSTOMER''
      ''PLAN_ID'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''Overhaul_planned_orders''
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

