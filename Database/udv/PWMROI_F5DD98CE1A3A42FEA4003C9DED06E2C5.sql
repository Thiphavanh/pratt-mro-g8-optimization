
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_F5DD98CE1A3A42FEA4003C9DED06E2C5.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWMROI_3701A7AFD2A14650970AA2A632A73570';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_F5DD98CE1A3A42FEA4003C9DED06E2C5';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWMROI_PLAN_SUB_AUTH';
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
  Width = 650
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
    Top = 27
    Width = 635
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
    Width = 210
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
  end
  object plansubjauthsel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_PLAN_SUBJECT_AUTHORITY_SEL''
    DeleteSqlId = ''PWMROI_DELETE_PLANSUBJ_AUTH''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SUBJECT_NO~4~Task Group~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUBJECT_REV~4~Task Group Rev~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''SUBJECT_DESCRIPTION~12~Task Desc~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''AUTHORITY_TYPE~6~Auth Type~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''AUTHORITY_DETAIL~25~Auth Detail~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''AUTHORITY_NOTES~15~Auth Notes~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      ''MANUAL_NO~10~Manual No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''MANUAL_REV~6~Manual Rev~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''MANUAL_TYPE~10~Manual Type~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''MANUAL_PROVIDER~6~Provider~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''MANUAL_SECTION~6~Manual Section~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''SB_PART_NO~10~SB Part No~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''AUTHORITY_REV~10~Authority Rev~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''AUTHORITY_REV_DATE~10~Authority Rev Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''ARC_AUTH~20~Arc Authority~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''UPDT_USERID~20~Update User~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''TIME_STAMP~20~Time Stamp~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''LAST_ACTION~10~Last Action~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~'')
    SelectedFieldsEditControl.Strings = (
      ''TIME_STAMP~Edit''
      ''MANUAL_SECTION~Edit''
      ''AUTHORITY_NOTES~Edit''
      ''SUBJECT_REV~Edit''
      ''AUTHORITY_TYPE~Edit''
      ''SUBJECT_NO~Edit'')
    InputUDVId = ''PWMROI_116CFCA6CC764EBA88AB932F6ABB41F8''
    InsertUDVId = ''PWMROI_116CFCA6CC764EBA88AB932F6ABB41F8''
    InsUpdDelUDVId = ''PWMROI_116CFCA6CC764EBA88AB932F6ABB41F8''
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''SUBJECT_DESCRIPTION''
      ''AUTHORITY_TYPE''
      ''AUTHORITY_DETAIL''
      ''AUTHORITY_NOTES''
      ''MANUAL_NO''
      ''MANUAL_REV''
      ''MANUAL_TYPE''
      ''MANUAL_SECTION''
      ''SB_PART_NO''
      ''AUTHORITY_REV''
      ''AUTHORITY_REV_DATE''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION'')
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

