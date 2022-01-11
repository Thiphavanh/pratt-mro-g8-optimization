
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5E28ABA56EBCB49AE05387971F0A8E87.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5E28ABA56EBCB49AE05387971F0A8E87';
v_udv_tag sfcore_udv_lib.udv_tag%type :='Overhaul_Work_Orders_All_Orders_Panel';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 427/692/800/809/468/1068/953;Defect1589;Defect1798';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
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
  object _OBJ1: TsfCommandButton
    Left = 186
    Top = 6
    Width = 125
    Height = 21
    Caption = ''Create Orders''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Create Orders,Priv={PW_OH_PARENT_ORDER_CREATE},Visible={},T'' +
        ''agValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVI'' +
        ''D=PWUST_5D907818F49E6DB6E05387971F0A5C0A'')
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
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ4: TsfCommandButton
    Left = 319
    Top = 6
    Width = 125
    Height = 21
    Hint = ''Release Orders''
    Caption = ''Release Orders''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Release Orders,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible=''#39''{ORDER_TY'' +
        ''PE<>''#39#39''SUPPLEMENTAL''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action'' +
        ''=UDV,UDVType=MRInsert,UDVID=MFI_7A2833F44F151DCCE04400144FA7B7D2'')
  end
  object _OBJ5: TsfCommandButton
    Left = 453
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
    TabOrder = 4
  end
  object _OBJ6: TsfCommandButton
    Left = 584
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
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Display Work Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Mfg Instructi'' +
        ''ons,ReportToolId=,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=E'' +
        ''DITMODES.Edit_PL)'')
  end
  object _OBJ7: TsfCommandButton
    Left = 716
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
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Delete Order,''#39''Priv={@haspriv(''#39#39''PW_OH_PARENT_ORDER_DELETE''#39#39'')'' +
        '' and ORDER_id <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@De'' +
        ''fault,Action=UDV,UDVType=MRInsert,UDVID=PWUST_65BC7AFD4184F9AFE0'' +
        ''5387971F0A5FCB'')
  end
  object Overhaul_Shop_Orders: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUSD_PWUST_SALES_ORDER_SEL_UDV''
    LinkedControls.Strings = (
      ''NAV''
      ''_OBJ2~''
      ''NAV~''
      ''_OBJ6~''
      ''_OBJ7~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_NO~10~Order No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PLAN_NO~10~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PART_NO~10~Part No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''PLAN_REVISION~5~Plan Rev~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      ''PLAN_TITLE~20~Plan Title~N~N~False~False~True~N~~~~~Default~N~~~''
      ''ALT_NO~10~Alt No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ORDER_STATUS~10~Order Status~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      
        ''ORDER_HOLD_STATUS~10~Hold Status~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''UCF_PLAN_VCH3~10~Module Code~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''WORK_SCOPE~10~Work Scope~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''SUPERIOR_NETWORK_ACTIVITY~10~Network Act~N~N~False~False~True~N~'' +
        ''~~~~Default~N~~~''
      
        ''SUB_NETWORK_ACTIVITY~10~Sub Network Act~N~N~False~False~True~N~~'' +
        ''~~~Default~N~~~''
      
        ''RELEASED_BY~10~Released By~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''CREATE_DATE~10~Create Date~N~N~False~False~True~N~~~~~Default~N~'' +
        ''~~''
      ''CREATE_BY~10~Create By~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''RELEASE_DATE~10~Release Date~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      ''ORDER_TYPE~10~Order Type~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''PARENT_PW_ORDER_NO~10~Parent Order~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''ALTERED_BY~10~Altered by~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''DATE_COMPLETED~10~Altered Date Completed~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''ASGND_WORK_LOC~0~Work Location~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''REASON_FOR_CHANGE~10~Reason For Change~N~N~False~False~True~N~~~'' +
        ''~~Default~N~~~''
      
        ''UCF_ORDER_VCH11~10~Incoming Sales Order~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH12~10~Outgoing Sales Order~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''NW_WORK_SCOPE~Edit''
      ''ASASGND_WORK_LOC~Edit''
      ''CREATE_BY_1~Edit''
      ''ENGINE_TYPE~Edit''
      ''SAP_WORK_LOC~Edit''
      ''ENGINE_MODEL1~Edit''
      ''PLANNED_ORDER_FLAG~Edit''
      ''NEW_SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''NEW_SUB_NETWORK_ACTIVITY~Edit''
      ''NEW_CUSTOMER~Edit''
      ''NEW_PLANNED_ORDER_FLAG~Edit''
      ''PW_ORDER_NO~Edit''
      ''UCF_ORDER_VCH12~Edit''
      ''PLAN_REVISION~Edit''
      ''UCF_PLAN_VCH3~Edit''
      ''SUPERIOR_NETWORK_ACTIVITY~Edit''
      ''SUB_NETWORK_ACTIVITY~Edit''
      ''RELEASED_BY~Edit''
      ''CREATE_DATE~Edit''
      ''RELEASE_DATE~Edit''
      ''ORDER_TYPE~Edit''
      ''REASON_FOR_CHANGE~Edit''
      ''PARENT_PW_ORDER_NO~Edit''
      ''DATE_COMPLETED~Edit''
      ''ORDER_NO~Edit'')
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
      ''ORDER_NO''
      ''PLAN_NO''
      ''PART_NO''
      ''PLAN_REVISION''
      ''PLAN_TITLE''
      ''ALT_NO''
      ''ORDER_STATUS''
      ''ORDER_HOLD_STATUS''
      ''UCF_PLAN_VCH3''
      ''WORK_SCOPE''
      ''SUPERIOR_NETWORK_ACTIVITY''
      ''SUB_NETWORK_ACTIVITY''
      ''RELEASED_BY''
      ''CREATE_DATE''
      ''CREATE_BY''
      ''RELEASE_DATE''
      ''ORDER_TYPE''
      ''PARENT_PW_ORDER_NO''
      ''ALTERED_BY''
      ''DATE_COMPLETED''
      ''ASGND_WORK_LOC''
      ''REASON_FOR_CHANGE''
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

