
set define off
prompt ---------------------------------------;
prompt Executing ... MFI_9BD2F2E3BFB7455F8B513CEB3CAF83AB.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='MF1_1000440';
v_udv_id sfcore_udv_lib.udv_id%type  :='MFI_9BD2F2E3BFB7455F8B513CEB3CAF83AB';
v_udv_tag sfcore_udv_lib.udv_tag%type :='StdOperSecurityGroupTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Standard operation security tab, Defect1354';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 200
  HelpContext = ''''
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
  AlignContents = acHorizontal
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
  object SEC_GRP: TsfUDVNavigator
    Left = 4
    Top = 6
    Width = 153
    Height = 18
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
    Left = 4
    Top = 26
    Width = 481
    Height = 120
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
  object StdOperSecurityGroupSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_StdOperSecurityGroupSel''
    DeleteSqlId = ''MFI_StdOperSecurityGroupDelete''
    LinkedControls.Strings = (
      ''SEC_GRP~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (

        ''SECURITY_GROUP~15~Security Group~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~''

        ''SECURITY_GROUP_DESC~20~Description~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~''

        ''SECURITY_GROUP_TYPE~15~Security Group Type~N~N~False~False~False'' +
        ''~N~~~~~Default~N~''

        ''ISSUING_AGENCY~15~Issuing Agency~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~''

        ''EXPIRATION_DATE~18~Expiration Date~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~''

        ''OBSOLETE_RECORD_FLAG~6~Obsolete?~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~''

        ''UPDT_USERID~10~Update;User ID~N~N~False~False~True~N~~~~~Default'' +
        ''~N~''
      ''TIME_STAMP~18~Update;Time~N~N~False~False~True~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''TIME_STAMP~Edit''
      ''OBSOLETE_RECORD_FLAG~Edit''
      ''ISSUING_AGENCY~Edit''
      ''SECURITY_GROUP_TYPE~Edit''
      ''SECURITY_GROUP~Edit'')
    InputUDVId = ''MFI_FDCFF595E43E42FAB5EFE8B3986FBC2C''
    InsertEnabledExpr = ''STDOPER_OBJECT_ID<>''#39#39
    DataDefinitions.Strings = (
      ''SECURITY_GROUP''
      ''SECURITY_GROUP_TYPE''
      ''ISSUING_AGENCY''
      ''OBSOLETE_RECORD_FLAG''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''StdOperSecurityGroupSel''
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

