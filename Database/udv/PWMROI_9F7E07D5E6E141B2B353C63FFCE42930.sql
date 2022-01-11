
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_9F7E07D5E6E141B2B353C63FFCE42930.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWMROI_3701A7AFD2A14650970AA2A632A73570';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_9F7E07D5E6E141B2B353C63FFCE42930';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PRMWOI_MANUAL_PLANS_UPD';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.0.1.20180226~2.4';
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
    Left = 8
    Top = 26
    Width = 882
    Height = 255
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
    Left = 17
    Top = 3
    Width = 172
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Other commands ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
  end
  object manualplansused: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_MANUAL_PLAN_UPD_SEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UPDATE_PLAN_FLAG~5~Update?~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''PLAN_NO~10~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PLAN_REVISION~4~Plan Rev~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''OLD_MANUAL_REV~15~Manual Rev~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''PART_NO~15~Part~N~N~False~False~False~N~~~~~Default~N~~~''
      ''SUBJECT_NO~4~Task Group~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUBJECT_TITLE~12~Task Group Desc~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''REV_STATUS~20~Plan Status~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''PLAN_ID~0~PLAN_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      ''SUBJECT_REV~0~SUBJECT_REV~N~N~True~False~True~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''MANUAL_TYPE~Edit''
      ''MANUAL_SECTION~Edit''
      ''TIME_STAMP~Edit''
      ''LAST_ACTION~Edit''
      ''PLAN_REVISION~Edit''
      ''REV_STATUS~Edit''
      ''UPDATE_PLAN_FLAG~Edit''
      ''SUBJECT_REV~Edit'')
    InsUpdDelUDVId = ''PWMROI_E6880287D45C4CB2BDB31403C2E439E3''
    OtherCommands.Strings = (
      
        ''Desc=Display Plan,Priv={},Visible={},TagValue=,FKey=,ParamsSrc=@'' +
        ''Default,Action=Invoke,Tool=PrPlg Instructions,ReportToolId=,Para'' +
        ''ms(''#39''PLAN_ID = :PLAN_ID,''#39'',PLAN_VERSION = 1,''#39''PLAN_REVISION = :PLAN'' +
        ''_REVISION,''#39'',PLAN_ALTERATIONS = 0,''#39''@InvokeToolMode=EDITMODES.Edit'' +
        ''_PL,''#39'',@InvokeTab=New)'')
    DataDefinitions.Strings = (
      ''UPDATE_PLAN_FLAG''
      ''PLAN_NO''
      ''PLAN_REVISION''
      ''SUBJECT_NO''
      ''PART_NO''
      ''OLD_MANUAL_REV''
      ''REV_STATUS''
      ''PLAN_ID''
      ''SUBJECT_REV'')
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

