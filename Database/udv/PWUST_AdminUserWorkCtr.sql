
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_813AB39C17B80E6DE05387971F0A48E4.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_813AB39C17B80E6DE05387971F0A48E4';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_AdminUserWorkCtr';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_USR_201 Defect 1119';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 664
  Height = 279
  AutoScroll = False
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
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 26
    Width = 490
    Height = 137
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 4
    Top = 4
    Width = 134
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Other commands ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object AdminUserWorkCtr: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''AdminUserWorkCtr''
    DeleteSqlId = ''AdminUserWorkCtrDel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''NEW_ASGND_WORK_LOC~20~Assigned;Work Loc~N~N~N~N~N~N~~~~~Default~'' +
        ''N~''
      
        ''NEW_ASGND_WORK_DEPT~20~Assigned;Work Dept~N~N~N~N~N~N~~~~~Defaul'' +
        ''t~N~''
      
        ''NEW_ASGND_WORK_CENTER~10~Assigned;Work Center~N~N~N~N~N~N~~~~~De'' +
        ''fault~N~''
      ''NEW_SHIFT~5~Shift~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''NEW_ASGND_WORK_CENTER~Edit''
      ''NEW_SHIFT~Edit'')
    InputUDVId = ''MFI_3297''
    InsertUDVId = ''MFI_3297''
    InsUpdDelUDVId = ''MFI_3297''
    InsertEnabledExpr = ''@HasPriv(''#39''PW_USER_WORK_LOC_CHG''#39'')''
    UpdateEnabledExpr = ''@HasPriv(''#39''PW_USER_WORK_LOC_CHG''#39'')''
    DeleteEnabledExpr = ''@HasPriv(''#39''PW_USER_WORK_LOC_CHG''#39'')''
    InsUpdDelEnabledExpr = ''@HasPriv(''#39''PW_USER_WORK_LOC_CHG''#39'')''
    OtherCommands.Strings = (
      
        ''Desc=User Assigned Work Loc/Dept/Center History,Priv={},Visible='' +
        ''{},TagValue=,FKey=,Action=Invoke,Tool=Tabular Report,ReportToolI'' +
        ''d=MFI_FND_AdminUserWorkLocDeptCtrHistory,Params(USERID=:USERID)'')
    DataDefinitions.Strings = (
      ''NEW_ASGND_WORK_LOC''
      ''NEW_ASGND_WORK_DEPT''
      ''NEW_ASGND_WORK_CENTER''
      ''SHIFT'')
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

