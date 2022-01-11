set define off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='HAMSI_16A615CEFF74529BE053D20F0A0A1076';
v_udv_tag varchar2(40) :='HAMSI_PAARPermissionsTab';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='PAAR Permission Tab';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 890
  Height = 200
  Caption = ''Permission Tab''
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
  AutoScrollbars = False
  DynamicFieldAttributes = False
  BottomMargin = -1
  object _OBJ1: TsfDBGrid
    Left = 5
    Top = 17
    Width = 435
    Height = 138
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
  object _OBJ2: TsfDBGrid
    Left = 445
    Top = 17
    Width = 431
    Height = 138
    TabStop = False
    ParentFont = False
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
  object _OBJ3: TsfUDVNavigator
    Left = 5
    Top = 4
    Width = 134
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 2
  end
  object _OBJ4: TsfUDVNavigator
    Left = 445
    Top = 4
    Width = 134
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 3
  end
  object _OBJ5: TsfLabel
    Left = 143
    Top = 6
    Width = 87
    Height = 13
    Caption = ''Sought Permission''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object _OBJ6: TsfLabel
    Left = 625
    Top = 6
    Width = 105
    Height = 13
    Caption = ''Authorized Permission''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object SoughtPermission: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_PAARSOUGHTPERMISIONREQUESTSEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    SelectedFields.Strings = (
      ''REQUEST_ID~26~Request ID~N~N~True~False~False~N~~~~~Default~N~''
      ''SGHT_ID~26~Sought ID~N~N~True~False~False~N~~~~~Default~N~''
      ''PMN_ACTION~10~Action~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_TYPE~10~Type~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_TYPE_ENTITY~10~Value~N~N~False~False~False~N~~~~~Default~N~''  
      ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~False~False~False~N~~~~~Default~N~''      
      ''PMN_EFF_THRU_DATE~14~Effective Thru Date~N~N~False~False~False~N~~~~~Default~N~''
      ''AUTH_ID~26~Auth ID~N~N~True~False~False~N~~~~~Default~N~''    
      ''UPDT_USERID~10~Update User ID~N~N~False~False~False~N~~~~~Default~N~''
      ''TIME_STAMP~12~Update Time~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PMN_EFF_THRU_DATE~Edit''
      ''PMN_TYPE~Edit''
      ''PMN_ACTION~Edit'')
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''SGHT_ID''
      ''PMN_ACTION''
      ''PMN_TYPE''
      ''PMN_TYPE_ENTITY''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE''
      ''AUTH_ID''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
  end
  object AuthorisedPermission: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_PAARAUTHORISEDPERMISIONSEL''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''@ToolScope''
    SelectedFields.Strings = (
      ''REQUEST_ID~26~Request ID~N~N~True~False~False~N~~~~~Default~N~''
      ''SGHT_ID~26~Sought ID~N~N~True~False~False~N~~~~~Default~N~''
      ''PAAR_RESULT~35~PAAR Review Result~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_TYPE~10~Type~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_TYPE_ENTITY~10~Value~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~False~False~False~N~~~~~Default~N~''  
      ''PMN_EFF_THRU_DATE~14~Effective Thru Date~N~N~False~False~False~N~~~~~Default~N~''
      ''PMN_ACTION~6~Action~N~N~True~False~False~N~~~~~Default~N~''
      ''AUTH_ID~26~Auth ID~N~N~True~False~False~N~~~~~Default~N~''
      ''TIME_STAMP~12~Update Time~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''REQUEST_ID~Edit''
      ''TIME_STAMP~Edit'')
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''SGHT_ID''
      ''PAAR_RESULT''
      ''PMN_ACTION''
      ''PMN_TYPE''
      ''PMN_TYPE_ENTITY''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE''
      ''AUTH_ID''
      ''UPDT_USERID''
      ''TIME_STAMP'')
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

