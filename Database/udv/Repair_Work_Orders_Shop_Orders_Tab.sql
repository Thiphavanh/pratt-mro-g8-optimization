
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_83FDC067CD4ED3B0E05387971F0AF40D.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_83FDC067CD4ED3B0E05387971F0AF40D';
v_udv_tag sfcore_udv_lib.udv_tag%type :='Repair_Work_Orders_Shop_Orders_Tab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_310;Defect1378;Defect1589;defect 1602;1392,1940';
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
    Top = 35
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
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ5: TsfCommandButton
    Left = 165
    Top = 6
    Width = 125
    Height = 21
    Caption = ''Create Sub Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
  end
  object _OBJ6: TsfCommandButton
    Left = 296
    Top = 6
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
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Display Work Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Mfg Instructi'' +
        ''ons,ReportToolId=,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=E'' +
        ''DITMODES.Edit_PL)'')
  end
  object _OBJ7: TsfCommandButton
    Left = 428
    Top = 6
    Width = 125
    Height = 21
    Hint = ''Delete Order''
    Caption = ''Delete Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Delete Order,''#39''Priv={@haspriv(''#39#39''PW_OH_PARENT_ORDER_DELETE''#39#39'')'' +
        '' and ORDER_id <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@De'' +
        ''fault,Action=UDV,UDVType=MRInsert,UDVID=PWUST_65BC7AFD4184F9AFE0'' +
        ''5387971F0A5FCB'')
  end
  object Overhaul_Shop_Orders: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_REPAIR_SHOP_WORK_ORDERS_DISPATCH_BOTTOM''
    LinkedControls.Strings = (
      ''NAV''
      ''_OBJ2~''
      ''NAV~''
      ''_OBJ6~''
      ''_OBJ7~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''PW_ORDER_NO~15~Order No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PW_SUB_ORDER_TYPE~10~Sub Order Type~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      ''PLAN_NO~40~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PART_NO~40~Part No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''PLAN_REVISION~5~Plan Rev~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      ''PLAN_TITLE~40~Plan Title~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ORDER_STATUS~20~Order Status~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      ''ALT_STATUS~20~Alt Status~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ORDER_HOLD_STATUS~20~Hold Status~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''SALES_ORDER_RELEASE_DATE~15~Release Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''SALES_ORDER_RELEASED_BY~10~Released By~N~N~False~False~False~N~~'' +
        ''~~~Default~N~~~''
      ''ALTERED_BY~10~Altered by~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''REASON_FOR_CHANGE~10~Reason For Change~N~N~False~False~True~N~~~'' +
        ''~~Default~N~~~''
      
        ''ALT_COUNT~5~Alteration Number~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''SALES_ORDER~20~Sales order~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''OUTGOING_MATERIAL~20~Outgoing Material~N~N~True~False~False~N~~~'' +
        ''~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''NW_WORK_SCOPE~Edit''
      ''ALT_NO~Edit''
      ''ASASGND_WORK_LOC~Edit''
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
      ''SUB_NETWORK_ACTIVITY~Edit''
      ''WORK_SCOPE~Edit''
      ''CREATE_BY~Edit''
      ''CREATE_DATE~Edit''
      ''SECURITY_GROUP~Edit''
      ''NEW_SALES_ORDER~Edit''
      ''PARENT_ORDER_ID~Edit''
      ''NEW_ENGINE_TYPE~Edit''
      ''NEW_WORK_SCOPE~Edit''
      ''UCF_ORDER_VCH11~Edit''
      ''UCF_ORDER_VCH12~Edit''
      ''NEW_SAP_WORK_LOC~Edit''
      ''PART_NO~Edit''
      ''ORDER_STATUS~Edit''
      ''ALT_STATUS~Edit''
      ''ORDER_HOLD_STATUS~Edit''
      ''PLAN_TITLE~Edit''
      ''ALT_COUNT~Edit''
      ''ALTERED_BY~Edit''
      ''PLAN_NO~Edit'')
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
      
        ''Desc=Create Orders,Priv={},Visible={},TagValue=,FKey=,ParamsSrc='' +
        ''@Default,Action=UDV,UDVType=Insert,UDVID=PWUST_5D907818F49E6DB6E'' +
        ''05387971F0A5C0A''
      
        ''Desc=Create Sub Order,Priv={},Visible={},TagValue=,FKey=,ParamsS'' +
        ''rc=@Default''
      
        ''Desc=Export List to CSV,''#39''Priv={@HasPriv(''#39#39''EXPORT_DISPATCH''#39#39'') AND'' +
        '' ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defaul'' +
        ''t,Action=Print,Name=File.CSV,Params()'')
    DataDefinitions.Strings = (
      ''PW_ORDER_NO''
      ''PW_SUB_ORDER_TYPE''
      ''PLAN_NO''
      ''PART_NO''
      ''PLAN_REVISION''
      ''PLAN_TITLE''
      ''ORDER_STATUS''
      ''ALT_STATUS''
      ''ORDER_HOLD_STATUS''
      ''SALES_ORDER_RELEASE_DATE''
      ''ALTERED_BY''
      ''REASON_FOR_CHANGE''
      ''ALT_COUNT''
      ''SALES_ORDER''
      ''OUTGOING_MATERIAL'')
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

