
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_BE2657D2DE40099EE05387971F0AE7B5.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_BE2657D2DE40099EE05387971F0AE7B5';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_WORK_LOC_CERT';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 1840 Work Location Cert';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 849
  Height = 542
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create Work Order''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
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
  object _OBJ1: TsfUDVNavigator
    Left = 3
    Top = 2
    Width = 134
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 3
    Top = 22
    Width = 482
    Height = 246
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
  object SFADM_CERT_ALT_SELECT: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SfadmCertsSelL_Work_Loc''
    DeleteSqlId = ''PWUST_SFADMCERTALTDEL_WORKLOC''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''WORK_LOCATION~0~WORK_LOCATION~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      ''CERT~0~CERT~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~30~Update UserID~N~N~False~False~False~N~MFI_2869~~~'' +
        ''~Default~N~~~''
      
        ''TIME_STAMP~18~Update Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~'')
    SelectedFieldsEditControl.Strings = (
      ''ALT_CERT~Edit'')
    TestParamValues.Strings = (
      ''@USERID=X007084'')
    InputUDVId = ''PWUST_BE3B48AA180CBA95E05387971F0AB3D4''
    DataDefinitions.Strings = (
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object _OBJ3: TsfLabel
    Left = 202
    Top = 4
    Width = 97
    Height = 13
    Caption = ''Cert Work Locations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
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

