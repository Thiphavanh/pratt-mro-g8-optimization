
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_58F331ADA5E69308E05387971F0A047D.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_58F331ADA5E69308E05387971F0A047D';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StandardNetworkFilterChild';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1130';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
  AutoScroll = False
  Caption = ''child''
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
    Left = 9
    Top = 38
    Width = 480
    Height = 233
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 8
    Top = 7
    Width = 153
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Delete ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
  end
  object setStandardNetworkFilterChild: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SelectStdNetChildDispatch''
    DeleteSqlId = ''PWUST_StandardNetwokChildDel''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope,PWUST_SelectStdNetParentDispatch''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SUB_NETWORK_ACT_NO~8~Sub-Network Act~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~''
      
        ''SUB_NETWORK_ACT_DESC~40~Sub-Network Description~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~''
      
        ''OBSOLETE_RECORD_FLAG~5~Obsolete Flag~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~''
      ''TIME_STAMP~10~Time Stamp~N~N~False~False~True~N~~~~~Default~N~~''
      
        ''UPDT_USERID~7~Update User ID~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~''
      
        ''NETWORK_NO~0~Network Number~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      
        ''NETWORK_NODE_NO~0~Network Node Number~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~''
      
        ''NETWORK_DESC~0~Network Description~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~''
      
        ''SUB_NETWORK_NO~0~Sub-Network No~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''SUB_NETWORK_NODE_NO~0~Sub-Network Node No~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~''
      
        ''SUB_NETWORK_DESC~0~Sub-Network Desc~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~''
      ''ENGINE_TYPE~0~Engine Type~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''ENGINE_TYPE_DESC~0~Engine Type Desc~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~''
      
        ''MODULE_CAT_CODE~12~Module/Category Code~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~''
      
        ''MODULE_CAT_DESC~40~Module/Category Description~N~N~False~False~F'' +
        ''alse~N~~~~~Default~N~~''
      ''GATE~15~Gate~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''CONTROL_KEY~6~Control Key~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~''
      
        ''DURATION_UNIT~13~Duration Unit~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''EWORKSCOPING_FLAG~4~Eworkscoping Flag~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~''
      ''LID_NO~14~Lid No~N~N~False~False~False~N~~~~~Default~N~~''
      ''DURATION~7~Duration~N~N~False~False~False~N~~~~~Default~N~~''
      ''WORK~10~Work~N~N~False~False~False~N~~~~~Default~N~~''
      ''WORK_UNIT~12~Work Unit~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''WORK_CENTER~10~Work Center~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      
        ''NETWORK_ACT_NO~0~Network Act~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''NETWORK_ACT_DESC~0~Network Act Desc~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~''
      ''ACTION~0~ACTION~N~N~False~False~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''STD_NETWORK_NO~Edit''
      ''TIME_STAMP~Edit''
      ''SUB_NETWORK_ACT_NO~Edit'')
    TestParamValues.Strings = (
      ''NETWORK_ACT_NO_P=1'')
    InsUpdDelUDVId = ''PWUST_59B656B118997285E05387971F0ADC51''
    DataDefinitions.Strings = (
      ''SUB_NETWORK_ACT_NO''
      ''SUB_NETWORK_ACT_DESC''
      ''OBSOLETE_RECORD_FLAG''
      ''TIME_STAMP''
      ''UPDT_USERID''
      ''NETWORK_NO''
      ''NETWORK_NODE_NO''
      ''NETWORK_DESC''
      ''SUB_NETWORK_NO''
      ''SUB_NETWORK_NODE_NO''
      ''SUB_NETWORK_DESC''
      ''ENGINE_TYPE''
      ''ENGINE_TYPE_DESC''
      ''MODULE_CAT_CODE''
      ''MODULE_CAT_DESC''
      ''GATE''
      ''CONTROL_KEY''
      ''DURATION_UNIT''
      ''EWORKSCOPING_FLAG''
      ''LID_NO''
      ''DURATION''
      ''WORK''
      ''WORK_UNIT''
      ''WORK_CENTER''
      ''NETWORK_ACT_NO''
      ''NETWORK_ACT_DESC''
      ''ACTION'')
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

