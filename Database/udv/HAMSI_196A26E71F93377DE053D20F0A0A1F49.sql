set define off
set sqlprefix off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='HAMSI_196A26E71F93377DE053D20F0A0A1F49';
v_udv_tag varchar2(40) :='HAMSI_WID_PAARRequestAuxUdv';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='Auxilary UDV for WID PAAR Request Dispatch';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20130124~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 475
  Height = 30
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
  object sendPAARRequest: TsfCommandButton
    Left = 317
    Top = 4
    Width = 131
    Height = 21
    Hint = ''Send PAAR Request''
    Caption = ''Send PAAR Request''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=Send PAAR Request,Priv={},Visible=''#39''{REQUEST_STATUS=''#39#39''OPEN''#39#39 +
        ''}''#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=@ToolScope'' +
        '',RefreshedSQLSourceName=,SqlID=HAMSI_WID_FIREPAARREVIEW'')
  end
  object _OBJ2: TsfCommandButton
    Left = 43
    Top = 4
    Width = 119
    Height = 21
    Caption = ''Display Order''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Display Order,Priv={},Visible={},TagValue=,FKey=,Action=Inv'' +
        ''oke,Tool=Mfg Instructions,ReportToolId=,Params(''#39''ORDER_ID = :ORDE'' +
        ''R_ID,''#39'',@InvokeTab=New)'')
  end
  object btnDisplayRequest: TsfCommandButton
    Left = 174
    Top = 4
    Width = 131
    Height = 21
    Hint = ''Other commands''
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
      
        ''Desc=Display Request,''#39''Priv={@HasPriv(''#39#39''CAN_VIEW_PAAR_REQUEST''#39#39'') '' +
        ''OR @HasPriv(''#39#39''CAN_CREATE_PAAR_REQUEST''#39#39'')}''#39'',Visible={},TagValue=,'' +
        ''FKey=,Action=Invoke,Tool=WIDPaarPermissionSght,ReportToolId=,Par'' +
        ''ams(''#39''REQUEST_ID = :REQUEST_ID,''#39'',''#39''@InvokeToolMode=EDITMODES.Edit,'' +
        #39'',@InvokeTab=New)'')
  end
  object PAARRequestSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PAARPERMSGHTSCOPEQRY''
    LinkedControls.Strings = (
      ''sendPAARRequest'')
    ParamsSQLSourceName = ''@ToolScope''
    SelectedFields.Strings = (
      
        ''REQUEST_STATUS~0~REQUEST_STATUS~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~'')
    ConsolidatedQuery = False
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

