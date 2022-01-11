
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_64CACD5C19907752E05387971F0A9F3A.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_64CACD5C19907752E05387971F0A9F3A';
v_udv_tag sfcore_udv_lib.udv_tag%type :='Overhaul_Work_Orders_SHOP_Orders_Panel';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 692/Defect 809/Defect1068;Defect1589';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
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
    Width = 153
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Other commands ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object _OBJ2: TsfDBGrid
    Left = 7
    Top = 25
    Width = 450
    Height = 200
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ3: TsfCommandButton
    Left = 170
    Top = 7
    Width = 125
    Height = 21
    Hint = ''Display Work Order''
    Caption = ''Display Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Display Work Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Mfg Instructi'' +
        ''ons,ReportToolId=,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=E'' +
        ''DITMODES.Edit_PL)'')
  end
  object Overhaul_Shop_Orders_1: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SALES_ORDER_SEL_UDV_1''
    LinkedControls.Strings = (
      ''NAV''
      ''_OBJ2~''
      ''NAV~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_NO~10~Order No~N~N~False~False~True~N~~~~~Default~N~~~''
      ''PLAN_TITLE~20~Plan Title~N~N~False~False~True~N~~~~~Default~N~~~''
      ''ALT_NO~10~Alt No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ORDER_STATUS~10~Order Status~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      ''ALT_STATUS~10~Alt Status~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ORDER_HOLD_STATUS~10~Hold Status~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH3~10~Module Code~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''WORK_SCOPE~10~Work Scope~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''WORK_SCOPE_DESC~40~Work Scope Desc~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''SUPERIOR_NETWORK_ACTIVITY~10~Network Act~N~N~False~False~True~N~'' +
        ''~~~~Default~N~~~''
      
        ''SUB_NET_DESC~40~Network Act Desc~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''SUB_NETWORK_ACTIVITY~10~Sub Network Act~N~N~False~False~True~N~~'' +
        ''~~~Default~N~~~''
      
        ''SUP_NET_DESC~40~Sub Network Act Desc~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~~''
      
        ''RELEASED_BY~10~Released By~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''CREATE_DATE~10~Create Date~N~N~False~False~True~N~~~~~Default~N~'' +
        ''~~''
      ''CREATE_BY~10~Create By~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''RELEASE_DATE~10~Release Date~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      ''ORDER_TYPE~10~Order Type~N~N~False~False~True~N~~~~~Default~N~~~''
      ''PLAN_NO~10~Plan No~N~N~False~False~True~N~~~~~Default~N~~~''
      ''PART_NO~10~Part No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ASGND_WORK_LOC~10~Asgnd Work Loc~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~~~''
      ''ALTERED_BY~10~Altered by~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''REASON_FOR_CHANGE~10~Reason For Change~N~N~False~False~True~N~~~'' +
        ''~~Default~N~~~''
      
        ''UCF_ORDER_VCH11~10~Incoming Sales Order~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH12~10~Outgoing Sales Order~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''NW_WORK_SCOPE~Edit''
      ''PLAN_TITLE~Edit''
      ''ALT_NO~Edit''
      ''ORDER_STATUS~Edit''
      ''ASASGND_WORK_LOC~Edit''
      ''CREATE_DATE~Edit''
      ''CREATE_BY~Edit''
      ''RELEASE_DATE~Edit''
      ''CREATE_BY_1~Edit''
      ''ORDER_TYPE~Edit''
      ''ENGINE_TYPE~Edit''
      ''SAP_WORK_LOC~Edit''
      ''ENGINE_MODEL1~Edit''
      ''PLANNED_ORDER_FLAG~Edit''
      ''NEW_SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''NEW_SUB_NETWORK_ACTIVITY~Edit''
      ''NEW_CUSTOMER~Edit''
      ''NEW_PLANNED_ORDER_FLAG~Edit''
      ''NEW_ENGINE_MODEL~Edit''
      ''SUB_NETWORK_ACTIVITY~Edit''
      ''WORK_SCOPE~Edit''
      ''PART_NO~Edit''
      ''ALT_STATUS~Edit''
      ''ORDER_HOLD_STATUS~Edit''
      ''ORDER_NO~Edit''
      ''UCF_ORDER_VCH11~Edit'')
    TestParamValues.Strings = (
      ''SALES_ORDER=1''
      ''NEW_SALES_ORDER=1''
      ''NEW_ENGINE_TYPE=1''
      ''NEW_ENGINE_MODEL=1''
      ''NEW_SAP_WORK_LOC=1''
      ''NEW_WORK_SCOPE=1'')
    OtherCommands.Strings = (
      
        ''Desc=Display Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Mfg Instructions,R'' +
        ''eportToolId=,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=EDITMO'' +
        ''DES.Edit_PL)''
      
        ''Desc=Export List to CSV,''#39''Priv={@HasPriv(''#39#39''EXPORT_DISPATCH''#39#39'') AND'' +
        '' ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defaul'' +
        ''t,Action=Print,Name=File.CSV,Params()'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''PLAN_TITLE''
      ''ALT_NO''
      ''ORDER_STATUS''
      ''ALT_STATUS''
      ''ORDER_HOLD_STATUS''
      ''UCF_PLAN_VCH3''
      ''WORK_SCOPE''
      ''WORK_SCOPE_DESC''
      ''SUPERIOR_NETWORK_ACTIVITY''
      ''SUB_NET_DESC''
      ''SUB_NETWORK_ACTIVITY''
      ''SUP_NET_DESC''
      ''RELEASED_BY''
      ''CREATE_DATE''
      ''CREATE_BY''
      ''RELEASE_DATE''
      ''ORDER_TYPE''
      ''PLAN_NO''
      ''PART_NO''
      ''PARENT_ORDER_ID''
      ''ASGND_WORK_LOC''
      ''ALTERED_BY''
      ''REASON_FOR_CHANGE''
      ''SECURITY_GROUP''
      ''UCF_ORDER_VCH11''
      ''UCF_ORDER_VCH12'')
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

