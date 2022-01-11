
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6821932B078F5D8FE05387971F0A61D0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6821932B078F5D8FE05387971F0A61D0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_AdminWorkLoc';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 919
  Height = 397
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
  object AdminWorkDept: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''AdminWorkDeptSelect''
    DeleteSqlId = ''AdminWorkDeptDel''
    KeyFieldNames = ''WORK_DEPT''
    LinkedControls.Strings = (
      ''Dept~''
      ''_OBJ6~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''WORK_DEPT~28~Work Dept~N~N~N~N~N~N~~~~~Default~N~''
      ''DEPT_TITLE~35~Dept Title~N~N~N~N~N~N~~~~~Default~N~''
      
        ''WORK_LOC~25~@Lookup(SUPP_LOC_FLAG,''#39''L:Work Loc,S:"Supplier"''#39'',''#39''Sup'' +
        ''plier''#39'')~N~N~N~N~N~N~~~~~Default~N~''
      ''UPDT_USERID~12~Update User ID~N~N~N~N~N~N~MFI_2869~~~~Default~N~''
      
        ''UCF_WORK_DEPT_VCH1~12~UCF_WORK_DEPT_VCH1~N~N~Y~N~N~N~~~~~Default'' +
        ''~N~''
      ''TIME_STAMP~12~Time Stamp~N~N~N~N~N~N~~~~~Default~N~''
      ''OBSOLETE_RECORD_FLAG~5~Obsolete?~N~N~N~N~N~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''WORK_LOC~Edit'')
    TestParamValues.Strings = (
      ''WORK_LOC=EL PASO''
      ''LOCATION_ID=ANY'')
    InputUDVId = ''MFI_1006805''
    InsertUDVId = ''MF1_1001159''
    DataDefinitions.Strings = (
      ''WORK_DEPT''
      ''DEPT_TITLE''
      ''WORK_LOC''
      ''UPDT_USERID''
      ''UCF_WORK_DEPT_VCH1''
      ''TIME_STAMP''
      ''OBSOLETE_RECORD_FLAG'')
    ConsolidatedQuery = False
  end
  object Dept: TsfDBGrid
    Left = 4
    Top = 26
    Width = 479
    Height = 100
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
      ''@Row~Gray~Default~(OBSOLETE_RECORD_FLAG = ''#39''Y''#39'')'')
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfLabel
    Left = 182
    Top = 11
    Width = 57
    Height = 13
    Caption = ''Department''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object AdminWorkCenter: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''AdminWorkCenterSelect''
    DeleteSqlId = ''AdminWorkCenterDel''
    KeyFieldNames = ''WORK_CENTER''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ7~'')
    ParamsSQLSourceName = ''AdminWorkDept''
    PublishParams = True
    SelectedFields.Strings = (
      ''WORK_CENTER~14~Work Center~N~N~N~N~False~N~~~~~Default~N~~''
      ''CENTER_TITLE~28~Center Title~N~N~N~N~False~N~~~~~Default~N~~''
      
        ''WORK_LOC~12~@Lookup(SUPP_LOC_FLAG,''#39''L:Work Loc,S:"Supplier"''#39'',''#39''Sup'' +
        ''plier''#39'')~N~N~False~False~False~N~~~~~Default~N~~''
      ''WORK_DEPT~12~Work Dept~N~N~N~N~False~N~~~~~Default~N~~''
      
        ''WORK_CENTER_TYPE~14~Work Center Type~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~''
      
        ''UCF_WORK_CENTER_VCH255_1~20~Certifications~N~N~False~False~False'' +
        ''~N~~~~~Default~N~~''
      
        ''AVG_LABOR_RATE_PER_HOUR~10~Avg Labor Rate Per Hour~N~N~False~Fal'' +
        ''se~False~N~~~~~Default~N~~''
      
        ''BURDENED_RATE~8~Burdened Rate~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_WORK_CENTER_VCH1~50~UCF_WORK_CENTER_VCH1~N~N~Y~N~False~N~~~~'' +
        ''~Default~N~~''
      
        ''UCF_WORK_CENTER_NUM1~10~UCF_WORK_CENTER_NUM1~N~Y~Y~N~False~N~~~~'' +
        ''~Default~N~~''
      
        ''UCF_WORK_CENTER_FLAG1~1~UCF_WORK_CENTER_FLAG1~N~N~Y~N~False~N~~~'' +
        ''~~Default~N~~''
      
        ''UPDT_USERID~12~Update User ID~N~N~N~N~False~N~MFI_2869~~~~Defaul'' +
        ''t~N~~''
      ''TIME_STAMP~12~Time Stamp~N~N~N~N~False~N~~~~~Default~N~~''
      ''OBSOLETE_RECORD_FLAG~5~Obsolete?~N~N~N~N~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_WORK_CENTER_FLAG1~Edit''
      ''CENTER_TITLE~Edit''
      ''WORK_LOC~Edit''
      ''WORK_CENTER~Edit'')
    TestParamValues.Strings = (
      ''WORK_LOC=EL PASO''
      ''WORK_DEPT='')
    InputUDVId = ''PWUST_6821A50025445D78E05387971F0AFE0D''
    InsertUDVId = ''PWUST_6821A50025405D78E05387971F0AFE0D''
    DataDefinitions.Strings = (
      ''WORK_CENTER''
      ''CENTER_TITLE''
      ''WORK_LOC''
      ''WORK_DEPT''
      ''WORK_CENTER_TYPE''
      ''AVG_LABOR_RATE_PER_HOUR''
      ''BURDENED_RATE''
      ''UCF_WORK_CENTER_VCH1''
      ''UCF_WORK_CENTER_NUM1''
      ''UCF_WORK_CENTER_FLAG1''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''OBSOLETE_RECORD_FLAG'')
    ConsolidatedQuery = False
  end
  object _OBJ3: TsfDBGrid
    Left = 4
    Top = 162
    Width = 479
    Height = 100
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~(OBSOLETE_RECORD_FLAG = ''#39''Y''#39'')'')
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ4: TsfLabel
    Left = 182
    Top = 147
    Width = 61
    Height = 13
    Caption = ''Work Center''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ6: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 172
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
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 2
    TabStop = True
  end
  object _OBJ7: TsfUDVNavigator
    Left = 4
    Top = 139
    Width = 172
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
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
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

