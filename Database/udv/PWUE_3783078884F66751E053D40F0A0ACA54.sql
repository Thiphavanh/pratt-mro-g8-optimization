set define off
set sqlprefix off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='PWUE_3783078884F66751E053D40F0A0ACA54';
v_udv_tag varchar2(40) :='PWUE_PLG_PaarReqAuxPnl';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='PWUE PLG Paar Req Aux Pnl';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20130124~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 750
  Height = 40
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctPanel
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acAsIs
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
  object btnSendRequest: TsfCommandButton
    Left = 250
    Top = 6
    Width = 100
    Height = 25
    Caption = ''Send Request''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=Send Request,Priv=''#39''{REQUEST_STATUS=''#39#39''OPEN''#39#39''}''#39'',Visible={},TagValue=,FKey=,'' +
        ''Action=ExecSql,ParamsSqlSourceName=srcPlgPaarHdrAuxQry,RefreshedSQLSourceName=,SqlID=HAMSI_FIREPAARREVIEW'')
  end
  object btnDisplayPlan: TsfCommandButton
    Left = 85
    Top = 6
    Width = 100
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
      
        ''Desc=Display Work Plan,Priv={},Visible={},TagValue=,FKey=,Action=Invoke,Tool=PrPlg Instructions,ReportToolId=,'' +
        ''Params(''#39''PLAN_ID = :PLAN_ID,''#39'',''#39''PLAN_VERSION = :PLAN_VERSION,''#39'',''#39''PLAN_REVISION = :PLA'' +
        ''N_REVISION,''#39'',''#39''PLAN_ALTERATIONS = :PLAN_ALTERATIONS,''#39'',''#39''@InvokeToolMode=EDITMODES.Edit_PL,''#39'',@InvokeTab=New)'')
  end
  object srcPlgPaarHdrAuxQry: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_PLG_PaarReqHdrByIdQry''
    LinkedControls.Strings = (
      ''btnDisplayPlan~''
      ''btnSendRequest~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''srcPlgPaarHdrAuxQry''
  end
end';
v_iclob clob;
begin
v_iclob := p1;
begin
insert into sfcore_udv_lib(udv_id,udv_tag,updt_userid,time_stamp,udv_type,udv_desc,state,load_ref,tool_version,object_rev,owner_group,udv_definition)
    values(v_udv_id,v_udv_tag,user,sysdate,v_udv_type,v_udv_desc,v_state,v_load_ref,v_tool_version,v_object_rev,v_owner_group,
    replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),chr(18)||chr(18),chr(39)||chr(39)));
commit;
exception when dup_val_on_index then
  update sfcore_udv_lib
  set udv_definition=replace(replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),chr(10),chr(13)||chr(10)),chr(18)||chr(18),chr(39)||chr(39)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_type=v_udv_type
  where udv_id=v_udv_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
values(v_udv_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on
set sqlprefix on

