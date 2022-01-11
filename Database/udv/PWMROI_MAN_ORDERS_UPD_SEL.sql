
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_6B5F01FB4C6B4719A66AD25C13ACE051.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_6B5F01FB4C6B4719A66AD25C13ACE051';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWMROI_MAN_ORDERS_UPD_SEL';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1995';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 900
  Height = 300
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
    Left = 7
    Top = 26
    Width = 865
    Height = 262
    TabStop = False
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
    Left = 7
    Top = 3
    Width = 134
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
  end
  object manualorderupdsel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_MANUAL_ORDER_UPD_SEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UPDATE_ORDER_FLAG~7~Update~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''ORDER_NO~10~Order~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PART_NO~25~Part~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''OLD_MANUAL_REV~10~Manual Rev~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''ORDER_ID~0~ORDER_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      ''SUBJECT_NO~5~Task Group~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUBJECT_REV~5~Task Revision~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''SUBJECT_DESCRIPTION~15~Task Group Description~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~~~''
      
        ''ORDER_STATUS~20~Order Status~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''ORDER_NO~Edit''
      ''UPDATE_ORDER_FLAG~Edit'')
    InsUpdDelUDVId = ''PWMROI_650197E0C01A44AABA0929B6608BE05A''
    DataDefinitions.Strings = (
      ''UPDATE_ORDER_FLAG''
      ''ORDER_NO''
      ''PART_NO''
      ''ORDER_STATUS''
      ''OLD_MANUAL_REV''
      ''ORDER_ID''
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''SUBJECT_DESCRIPTION'')
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

