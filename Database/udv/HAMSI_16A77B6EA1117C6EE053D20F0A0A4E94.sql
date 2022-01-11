set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36C8B4A07FC5D74E0400A0AD20F28E1';
v_udv_id varchar2(85) :='HAMSI_16A77B6EA1117C6EE053D20F0A0A4E94';
v_udv_tag varchar2(40) :='HAMSI_PLG_PaarReqSecGroupSghtPnl';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='HAMSI PLG Paar Req Sec Group Sght Pnl';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 600
  Height = 175
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
  AlignContents = acAsIs
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
  object navSecurityGroup: TsfUDVNavigator
    Left = 10
    Top = 2
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object grdSecurityGroup: TsfDBGrid
    Left = 10
    Top = 25
    Width = 575
    Height = 100
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
  object SecurityGroupProfile: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_SuggestedPermissionSecurityGroupProfileSel''
    DeleteSqlId = ''HAMSI_SUGGESTEDPERMISSIONPROFILEDEL''
    LinkedControls.Strings = (
      ''navSecurityGroup~''
      ''grdSecurityGroup~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''PMN_TYPE_ENTITY~10~Security Group~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''SECURITY_GROUP_DESC~18~Security Group Description~N~N~False~Fals'' +
        ''e~False~N~~~~~Default~Y~''
      
        ''PMN_EFF_FROM_DATE~14~Effective From Date~N~N~True~False~True~N~~'' +
        ''~~~Default~N~''
      
        ''PMN_EFF_THRU_DATE~14~Effective Thru Date~N~N~True~False~True~N~~'' +
        ''~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PMN_EFF_THRU_DATE~Edit'')
    InputUDVId = ''HAMSI_16F8DE2E1B317EC8E053D20F0A0A270B''
    InsertUDVId = ''HAMSI_16F8DE2E1B317EC8E053D20F0A0A270B''
    InsertEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DeleteEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''PMN_TYPE_ENTITY''
      ''SECURITY_GROUP_DESC''
      ''PMN_EFF_FROM_DATE''
      ''PMN_EFF_THRU_DATE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''SecurityGroupProfile''
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

