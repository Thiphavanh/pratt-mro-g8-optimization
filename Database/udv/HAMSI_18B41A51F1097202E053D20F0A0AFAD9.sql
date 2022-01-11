set define off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='HAMSI_18B41A51F1097202E053D20F0A0AFAD9';
v_udv_tag varchar2(40) :='HAMSI_PermissionsTabUdv';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='Permissions Tab UDV';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 700
  Height = 250
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
  object _OBJ1: TsfDBGrid
    Left = 2
    Top = 21
    Width = 320
    Height = 120
    TabStop = False
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object _OBJ2: TsfUDVNavigator
    Left = 2
    Top = 0
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 1
  end
  object _OBJ3: TsfCommandButton
    Left = 106
    Top = 0
    Width = 100
    Height = 21
    Hint = ''Set Permission''
    Caption = ''Set Permission''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (     
        ''Desc=Set Permission,Priv={},Visible={},TagValue=,FKey=,Action=Invoke,'' +
        ''Tool=PaarPermissionApproval,ReportToolId=,Params(''POUND39_HOLDER''REQUEST_ID = :REQUEST_ID,'' +
        POUND39_HOLDER'',''POUND39_HOLDER''@InvokeToolMode=EDITMODES.Edit,''POUND39_HOLDER'',@InvokeTab=New)'')
  end
  object PAARPermissionSource: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_PERMISSIONSTAB''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''REQUEST_ID~26~Request Id~N~N~False~False~False~N~~~~~Default~N~''
      ''AUTH_ID~26~Auth Id~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_ACTION~8~PMN;Action~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_STATUS~8~PMN;Status~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_TYPE~10~PMN;Type~N~N~False~False~False~N~~~~~Default~N~''      
      ''PMN_TYPE_ENTITY~18~PMN;Type Entity~N~N~False~False~False~N~~~~~Default~N~''      
      ''PMN_EFF_FROM_DATE~15~Eff;From Date~N~N~False~False~False~N~~~~~Default~N~''     
      ''PMN_EFF_THRU_DATE~15~Eff;Thru Date~N~N~False~False~False~N~~~~~Default~N~''
      ''SGHT_ID~26~Sght Id~N~N~False~False~False~N~~~~~Default~N~''     
      ''UPDT_USERID~10~Update User Id~N~N~False~False~False~N~~~~~Default~N~'' 
      ''TIME_STAMP~14~Update Time Stamp~N~N~False~False~False~N~~~~~Default~N~''      
      ''LAST_ACTION~10~Last Action~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PMN_TYPE_ENTITY~Edit''
      ''REQUEST_ID~Edit'')
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''AUTH_ID''
      ''PMN_ACTION''
      ''PMN_STATUS''
      ''PMN_TYPE''
      ''PMN_TYPE_ENTITY''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE''
      ''SGHT_ID''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PAARPermissionSource''
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

