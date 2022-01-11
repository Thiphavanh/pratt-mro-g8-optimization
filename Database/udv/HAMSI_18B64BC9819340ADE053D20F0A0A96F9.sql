
set define off
prompt ---------------------------------------;
prompt Executing ... HAMSI_18B64BC9819340ADE053D20F0A0A96F9.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='';
v_udv_id sfcore_udv_lib.udv_id%type  :='HAMSI_18B64BC9819340ADE053D20F0A0A96F9';
v_udv_tag sfcore_udv_lib.udv_tag%type :='HAMSI_PAARRequestsForApprovalDisAuxUdv';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='PAAR Requests for Approval Dispatch Aux UDV';
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
  Height = 35
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
  AlignContents = acAsIs
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
  object btnSendApproval: TsfCommandButton
    Left = 125
    Top = 5
    Width = 96
    Height = 25
    Hint = ''Send Approval''
    Caption = ''Send Approval''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=Send Approval,''#39''Priv={@HasPriv(''#39#39''CAN_PROCESS_PAAR_APPROVAL''#39#39 +
        '') AND IS_OPEN_REQUEST = ''#39#39''Y''#39#39''}''#39'',Visible={},TagValue=Planning,FKe'' +
        ''y=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@ToolSc'' +
        ''ope,RefreshedSQLSourceName=,SqlID=PWUST_SENDPAAREXTERNALAPPROVAL'')
  end
  object btnDisplayPlan: TsfCommandButton
    Left = 10
    Top = 5
    Width = 96
    Height = 25
    Caption = ''Display Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Display Plan,Priv={},Visible={},TagValue=,FKey=,Action=Invo'' +
        ''ke,Tool=PrPlg Instructions,ReportToolId=,Params(''#39''PLAN_ID = :PLAN'' +
        ''_ID,''#39'',''#39''PLAN_VERSION = :PLAN_VERSION,''#39'',''#39''PLAN_REVISION = :PLAN_REV'' +
        ''ISION,''#39'',''#39''PLAN_ALTERATIONS = :PLAN_ALTERATIONS,''#39'',''#39''@InvokeToolMode'' +
        ''=EDITMODES.Edit_PL,''#39'',@InvokeTab=New)'')
  end
  object sqlRequestOpen: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_EXT_ApvlIsPlanRequestOpenQry''
    LinkedControls.Strings = (
      ''btnSendApproval~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''IS_OPEN_REQUEST~0~IS_OPEN_REQUEST~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~'')
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

