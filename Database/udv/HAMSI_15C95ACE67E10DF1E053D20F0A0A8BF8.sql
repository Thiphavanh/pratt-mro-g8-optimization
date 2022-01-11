set define off
declare
v_folder_id varchar2(40) :='';
v_udv_id varchar2(85) :='HAMSI_15C95ACE67E10DF1E053D20F0A0A8BF8';
v_udv_tag varchar2(40) :='UTASPlanSecurityGroups';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='MFI_7AD9281BDC3C46BCAB6F91E16C82630D';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 200
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
  object SEC_GRP: TsfUDVNavigator
    Left = 2
    Top = 2
    Width = 153
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
  end
  object _OBJ1: TsfDBGrid
    Left = 2
    Top = 25
    Width = 490
    Height = 170
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clBlack
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
  end
  object _OBJ2: TsfCommandButton
    Left = 170
    Top = 2
    Width = 148
    Height = 21
    Caption = ''Linked Object SG Report''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=LinkedObjectSecurityGroup,Priv={},Visible={},TagValue=,FKey'' +
        ''=,Action=Invoke,Tool=Tabular Report,ReportToolId=HAMSI_SECURITYG'' +
        ''ROUPOBJECTS,Params(''POUND39_HOLDER''PLAN_ID=:PLAN_ID,PLAN_VERSION=:PLAN_VERSION,'' +
        ''PLAN_REVISION=:PLAN_REVISION,PLAN_ALTERATIONS=:PLAN_ALTERATIONS''POUND39_HOLDER +
        '')'')
  end
  object _OBJ4: TsfCommandButton
    Left = 323
    Top = 2
    Width = 100
    Height = 21
    Caption = ''Restrict Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Restrict Plan,''POUND39_HOLDER''Priv={@HasPriv(''POUND39_HOLDERPOUND39_HOLDER''CAN_CREATE_PAAR_REQUEST''POUND39_HOLDERPOUND39_HOLDER'') '' +
        ''AND (@GetQueryValue(HAMSI_PLG_PaarActiveRequestExistsQry) = ''POUND39_HOLDERPOUND39_HOLDER''N''POUND39_HOLDER +
        POUND39_HOLDER'')}''POUND39_HOLDER'',Visible={},TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceNa'' +
        ''me=@ToolScope,RefreshedSQLSourceName=,SqlID=HAMSI_PAARREQUESTINS'')
  end
  object PlanSecurityGroupSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_PlanSecurityGroupSel''
    DeleteSqlId = ''HAMSI_FND_WORKPLANSECURITYGROUPDELETE''
    LinkedControls.Strings = (
      ''SEC_GRP~''
      ''_OBJ1~''
      ''_OBJ2~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (      
      ''SECURITY_GROUP~15~Security Group~N~N~False~False~False~N~~~~~Default~N~''      
      ''SECURITY_GROUP_DESC~20~Description~N~N~False~False~False~N~~~~~Default~N~''     
      ''SECURITY_GROUP_TYPE~15~Security Group Type~N~N~False~False~False~N~~~~~Default~N~''    
      ''EXPIRATION_DATE~18~Expiration Date~N~N~False~False~False~N~~~~~Default~N~''   
      ''ISSUING_AGENCY~10~Issuing Agency~N~N~False~False~True~N~~~~~Default~N~''   
      ''OBSOLETE_RECORD_FLAG~6~Obsolete?~N~N~False~False~True~N~~~~~Default~N~''
      ''UPDT_USERID~15~Update User ID~N~N~False~False~True~N~~~~~Default~N~''
      ''TIME_STAMP~15~Update Time~N~N~False~False~True~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''INH_SEC_GRP_FLAG~Edit''
      ''SECURITY_GROUP~Edit''
      ''OBSOLETE_RECORD_FLAG~Edit'')
    TestParamValues.Strings = (
      ''PLAN_NO=0001642'')
    InputUDVId = ''HAMSI_15F118FF00894EB1E053D20F0A0AFCAA''
    InsertEnabledExpr = ''1=0''
    DeleteEnabledExpr = ''1=0''
    DataDefinitions.Strings = (
      ''SECURITY_GROUP''
      ''SECURITY_GROUP_DESC''
      ''SECURITY_GROUP_TYPE''
      ''EXPIRATION_DATE''
      ''ISSUING_AGENCY''
      ''OBSOLETE_RECORD_FLAG''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanSecurityGroupSel''
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

