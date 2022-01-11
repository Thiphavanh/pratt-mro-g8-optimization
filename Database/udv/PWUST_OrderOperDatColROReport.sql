
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6F036C3C0E182D95E05387971F0A1EEE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6F036C3C0E182D95E05387971F0A1EEE';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperDatColROReport';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect839,849;1848';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 820
  Height = 300
  HelpContext = ''''
  AutoScroll = False
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
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 4
    Width = 800
    Height = 200
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
  object WidOperDatCol_ALTRO: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_MFI_WID_RptOrderOperDCSel''
    LinkedControls.Strings = (
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''SERIAL_NO~10~Serial No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''DAT_COL_TYPE~7~DC Type~N~N~N~N~N~N~~~~~Default~N~~~''
      ''DAT_COL_TITLE~8~Title~N~N~N~N~N~N~~~~~Default~N~~~''
      ''VALUE~5~Value~N~N~False~False~False~N~~~~~Default~N~~~''
      ''DAT_COL_UOM~3~UOM~N~N~N~N~N~N~~~~~Default~N~~~''
      ''DAT_COL_CERT~7~Rq Cert~N~N~False~False~False~N~~~~~Default~N~~~''
      ''UPDT_USERID~7~Upd Usr~N~N~False~False~False~N~~~~~Default~N~~~''
      ''COMMENTS~10~Comments~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SERIAL_NO~Edit''
      ''DAT_COL_TITLE~Edit'')
    TestParamValues.Strings = (
      ''order_control_type=''
      ''order_id=''
      ''oper_key=''
      ''step_key=''
      ''ref_id='')
    InputUDVId = ''MFI_1003689''
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE''
      ''VALUE''
      ''DAT_COL_UOM''
      ''DAT_COL_CERT''
      ''UPDT_USERID''
      ''COMMENTS'')
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

